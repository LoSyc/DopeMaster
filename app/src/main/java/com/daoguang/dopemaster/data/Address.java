package com.daoguang.dopemaster.data;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.UUID;
/**
 * 地址信息类,使用JSON 保存地址在本地
 * Created by 麦均贤 on 2015/3/11.
 */
public class Address {
    private static final String JSON_ID = "id";
    private static final String JSON_CONTACT = "contact";
    private static final String JSON_PRO = "proAdd";
    private static final String JSON_CITY = "cityAdd";
    private static final String JSON_DIST = "distAdd";
    private static final String JSON_STREET = "streetAdd";
    private static final String JSON_ZIPCODE = "zipCode";
    private static final String JSON_PHONE = "phone";
    private UUID mId;
    private String mContact = "";
    private String mProAdd = "";
    private String mCityAdd = "";
    private String mDistAdd = "";
    private String mStreetAdd = "";
    private String mZipCode = "";
    private String mPhone = "";
    public Address() {
        mId = UUID.randomUUID();
    }
    public Address(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mContact = json.getString(JSON_CONTACT);
        mProAdd = json.getString(JSON_PRO);
        mCityAdd = json.getString(JSON_CITY);
        mDistAdd = json.getString(JSON_DIST);
        mStreetAdd = json.getString(JSON_STREET);
        mZipCode = json.getString(JSON_ZIPCODE);
        mPhone = json.getString(JSON_PHONE);
    }
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_CONTACT, mContact);
        json.put(JSON_PRO, mProAdd);
        json.put(JSON_CITY, mCityAdd);
        json.put(JSON_DIST, mDistAdd);
        json.put(JSON_STREET, mStreetAdd);
        json.put(JSON_ZIPCODE, mZipCode);
        json.put(JSON_PHONE, mPhone);
        return json;
    }
    public String getPhone() {
        return mPhone;
    }
    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }
    public UUID getId() {
        return mId;
    }
    public void setId(UUID mId) {
        this.mId = mId;
    }
    public String getContact() {
        return mContact;
    }
    public void setContact(String mContact) {
        this.mContact = mContact;
    }
    public String getProAdd() {
        return mProAdd;
    }
    public void setProAdd(String mProAdd) {
        this.mProAdd = mProAdd;
    }
    public String getCityAdd() {
        return mCityAdd;
    }
    public void setCityAdd(String mCityAdd) {
        this.mCityAdd = mCityAdd;
    }
    public String getDistAdd() {
        return mDistAdd;
    }
    public void setDistAdd(String mDistAdd) {
        this.mDistAdd = mDistAdd;
    }
    public String getStreetAdd() {
        return mStreetAdd;
    }
    public void setStreetAdd(String mStreetAdd) {
        this.mStreetAdd = mStreetAdd;
    }
    public String getZipCode() {
        return mZipCode;
    }
    public void setZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }
}
