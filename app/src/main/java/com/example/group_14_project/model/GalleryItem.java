package com.example.group_14_project.model;
//class use to support flickrfetcher class
//get and set caption,id,and url
public class GalleryItem {
    private String mCaption;
    private String mId;
    private String mUrl;

    public GalleryItem() {
    }

    @Override
    public String toString() {
        return mCaption;
    }


    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setCaption(String mCaption) {
        this.mCaption = mCaption;
    }
    public String getCaption() {
        return mCaption;
    }
}
