package bike.douglas.com.bikejanu.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class Imagem4 extends Fragment {

    private ImageView imagemBike;
    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    String numero_serie_serie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.imagem4, container, false);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        imagemBike = (ImageView) rootView.findViewById(R.id.imagem4GaleriaID) ;





        Galeria_Bike galeria_bike_numero_serie = new Galeria_Bike();

        numero_serie_serie = galeria_bike_numero_serie.Numero_serie_serie;


        DatabaseReference reference = databaseReference.child("TodasBikes").child(numero_serie_serie);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (getActivity() == null) {
                    return;
                }

                if(dataSnapshot !=null) {

                    Bike dados = dataSnapshot.getValue(Bike.class);


                    if (dados.getFotoBikeUrl4().equals("")  || dados.getFotoBikeUrl4() == null){

                        Glide.with(Imagem4.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/nao_cadastrada.jpeg?alt=media&token=79bf19e5-7251-4343-bc8a-b172c2529fbe").into(imagemBike);


                    }else{


                        Glide.with(Imagem4.this).load(dados.getFotoBikeUrl4()).into(imagemBike);


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