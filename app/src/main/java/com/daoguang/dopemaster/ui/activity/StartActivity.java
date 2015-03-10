package com.daoguang.dopemaster.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.support.utils.ViewFinder;
import com.daoguang.dopemaster.ui.activity.main.MainActivity;

/**
 * Created by joker on 2015/3/10.
 */
public class StartActivity extends FragmentActivity{

    private ImageView imageView;
    private TextView textView;
    private Animation entrance;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.entrance_image);
        init();
    }

    public void init() {

        ViewFinder finder = new ViewFinder(this);
        imageView = finder.find(R.id.image);
        textView = finder.find(R.id.title);
        entrance = AnimationUtils.loadAnimation(this,R.anim.entrance);

        entrance.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                    next();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(entrance);
    }




    public void next() {
        Intent  intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, R.anim.alpha_out);
    }
}
