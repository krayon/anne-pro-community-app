package com.obins.anne;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.obins.anne.db.CustomLightEffect;
import com.obins.anne.db.GameLightEffect;
import com.obins.anne.db.NormalLightEffect;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.MusicLightEffect;
import com.obins.anne.viewpart.CircleView;
import java.util.ArrayList;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;

public class Fragment_LightEffect extends Fragment implements OnClickListener {
    private static String TAG = "Fragment_LightEffect";
    private static List<Integer> listItemColorId = null;
    private static List<Integer> listItemTransparentColorId = null;
    private Button custom_Button;
    private ListView custom_ListView;
    private View custom_View;
    private View custom_selece_buttomhalf_View;
    private CustomAdapter customadapter = null;
    private int customlLightEffectSelected = -1;
    private int gameLightEffectSelected = -1;
    private Button game_Button;
    private ListView game_ListView;
    private View game_View;
    private View game_selece_buttomhalf_View;
    private GameAdapter gameadapter = null;
    private TextView lighteffectname_TextView;
    private List<CustomLightEffect> listCustomLightEffect = null;
    private List<GameLightEffect> listGameLightEffect = null;
    private List<NormalLightEffect> listNormalLightEffect = null;
    private MusicLightEffect musicLightEffect;
    private int normalLightEffectSelected = -1;
    private Button normalLighteffect_Button;
    private ListView normalLighteffect_ListView;
    private View normalLighteffect_View;
    private NormalLighteffectAdapter normalLighteffectadapter = null;
    private View normaleffectlight_selece_buttomhalf_View;
    private TextView settingmusic_TextView;
    private int whoisshow = 0;

    public class CustomAdapter extends BaseAdapter {
        private List<CustomLightEffect> data;
        private LayoutInflater mInflater;

        class C01661 implements OnClickListener {
            C01661() {
            }

            public void onClick(View arg0) {
            }
        }

        public CustomAdapter(Context context, List<CustomLightEffect> data) {
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getCount() {
            return this.data.size();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(C0182R.layout.fragment_lighteffect_item, null);
                holder.order_TextView = (TextView) convertView.findViewById(C0182R.id.order_TextView);
                holder.left_CircleView = (CircleView) convertView.findViewById(C0182R.id.left_CircleView);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.order_TextView.setText(new StringBuilder(String.valueOf(position + 1)).toString());
            int colorId = ((Integer) Fragment_LightEffect.listItemColorId.get(position % Fragment_LightEffect.listItemColorId.size())).intValue();
            int transparentcolorId = ((Integer) Fragment_LightEffect.listItemTransparentColorId.get(position % Fragment_LightEffect.listItemTransparentColorId.size())).intValue();
            holder.left_CircleView.setColor(colorId);
            holder.name_TextView.setText(((CustomLightEffect) Fragment_LightEffect.this.listCustomLightEffect.get(position)).name);
            convertView.setOnClickListener(new C01661());
            return convertView;
        }
    }

    public class GameAdapter extends BaseAdapter {
        private List<GameLightEffect> data;
        private LayoutInflater mInflater;

        public GameAdapter(Context context, List<GameLightEffect> data) {
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getCount() {
            return this.data.size();
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(C0182R.layout.fragment_lighteffect_item, null);
                holder.order_TextView = (TextView) convertView.findViewById(C0182R.id.order_TextView);
                holder.left_CircleView = (CircleView) convertView.findViewById(C0182R.id.left_CircleView);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                holder.right_CircleView = (CircleView) convertView.findViewById(C0182R.id.right_CircleView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.order_TextView.setText(new StringBuilder(String.valueOf(position + 1)).toString());
            int colorId = ((Integer) Fragment_LightEffect.listItemColorId.get(position % Fragment_LightEffect.listItemColorId.size())).intValue();
            int transparentcolorId = ((Integer) Fragment_LightEffect.listItemTransparentColorId.get(position % Fragment_LightEffect.listItemTransparentColorId.size())).intValue();
            holder.left_CircleView.setColor(colorId);
            holder.name_TextView.setText(((GameLightEffect) Fragment_LightEffect.this.listGameLightEffect.get(position)).name);
            if (Fragment_LightEffect.this.gameLightEffectSelected == position) {
                holder.right_CircleView.setVisibility(0);
            } else {
                holder.right_CircleView.setVisibility(8);
            }
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    boolean turnOn = false;
                    if (Fragment_LightEffect.this.gameLightEffectSelected == -1) {
                        Fragment_LightEffect.this.gameLightEffectSelected = position;
                        turnOn = true;
                    } else if (Fragment_LightEffect.this.gameLightEffectSelected == position) {
                        Fragment_LightEffect.this.gameLightEffectSelected = -1;
                    } else if (Fragment_LightEffect.this.gameLightEffectSelected != position) {
                        Fragment_LightEffect.this.gameLightEffectSelected = position;
                        turnOn = true;
                    }
                    BluetoothLeService mBluetoothLeService = BluetoothLeService.getInstance();
                    if (mBluetoothLeService.isConnectedOk()) {
                        if (turnOn) {
                            BLEHandle.gameLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), position);
                        } else {
                            BLEHandle.normalLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), 0);
                        }
                    }
                    Fragment_LightEffect.this.normalLightEffectSelected = -1;
                    Fragment_LightEffect.this.customlLightEffectSelected = -1;
                    Fragment_LightEffect.this.normalLighteffectadapter.notifyDataSetChanged();
                    Fragment_LightEffect.this.gameadapter.notifyDataSetChanged();
                    Fragment_LightEffect.this.customadapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    public class NormalLighteffectAdapter extends BaseAdapter {
        private List<NormalLightEffect> data;
        private LayoutInflater mInflater;

        public NormalLighteffectAdapter(Context context, List<NormalLightEffect> data) {
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getCount() {
            return this.data.size();
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(C0182R.layout.fragment_lighteffect_item, null);
                holder.order_TextView = (TextView) convertView.findViewById(C0182R.id.order_TextView);
                holder.left_CircleView = (CircleView) convertView.findViewById(C0182R.id.left_CircleView);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                holder.right_CircleView = (CircleView) convertView.findViewById(C0182R.id.right_CircleView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.order_TextView.setText(new StringBuilder(String.valueOf(position + 1)).toString());
            int colorId = ((Integer) Fragment_LightEffect.listItemColorId.get(position % Fragment_LightEffect.listItemColorId.size())).intValue();
            int transparentcolorId = ((Integer) Fragment_LightEffect.listItemTransparentColorId.get(position % Fragment_LightEffect.listItemTransparentColorId.size())).intValue();
            holder.left_CircleView.setColor(colorId);
            holder.name_TextView.setText(((NormalLightEffect) Fragment_LightEffect.this.listNormalLightEffect.get(position)).name);
            if (Fragment_LightEffect.this.normalLightEffectSelected == position) {
                holder.right_CircleView.setVisibility(0);
            } else {
                holder.right_CircleView.setVisibility(8);
            }
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    boolean turnOn = false;
                    if (Fragment_LightEffect.this.normalLightEffectSelected == -1) {
                        Fragment_LightEffect.this.normalLightEffectSelected = position;
                        turnOn = true;
                    } else if (Fragment_LightEffect.this.normalLightEffectSelected == position) {
                        Fragment_LightEffect.this.normalLightEffectSelected = -1;
                        turnOn = false;
                    } else if (Fragment_LightEffect.this.normalLightEffectSelected != position) {
                        Fragment_LightEffect.this.normalLightEffectSelected = position;
                        turnOn = true;
                    }
                    BluetoothLeService mBluetoothLeService = BluetoothLeService.getInstance();
                    if (mBluetoothLeService.isConnectedOk()) {
                        if (turnOn) {
                            if (position != 15) {
                                BLEHandle.normalLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), position);
                            } else if (Fragment_LightEffect.this.musicLightEffect == null) {
                                Fragment_LightEffect.this.musicLightEffect = new MusicLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), AppUtils.musicfilepath);
                                Fragment_LightEffect.this.musicLightEffect.playMusicLightEffect(Fragment_LightEffect.this.getActivity());
                            }
                        } else if (position != 15) {
                            BLEHandle.normalLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), 0);
                        } else if (Fragment_LightEffect.this.musicLightEffect != null) {
                            Fragment_LightEffect.this.musicLightEffect.stopPlayMusicLightEffect();
                            Fragment_LightEffect.this.musicLightEffect = null;
                        }
                    }
                    Fragment_LightEffect.this.gameLightEffectSelected = -1;
                    Fragment_LightEffect.this.customlLightEffectSelected = -1;
                    Fragment_LightEffect.this.normalLighteffectadapter.notifyDataSetChanged();
                    Fragment_LightEffect.this.gameadapter.notifyDataSetChanged();
                    Fragment_LightEffect.this.customadapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public CircleView left_CircleView;
        public TextView name_TextView;
        public TextView order_TextView;
        private CircleView right_CircleView;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0182R.layout.fragment_lighteffect, new RelativeLayout(getActivity()), true);
        listItemColorId = new ArrayList();
        listItemTransparentColorId = new ArrayList();
        listItemColorId.clear();
        listItemTransparentColorId.clear();
        listItemColorId.add(Integer.valueOf(getActivity().getResources().getColor(C0182R.color.green)));
        listItemTransparentColorId.add(Integer.valueOf(getActivity().getResources().getColor(C0182R.color.green_transparent)));
        listItemColorId.add(Integer.valueOf(getActivity().getResources().getColor(C0182R.color.purple)));
        listItemTransparentColorId.add(Integer.valueOf(getActivity().getResources().getColor(C0182R.color.purple_transparent)));
        listItemColorId.add(Integer.valueOf(getActivity().getResources().getColor(C0182R.color.blue)));
        listItemTransparentColorId.add(Integer.valueOf(getActivity().getResources().getColor(C0182R.color.blue_transparent)));
        initdata();
        initui(v);
        return v;
    }

    private void initdata() {
        this.listNormalLightEffect = new ArrayList();
        this.listGameLightEffect = new ArrayList();
        this.listCustomLightEffect = new ArrayList();
        NormalLightEffect normalLightEffect1 = new NormalLightEffect();
        normalLightEffect1.name = "LED 关闭";
        NormalLightEffect normalLightEffect2 = new NormalLightEffect();
        normalLightEffect2.name = "静态纯色 红色";
        NormalLightEffect normalLightEffect3 = new NormalLightEffect();
        normalLightEffect3.name = "静态纯色 黄色";
        NormalLightEffect normalLightEffect4 = new NormalLightEffect();
        normalLightEffect4.name = "静态纯色 绿色";
        NormalLightEffect normalLightEffect5 = new NormalLightEffect();
        normalLightEffect5.name = "静态纯色 青色";
        NormalLightEffect normalLightEffect6 = new NormalLightEffect();
        normalLightEffect6.name = "静态纯色 蓝色";
        NormalLightEffect normalLightEffect7 = new NormalLightEffect();
        normalLightEffect7.name = "静态纯色 紫色";
        NormalLightEffect normalLightEffect8 = new NormalLightEffect();
        normalLightEffect8.name = "静态纯色 粉红";
        NormalLightEffect normalLightEffect9 = new NormalLightEffect();
        normalLightEffect9.name = "静态纯色 橙色";
        NormalLightEffect normalLightEffect10 = new NormalLightEffect();
        normalLightEffect10.name = "纯色呼吸灯";
        NormalLightEffect normalLightEffect11 = new NormalLightEffect();
        normalLightEffect11.name = "七彩呼吸灯 方向A";
        NormalLightEffect normalLightEffect12 = new NormalLightEffect();
        normalLightEffect12.name = "七彩呼吸灯 方向B";
        NormalLightEffect normalLightEffect13 = new NormalLightEffect();
        normalLightEffect13.name = "七彩呼吸灯 方向C";
        NormalLightEffect normalLightEffect14 = new NormalLightEffect();
        normalLightEffect14.name = "七彩呼吸灯 方向D";
        NormalLightEffect normalLightEffect15 = new NormalLightEffect();
        normalLightEffect15.name = "单色触发灯 默认红色";
        new NormalLightEffect().name = "音乐频谱";
        this.listNormalLightEffect.add(normalLightEffect1);
        this.listNormalLightEffect.add(normalLightEffect2);
        this.listNormalLightEffect.add(normalLightEffect3);
        this.listNormalLightEffect.add(normalLightEffect4);
        this.listNormalLightEffect.add(normalLightEffect5);
        this.listNormalLightEffect.add(normalLightEffect6);
        this.listNormalLightEffect.add(normalLightEffect7);
        this.listNormalLightEffect.add(normalLightEffect8);
        this.listNormalLightEffect.add(normalLightEffect9);
        this.listNormalLightEffect.add(normalLightEffect10);
        this.listNormalLightEffect.add(normalLightEffect11);
        this.listNormalLightEffect.add(normalLightEffect12);
        this.listNormalLightEffect.add(normalLightEffect13);
        this.listNormalLightEffect.add(normalLightEffect14);
        this.listNormalLightEffect.add(normalLightEffect15);
        GameLightEffect gameLightEffect1 = new GameLightEffect();
        gameLightEffect1.name = "LOL";
        GameLightEffect gameLightEffect2 = new GameLightEffect();
        gameLightEffect2.name = "刀塔2";
        GameLightEffect gameLightEffect3 = new GameLightEffect();
        gameLightEffect3.name = "英雄联盟";
        GameLightEffect gameLightEffect4 = new GameLightEffect();
        gameLightEffect4.name = "穿越火线";
        GameLightEffect gameLightEffect5 = new GameLightEffect();
        gameLightEffect5.name = "魔兽世界";
        GameLightEffect gameLightEffect6 = new GameLightEffect();
        gameLightEffect6.name = "地下城与勇士";
        GameLightEffect gameLightEffect7 = new GameLightEffect();
        gameLightEffect7.name = "QQ飞车";
        GameLightEffect gameLightEffect8 = new GameLightEffect();
        gameLightEffect8.name = "FIFA OL";
        GameLightEffect gameLightEffect9 = new GameLightEffect();
        gameLightEffect9.name = "NBA2K OL";
        GameLightEffect gameLightEffect10 = new GameLightEffect();
        gameLightEffect10.name = "坦克世界";
        this.listGameLightEffect.add(gameLightEffect1);
        this.listGameLightEffect.add(gameLightEffect2);
        this.listGameLightEffect.add(gameLightEffect3);
        this.listGameLightEffect.add(gameLightEffect4);
        this.listGameLightEffect.add(gameLightEffect5);
        this.listGameLightEffect.add(gameLightEffect6);
        this.listGameLightEffect.add(gameLightEffect7);
        this.listGameLightEffect.add(gameLightEffect8);
        this.listGameLightEffect.add(gameLightEffect9);
        this.listGameLightEffect.add(gameLightEffect10);
    }

    private void initui(View v) {
        this.settingmusic_TextView = (TextView) v.findViewById(C0182R.id.settingmusic_TextView);
        this.settingmusic_TextView.setOnClickListener(this);
        this.normaleffectlight_selece_buttomhalf_View = v.findViewById(C0182R.id.normaleffectlight_selece_buttomhalf_View);
        this.game_selece_buttomhalf_View = v.findViewById(C0182R.id.game_selece_buttomhalf_View);
        this.custom_selece_buttomhalf_View = v.findViewById(C0182R.id.custom_selece_buttomhalf_View);
        this.normalLighteffect_View = v.findViewById(C0182R.id.normalLighteffect_View);
        this.game_View = v.findViewById(C0182R.id.game_View);
        this.custom_View = v.findViewById(C0182R.id.custom_View);
        this.lighteffectname_TextView = (TextView) v.findViewById(C0182R.id.lighteffectname_TextView);
        this.lighteffectname_TextView.setOnClickListener(this);
        this.normalLighteffect_Button = (Button) v.findViewById(C0182R.id.normalLighteffect_Button);
        this.game_Button = (Button) v.findViewById(C0182R.id.game_Button);
        this.custom_Button = (Button) v.findViewById(C0182R.id.custom_Button);
        this.normalLighteffect_Button.setOnClickListener(this);
        this.game_Button.setOnClickListener(this);
        this.custom_Button.setOnClickListener(this);
        this.normalLighteffect_ListView = (ListView) v.findViewById(C0182R.id.normalLighteffect_ListView);
        this.game_ListView = (ListView) v.findViewById(C0182R.id.game_ListView);
        this.custom_ListView = (ListView) v.findViewById(C0182R.id.custom_ListView);
        this.normalLighteffectadapter = new NormalLighteffectAdapter(getActivity(), this.listNormalLightEffect);
        this.normalLighteffect_ListView.setAdapter(this.normalLighteffectadapter);
        this.normalLighteffectadapter.notifyDataSetChanged();
        this.gameadapter = new GameAdapter(getActivity(), this.listGameLightEffect);
        this.game_ListView.setAdapter(this.gameadapter);
        this.gameadapter.notifyDataSetChanged();
        this.customadapter = new CustomAdapter(getActivity(), this.listCustomLightEffect);
        this.custom_ListView.setAdapter(this.customadapter);
        this.customadapter.notifyDataSetChanged();
        this.normalLighteffect_ListView.setVisibility(0);
        this.game_ListView.setVisibility(8);
        this.custom_ListView.setVisibility(8);
        this.normalLighteffect_View.setVisibility(0);
        this.game_View.setVisibility(8);
        this.custom_View.setVisibility(8);
        this.normaleffectlight_selece_buttomhalf_View.setVisibility(0);
        this.game_selece_buttomhalf_View.setVisibility(8);
        this.custom_selece_buttomhalf_View.setVisibility(8);
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.normalLighteffect_Button:
                this.normalLighteffect_ListView.setVisibility(0);
                this.game_ListView.setVisibility(8);
                this.custom_ListView.setVisibility(8);
                this.normalLighteffect_View.setVisibility(0);
                this.game_View.setVisibility(8);
                this.custom_View.setVisibility(8);
                this.normalLighteffect_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_blue_halfcircle_23));
                this.game_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_transparent_halfcircle_red));
                this.custom_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_transparent_halfcircle_red));
                this.normaleffectlight_selece_buttomhalf_View.setVisibility(0);
                this.game_selece_buttomhalf_View.setVisibility(8);
                this.custom_selece_buttomhalf_View.setVisibility(8);
                this.whoisshow = 0;
                return;
            case C0182R.id.game_Button:
                this.normalLighteffect_ListView.setVisibility(8);
                this.game_ListView.setVisibility(0);
                this.custom_ListView.setVisibility(8);
                this.normalLighteffect_View.setVisibility(8);
                this.game_View.setVisibility(0);
                this.custom_View.setVisibility(8);
                this.normalLighteffect_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_transparent_halfcircle_red));
                this.game_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_blue_halfcircle_23));
                this.custom_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_transparent_halfcircle_red));
                this.normaleffectlight_selece_buttomhalf_View.setVisibility(8);
                this.game_selece_buttomhalf_View.setVisibility(0);
                this.custom_selece_buttomhalf_View.setVisibility(8);
                this.whoisshow = 1;
                return;
            case C0182R.id.custom_Button:
                this.normalLighteffect_ListView.setVisibility(8);
                this.game_ListView.setVisibility(8);
                this.custom_ListView.setVisibility(0);
                this.normalLighteffect_View.setVisibility(8);
                this.game_View.setVisibility(8);
                this.custom_View.setVisibility(0);
                this.normalLighteffect_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_transparent_halfcircle_red));
                this.game_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_transparent_halfcircle_red));
                this.custom_Button.setBackground(getActivity().getResources().getDrawable(C0182R.drawable.gradientbutton_blue_halfcircle_23));
                this.normaleffectlight_selece_buttomhalf_View.setVisibility(8);
                this.game_selece_buttomhalf_View.setVisibility(8);
                this.custom_selece_buttomhalf_View.setVisibility(0);
                this.whoisshow = 2;
                return;
            case C0182R.id.settingmusic_TextView:
                startActivity(new Intent(getActivity(), Activity_MusicList.class));
                return;
            default:
                return;
        }
    }
}
