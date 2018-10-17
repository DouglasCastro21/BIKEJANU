package bike.douglas.com.bikejanu.Fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.Grafico;
import bike.douglas.com.bikejanu.Activity.MapsRoubosActivity;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
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


caixaDialogoGraficos();

            }
        });


    }

    private void caixaDialogoGraficos(){

        final CharSequence[] opcoes = {"Mapa dos Crimes", "Gráfico de Barras", "Gráfico de Pizza"};



        AlertDialog.Builder builder = new AlertDialog.Builder(Consultar_Indice.this);
        builder.setTitle("");
        builder.setItems(opcoes, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int i) {

                if (opcoes[i].equals("Mapa dos Crimes")) {

                    startActivity(new Intent(Consultar_Indice.this, MapsRoubosActivity.class));


                }else if (opcoes[i].equals("Gráfico de Barras")){

                      startActivity(new Intent(Consultar_Indice.this, Grafico.class));


                }else if (opcoes[i].equals("Gráfico de Pizza")){

                    Toast.makeText(Consultar_Indice.this, " grafico de pizza", Toast.LENGTH_LONG).show();




                }

            }

                });

                builder.show();
            }


}
