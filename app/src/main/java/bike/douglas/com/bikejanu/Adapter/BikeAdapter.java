package bike.douglas.com.bikejanu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.R;

import java.util.ArrayList;

import bike.douglas.com.bikejanu.Entidades.Bike;

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


            Bike bike1 = bike.get(position);

            txtViewNumeroSerie.setText(bike1.getNumero_serie());
            txtViewMarca.setText(bike1.getMarca());
            txtViewDescricao.setText(bike1.getDescricao());



            imagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(BikeAdapter.super.getContext(), "VC clicou na imagem!", Toast.LENGTH_LONG).show();



                }
            });

            txtViewDescricao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(BikeAdapter.super.getContext(), "VC clicou no textoo!", Toast.LENGTH_LONG).show();



                }
            });





        }
        return view;
    }


}
