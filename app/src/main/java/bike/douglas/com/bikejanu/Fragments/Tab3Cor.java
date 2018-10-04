package bike.douglas.com.bikejanu.Fragments;


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

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.R;


public class Tab3Cor extends Fragment {


    private EditText editPalavra;
    private ListView listPesquisa;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    private List<Bike> listBikes = new ArrayList<Bike>();
    private ArrayAdapter<Bike> arrayAdapterBike;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3cor, container, false);


        editPalavra = (EditText)  rootView.findViewById(R.id.pesquisaCorID);
        listPesquisa = (ListView) rootView.findViewById(R.id.listabikeCorID);

        inicializarFirebase();
        eventEdit();



        return rootView;
    }


    private void inicializarFirebase() {

        FirebaseApp.initializeApp(Tab3Cor.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    private void eventEdit() {

        editPalavra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String palavra = editPalavra.getText().toString().trim();
                pesquisarPalavra(palavra);

            }
        });


    }



    private void pesquisarPalavra(String palavra) {

        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();

        if (palavra.equals("")) {

            query = databaseReference.child("TodasBikes").orderByChild("cor");
        }else{

            query = databaseReference.child("TodasBikes")
                    .orderByChild("cor").startAt(palavra).endAt(palavra+"\uf8ff");
        }


        listBikes.clear();


        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {


                listBikes.clear();

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);


                }


               // arrayAdapterBike = new ArrayAdapter(
                  //      Tab3Cor.super.getContext(),android.R.layout.simple_list_item_1,
                     //   listBikes  );

                arrayAdapterBike = new BikeAdapter(Tab3Cor.super.getContext(), (ArrayList<Bike>) listBikes);
                listPesquisa.setAdapter(arrayAdapterBike);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        pesquisarPalavra("");
    }
}