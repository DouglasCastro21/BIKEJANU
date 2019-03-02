package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.rtoshiro.util.format.pattern.MaskPattern;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.security.cert.CRLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;

public class GraficoAnoLinhaFragment extends Fragment {


    int  ano ;
    int contandoBikesRouboAno2018=0;
    int contandoBikesRouboAno2019=0;




    // Alimentar com REDS
    int contandoBikesFurtoAno2018=0;
    int contandoBikesFurtoAno2019=0;



    private LineChart  lineChart;




    private String[] nomes   = new String[]{};
    private int[]     roubos = new int   []{};
    private int []   cores   = new int   []{};



    private String[] legenda  = new String[]{};
    private int[]    furtos = new int   []{};



    private        FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_ano_linha, container, false);




        inicializarFirebase();
        lineChart = (LineChart) rootView.findViewById(R.id.graficoAnoLinhas);






        lineChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.conteinerFragmentos,new GraficoAnoLinhaGeralFragm()).commit();





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
                    if (anoDeBusca.contains("2018") && b.getStatus().equals("Roubada")){

                        contandoBikesRouboAno2018++;
                        //   Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "" + texto.toLowerCase().contains(procurarPor.toLowerCase()), Toast.LENGTH_LONG).show();


                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Roubada")){

                        contandoBikesRouboAno2019++;


                    }

                    if (anoDeBusca.contains("2018") && b.getStatus().equals("Furtada")){

                        contandoBikesFurtoAno2018++;


                    }


                    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada")){

                        contandoBikesFurtoAno2019++;


                    }


                    graficoANOS();


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

        FirebaseApp.initializeApp(GraficoAnoLinhaFragment.super.getContext());
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



        lineChart = (LineChart) getSameChart(lineChart,"",Color.RED,Color.TRANSPARENT,3000);
        lineChart.setDrawGridBackground(true);

        lineChart.setActivated(true);






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



    public void     graficoANOS(){

        String[] bairros   = new String[]{"2018","2019"};
        int[]    rob = new int   [] {2,contandoBikesRouboAno2019};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        String[] leg = new String[]{"Furto","Roubo"};
        int[]    furt = new int   [] {129,contandoBikesFurtoAno2019};





        nomes    = bairros;
        roubos   = rob;
        cores = cor;
        legenda  = leg;
        furtos   = furt;





        criarGraficos();


    }



}
