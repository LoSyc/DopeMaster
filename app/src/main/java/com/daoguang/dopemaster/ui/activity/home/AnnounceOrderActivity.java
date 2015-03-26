package com.daoguang.dopemaster.ui.activity.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.BaseActivity;
import com.daoguang.dopemaster.support.callBack.AnnounceFrCall;
import com.daoguang.dopemaster.ui.fragment.home.AnnounceOrdFragment;
import com.daoguang.dopemaster.ui.fragment.home.HuXingChooseFragment;

/**
 * Created by joker on 2015/3/15.
 */
public class AnnounceOrderActivity extends BaseActivity implements AnnounceFrCall{


    private AnnounceOrdFragment announceOrdFragment;
    private HuXingChooseFragment huxingChooseFr;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    public void initWidget() {
        setContentView(R.layout.home_announce_order);
        fragmentManager = getTheFragmentManager();

        switchFragment(0);
    }

    @Override
    public void widgetClick(View v) {


    }


    private void hideFragments(FragmentTransaction transaction) {
        if(null != announceOrdFragment){
            transaction.hide(announceOrdFragment);
        }

        if(null != huxingChooseFr){
            transaction.hide(huxingChooseFr);
        }
    }


    @Override
    public void showHuxingFr() {
       switchFragment(1);
    }

    @Override
    public void hideFragment() {
        hideFragments(transaction);
        switchFragment(0);
    }

    public void switchFragment(int id){
        transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (id) {
            case 0 :
                if(null == announceOrdFragment){
                    announceOrdFragment = new AnnounceOrdFragment();
                    transaction.add(R.id.announce_orderFr,announceOrdFragment);
                }else {
                    transaction.show(announceOrdFragment);
                }
                break;

            case 1 :
                hideFragments(transaction);
                if(null == huxingChooseFr){
                    huxingChooseFr = new HuXingChooseFragment();
                    transaction.add(R.id.announce_orderFr,huxingChooseFr);
                }else {
                    transaction.show(huxingChooseFr);
                }
                break;
        }

        transaction.commit();
    }

}
