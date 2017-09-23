package www.robinwatch.squid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import www.robinwatch.squid.network.HttpCallback;

public class DownloadFile {
    private HttpCallback cb;
    private String savename;
    private String savepath;
    private String url;

    class C01961 extends Thread {
        C01961() {
        }

        public void run() {
            JSONObject jsonObject;
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(DownloadFile.this.url);
                jsonObject = new JSONObject();
                try {
                    HttpEntity entity = client.execute(get).getEntity();
                    long length = entity.getContentLength();
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        fileOutputStream = new FileOutputStream(new File(new File(DownloadFile.this.savepath), DownloadFile.this.savename));
                        byte[] buf = new byte[1024];
                        while (true) {
                            int ch = is.read(buf);
                            if (ch == -1) {
                                break;
                            }
                            fileOutputStream.write(buf, 0, ch);
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    jsonObject.put("code", "10000");
                    DownloadFile.this.cb.excute(jsonObject.toString());
                } catch (MalformedURLException e) {
                    try {
                        jsonObject.put("code", "10001");
                        DownloadFile.this.cb.excute(jsonObject.toString());
                    } catch (Exception e2) {
                    }
                    e.printStackTrace();
                } catch (ClientProtocolException e3) {
                    try {
                        jsonObject.put("code", "10001");
                        DownloadFile.this.cb.excute(jsonObject.toString());
                    } catch (Exception e4) {
                    }
                    e3.printStackTrace();
                } catch (IOException e5) {
                    try {
                        jsonObject.put("code", "10001");
                        DownloadFile.this.cb.excute(jsonObject.toString());
                    } catch (Exception e6) {
                    }
                    e5.printStackTrace();
                } catch (MalformedURLException e7) {
                    try {
                        jsonObject.put("code", "10001");
                        DownloadFile.this.cb.excute(jsonObject.toString());
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                    e7.printStackTrace();
                }
            } catch (Exception e8) {
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", "10001");
                    DownloadFile.this.cb.excute(jsonObject.toString());
                } catch (Exception e222) {
                    e222.printStackTrace();
                }
                e8.printStackTrace();
            }
        }
    }

    public DownloadFile(String url, String savepath, String savename, HttpCallback cb) {
        this.url = url;
        this.savename = savename;
        this.savepath = savepath;
        this.cb = cb;
    }

    public void startDown() {
        new C01961().start();
    }
}
