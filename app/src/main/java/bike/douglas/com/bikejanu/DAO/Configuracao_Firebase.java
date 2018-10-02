package bike.douglas.com.bikejanu.DAO;


import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Fragments.Consultar_Indice;
import bike.douglas.com.bikejanu.Fragments.Tab1Codigo;

public class Configuracao_Firebase {

    private  static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;
    private  static FirebaseDatabase firebaseDatabase;





    public static DatabaseReference getFirebase(){


        if (referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();


        }

        return referenciaFirebase;
    }




    //persistencia

    public static  FirebaseDatabase getFireb(){

        if(firebaseDatabase ==null){
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);




        }

        return firebaseDatabase;


    }



    public static FirebaseAuth getFirebaseAutenticacao(){

       if (autenticacao ==null){

           autenticacao = FirebaseAuth.getInstance();
       }
        return autenticacao;
    }




}
