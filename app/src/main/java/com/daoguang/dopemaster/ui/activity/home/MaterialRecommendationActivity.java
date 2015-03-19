package com.daoguang.dopemaster.ui.activity.home;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.BaseActivity;
import com.daoguang.dopemaster.support.adapter.HomeStaggeredGridAdapter;
import com.daoguang.dopemaster.support.bean.MaterialData;
import com.daoguang.dopemaster.support.bean.MaterialObject;
import com.daoguang.dopemaster.ui.view.TopBar;
import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;


public class MaterialRecommendationActivity extends BaseActivity implements
        AbsListView.OnScrollListener,AbsListView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,TopBar.TopBarClickListener,SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "MaterialRecommendation";

    private ArrayList<MaterialObject> mData;
    private HomeStaggeredGridAdapter mAdapter;
    private StaggeredGridView mGridView;
    private TopBar mTopBar;
    private boolean mHasRequestedMore;
    private SwipeRefreshLayout mRefresh;

    @Override
    public void initWidget() {
        setContentView(R.layout.home_material_recommendation);

        mGridView = (StaggeredGridView)findViewById(R.id.grid_view);
        mTopBar = (TopBar) findViewById(R.id.topBar);
        mRefresh = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);

        mRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefresh.setOnRefreshListener(this);

        mTopBar.setLeftIsVisable(true);
        mTopBar.setrightIsVisable(false);
        mTopBar.setOnTopBarClickListener(this);

        mAdapter = new HomeStaggeredGridAdapter(this, R.id.dynamic_imageview);

        if(mData == null) {
            mData = MaterialData.generateMaterialData();
        }

        for (MaterialObject object : mData) {
            mAdapter.add(object);
        }

        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
        mGridView.setOnItemLongClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.topBar:
                leftClick();
                rightClick();
                break;

            case R.id.swipeRefreshLayout:
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Item Long CLick" + position, Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Item CLick" + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
        // our handling
        
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d(TAG, "onScroll lastInScreen - so load more");
                mHasRequestedMore = true;
                onLoadMoreItems();
            }
        }
    }

    private void onLoadMoreItems() {
        final ArrayList<MaterialObject> mData = MaterialData.generateMaterialData();
        for (MaterialObject object  : mData) {
            mAdapter.add(object);
        }
        // stash all the data in our backing store
        mData.addAll(mData);
        // notify the adapter that we can update now
        mAdapter.notifyDataSetChanged();
        mHasRequestedMore = false;
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void onRefresh() {
        //操作

        //停止刷新
        mRefresh.setRefreshing(false);
    }
}
