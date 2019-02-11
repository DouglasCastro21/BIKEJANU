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

import java.util.ArrayList;

import bike.douglas.com.bikejanu.R;


public class GraficoPizzaTotalBikesFragment extends Fragment {



    protected ImageView spinnerImagem;
     private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;


    private PieChart pieChart;

    private String[] nomes   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
    private int[]    valores = new int   []{300,150,100,50};
    private int []   cores   = new int   []{Color.BLUE,Color.RED,Color.DKGRAY,Color.GREEN};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_grafico_pizza_total_bikes, container, false);


        pieChart =  (PieChart) rootView.findViewById(R.id.graficoPizza);



        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoPizzaTotalBikesFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);



        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){


                     String[] titulos   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
                     int[]    valor = new int   []{1000,700,300,500};
                     int []   cor   = new int   []{Color.BLUE,Color.RED,Color.DKGRAY,Color.GREEN};



                    nomes   = titulos;
                    valores =  valor;
                    cores   =  cor;



                    criarGraficos();

                } else if(position == 1){


                    String[] titulos   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
                    int[]    valor = new int   []{600,200,400,300};
                    int []   cor   = new int   []{Color.BLUE,Color.RED,Color.DKGRAY,Color.GREEN};




                    nomes   = titulos;
                    valores =  valor;
                    cores   =  cor;


                    criarGraficos();



                }else if (position==2){

                    String[] titulos   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
                    int[]    valor = new int   []{400,150,250,200};
                    int []   cor   = new int   []{Color.BLUE,Color.RED,Color.DKGRAY,Color.GREEN};




                    nomes   = titulos;
                    valores =  valor;
                    cores   =  cor;


                    criarGraficos();

                }else if(position==3){

                    String[] titulos   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
                    int[]    valor = new int   []{300,200,100,150};
                    int []   cor   = new int   []{Color.BLUE,Color.RED,Color.DKGRAY,Color.GREEN};




                    nomes   = titulos;
                    valores =  valor;
                    cores   =  cor;


                    criarGraficos();



                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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



        pieChart = (PieChart) getSameChart(pieChart,"",Color.RED,Color.WHITE,2000);
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

        barDataSet.setBarShadowColor(Color.GRAY);

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
