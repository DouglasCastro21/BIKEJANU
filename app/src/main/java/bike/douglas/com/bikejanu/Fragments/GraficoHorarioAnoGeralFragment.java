package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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

public class GraficoHorarioAnoGeralFragment extends Fragment {


    String ano2019 ="2019";

   private Bike b;
   private String procuraAno;


    private String h;
    private  int  horaDaOcorrencia=0;


    private int   madrugadaMin =  0000;
    private  int   madrugadaMax =  559;

    private int   manhaMin =  600;
    private  int   manhaMax =  1159;


    private  int   tardeMin =  1200;
    private  int   tardeMax =  1759;

    private  int   noiteMin =  1800 ;
    private  int   noiteMax =  2359;



    private  int madrugadaTodos=0;
    private  int manhaTodos=0;
    private  int tardeTodos=0;
    private  int noiteTodos=0;


    private  int madrugada2017=15;
    private  int manha2017=30;
    private  int tarde2017=70;
    private  int noite2017=35;

    private  int madrugada2018=11;
    private  int manha2018=41;
    private  int tarde2018=44;
    private  int noite2018=35;

    private  int madrugada2019=4;
    private  int manha2019=21;
    private  int tarde2019=19;
    private  int noite2019=12;





    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;


    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;




    private BarChart barChart;



    String[] nomes   = new String[]{};
    int[]    roubos =  new int   []{};
    int []   cores   = new int   []{};
    String[] legenda  = new String[]{};



    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_horario_ano_geral, container, false);




        inicializarFirebase();
        barChart =  (BarChart) rootView.findViewById(R.id.graficoBairroBarraGeral);



        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoHorarioAnoGeralFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){


                    positionTodosAnos();  /// alimentar com dados de 2018


                } else if(position == 1){



                 positionAno2018(); /// Acresentar os dados de 2018 ..alimentr



                }else if (position==2){

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

                final FragmentTransaction transaction = getFragmentManager().beginTransaction();

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




                    if (anoDeBusca.contains(ano2019)  && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


                        if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                            madrugada2019++;
                        }


                        if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                            manha2019++;
                        }

                        if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                            tarde2019++;
                        }

                        if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                            noite2019++;
                        }


                    }


                }



                madrugadaTodos   = madrugada2017 + madrugada2018   + madrugada2019;
                manhaTodos       = manha2017     + manha2018       +manha2019;
                tardeTodos       = tarde2017    + tarde2018       +tarde2019;
                noiteTodos       = noite2017     + noite2018       +noite2019;

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });


        return rootView;


    }



    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoHorarioAnoGeralFragment.super.getContext());
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


      //  barChart.zoom(3,0,2,0);



        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        for (int i=0;i<roubos.length;i++){
            yVals1.add(new BarEntry(i,roubos[i]));

        }







        BarDataSet set1;


        set1 = new BarDataSet(yVals1,"Roubo");
        set1.setColor(Color.RED);
        set1.setValueTextSize(15);
        set1.setValueTextColor(Color.BLUE);






        BarData data = new BarData(set1);

        barChart.setData(data);



        axisX(barChart.getXAxis());

        // axisRight(barChart.getAxisRight());


        barChart.getLegend().setEnabled(true);

        data.setBarWidth(0.45f);



        barChart.invalidate();


    }


    public void positionTodosAnos(){

        String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
        int[]    rob = new int   []{madrugadaTodos,manhaTodos,tardeTodos,noiteTodos};
        int []   cor   = new int   []{Color.RED};
        final String[] leg  = new String[]{"Furto/Roubo"};



        nomes   = turnos;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;



        criarGraficos();


    }



    public void positionAno2017(){


        String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
        int[]    rob = new int   []{madrugada2017,manha2017,tarde2017,noite2017};
        int []   cor   = new int   []{Color.RED};
        final String[] leg  = new String[]{"Furto/Roubo"};



        nomes   = turnos;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;




        criarGraficos();



    }




    public void positionAno2018(){


        String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
        int[]    rob = new int   []{madrugada2018,manha2018,tarde2018,noite2018};
        int []   cor   = new int   []{Color.RED};
        final String[] leg  = new String[]{"Furto/Roubo"};



        nomes   = turnos;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;




        criarGraficos();



    }





    public void  positionAno2019(){



        String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
        int[]    rob = new int   []{madrugada2019,manha2019,tarde2019,noite2019 };
        int []   cor   = new int   []{Color.RED};
        final     String[] leg  = new String[]{"Furto/Roubo"};



        nomes   = turnos;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;


        criarGraficos();


    }


}
