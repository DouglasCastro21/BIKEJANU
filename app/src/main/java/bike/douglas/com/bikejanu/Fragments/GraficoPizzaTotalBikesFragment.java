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


public class GraficoPizzaTotalBikesFragment extends Fragment {

    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;

    protected ImageView spinnerImagem;
     private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;


    private PieChart pieChart;

    private String[] nomes   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
    private int[]    valores = new int   []{300,150,100,50};
    private int []   cores   = new int   []{Color.BLUE,Color.RED,Color.DKGRAY,Color.GREEN};




    int contandoBikesRouboTodosAno=0;
    int contandoBikesFurtoTodosAno=0;

    int contandoBikesRouboAno2018=2;// alimentar
    int contandoBikesFurtoAno2019=0;

    int contandoBikesFurtoAno2018=129; // alimentar
    int contandoBikesRouboAno2019=0;



    int TodasBikesDoBD=0;
    int contandoBikesDoBD2018=0;// alimentar
    int contandoBikesDoBD2019=0;


    int bikesRecuperadasTodosAnos=0;
    int bikesRecuperadas2018=36;   // alimentar
    int bikesRecuperadas2019=0;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_grafico_pizza_total_bikes, container, false);


        inicializarFirebase();
        pieChart =  (PieChart) rootView.findViewById(R.id.graficoPizza);



        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoPizzaTotalBikesFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);



        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();



        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();




        query = databaseReference.child("TodasBikes");


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



                    String anoDeBusca = b.getAlertaDate();

                    if (b.getStatus().equals("Roubada")){

                       contandoBikesRouboTodosAno++;


                    }

                    if (b.getStatus().equals("Furtada")){


                        contandoBikesFurtoTodosAno++;


                    }




                    if (anoDeBusca.contains("2019")  && b.getStatus().equals("Roubada")){

                        contandoBikesRouboAno2019++;


                    }

                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada")){


                        contandoBikesFurtoAno2019++;


                    }



//CONTANDO TODAS AS BIKES DO SISTEMA

                    if (b.getStatus().equals("Roubada")||b.getStatus().equals("Furtada")||b.getStatus().equals("Sem Impedimento")||b.getStatus().equals("Recuperada")){

                        TodasBikesDoBD++;


                    }

                    if (anoDeBusca.contains("2019")&& (b.getStatus().equals("Roubada")||b.getStatus().equals("Furtada")||b.getStatus().equals("Sem impedimento")||b.getStatus().equals("Recuperada"))){

                        //sem restrição que um dia foi recuperada
                        contandoBikesDoBD2019++;


                    }





                    //CONTANDO TODAS AS BIKES Recuperadas do SISTEMA

                    if (b.getStatus().equals("Recuperada")){

                        bikesRecuperadasTodosAnos++;


                    }

                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Recuperada")){

                        //sem restrição que um dia foi recuperada
                        bikesRecuperadas2019++;


                    }




                }







                // arrayAdapterBike = new BikeAdapter(GraficoAnoBarraFragment.super.getContext(), (ArrayList<Bike>) listBikes);
                //   listPesquisa.setAdapter(arrayAdapterBike);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });




        contandoBikesFurtoTodosAno = contandoBikesFurtoAno2019    +  contandoBikesFurtoAno2018;
        contandoBikesRouboTodosAno = contandoBikesRouboAno2019    +  contandoBikesRouboAno2018;

        TodasBikesDoBD             =  contandoBikesDoBD2019       +  contandoBikesDoBD2018;
        bikesRecuperadasTodosAnos  =  bikesRecuperadas2019        +  bikesRecuperadas2018;




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){


                     String[] titulos   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
                     int[]    valor = new int   []{TodasBikesDoBD,contandoBikesRouboTodosAno,contandoBikesFurtoTodosAno,bikesRecuperadasTodosAnos};
                     int []   cor   = new int   []{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};



                    nomes   = titulos;
                    valores =  valor;
                    cores   =  cor;



                    criarGraficos();

                } else if(position == 1){


                    String[] titulos   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
                    int[]    valor = new int   []{contandoBikesDoBD2018,contandoBikesRouboAno2018,contandoBikesFurtoAno2018,bikesRecuperadas2018};
                    int []   cor   = new int   []{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};




                    nomes   = titulos;
                    valores =  valor;
                    cores   =  cor;


                    criarGraficos();



                }else if (position==2){

                    String[] titulos   = new String[]{"Bikes Cadastradas","Roubadas","Furtadas","Recuperadas"};
                    int[]    valor = new int   []{contandoBikesDoBD2019,contandoBikesRouboAno2019,contandoBikesFurtoAno2019,bikesRecuperadas2019};
                    int []   cor   = new int   []{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN};



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



    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoPizzaTotalBikesFragment.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


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
