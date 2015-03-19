package com.daoguang.dopemaster.ui.fragment.order;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daoguang.dopemaster.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ListView自定义类，包含下拉刷新功能
 * 作者：宋益聪
 * 时间：三月十一号
 */
public class Order_Listview extends ListView implements AbsListView.OnScrollListener {

    private View mHeader;// 顶部布局文件；
    private int mHeaderHeight;// 顶部布局文件的高度；
    private int mFirstVisibleItem;// 当前第一个可见的item的位置；
    private int mScrollState;// listview 当前滚动状态；
    private boolean mIsRemark;// 标记，当前是在listview最顶端摁下的；
    private int mStartY;// 摁下时的Y值；

    int mState;// 当前的状态；
    final int NONE = 0;// 正常状态；
    final int PULL = 1;// 提示下拉状态；
    final int RELESE = 2;// 提示释放状态；
    final int REFLASHING = 3;// 刷新状态；
    IReflashListener mIReflashListener;//刷新数据的接口

    /**
     * 下面的方法都为listview初始化的方法
     *
     * @param context
     */
    public Order_Listview(Context context) {
        super(context);
        initView(context);
    }

    public Order_Listview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Order_Listview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化界面，添加顶部布局文件到 listview
     *
     * @param context
     */
    private void initView(Context context) {
        //创建布局并将本身布局引进去
        LayoutInflater inflater = LayoutInflater.from(context);
        //设置头部
        mHeader = inflater.inflate(R.layout.order_listview_header, null);
        measureView(mHeader);
        mHeaderHeight = mHeader.getMeasuredHeight();
        //将布局的高设置为负值
        topPadding(-mHeaderHeight);
        this.addHeaderView(mHeader);
        this.setOnScrollListener(this);

    }

    /**
     * 通知父布局，占用的宽，高；
     *
     * @param view
     */
    private void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            //如果没有引入，就重新申请一个
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        //获取子布局的宽和高
        int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int height;
        int tempHeight = p.height;
        //如果高大于0，则
        if (tempHeight > 0) {
            //父布局将决定子布局的大小。
            height = MeasureSpec.makeMeasureSpec(tempHeight,
                    MeasureSpec.EXACTLY);
        } else {
            //父布局不决定子布局的大小。
            height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);
    }

    /**
     * 设置header 布局 上边距；
     *
     * @param topPadding
     */
    private void topPadding(int topPadding) {
        mHeader.setPadding(mHeader.getPaddingLeft(), topPadding,
                mHeader.getPaddingRight(), mHeader.getPaddingBottom());
        mHeader.invalidate();
    }
    //初始化方法结束


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mScrollState = scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mFirstVisibleItem == 0) {
                    mIsRemark = true;
                    mStartY = (int) ev.getY();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                if (mState == RELESE) {
                    mState = REFLASHING;
                    // 加载最新数据；
                    reflashViewByState();
                    mIReflashListener.onReflash();
                } else if (mState == PULL) {
                    mState = NONE;
                    mIsRemark = false;
                    reflashViewByState();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 判断移动过程操作；
     *
     * @param ev
     */
    private void onMove(MotionEvent ev) {
        if (!mIsRemark) {
            return;
        }
        int tempY = (int) ev.getY();
        int space = tempY - mStartY;
        int topPadding = space - mHeaderHeight;
        switch (mState) {
            case NONE:
                if (space > 0) {
                    mState = PULL;
                    reflashViewByState();
                }
                break;
            case PULL:
                topPadding(topPadding);
                if (space > mHeaderHeight + 30
                        && mScrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    mState = RELESE;
                    reflashViewByState();
                }
                break;
            case RELESE:
                topPadding(topPadding);
                if (space < mHeaderHeight + 30) {
                    mState = PULL;
                    reflashViewByState();
                } else if (space <= 0) {
                    mState = NONE;
                    mIsRemark = false;
                    reflashViewByState();
                }
                break;
        }
    }

    /**
     * 根据当前状态，改变界面显示；
     */
    private void reflashViewByState() {
        TextView tip = (TextView) mHeader.findViewById(R.id.order_tip);
        ImageView arrow = (ImageView) mHeader.findViewById(R.id.order_arrow);
        ProgressBar progress = (ProgressBar) mHeader.findViewById(R.id.order_progress);
        RotateAnimation anim = new RotateAnimation(0, 180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        anim.setFillAfter(true);
        RotateAnimation anim1 = new RotateAnimation(180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        anim1.setDuration(500);
        anim1.setFillAfter(true);
        switch (mState) {
            case NONE:
                arrow.clearAnimation();
                topPadding(-mHeaderHeight);
                break;

            case PULL:
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("下拉可以刷新！");
                arrow.clearAnimation();
                arrow.setAnimation(anim1);
                break;
            case RELESE:
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("松开可以刷新！");
                arrow.clearAnimation();
                arrow.setAnimation(anim);
                break;
            case REFLASHING:
                topPadding(50);
                arrow.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                tip.setText("正在刷新...");
                arrow.clearAnimation();
                break;
        }
    }

    /**
     * 获取完数据；
     */
    public void reflashComplete() {
        mState = NONE;
        mIsRemark = false;
        reflashViewByState();
        TextView lastupdatetime = (TextView) mHeader
                .findViewById(R.id.order_lastupdate_time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        lastupdatetime.setText(time);
    }

    public void setInterface(IReflashListener iReflashListener){
        mIReflashListener = iReflashListener;
    }

        /**
         * 刷新数据接口
         *
         * @author Administrator
         */
    public interface IReflashListener {
        public void onReflash();
    }
}
