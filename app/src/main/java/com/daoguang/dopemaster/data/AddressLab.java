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
    private static final String FILENAME = "dopemaster.address.data.json";

    private ArrayList<Address> mAddrColle;
    private AddressIntentJSONSerializer mSerializer;

    private static AddressLab sAddressLab;
    private Context mAppContext;

    private AddressLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new AddressIntentJSONSerializer(mAppContext, FILENAME);

        try {
            mAddrColle = mSerializer.loadAddrColle();
        } catch (Exception e) {
            mAddrColle = new ArrayList<Address>();
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
        for (Address addr : mAddrColle) {
            if (addr.getId().equals(id))
                return addr;
        }
        return null;
    }

    public void addAddress(Address addr) {
        mAddrColle.add(addr);
        saveAddrColle();
    }

    public ArrayList<Address> getAddrColle() {
        return mAddrColle;
    }

    public void deleteAddress(Address addr) {
        mAddrColle.remove(addr);
        Log.i(TAG, "addr was delete!");
        saveAddrColle();
    }

    public boolean saveAddrColle() {
        try {
            mSerializer.saveAddrColle(mAddrColle);
            Log.d(TAG, "address saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving address: " + e);
            return false;
        }
    }
}
