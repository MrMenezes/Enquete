package com.senai.enquete;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;


public class EnqueteActivity extends Activity {

    int imageIds[]={R.drawable.image1,R.drawable.image2,R.drawable.image3,};
    List<Animation> anim = new ArrayList<Animation>() ;
    ImageSwitcher Switch;
    ImageView images;
    float initialX;
    private  int position = 0;
    private Button btnVotar,btnChart;
    Bundle savedInstanceStateTemp;

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

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
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.enquete);
        //read bitmap dimensions and type
        /*BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout5);
        ll.setBackground(
                decodeSampledBitmapFromResource(getResources(), R.drawable.background, 100, 100));
        ;*/


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
}



