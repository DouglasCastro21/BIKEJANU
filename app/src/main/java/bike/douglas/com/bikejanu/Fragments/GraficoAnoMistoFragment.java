package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;



public class GraficoAnoMistoFragment extends Fragment {


    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;



    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_ano_misto, container, false);






        inicializarFirebase();




        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();




        query = databaseReference.child("TodasBikes").orderByChild("numero_serie");




        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                listBikes.clear();  //limpa lista


                // verifica itens da lista

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);







                    String texto = b.getAlertaDate();
                    //	String procurarPor = "2018";

                    if (texto.contains("2018") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        contandoBikesAno2018++;
                        //   Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "" + texto.toLowerCase().contains(procurarPor.toLowerCase()), Toast.LENGTH_LONG).show();


                    }


                    if (texto.contains("2019") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        contandoBikesAno2019++;


                    }




                }


                // inicio do grafico

                final Calendar calendar = Calendar.getInstance();
                ano = calendar.get(Calendar.YEAR);


                GraphView graph = (GraphView) rootView.findViewById(R.id.graphAnoMisto);

                if(ano == 2018){

                    BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {




                            new  DataPoint(2015, 77),
                            new  DataPoint(2016, 90),
                            new  DataPoint(2017, 120),
                            new  DataPoint(2018, contandoBikesAno2018),




                    });



                    graph.addSeries(series);
                    series.setDrawValuesOnTop(true);
                    series.setValuesOnTopColor(Color.RED);
                    series.setSpacing(10);
                    series.setAnimated(true);
                    series.setTitle("Roubo/furtos");



                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                    staticLabelsFormatter.setHorizontalLabels(new String[] {"2015", "2016", "2017","2018"});
                    // staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);



                    LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                            new  DataPoint(2015, 77),
                            new  DataPoint(2016, 90),
                            new  DataPoint(2017, 120),
                            new  DataPoint(2018, contandoBikesAno2018),
                    });
                    graph.addSeries(series2);
                    series2.setDrawDataPoints(true);
                    series2.setAnimated(true);
                    series2.setTitle("Roubo/furtos");
                    series2.setColor(Color.GREEN);


                }




                if(ano == 2019){

                    BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {




                            new  DataPoint(2016, 90),
                            new  DataPoint(2017, 120),
                            new  DataPoint(2018, contandoBikesAno2018),
                            new  DataPoint(2019, contandoBikesAno2019),


                    });



                    graph.addSeries(series);
                    series.setDrawValuesOnTop(true);
                    series.setValuesOnTopColor(Color.RED);
                    series.setSpacing(10);
                    series.setAnimated(true);
                    series.setTitle("Roubo/furtos");





                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                    staticLabelsFormatter.setHorizontalLabels(new String[] { "2016", "2017","2018","2019"});
                    // staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);



                    LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                            new  DataPoint(2015, 77),
                            new  DataPoint(2016, 90),
                            new  DataPoint(2017, 120),
                            new  DataPoint(2018, contandoBikesAno2019),
                    });

                    graph.addSeries(series2);
                    series2.setDrawDataPoints(true);
                    series2.setAnimated(true);
                    series2.setTitle("Roubo/furtos");
                    series2.setColor(Color.GREEN);
                }


                graph.setTitle("Roubo e furto de Bicicletas ");
                graph.getLegendRenderer().setVisible(true);
                graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);



                /////fim do grafico



                // simula a lista

               // arrayAdapterBike = new BikeAdapter(GraficoAnoMistoFragment.super.getContext(), (ArrayList<Bike>) listBikes);
                //   listPesquisa.setAdapter(arrayAdapterBike);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });




        return rootView;



    }



    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoAnoMistoFragment.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }


}
