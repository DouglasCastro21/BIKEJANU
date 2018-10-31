package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import bike.douglas.com.bikejanu.Activity.Estatisticas;
import bike.douglas.com.bikejanu.R;


public class GraficoAnoBarraFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grafico_ano_barra, container, false);


        GraphView graph = (GraphView) rootView.findViewById(R.id.graphAnoBarra);


        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {



                new  DataPoint(2015, 77),
                new  DataPoint(2016, 90),
                new  DataPoint(2017, 120),
                new  DataPoint(2018, 130),


        });

       graph.addSeries(series);

        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        series.setSpacing(10);
        series.setAnimated(true);
        series.setTitle("Roubo/furtos");


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

