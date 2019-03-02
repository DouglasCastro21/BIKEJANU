package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class GraficoHorarioGeralPizzaFragment extends Fragment {


    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;


    private PieChart pieChart;




    private String[] nomes   = new String[]{"Madrug'","Manhã", "Tarde","Noite"};
    private   int[]    valores  = new int   []{120,125,112,314,};
    private int []   cores   = new int   []{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_grafico_horario_geral_pizza, container, false);


        pieChart =  (PieChart) rootView.findViewById(R.id.graficoHorarioGeralPizza);



        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoHorarioGeralPizzaFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){


                  String[] turnos   = new String[]{"Madrug'","Manhã", "Tarde","Noite"};
                  int[]    valor  = new int   []{101,125,123,342,};
                   int []   cor   = new int   []{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};




                    nomes   = turnos;
                    valores =  valor;
                    cores   =  cor;



                    criarGraficos();

                } else if(position == 1){


                    String[] turnos   = new String[]{"Madrug'","Manhã", "Tarde","Noite"};
                    int[]    valor  = new int   []{110,115,112,314,};
                    int []   cor   = new int   []{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};




                    nomes   = turnos;
                    valores =  valor;
                    cores   =  cor;

                    criarGraficos();



                }else if (position==2){

                    String[] turnos   = new String[]{"Madrug'","Manhã", "Tarde","Noite"};
                    int[]    valor  = new int   []{110,115,112,314,};
                    int []   cor   = new int   []{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};




                    nomes   = turnos;
                    valores =  valor;
                    cores   =  cor;


                    criarGraficos();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();


// nao funciona aq
        pieChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                transaction.replace(R.id.conteinerFragmentos,new GraficoHorarioBarraFragment()).commit();



                return false;

            }
        });


        return rootView;
    }









    private Chart getSameChart(Chart chart, String descricao, int textColor, int background, int animacaoY){

        chart.getDescription().setText(descricao);
        chart.getDescription().setTextSize(15);
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


    private void axisX(XAxis axis){

        axis.setGranularityEnabled(true);

        axis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        axis.setValueFormatter(new IndexAxisValueFormatter(nomes));




    }



    private ArrayList<BarEntry> getBarEntries(){


        ArrayList<BarEntry> entries = new ArrayList<>();


        for(int i=0;i <valores.length;i++)
            entries.add(new BarEntry(i,valores[i]));
        return entries;



    }


    private ArrayList<PieEntry> getPieEntries(){

        ArrayList<PieEntry> entries = new ArrayList<>();


        for(int i=0;i <valores.length;i++)
            entries.add(new PieEntry(valores[i]));
        return entries;


    }







    public void criarGraficos(){



        pieChart = (PieChart) getSameChart(pieChart,"",Color.RED,Color.TRANSPARENT,2000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setDrawHoleEnabled(false);


        pieChart.setData(getPieDate());
        pieChart.invalidate();




    }

    private DataSet getDate(DataSet dataSet){

        dataSet.setColors(cores);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(15);




        return dataSet;
    }



    private BarData getBarDate(){
        BarDataSet barDataSet = (BarDataSet)getDate(new BarDataSet(getBarEntries(),""));

        barDataSet.setBarShadowColor(Color.YELLOW);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);




        return barData;
    }




    private PieData getPieDate(){
        PieDataSet pieDataSet = (PieDataSet)getDate(new PieDataSet(getPieEntries(),""));

        pieDataSet.setSliceSpace(2);

        // passa para poercentagem
        //pieDataSet.setValueFormatter(new PercentFormatter());





        return new PieData(pieDataSet);
    }








}
