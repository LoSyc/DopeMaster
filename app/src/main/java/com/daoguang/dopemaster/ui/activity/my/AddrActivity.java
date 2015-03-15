package com.daoguang.dopemaster.ui.activity.my;

import android.support.v4.app.Fragment;
import com.daoguang.dopemaster.ui.fragment.my.MyAddrFragment;

import java.util.UUID;

/**
 * 托管地址类
 * 引用 demo http://www.eoeandroid.com/forum.php?mod=viewthread&tid=555387&extra=page%3D1&page=1
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
