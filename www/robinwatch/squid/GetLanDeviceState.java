package www.robinwatch.squid;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import www.robinwatch.squid.network.HttpCallback;

public class GetLanDeviceState {
    DataInputStream din;
    DataOutputStream dout;
    Socket socket;

    public void getLanDeviceState(int port, String dataString, String deviceip, HttpCallback cb) {
        final String str = dataString;
        final int i = port;
        final String str2 = deviceip;
        final HttpCallback httpCallback = cb;
        new Thread(new Runnable() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r21 = this;
                r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
                r0 = new byte[r3];
                r16 = r0;
                r6 = new java.net.DatagramPacket;
                r0 = r16;
                r6.<init>(r0, r3);
                r13 = 0;
                r10 = new org.json.JSONArray;
                r10.<init>();
                r18 = new java.net.DatagramSocket;	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r18.<init>();	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r0 = r21;
                r0 = r2;	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r19 = r0;
                r5 = r19.getBytes();	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r6.setData(r5);	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r0 = r5.length;	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r19 = r0;
                r0 = r19;
                r6.setLength(r0);	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r0 = r21;
                r0 = r3;	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r19 = r0;
                r0 = r19;
                r6.setPort(r0);	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r0 = r21;
                r0 = r4;	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r19 = r0;
                r17 = java.net.InetAddress.getByName(r19);	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r0 = r17;
                r6.setAddress(r0);	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r0 = r18;
                r0.send(r6);	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r19 = new www.robinwatch.squid.GetLanDeviceState$1$1;	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r0 = r19;
                r1 = r21;
                r2 = r18;
                r0.<init>(r2);	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r19.start();	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r19 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
                r0 = r19;
                r15 = new byte[r0];	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r14 = new java.net.DatagramPacket;	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r14.<init>(r15, r3);	 Catch:{ IOException -> 0x0170, Exception -> 0x0183 }
                r0 = r18;
                r0.receive(r14);	 Catch:{ Exception -> 0x0196, IOException -> 0x019c }
            L_0x006a:
                r19 = r14.getAddress();	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                if (r19 == 0) goto L_0x0160;
            L_0x0070:
                r19 = r14.getAddress();	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r12 = r19.toString();	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = "\\/";
                r20 = "";
                r0 = r19;
                r1 = r20;
                r9 = r12.replaceAll(r0, r1);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r4 = new java.lang.String;	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = 0;
                r20 = r14.getLength();	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r0 = r19;
                r1 = r20;
                r4.<init>(r15, r0, r1);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r11 = www.robinwatch.squid.DeviceData.parseData(r15);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                if (r11 != 0) goto L_0x00a6;
            L_0x0099:
                r0 = r21;
                r0 = r5;	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r0;
                r20 = "";
                r19.excute(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r13 = r14;
            L_0x00a5:
                return;
            L_0x00a6:
                r19 = "ip";
                r0 = r19;
                r11.put(r0, r9);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "fuck ver=";
                r19.<init>(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "ver";
                r0 = r20;
                r20 = r11.getString(r0);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = ",head=";
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "head";
                r0 = r20;
                r20 = r11.getString(r0);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = ",mac=";
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "mac";
                r0 = r20;
                r20 = r11.getString(r0);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = ",id=";
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "id";
                r0 = r20;
                r20 = r11.getString(r0);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = ",state=";
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "state";
                r0 = r20;
                r20 = r11.getString(r0);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = ",check=";
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "check";
                r0 = r20;
                r20 = r11.getString(r0);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.toString();	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                www.robinwatch.squid.utils.C0213L.m19i(r19);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = "id";
                r0 = r19;
                r8 = r11.getString(r0);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r10.put(r11);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "收到来自：";
                r19.<init>(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r0 = r19;
                r19 = r0.append(r9);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "UDP请求。。。\n";
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.toString();	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                www.robinwatch.squid.utils.C0213L.m19i(r19);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "请求内容：";
                r19.<init>(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r0 = r19;
                r19 = r0.append(r4);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r20 = "\n\n";
                r19 = r19.append(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r19.toString();	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                www.robinwatch.squid.utils.C0213L.m19i(r19);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
            L_0x0160:
                r0 = r21;
                r0 = r5;	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19 = r0;
                r20 = r10.toString();	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r19.excute(r20);	 Catch:{ IOException -> 0x019c, Exception -> 0x0199 }
                r13 = r14;
                goto L_0x00a5;
            L_0x0170:
                r7 = move-exception;
            L_0x0171:
                r7.printStackTrace();
                r0 = r21;
                r0 = r5;
                r19 = r0;
                r20 = r10.toString();
                r19.excute(r20);
                goto L_0x00a5;
            L_0x0183:
                r7 = move-exception;
            L_0x0184:
                r7.printStackTrace();
                r0 = r21;
                r0 = r5;
                r19 = r0;
                r20 = r10.toString();
                r19.excute(r20);
                goto L_0x00a5;
            L_0x0196:
                r19 = move-exception;
                goto L_0x006a;
            L_0x0199:
                r7 = move-exception;
                r13 = r14;
                goto L_0x0184;
            L_0x019c:
                r7 = move-exception;
                r13 = r14;
                goto L_0x0171;
                */
                throw new UnsupportedOperationException("Method not decompiled: www.robinwatch.squid.GetLanDeviceState.1.run():void");
            }
        }).start();
    }
}
