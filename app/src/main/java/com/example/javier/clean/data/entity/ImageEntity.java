package com.example.javier.clean.data.entity;

import com.google.gson.annotations.SerializedName;

public class ImageEntity {

    @SerializedName("img_flag")
    private String imageFlag;
    @SerializedName("img_profile")
    private String imageProfile;
    @SerializedName("img_header")
    private String imageHeader;
    @SerializedName("img_detail")
    private String imageDetail;

    public String getImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(String imageFlag) {
        this.imageFlag = imageFlag;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(String imageHeader) {
        this.imageHeader = imageHeader;
    }

    public String getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(String imageDetail) {
        this.imageDetail = imageDetail;
    }
}
