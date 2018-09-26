package bike.douglas.com.bikejanu.Fragments;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.R;





public class Consultar_Indice extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__indice);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);



        recebeQtd();



       // Toast.makeText(Consultar_Indice.this, "Quantidade   " +recebeQtd(), Toast.LENGTH_LONG).show();




    }


    public int recebeQtd() {

        Tab1Codigo novaClasse = new Tab1Codigo();

        Toast.makeText(Consultar_Indice.this, "QTD   " +novaClasse.quantidadeBikes, Toast.LENGTH_LONG).show();

       return novaClasse.quantidadeBikes;
    }




}
