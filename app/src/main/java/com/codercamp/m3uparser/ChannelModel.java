package com.codercamp.m3uparser;

/**
 * Created by Md. Shamim Hossain on 5/30/2024.
 * mdshamimh529@gmail.com
 */
public class ChannelModel {
    String title = null;
    String path = null;
    String logo = null;
    String mimeType = null;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
