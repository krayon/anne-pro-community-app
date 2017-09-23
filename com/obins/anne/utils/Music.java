package com.obins.anne.utils;

public class Music {
    private String album;
    private String artists;
    private int id;
    private String path;
    private long size;
    private String sortletters;
    private String times;
    private String title;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtists() {
        return this.artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTimes() {
        return this.times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getSortletters() {
        return this.sortletters;
    }

    public void setSortletters(String sortletters) {
        this.sortletters = sortletters;
    }
}
