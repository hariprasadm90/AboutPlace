package com.hari.aboutplaceproject.model;

public class ListItem {

    private String mTitle;
    private String mDescription;
    private String mImageHref;

    public ListItem(String title, String description, String imageHref) {
        this.mTitle = title;
        this.mDescription = description;
        this.mImageHref = imageHref;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getImageHref() {
        return mImageHref;
    }

    public void setImageHref(String imageHref) {
        this.mImageHref = imageHref;
    }
}
