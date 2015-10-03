package com.senai.enquete;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.senai.enquete.dao.EnqueteDAO;

import java.text.DecimalFormat;

public class ResultadoActivity extends Activity{

    private TextView textNao, textSim, textTotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Float total,sim,nao;
        nao = new EnqueteDAO(this.getApplicationContext()).getMax("N");
        sim = new EnqueteDAO(this.getApplicationContext()).getMax("S");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);
        total=(sim.isNaN()?0:sim)+(nao.isNaN()?0:nao);
        sim=((sim/total*100));
        nao=(nao/total*100);
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