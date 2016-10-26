package org.gyjl.superweibo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/26.
 */

public class Gallery {
    @SerializedName("id")
    private int id;
    @SerializedName("galleryclass")
    private int  galleryclass ;//          图片分类
    @SerializedName("title")
    private String title     ;//          标题
    @SerializedName("img")
    private String img     ;//          图库封面
    @SerializedName("count")
    private int count     ;//          访问数
    @SerializedName("rcount")
    private int rcount     ;//           回复数
    @SerializedName("fcount")
    private int fcount     ;//          收藏数

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("size")
    private int size     ;//      图片多少张
}
