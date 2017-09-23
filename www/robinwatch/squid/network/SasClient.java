package www.robinwatch.squid.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import www.robinwatch.squid.Config;
import www.robinwatch.squid.utils.C0213L;

public class SasClient implements Runnable {
    private static final int PORT = 5359;
    HttpCarPositonCallback cb;
    private String chat_in;
    DataInputStream din;
    DataOutputStream dout;
    Socket socket;
    Thread thread;

    public SasClient(HttpCarPositonCallback callback) throws UnknownHostException, IOException {
        this.cb = callback;
        try {
            this.socket = new Socket(Config.BaseIp, PORT);
            this.din = new DataInputStream(this.socket.getInputStream());
            this.dout = new DataOutputStream(this.socket.getOutputStream());
            this.thread = new Thread(this);
        } catch (Exception e) {
            this.cb.excute(null, 0);
            e.printStackTrace();
        }
    }

    public void Stop() throws InterruptedException, IOException {
        if (this.socket != null) {
            this.socket.close();
        }
    }

    public int send(String data) throws IOException {
        if (this.dout != null) {
            this.dout.write(data.getBytes());
        }
        return 0;
    }

    public void startReadThread() {
        if (this.thread != null) {
            this.thread.start();
        }
    }

    public void run() {
        byte[] buffer = new byte[1024];
        while (!this.socket.isClosed() && this.socket.isConnected()) {
            try {
                int n = this.din.read(buffer);
                if (n == -1) {
                    break;
                } else if (this.cb != null) {
                    this.cb.excute(buffer, n);
                }
            } catch (IOException e) {
            }
        }
        C0213L.m19i("newcar Socket disConneted");
    }
}
