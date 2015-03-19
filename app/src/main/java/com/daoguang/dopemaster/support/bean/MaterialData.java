package com.daoguang.dopemaster.support.bean;

import com.daoguang.dopemaster.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/13.
 */
public class MaterialData {

    private final static int STAGGEREDGRID_DATA_ITEM_COUNT = 4;
    private static MaterialObject mObject;

    private static int [] mImageData = {R.drawable.home_material_1,
                                R.drawable.home_material_2,
                                R.drawable.home_material_3,
                                R.drawable.home_material_4};

    private static String [] mTextData = {"装修材料 ： 立体门头材料",
                                   "装修材料 ： 3D门头",
                                   "装修材料 ： 玉石玻璃",
                                   "装修材料 ： 德工漆"};

    public static ArrayList<MaterialObject> generateMaterialData() {
        final ArrayList<MaterialObject> mData = new ArrayList<MaterialObject>();

        for (int i = 0; i < STAGGEREDGRID_DATA_ITEM_COUNT; i++) {
            mObject = new MaterialObject(mImageData[i], mTextData[i]);
            mData.add(mObject);
        }

        return mData;
    }
}
