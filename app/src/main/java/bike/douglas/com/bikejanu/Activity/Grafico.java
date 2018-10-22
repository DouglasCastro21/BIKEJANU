package bike.douglas.com.bikejanu.Activity;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.renderscript.Element;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Fragments.Consultar_Indice;
import bike.douglas.com.bikejanu.R;


public class Grafico extends AppCompatActivity {


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private ListView listViewDados;



    private List<Bike> listabikes = new ArrayList<Bike>();
    private ArrayAdapter<Bike> arrayAdapterBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        inicializarFirebase();
        listaBikes();




        GraphView graph = (GraphView) findViewById(R.id.graph);



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







    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(Grafico.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    //lista todas as bikes dos usuarios

    public void listaBikes(){



        //Instânciar objetos
        listabikes = new ArrayList<>();


        ////////////////////////////////////////////


        arrayAdapterBike = new BikeAdapter(Grafico.this, (ArrayList<Bike>) listabikes);
        //  listViewDados.setAdapter(arrayAdapterBike);

        // registerForContextMenu(listViewDados);





        // escolhe os nós que vão ser listados
        databaseReference = Configuracao_Firebase.getFirebase()
                .child("TodasBikes");







        //Listener para recuperar bikes
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar lista

                listabikes.clear();

                //Listar bikes
                for (DataSnapshot dados: dataSnapshot.getChildren() ){

                    Bike b = dados.getValue( Bike.class );
                    listabikes.add( b );


                }

                arrayAdapterBike.notifyDataSetChanged();
                Toast.makeText(Grafico.this, "quantidade de bikes!  :"+arrayAdapterBike.getCount(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
