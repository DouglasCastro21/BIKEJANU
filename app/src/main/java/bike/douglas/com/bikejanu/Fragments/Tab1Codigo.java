package bike.douglas.com.bikejanu.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.R;



public class Tab1Codigo extends Fragment {


    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> bikes;
    private DatabaseReference firebase;

    public Tab1Codigo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// instancia
        bikes = new ArrayList<>();

        bikes.add("Monark");
        bikes.add("Chimano");


        View view = inflater.inflate(R.layout.tab1codigo, container, false);
        listView = (ListView) view.findViewById(R.id.listabikeCodigoID);

        adapter = new ArrayAdapter(

                getActivity(),
                android.R.layout.simple_list_item_1,
                bikes




                );

        listView.setAdapter(adapter);


        // recuperar bikes do Bd firebase

        firebase = Configuracao_Firebase.getFirebase()
                .child("Usuarios").child("Bikes");



        // listener para recuperar as bikes

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // limpar lista

              //  bikes.clear();

                //lista bikes

                for (DataSnapshot dados:dataSnapshot.getChildren() ){

                    Bike bike = dados.getValue(Bike.class);

                    bikes.add(bike.getMarca());


                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}

