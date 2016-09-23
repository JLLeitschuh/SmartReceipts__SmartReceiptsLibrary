package co.smartreceipts.android.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import co.smartreceipts.android.R;

public class ExportBackupDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public static final String TAG = ExportBackupDialogFragment.class.getName();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.dialog_export_title);
        builder.setMessage(R.string.dialog_export_text);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.dialog_export_positive, this);
        builder.setNegativeButton(android.R.string.cancel, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            // TODO: Kick off export
        }
        dismiss();
    }
}
