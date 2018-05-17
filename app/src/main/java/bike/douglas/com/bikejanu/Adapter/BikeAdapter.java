package bike.douglas.com.bikejanu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        if(bike!=null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.lista_bikes,parent ,false);

            TextView txtViewNumeroSerie = (TextView) view.findViewById(R.id.txtViewNumeroSerie);
            TextView txtViewMarca = (TextView) view.findViewById(R.id.txtViewMarca);
            TextView txtViewModelo = (TextView) view.findViewById(R.id.txtViewModelo);
            TextView txtViewCor = (TextView) view.findViewById(R.id.txtViewCor);


            Bike bike1 = bike.get(position);

            txtViewNumeroSerie.setText(bike1.getNumero_serie());
            txtViewMarca.setText(bike1.getMarca());
            txtViewModelo.setText(bike1.getModelo());
            txtViewCor.setText(bike1.getCor().toString());

        }
        return view;
    }

}
