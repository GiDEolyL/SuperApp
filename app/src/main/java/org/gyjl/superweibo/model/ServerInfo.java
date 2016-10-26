package org.gyjl.superweibo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ServerInfo {
    @SerializedName("name")
    private String mName;
    @SerializedName("version")
    private String mVersion;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("updateTime")
    private String mUpdateTime;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        mUpdateTime = updateTime;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }
}
