package com.senai.enquete;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.senai.enquete.dao.EnqueteDAO;
import com.senai.enquete.dto.EnqueteDTO;

import java.util.ArrayList;
import java.util.List;


public class EnqueteActivity extends Activity {

    private Context myContext;
    private Intent intentResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myContext = this.getApplicationContext();
        intentResultado = new Intent(this, ResultadoActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enquete);
        Button botaoVoltar = (Button) findViewById(R.id.buttonVolt);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button botaoSim = (Button) findViewById(R.id.buttonSim);

        botaoSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EnqueteDAO(myContext).adicionar(new EnqueteDTO("S"));
                startActivity(intentResultado);
            }
        });

        Button botaoNao = (Button) findViewById(R.id.buttonNao);
        botaoNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EnqueteDAO(myContext).adicionar(new EnqueteDTO("N"));
                startActivity(intentResultado);
            }
        });

        //read bitmap dimensions and type
/*       BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout5);
        Bitmap bitmap = decodeSampledBitmapFromResource(getResources(),R.drawable.background, 100, 100);
        Drawable drawable = new BitmapDrawable(getResources(),bitmap);
        ll.setBackgroundDrawable(drawable);
        */

    }
}



