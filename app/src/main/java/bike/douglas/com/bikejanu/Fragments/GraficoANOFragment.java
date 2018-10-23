package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import bike.douglas.com.bikejanu.R;


public class GraficoANOFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grafico_ano, container, false);



        GraphView graph = (GraphView) rootView.findViewById(R.id.graphAno);



        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {


                new  DataPoint(2010,18),
                new  DataPoint(2011,9),
                new  DataPoint(2012,22),
                new  DataPoint(2013,61),
                new  DataPoint(2014,91),



                //     new DataPoint(2015, 77),
                //    new DataPoint(2016, 143),
                //     new DataPoint(2017, 150),



        });

        graph.addSeries(series);

        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        series.setSpacing(10);
        series.setAnimated(true);



        graph.setTitle("Roubo e furto de Bicicletas ");
        series.setTitle("Roubo/furtos");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);





        return rootView;



    }

}

