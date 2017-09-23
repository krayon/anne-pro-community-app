package com.obins.anne;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Audio.Media;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.obins.anne.listmusic.CharacterParser;
import com.obins.anne.listmusic.PinyinComparator;
import com.obins.anne.listmusic.SortAdapter;
import com.obins.anne.listmusic.TitleToPinyinUtil;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.Music;
import com.obins.anne.utils.MusicLightEffect;
import com.obins.anne.viewpart.ClearEditText;
import com.obins.anne.viewpart.SideBar;
import com.obins.anne.viewpart.SideBar.OnTouchingLetterChangedListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.utils.C0213L;

public class Activity_MusicList extends BaseActivity implements OnClickListener {
    private static final int MUSIC_PAUSE = 0;
    private static final int MUSIC_PLAY = 1;
    private static int duration;
    private static int flag;
    private SortAdapter adapter;
    private RelativeLayout back_RelativeLayout;
    protected ImageButton bottom_next_btn;
    protected ImageButton bottom_play_btn;
    private CharacterParser characterParser;
    private int currentPosition;
    private TextView dialog;
    private boolean is_Play_flag = false;
    private RelativeLayout left_RelativeLayout;
    private ClearEditText mClearEditText;
    private Music music;
    private MusicLightEffect musicLightEffect;
    private String musicName = AppConfig.SERVER_IP;
    private String[] musicNameArray;
    protected TextView music_article_tx;
    protected SeekBar music_durtion_sBar;
    protected TextView music_durtion_time;
    protected ImageButton music_history_btn;
    protected TextView music_preform_time;
    protected TextView music_title_tx;
    private List<Music> musiclist;
    private TextView name_TextView;
    private ImageView pause_ImageView;
    private TextView pause_TextView;
    private PinyinComparator pinyinComparator = new PinyinComparator();
    private ImageView play_ImageView;
    private int selectItem = -1;
    private SideBar sideBar;
    private ListView sortListView;

    class C01331 implements OnClickListener {
        C01331() {
        }

        public void onClick(View arg0) {
            if (Activity_MusicList.this.is_Play_flag) {
                Activity_MusicList.this.is_Play_flag = false;
                if (Activity_MusicList.this.musicLightEffect != null) {
                    Activity_MusicList.this.musicLightEffect.stopPlayMusicLightEffect();
                }
            } else {
                Activity_MusicList.this.is_Play_flag = true;
                if (Activity_MusicList.this.musicLightEffect != null) {
                    Activity_MusicList.this.musicLightEffect.playMusicLightEffect(Activity_MusicList.this);
                }
            }
            Activity_MusicList.this.changeUi();
        }
    }

    class C01342 implements Runnable {
        C01342() {
        }

        public void run() {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BLEHandle.ctlLightEffect(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), 5, 10, 1);
        }
    }

    class C01353 implements Runnable {
        C01353() {
        }

        public void run() {
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BLEHandle.normalLightEffect(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), 0);
        }
    }

    class C01364 implements OnClickListener {
        C01364() {
        }

        public void onClick(View v) {
        }
    }

    class C01375 implements OnSeekBarChangeListener {
        C01375() {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }
    }

    class C01386 implements OnClickListener {
        C01386() {
        }

        public void onClick(View v) {
            switch (Activity_MusicList.flag) {
                case 0:
                    Activity_MusicList.this.pause();
                    Activity_MusicList.this.bottomUpdate();
                    return;
                case 1:
                    Activity_MusicList.this.play();
                    Activity_MusicList.this.bottomUpdate();
                    return;
                default:
                    return;
            }
        }
    }

    class C01397 implements OnClickListener {
        C01397() {
        }

        public void onClick(View v) {
        }
    }

    class C01408 implements OnItemClickListener {
        C01408() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Activity_MusicList.this.selectItem = position;
            BluetoothLeService mBluetoothLeService = BluetoothLeService.getInstance();
            if (mBluetoothLeService.isConnectedOk()) {
                Activity_MusicList.this.music = (Music) Activity_MusicList.this.musiclist.get(position);
                if (Activity_MusicList.this.musicLightEffect == null) {
                    Activity_MusicList.this.musicLightEffect = new MusicLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), Activity_MusicList.this.music.getPath());
                    Activity_MusicList.this.musicLightEffect.playMusicLightEffect(Activity_MusicList.this);
                } else {
                    Activity_MusicList.this.musicLightEffect.stopPlayMusicLightEffect();
                    Activity_MusicList.this.musicLightEffect = new MusicLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), Activity_MusicList.this.music.getPath());
                    Activity_MusicList.this.musicLightEffect.playMusicLightEffect(Activity_MusicList.this);
                }
                Activity_MusicList.flag = 1;
                Activity_MusicList.this.is_Play_flag = true;
                Activity_MusicList.this.musicName = ((Music) Activity_MusicList.this.musiclist.get(position)).getTitle();
                Activity_MusicList.this.changeUi();
                Activity_MusicList.this.bottomUpdate();
                Activity_MusicList.this.adapter.setPosition(position);
                Activity_MusicList.this.adapter.notifyDataSetChanged();
            }
        }
    }

    class C02389 implements OnTouchingLetterChangedListener {
        C02389() {
        }

        public void onTouchingLetterChanged(String s) {
            int position = Activity_MusicList.this.adapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                Activity_MusicList.this.sortListView.setSelection(position);
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_musiclist);
        initUi();
        initDate();
        initButtom();
        this.left_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.left_RelativeLayout);
        this.play_ImageView = (ImageView) findViewById(C0182R.id.play_ImageView);
        this.pause_ImageView = (ImageView) findViewById(C0182R.id.pause_ImageView);
        this.name_TextView = (TextView) findViewById(C0182R.id.name_TextView);
        this.left_RelativeLayout.setOnClickListener(new C01331());
        if (BluetoothLeService.getInstance().isConnectedOk()) {
            BLEHandle.normalLightEffect(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), 4);
            new Thread(new C01342()).start();
        }
        changeUi();
    }

    protected void onDestroy() {
        if (this.musicLightEffect != null) {
            this.musicLightEffect.stopPlayMusicLightEffect();
        }
        if (BluetoothLeService.getInstance().isConnectedOk()) {
            new Thread(new C01353()).start();
        }
        super.onDestroy();
    }

    private void changeUi() {
        if (this.is_Play_flag) {
            this.pause_ImageView.setVisibility(0);
            this.play_ImageView.setVisibility(4);
        } else {
            this.pause_ImageView.setVisibility(4);
            this.play_ImageView.setVisibility(0);
        }
        this.name_TextView.setText(this.musicName);
    }

    private void initButtom() {
        this.music_history_btn = (ImageButton) findViewById(C0182R.id.music_history_btn);
        this.bottom_play_btn = (ImageButton) findViewById(C0182R.id.bottom_play_btn);
        this.bottom_next_btn = (ImageButton) findViewById(C0182R.id.bottom_next_btn);
        this.music_durtion_sBar = (SeekBar) findViewById(C0182R.id.music_durtion_sBar);
        this.music_title_tx = (TextView) findViewById(C0182R.id.music_title_tx);
        this.music_article_tx = (TextView) findViewById(C0182R.id.music_article_tx);
        this.music_preform_time = (TextView) findViewById(C0182R.id.music_preform_time);
        this.music_durtion_time = (TextView) findViewById(C0182R.id.music_durtion_time);
        if (this.music_durtion_sBar != null) {
            this.music_durtion_sBar.setMax(duration);
        }
        if (this.music_durtion_sBar != null) {
            this.music_durtion_sBar.setProgress(this.currentPosition);
        }
        this.music_history_btn.setOnClickListener(new C01364());
        this.music_durtion_sBar.setOnSeekBarChangeListener(new C01375());
        this.bottom_play_btn.setOnClickListener(new C01386());
        this.bottom_next_btn.setOnClickListener(new C01397());
    }

    private void pause() {
        if (BluetoothLeService.getInstance().isConnectedOk()) {
            if (this.musicLightEffect != null) {
                this.musicLightEffect.stopPlayMusicLightEffect();
                this.musicLightEffect = null;
            }
            flag = 0;
        }
    }

    private void play() {
        if (this.music != null) {
            BluetoothLeService mBluetoothLeService = BluetoothLeService.getInstance();
            if (mBluetoothLeService.isConnectedOk()) {
                if (this.musicLightEffect == null) {
                    this.musicLightEffect = new MusicLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), this.music.getPath());
                    this.musicLightEffect.playMusicLightEffect(this);
                } else {
                    this.musicLightEffect.stopPlayMusicLightEffect();
                    this.musicLightEffect = new MusicLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), this.music.getPath());
                    this.musicLightEffect.playMusicLightEffect(this);
                }
                flag = 1;
            }
        }
    }

    public void bottomUpdate() {
        if (this.selectItem >= 0) {
            this.music = new Music();
            this.music = (Music) this.musiclist.get(this.selectItem);
            if (!(this.music_title_tx == null || this.music_article_tx == null || this.music_durtion_time == null)) {
                this.music_title_tx.setText(this.music.getTitle());
                this.music_article_tx.setText(this.music.getArtists());
                this.music_durtion_time.setText(this.music.getTimes());
            }
        }
        switch (flag) {
            case 0:
                if (this.bottom_play_btn != null) {
                    this.bottom_play_btn.setBackgroundResource(C0182R.drawable.bottom_pause);
                    return;
                }
                return;
            case 1:
                if (this.bottom_play_btn != null) {
                    this.bottom_play_btn.setBackgroundResource(C0182R.drawable.bottom_play);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.back_RelativeLayout:
                finish();
                return;
            case C0182R.id.pause_TextView:
                if (this.musicLightEffect != null) {
                    this.musicLightEffect.stopPlayMusicLightEffect();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void initUi() {
        this.pause_TextView = (TextView) findViewById(C0182R.id.pause_TextView);
        this.pause_TextView.setOnClickListener(this);
        this.back_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.back_RelativeLayout);
        this.back_RelativeLayout.setOnClickListener(this);
        this.sideBar = (SideBar) findViewById(C0182R.id.sidrbar);
        this.dialog = (TextView) findViewById(C0182R.id.dialog);
        this.sideBar.setTextView(this.dialog);
        this.mClearEditText = (ClearEditText) findViewById(C0182R.id.filter_edit);
        this.sortListView = (ListView) findViewById(C0182R.id.country_lvcountry);
    }

    private void initDate() {
        sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + Environment.getExternalStorageDirectory().getAbsolutePath())));
        ContentResolver cr = getApplication().getContentResolver();
        if (cr != null) {
            Cursor cursor = cr.query(Media.EXTERNAL_CONTENT_URI, null, null, null, "title_key");
            if (cursor != null) {
                this.musiclist = new ArrayList();
                this.musiclist.clear();
                if (cursor.moveToFirst()) {
                    do {
                        String oldpath = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        Music music = new Music();
                        music.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                        music.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                        music.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow("album")));
                        music.setArtists(cursor.getString(cursor.getColumnIndexOrThrow("artist")));
                        music.setPath(cursor.getString(cursor.getColumnIndexOrThrow("_data")));
                        music.setSize(cursor.getLong(cursor.getColumnIndexOrThrow("_size")));
                        this.musiclist.add(music);
                    } while (cursor.moveToNext());
                }
                this.musicNameArray = new String[this.musiclist.size()];
                List<String> music_path = new ArrayList();
                for (int j = 0; j < this.musiclist.size(); j++) {
                    this.musicNameArray[j] = ((Music) this.musiclist.get(j)).getTitle();
                }
                this.musiclist = TitleToPinyinUtil.filledData(this.musicNameArray, this.musiclist);
                Collections.sort(this.musiclist, this.pinyinComparator);
                this.characterParser = CharacterParser.getInstance();
                String[] musicNameArray = new String[this.musiclist.size()];
                int listLenth = this.musiclist.size();
                C0213L.m20i(AppConfig.SERVER_IP, "listLenth = " + listLenth);
                for (int i = 0; i < listLenth; i++) {
                    musicNameArray[i] = ((Music) this.musiclist.get(i)).getTitle();
                }
                this.adapter = new SortAdapter(this, this.musiclist);
                this.sortListView.setAdapter(this.adapter);
                this.adapter.notifyDataSetChanged();
                this.sortListView.setOnItemClickListener(new C01408());
                this.sideBar.setOnTouchingLetterChangedListener(new C02389());
                this.mClearEditText.addTextChangedListener(new TextWatcher() {
                    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                        Activity_MusicList.this.filterData(arg0.toString());
                    }

                    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    }

                    public void afterTextChanged(Editable arg0) {
                    }
                });
            }
        }
    }

    private void filterData(String filterStr) {
        List<Music> filterDateList = new ArrayList();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = this.musiclist;
        } else {
            filterDateList.clear();
            for (Music music : this.musiclist) {
                String name = music.getTitle();
                if (name.indexOf(filterStr.toString()) != -1 || this.characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(music);
                }
            }
        }
        this.adapter.updateListView(filterDateList);
    }
}
