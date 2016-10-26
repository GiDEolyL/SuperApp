package org.gyjl.superweibo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class GalleryList {
    @SerializedName("status")
    private boolean status;
    @SerializedName("tngou")
    private List<Gallery> mGalleries;

    public List<Gallery> getGalleries() {
        return mGalleries;
    }

    public void setGalleries(List<Gallery> galleries) {
        mGalleries = galleries;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
