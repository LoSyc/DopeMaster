package com.daoguang.dopemaster.support.bean;

/**
 * Created by Administrator on 2015/3/14.
 */
public class PriceSort {

    private int imageId;
    private String masterName;
    private String orderNumber;

    public PriceSort(int imageId, String masterName, String orderNumber) {
        this.imageId = imageId;
        this.masterName = masterName;
        this.orderNumber = orderNumber;
    }

    public int getImageId() {
        return imageId;
    }

    public String getMasterName() {
        return masterName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
