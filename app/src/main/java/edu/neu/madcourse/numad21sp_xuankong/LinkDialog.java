package edu.neu.madcourse.numad21sp_xuankong;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.snackbar.Snackbar;

public class LinkDialog extends AppCompatDialogFragment {

    private String url = "";
    EditText link;
    private DialogListener dialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.addlink_layout_dialog, null);

        link = view.findViewById(R.id.linkDialog);
        View dialogLayout = view.findViewById(R.id.dialogLayout);
        builder.setView(view)
                .setTitle("Add Link")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar snackbar = Snackbar.make(dialogLayout,"True", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            url = link.getText().toString();
                            dialogListener.applyTexts(url);
                    }
                });
        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dialogListener = (DialogListener) context;
    }

    public interface DialogListener {
        void applyTexts(String link);
    }
}
