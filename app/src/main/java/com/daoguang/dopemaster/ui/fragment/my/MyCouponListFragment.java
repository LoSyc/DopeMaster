package com.daoguang.dopemaster.ui.fragment.my;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.FontIconButton;
import com.daoguang.dopemaster.support.utils.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 优惠券列表框
 * Created by 麦均贤 on 2015/3/17.
 */
public class MyCouponListFragment extends Fragment {
    private static final String TAG = "fragment.my.MyCouponFragment";
    private ListView mListView;
    private CouponAdapter mAdapter;
    private ArrayList<Coupon> mCouponColle;

    private class CouponAdapter extends BaseAdapter{
        private ArrayList<Coupon> couponColle;
        private Context context;

        public CouponAdapter(Context context, ArrayList<Coupon> couponColle) {
            this.context = context;
            this.couponColle = couponColle;
        }

        @Override
        public int getCount() {
            return couponColle.size();
        }

        @Override
        public Object getItem(int position) {
            return couponColle.get(position);
        }

        @Override
        public long getItemId(int position) {
            return couponColle.indexOf(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = ViewHolder.get(context, convertView, parent, R.layout.list_item_coupon, position);
            holder.getView(R.id.team_name);
            holder.getView(R.id.my_coupon_demand);
            holder.getView(R.id.my_coupon_limit);
            holder.getView(R.id.my_coupon_rate);
            holder.getView(R.id.my_coupon_state);

            Coupon coupon = (Coupon) getItem(holder.getPosition());

            holder.setText(R.id.team_name, coupon.getTeamName());
            holder.setText(R.id.my_coupon_rate, "￥" + String.valueOf(coupon.getRate()));
            holder.setText(R.id.my_coupon_demand, coupon.getDemand());
            if (coupon.state) {
                holder.setText(R.id.my_coupon_state, "未使用");
            } else {
                holder.setText(R.id.my_coupon_state, "已失效");
            }
            holder.setText(R.id.my_coupon_limit, coupon.getStrlimit());

            return holder.getConvertView();
        }
    }

    private class Coupon {
        private String teamName;
        private int rate;
        private boolean state;
        private String demand;
        private Date limit;
        private String strlimit;

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public String getDemand() {
            return demand;
        }

        public void setDemand(String demand) {
            this.demand = demand;
        }

        public Date getLimit() {
            return limit;
        }

        public void setLimit(Date limit) {
            this.limit = limit;
        }

        public String getStrlimit() {
            return strlimit;
        }

        public void setStrlimit(String strlimit) {
            this.strlimit = strlimit;
        }

        public Coupon(String name, int rate, String demand, boolean state, Date limit) {
            this.teamName = name;
            this.demand = demand;
            this.rate = rate;
            this.state = state;
            this.limit = limit;

            this.strlimit = new SimpleDateFormat("yyyy/MM/dd").format(limit);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date date = new Date();
        mCouponColle = new ArrayList<Coupon>();
        date.getTime();
        for (int i = 1; i < 23; i++) {
            Coupon coupon;
            if (i % 2 == 0) {
                coupon = new Coupon("装修师傅团队" + i + "号", i * 10, "订单满" + i * 32 + "元可以使用"
                        , true, date);
            } else {
                coupon = new Coupon("装修师傅团队" + i + "号", i * 10, "订单满" + i * 32 + "元可以使用"
                        , false, date);
            }
            mCouponColle.add(coupon);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_coupon_list, container, false);
        mListView = (ListView) view.findViewById(R.id.coupon_list_view);
        mAdapter = new CouponAdapter(getActivity(), mCouponColle);
        mListView.setAdapter(mAdapter);

        RelativeLayout emptyView = (RelativeLayout) view.findViewById(R.id.coupon_empty_list);
        mListView.setEmptyView(emptyView);

        FontIconButton mBackButton = (FontIconButton) view.findViewById(R.id.back_coupon);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }
}
