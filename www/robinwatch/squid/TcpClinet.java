package www.robinwatch.squid;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import com.obins.anne.utils.AppConfig;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import org.json.JSONObject;
import www.robinwatch.squid.network.HttpCallback;

public class TcpClinet {
    private HttpCallback cbCallback;
    private Socket clientSocket;
    String dev_type;
    File file;
    String filePath = AppConfig.SERVER_IP;
    FileInputStream file_in;
    OutputStream out;
    private BufferedReader sin;
    private PrintWriter sout;
    String version;

    public TcpClinet(String ipstring, int port, String filePath, String dev_type, String version, HttpCallback cb) throws IOException {
        this.filePath = filePath;
        this.dev_type = dev_type;
        this.version = version;
        this.cbCallback = cb;
        this.clientSocket = new Socket(ipstring, port);
        this.sin = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.out = this.clientSocket.getOutputStream();
        this.sout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.out)), true);
        this.file = new File(filePath);
        this.file_in = new FileInputStream(this.file);
    }

    public boolean checkFw() {
        int i;
        byte[] data = new byte[256];
        for (i = 0; i < 256; i++) {
            data[i] = (byte) -1;
        }
        try {
            int len = this.file_in.read(data, 0, data.length);
            String readBuf = "OTA Version Check :";
            for (i = 0; i < 31; i++) {
                readBuf = new StringBuilder(String.valueOf(readBuf)).append(Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK)).append(" ").toString();
            }
            System.out.println(readBuf);
            System.out.println("version = " + this.version + ", dev_type = " + this.dev_type);
            if (data[0] == (byte) 118 && data[1] == (byte) -127 && data[2] == (byte) -86) {
                Boolean version_err = Boolean.valueOf(false);
                if (Integer.valueOf(this.version).intValue() % 2 == 0) {
                    if (data[8] != (byte) 0) {
                        System.out.println("奇数版本 erro");
                        version_err = Boolean.valueOf(true);
                    }
                } else if (data[8] != (byte) 1) {
                    System.out.println("偶数版本 erro");
                    version_err = Boolean.valueOf(true);
                }
                if (data[9] != Integer.valueOf(this.version).intValue()) {
                    version_err = Boolean.valueOf(true);
                }
                String a = Integer.toHexString(data[10] & MotionEventCompat.ACTION_MASK);
                if (a.length() == 1) {
                    a = "0" + a;
                }
                String b = Integer.toHexString(data[11] & MotionEventCompat.ACTION_MASK);
                if (b.length() == 1) {
                    b = "0" + b;
                }
                if (!new StringBuilder(String.valueOf(b)).append(a).toString().equals(this.dev_type)) {
                    System.out.println("dev_type erro");
                    version_err = Boolean.valueOf(true);
                }
                if (version_err.booleanValue()) {
                    System.out.println("version  err");
                    return false;
                }
                try {
                    this.file_in.close();
                    this.file_in = new FileInputStream(this.file);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            System.out.println("7681aa err");
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void SendStaBin() throws IOException {
        int pktnum = 0;
        long sendsum = 0;
        boolean update_success = true;
        if (checkFw()) {
            long filesum = this.file.length();
            byte[] DATA_UP_FW_START_CMD = new byte[4];
            DATA_UP_FW_START_CMD[1] = (byte) 32;
            DATA_UP_FW_START_CMD[2] = (byte) 24;
            DATA_UP_FW_START_CMD[3] = (byte) -2;
            Send(DATA_UP_FW_START_CMD);
            if (this.sin.read() != 21) {
                System.out.println("ret_code err DATA_UP_FW_START_CMD");
                backerr();
                return;
            }
            byte[] DATA_UP_FW_CAN;
            System.out.println(" ret_code NAK");
            int len;
            do {
                JSONObject jsonObject;
                byte[] DATA_UP_FW_DATA_HEAD = new byte[]{(byte) 1, (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & pktnum) >> 8), (byte) (pktnum & MotionEventCompat.ACTION_MASK)};
                byte[] data = new byte[256];
                for (int i = 0; i < 256; i++) {
                    data[i] = (byte) -1;
                }
                len = this.file_in.read(data, 0, data.length);
                if (len > 0) {
                    sendsum += (long) len;
                }
                int crc_xmodem = CRC_XModem(data);
                byte[] out_bytes = byteMerger(DATA_UP_FW_START_CMD, DATA_UP_FW_DATA_HEAD, data, new byte[]{(byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & crc_xmodem) >> 8), (byte) (crc_xmodem & MotionEventCompat.ACTION_MASK)});
                System.out.print("send paket " + pktnum + ", ");
                Send(out_bytes);
                pktnum++;
                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("code", "10001");
                    jsonObject.put("result", pktnum);
                    this.cbCallback.excute(jsonObject.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int ret_code = this.sin.read();
                if (ret_code == 6) {
                    System.out.println("ret_code ACK");
                } else if (ret_code == 21) {
                    System.out.println("ret_code NAK");
                    backerr();
                    update_success = false;
                } else {
                    System.out.println("ret_code err");
                    backerr();
                    update_success = false;
                }
                if (sendsum == filesum || !update_success) {
                    DATA_UP_FW_CAN = new byte[5];
                    DATA_UP_FW_CAN[1] = (byte) 32;
                    DATA_UP_FW_CAN[2] = (byte) 24;
                    DATA_UP_FW_CAN[3] = (byte) -2;
                    DATA_UP_FW_CAN[4] = (byte) 24;
                    Send(DATA_UP_FW_CAN);
                    backerr();
                    return;
                }
                byte[] DATA_UP_FW_EOT = new byte[5];
                DATA_UP_FW_EOT[1] = (byte) 32;
                DATA_UP_FW_EOT[2] = (byte) 24;
                DATA_UP_FW_EOT[3] = (byte) -2;
                DATA_UP_FW_EOT[4] = (byte) 4;
                Send(DATA_UP_FW_EOT);
                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("code", "10000");
                    this.cbCallback.excute(jsonObject.toString());
                    return;
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                    return;
                }
            } while (len > 0);
            if (sendsum == filesum) {
            }
            DATA_UP_FW_CAN = new byte[5];
            DATA_UP_FW_CAN[1] = (byte) 32;
            DATA_UP_FW_CAN[2] = (byte) 24;
            DATA_UP_FW_CAN[3] = (byte) -2;
            DATA_UP_FW_CAN[4] = (byte) 24;
            Send(DATA_UP_FW_CAN);
            backerr();
            return;
        }
        backerr();
    }

    private void backerr() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "10002");
            this.cbCallback.excute(jsonObject.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Close() throws IOException {
        this.clientSocket.close();
    }

    public void Send(String data) {
        this.sout.write(data);
        this.sout.flush();
    }

    public void Send(byte[] data) throws IOException {
        this.out.write(data);
        this.out.flush();
    }

    public void Send(byte[] header, byte[] data) throws IOException {
        System.out.println(Integer.toHexString(CRC_XModem(data)));
        byte[] crc = new byte[]{(byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & crc_xmodem) >> 8), (byte) (crc_xmodem & MotionEventCompat.ACTION_MASK)};
        System.out.println("0x" + Byte.toString(crc[0]) + " 0x" + Byte.toString(crc[1]));
        byte[] out_bytes = byteMerger(header, data, crc);
        System.out.println(Arrays.toString(out_bytes));
        this.out.write(out_bytes);
        this.out.flush();
        System.out.println(this.sin.read());
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[(byte_1.length + byte_2.length)];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2, byte[] byte_3) {
        byte[] byte_ret = new byte[((byte_1.length + byte_2.length) + byte_3.length)];
        System.arraycopy(byte_1, 0, byte_ret, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_ret, byte_1.length, byte_2.length);
        System.arraycopy(byte_3, 0, byte_ret, byte_1.length + byte_2.length, byte_3.length);
        return byte_ret;
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2, byte[] byte_3, byte[] byte_4) {
        byte[] byte_ret = new byte[(((byte_1.length + byte_2.length) + byte_3.length) + byte_4.length)];
        System.arraycopy(byte_1, 0, byte_ret, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_ret, byte_1.length, byte_2.length);
        System.arraycopy(byte_3, 0, byte_ret, byte_1.length + byte_2.length, byte_3.length);
        System.arraycopy(byte_4, 0, byte_ret, (byte_1.length + byte_2.length) + byte_3.length, byte_4.length);
        return byte_ret;
    }

    public int CRC_XModem(byte[] bytes) {
        int crc = 0;
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit;
                boolean c15;
                if (((b >> (7 - i)) & 1) == 1) {
                    bit = true;
                } else {
                    bit = false;
                }
                if (((crc >> 15) & 1) == 1) {
                    c15 = true;
                } else {
                    c15 = false;
                }
                crc <<= 1;
                if ((c15 ^ bit) != 0) {
                    crc ^= 4129;
                }
            }
        }
        return crc & SupportMenu.USER_MASK;
    }
}
