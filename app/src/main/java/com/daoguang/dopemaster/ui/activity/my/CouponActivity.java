package com.daoguang.dopemaster.ui.activity.my;

import android.support.v4.app.Fragment;
import com.daoguang.dopemaster.ui.fragment.my.MyCouponListFragment;

/**
 * 托管优惠券的Activity
 * Created by 麦均贤 on 2015/3/17.
 */
public class CouponActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MyCouponListFragment();
    }
}
