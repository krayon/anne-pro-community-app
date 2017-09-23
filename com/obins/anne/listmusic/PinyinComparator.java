package com.obins.anne.listmusic;

import com.obins.anne.utils.Music;
import java.util.Comparator;

public class PinyinComparator implements Comparator<Music> {
    public int compare(Music o1, Music o2) {
        if (o1.getSortletters().equals("@") || o2.getSortletters().equals("#")) {
            return -1;
        }
        if (o1.getSortletters().equals("#") || o2.getSortletters().equals("@")) {
            return 1;
        }
        return o1.getSortletters().compareTo(o2.getSortletters());
    }
}
