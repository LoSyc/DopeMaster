package com.daoguang.dopemaster.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2015-3-20.
 */
public class UserName {
    private final static String path="/sdcard/head";
    public void savedata(String username)
    {
        try {
            File file=new File(path,"UserName.properties");
            file.createNewFile();
            FileOutputStream fout=new FileOutputStream(file);
            Properties pro=new Properties();
            pro.setProperty("UserName",username);
            pro.store(fout,"UserName.properties");
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public String readdata()
    {
        String username;
        File file=new File(path,"UserName.properties");
        try {
            if(file.exists())
            {
                FileInputStream fin=new FileInputStream(file);
                Properties pro=new Properties();
                pro.load(fin);
                username=pro.getProperty("UserName");
                fin.close();
            }
            else {
                username="null";
                savedata(username);
            }
            return username;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
