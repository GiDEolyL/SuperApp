package org.gyjl.superweibo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class GalleryClassList {
    @SerializedName("status")
    private boolean status;

    @SerializedName("tngou")
    private List<Galleryclass> mGalleryclasses;

    public List<Galleryclass> getGalleryclasses() {
        return mGalleryclasses;
    }

    public void setGalleryclasses(List<Galleryclass> galleryclasses) {
        mGalleryclasses = galleryclasses;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
