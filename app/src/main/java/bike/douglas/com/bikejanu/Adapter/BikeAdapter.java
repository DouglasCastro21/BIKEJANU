package bike.douglas.com.bikejanu.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.DadosBike;

import bike.douglas.com.bikejanu.Activity.Dialogo_Personalizado;
import bike.douglas.com.bikejanu.Activity.ImagemBike;
import bike.douglas.com.bikejanu.Activity.MainActivity;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Fragments.Entrar;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Entidades.Bike;

import static android.support.v4.content.ContextCompat.startActivity;

public class BikeAdapter extends ArrayAdapter<Bike>{


    private Context context;
    private List<Bike> listabikes = new ArrayList<Bike>();


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;



    public BikeAdapter( Context c, ArrayList<Bike> objects) {
        super(c, 0,objects);

        this.context = c;
        this.listabikes = objects;

    }



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        View view = null;


        // verifica se a lista está vazia
        if(listabikes!=null){

            // inicializa objetos para a montagem da view

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.lista_bikes,parent ,false);

            TextView txtViewNumeroSerie = (TextView) view.findViewById(R.id.txtViewNumeroSerie);
            final TextView txtViewMarca = (TextView) view.findViewById(R.id.txtViewMarca);
            TextView txtViewDescricao = (TextView) view.findViewById(R.id.txtViewDescricaoID);
            final ImageView imagem =(ImageView) view.findViewById(R.id.imagemListaID);


            final Bike bike1 = listabikes.get(position);



            firebaseDatabase = FirebaseDatabase.getInstance();
//          firebaseDatabase.setPersistenceEnabled(true);
            databaseReference = firebaseDatabase.getReference();

            txtViewNumeroSerie.setText(bike1.getNumero_serie());
            txtViewMarca.setText(bike1.getMarca());
            txtViewDescricao.setText(bike1.getDescricao());



            imagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(BikeAdapter.super.getContext(),DadosBike.class);
                    context.startActivity(intent);

                }
            });



            txtViewDescricao.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {


                   // recupera posição da bike

                   Bike bikeselecao = new Bike();
                   bikeselecao = listabikes.get(position);

                   // recupera usuario pelo email
                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                   String email = user.getEmail();


                   // converte o email pra base 64
                   String identificadorUsuario = Base64Custom.codificarBase64(email);



                   //EXCLUI A BIKE
                   databaseReference = Configuracao_Firebase.getFirebase().child("Bikes").child(identificadorUsuario);
                   databaseReference.child(bikeselecao.getNumero_serie()).removeValue();




                   //  Intent intent = new Intent(BikeAdapter.super.getContext(),DadosBike.class);
                   //    context.startActivity(intent);
               }
            });



        }
        return view;
    }

    private void caixaDialogo(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(BikeAdapter.super.getContext());

        // configurando dialogo

        alertaDialog.setTitle("Marca");
        alertaDialog.setMessage("Deseja realmente sair ? ");
        alertaDialog.setCancelable(false);



        // conf botões

        alertaDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // deslogarUsuario();


            }
        });

        alertaDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertaDialog.create();
        alertaDialog.show();
    }




}
