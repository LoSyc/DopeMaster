package com.daoguang.dopemaster.support.bean;

/**
 * Created by joker on 2015/3/22.
 */
public class DesignInfo {
    private String style;
    private int imagId;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getImagId() {
        return imagId;
    }

    public void setId(int id) {
        this.imagId = id;
    }

    public DesignInfo(){

    }

    public DesignInfo(String style, int imagId) {
        this.style = style;
        this.imagId = imagId;
    }
}
