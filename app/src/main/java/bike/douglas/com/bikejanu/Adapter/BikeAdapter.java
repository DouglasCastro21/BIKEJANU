package bike.douglas.com.bikejanu.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.DadosBike;
import bike.douglas.com.bikejanu.Activity.ImagemBike;
import bike.douglas.com.bikejanu.Activity.MainActivity;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Fragments.Entrar;
import bike.douglas.com.bikejanu.R;

import java.util.ArrayList;

import bike.douglas.com.bikejanu.Entidades.Bike;

import static android.support.v4.content.ContextCompat.startActivity;

public class BikeAdapter extends ArrayAdapter<Bike> {

    private ArrayList<Bike> bike;
    private Context context;


    public BikeAdapter( Context c, ArrayList<Bike> objects) {
        super(c, 0,objects);

        this.context = c;
        this.bike = objects;


    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = null;


        // verifica se a lista está vazia
        if(bike!=null){

            // inicializa objetos para a montagem da view

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.lista_bikes,parent ,false);

            TextView txtViewNumeroSerie = (TextView) view.findViewById(R.id.txtViewNumeroSerie);
            TextView txtViewMarca = (TextView) view.findViewById(R.id.txtViewMarca);
            TextView txtViewDescricao = (TextView) view.findViewById(R.id.txtViewDescricaoID);
            ImageView imagem =(ImageView) view.findViewById(R.id.imagemListaID);


            final Bike bike1 = bike.get(position);

            txtViewNumeroSerie.setText(bike1.getNumero_serie());
            txtViewMarca.setText(bike1.getMarca());
            txtViewDescricao.setText(bike1.getDescricao());


            imagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder alertaDialog = new AlertDialog.Builder(BikeAdapter.super.getContext());

                    // configurando dialogo

                    alertaDialog.setTitle(bike1.getMarca());
                    alertaDialog.setMessage("Deseja realmente sair ? ");
                    alertaDialog.setCancelable(false);







                    // conf botões

                    alertaDialog.setPositiveButton(bike1.getMarca(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                            Intent intent = new Intent(BikeAdapter.super.getContext(),ImagemBike.class);
                            context.startActivity(intent);



                        }
                    });

                    alertaDialog.setNegativeButton(bike1.getCor(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                        }
                    });

                    alertaDialog.create();
                    alertaDialog.show();





                }
            });

            txtViewDescricao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(BikeAdapter.super.getContext(),DadosBike.class);
                    context.startActivity(intent);


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
