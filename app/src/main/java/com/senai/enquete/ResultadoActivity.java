package com.senai.enquete;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.senai.enquete.dao.EnqueteDAO;

import java.text.DecimalFormat;

public class ResultadoActivity extends Activity{

    private TextView textNao, textSim, textTotal;
    private float mDownX,mDownY;
    private boolean isOnLongPress;
    private static float SCROLL_THRESHOLD = 10;
    private static int LONG_PRESS_TIME = 3000;

    public ResultadoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Float total,sim,nao;
        nao = new EnqueteDAO(this.getApplicationContext()).getMax("N");
        sim = new EnqueteDAO(this.getApplicationContext()).getMax("S");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        total=sim+nao;
        sim=((sim/total*100));
        nao=(nao/total*100);
        sim=sim.isNaN()?0:sim;
        nao=nao.isNaN()?0:nao;
        textNao = (TextView) findViewById(R.id.textQuantNao);
        textNao.setText(new DecimalFormat("#").format(nao).toString()+"%");
        textSim = (TextView) findViewById(R.id.textQuantSim);
        textSim.setText(new DecimalFormat("#").format(sim).toString()+"%");
        Button botaoVoltar = (Button) findViewById(R.id.buttonVolt);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {



            switch (ev.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    mDownX = ev.getX();
                    mDownY = ev.getY();
                    isOnLongPress = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    isOnLongPress = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isOnLongPress && (Math.abs(mDownX - ev.getX()) > SCROLL_THRESHOLD || Math.abs(mDownY - ev.getY()) > SCROLL_THRESHOLD)) {
                        isOnLongPress = false;
                    }
                    break;
            }




        return super.dispatchTouchEvent(ev);
    }
    private Runnable mLongPressed = new Runnable() {
        public void run() {
           Toast.makeText(getApplicationContext(),(new EnqueteDAO(getApplicationContext()).getMax("S")+new EnqueteDAO(getApplicationContext()).getMax("N").toString()),Toast.LENGTH_LONG);
        }
    };
}