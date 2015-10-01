package com.senai.enquete;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.senai.enquete.dao.EnqueteDAO;
import com.senai.enquete.dto.EnqueteDTO;

/**
 * Created by Mr Menezes on 01/10/2015.
 */
public class PieChartDialogFragment extends DialogFragment {
    private Context myContext;

    public  Context getMyContext(){
        return  myContext;
    }


    public void onCreate(Bundle savedInstanceStatem, Context context) {
        this.myContext = context;
        this.onCreate(savedInstanceStatem);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStatem) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("O Senai Faz a Pergunta não sei se muito grande mas importante e agora o que responder?")
                .setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}