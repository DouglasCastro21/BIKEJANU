package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import bike.douglas.com.bikejanu.Activity.EditarBike;
import bike.douglas.com.bikejanu.R;


public class GraficoBairroBarraFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grafico_bairro_barra, container, false);


        Toast.makeText(GraficoBairroBarraFragment.super.getContext(), " Bairro Barra!", Toast.LENGTH_LONG).show();




        return  rootView;

    }


}
