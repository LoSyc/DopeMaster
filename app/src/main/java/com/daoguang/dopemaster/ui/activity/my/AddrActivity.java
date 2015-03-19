package com.daoguang.dopemaster.ui.activity.my;

import android.support.v4.app.Fragment;
import com.daoguang.dopemaster.ui.fragment.my.MyAddrFragment;

import java.util.UUID;

/**
 * 托管地址类
 * 用于往Fragment中传递参数
 * Created by 麦均贤 on 2015/3/11.
 */

public class AddrActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID addrId = (UUID) getIntent()
                .getSerializableExtra(MyAddrFragment.EXTRA_ADDR_ID);
        return MyAddrFragment.newInstance(addrId);
    }


}
