package com.daoguang.dopemaster.ui.fragment.show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daoguang.dopemaster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片适配器
 * Created by Administrator on 2015/3/11.
 */
public class Show_PictureAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Show_Picture> pictures;

    public Show_PictureAdapter(String[] titles, int[] images, String[] prices, Context context)
    {
        super();
        pictures = new ArrayList<Show_Picture>();
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < images.length; i++)
        {
            Show_Picture picture = new Show_Picture(titles[i], images[i],prices[i]);
            pictures.add(picture);
        }
    }

    @Override
    public int getCount()
    {
        if (null != pictures)
        {
            return pictures.size();
        } else
        {
            return 0;
        }
    }

    @Override
    public Object getItem(int position)
    {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Show_ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.show_pictureitem, null);
            viewHolder = new Show_ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.show_title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.show_image);
            viewHolder.price=(TextView) convertView.findViewById(R.id.show_price);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (Show_ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(pictures.get(position).getTitle());
        viewHolder.image.setImageResource(pictures.get(position).getImageId());
        viewHolder.price.setText(pictures.get(position).getPrice());
        return convertView;
    }

}
