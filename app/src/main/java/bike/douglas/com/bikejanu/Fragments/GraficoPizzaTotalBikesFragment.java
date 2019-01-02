package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    private PieChart pieChart;

    private String[] nomes   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
    private int[]    valores = new int   []{3000,150,100,80};
    private int []   cores   = new int   []{Color.BLUE,Color.RED,Color.DKGRAY,Color.GREEN};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_grafico_pizza_total_bikes, container, false);


        pieChart =  (PieChart) rootView.findViewById(R.id.graficoPizza);




        creatCharts();


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



        pieChart = (PieChart) getSameChart(pieChart,"",Color.RED,Color.WHITE,3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        //  pieChart.setDrawHoleEnabled(false);


        pieChart.setData(getPieDate());
        pieChart.invalidate();
    }

    private DataSet getDate(DataSet dataSet){

        dataSet.setColors(cores);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(10);

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
