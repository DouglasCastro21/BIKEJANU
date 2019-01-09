package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class GraficoRuaLinhaFragment extends Fragment {

    int cont=0;
    int jatoba=0;
    int boaVista=0;

    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;


    private static String  bairro;

    private LineChart lineChart;

    private String[] nomes   = new String[]{"Boa Vista","Jussara","Alvorada","Ceramica","São João","Brasilina","Centro"};
    private int[]    valores = new int   []{1,2,4,8,12,12,34};
    private int []   cores   = new int   []{Color.BLUE,Color.YELLOW,Color.DKGRAY,Color.RED,Color.BLACK,Color.GREEN,Color.RED};




    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;


    //   public static List<String> listBairros = new ArrayList<String>();
    // public static ArrayAdapter<String> arrayAdapterBairro;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_rua_linha, container, false);

        lineChart =  (LineChart) rootView.findViewById(R.id.graficoRuaLinha);

        inicializarFirebase();





        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();

        //   listBairros = new ArrayList<>();


        query = databaseReference.child("TodasBikes").orderByChild("numero_serie");


        //  query = databaseReference.child("TodasBikes")
        //     .orderByChild("numero_serie").startAt(palavra).endAt(palavra+"\uf8ff");



        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                listBikes.clear();  //limpa lista


                // verifica itens da lista

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);



                    String texto = b.getAlertaDate();
                    //  bairro = b.getAlertaBairro();





                    if (!b.getAlertaBairro().equals("") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        //  Toast.makeText(GraficoBairroBarraFragment.super.getContext(), " Bairro :"+bairro, Toast.LENGTH_LONG).show();

                        bairro = b.getAlertaBairro();


                        if(bairro.equals("Jatoba") ){

                            jatoba++;


                        }else if(bairro.equals("Boa Vista") ){

                            boaVista++;


                        }



                        cont++;


                    }





                    if (texto.contains("2018") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        contandoBikesAno2018++;

                    }


                    if (texto.contains("2019") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        contandoBikesAno2019++;


                    }

                }


                // inicio do grafico

                final Calendar calendar = Calendar.getInstance();
                ano = calendar.get(Calendar.YEAR);





                if(ano == 2018){


                    creatCharts();

                }




                if(ano == 2019){


                    creatCharts();


                }



/////fim do grafico



// simula a lista

                arrayAdapterBike = new BikeAdapter(GraficoRuaLinhaFragment.super.getContext(), (ArrayList<Bike>) listBikes);
                //   listPesquisa.setAdapter(arrayAdapterBike);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }

        });




        return rootView;

    }


    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoRuaLinhaFragment.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }

    private Chart getSameChart(Chart chart, String descricao, int textColor, int background, int animacaoY){

        chart.getDescription().setText(descricao);
        chart.getDescription().setTextSize(24);
        chart.setBackgroundColor(background);
        chart.animateY(animacaoY);


        legend(chart);

        return  chart;
    }

    public void legend(Chart chart){

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);


        ArrayList<LegendEntry> entries = new ArrayList<>();

        for(int i=0;i<nomes.length;i++){

            LegendEntry entry = new LegendEntry();
            entry.formColor = cores[i];
            entry.label = nomes[i];
            entries.add(entry);

        }


        legend.setCustom(entries);

    }



    private ArrayList<Entry> getLineEntries(){


        ArrayList<Entry> entries = new ArrayList<>();


        for(int i=0;i <valores.length;i++)
            entries.add(new Entry(i,valores[i]));



        return entries;



    }




    private void axisX(XAxis axis){

        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(nomes));


    }

    private void axisLeft(YAxis axis){
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);

    }


    private void axisRight(YAxis axis){
        axis.setEnabled(true);

    }


    public void creatCharts(){

        lineChart = (LineChart) getSameChart(lineChart,"",Color.RED,Color.WHITE,3000);
        lineChart.setDrawGridBackground(true);

        lineChart.setActivated(true);



        lineChart.setData(getLineDate());
        lineChart.invalidate();

        axisX(lineChart.getXAxis());
        axisLeft(lineChart.getAxisLeft());
        axisRight(lineChart.getAxisRight());

        lineChart.getLegend().setEnabled(true);



    }


    private DataSet getDate(DataSet dataSet){

        dataSet.setColors(cores);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(15);

        return dataSet;
    }



    private LineData getLineDate(){
        LineDataSet lineDataSet = (LineDataSet) getDate(new LineDataSet(getLineEntries(),""));


        lineDataSet.setCircleColor(Color.RED);

        LineData lineData= new LineData(lineDataSet);
        lineData.setValueTextColor(Color.BLUE);



        return lineData;
    }



}
