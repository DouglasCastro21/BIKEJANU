package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.EditarBike;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class GraficoAnoBarraFragment extends Fragment {



    int  ano ;
    int contandoBikesRouboAno2018=0;
    int contandoBikesRouboAno2019=0;




    // Alimentar com REDS
    int contandoBikesFurtoAno2018=0;
    int contandoBikesFurtoAno2019=0;




    private BarChart barChart;




    private String[] nomes   = new String[]{};
    private int[]     roubos = new int   []{};
    private int []   cores   = new int   []{};
    private String[] legenda  = new String[]{};
    private int[]    furtos = new int   []{};



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



         //   graficoANOS();



        barChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.conteinerFragmentos,new GraficoAnoBarraGeralFragment()).commit();



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




		if (anoDeBusca.contains("2018") && b.getStatus().equals("Roubada")){

          //Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "sei lá" + texto.toLowerCase().contains(procurarPor.toLowerCase()), Toast.LENGTH_LONG).show();

            contandoBikesRouboAno2018++ ;


         //   Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "ROubo 2018"+contandoBikesRouboAno2018, Toast.LENGTH_LONG).show();

        }


        if ( anoDeBusca.contains("2018") && b.getStatus().equals("Furtada")){

		    contandoBikesFurtoAno2018++;






        }


           if (anoDeBusca.contains("2019")  && b.getStatus().equals("Roubada")){

             contandoBikesRouboAno2019++;


		    }

		    if (anoDeBusca.contains("2019") && b.getStatus().equals("Furtada")){


		    contandoBikesFurtoAno2019++;


		    }


		    graficoANOS();


		}











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


        barChart = (BarChart) getSameChart(barChart,"",Color.RED,Color.WHITE,3000);
        barChart.setDrawGridBackground(true);



         barChart.setActivated(true);



        ArrayList<BarEntry> yVals1 = new ArrayList<>();






        for(int i=0;i <furtos.length;i++){
            yVals1.add(new BarEntry(i,furtos[i]));

        }


        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        for (int i=0;i<roubos.length;i++){
            yVals2.add(new BarEntry(i,roubos[i]));

        }











        BarDataSet set1,set2;


        set1 = new BarDataSet(yVals1,"Furto");
        set1.setColor(Color.YELLOW);
        set1.setValueTextSize(15);
        set1.setValueTextColor(Color.BLUE);



        set2= new BarDataSet(yVals2, "Roubo");

        set2.setColor(Color.RED);
        set2.setValueTextSize(15);
        set2.setValueTextColor(Color.BLUE);






        BarData data = new BarData(set1,set2);


        barChart.setData(data);



        axisX(barChart.getXAxis());

       // axisRight(barChart.getAxisRight());


        barChart.getLegend().setEnabled(true);

        data.setBarWidth(0.45f);



        barChart.invalidate();


    }





    public void     graficoANOS(){

        String[] bairros   = new String[]{"2018","2019"};
        int[]    rob = new int   [] {contandoBikesRouboAno2018,contandoBikesRouboAno2019};
        int []   cor   = new int   []{Color.YELLOW,Color.RED};
        String[] leg = new String[]{"Furto","Roubo"};
        int[]    furt = new int   [] {contandoBikesFurtoAno2018,contandoBikesFurtoAno2019};




        nomes    = bairros;
        roubos   = rob;
        cores = cor;
        legenda  = leg;
        furtos   = furt;





        criarGraficos();


    }

}

