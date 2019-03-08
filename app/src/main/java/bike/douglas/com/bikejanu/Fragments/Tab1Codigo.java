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
import android.widget.TextView;

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
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;



public class Tab1Codigo extends Fragment {


    private EditText editPalavra;

    private TextView naoNumeroSerie;

    private  ListView listPesquisa;



    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;



    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1codigo, container, false);


         editPalavra = (EditText)  rootView.findViewById(R.id.pesquisaCodigoID);
         listPesquisa = (ListView) rootView.findViewById(R.id.listabikeCodigoID);
         naoNumeroSerie = (TextView) rootView.findViewById(R.id.naoNumeroSerieID);


         inicializarFirebase();
         eventEdit();




        return rootView;
    }





    private void inicializarFirebase() {

        FirebaseApp.initializeApp(Tab1Codigo.super.getContext());
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
          Bike bike;


          //Instânciar objetos
          listBikes = new ArrayList<>();

          bike = new Bike();


        if (palavra.equals("")) {

            query = databaseReference.child("TodasBikes").orderByChild("numero_serie");


        }else{



            query = databaseReference.child("TodasBikes")
                    .orderByChild("numero_serie").startAt(palavra).endAt(palavra+"\uf8ff");






        }








        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                listBikes.clear();  // verificar depois o caso da area trabaljo



                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                     Bike b = objSnapshot.getValue(Bike.class);
                     listBikes.add(b);



            }



            if(listBikes.isEmpty()){

                naoNumeroSerie.setVisibility(View.VISIBLE);


            }else {

                naoNumeroSerie.setVisibility(View.GONE);

            }
                // verificar se precisa tirar ...nao sei pra uqe isso
               // arrayAdapterBike = new ArrayAdapter(
                   //     Tab1Codigo.super.getContext(),android.R.layout.simple_list_item_1, listBikes  );



                arrayAdapterBike = new BikeAdapter(Tab1Codigo.super.getContext(), (ArrayList<Bike>) listBikes);
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

