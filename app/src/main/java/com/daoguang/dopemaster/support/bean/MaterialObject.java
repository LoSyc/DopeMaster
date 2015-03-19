package com.daoguang.dopemaster.support.bean;

/**
 * Created by Administrator on 2015/3/12.
 */
public class MaterialObject {

    private String text;
    private int imageId;

    public MaterialObject() {}
    public MaterialObject(int imageId, String text) {
        this.imageId = imageId;
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public int getImageId() {
        return imageId;
    }
}
