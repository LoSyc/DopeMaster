package com.daoguang.dopemaster.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.BaseActivity;
import com.daoguang.dopemaster.support.utils.ViewFinder;

/**
 * Created by joker on 2015/3/16.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private Button yzmBt;

    @Override
    public void initWidget() {
        setContentView(R.layout.register_layout);
        ViewFinder finder = new ViewFinder(this);
        yzmBt = finder.find(R.id.yzmBt);
        yzmBt.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.yzmBt :
                new CountDownTimer(60000, 1000) {
                    // 第一个参数是总的倒计时时间
                    // 第二个参数是每隔多少时间(ms)调用一次onTick()方法
                    public void onTick(long millisUntilFinished) {
                        yzmBt.setText(millisUntilFinished / 1000 + "s");
                        yzmBt.setEnabled(false);
                    }

                    public void onFinish() {
                        yzmBt.setText("获取验证码");
                        yzmBt.setEnabled(true);
                    }
                }.start();
                break;

            case R.id.loginBt :

                break;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
