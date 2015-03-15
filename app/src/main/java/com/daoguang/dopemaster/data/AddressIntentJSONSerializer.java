package com.daoguang.dopemaster.data;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/11.
 */
public class AddressIntentJSONSerializer {
    private static final String TAG = "AddressIntentJSONSerializer";
    private Context mContext;
    private String mFilename;

    public AddressIntentJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public ArrayList<Address> loadAddrColle() throws IOException, JSONException {
        ArrayList<Address> addrColle = new ArrayList<Address>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                Log.i(TAG, "array.length() = " + String.valueOf(array.length()));
                addrColle.add(new Address(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            Log.i(TAG, "loadAddrColle() error!");
        } finally {
            if (reader != null)
                reader.close();
        }
        return addrColle;
    }

    public void saveAddrColle(ArrayList<Address> addrColle) throws JSONException, IOException {
        // build an array in JSON
        JSONArray array = new JSONArray();
        for (Address addr : addrColle) {
            array.put(addr.toJSON());
        }
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
