package com.obins.anne.update;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.obins.anne.C0182R;
import com.obins.anne.MainActivity;
import com.obins.anne.utils.AppConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class AppUpdate {
    private static final int MSG_ERROR = 2;
    private static final int MSG_FINISH = 1;
    private static final int MSG_UPDATE = 0;
    private static final int NOTIF_ID = 1818188;
    private static final String TAG = "AppUpdate";
    private static final int down_step_custom = 1;
    OnClickListener PositiveButton = new C01842();
    private Dialog checkVersiondialog;
    private RemoteViews contentView;
    private Context context;
    private String download_erroString = "下载失败";
    File file;
    private String finish_downloadString = "下载完成";
    private Handler handlernotify = new C01831();
    private NotificationManager manager;
    OnClickListener f1n = new C01853();
    private String net_erroString = "网络错误";
    private Notification notif;
    private String update_apkname = "ws001.apk";
    private String update_savename = "ws001_update.apk";
    private String update_savepath = AppConfig.SERVER_IP;
    private String update_server = AppConfig.SERVER_IP;
    private String update_verjson = "ver.json";
    private String url = AppConfig.SERVER_IP;

    class C01831 extends Handler {
        C01831() {
        }

        public void handleMessage(Message msg) {
            Notification access$0;
            switch (msg.what) {
                case 0:
                    int len = ((Integer) msg.obj).intValue();
                    AppUpdate.this.notif.contentView.setTextViewText(C0182R.id.tvProgress, new StringBuilder(String.valueOf(len)).append("%").toString());
                    AppUpdate.this.notif.contentView.setProgressBar(C0182R.id.pbProgress, 100, len, false);
                    AppUpdate.this.manager.notify(AppUpdate.NOTIF_ID, AppUpdate.this.notif);
                    break;
                case 1:
                    AppUpdate.this.notif.contentView.setTextViewText(C0182R.id.tvProgress, AppUpdate.this.finish_downloadString);
                    AppUpdate.this.notif.contentView.setProgressBar(C0182R.id.pbProgress, 100, 100, false);
                    Intent installIntent = new Intent("android.intent.action.VIEW");
                    installIntent.setDataAndType(Uri.fromFile(AppUpdate.this.file), "application/vnd.android.package-archive");
                    AppUpdate.this.notif.contentIntent = PendingIntent.getActivity(AppUpdate.this.context, 8880, installIntent, 1073741824);
                    access$0 = AppUpdate.this.notif;
                    access$0.flags |= 16;
                    AppUpdate.this.manager.notify(AppUpdate.NOTIF_ID, AppUpdate.this.notif);
                    AppUpdate.this.manager.cancel(AppUpdate.NOTIF_ID);
                    Toast.makeText(AppUpdate.this.context, AppUpdate.this.finish_downloadString, 0).show();
                    AppUpdate.this.update();
                    break;
                case 2:
                    String errorMsg = msg.obj;
                    if (TextUtils.isEmpty(errorMsg)) {
                        errorMsg = AppUpdate.this.net_erroString;
                    }
                    errorMsg = new StringBuilder(String.valueOf(errorMsg)).append(",").append(AppUpdate.this.download_erroString).toString();
                    AppUpdate.this.notif.contentView.setTextViewText(C0182R.id.tvProgress, AppUpdate.this.download_erroString);
                    access$0 = AppUpdate.this.notif;
                    access$0.flags |= 16;
                    AppUpdate.this.manager.notify(AppUpdate.NOTIF_ID, AppUpdate.this.notif);
                    Toast.makeText(AppUpdate.this.context, errorMsg, 0).show();
                    break;
            }
            super.handleMessage(msg);
        }
    }

    class C01842 implements OnClickListener {
        C01842() {
        }

        public void onClick(DialogInterface dialog, int which) {
            AppUpdate.this.createNotification();
            AppUpdate.this.downFile(new StringBuilder(String.valueOf(AppUpdate.this.update_server)).append(AppUpdate.this.update_apkname).toString());
        }
    }

    class C01853 implements OnClickListener {
        C01853() {
        }

        public void onClick(DialogInterface dialog, int which) {
            AppUpdate.this.checkVersiondialog.dismiss();
        }
    }

    class C01864 extends Thread {
        C01864() {
        }

        public void run() {
            AppUpdate.this.createNotification();
            AppUpdate.this.downFile(AppUpdate.this.url);
        }
    }

    public AppUpdate(Context c) {
        this.context = c;
    }

    public void setParam(String update_server, String update_verjson, String update_apkname, String update_savepath, String update_savename) {
        this.update_server = update_server;
        this.update_verjson = update_verjson;
        this.update_savename = update_savename;
        this.update_apkname = update_apkname;
        this.update_savepath = update_savepath;
    }

    public void setNEWParam(String url, String update_savepath, String update_savename) {
        this.url = url;
        this.update_savepath = update_savepath;
        this.update_savename = update_savename;
    }

    public void startDownload() {
        new C01864().start();
    }

    private void downFile(final String url) {
        new Thread() {
            public void run() {
                int downloadCount = 0;
                int updateCount = 0;
                try {
                    HttpEntity entity = new DefaultHttpClient().execute(new HttpGet(url)).getEntity();
                    long length = entity.getContentLength();
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File filepath = new File(AppUpdate.this.update_savepath);
                        AppUpdate.this.file = new File(filepath, AppUpdate.this.update_savename);
                        fileOutputStream = new FileOutputStream(AppUpdate.this.file);
                        byte[] buf = new byte[1024];
                        int count = 0;
                        int totalSize = (int) length;
                        while (true) {
                            int ch = is.read(buf);
                            if (ch == -1) {
                                break;
                            }
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            downloadCount += ch;
                            if (updateCount == 0 || ((downloadCount * 100) / totalSize) - 1 >= updateCount) {
                                updateCount++;
                                AppUpdate.this.handlernotify.sendMessage(AppUpdate.this.handlernotify.obtainMessage(0, Integer.valueOf(updateCount)));
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    AppUpdate.this.handlernotify.sendEmptyMessage(1);
                } catch (MalformedURLException e) {
                    AppUpdate.this.handlernotify.sendEmptyMessage(2);
                    e.printStackTrace();
                } catch (ClientProtocolException e2) {
                    AppUpdate.this.handlernotify.sendEmptyMessage(2);
                    e2.printStackTrace();
                } catch (IOException e3) {
                    AppUpdate.this.handlernotify.sendEmptyMessage(2);
                    e3.printStackTrace();
                } catch (Exception e4) {
                    AppUpdate.this.handlernotify.sendEmptyMessage(2);
                    e4.printStackTrace();
                }
            }
        }.start();
    }

    public String getContent(String url) throws Exception {
        StringBuilder sb = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpParams httpParams = client.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
        HttpConnectionParams.setSoTimeout(httpParams, 5000);
        HttpEntity entity = client.execute(new HttpGet(url)).getEntity();
        if (entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"), 8192);
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(new StringBuilder(String.valueOf(line)).append("\n").toString());
            }
            reader.close();
        }
        return sb.toString();
    }

    void update() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(new File(this.update_savepath), this.update_savename)), "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        this.context.startActivity(intent);
    }

    public void createNotification() {
        this.manager = (NotificationManager) this.context.getSystemService("notification");
        PendingIntent pIntent = PendingIntent.getActivity(this.context, 0, new Intent(this.context, MainActivity.class), 134217728);
        this.notif = new Notification();
        this.notif.icon = C0182R.drawable.ic_launcher;
        this.notif.tickerText = this.context.getResources().getString(C0182R.string.app_name);
        this.notif.contentView = new RemoteViews(this.context.getPackageName(), C0182R.layout.notifi_layout);
        this.notif.flags = 2;
        this.notif.contentIntent = pIntent;
        this.manager.notify(NOTIF_ID, this.notif);
    }
}
