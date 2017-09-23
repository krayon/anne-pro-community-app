package www.robinwatch.squid.network;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import www.robinwatch.squid.utils.C0213L;

public class HttpBean {
    private static final int CS_GZIP = 1;
    private static final int CS_NONE = 0;
    private String apiUrl;
    HttpCallback callback;
    private String charset = "UTF-8";
    private int compress = 0;
    String filename;
    HashMap<String, File> files;
    private HttpClient httpClinet;
    private HttpParams httpParams;
    OnNetReturnListener onNetReturnListen;
    private int photoheith = 800;
    private int photowidth = 600;
    private int signphotoheith = 200;
    private int signphotoweith = 150;
    private int timeoutConnection = 12000;
    private int timeoutSocket = 12000;
    HashMap<String, String> urlParams;

    class C02121 implements Runnable {
        C02121() {
        }

        public void run() {
            try {
                String result = HttpBean.this.post(HttpBean.this.urlParams);
                if (result == null || HttpBean.this.callback == null) {
                    if (result == null) {
                        result = "﻿{\"code\":\"12580\",\"messge\":\"TimeoutException\",\"result\":\"null\"}";
                        if (HttpBean.this.onNetReturnListen == null) {
                            HttpBean.this.callback.excute(result);
                        } else {
                            HttpBean.this.onNetReturnListen.excute(HttpBean.this.callback, result);
                        }
                    }
                } else if (HttpBean.this.onNetReturnListen == null) {
                    HttpBean.this.callback.excute(result);
                } else {
                    HttpBean.this.onNetReturnListen.excute(HttpBean.this.callback, result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class fileDownloadThread extends Thread {
        private HttpCallback callback;
        private String filename;
        private String filepath;

        public fileDownloadThread(String fileName, String filePath, HttpCallback cb) {
            this.filename = fileName;
            this.filepath = filePath;
            this.callback = cb;
        }

        public void run() {
            try {
                HttpBean.this.downloadFile(this.filename, this.filepath, this.callback);
            } catch (Exception e) {
                this.callback.excute("﻿{\"code\":\"12580\",\"messge\":\"TimeoutException\",\"result\":\"null\"}");
                e.printStackTrace();
            }
        }
    }

    public HttpBean(String url) {
        this.apiUrl = url;
        initClinet(url);
    }

    public HttpBean(String url, String charset, int compress) {
        this.apiUrl = url;
        this.charset = charset;
        this.compress = compress;
        initClinet(url);
    }

    private void initClinet(String url) {
        this.httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(this.httpParams, this.timeoutConnection);
        HttpConnectionParams.setSoTimeout(this.httpParams, this.timeoutSocket);
        this.httpClinet = new DefaultHttpClient(this.httpParams);
    }

    private String post(HashMap<String, String> urlParams) throws Exception {
        try {
            HttpPost httpPost = headerFilter(new HttpPost(this.apiUrl));
            List<NameValuePair> postParams = new ArrayList();
            for (Entry entry : urlParams.entrySet()) {
                postParams.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
            }
            if (this.charset != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(postParams, this.charset));
            } else {
                httpPost.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
            }
            C0213L.m25w("HttpBean.post.url", this.apiUrl);
            C0213L.m25w("HttpBean.post.data", postParams.toString());
            HttpResponse httpResponse = this.httpClinet.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                C0213L.m19i(" HttpStatus.SC_OK ");
                String httpResult = resultFilter(httpResponse.getEntity());
                C0213L.m25w("HttpBean.post.result", httpResult);
                return httpResult;
            }
            C0213L.m19i("! HttpStatus.SC_OK ");
            return null;
        } catch (ConnectionPoolTimeoutException e) {
            e.printStackTrace();
            return null;
        } catch (ConnectTimeoutException e2) {
            e2.printStackTrace();
            return null;
        } catch (SocketTimeoutException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public String get() throws Exception {
        try {
            headerFilter(new HttpGet(this.apiUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpGet headerFilter(HttpGet httpGet) {
        switch (this.compress) {
            case 1:
                httpGet.addHeader("Accept-Encoding", "gzip");
                break;
        }
        return httpGet;
    }

    private HttpPost headerFilter(HttpPost httpPost) {
        switch (this.compress) {
            case 1:
                httpPost.addHeader("Accept-Encoding", "gzip");
                break;
        }
        return httpPost;
    }

    private String resultFilter(HttpEntity entiry) {
        String result = null;
        try {
            int i = this.compress;
            result = EntityUtils.toString(entiry, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void doPost(HashMap<String, String> up, HttpCallback cb, OnNetReturnListener netreturn) {
        this.urlParams = up;
        this.callback = cb;
        this.onNetReturnListen = netreturn;
        new Thread(new C02121()).start();
    }

    private String sendHttpClientPostRequest(HashMap<String, File> files) throws Exception {
        HttpPost httpPost = headerFilter(new HttpPost(this.apiUrl));
        List<NameValuePair> postParams = new ArrayList();
        for (Entry entry : files.entrySet()) {
            postParams.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
        }
        if (this.charset != null) {
            httpPost.setEntity(new UrlEncodedFormEntity(postParams, this.charset));
        } else {
            httpPost.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
        }
        C0213L.m25w("HttpBean.post.url", this.apiUrl);
        C0213L.m25w("HttpBean.post.data", postParams.toString());
        HttpResponse httpResponse = this.httpClinet.execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() != 200) {
            return null;
        }
        String httpResult = resultFilter(httpResponse.getEntity());
        C0213L.m25w("HttpBean.post.result", httpResult);
        return httpResult;
    }

    public void doDownloadFile(String filename, String filepath, HttpCallback cb) {
        new fileDownloadThread(filename, filepath, cb).start();
    }

    private void downloadFile(String fn, String filepath, HttpCallback cb) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(this.apiUrl).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(6000);
        this.callback = cb;
        this.filename = fn;
        int fileLength = conn.getContentLength();
        C0213L.m19i("result fileLength = " + fileLength);
        File file = new File(new StringBuilder(String.valueOf(filepath)).append("/").append(this.filename).toString());
        if (file.exists()) {
            C0213L.m25w("HttpBean.downloadFile", " file exists");
            file.delete();
        }
        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            byte[] data = readInStream(inputStream, fileLength);
            FileOutputStream outputStream = new FileOutputStream(file);
            inputStream.close();
            outputStream.write(data);
            outputStream.close();
            this.callback.excute("﻿{\"code\":\"10000\",\"messge\":\"DownloadFileSUccess\",\"result\":{\"filename\":\"" + this.filename + "\"}}");
        } else {
            this.callback.excute("﻿{\"code\":\"12580\",\"messge\":\"TimeoutException\",\"result\":\"null\"}");
        }
        conn.disconnect();
    }

    private byte[] readInStream(InputStream inputStream, int fileLength) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[fileLength];
        while (true) {
            int length = inputStream.read(buffer);
            if (length == -1) {
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(buffer, 0, length);
        }
    }
}
