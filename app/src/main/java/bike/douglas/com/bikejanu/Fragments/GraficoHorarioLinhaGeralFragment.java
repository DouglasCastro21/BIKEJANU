package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.CombinedChart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;

public class GraficoHorarioLinhaGeralFragment extends Fragment {





    private CombinedChart combinedChart;



    private String[] nomes   = new String[]{"2016","2017","2018","2019"};
    private int[]    valores = new int   []{20,40,60,1};
    private int []   cores   = new int   []{Color.GREEN,Color.BLUE,Color.BLACK,Color.RED};



    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_horario_linha_geral, container, false);


        combinedChart = (CombinedChart) rootView.findViewById(R.id.graficoHorarioMisto);

        return rootView;

    }



}
