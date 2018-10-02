package bike.douglas.com.bikejanu.Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Entidades.Usuarios;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.R;


public  class Tab2Data extends Fragment {


    private EditText editPalavra;

    private int dia,mes,ano;
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> contatos;
    private DatabaseReference firebase;

    public Tab2Data() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2data, container, false);



        editPalavra = (EditText) view.findViewById(R.id.pesquisaDataID);




        editPalavra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          final Calendar calendar = Calendar.getInstance();

          dia = calendar.get(Calendar.DAY_OF_MONTH);
          mes = calendar.get(Calendar.MONTH);
          ano = calendar.get(Calendar.YEAR);


                final DatePickerDialog datePickerDialog = new  DatePickerDialog(Tab2Data.super.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                     //   String current = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
                      editPalavra.setText(dayOfMonth +"/"+(month+1)+"/"+year);




                    }
                }

                   ,ano,mes,dia);
                    datePickerDialog.show();

            }
        });

        return view;
    }







}
