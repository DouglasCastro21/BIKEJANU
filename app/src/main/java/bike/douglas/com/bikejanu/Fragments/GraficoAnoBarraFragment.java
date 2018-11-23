package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.Estatisticas;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class GraficoAnoBarraFragment extends Fragment {


    private int cont=0;
    private ListView listPesquisa;



    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_ano_barra, container, false);


        listPesquisa = (ListView) rootView.findViewById(R.id.listaGoneID);

        inicializarFirebase();







        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();




            query = databaseReference.child("TodasBikes").orderByChild("numero_serie");


          //  query = databaseReference.child("TodasBikes")
               //     .orderByChild("numero_serie").startAt(palavra).endAt(palavra+"\uf8ff");



        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                listBikes.clear();  //limpa lista


                // verifica itens da lista

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);




                    if (b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        cont++;

                    }


                }


                // inicio do grafico



                GraphView graph = (GraphView) rootView.findViewById(R.id.graphAnoBarra);


                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {




                        new  DataPoint(2015, 77),
                        new  DataPoint(2016, 90),
                        new  DataPoint(2017, 120),
                        new  DataPoint(2018, cont),


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
/////fim do grafico



// simula a lista

                arrayAdapterBike = new BikeAdapter(GraficoAnoBarraFragment.super.getContext(), (ArrayList<Bike>) listBikes);
                listPesquisa.setAdapter(arrayAdapterBike);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });




        return rootView;



    }






    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoAnoBarraFragment.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }




    private void pesquisarPalavra(String palavra) {

    }



}

