package com.daoguang.dopemaster.ui.fragment.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.daoguang.dopemaster.R;

/**
 * Created by Administrator on 2015/3/14.
 */
public class BackDialogFragment extends DialogFragment {

    private static final String EXTRA_OPERATE = "fragment.my.BackDialogFragment";
    public static final int DIALOG_BACK_OK = 3;
    public static final int DIALOG_BACK_CANCEL = 4;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_backaddrlist, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setTitle(R.string.notify);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendResult(DIALOG_BACK_OK);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendResult(DIALOG_BACK_CANCEL);
            }
        });
        final AlertDialog dialog = builder.create();
        return dialog;
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_OPERATE, 0);

        getTargetFragment()
                .onActivityResult(Activity.RESULT_OK, resultCode, i);
    }
}
