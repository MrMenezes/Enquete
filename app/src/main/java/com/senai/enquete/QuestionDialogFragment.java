package com.senai.enquete;

import android.app.Activity;
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
public class QuestionDialogFragment extends DialogFragment {
    private Context myContext;
    private Bundle savedInstanceStatem;
    public  Context getMyContext(){
        return  myContext;
    }
    public Bundle getSavedInstanceStatem(){return this.savedInstanceStatem;}


    public void onCreate(Bundle savedInstanceStatem, Context context) {
        this.myContext = context;
        this.savedInstanceStatem =savedInstanceStatem;
        this.onCreate(savedInstanceStatem);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStatem) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Resultado")
                .setMessage("O Senai Faz a Pergunta não sei se muito grande mas importante e agora o que responder?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new EnqueteDAO(getMyContext()).adicionar(new EnqueteDTO("S"));
                        PieChartDialogFragment newFragment = new PieChartDialogFragment();
                        newFragment.onCreate(getSavedInstanceStatem(), getMyContext());
                        newFragment.show(getFragmentManager(), "Question");
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new EnqueteDAO(getMyContext()).adicionar(new EnqueteDTO("N"));
                        PieChartDialogFragment newFragment = new PieChartDialogFragment();
                        newFragment.onCreate(getSavedInstanceStatem(), getMyContext());
                        newFragment.show(getFragmentManager(), "Question");
                    }
                });

        return builder.create();
    }
}