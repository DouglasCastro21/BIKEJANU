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


public class GraficoBairroBarraFragment extends Fragment {




    int qtdAlvoradaFurto=0;    // int qtdAlvoradaFurtoTodos=0+ 23 dados de 2018 apenas alimentado;
    int qtdBoaEsperancaFurto=0;
    int qtdBomJardimFurto=0;
    int qtdCentroFurto=0;
    int qtdCeramicaFurto=0;
    int qtdJadeteFurto=0;
    int qtdJatobaFurto=0;
    int qtdMangueirasFurto=0;
    int qtdBandeirantesFurto=0;
    int qtdJussaraFurto=0;
    int qtdLevianopolisFurto=0;


    int qtdAlvoradaRoubo=0;
    int qtdBoaEsperancaRoubo=0;
    int qtdBomJardimRoubo=0;
    int qtdCentroRoubo=0;
    int qtdCeramicaRoubo=0;
    int qtdJadeteRoubo=0;
    int qtdJatobaRoubo=0;
    int qtdMangueirasRoubo=0;
    int qtdBandeirantesRoubo=0;
    int qtdJussaraRoubo=0;
    int qtdLevianopolisRoubo=0;



    int qtdAlvoradaFurto2018=0;
    int qtdBoaEsperancaFurto2018=0;
    int qtdBomJardimFurto2018=0;
    int qtdCentroFurto2018=0;
    int qtdCeramicaFurto2018=0;
    int qtdJadeteFurto2018=0;
    int qtdJatobaFurto2018=0;
    int qtdMangueirasFurto2018=0;
    int qtdBandeirantesFurto2018=0;
    int qtdJussaraFurto2018=0;
    int qtdLevianopolisFurto2018=0;


    int qtdAlvoradaRoubo2018=0;
    int qtdBoaEsperancaRoubo2018=0;
    int qtdBomJardimRoubo2018=0;
    int qtdCentroRoubo2018=0;
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


    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;



    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;




    private BarChart barChart;

    String[] nomes   = new String[]{"Boa Vista","Jussara","Alvorada","Ceramica","ussara","Alvorada","Ceramica","Alvorada","Ceramica"};
    int[]    roubos =  new int   []{25,15,14,20,12,10,18};
    int []   cores   = new int   []{Color.DKGRAY,Color.RED};
    String[] legenda  = new String[]{"Furto","Roubo"};
    int[]    furtos = new int   []{5,10,6,10,5,10,6,10,5};





    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_bairro_barra, container, false);




        inicializarFirebase();
        barChart =  (BarChart) rootView.findViewById(R.id.graficoBairroBarra);


      //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoBairroBarraFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);



        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){

                    positionTodosAnos();  // alimentar com dados de 2018


                } else if(position == 1){

                    positionAno2018(); /// alimentar com dados de 2018



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

                transaction.replace(R.id.conteinerFragmentos,new GraficoBairroBarraGeralFragment()).commit();



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
                    String procurarBairro = b.getAlertaBairro();
                    //	String procurarPor = "2018";




                    /// todos os anos  furtadas


                    if (b.getAlertaBairro().equals("Alvorada") && b.getStatus().equals("Furtada")){

                        qtdAlvoradaFurto++;

                    }

                    if (b.getAlertaBairro().equals("Boa Esperança") && b.getStatus().equals("Furtada")){


                        qtdBoaEsperancaFurto++;

                    }


                    if (b.getAlertaBairro().equals("Bom Jardim")&& b.getStatus().equals("Furtada")){

                        qtdBomJardimFurto++;


                    }

                    if (b.getAlertaBairro().equals("Centro")&& b.getStatus().equals("Furtada")){

                        qtdCentroFurto++;

                    }

                    if (b.getAlertaBairro().equals("Ceramica")&& b.getStatus().equals("Furtada")){

                        qtdCeramicaFurto++;

                    }

                    if (b.getAlertaBairro().equals("Vila Jadete")&& b.getStatus().equals("Furtada")){

                        qtdJadeteFurto++;

                    }


                    if (b.getAlertaBairro().equals("Jatoba")&& b.getStatus().equals("Furtada")){

                        qtdJatobaFurto++;

                    }

                    if (b.getAlertaBairro().equals("Mangueiras")&& b.getStatus().equals("Furtada")){

                        qtdMangueirasFurto++;

                    }

                    if (b.getAlertaBairro().equals("Bandeirantes")&& b.getStatus().equals("Furtada")){

                        qtdBandeirantesFurto++;

                    }

                    if (b.getAlertaBairro().equals("Vl Jussara")&& b.getStatus().equals("Furtada")){

                        qtdJussaraFurto++;

                    }


                    if (b.getAlertaBairro().equals("Levianopolis")&& b.getStatus().equals("Furtada")){

                        qtdLevianopolisFurto++;

                    }



                    /// todos os anos roubadas


                    if (b.getAlertaBairro().equals("Alvorada") && b.getStatus().equals("Roubada")){

                        qtdAlvoradaRoubo++;

                    }

                    if (b.getAlertaBairro().equals("Boa Esperança") && b.getStatus().equals("Roubada")){


                        qtdBoaEsperancaRoubo++;

                    }


                    if (b.getAlertaBairro().equals("Bom Jardim")&& b.getStatus().equals("Roubada")){

                        qtdBomJardimRoubo++;


                    }

                    if (b.getAlertaBairro().equals("Centro")&& b.getStatus().equals("Roubada")){

                        qtdCentroRoubo++;

                    }

                    if (b.getAlertaBairro().equals("Ceramica")&& b.getStatus().equals("Roubada")){

                        qtdCeramicaRoubo++;

                    }

                    if (b.getAlertaBairro().equals("Vila Jadete")&& b.getStatus().equals("Roubada")){

                        qtdJadeteRoubo++;

                    }


                    if (b.getAlertaBairro().equals("Jatoba")&& b.getStatus().equals("Roubada")){

                        qtdJatobaRoubo++;

                    }

                    if (b.getAlertaBairro().equals("Mangueiras")&& b.getStatus().equals("Roubada")){

                        qtdMangueirasRoubo++;

                    }

                    if (b.getAlertaBairro().equals("Bandeirantes")&& b.getStatus().equals("Roubada")){

                        qtdBandeirantesRoubo++;

                    }

                    if (b.getAlertaBairro().equals("Vl Jussara")&& b.getStatus().equals("Roubada")){

                        qtdJussaraRoubo++;

                    }


                    if (b.getAlertaBairro().equals("Levianopolis")&& b.getStatus().equals("Roubada")){

                        qtdLevianopolisRoubo++;

                    }





                    if (anoDeBusca.contains("2018") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        contandoBikesAno2018++;
                        //   Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "" + texto.toLowerCase().contains(procurarPor.toLowerCase()), Toast.LENGTH_LONG).show();


                    }



                    /// bikes furtadas em 2019
                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Alvorada")){


                            qtdAlvoradaFurto2019++;;

                    }



                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Boa Esperança") ){

                        qtdBoaEsperancaFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Bom Jardim") ){

                        qtdBomJardimFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Centro") ){


                        qtdCentroFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Ceramica") ){

                        qtdCeramicaFurto2019++;


                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Vila Jadete") ){


                            qtdJadeteFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Jatoba") ){



                        qtdJatobaFurto2019++;
                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Mangueiras") ){


                        qtdMangueirasFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Bandeirantes") ){


                        qtdBandeirantesFurto2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Vl Jussara") ){


                        qtdJussaraFurto2019++;

                    }



                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Furtada") && b.getAlertaBairro().equals("Levianopolis") ){


                        qtdLevianopolisFurto2019++;

                    }





                    /// bikes Roubadas em 2019
                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Alvorada")){


                        qtdAlvoradaRoubo2019++;


                    }



                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Boa Esperança") ){

                        qtdBoaEsperancaRoubo2019++;


                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Bom Jardim") ){


                    qtdBomJardimRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Centro") ){


                    qtdCentroRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Ceramica") ){


                    qtdCeramicaRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Vila Jadete") ){

                        qtdJadeteRoubo2019++;


                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Jatoba") ){


                        qtdJatobaRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Mangueiras") ){


                    qtdMangueirasRoubo2019++;

                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Bandeirantes") ){

                        qtdBandeirantesRoubo2019++;


                    }


                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Vl Jussara") ){

                        qtdJussaraRoubo2019++;


                    }



                    if (anoDeBusca.contains("2019") && b.getAlertaBairro().equals("Roubada") && b.getAlertaBairro().equals("Levianopolis") ){


                        qtdLevianopolisRoubo2019++;

                    }




                }




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

        FirebaseApp.initializeApp(GraficoBairroBarraFragment.super.getContext());
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



        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        for (int i=0;i<roubos.length;i++){
            yVals1.add(new BarEntry(i,roubos[i]));

        }


        ArrayList<BarEntry> yVals2 = new ArrayList<>();

        for(int i=0;i <furtos.length;i++){
            yVals2.add(new BarEntry(i,furtos[i]));

        }


        BarDataSet set1,set2;


        set1 = new BarDataSet(yVals1,"Roubo");
        set1.setColor(Color.RED);
        set1.setValueTextSize(15);
        set1.setValueTextColor(Color.BLUE);



        set2= new BarDataSet(yVals2, "Furto");

        set2.setColor(Color.YELLOW);
        set2.setValueTextSize(15);
        set2.setValueTextColor(Color.BLUE);


        BarData data = new BarData(set1,set2);

        barChart.setData(data);



        axisX(barChart.getXAxis());


        barChart.getLegend().setEnabled(true);

        data.setBarWidth(0.45f);



        barChart.invalidate();


    }


    public void positionTodosAnos(){

        String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Jatoba","Mangueiras","Bandeirantes","Vila Jussara","Levianopolis"};
        int[]    rob = new int   []{qtdAlvoradaRoubo,qtdBoaEsperancaRoubo,qtdBomJardimRoubo,qtdCentroRoubo,qtdCeramicaRoubo,qtdJadeteRoubo,qtdJatobaRoubo,qtdMangueirasRoubo,qtdBandeirantesRoubo,qtdJussaraRoubo,qtdLevianopolisRoubo};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        final String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{qtdAlvoradaFurto,qtdBoaEsperancaFurto,qtdBomJardimFurto,qtdCentroFurto,qtdCeramicaFurto,qtdJadeteFurto,qtdJatobaFurto,qtdMangueirasFurto,qtdBandeirantesFurto,qtdJussaraFurto,qtdLevianopolisFurto};



        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;


        criarGraficos();


    }



   public void positionAno2018(){


       String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Jatoba","Mangueiras","Bandeirantes","Vila Jussara","Levianopolis"};
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

        String[] bairros   = new String[]{"Alvorada","Boa Esperança","Bom Jardim","Centro","Ceramica","Vila Jadete","Jatoba","Mangueiras","Bandeirantes","Vila Jussara","Levianopolis"};
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
