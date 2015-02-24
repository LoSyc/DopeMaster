package com.daoguang.dopemaster.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoguang.dopemaster.R;

/**
 * Created by joker on 2015/2/23.
 */
public class TopBar extends RelativeLayout{

    private Button leftButton,rightButton;
    private TextView tvTitle;

    private Drawable leftIcon;

    private String titleText;
    private float titleTextSize;
    private int titleTextColor;

    private Drawable rightIcon;

    private float leftMargin,topMargin;

    private LayoutParams leftParams,rightParams,titleParams;

    private TopBarClickListener topBarClickListener;

    public interface TopBarClickListener {
        public void leftClick();
        public void rightClick();
    }

    public void setOnTopBarClickListener(TopBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("deprecation")
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        leftIcon = ta.getDrawable(R.styleable.TopBar_leftIcon);

        titleText = ta.getString(R.styleable.TopBar_titleText);
        titleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor,0);
        titleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize,0);

        leftMargin = ta.getDimension(R.styleable.TopBar_leftMargin,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                15, context.getResources().getDisplayMetrics()));
        topMargin = ta.getDimension(R.styleable.TopBar_topMargin,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2, context.getResources().getDisplayMetrics()));

        rightIcon = ta.getDrawable(R.styleable.TopBar_rightIcon);

        ta.recycle();

        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            leftButton.setBackgroundDrawable(leftIcon);
            rightButton.setBackgroundDrawable(rightIcon);
        } else {
            leftButton.setBackground(leftIcon);
            rightButton.setBackground(rightIcon);
        }

        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(titleText);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(Color.parseColor("#5F97FA"));

        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL,TRUE);
        leftParams.setMargins((int)leftMargin,(int)topMargin,0,0);

        addView(leftButton, leftParams);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL,TRUE);
        rightParams.setMargins((int)leftMargin,(int)topMargin,0,0);

        addView(rightButton,rightParams);

        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);

        addView(tvTitle,titleParams);
        
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarClickListener.leftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarClickListener.rightClick();
            }
        });
    }

    public void setLeftIsVisable(Boolean flag) {
        if(flag){
            leftButton.setVisibility(View.VISIBLE);
        }else{
            leftButton.setVisibility(View.GONE);
        }
    }

    public void setrightIsVisable(Boolean flag) {
        if(flag){
            rightButton.setVisibility(View.VISIBLE);
        }else{
            rightButton.setVisibility(View.GONE);
        }
    }
}
