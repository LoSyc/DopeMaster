package com.daoguang.dopemaster.ui.fragment.my;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.data.UserName;
import com.daoguang.dopemaster.ui.activity.my.AddrListActivity;
import com.daoguang.dopemaster.ui.activity.my.CouponActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import de.hdodenhof.circleimageview.CircleImageView;



/**
 * Created by joker on 2015/2/1.
 */
public class MyFragment extends Fragment implements View.OnClickListener{

    private static final String path= "/sdcard/head";
    private static final int take_photo=1;
    private static final int crop_photo=2;
    private static final int make_photo=3;
    private Bitmap headBitmap;
    public CircleImageView circleImageView;
    public TextView mynameTV;
    public EditText nameET;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView =inflater.inflate(R.layout.my_layout,container,false);
        circleImageView= (CircleImageView)myView.findViewById(R.id.head_image_view);
        mynameTV= (TextView) myView.findViewById(R.id.head_tv_first);
        initView();
        RelativeLayout myhead= (RelativeLayout) myView.findViewById(R.id.head_re_layout);
        RelativeLayout mMyCoupon = (RelativeLayout) myView.findViewById(R.id.my_coupon);
        mMyCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CouponActivity.class);
                startActivity(i);
            }
        });
        myhead.setOnClickListener(this);
        TableRow mMyAddr = (TableRow) myView.findViewById(R.id.my_addr);
        mMyAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddrListActivity.class);
                startActivity(i);
            }
        });
        return myView;
    }

    public void initView()
    {
      Bitmap head=BitmapFactory.decodeFile(path+"/headImage.jpg");
      if(head!=null)
      {
          circleImageView.setImageBitmap(head);
      }
      UserName nametext=new UserName();
      String namestr=nametext.readdata();
      mynameTV.setText(namestr);
    }
    @Override
    public void onClick(View v) {
        showDialog();
    }
    private void showDialog() {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.my_buttom_popup_layout, null);
        final Dialog dialog = new Dialog(getActivity(), R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        wl.width = LayoutParams.MATCH_PARENT;
        wl.height = LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Button myusernametext= (Button) view.findViewById(R.id.user_name);
        Button myusergallery= (Button) view.findViewById(R.id.user_gallery);
        Button myusercamera= (Button) view.findViewById(R.id.user_camera);
        Button myusercancel= (Button) view.findViewById(R.id.user_cancel);
        myusernametext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showNameTextDialog();
            }
        });
        myusergallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(Intent.ACTION_PICK,null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
            startActivityForResult(intent,take_photo);
            dialog.cancel();
            }
        });
        myusercamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file=new File(Environment.getExternalStorageDirectory(),"headImage.jpg");
                Uri uri=Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent, crop_photo);
                dialog.cancel();

            }
        });
        myusercancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode)
        {
            case take_photo:
                if (resultCode==Activity.RESULT_OK)
                {
                  cropPhoto(data.getData());
                }
                break;
            case crop_photo:
                if(resultCode==Activity.RESULT_OK)
                {
                    File temp=new File(Environment.getExternalStorageDirectory()+"/headImage.jpg");
                    cropPhoto(Uri.fromFile(temp));
                }
                break;
            case make_photo:
                if(data!=null)
                {
                    Bundle bundle=data.getExtras();
                    headBitmap=bundle.getParcelable("data");
                    if(headBitmap!=null)
                    {
                        setPicToView(headBitmap);
                        circleImageView.setImageBitmap(headBitmap);
                    }
                }
                break;
            default:break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent, make_photo);
    }
    public void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();
        String fileName =path + "/headImage.jpg";
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void showNameTextDialog()
    {
        final View myNameView=LayoutInflater.from(getActivity()).inflate(R.layout.my_name_text_layout,null);
        final Dialog nameDialog=new Dialog(getActivity(),R.style.AppTheme);
        nameDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nameDialog.setContentView(myNameView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        nameDialog.show();
        ImageView backImage= (ImageView) myNameView.findViewById(R.id.back_name);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameDialog.cancel();
            }
        });
        TextView saveView= (TextView) myNameView.findViewById(R.id.sava_name);
        nameET= (EditText)myNameView.findViewById(R.id.my_name_editText);
        String mynamestr=mynameTV.getText().toString();
        nameET.setText(mynamestr);
        nameET.setSelection(mynamestr.length());
        saveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr;
                nameStr=nameET.getText().toString().trim();
                UserName nametext=new UserName();
                mynameTV.setText(nameStr);
                nametext.savedata(nameStr);
                nameDialog.cancel();
            }
        });
    }
}
