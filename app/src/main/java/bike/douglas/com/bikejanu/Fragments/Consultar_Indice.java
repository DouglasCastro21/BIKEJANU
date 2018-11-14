package bike.douglas.com.bikejanu.Fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import bike.douglas.com.bikejanu.R;





public class Consultar_Indice extends AppCompatActivity  {


private ImageView botaoGrafico;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__indice);


        botaoGrafico = (ImageView)findViewById(R.id.BotaoGraficoID);



        botaoGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


    }




}
