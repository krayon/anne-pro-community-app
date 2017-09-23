package www.robinwatch.squid;

import android.support.v4.view.MotionEventCompat;
import com.obins.anne.utils.AppConfig;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import www.robinwatch.squid.network.HttpCallback;
import www.robinwatch.squid.utils.C0213L;

public class LanCtlDevice {
    boolean is_run1 = true;
    boolean is_run2 = true;

    public void lanCtlDevice(int port, byte[] dataByte, String deviceip, HttpCallback cb) {
        final byte[] bArr = dataByte;
        final int i = port;
        final String str = deviceip;
        final HttpCallback httpCallback = cb;
        new Thread(new Runnable() {
            public void run() {
                DatagramPacket dataPacket = new DatagramPacket(new byte[128], 128);
                String backDataString = AppConfig.SERVER_IP;
                Exception e;
                try {
                    IOException e2;
                    DatagramPacket receivePacket;
                    DatagramSocket udpSocket = new DatagramSocket();
                    byte[] data = bArr;
                    C0213L.m19i(" data.length = " + data.length);
                    String logString = " Data : ";
                    int i = 0;
                    while (i < data.length) {
                        try {
                            logString = new StringBuilder(String.valueOf(logString)).append(new StringBuilder(String.valueOf(Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK))).append(" ").toString()).toString();
                            i++;
                        } catch (Exception e3) {
                        } catch (IOException e4) {
                            e2 = e4;
                        }
                    }
                    C0213L.m19i(logString);
                    dataPacket.setData(data);
                    dataPacket.setLength(data.length);
                    dataPacket.setPort(i);
                    dataPacket.setAddress(InetAddress.getByName(str));
                    udpSocket.send(dataPacket);
                    final DatagramSocket datagramSocket = udpSocket;
                    new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            datagramSocket.close();
                            LanCtlDevice.this.is_run1 = false;
                        }
                    }.start();
                    DatagramPacket receivePacket2 = null;
                    while (LanCtlDevice.this.is_run1) {
                        try {
                            byte[] recvdata = new byte[256];
                            receivePacket = new DatagramPacket(recvdata, 128);
                            udpSocket.receive(receivePacket);
                            if (receivePacket.getAddress() != null) {
                                String ipString = receivePacket.getAddress().toString().replaceAll("\\/", AppConfig.SERVER_IP);
                                backDataString = new StringBuilder(String.valueOf(recvdata[0])).toString();
                                LanCtlDevice.this.is_run1 = false;
                            }
                            receivePacket2 = receivePacket;
                        } catch (IOException e5) {
                            e2 = e5;
                            receivePacket = receivePacket2;
                        } catch (Exception e6) {
                            e = e6;
                            receivePacket = receivePacket2;
                        }
                    }
                    httpCallback.excute(backDataString);
                    receivePacket = receivePacket2;
                    return;
                    e2.printStackTrace();
                    httpCallback.excute(backDataString);
                    return;
                    e.printStackTrace();
                    httpCallback.excute(backDataString);
                } catch (IOException e42) {
                    e2 = e42;
                } catch (Exception e7) {
                    e = e7;
                }
            }
        }).start();
    }

    public void lanCtlDeviceLongTimeOut(int port, byte[] dataByte, String deviceip, HttpCallback cb) {
        final byte[] bArr = dataByte;
        final int i = port;
        final String str = deviceip;
        final HttpCallback httpCallback = cb;
        new Thread(new Runnable() {
            public void run() {
                DatagramPacket dataPacket = new DatagramPacket(new byte[128], 128);
                String backDataString = AppConfig.SERVER_IP;
                Exception e;
                try {
                    IOException e2;
                    DatagramPacket receivePacket;
                    DatagramSocket udpSocket = new DatagramSocket();
                    byte[] data = bArr;
                    C0213L.m19i(" data.length = " + data.length);
                    String logString = " Data : ";
                    int i = 0;
                    while (i < data.length) {
                        try {
                            logString = new StringBuilder(String.valueOf(logString)).append(new StringBuilder(String.valueOf(Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK))).append(" ").toString()).toString();
                            i++;
                        } catch (Exception e3) {
                        } catch (IOException e4) {
                            e2 = e4;
                        }
                    }
                    C0213L.m19i(logString);
                    dataPacket.setData(data);
                    dataPacket.setLength(data.length);
                    dataPacket.setPort(i);
                    dataPacket.setAddress(InetAddress.getByName(str));
                    udpSocket.send(dataPacket);
                    final DatagramSocket datagramSocket = udpSocket;
                    new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            datagramSocket.close();
                            LanCtlDevice.this.is_run2 = false;
                        }
                    }.start();
                    DatagramPacket receivePacket2 = null;
                    while (LanCtlDevice.this.is_run2) {
                        try {
                            byte[] recvdata = new byte[256];
                            receivePacket = new DatagramPacket(recvdata, 128);
                            udpSocket.receive(receivePacket);
                            if (receivePacket.getAddress() != null) {
                                String ipString = receivePacket.getAddress().toString().replaceAll("\\/", AppConfig.SERVER_IP);
                                backDataString = new StringBuilder(String.valueOf(recvdata[0])).toString();
                                LanCtlDevice.this.is_run2 = false;
                            }
                            receivePacket2 = receivePacket;
                        } catch (IOException e5) {
                            e2 = e5;
                            receivePacket = receivePacket2;
                        } catch (Exception e6) {
                            e = e6;
                            receivePacket = receivePacket2;
                        }
                    }
                    httpCallback.excute(backDataString);
                    receivePacket = receivePacket2;
                    return;
                    e2.printStackTrace();
                    httpCallback.excute(backDataString);
                    return;
                    e.printStackTrace();
                    httpCallback.excute(backDataString);
                } catch (IOException e42) {
                    e2 = e42;
                } catch (Exception e7) {
                    e = e7;
                }
            }
        }).start();
    }
}
