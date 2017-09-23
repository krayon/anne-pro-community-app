package www.robinwatch.squid.network;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import www.robinwatch.squid.utils.C0213L;

public class HttpBeanUploadUtil {
    private static final String CHARSET = "utf-8";
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10000;

    public static String uploadFile(File file, String RequestURL) {
        String BOUNDARY = UUID.randomUUID().toString();
        String PREFIX = "--";
        String LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data";
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(RequestURL).openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", new StringBuilder(String.valueOf(CONTENT_TYPE)).append(";boundary=").append(BOUNDARY).toString());
            if (file == null) {
                return null;
            }
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            StringBuffer sb = new StringBuffer();
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINE_END);
            sb.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\"" + file.getName() + "\"" + LINE_END);
            sb.append("Content-Type: application/octet-stream; charset=utf-8" + LINE_END);
            sb.append(LINE_END);
            dos.write(sb.toString().getBytes());
            InputStream is = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            while (true) {
                int len = is.read(bytes);
                if (len == -1) {
                    break;
                }
                dos.write(bytes, 0, len);
            }
            is.close();
            dos.write(LINE_END.getBytes());
            dos.write(new StringBuilder(String.valueOf(PREFIX)).append(BOUNDARY).append(PREFIX).append(LINE_END).toString().getBytes());
            dos.flush();
            C0213L.m17e(TAG, "response code:" + conn.getResponseCode());
            C0213L.m17e(TAG, "request success");
            InputStream input = conn.getInputStream();
            StringBuffer sb1 = new StringBuffer();
            while (true) {
                int ss = input.read();
                if (ss == -1) {
                    String result = sb1.toString();
                    C0213L.m17e(TAG, "result : " + result);
                    return result;
                }
                sb1.append((char) ss);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String post(String url, Map<String, String> params, Map<String, File> files) throws IOException {
        String BOUNDARY = UUID.randomUUID().toString();
        String PREFIX = "--";
        String LINEND = "\r\n";
        String CHARSET = "UTF-8";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setReadTimeout(TIME_OUT);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "multipart/form-data" + ";boundary=" + BOUNDARY);
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : params.entrySet()) {
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINEND);
            sb.append("Content-Disposition: form-data; name=\"" + ((String) entry.getKey()) + "\"" + LINEND);
            sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
            sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
            sb.append(LINEND);
            sb.append((String) entry.getValue());
            sb.append(LINEND);
        }
        DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
        dataOutputStream.write(sb.toString().getBytes());
        if (files != null) {
            for (Entry<String, File> file : files.entrySet()) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\"" + ((File) file.getValue()).getName() + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                dataOutputStream.write(sb1.toString().getBytes());
                InputStream is = new FileInputStream((File) file.getValue());
                byte[] buffer = new byte[1024];
                while (true) {
                    int len = is.read(buffer);
                    if (len == -1) {
                        break;
                    }
                    dataOutputStream.write(buffer, 0, len);
                }
                is.close();
                dataOutputStream.write(LINEND.getBytes());
            }
        }
        dataOutputStream.write(new StringBuilder(String.valueOf(PREFIX)).append(BOUNDARY).append(PREFIX).append(LINEND).toString().getBytes());
        dataOutputStream.flush();
        int res = conn.getResponseCode();
        InputStream in = conn.getInputStream();
        StringBuilder sb2 = new StringBuilder();
        if (res == 200) {
            while (true) {
                int ch = in.read();
                if (ch == -1) {
                    break;
                }
                sb2.append((char) ch);
            }
        }
        dataOutputStream.close();
        conn.disconnect();
        return sb2.toString();
    }
}
