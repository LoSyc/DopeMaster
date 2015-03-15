package com.daoguang.dopemaster.ui.fragment.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.data.CityAddr;
import com.daoguang.dopemaster.data.DistrictAddr;
import com.daoguang.dopemaster.data.ProvinceAddr;
import com.daoguang.dopemaster.sys.service.XmlParserHandler;
import demo.wheel.widget.OnWheelChangedListener;
import demo.wheel.widget.WheelView;
import demo.wheel.widget.adapters.ArrayWheelAdapter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地址三级联动对话框
 * 地址三级联动引用 demo http://www.eoeandroid.com/forum.php?mod=viewthread&tid=555387&extra=page%3D1&page=1
 * Created by 麦均贤 on 2015/3/13.
 */
public class AddrPickerFragment extends DialogFragment implements OnWheelChangedListener {
    public static final String EXTRA_DATA = "fragment.my.addrpickerfragment.DATA";
    public static final int DIALOG_PROCITYDIST = 2;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    protected String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     * key - 市 values - 区
     * key - 区 values - 邮编
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    protected String mCurrentProviceName;
    protected String mCurrentCityName;
    protected String mCurrentDistrictName = "";
    protected String mCurrentZipCode = "";

    private String mAddr;

    public static AddrPickerFragment newInstance(String addr) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATA, addr);

        AddrPickerFragment fragment = new AddrPickerFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mAddr = (String) getArguments().getSerializable(EXTRA_DATA);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_procitydist, null);
        setUpViews(view);
        setUpListener();
        setUpData();

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.addr_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mAddr = mCurrentProviceName + " " + mCurrentCityName + " "
                                + mCurrentDistrictName + " " + mCurrentZipCode;
                        getArguments().putSerializable(EXTRA_DATA, mAddr);
                        sendResult(DIALOG_PROCITYDIST);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_DATA, mAddr);

        getTargetFragment()
                .onActivityResult(Activity.RESULT_OK, resultCode, i);
    }

    /**
     * 解析省市区的XML数据
     */
    protected void initProvinceDatas() {
        List<ProvinceAddr> provinceList = null;
        AssetManager asset = getActivity().getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            // 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityAddr> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictAddr> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }

            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityAddr> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictAddr> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictAddr[] distrinctArray = new DistrictAddr[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictAddr districtModel = new DistrictAddr(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void setUpViews(View v) {
        mViewProvince = (WheelView) v.findViewById(R.id.id_province);
        mViewCity = (WheelView) v.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) v.findViewById(R.id.id_district);
    }

    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), areas));
        mViewDistrict.setCurrentItem(0);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

}
