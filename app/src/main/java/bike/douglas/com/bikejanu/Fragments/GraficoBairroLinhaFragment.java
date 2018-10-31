package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import bike.douglas.com.bikejanu.R;

public class GraficoBairroLinhaFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grafico_bairro_linha, container, false);


        Toast.makeText(GraficoBairroLinhaFragment.super.getContext(), " Bairro Linha!", Toast.LENGTH_LONG).show();

        return  rootView;

    }


}
