package com.senai.enquete;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.senai.enquete.dao.EnqueteDAO;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ResultadoActivity extends Activity{

    private TextView textNao, textSim, textTotal;


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
        TextView texResult = (TextView) findViewById(R.id.textView1);
        texResult.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Sim: "+new EnqueteDAO(getApplicationContext()).getMax("S").toString()+
                                " Não: "+new EnqueteDAO(getApplicationContext()).getMax("N").toString(),Toast.LENGTH_LONG).show();
                return false;
            }
        });
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

}