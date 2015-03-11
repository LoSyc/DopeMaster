package com.daoguang.dopemaster.data;

/**
 * 区级地址类
 * 包含区名称,与对应的邮编
 * 引用 demo http://www.eoeandroid.com/forum.php?mod=viewthread&tid=555387&extra=page%3D1&page=1
 * Created by 麦均贤 on 2015/3/11.
 */
public class DistrictAddr {
    private String name;
    private String zipcode;

    public DistrictAddr() {
        super();
    }

    public DistrictAddr(String name, String zipcode) {
        super();
        this.name = name;
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "DistrictAddr [name=" + name + ", zipcode=" + zipcode + "]";
    }
}
