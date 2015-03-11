package com.daoguang.dopemaster.data;

import java.util.List;

/**
 * 市级地址类
 * 包含市名称,与区级联动
 * 引用 demo http://www.eoeandroid.com/forum.php?mod=viewthread&tid=555387&extra=page%3D1&page=1
 * Created by 麦均贤 on 2015/3/11.
 */
public class CityAddr {
    private String name;
    private List<DistrictAddr> districtList;

    public CityAddr() {
        super();
    }

    public CityAddr(String name, List<DistrictAddr> districtList) {
        super();
        this.name = name;
        this.districtList = districtList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictAddr> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictAddr> districtList) {
        this.districtList = districtList;
    }

    @Override
    public String toString() {
        return "CityAddr [name=" + name + ", districtList=" + districtList
                + "]";
    }
}
