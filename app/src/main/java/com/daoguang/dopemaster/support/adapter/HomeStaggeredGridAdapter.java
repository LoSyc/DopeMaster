package com.daoguang.dopemaster.support.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.support.bean.MaterialObject;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.etsy.android.grid.util.DynamicHeightTextView;

import java.util.Random;

/**
 * Created by Administrator on 2015/3/12.
 */
public class HomeStaggeredGridAdapter extends ArrayAdapter<MaterialObject> {

   static class ViewHolder {
        private DynamicHeightImageView dynamicImgV;
        private LinearLayout collectLinearLayout;
        private LinearLayout likeLinearLayout;
        private ImageView dividerImgV;
        private ImageView collectImgV;
        private ImageView likeImgV;
        private TextView textView;
        private TextView collectTxtV;
        private TextView likeTxtV;
    }

    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final String TAG = "HomeStaggeredGridAdapter";

    private static int [] mImageData = {R.drawable.home_material_1,
            R.drawable.home_material_2,
            R.drawable.home_material_3,
            R.drawable.home_material_4};

    private static String [] mTextData = {"装修材料 ： 立体门头材料",
            "装修材料 ： 3D门头",
            "装修材料 ： 玉石玻璃",
            "装修材料 ： 德工漆"};

    private int mImageId;
    private int mTextId;

    public HomeStaggeredGridAdapter(final Context context, final int dynamicImageiewResourceId) {
        super(context, dynamicImageiewResourceId);
        mLayoutInflater = LayoutInflater.from(context);
        mRandom = new Random();
    }

    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        MaterialObject mObject;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.home_material_list_item, parent, false);
            vh = new ViewHolder();

            vh.dynamicImgV = (DynamicHeightImageView) convertView.findViewById(R.id.dynamic_imageview);
            vh.textView = (TextView) convertView.findViewById(R.id.text_view);
            vh.collectLinearLayout = (LinearLayout)convertView.findViewById(R.id.home_material_collect);
            vh.likeLinearLayout = (LinearLayout)convertView.findViewById(R.id.home_material_like);
            vh.dividerImgV = (ImageView) convertView.findViewById(R.id.divider_imageview);
            vh.collectImgV = (ImageView) convertView.findViewById(R.id.collect_imageview);
            vh.likeImgV = (ImageView) convertView.findViewById(R.id.like_imageview);
            vh.collectTxtV = (TextView) convertView.findViewById(R.id.collect_textview);
            vh.likeTxtV = (TextView) convertView.findViewById(R.id.like_textview);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);
        Log.d(TAG, "getView position:" + position + " h:" + positionHeight);

        mImageId = position >= mImageData.length?
                position % mImageData.length : position;
        mTextId = position >= mTextData.length?
                position % mTextData.length : position;

        mObject = new MaterialObject(mImageData[mImageId], mTextData[mTextId]);
        vh.dynamicImgV.setHeightRatio(positionHeight);
        vh.dynamicImgV.setImageResource(mObject.getImageId());

        vh.textView.setText(mObject.getText());

        vh.collectLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int collectCount = Integer.parseInt((String) vh.collectTxtV.getText());
                vh.collectTxtV.setText((collectCount+1));
            }
        });

        vh.likeTxtV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int likeCount = Integer.parseInt((String) vh.likeTxtV.getText());
                vh.likeTxtV.setText((likeCount+1));
            }
        });
        return convertView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 5.0) + 1.0; // height will be 1.0 - 1.5 the width
    }
}