package bike.douglas.com.bikejanu.Fragments;


import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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


public  class Tab2Marca extends Fragment {


    private EditText editPalavra;
    private ListView listPesquisa;
    private TextView naoData;


    private FirebaseDatabase firebaseDatabase;
    private  DatabaseReference databaseReference;



    private  List<Bike> listBikes = new ArrayList<Bike>();
    private  ArrayAdapter<Bike> arrayAdapterBike;

    private int dia,mes,ano;
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> contatos;
    private DatabaseReference firebase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2marca, container, false);





        editPalavra = (EditText) view.findViewById(R.id.pesquisaDataID);
        listPesquisa = (ListView) view.findViewById(R.id.listabikeDataID);


        naoData = (TextView) view.findViewById(R.id.naoDataID);

        inicializarFirebase();
        eventEdit();


        return view;
    }


    private void inicializarFirebase() {

        FirebaseApp.initializeApp(Tab2Marca.super.getContext());
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

            query = databaseReference.child("TodasBikes").orderByChild("marca");

        }else{

            query = databaseReference.child("TodasBikes")
                    .orderByChild("marca").startAt(palavra).endAt(palavra+"\uf8ff");
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

                    naoData.setVisibility(View.VISIBLE);


                }else {

                    naoData.setVisibility(View.GONE);

                }




                // verificar se precisa tirar ...nao sei pra uqe isso
              //   arrayAdapterBike = new ArrayAdapter(
                //  Tab2Marca.super.getContext(),android.R.layout.simple_list_item_1, listBikes  );

                arrayAdapterBike = new BikeAdapter(Tab2Marca.super.getContext(), (ArrayList<Bike>) listBikes);
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
