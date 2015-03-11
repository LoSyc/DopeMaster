package com.daoguang.dopemaster.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2015/3/11.
 */
public class AddressLab {
    private static final String TAG = "addressLab";
    private static final String FILENAME = "address.json";

    private ArrayList<Address> mAddress;
    private AddressIntentJSONSerializer mSerializer;

    private static AddressLab sAddressLab;
    private Context mAppContext;

    private AddressLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new AddressIntentJSONSerializer(mAppContext, FILENAME);

        try {
            mAddress = mSerializer.loadAddress();
        } catch (Exception e) {
            mAddress = new ArrayList<Address>();
            Log.e(TAG, "Error loading address: ", e);
        }
    }

    public static AddressLab get(Context c) {
        if (sAddressLab == null) {
            sAddressLab = new AddressLab(c.getApplicationContext());
        }
        return sAddressLab;
    }

    public Address getAddress(UUID id) {
        for (Address add : mAddress) {
            if (add.getId().equals(id))
                return add;
        }
        return null;
    }

    public void addAddress(Address c) {
        mAddress.add(c);
        saveAddresss();
    }

    public ArrayList<Address> getAddresss() {
        return mAddress;
    }

    public void deleteAddress(Address c) {
        mAddress.remove(c);
        saveAddresss();
    }

    public boolean saveAddresss() {
        try {
            mSerializer.saveAddresss(mAddress);
            Log.d(TAG, "address saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving address: " + e);
            return false;
        }
    }
}
