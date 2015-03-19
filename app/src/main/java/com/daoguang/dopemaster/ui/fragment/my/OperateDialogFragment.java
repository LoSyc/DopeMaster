package com.daoguang.dopemaster.ui.fragment.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.FontIconButton;

import java.util.UUID;

/**
 * Created by Administrator on 2015/3/14.
 */
public class OperateDialogFragment extends DialogFragment {
    private static final String EXTRA_OPERATE = "fragment.my.OperateDialogFragment";
    private static final String TAG = "OperateDialogFragment";
    public static final int DIALOG_MODIFY = 0;
    public static final int DIALOG_DELETE = 1;
    private android.support.v4.app.FragmentManager fm;
    private UUID mId;

    public static OperateDialogFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_OPERATE, id);

        OperateDialogFragment fragment = new OperateDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mId = (UUID) getArguments().getSerializable(EXTRA_OPERATE);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_addroperate, null);
        fm = getActivity().getSupportFragmentManager();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setTitle(R.string.operate_addr);
        final AlertDialog dialog = builder.create();

        FontIconButton mModifyButton = (FontIconButton) view.findViewById(R.id.modify_addr);
        mModifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(Activity.RESULT_OK, DIALOG_MODIFY);
                dialog.cancel();
            }
        });

        FontIconButton mDeleteButton = (FontIconButton) view.findViewById(R.id.delete_addr);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(Activity.RESULT_OK, DIALOG_DELETE);
                dialog.cancel();
            }
        });

        return dialog;
    }

    private void sendResult(int requestCode, int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_OPERATE, mId);

        getTargetFragment()
                .onActivityResult(requestCode, resultCode, i);
    }
}
