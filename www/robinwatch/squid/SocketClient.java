package www.robinwatch.squid;

import android.support.v4.view.MotionEventCompat;
import com.obins.anne.utils.AppConfig;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import org.json.JSONArray;
import org.json.JSONObject;
import www.robinwatch.squid.network.HttpCallback;
import www.robinwatch.squid.utils.C0213L;

public class SocketClient {
    DataInputStream din;
    DataOutputStream dout;
    Boolean is_run1 = Boolean.valueOf(true);
    Boolean is_run2 = Boolean.valueOf(true);
    Boolean is_run3 = Boolean.valueOf(true);
    Boolean is_run4 = Boolean.valueOf(true);
    Boolean is_run5 = Boolean.valueOf(true);
    Socket socket;

    public void send(final String ip, final int port, final byte[] data) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    String sendBuf = "tison fuck you:";
                    for (int i = 0; i < 8; i++) {
                        sendBuf = new StringBuilder(String.valueOf(sendBuf)).append(Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK)).append(" ").toString();
                    }
                    C0213L.m19i(sendBuf);
                    SocketClient.this.socket = new Socket(ip, port);
                    SocketClient.this.dout = new DataOutputStream(SocketClient.this.socket.getOutputStream());
                    SocketClient.this.dout.write(data);
                    SocketClient.this.dout.writeBytes("\n");
                    SocketClient.this.dout.flush();
                    SocketClient.this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void udpBrocast(int port, String dataString, String myip, String dev_id, HttpCallback cb) {
        final String str = dataString;
        final int i = port;
        final String str2 = myip;
        final String str3 = dev_id;
        final HttpCallback httpCallback = cb;
        new Thread(new Runnable() {
            public void run() {
                final DatagramPacket dataPacket = new DatagramPacket(new byte[128], 128);
                JSONArray jArray = new JSONArray();
                Exception e;
                try {
                    DatagramPacket receivePacket;
                    IOException e2;
                    DatagramSocket udpSocket = new DatagramSocket();
                    byte[] data = str.getBytes();
                    dataPacket.setData(data);
                    dataPacket.setLength(data.length);
                    dataPacket.setPort(i);
                    dataPacket.setAddress(InetAddress.getByName("255.255.255.255"));
                    udpSocket.send(dataPacket);
                    final DatagramSocket datagramSocket = udpSocket;
                    new Thread() {
                        public void run() {
                            int i = 0;
                            while (SocketClient.this.is_run1.booleanValue() && i < 50) {
                                try {
                                    i++;
                                    Thread.sleep(1000);
                                    datagramSocket.send(dataPacket);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            datagramSocket.close();
                            SocketClient.this.is_run2 = Boolean.valueOf(false);
                        }
                    }.start();
                    DatagramPacket receivePacket2 = null;
                    while (SocketClient.this.is_run2.booleanValue()) {
                        try {
                            byte[] recvdata = new byte[256];
                            receivePacket = new DatagramPacket(recvdata, 128);
                            if (receivePacket.getAddress() != null) {
                                String ipString = receivePacket.getAddress().toString().replaceAll("\\/", AppConfig.SERVER_IP);
                                C0213L.m19i("xx myip=" + str2 + ",ipstring=" + ipString);
                                if (ipString.equals(str2)) {
                                    receivePacket2 = receivePacket;
                                } else {
                                    String codeString = new String(recvdata, 0, receivePacket.getLength());
                                    JSONObject jsonObject = DeviceData.parseData(recvdata);
                                    if (jsonObject == null) {
                                        receivePacket2 = receivePacket;
                                    } else {
                                        jsonObject.put("ip", ipString);
                                        C0213L.m19i("fuck ver=" + jsonObject.getString("ver") + ",head=" + jsonObject.getString("head") + ",mac=" + jsonObject.getString("mac") + ",id=" + jsonObject.getString("id") + ",state=" + jsonObject.getString("state") + ",check=" + jsonObject.getString("check"));
                                        if (jsonObject.getString("id").equals(str3)) {
                                            jArray.put(jsonObject);
                                            SocketClient.this.is_run1 = Boolean.valueOf(false);
                                            SocketClient.this.is_run2 = Boolean.valueOf(false);
                                        } else {
                                            receivePacket2 = receivePacket;
                                        }
                                    }
                                }
                            }
                            receivePacket2 = receivePacket;
                        } catch (IOException e3) {
                            e2 = e3;
                            receivePacket = receivePacket2;
                        } catch (Exception e4) {
                            e = e4;
                            receivePacket = receivePacket2;
                        }
                        try {
                            udpSocket.receive(receivePacket);
                        } catch (Exception e5) {
                        } catch (IOException e6) {
                            e2 = e6;
                        }
                    }
                    httpCallback.excute(jArray.toString());
                    receivePacket = receivePacket2;
                    return;
                    e2.printStackTrace();
                    httpCallback.excute(jArray.toString());
                    return;
                    e.printStackTrace();
                    httpCallback.excute(jArray.toString());
                } catch (IOException e62) {
                    e2 = e62;
                } catch (Exception e7) {
                    e = e7;
                }
            }
        }).start();
    }

    public void udpBrocastSearchall(int port, String dataString, String myip, HttpCallback cb) {
        final String str = dataString;
        final int i = port;
        final String str2 = myip;
        final HttpCallback httpCallback = cb;
        new Thread(new Runnable() {
            public void run() {
                final DatagramPacket dataPacket = new DatagramPacket(new byte[128], 128);
                JSONArray jArray = new JSONArray();
                Exception e;
                try {
                    DatagramPacket receivePacket;
                    IOException e2;
                    DatagramSocket udpSocket = new DatagramSocket();
                    byte[] data = str.getBytes();
                    dataPacket.setData(data);
                    dataPacket.setLength(data.length);
                    dataPacket.setPort(i);
                    dataPacket.setAddress(InetAddress.getByName("255.255.255.255"));
                    udpSocket.send(dataPacket);
                    final DatagramSocket datagramSocket = udpSocket;
                    new Thread() {
                        public void run() {
                            int i = 0;
                            while (SocketClient.this.is_run3.booleanValue() && i < 15) {
                                try {
                                    i++;
                                    Thread.sleep(1000);
                                    datagramSocket.send(dataPacket);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            datagramSocket.close();
                            SocketClient.this.is_run4 = Boolean.valueOf(false);
                        }
                    }.start();
                    DatagramPacket receivePacket2 = null;
                    while (SocketClient.this.is_run4.booleanValue()) {
                        try {
                            byte[] recvdata = new byte[256];
                            receivePacket = new DatagramPacket(recvdata, 128);
                            if (receivePacket.getAddress() != null) {
                                String ipString = receivePacket.getAddress().toString().replaceAll("\\/", AppConfig.SERVER_IP);
                                C0213L.m19i("xx myip=" + str2 + ",ipstring=" + ipString);
                                if (ipString.equals(str2)) {
                                    receivePacket2 = receivePacket;
                                } else {
                                    String codeString = new String(recvdata, 0, receivePacket.getLength());
                                    JSONObject jsonObject = DeviceData.parseData(recvdata);
                                    if (jsonObject == null) {
                                        receivePacket2 = receivePacket;
                                    } else {
                                        jsonObject.put("ip", ipString);
                                        C0213L.m19i("fuck ver=" + jsonObject.getString("ver") + ",head=" + jsonObject.getString("head") + ",mac=" + jsonObject.getString("mac") + ",id=" + jsonObject.getString("id") + ",state=" + jsonObject.getString("state") + ",check=" + jsonObject.getString("check"));
                                        String idString = jsonObject.getString("id");
                                        jArray.put(jsonObject);
                                        C0213L.m19i("收到来自：" + ipString + "UDP请求。。。\n");
                                        C0213L.m19i("请求内容：" + codeString + "\n\n");
                                    }
                                }
                            }
                            receivePacket2 = receivePacket;
                        } catch (IOException e3) {
                            e2 = e3;
                            receivePacket = receivePacket2;
                        } catch (Exception e4) {
                            e = e4;
                            receivePacket = receivePacket2;
                        }
                        try {
                            udpSocket.receive(receivePacket);
                        } catch (Exception e5) {
                        } catch (IOException e6) {
                            e2 = e6;
                        }
                    }
                    httpCallback.excute(jArray.toString());
                    receivePacket = receivePacket2;
                    return;
                    e2.printStackTrace();
                    httpCallback.excute(jArray.toString());
                    return;
                    e.printStackTrace();
                    httpCallback.excute(jArray.toString());
                } catch (IOException e62) {
                    e2 = e62;
                } catch (Exception e7) {
                    e = e7;
                }
            }
        }).start();
    }

    public void udpBrocastSoon(int port, String dataString, String myip, HttpCallback cb) {
        final String str = dataString;
        final int i = port;
        final String str2 = myip;
        final HttpCallback httpCallback = cb;
        new Thread(new Runnable() {
            public void run() {
                final DatagramPacket dataPacket = new DatagramPacket(new byte[128], 128);
                JSONArray jArray = new JSONArray();
                Exception e;
                try {
                    DatagramPacket receivePacket;
                    IOException e2;
                    DatagramSocket udpSocket = new DatagramSocket();
                    byte[] data = str.getBytes();
                    dataPacket.setData(data);
                    dataPacket.setLength(data.length);
                    dataPacket.setPort(i);
                    dataPacket.setAddress(InetAddress.getByName("255.255.255.255"));
                    udpSocket.send(dataPacket);
                    final DatagramSocket datagramSocket = udpSocket;
                    new Thread() {
                        public void run() {
                            try {
                                datagramSocket.send(dataPacket);
                                Thread.sleep(1000);
                                datagramSocket.send(dataPacket);
                                Thread.sleep(1000);
                                SocketClient.this.is_run5 = Boolean.valueOf(false);
                                datagramSocket.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    DatagramPacket receivePacket2 = null;
                    while (SocketClient.this.is_run5.booleanValue()) {
                        try {
                            byte[] recvdata = new byte[256];
                            receivePacket = new DatagramPacket(recvdata, 128);
                            if (receivePacket.getAddress() != null) {
                                String ipString = receivePacket.getAddress().toString().replaceAll("\\/", AppConfig.SERVER_IP);
                                C0213L.m19i("xx myip=" + str2 + ",ipstring=" + ipString);
                                if (ipString.equals(str2)) {
                                    receivePacket2 = receivePacket;
                                } else {
                                    String codeString = new String(recvdata, 0, receivePacket.getLength());
                                    JSONObject jsonObject = DeviceData.parseData(recvdata);
                                    if (jsonObject == null) {
                                        receivePacket2 = receivePacket;
                                    } else {
                                        jsonObject.put("ip", ipString);
                                        C0213L.m19i("fuck ver=" + jsonObject.getString("ver") + ",head=" + jsonObject.getString("head") + ",mac=" + jsonObject.getString("mac") + ",id=" + jsonObject.getString("id") + ",state=" + jsonObject.getString("state") + ",check=" + jsonObject.getString("check"));
                                        String idString = jsonObject.getString("id");
                                        jArray.put(jsonObject);
                                        C0213L.m19i("收到来自：" + ipString + "UDP请求。。。\n");
                                        C0213L.m19i("请求内容：" + codeString + "\n\n");
                                    }
                                }
                            }
                            receivePacket2 = receivePacket;
                        } catch (IOException e3) {
                            e2 = e3;
                            receivePacket = receivePacket2;
                        } catch (Exception e4) {
                            e = e4;
                            receivePacket = receivePacket2;
                        }
                        try {
                            udpSocket.receive(receivePacket);
                        } catch (Exception e5) {
                        } catch (IOException e6) {
                            e2 = e6;
                        }
                    }
                    httpCallback.excute(jArray.toString());
                    receivePacket = receivePacket2;
                    return;
                    e2.printStackTrace();
                    httpCallback.excute(jArray.toString());
                    return;
                    e.printStackTrace();
                    httpCallback.excute(jArray.toString());
                } catch (IOException e62) {
                    e2 = e62;
                } catch (Exception e7) {
                    e = e7;
                }
            }
        }).start();
    }

    public void close() throws IOException {
    }
}
