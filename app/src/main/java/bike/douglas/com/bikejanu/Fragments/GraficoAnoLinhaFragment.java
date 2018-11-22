package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;

public class GraficoAnoLinhaFragment extends Fragment {

    private   FirebaseDatabase firebaseDatabase;
    private  DatabaseReference databaseReference;



    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grafico_ano_linha, container, false);








        GraphView graph = (GraphView) rootView.findViewById(R.id.graphAnoLinha);


        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {

                new  DataPoint(2015, 77),
                new  DataPoint(2016, 90),
                new  DataPoint(2017, 120),
                new  DataPoint(2018, 130),



        });

        graph.addSeries(series2);
        series2.setDrawDataPoints(true);
        series2.setAnimated(true);
        series2.setTitle("Roubo/furtos");
        series2.setColor(Color.GREEN);


        graph.setTitle("Roubo e furto de Bicicletas ");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);




        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"2015", "2016", "2017","2018"});
        // staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);



        return rootView;
    }




}
