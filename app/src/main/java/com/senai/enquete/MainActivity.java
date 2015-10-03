package com.senai.enquete;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.senai.enquete.dao.EnqueteDAO;
import com.senai.enquete.dto.EnqueteDTO;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    int imageIds[]={R.drawable.totem_img1,R.drawable.totem_img2,R.drawable.totem_img3,R.drawable.totem_img4};
    List<Animation> anim = new ArrayList<Animation>() ;
    ViewFlipper Switch;
    ImageView images;
    float initialX;
    private  int position = 0;
    private Button btnVotar,btnChart;
    Bundle savedInstanceStateTemp;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        Switch = (ViewFlipper) findViewById(R.id.viewFlipper);
        Switch.setFlipInterval(3600);
        images = (ImageView) findViewById(R.id.imageView1);
        savedInstanceStateTemp = savedInstanceState;
        btnChart = (Button) findViewById(R.id.buttonChart);
        final Intent intentResultado = new Intent(this, ResultadoActivity.class);

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentResultado);
            }
        });
        btnVotar = (Button) findViewById(R.id.button);
        final Intent intent =  new Intent(this, EnqueteActivity.class);
        btnVotar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
                /*QuestionDialogFragment newFragment = new QuestionDialogFragment();
                newFragment.onCreate(savedInstanceStateTemp, getApplicationContext());
                newFragment.show(getFragmentManager(),"Question");*/
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                if (initialX < finalX)
                {
                    position++;
                    if(position>imageIds.length-1){position=0;}
                    images.setImageResource(imageIds[position]);
                    Switch.showNext();


                }
                else
                {
                    position= position-1;
                    if(position<0){position=imageIds.length-1;}
                    images.setImageResource(imageIds[position]);
                    Switch.showPrevious();



                }
                break;
        }
        return false;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    @Override
    public void onBackPressed() {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        View promptsView = li.inflate(R.layout.exit_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                if (userInput.getText().toString().equals("adsapp")){

                                    finish();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}



