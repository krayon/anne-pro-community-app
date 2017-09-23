package com.obins.anne.listmusic;

import android.text.TextUtils;
import com.obins.anne.utils.Music;
import java.util.ArrayList;
import java.util.List;

public class TitleToPinyinUtil {
    private static List<Music> SourceDateList = new ArrayList();
    private static CharacterParser characterParser = CharacterParser.getInstance();

    public static List<Music> filterData(String filterStr, List<Music> musicList) {
        if (TextUtils.isEmpty(filterStr)) {
            SourceDateList = musicList;
        } else {
            SourceDateList.clear();
            for (Music music : musicList) {
                String name = music.getTitle();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    SourceDateList.add(music);
                }
            }
        }
        return SourceDateList;
    }

    public static List<Music> filledData(String[] date, List<Music> SourceDateList) {
        for (int i = 0; i < date.length; i++) {
            Music music = (Music) SourceDateList.get(i);
            music.setTitle(date[i]);
            String sortString = characterParser.getSelling(date[i]).substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                music.setSortletters(sortString.toUpperCase());
            } else {
                music.setSortletters("#");
            }
        }
        return SourceDateList;
    }
}
