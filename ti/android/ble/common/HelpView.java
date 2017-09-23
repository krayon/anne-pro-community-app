package ti.android.ble.common;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class HelpView extends Fragment {
    private String mFile = "about.html";
    private int mIdFragment;
    private int mIdWebPage;

    public HelpView(String file, int idFragment, int idWebPage) {
        if (file != null) {
            this.mFile = file;
        }
        this.mIdFragment = idFragment;
        this.mIdWebPage = idWebPage;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(this.mIdFragment, container, false);
        ((WebView) rootView.findViewById(this.mIdWebPage)).loadUrl("file:///android_asset/" + this.mFile);
        return rootView;
    }
}
