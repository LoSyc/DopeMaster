package com.daoguang.dopemaster.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by Administrator on 2015/3/11.
 */
public class Address {
    private static final String JSON_ID = "id";
    private static final String JSON_PRO = "proAdd";
    private static final String JSON_CITY = "cityAdd";
    private static final String JSON_DIST = "distAdd";
    private static final String JSON_STREET = "streetAdd";
    private static final String JSON_ZIPCODE = "zipCode";

    private UUID mId;
    private String mProAdd;
    private String mCityAdd;
    private String mDistAdd;
    private String mStreetAdd;
    private String mZipCode;

    public Address(ProvinceAddr proAdd, CityAddr cityAdd, DistrictAddr distAdd) {
        mId = UUID.randomUUID();
        mProAdd = proAdd.getName();
        mCityAdd = cityAdd.getName();
        mDistAdd = distAdd.getName();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public Address(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mProAdd = json.getString(JSON_PRO);
        mCityAdd = json.getString(JSON_CITY);
        mDistAdd = json.getString(JSON_DIST);
        mStreetAdd = json.getString(JSON_STREET);
        mZipCode = json.getString(JSON_ZIPCODE);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_PRO, mProAdd);
        json.put(JSON_CITY, mCityAdd);
        json.put(JSON_DIST, mDistAdd);
        json.put(JSON_STREET, mStreetAdd);
        json.put(JSON_ZIPCODE, mZipCode);
        return json;
    }

    public String getmProAdd() {
        return mProAdd;
    }

    public void setmProAdd(String mProAdd) {
        this.mProAdd = mProAdd;
    }

    public String getmCityAdd() {
        return mCityAdd;
    }

    public void setmCityAdd(String mCityAdd) {
        this.mCityAdd = mCityAdd;
    }

    public String getmDistAdd() {
        return mDistAdd;
    }

    public void setmDistAdd(String mDistAdd) {
        this.mDistAdd = mDistAdd;
    }

    public String getmStreetAdd() {
        return mStreetAdd;
    }

    public void setmStreetAdd(String mStreetAdd) {
        this.mStreetAdd = mStreetAdd;
    }

    public String getmZipCode() {
        return mZipCode;
    }

    public void setmZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }
}
