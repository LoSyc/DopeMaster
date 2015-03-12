package com.daoguang.dopemaster.sys.service;

import com.daoguang.dopemaster.data.CityAddr;
import com.daoguang.dopemaster.data.DistrictAddr;
import com.daoguang.dopemaster.data.ProvinceAddr;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * xml 解析类
 * 使用 SAX 解析省市区 xml 文件(省市区的对应信息保存在 assets/province_data.xml)
 * 引用 demo http://www.eoeandroid.com/forum.php?mod=viewthread&tid=555387&extra=page%3D1&page=1
 * Created by 麦均贤 on 2015/3/11.
 */
public class XmlParserHandler extends DefaultHandler {

    private List<ProvinceAddr> provinceList = new ArrayList<ProvinceAddr>();

    public XmlParserHandler() {

    }

    public List<ProvinceAddr> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {
        // 当读到第一个开始标签的时候，会触发这个方法
    }

    ProvinceAddr provinceModel = new ProvinceAddr();
    CityAddr cityModel = new CityAddr();
    DistrictAddr districtModel = new DistrictAddr();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // 当遇到开始标记的时候，调用这个方法
        if (qName.equals("province")) {
            provinceModel = new ProvinceAddr();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setCityList(new ArrayList<CityAddr>());
        } else if (qName.equals("city")) {
            cityModel = new CityAddr();
            cityModel.setName(attributes.getValue(0));
            cityModel.setDistrictList(new ArrayList<DistrictAddr>());
        } else if (qName.equals("district")) {
            districtModel = new DistrictAddr();
            districtModel.setName(attributes.getValue(0));
            districtModel.setZipcode(attributes.getValue(1));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // 遇到结束标记的时候，会调用这个方法
        if (qName.equals("district")) {
            cityModel.getDistrictList().add(districtModel);
        } else if (qName.equals("city")) {
            provinceModel.getCityList().add(cityModel);
        } else if (qName.equals("province")) {
            provinceList.add(provinceModel);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    }
}
