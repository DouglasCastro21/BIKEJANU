package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.EditarBike;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class GraficoHorarioAnoFragment extends Fragment {



    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;


    String ano2019 ="2019";

    private Bike b;
    private String procuraAno;

    private String h;
    private  int  horaDaOcorrencia=0;







    private int   madrugadaMin =  0000;
    private  int   madrugadaMax =  559;

    private int    manhaMin =  600;
    private  int   manhaMax =  1159;


    private  int   tardeMin =  1200;
    private  int   tardeMax =  1759;

    private  int   noiteMin =  1800 ;
    private  int   noiteMax =  2359;






    private  int madrugadaRouboTodos=0;
    private  int manhaRouboTodos=0;
    private  int tardeRouboTodos=0;
    private  int noiteRouboTodos=0;

    private  int madrugadaFurtoTodos=0;
    private  int manhaFurtoTodos=0;
    private  int tardeFurtoTodos=0;
    private  int noiteFurtoTodos=0;



    private  int madrugadaRoubo2017=1;
    private  int manhaRoubo2017=0;
    private  int tardeRoubo2017=2;
    private  int noiteRoubo2017=6;

    private  int madrugadaFurto2017=14;
    private  int manhaFurto2017=30;
    private  int tardeFurto2017=68;
    private  int noiteFurto2017=29;


    private  int madrugadaRoubo2018=0;
    private  int manhaRoubo2018=3;
    private  int tardeRoubo2018=0;
    private  int noiteRoubo2018=5;

    private  int madrugadaFurto2018=11;
    private  int manhaFurto2018=38;
    private  int tardeFurto2018=44;
    private  int noiteFurto2018=30;


    private  int madrugadaRoubo2019=0;
    private  int manhaRoubo2019=0;
    private  int tardeRoubo2019=0;
    private  int noiteRoubo2019=0;

    private  int madrugadaFurto2019=0;
    private  int manhaFurto2019=0;
    private  int tardeFurto2019=0;
    private  int noiteFurto2019=0;











    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;




    private BarChart barChart;

    String[] nomes   = new String[]{};
    int[]    roubos =  new int   []{};
    int []   cores   = new int   []{};
    String[] legenda  = new String[]{"Furto","Roubo"};
    int[]    furtos = new int   []{};





    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_horario_ano, container, false);




        inicializarFirebase();
        barChart =  (BarChart) rootView.findViewById(R.id.graficoBairroBarra);


      //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoHorarioAnoFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);



        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0) {

                    positionTodosAnos();

                }
                if(position == 1) {

                    positionAno2018();
                }
                if (position==2){

                    positionAno2019();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        barChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.conteinerFragmentos,new GraficoHorarioAnoGeralFragment()).commit();



                return false;


            }
        });







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

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);


                    procuraAno    =    b.getAlertaDate();



                    //---------------------------------------------------------------------------------------

                    String hora = b.getAlertaHora();
                    String anoDeBusca = b.getAlertaDate();


                    h = hora.replace(":","");

                    if(!h.equals("")) {
                        horaDaOcorrencia = Integer.parseInt(h);
                    }



                    if (anoDeBusca.contains(ano2019)  && (b.getStatus().equals("Roubada"))){


                        if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                            madrugadaRoubo2019++;
                        }


                        if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                            manhaRoubo2019++;
                        }

                        if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                            tardeRoubo2019++;
                        }

                        if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                            noiteRoubo2019++;
                        }


                    }



                    if (anoDeBusca.contains(ano2019)  &&  b.getStatus().equals("Furtada")){


                        if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                            madrugadaFurto2019++;
                        }


                        if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                            manhaFurto2019++;
                        }

                        if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                            tardeFurto2019++;
                        }

                        if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                            noiteFurto2019++;
                        }


                    }


                }




                        madrugadaRouboTodos   =     madrugadaRoubo2017 + madrugadaRoubo2018 +   madrugadaRoubo2019;
                        manhaRouboTodos       =     manhaRoubo2017     + manhaRoubo2018     +   manhaRoubo2019;
                        tardeRouboTodos       =     tardeRoubo2017     +  tardeRoubo2018     +  tardeRoubo2019;
                        noiteRouboTodos       =     noiteRoubo2017     +  noiteRoubo2018     +  noiteRoubo2019;




                        madrugadaFurtoTodos   =     madrugadaFurto2017 + madrugadaFurto2018 +   madrugadaFurto2019;
                        manhaFurtoTodos       =     manhaFurto2017     + manhaFurto2018     +   manhaFurto2019;
                        tardeFurtoTodos       =     tardeFurto2017     +  tardeFurto2018     +  tardeFurto2019;
                        noiteFurtoTodos       =     noiteFurto2017     +  noiteFurto2018     +  noiteFurto2019;


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });








        return rootView;

    }


    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoHorarioAnoFragment.super.getContext());
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
        legend.setTextSize(15);



        ArrayList<LegendEntry> entries = new ArrayList<>();

        for(int i=0;i<legenda.length;i++){

            LegendEntry entry = new LegendEntry();
            entry.formColor = cores[i];
            entry.label = legenda[i];
            entries.add(entry);

        }


        legend.setCustom(entries);

    }


    private void axisX(XAxis axis){

        axis.setGranularityEnabled(true);

        axis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        axis.setValueFormatter(new IndexAxisValueFormatter(nomes));

    }


    private void criarGraficos(){


        barChart = (BarChart) getSameChart(barChart,"",Color.RED,Color.TRANSPARENT,3000);
        barChart.setDrawGridBackground(true);

        barChart.setActivated(true);


      //  barChart.zoom(3,0,3,0);


        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        for (int i=0;i<roubos.length;i++){
            yVals1.add(new BarEntry(i,roubos[i]));

        }


        ArrayList<BarEntry> yVals2 = new ArrayList<>();

        for(int i=0;i <furtos.length;i++){
            yVals2.add(new BarEntry(i,furtos[i]));

        }


        BarDataSet set1,set2;


        set1 = new BarDataSet(yVals1,"Furto");
        set1.setColor(Color.RED);
        set1.setValueTextSize(15);
        set1.setValueTextColor(Color.BLUE);



        set2= new BarDataSet(yVals2, "Roubo");

        set2.setColor(Color.YELLOW);
        set2.setValueTextSize(15);
        set2.setValueTextColor(Color.BLUE);

        BarData data = new BarData(set2,set1);

        barChart.setData(data);



        axisX(barChart.getXAxis());


        barChart.getLegend().setEnabled(true);

        data.setBarWidth(0.45f);


        barChart.invalidate();



    }


    public void positionTodosAnos(){

        String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
      int[]    rob =  new   int   []{madrugadaRouboTodos,manhaRouboTodos,tardeRouboTodos,noiteRouboTodos};
        int[]   cor =    new int   []{Color.YELLOW,Color.RED};
        final String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{madrugadaFurtoTodos,manhaFurtoTodos,tardeFurtoTodos,noiteFurtoTodos};



        nomes   = turnos;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;


        criarGraficos();


    }



    public void positionAno2017(){


        String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
        int[]    rob = new int   []{madrugadaRoubo2017,manhaRoubo2017,tardeRoubo2017,noiteRoubo2017};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        final String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{madrugadaFurto2017,manhaFurto2017,tardeFurto2017,noiteFurto2017};



        nomes   = turnos;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;



        criarGraficos();



    }




    public void positionAno2018(){


        String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
        int[]    rob = new int   []{madrugadaRoubo2018,manhaRoubo2018,tardeRoubo2018,noiteRoubo2018};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        final String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{madrugadaFurto2018,manhaFurto2018,tardeFurto2018,noiteFurto2018};



        nomes   = turnos;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;



        criarGraficos();



    }


    public void  positionAno2019(){

        String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
        int[]    rob = new int   []{madrugadaRoubo2019,manhaRoubo2019,tardeRoubo2019,noiteRoubo2019};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        final String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{madrugadaFurto2019,manhaFurto2019,tardeFurto2019,noiteFurto2019};




        nomes   = turnos;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;


        criarGraficos();


    }


}
