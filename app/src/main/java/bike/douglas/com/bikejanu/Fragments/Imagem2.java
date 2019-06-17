package bike.douglas.com.bikejanu.Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public  class Imagem2 extends Fragment {
    private String test2;

    private  ImageView imagemBike;
    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imagem2, container, false);



        imagemBike = (ImageView) view.findViewById(R.id.imagem2GaleriaID) ;



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();





        DatabaseReference reference = databaseReference.child("TodasBikes").child("000");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot !=null) {

                    Bike dados = dataSnapshot.getValue(Bike.class);



                    Glide.with(Imagem2.this).load(dados.getFotoBikeUrl2()).into(imagemBike);


                    if (dados.getFotoBikeUrl2().equals("")){

                        Glide.with(Imagem2.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagemBike);


                    }




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        return view;
    }

}
