package bike.douglas.com.bikejanu.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class Imagem5 extends Fragment {

    private ImageView imagemBike;

    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.imagem5, container, false);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



        imagemBike = (ImageView) rootView.findViewById(R.id.imagem5GaleriaID) ;

        DatabaseReference reference = databaseReference.child("TodasBikes").child("000");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot !=null) {

                    Bike dados = dataSnapshot.getValue(Bike.class);



                    Glide.with(Imagem5.this).load(dados.getFotoBikeUrl5()).into(imagemBike);



                    if (dados.getFotoBikeUrl5().equals("")){

                        Glide.with(Imagem5.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagemBike);


                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return rootView;
    }



}