package bike.douglas.com.bikejanu.Activity;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Collection;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

public class Galeria extends AppCompatActivity {


    private DatabaseReference  databaseReference;

    ArrayList<Bike> arrayFotosBikes = new ArrayList<>();



    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        final ViewPager pager =  (ViewPager) findViewById(R.id.pagerID);
        final CirclePageIndicator indicator =(CirclePageIndicator)findViewById(R.id.indicadorID);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        String email = user.getEmail();

        // converte o email pra base 64
        String identificadorUsuario= Base64Custom.codificarBase64(email);





        // escolhe  a bike que vai ser mostradA
        databaseReference = Configuracao_Firebase.getFirebase()
                .child("Bikes")
                .child(identificadorUsuario);
            //    .child("testes");



     databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


             for (DataSnapshot dados: dataSnapshot.getChildren() ){

                 Bike imagensBikes = dados.getValue( Bike.class );

                 arrayFotosBikes.add(imagensBikes);
              


             }

            pager.setAdapter(new AdaptadorFotos(getApplicationContext(),arrayFotosBikes));
            indicator.setViewPager(pager);

         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });

    }



}
