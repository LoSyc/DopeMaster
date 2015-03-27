package com.daoguang.dopemaster.ui.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.support.callBack.AnnounceFrCall;
import com.daoguang.dopemaster.ui.view.TopBar;

/**
 * Created by joker on 2015/3/17.
 */
public class AnnounceOrdFragment extends Fragment implements View.OnClickListener{

    private TextView danweiTv;
    private View huxingLo;
    private TextView huxingTv;

    private View designLayout;

    private AnnounceFrCall call;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View announceOrdLay = inflater.inflate(R.layout.home_announce_order_layout,container,false);
        danweiTv = (TextView) announceOrdLay.findViewById(R.id.mi_id);
        SpannableString spanText = new SpannableString("m2");
        spanText.setSpan(new SuperscriptSpan(), 1, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        danweiTv.append(spanText);

        huxingTv = (TextView) announceOrdLay.findViewById(R.id.huxingTv);

        huxingLo = announceOrdLay.findViewById(R.id.huxingId);
        huxingLo.setOnClickListener(this);

        designLayout = announceOrdLay.findViewById(R.id.designLayout);
        designLayout.setOnClickListener(this);

        TopBar topBar = (TopBar) announceOrdLay.findViewById(R.id.topBar);
        topBar.setOnTopBarClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {
                getActivity().finish();
            }

            @Override
            public void rightClick() {

            }
        });
        return announceOrdLay ;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof AnnounceFrCall) {
            call = (AnnounceFrCall) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implements AnnounceFrCall");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            String evaluate = data
                    .getStringExtra(HuXingChooseFragment.RESPONSE_EVALUATE);
            huxingTv.setText(evaluate);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.huxingId :
                call.showHuxingFr();
                break;

            case R.id.designLayout :
                call.showDesignFr();
                break;
        }
    }
}
