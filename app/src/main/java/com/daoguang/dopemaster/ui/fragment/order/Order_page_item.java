package com.daoguang.dopemaster.ui.fragment.order;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.daoguang.dopemaster.R;

/**
 * Created by Administrator on 2015/3/20.
 */
public class Order_page_item extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        setContentView(R.layout.order_page);

    }
}
