package www.robinwatch.squid;

import com.obins.anne.utils.AppConfig;
import java.io.Serializable;
import java.util.HashMap;
import org.json.JSONObject;
import www.robinwatch.squid.network.HttpBean;
import www.robinwatch.squid.network.HttpCallback;
import www.robinwatch.squid.utils.C0213L;

public class Squid implements Serializable {
    private String filepath = null;
    private String password = null;
    private String server_url = null;
    private String username = null;

    public static void setIs_Debug(Boolean result) {
        C0213L.isDebug = result.booleanValue();
    }

    public void setNet(int value) {
        if (value == 0) {
            Config.flag = 0;
            Config.BaseIp = "192.168.9.8";
            Config.SquidPath = "/rainbow/rainbow.php";
            Config.RainbowPath = "/rainbow/rainbow.php";
            Config.downloadFwPath = "/AppServer";
            Config.duangPath = AppConfig.SERVER_IP;
            return;
        }
        Config.flag = 1;
        Config.BaseIp = "api.robinwatch.com";
        Config.SquidPath = "/rainbow";
        Config.RainbowPath = "/rainbow";
        Config.downloadFwPath = AppConfig.SERVER_IP;
        Config.duangPath = AppConfig.SERVER_IP;
    }

    public void fwUpdate(String ipString, String filePath, String dev_type, String version, HttpCallback cb) {
        try {
            TcpClinet tc = new TcpClinet(ipString, Config.FwUpdatePort, filePath, dev_type, version, cb);
            tc.SendStaBin();
            tc.Close();
        } catch (Exception e) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", "10002");
                cb.excute(jsonObject.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void checkFwVersion(String dev_type, HttpCallback cb) {
        HashMap<String, String> checkFwVersionparams = new HashMap();
        checkFwVersionparams.put("dev_type", dev_type);
        try {
            new HttpBean(Config.Http + Config.BaseIp + Config.RainbowPath + Config.OTAUpdate).doPost(checkFwVersionparams, cb, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downLoadFWFile(String path, String savepath, String savename, HttpCallback cb) {
        new DownloadFile(Config.Http + Config.BaseIp + Config.downloadFwPath + path, savepath, savename, cb).startDown();
    }

    public void downLoadFWFileWithUrl(String url, String savepath, String savename, HttpCallback cb) {
        new DownloadFile(url, savepath, savename, cb).startDown();
    }

    public void getAppVersion(HttpCallback cb, String appName) {
        try {
            String url;
            if (Config.flag == 0) {
                url = Config.Http + Config.BaseIp + Config.RainbowPath + Config.appUpdate;
            } else {
                url = Config.Http + Config.BaseIp + Config.RainbowPath + Config.appUpdate;
            }
            HashMap<String, String> checkAppVersionparams = new HashMap();
            checkAppVersionparams.put("app_name", appName);
            new HttpBean(url).doPost(checkAppVersionparams, cb, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setserveip(String ip) {
        Config.BaseIp = ip;
    }
}
