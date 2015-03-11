package com.daoguang.dopemaster.data;

import java.util.List;

/**
 * 省级地址类
 * 包含省名称,与市级联动
 * 引用 demo http://www.eoeandroid.com/forum.php?mod=viewthread&tid=555387&extra=page%3D1&page=1
 * Created by 麦均贤 on 2015/3/11.
 */
public class ProvinceAddr {
    private String name;
    private List<CityAddr> cityList;

    public ProvinceAddr() {
        super();
    }

    public ProvinceAddr(String name, List<CityAddr> cityList) {
        super();
        this.name = name;
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityAddr> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityAddr> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "ProvinceAddr [name=" + name + ", cityList=" + cityList + "]";
    }
}
