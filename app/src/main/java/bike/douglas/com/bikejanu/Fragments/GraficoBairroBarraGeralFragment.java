package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
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

public class GraficoBairroBarraGeralFragment extends Fragment {


    String ano2019 ="2019";

    int qtdAlvoradaTodos=0;
    int qtdBoaEsperancaTodos=0;
    int qtdBomJardimTodos= 0;
    int qtdCentroTodos= 0;
    int qtdCeramicaTodos= 0;
    int qtdJadeteTodos= 0;
    int qtdJatobaTodos= 0;
    int qtdMangueirasTodos= 0;
    int qtdBandeirantesTodos= 0;
    int qtdJussaraTodos= 0;
    int qtdLevianopolisTodos=0;





/// alimentar com Dados do REDS


    int qtdAlvoradaFurtoRoubo2018 =0;
    int qtdBoaEsperancaFurtoRoubo2018 =0;
    int qtdBomJardimFurtoRoubo2018 =0;
    int qtdCentroFurtoRoubo2018 =0;
    int qtdCeramicaFurtoRoubo2018 =0;
    int qtdJadeteFurtoRoubo2018 =0;
    int qtdJatobaFurtoRoubo2018 =0;
    int qtdMangueirasFurtoRoubo2018 =0;
    int qtdBandeirantesFurtosRoubo2018 =0;
    int qtdJussaraFurtoRoubo2018 =0;
    int qtdLevianopolisFurtoRoubo2018 =0;






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
        final View rootView = inflater.inflate(R.layout.fragment_grafico_bairro_barra_geral, container, false);




        inicializarFirebase();
        barChart =  (BarChart) rootView.findViewById(R.id.graficoBairroBarraGeral);



        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoBairroBarraGeralFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
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

                final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.conteinerFragmentos,new GraficoBairroBarraFragment()).commit();



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

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);




                       String anoDeBusca = b.getAlertaDate();
                    //	String procurarPor = "2018";




                    /// todos os anos  furtadas e roubadas


                    if (b.getAlertaBairro().equals("Alvorada") && (b.getStatus().equals("Furtada") || b.getStatus().equals("Roubada"))){

                        qtdAlvoradaTodos++;

                    }

                    if (b.getAlertaBairro().equals("Boa Esperança") && (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){


                        qtdBoaEsperancaTodos++;

                    }


                    if (b.getAlertaBairro().equals("Vila Brasilandia")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

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


                    if (b.getAlertaBairro().equals("Franklim")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJatobaTodos++;

                    }

                    if (b.getAlertaBairro().equals("Quintas das Mangueiras")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdMangueirasTodos++;

                    }

                    if (b.getAlertaBairro().equals("Bandeirantes")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdBandeirantesTodos++;

                    }

                    if (b.getAlertaBairro().equals("Jussara") && (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJussaraTodos++;

                    }


                    if (b.getAlertaBairro().equals("Vila Levianopolis")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdLevianopolisTodos++;

                    }





                    /// ANo 2019


                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Alvorada") && (b.getStatus().equals("Furtada") || b.getStatus().equals("Roubada"))){

                        qtdAlvoradaFurtoRoubo2019++;

                    }

                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Boa Esperança") && (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){


                        qtdBoaEsperancaFurtoRoubo2019++;

                    }


                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Vila Brasilandia")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdBomJardimFurtoRoubo2019++;


                    }

                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Centro")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdCentroFurtoRoubo2019++;

                    }

                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Ceramica")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdCeramicaFurtoRoubo2019++;

                    }

                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Vila Jadete")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJadeteFurtoRoubo2019++;

                    }


                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Franklim")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJatobaFurtoRoubo2019++;

                    }

                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Quintas das Mangueiras")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdMangueirasFurtoRoubo2019++;

                    }

                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Bandeirantes")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdBandeirantesFurtosRoubo2019++;

                    }

                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Jussara")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdJussaraFurtoRoubo2019++;

                    }



                    if (anoDeBusca.contains(ano2019) && b.getAlertaBairro().equals("Vila Levianopolis")&& (b.getStatus().equals("Furtada")|| b.getStatus().equals("Roubada"))){

                        qtdLevianopolisFurtoRoubo2019++;

                    }


                }







/////fim do grafico



// simula a lista

                // arrayAdapterBike = new BikeAdapter(GraficoAnoBarraFragment.super.getContext(), (ArrayList<Bike>) listBikes);
                //   listPesquisa.setAdapter(arrayAdapterBike);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });


        return rootView;


    }



    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoBairroBarraGeralFragment.super.getContext());
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


        barChart = (BarChart) getSameChart(barChart,"",Color.RED,Color.WHITE,3000);
        barChart.setDrawGridBackground(true);

        barChart.setActivated(true);


        barChart.zoom(3,0,2,0);



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

        String[] bairros   = new String[]{"Alvorada","Boa Esperança","V. Brasilandia","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
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


        String[] bairros   = new String[]{"Alvorada","Boa Esperança","V. Brasilandia","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
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

        String[] bairros   = new String[]{"Alvorada","Boa Esperança","V. Brasilandia","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
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
