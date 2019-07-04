package bike.douglas.com.bikejanu.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.DadosBike;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;


public class Imagem1 extends Fragment {




     String numero_serie_serie;



    ImageView imagemBike;

    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                             View rootView = inflater.inflate(R.layout.imagem1, container, false);




        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();




        imagemBike = (ImageView) rootView.findViewById(R.id.imagem1GaleriaID) ;


        Galeria_Bike galeria_bike_numero_serie = new Galeria_Bike();

        numero_serie_serie = galeria_bike_numero_serie.Numero_serie_serie;



        DatabaseReference reference = databaseReference.child("TodasBikes").child(numero_serie_serie);



        //    String numero_serie = bundle.getString("Numero_serie", "0");


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (getActivity() == null) {
                        return;
                    }
                   

                    if(dataSnapshot !=null) {

                        Bike dados = dataSnapshot.getValue(Bike.class);


                        if (dados.getFotoBikeUrl1().equals("")  ||dados.getFotoBikeUrl1() == null){

                            Glide.with(Imagem1.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/nao_cadastrada.jpeg?alt=media&token=79bf19e5-7251-4343-bc8a-b172c2529fbe").into(imagemBike);


                        }else{

                            Glide.with(Imagem1.this).load(dados.getFotoBikeUrl1()).into(imagemBike);

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

