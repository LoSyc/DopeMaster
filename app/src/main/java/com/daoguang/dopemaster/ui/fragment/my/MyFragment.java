package com.daoguang.dopemaster.ui.fragment.my;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.ui.activity.my.AddrActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by joker on 2015/2/1.
 */
public class MyFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "myfragment";
    private Uri imageuri;
    public static final int take_photo=1;
    private static final int crop_photo=2;
    public CircleImageView circleImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView =inflater.inflate(R.layout.my_layout,container,false);
        RelativeLayout myhead= (RelativeLayout) myView.findViewById(R.id.head_re_layout);
       // RelativeLayout mycollect= (RelativeLayout) myView.findViewById(R.id.collect_re_layout);
        //RelativeLayout mydiscount= (RelativeLayout) myView.findViewById(R.id.dis_re_layout);
        myhead.setOnClickListener(this);

        TableRow mMyAddr = (TableRow) myView.findViewById(R.id.my_addr);

        mMyAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddrActivity.class);
                i.putExtra(TAG, 0);
                startActivityForResult(i, 0);

            }
        });

        return myView;
    }


    @Override
    public void onClick(View v) {
        showDialog();
    }
    private void showDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.my_buttom_popup_layout, null);
        final Dialog dialog = new Dialog(getActivity(), R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = LayoutParams.MATCH_PARENT;
        wl.height = LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Button myusergallery=(Button)view.findViewById(R.id.user_gallery);
        Button myusercamera= (Button) view.findViewById(R.id.user_gallery);
        Button myusercancel= (Button) view.findViewById(R.id.user_cancel);
        myusergallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery();
            }
        });
        myusercamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencamera();
            }
        });
        myusercancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
    private void opengallery(){
        File outputImage=new File(Environment.getExternalStorageDirectory(),"out_image.jpg");
        try{
            if(outputImage.exists())
            {outputImage.delete();}
            outputImage.createNewFile();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        imageuri= Uri.fromFile(outputImage);
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.putExtra("crop",true);
        intent.putExtra("scale",true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        startActivityForResult(intent,crop_photo);
    }
    private void opencamera(){
       File outImage=new File(Environment.getExternalStorageDirectory(),"tempImage.jpg");
        try{
            if(outImage.exists())
            {
                outImage.delete();
            }
            outImage.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        imageuri=Uri.fromFile(outImage);
        Intent intent=new Intent("android.media.action.IMAGE.CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        startActivityForResult(intent,take_photo);
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode)
        {
            case take_photo:
                if (resultCode== Activity.RESULT_OK)
                {
                  Intent intent= new Intent("com.android.camera.action.CROP");
                  intent.setDataAndType(imageuri,"image/*");
                  intent.putExtra("scale",true);
                  intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
                  startActivityForResult(intent,crop_photo);
                }
                break;
            case crop_photo:
                if(resultCode==Activity.RESULT_OK)
                {
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageuri));
                        circleImageView.setImageBitmap(bitmap);
                    }
                    catch (FileNotFoundException e)
                    {e.printStackTrace();}
                }
        }
    }
}
