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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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



public class GraficoBairroLinhaGeralFragment extends Fragment {





    String ano2019 ="2019";


    int qtdAlvoradaTodos=0;
    int qtdBoaEsperancaTodos=0;
    int qtdBomJardimTodos=0;
    int qtdCentroTodos=0;
    int qtdCeramicaTodos=0;
    int qtdJadeteTodos=0;
    int qtdJatobaTodos=0;
    int qtdMangueirasTodos=0;
    int qtdBandeirantesTodos=0;
    int qtdJussaraTodos=0;
    int qtdLevianopolisTodos=0;



    // Alimentar com dados do REDS

    int qtdAlvoradaFurtoRoubo2018=1;
    int qtdBoaEsperancaFurtoRoubo2018=1;
    int qtdBomJardimFurtoRoubo2018= 8;
    int qtdCentroFurtoRoubo2018= 64;
    int qtdCeramicaFurtoRoubo2018= 5;
    int qtdJadeteFurtoRoubo2018= 1;
    int qtdJatobaFurtoRoubo2018= 2;   // é frankilim
    int qtdMangueirasFurtoRoubo2018= 7;
    int qtdBandeirantesFurtosRoubo2018= 2;
    int qtdJussaraFurtoRoubo2018= 6;
    int qtdLevianopolisFurtoRoubo2018=3;







    int qtdAlvoradaFurtoRoubo2019 =0;
    int qtdBoaEsperancaFurtoRoubo2019 =0;
    int qtdBomJardimFurtoRoubo2019 =0;
    int qtdCentroFurtoRoubo2019 =0;
    int qtdCeramicaFurtoRoubo2019 =0;
    int qtdJadeteFurtoRoubo2019 =0;
    int qtdJatobaFurtoRoubo2019 =0;
    int qtdMangueirasFurtoRoubo2019 =0;
    int qtdBandeirantesFurtosRoubo2019 =0;
    int qtdJussaraFurtoRoubo2019 =0;
    int qtdLevianopolisFurtoRoubo2019 =0;



    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;



    protected ImageView spinnerImagem;
   private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;

    private static String  bairro;

    private LineChart lineChart;


    String[] nomes   = new String[]{""};
    int[]    roubos =  new int   []{};
    int []   cores   = new int   []{};
    String[] legenda  = new String[]{};




    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;


    //   public static List<String> listBairros = new ArrayList<String>();
    // public static ArrayAdapter<String> arrayAdapterBairro;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_bairro_linha_geral, container, false);

        lineChart =  (LineChart) rootView.findViewById(R.id.graficoBairroLinhaGeral);

        inicializarFirebase();




        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoBairroLinhaGeralFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);




        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){


                    positionTodosAnos();

                } else if(position == 1){



                    positionAno2018();



                }else if (position==2){


                   positionAno2019();


                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        lineChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.conteinerFragmentos,new GraficoBairroLinhaFragment()).commit();



                return false;
            }
        });


        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();

        //   listBairros = new ArrayList<>();


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



                    String texto = b.getAlertaDate();
                    //  bairro = b.getAlertaBairro();





                    /// todos os anos  furtadas e roubadas


                    if (b.getAlertaBairro().equals("Alvorada") && (b.getStatus().equals("Furtada") || b.getStatus().equals("Roubada"))){

                        qtdAlvoradaTodos++;

                    }

                    if (b.getAlertaBairro().equals("Boa Esperança") && (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){


                        qtdBoaEsperancaTodos++;

                    }


                    if (b.getAlertaBairro().equals("Bom Jardim")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdBomJardimTodos++;


                    }

                    if (b.getAlertaBairro().equals("Centro")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdCentroTodos++;

                    }

                    if (b.getAlertaBairro().equals("Ceramica")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdCeramicaTodos++;

                    }

                    if (b.getAlertaBairro().equals("Vila Jadete")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJadeteTodos++;

                    }


                    if (b.getAlertaBairro().equals("Franklim")&&  (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJatobaTodos++;

                    }

                    if (b.getAlertaBairro().equals("Quintas das Mangueiras")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdMangueirasTodos++;

                    }

                    if (b.getAlertaBairro().equals("Bandeirantes")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdBandeirantesTodos++;

                    }

                    if (b.getAlertaBairro().equals("Jussara")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJussaraTodos++;

                    }


                    if (b.getAlertaBairro().equals("Vila Levianopolis")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdLevianopolisTodos++;

                    }





                    /// ANo 2019


                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Alvorada") && (b.getStatus().equals("Furtada") || b.getStatus().equals("Roubada"))){

                        qtdAlvoradaFurtoRoubo2019++;

                    }

                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Boa Esperança") && (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){


                        qtdBoaEsperancaFurtoRoubo2019++;

                    }


                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Bom Jardim")&& ( b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdBomJardimFurtoRoubo2019++;


                    }

                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Centro")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdCentroFurtoRoubo2019++;

                    }

                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Ceramica")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdCeramicaFurtoRoubo2019++;

                    }

                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Vila Jadete")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJadeteFurtoRoubo2019++;

                    }

                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Franklim")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJatobaFurtoRoubo2019++;

                    }

                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Quintas das Mangueiras")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdMangueirasFurtoRoubo2019++;

                    }

                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Bandeirantes")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdBandeirantesFurtosRoubo2019++;

                    }

                    if (b.getAlertaDate().contains(ano2019) && b.getAlertaBairro().equals("Jussara")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJussaraFurtoRoubo2019++;

                    }



                    if (b.getAlertaDate().contains(ano2019) && (b.getAlertaBairro().equals("Vila Levianopolis")&& b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdLevianopolisFurtoRoubo2019++;

                    }






                }




                qtdAlvoradaTodos      =qtdAlvoradaFurtoRoubo2018     +  qtdAlvoradaFurtoRoubo2019;
                qtdBoaEsperancaTodos  =qtdBoaEsperancaFurtoRoubo2018 +  qtdBoaEsperancaFurtoRoubo2019;
                qtdBomJardimTodos     =qtdBomJardimFurtoRoubo2018    +  qtdBomJardimFurtoRoubo2019;
                qtdCentroTodos       =qtdCentroFurtoRoubo2018       +  qtdCentroFurtoRoubo2019;
                qtdCeramicaTodos      =qtdCeramicaFurtoRoubo2018     +  qtdCeramicaFurtoRoubo2019;
                qtdJadeteTodos        =qtdJadeteFurtoRoubo2018       +  qtdJadeteFurtoRoubo2019;
                qtdJatobaTodos        =qtdJatobaFurtoRoubo2018       +  qtdJatobaFurtoRoubo2019;
                qtdMangueirasTodos    =qtdMangueirasFurtoRoubo2018   +  qtdMangueirasFurtoRoubo2019;
                qtdBandeirantesTodos  =qtdBandeirantesFurtosRoubo2018 +  qtdBandeirantesFurtosRoubo2019;
                qtdJussaraTodos       =qtdJussaraFurtoRoubo2018      +  qtdJussaraFurtoRoubo2019;
                qtdLevianopolisTodos  =qtdLevianopolisFurtoRoubo2018 +  qtdLevianopolisFurtoRoubo2019;




// simula a lista

               // arrayAdapterBike = new BikeAdapter(GraficoBairroLinhaFragment.super.getContext(), (ArrayList<Bike>) listBikes);
                //   listPesquisa.setAdapter(arrayAdapterBike);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }

        });




        return rootView;

    }


    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoBairroLinhaGeralFragment.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }


    private Chart getSameChart(Chart chart, String descricao, int textColor, int background, int animacaoX){

        chart.getDescription().setText(descricao);
        chart.getDescription().setTextSize(24);
        chart.setBackgroundColor(background);
        chart.animateX(animacaoX);


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



        lineChart = (LineChart) getSameChart(lineChart,"",Color.RED,Color.TRANSPARENT,2000);
        lineChart.setDrawGridBackground(true);

        lineChart.setActivated(true);

        lineChart.zoom(2,0,2,0);


        ArrayList<Entry> yVals1 = new ArrayList<>();
        for (int i=0;i<roubos.length;i++){
            yVals1.add(new Entry(i,roubos[i]));

        }





        LineDataSet set1;


        set1 = new LineDataSet(yVals1,"Roubo");
        set1.setColor(Color.RED);
        set1.setCircleColor(Color.RED);
        set1.setDrawCircles(true);
        set1.setLineWidth(4f);
        set1.setValueTextSize(15);
        set1.setValueTextColor(Color.BLUE);




        LineData data = new LineData(set1);

        lineChart.setData(data);




        axisX(lineChart.getXAxis());

        lineChart.getLegend().setEnabled(true);



    }



    public void positionTodosAnos(){

        String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
        int[]    rob = new int   []{qtdAlvoradaTodos,qtdBoaEsperancaTodos,qtdBomJardimTodos,qtdCentroTodos,qtdCeramicaTodos,qtdJadeteTodos,qtdJatobaTodos,qtdMangueirasTodos,qtdBandeirantesTodos,qtdJussaraTodos,qtdLevianopolisTodos};
        int []   cor   = new int   []{Color.RED};
        final String[] leg  = new String[]{"Furto/Roubo"};



        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;



        criarGraficos();


    }


    public void positionAno2018(){


        String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
        int[]    rob = new int   []{qtdAlvoradaFurtoRoubo2018,qtdBoaEsperancaFurtoRoubo2018,qtdBomJardimFurtoRoubo2018,qtdCentroFurtoRoubo2018,qtdCeramicaFurtoRoubo2018,qtdJadeteFurtoRoubo2018,qtdJatobaFurtoRoubo2018,qtdMangueirasFurtoRoubo2018,qtdBandeirantesFurtosRoubo2018,qtdJussaraFurtoRoubo2018,qtdLevianopolisFurtoRoubo2018};
        int []   cor   = new int   []{Color.RED};
        final String[] leg  = new String[]{"Furto/Roubo"};



        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;




        criarGraficos();



    }


    public void  positionAno2019(){

        String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
        int[]    rob = new int   []{qtdAlvoradaFurtoRoubo2019, qtdBoaEsperancaFurtoRoubo2019, qtdBomJardimFurtoRoubo2019, qtdCentroFurtoRoubo2019, qtdCeramicaFurtoRoubo2019, qtdJadeteFurtoRoubo2019, qtdJatobaFurtoRoubo2019, qtdMangueirasFurtoRoubo2019, qtdBandeirantesFurtosRoubo2019, qtdJussaraFurtoRoubo2019, qtdLevianopolisFurtoRoubo2019};
        int []   cor   = new int   []{Color.RED};
        final     String[] leg  = new String[]{"Furto/Roubo"};



        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;


        criarGraficos();


    }


}
