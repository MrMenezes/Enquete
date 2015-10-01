package com.senai.enquete;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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

import com.senai.enquete.dao.EnqueteDAO;
import com.senai.enquete.dto.EnqueteDTO;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    int imageIds[]={R.drawable.image1,R.drawable.image2,};
    List<Animation> anim = new ArrayList<Animation>() ;
    ImageSwitcher Switch;
    ImageView images;
    float initialX;
    private  int position = 0;
    private Button btnVotar,btnChart;
    Bundle savedInstanceStateTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        images = (ImageView) findViewById(R.id.imageView1);

        anim.add(AnimationUtils.loadAnimation(this,R.anim.enter_from_left));
        anim.add(AnimationUtils.loadAnimation(this,R.anim.exit_to_right));
        anim.add(AnimationUtils.loadAnimation(this,R.anim.enter_from_right));
        anim.add(AnimationUtils.loadAnimation(this,R.anim.exit_to_left));
        savedInstanceStateTemp = savedInstanceState;
        btnChart = (Button) findViewById(R.id.buttonChart);
        btnChart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnChart.setText("Sim: "+new EnqueteDAO(getApplicationContext()).getMax("S")+
                        " Não:"+new EnqueteDAO(getApplicationContext()).getMax("N"));
            }
        });
        btnVotar = (Button) findViewById(R.id.button);
        btnVotar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                QuestionDialogFragment newFragment = new QuestionDialogFragment();
                newFragment.onCreate(savedInstanceStateTemp, getApplicationContext());
                newFragment.show(getFragmentManager(),"Question");
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
                    //images.setBackgroundResource(R.drawable.mb__messagebar_divider);
                    Switch.setInAnimation(anim.get(0));
                    Switch.setOutAnimation(anim.get(1));
                    Switch.showNext();


                }
                else
                {                    position= position-1;
                        if(position<0){position=imageIds.length-1;}
                        images.setImageResource(imageIds[position]);
                    Switch.setInAnimation(anim.get(2));
                    Switch.setOutAnimation(anim.get(3));
                    Switch.showPrevious();



                }
                break;
        }
        return false;
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
                                if (userInput.getText().toString().equals("!s3n@i!")){
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



