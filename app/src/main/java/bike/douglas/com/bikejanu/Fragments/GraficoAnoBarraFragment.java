package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
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
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererHorizontalBarChart;
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
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.Estatisticas;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class GraficoAnoBarraFragment extends Fragment {



    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;





    private BarChart barChart;



    private String[] nomes   = new String[]{"2016","2017","2018","2019"};
    private int[]     roubos = new int   []{20,16,20,11};
    private int []   cores   = new int   []{Color.RED,Color.DKGRAY};



    private String[] nome   = new String[]{"Roubo","Furto"};
    private int[]    furtos = new int   []{5,10,6,10};


    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_ano_barra, container, false);




        inicializarFirebase();
        barChart =  (BarChart) rootView.findViewById(R.id.graficoBarrasBarChar);








        barChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "VC USOU CLIK LONGO", Toast.LENGTH_LONG).show();


                return false;
            }
        });




        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();




            query = databaseReference.child("TodasBikes").orderByChild("numero_serie");


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
	//	String procurarPor = "2018";

		if (texto.contains("2018") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

            contandoBikesAno2018++;
		 //   Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "" + texto.toLowerCase().contains(procurarPor.toLowerCase()), Toast.LENGTH_LONG).show();


        }


        if (texto.contains("2019") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

		    contandoBikesAno2019++;


        }




                }


                // inicio do grafico

                final Calendar calendar = Calendar.getInstance();
                ano = calendar.get(Calendar.YEAR);


               // GraphView graph = (GraphView) rootView.findViewById(R.id.graphAnoBarra);

                if(ano == 2018){





                }




                if(ano == 2019){


                   criarGraficos();


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

        FirebaseApp.initializeApp(GraficoAnoBarraFragment.super.getContext());
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

        for(int i=0;i<nome.length;i++){

            LegendEntry entry = new LegendEntry();
            entry.formColor = cores[i];
            entry.label = nome[i];
            entries.add(entry);

        }


        legend.setCustom(entries);

    }



    private void axisX(XAxis axis){

        axis.setGranularityEnabled(true);

        axis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        axis.setValueFormatter(new IndexAxisValueFormatter(nomes));






    }

    private void axisLeft(YAxis axis){
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);

    }


    private void axisRight(YAxis axis){
        axis.setEnabled(true);

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

        set2.setColor(Color.DKGRAY);
        set2.setValueTextSize(15);
        set2.setValueTextColor(Color.BLUE);



        BarData data = new BarData(set1,set2);

        barChart.setData(data);



        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());


        barChart.getLegend().setEnabled(true);
        set1.setValueTextSize(15);
        data.setBarWidth(0.45f);



        barChart.invalidate();


    }


}

