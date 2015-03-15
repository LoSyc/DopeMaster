package com.daoguang.dopemaster.ui.fragment.my;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.FontIconButton;
import com.daoguang.dopemaster.data.Address;
import com.daoguang.dopemaster.data.AddressLab;
import com.daoguang.dopemaster.support.utils.ViewHolder;
import com.daoguang.dopemaster.ui.activity.my.AddrActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/11.
 */
public class MyAddrListFragment extends Fragment {
    private static final String TAG = "fragment.my.AddressListFragment";
    private static final int REQUEST_DATA = 0;
    private static final String DIALOG_OPERATE = "operate";
    private ArrayList<Address> mAddrColle;
    private AddressAdapter adapter;
    private FragmentManager fm;
    private ListView listView;
    private Address addr;
    private int posit;

    private class AddressAdapter extends BaseAdapter {
        private ArrayList<Address> mAddrColles;
        private Context mContext;

        public AddressAdapter(Context context, ArrayList<Address> addrColle) {
            mAddrColles = addrColle;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mAddrColles.size();
        }

        @Override
        public Address getItem(int position) {
            return mAddrColles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mAddrColles.indexOf(getItem(position));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = ViewHolder.get(mContext, convertView, parent, R.layout.list_item_addr, position);
            holder.getView(R.id.addr_name);
            holder.getView(R.id.client_name);
            holder.getView(R.id.detail_address);

            Address addr = getItem(holder.getPosition());

            holder.setText(R.id.addr_name, addr.getContact() + " & 电话:" + addr.getPhone());
            holder.setText(R.id.client_name, addr.getContact());
            holder.setText(R.id.detail_address, addr.getProAdd() + addr.getCityAdd() + addr.getDistAdd()
                    + addr.getStreetAdd() + " 邮编: " + addr.getZipCode());

            return holder.getConvertView();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mAddrColle = AddressLab.get(getActivity()).getAddrColle();
    }


    @Override
    @TargetApi(11)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.i(TAG, "MyAddrListFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_addr_list, container, false);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new AddressAdapter(getActivity(), mAddrColle);
        listView.setAdapter(adapter);
        RelativeLayout emptyView = (RelativeLayout) view.findViewById(R.id.empty_list);
        listView.setEmptyView(emptyView);

        AddressLab addrLab = AddressLab.get(getActivity());
        for (int i = adapter.getCount() - 1; i >= 0; i--) {
            if (MyAddrFragment.deleteFlag) {
                addrLab.deleteAddress(adapter.getItem(i));
                Log.i(TAG, "mAddress was delete!");
            }
        }
        adapter.notifyDataSetChanged();
        fm = getActivity().getSupportFragmentManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addr = ((AddressAdapter) listView.getAdapter()).getItem(position);
                Intent intent = new Intent(getActivity(), AddrActivity.class);
                intent.putExtra(MyAddrFragment.EXTRA_ADDR_ID, addr.getId());
                startActivityForResult(intent, 0);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                addr = ((AddressAdapter) listView.getAdapter()).getItem(position);
                posit = position;
                OperateDialogFragment dialog = OperateDialogFragment.newInstance(addr.getId());
                dialog.setTargetFragment(MyAddrListFragment.this, REQUEST_DATA);
                dialog.show(fm, DIALOG_OPERATE);
                return true;
            }
        });

        FontIconButton mBackButton = (FontIconButton) view.findViewById(R.id.back_addr);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        FontIconButton mCreateAddrButton = (FontIconButton) view.findViewById(R.id.create_addr);
        mCreateAddrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAddress();
            }
        });

        Button mEmptyCreatAddrButton = (Button) view.findViewById(R.id.empty_creat_addr);
        mEmptyCreatAddrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAddress();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((AddressAdapter) listView.getAdapter()).notifyDataSetChanged();
        if (requestCode != Activity.RESULT_OK) return;
        if (resultCode == OperateDialogFragment.DIALOG_MODIFY) {
            Log.i(TAG, "DIALOG_MODIFY was click!");
            addr = ((AddressAdapter) listView.getAdapter()).getItem(posit);
            Intent intent = new Intent(getActivity(), AddrActivity.class);
            intent.putExtra(MyAddrFragment.EXTRA_ADDR_ID, addr.getId());
            startActivityForResult(intent, 0);
        }
        if (resultCode == OperateDialogFragment.DIALOG_DELETE) {
            Log.i(TAG, "DIALOG_DELETE was click!");
            AddressLab addrLab = AddressLab.get(getActivity());
            addrLab.deleteAddress(adapter.getItem(posit));
            Log.i(TAG, "mAddress was delete!");
            adapter.notifyDataSetChanged();
        }
    }

    public void createAddress() {
        Address addr = new Address();
        AddressLab.get(getActivity()).getAddrColle().add(addr);
        Intent intent = new Intent(getActivity(), AddrActivity.class);
        intent.putExtra(MyAddrFragment.EXTRA_ADDR_ID, addr.getId());
        Log.i(TAG, "UUID = " + String.valueOf(addr.getId()));
        startActivityForResult(intent, 0);
    }

}
