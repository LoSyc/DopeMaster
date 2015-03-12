package com.daoguang.dopemaster.ui.fragment.show;

/**
 * Created by Administrator on 2015/3/11.
 */
public class Show_Picture {
    private String title;
    private int imageId;
    private String price;

    public Show_Picture()
    {
        super();
    }

    public Show_Picture(String title, int imageId,String price)
    {
        super();
        this.title = title;
        this.imageId = imageId;
        this.price=price;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getImageId()
    {
        return imageId;
    }

    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price;}
}
