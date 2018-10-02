package bike.douglas.com.bikejanu.Fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;





public class Consultar_Indice extends AppCompatActivity {



    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private ListView listViewDados;


    private List<Bike> listabikes = new ArrayList<Bike>();
    private ArrayAdapter <Bike> arrayAdapterBike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__indice);

        inicializarFirebase();
        listaBikes();







        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);













    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(Consultar_Indice.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    //lista todas as bikes dos usuarios

    public void listaBikes(){



        //Instânciar objetos
        listabikes = new ArrayList<>();


        ////////////////////////////////////////////


        arrayAdapterBike = new BikeAdapter(Consultar_Indice.this, (ArrayList<Bike>) listabikes);
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
                Toast.makeText(Consultar_Indice.this, "quantidade de bikes!  :"+arrayAdapterBike.getCount(), Toast.LENGTH_LONG).show();



               }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
