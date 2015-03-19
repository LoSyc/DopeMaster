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
import com.daoguang.dopemaster.ui.activity.my.AddrListActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by joker on 2015/2/1.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private Uri imageuri;
    private static final int take_photo = 1;
    private static final int crop_photo = 2;
    public CircleImageView circleImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.my_layout, container, false);
        RelativeLayout myhead = (RelativeLayout) myView.findViewById(R.id.head_re_layout);
        // RelativeLayout mycollect= (RelativeLayout) myView.findViewById(R.id.collect_re_layout);
        //RelativeLayout mydiscount= (RelativeLayout) myView.findViewById(R.id.dis_re_layout);
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
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Button myusergallery = (Button) view.findViewById(R.id.user_gallery);
        Button myusercamera = (Button) view.findViewById(R.id.user_camera);
        Button myusercancel = (Button) view.findViewById(R.id.user_cancel);
        myusergallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File galleryImage = new File(Environment.getExternalStorageDirectory(), "HeadImage.jpg");
                try {
                    if (galleryImage.exists()) {
                        galleryImage.delete();
                    }
                    galleryImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageuri = Uri.fromFile(galleryImage);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                intent.putExtra("scale", true);
                intent.putExtra("crop", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(intent, crop_photo);
                dialog.cancel();

            }
        });
        myusercamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File cameraImage = new File(Environment.getExternalStorageDirectory(), "HeadImage.jpg");
                try {
                    if (cameraImage.exists()) {
                        cameraImage.delete();
                    }
                    cameraImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageuri = Uri.fromFile(cameraImage);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(intent, take_photo);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case take_photo:
                if (resultCode == Activity.RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageuri, "image/*");
                    intent.putExtra("crop", true);
                    intent.putExtra("scale", true);
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 90);
                    intent.putExtra("outputY", 90);
                    intent.putExtra("return-data", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                    startActivityForResult(intent, crop_photo);
                }
                break;
            case crop_photo:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        circleImageView = (CircleImageView) getView().findViewById(R.id.head_image_view);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 1;
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageuri), null, options);
                        circleImageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                break;
            default:
                break;
        }
    }
}
