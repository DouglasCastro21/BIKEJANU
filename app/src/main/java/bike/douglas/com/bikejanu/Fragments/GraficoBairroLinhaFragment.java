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

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
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
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;

public class GraficoBairroLinhaFragment extends Fragment {




    int qtdAlvoradaFurtoTodos =0;    // int qtdAlvoradaFurtoTodos=0+ 23 dados de 2018 apenas alimentado;
    int qtdBoaEsperancaFurtoTodos =0;
    int qtdBomJardimFurtoTodos =0;
    int qtdCentroFurtoTodos =0;
    int qtdCeramicaFurtoTodos =0;
    int qtdJadeteFurtoTodos =0;
    int qtdJatobaFurtoTodos =0;
    int qtdMangueirasFurtoTodos =0;
    int qtdBandeirantesFurtoTodos =0;
    int qtdJussaraFurtoTodos =0;
    int qtdLevianopolisFurtoTodos =0;


    int qtdAlvoradaRouboTodos =0;
    int qtdBoaEsperancaRouboTodos =0;
    int qtdBomJardimRouboTodos =0;
    int qtdCentroRouboTodos =0;
    int qtdCeramicaRouboTodos =0;
    int qtdJadeteRouboTodos =0;
    int qtdJatobaRouboTodos =0;
    int qtdMangueirasRouboTodos =0;
    int qtdBandeirantesRouboTodos =0;
    int qtdJussaraRouboTodos =0;
    int qtdLevianopolisRouboTodos =0;



 /// Alimentar com REDS

    int qtdAlvoradaFurto2018=1;
    int qtdBoaEsperancaFurto2018=1;
    int qtdBomJardimFurto2018=8;
    int qtdCentroFurto2018=62;
    int qtdCeramicaFurto2018=5;
    int qtdJadeteFurto2018=1;
    int qtdJatobaFurto2018=2;  // é frankinlim
    int qtdMangueirasFurto2018=7;
    int qtdBandeirantesFurto2018=2;
    int qtdJussaraFurto2018=6;
    int qtdLevianopolisFurto2018=3;


    int qtdAlvoradaRoubo2018=0;
    int qtdBoaEsperancaRoubo2018=0;
    int qtdBomJardimRoubo2018=0;
    int qtdCentroRoubo2018=2;
    int qtdCeramicaRoubo2018=0;
    int qtdJadeteRoubo2018=0;
    int qtdJatobaRoubo2018=0;
    int qtdMangueirasRoubo2018=0;
    int qtdBandeirantesRoubo2018=0;
    int qtdJussaraRoubo2018=0;
    int qtdLevianopolisRoubo2018=0;



    int qtdAlvoradaFurto2019=0;
    int qtdBoaEsperancaFurto2019=0;
    int qtdBomJardimFurto2019=0;
    int qtdCentroFurto2019=0;
    int qtdCeramicaFurto2019=0;
    int qtdJadeteFurto2019=0;
    int qtdJatobaFurto2019=0;
    int qtdMangueirasFurto2019=0;
    int qtdBandeirantesFurto2019=0;
    int qtdJussaraFurto2019=0;
    int qtdLevianopolisFurto2019=0;


    int qtdAlvoradaRoubo2019=0;
    int qtdBoaEsperancaRoubo2019=0;
    int qtdBomJardimRoubo2019=0;
    int qtdCentroRoubo2019=0;
    int qtdCeramicaRoubo2019=0;
    int qtdJadeteRoubo2019=0;
    int qtdJatobaRoubo2019=0;
    int qtdMangueirasRoubo2019=0;
    int qtdBandeirantesRoubo2019=0;
    int qtdJussaraRoubo2019=0;
    int qtdLevianopolisRoubo2019=0;



   // int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;


    private static String  bairro;

    private LineChart lineChart;


    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;




    String[] nomes   = new String[]{};
    int[]    roubos =  new int   []{};
    int []   cores   = new int   []{};
    String[] legenda  = new String[]{};
    int[]    furtos = new int   []{};


    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;


    //   public static List<String> listBairros = new ArrayList<String>();
    // public static ArrayAdapter<String> arrayAdapterBairro;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_bairro_linha, container, false);

        lineChart =  (LineChart) rootView.findViewById(R.id.graficoBairroLinhas);

        inicializarFirebase();





        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoBairroLinhaFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);


       // final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();


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

                transaction.replace(R.id.conteinerFragmentos,new GraficoBairroLinhaGeralFragment()).commit();





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

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);




                    String anoDeBusca = b.getAlertaDate();
                    String procurarBairro = b.getAlertaBairro();
                    //	String procurarPor = "2018";




                    /// todos os anos  furtadas


                    if (b.getAlertaBairro().equals("Alvorada") && b.getStatus().equals("Furtada")){

                        qtdAlvoradaFurtoTodos++;

                    }

                    if (b.getAlertaBairro().equals("Boa Esperança") && b.getStatus().equals("Furtada")){


                        qtdBoaEsperancaFurtoTodos++;

                    }


                    if (b.getAlertaBairro().equals("Bom Jardim")&& b.getStatus().equals("Furtada")){

                        qtdBomJardimFurtoTodos++;


                    }

                    if (b.getAlertaBairro().equals("Centro")&& b.getStatus().equals("Furtada")){

                        qtdCentroFurtoTodos++;

                    }

                    if (b.getAlertaBairro().equals("Ceramica")&& b.getStatus().equals("Furtada")){

                        qtdCeramicaFurtoTodos++;

                    }

                    if (b.getAlertaBairro().equals("Vila Jadete")&& b.getStatus().equals("Furtada")){

                        qtdJadeteFurtoTodos++;

                    }


                    if (b.getAlertaBairro().equals("Franklim")&& b.getStatus().equals("Furtada")){

                        qtdJatobaFurtoTodos++;

                    }

                    if (b.getAlertaBairro().equals("Quintas das Mangueiras")&& b.getStatus().equals("Furtada")){

                        qtdMangueirasFurtoTodos++;

                    }

                    if (b.getAlertaBairro().equals("Bandeirantes")&& b.getStatus().equals("Furtada")){

                        qtdBandeirantesFurtoTodos++;

                    }

                    if (b.getAlertaBairro().equals("Jussara")&& b.getStatus().equals("Furtada")){

                        qtdJussaraFurtoTodos++;

                    }


                    if (b.getAlertaBairro().equals("Vila Levianopolis")&& b.getStatus().equals("Furtada")){

                        qtdLevianopolisFurtoTodos++;

                    }



                    /// todos os anos roubadas


                    if (b.getAlertaBairro().equals("Alvorada") && b.getStatus().equals("Roubada")){

                        qtdAlvoradaRouboTodos++;

                    }

                    if (b.getAlertaBairro().equals("Boa Esperança") && b.getStatus().equals("Roubada")){


                        qtdBoaEsperancaRouboTodos++;

                    }


                    if (b.getAlertaBairro().equals("Bom Jardim")&& b.getStatus().equals("Roubada")){

                        qtdBomJardimRouboTodos++;


                    }

                    if (b.getAlertaBairro().equals("Centro")&& b.getStatus().equals("Roubada")){

                        qtdCentroRouboTodos++;

                    }

                    if (b.getAlertaBairro().equals("Ceramica")&& b.getStatus().equals("Roubada")){

                        qtdCeramicaRouboTodos++;

                    }

                    if (b.getAlertaBairro().equals("Vila Jadete")&& b.getStatus().equals("Roubada")){

                        qtdJadeteRouboTodos++;

                    }


                    if (b.getAlertaBairro().equals("Franklim")&& b.getStatus().equals("Roubada")){

                        qtdJatobaRouboTodos++;

                    }

                    if (b.getAlertaBairro().equals("Quintas das Mangueiras")&& b.getStatus().equals("Roubada")){

                        qtdMangueirasRouboTodos++;

                    }

                    if (b.getAlertaBairro().equals("Bandeirantes")&& b.getStatus().equals("Roubada")){

                        qtdBandeirantesRouboTodos++;

                    }

                    if (b.getAlertaBairro().equals("Jussara")&& b.getStatus().equals("Roubada")){

                        qtdJussaraRouboTodos++;

                    }


                    if (b.getAlertaBairro().equals("Vila Levianopolis")&& b.getStatus().equals("Roubada")){

                        qtdLevianopolisRouboTodos++;

                    }









                    /// bikes furtadas em 2019
                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Alvorada")){


                        qtdAlvoradaFurto2019++;

                    }



                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Boa Esperança") ){

                        qtdBoaEsperancaFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Bom Jardim") ){

                        qtdBomJardimFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Centro") ){


                        qtdCentroFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Ceramica") ){

                        qtdCeramicaFurto2019++;


                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Vila Jadete") ){


                        qtdJadeteFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Franklim") ){



                        qtdJatobaFurto2019++;
                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Quintas das Mangueiras") ){


                        qtdMangueirasFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Bandeirantes") ){


                        qtdBandeirantesFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Jussara") ){


                        qtdJussaraFurto2019++;

                    }



                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada") && b.getAlertaBairro().equals("Vila Levianopolis") ){


                        qtdLevianopolisFurto2019++;

                    }





                    /// bikes Roubadas em 2019
                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Alvorada")){


                        qtdAlvoradaRoubo2019++;


                    }



                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Boa Esperança") ){

                        qtdBoaEsperancaRoubo2019++;


                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Bom Jardim") ){


                        qtdBomJardimRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Centro") ){


                        qtdCentroRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Ceramica") ){


                        qtdCeramicaRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Vila Jadete") ){

                        qtdJadeteRoubo2019++;


                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Franklim") ){


                        qtdJatobaRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Quintas das Mangueiras") ){


                        qtdMangueirasRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Bandeirantes") ){

                        qtdBandeirantesRoubo2019++;


                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Jussara") ){

                        qtdJussaraRoubo2019++;


                    }



                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada") && b.getAlertaBairro().equals("Vila Levianopolis") ){


                        qtdLevianopolisRoubo2019++;

                    }









                }



                qtdAlvoradaFurtoTodos      =qtdAlvoradaFurto2018     +  qtdAlvoradaFurto2019;
                qtdBoaEsperancaFurtoTodos  =qtdBoaEsperancaFurto2018 +  qtdBoaEsperancaFurto2019;
                qtdBomJardimFurtoTodos     =qtdBomJardimFurto2018    +  qtdBomJardimFurto2019;
                qtdCentroFurtoTodos        =qtdCentroFurto2018       +  qtdCentroFurto2019;
                qtdCeramicaFurtoTodos      =qtdCeramicaFurto2018     +  qtdCeramicaFurto2019;
                qtdJadeteFurtoTodos        =qtdJadeteFurto2018       +  qtdJadeteFurto2019;
                qtdJatobaFurtoTodos        =qtdJatobaFurto2018       +  qtdJatobaFurto2019;
                qtdMangueirasFurtoTodos    =qtdMangueirasFurto2018   +  qtdMangueirasFurto2019;
                qtdBandeirantesFurtoTodos  =qtdBandeirantesFurto2018 +  qtdBandeirantesFurto2019;
                qtdJussaraFurtoTodos       =qtdJussaraFurto2018      +  qtdJussaraFurto2019;
                qtdLevianopolisFurtoTodos  =qtdLevianopolisFurto2018 +  qtdLevianopolisFurto2019;


                qtdAlvoradaRouboTodos       =qtdAlvoradaRoubo2018    +  qtdAlvoradaRoubo2019;
                qtdBoaEsperancaRouboTodos   =qtdBoaEsperancaRoubo2018+  qtdBoaEsperancaRoubo2019;
                qtdBomJardimRouboTodos      =qtdBomJardimRoubo2018   +  qtdBomJardimRoubo2019;
                qtdCentroRouboTodos         =qtdCentroRoubo2018      +  qtdCentroRoubo2019;
                qtdCeramicaRouboTodos       =qtdCeramicaRoubo2018    +  qtdCeramicaRoubo2019;
                qtdJadeteRouboTodos         =qtdJadeteRoubo2018      +  qtdJadeteRoubo2019;
                qtdJatobaRouboTodos         =qtdJatobaRoubo2018      +  qtdJatobaRoubo2019;
                qtdMangueirasRouboTodos     =qtdMangueirasRoubo2018  +  qtdMangueirasRoubo2019;
                qtdBandeirantesRouboTodos   =qtdBandeirantesRoubo2018+  qtdBandeirantesRoubo2019;
                qtdJussaraRouboTodos        =qtdJussaraRoubo2018     +  qtdJussaraRoubo2019;
                qtdLevianopolisRouboTodos   =qtdLevianopolisRoubo2018+  qtdLevianopolisRoubo2019;


// simula a lista

                arrayAdapterBike = new BikeAdapter(GraficoBairroLinhaFragment.super.getContext(), (ArrayList<Bike>) listBikes);
                //   listPesquisa.setAdapter(arrayAdapterBike);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }

          });




        return rootView;

    }


    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoBairroLinhaFragment.super.getContext());
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



        ArrayList<Entry> yVals2 = new ArrayList<>();

        for(int i=0;i <furtos.length;i++){
            yVals2.add(new Entry(i,furtos[i]));

        }



        LineDataSet set1,set2;


        set1 = new LineDataSet(yVals1,"Roubo");
        set1.setColor(Color.RED);
        set1.setCircleColor(Color.RED);
        set1.setDrawCircles(true);
        set1.setLineWidth(4f);
        set1.setValueTextSize(15);
        set1.setValueTextColor(Color.BLUE);



        set2= new LineDataSet(yVals2, "Furto");
        set2.setColor(Color.YELLOW);
        set2.setCircleColor(Color.RED);
        set2.setLineWidth(4f);
        set2.setDrawCircles(true);
        set2.setValueTextSize(15);
        set2.setValueTextColor(Color.BLUE);

        LineData data = new LineData(set1,set2);

        lineChart.setData(data);




        axisX(lineChart.getXAxis());


        lineChart.getLegend().setEnabled(true);



    }





    public void positionTodosAnos(){

        String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
        int[]    rob = new int   []{qtdAlvoradaRouboTodos, qtdBoaEsperancaRouboTodos, qtdBomJardimRouboTodos, qtdCentroRouboTodos, qtdCeramicaRouboTodos, qtdJadeteRouboTodos, qtdJatobaRouboTodos, qtdMangueirasRouboTodos, qtdBandeirantesRouboTodos, qtdJussaraRouboTodos, qtdLevianopolisRouboTodos};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        final String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{qtdAlvoradaFurtoTodos, qtdBoaEsperancaFurtoTodos, qtdBomJardimFurtoTodos, qtdCentroFurtoTodos, qtdCeramicaFurtoTodos, qtdJadeteFurtoTodos, qtdJatobaFurtoTodos, qtdMangueirasFurtoTodos, qtdBandeirantesFurtoTodos, qtdJussaraFurtoTodos, qtdLevianopolisFurtoTodos};



        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;


        criarGraficos();


    }



    public void positionAno2018(){


        String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
        int[]    rob = new int   []{qtdAlvoradaRoubo2018,qtdBoaEsperancaRoubo2018,qtdBomJardimRoubo2018,qtdCentroRoubo2018,qtdCeramicaRoubo2018,qtdJadeteRoubo2018,qtdJatobaRoubo2018,qtdMangueirasRoubo2018,qtdBandeirantesRoubo2018,qtdJussaraRoubo2018,qtdLevianopolisRoubo2018};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        final String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{qtdAlvoradaFurto2018,qtdBoaEsperancaFurto2018,qtdBomJardimFurto2018,qtdCentroFurto2018,qtdCeramicaFurto2018,qtdJadeteFurto2018,qtdJatobaFurto2018,qtdMangueirasFurto2018,qtdBandeirantesFurto2018,qtdJussaraFurto2018,qtdLevianopolisFurto2018};



        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;



        criarGraficos();



    }


    public void  positionAno2019(){

        String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Franklim","Q.Mangueiras","Bandeirantes","Vila Jussara","V. Levianopolis"};
        int[]    rob = new int   []{qtdAlvoradaRoubo2019,qtdBoaEsperancaRoubo2019,qtdBomJardimRoubo2019,qtdCentroRoubo2019,qtdCeramicaRoubo2019,qtdJadeteRoubo2019,qtdJatobaRoubo2019,qtdMangueirasRoubo2019,qtdBandeirantesRoubo2019,qtdJussaraRoubo2019,qtdLevianopolisRoubo2019};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        final     String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{qtdAlvoradaFurto2019,qtdBoaEsperancaFurto2019,qtdBomJardimFurto2019,qtdCentroFurto2019,qtdCeramicaFurto2019,qtdJadeteFurto2019,qtdJatobaFurto2019,qtdMangueirasFurto2019,qtdBandeirantesFurto2019,qtdJussaraFurto2019,qtdLevianopolisFurto2019};



        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;


        criarGraficos();


    }




}
