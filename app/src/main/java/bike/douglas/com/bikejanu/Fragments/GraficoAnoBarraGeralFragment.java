package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;



public class GraficoAnoBarraGeralFragment extends Fragment {




    int  ano ;
    int contandoBikesAnoFurtosRoubos2018=0;
    int contandoBikesFurtosRoubosAno2019=56;
    int contandoBikesFurtosRoubosAno2020=0;



    String data_ano;

    private BarChart barChart;



    private String[] nomes   = new String[]{};
    private int[]     roubos = new int   []{};
    private int []   cores   = new int   []{};
    private String[] legenda   = new String[]{};


    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_ano_barra_geral, container, false);




        inicializarFirebase();
        barChart =  (BarChart) rootView.findViewById(R.id.graficoAnoBarraGeral);





        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");


        Date data = new Date();

        Calendar  cal = Calendar.getInstance();
        cal.setTime(data);

        final Date data_atual = cal.getTime();

        data_ano = dateFormat.format(data_atual);








        graficoANOS();






        barChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.conteinerFragmentos,new GraficoAnoBarraFragment()).commit();



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

                    if (anoDeBusca.contains("2018") && (b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada"))){

                        contandoBikesAnoFurtosRoubos2018++;


                    }


                    if (anoDeBusca.contains("2019") && (b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada"))){

                        contandoBikesFurtosRoubosAno2019++;


                    }



                    if(data_ano.equals("2020")){

                        if (anoDeBusca.contains("2020") && (b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada"))){

                            contandoBikesFurtosRoubosAno2020++;


                        }




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

        FirebaseApp.initializeApp(GraficoAnoBarraGeralFragment.super.getContext());
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




    private void axisRight(YAxis axis){
        axis.setEnabled(true);

    }




    private void criarGraficos(){


        barChart = (BarChart) getSameChart(barChart,"",Color.RED,Color.TRANSPARENT,3000);
        barChart.setDrawGridBackground(true);

        barChart.setActivated(true);



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
      //  axisRight(barChart.getAxisRight());


        barChart.getLegend().setEnabled(true);

        data.setBarWidth(0.45f);



        barChart.invalidate();


    }




    public void     graficoANOS() {



        if(data_ano.equals("2019")){

        String[] bairros = new String[]{"2018", "2019"};
        int[] rob = new int[]{131, contandoBikesFurtosRoubosAno2019};
        int[] cor = new int[]{Color.RED};
        String[] leg = new String[]{"Furto/Roubo"};


        nomes = bairros;
        roubos = rob;
        cores = cor;
        legenda = leg;


        criarGraficos();

    }



        if(data_ano.equals("2020")){



            String[] bairros   = new String[]{"2018","2019","2020"};
            int[]    rob = new int   []{131,contandoBikesFurtosRoubosAno2019,contandoBikesFurtosRoubosAno2020};
            int []   cor   = new int   []{Color.RED};
            String[] leg = new String[]{"Furto/Roubo"};




            nomes    = bairros;
            roubos   = rob;
            cores    = cor;
            legenda  = leg;




            criarGraficos();


        }






    }



}
