package com.daoguang.dopemaster.ui.fragment.my;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.FontIconButton;
import com.daoguang.dopemaster.data.Address;
import com.daoguang.dopemaster.data.AddressLab;

import java.util.UUID;

/**
 * Address 类保存地址
 * EditText 中监听文本改变事件实时改变地址类对象内容实现自动保存
 * onActivityResult() 根据弹出的对话框与用户按下的按钮启动该页面(包括地址省市区级的选择与后退事件的监听)
 * onListenerBackAddr() 监听后退与返回按钮事件方法,用于当未填写完地址表单时,提醒用户会删除改地址
 * backLister 对象时用于在Fragment 中监听返回键所设置的私有对象
 * isContentEmpty() 判断表单内容是否有未填写的
 *
 * onPause() 中内容是修复当表单为空时退出会创建一个空对象
 * (原因:address 对象的自动保存,在建立此fragment 时就创建了一个address 对象,
 * 修复:在onPause() 中监听表单是否有空值来删除此对象,
 * 不能写在onStop()中,否则退出时会在listview中创建一个空视图,即时对象已经不存在了,原因是生命周期的内容,不详述)
 *
 * 地址三级联动引用 demo http://www.eoeandroid.com/forum.php?mod=viewthread&tid=555387&extra=page%3D1&page=1
 * Created by 麦均贤 on 2015/3/11.
 */
public class MyAddrFragment extends Fragment {
    private static final String DIALOG_DATA = "data";
    private static final int REQUEST_DATA = -1;
    private static final String TAG = "my.MyAddrFragment";
    public static final String EXTRA_ADDR_ID = "dopemaster.fragment.my.ADDR_ID";
    public static boolean deleteFlag = false;
    private FragmentManager fm;

    private Address mAddress;
    private String mAddrBuff;
    private EditText mNameEdit;
    private EditText mPhoneEdit;
    private EditText mProEdit;
    private EditText mZipCodeEdit;
    private EditText mStreetEdit;

    UUID mAddrId;

    public static MyAddrFragment newInstance(UUID AddrId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ADDR_ID, AddrId);

        MyAddrFragment fragment = new MyAddrFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddrId = (UUID) getArguments().getSerializable(EXTRA_ADDR_ID);
        mAddress = AddressLab.get(getActivity()).getAddress(mAddrId);


    }


    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addr, null);
        fm = getActivity().getSupportFragmentManager();

        mNameEdit = (EditText) view.findViewById(R.id.new_addr_name);
        mNameEdit.setText(mAddress.getContact());
        mNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAddress.setContact(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPhoneEdit = (EditText) view.findViewById(R.id.new_addr_phone);
        mPhoneEdit.setText(mAddress.getPhone());
        mPhoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAddress.setPhone(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mProEdit = (EditText) view.findViewById(R.id.new_addr_pro);
        mProEdit.setText(mAddress.getProAdd());
        mProEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    AddrPickerFragment dialog = AddrPickerFragment.newInstance(mAddrBuff);
                    dialog.setTargetFragment(MyAddrFragment.this, REQUEST_DATA);
                    dialog.show(fm, DIALOG_DATA);
                }
                return false;
            }
        });
        mProEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String[] strs = s.toString().split(" ");
                mAddress.setProAdd(strs[0]);
                mAddress.setCityAdd(strs[1]);
                mAddress.setDistAdd(strs[2]);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mZipCodeEdit = (EditText) view.findViewById(R.id.new_addr_zipcode);
        mZipCodeEdit.setText(mAddress.getZipCode());
        mZipCodeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 mAddress.setZipCode(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mStreetEdit = (EditText) view.findViewById(R.id.new_addr_street);
        mStreetEdit.setText(mAddress.getStreetAdd());
        mStreetEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAddress.setStreetAdd(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        FontIconButton mBackButton = (FontIconButton) view.findViewById(R.id.back_addrlist);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isContentEmpty()) {
                    onListenerBackAddr();
                } else {
                    getActivity().finish();
                }
            }
        });

        view.setOnKeyListener(backLister);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != Activity.RESULT_OK) return;
        if (resultCode == AddrPickerFragment.DIALOG_PROCITYDIST) {
            mAddrBuff = (String) data.getSerializableExtra(AddrPickerFragment.EXTRA_DATA);
            String[] addr = mAddrBuff.split(" ");
            mProEdit.setText(addr[0] + " " + addr[1] + " " + addr[2]);
            mZipCodeEdit.setText(addr[3]);
        }
        if (resultCode == BackDialogFragment.DIALOG_BACK_OK) {
            getActivity().finish();
        }
    }

    private void onListenerBackAddr() {
        if (isContentEmpty()) {
            BackDialogFragment dialog = new BackDialogFragment();
            dialog.setTargetFragment(MyAddrFragment.this, REQUEST_DATA);
            dialog.show(fm, DIALOG_DATA);
        } else {
            getActivity().finish();
        }
    }

    private View.OnKeyListener backLister = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    onListenerBackAddr();
                }
            }
            return false;
        }

    };

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "---------------------onStop()");
        AddressLab.get(getActivity()).saveAddrColle();
    }

    @Override
    public void onStart() {
        Log.i(TAG, "---------------------onStart()");
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "---------------------onPause()");
        if (isContentEmpty()) {
            AddressLab.get(getActivity()).deleteAddress(mAddress);
        } else {
            if (AddressLab.get(getActivity()).getAddress(mAddrId) == null) {
                AddressLab.get(getActivity()).addAddress(mAddress);
            }
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "---------------------onResume()");
        super.onResume();
    }

    public boolean isContentEmpty() {
        if (mAddress.getZipCode().equals("") || mAddress.getCityAdd().equals("") || mAddress.getContact().equals("")
                || mAddress.getDistAdd().equals("") || mAddress.getPhone().equals("") || mAddress.getProAdd().equals("")
                || mAddress.getStreetAdd().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}

