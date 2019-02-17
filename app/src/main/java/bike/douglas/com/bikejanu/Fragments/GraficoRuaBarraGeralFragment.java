package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ImageView;

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

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;

// mostra roubos e furtos em um só grafico

public class GraficoRuaBarraGeralFragment extends Fragment {




    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;




    protected ImageView spinnerImagem;
   private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;





    private BarChart barChart;



    String[] nomes  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};

    private int[]    roubos = new int   []{10,15,14,20};
    private int []   cores   = new int   []{Color.RED};

    private String[] legenda   = new String[]{"Furto/Roubo"};


    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_rua_barra_geral, container, false);


        inicializarFirebase();
        barChart =  (BarChart) rootView.findViewById(R.id.graficoRuaBarraGeral);

//carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoRuaBarraGeralFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);




          spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
                 @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                     if(position==0){




                         String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};
                         int[]    valores = new int   []{10,10,10,10};
                         int []   coresRuas   = new int   []{Color.DKGRAY,Color.RED};
                         String[] legendaGrafico  = new String[]{"Furto","Roubo"};
                         int[]    furtosRuas = new int   []{5,10,6,10};



                         nomes   = ruas;
                         roubos =  valores;
                         cores   =  coresRuas;
                         legenda  = legendaGrafico;



                         criarGraficos();

                     } else if(position == 1){

                         String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};
                         int[]    valores = new int   []{10,10,10,10};
                         int []   coresRuas   = new int   []{Color.DKGRAY,Color.RED};
                         String[] legendaGrafico  = new String[]{"Furto","Roubo"};
                         int[]    furtosRuas = new int   []{5,10,6,10};



                         nomes   = ruas;
                         roubos =  valores;
                         cores   =  coresRuas;
                         legenda  = legendaGrafico;



                         criarGraficos();



                     }else if (position==2){

                         String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};
                         int[]    valores = new int   []{10,10,10,10};
                         int []   coresRuas   = new int   []{Color.DKGRAY,Color.RED};
                         String[] legendaGrafico  = new String[]{"Furto","Roubo"};
                         int[]    furtosRuas = new int   []{5,10,6,10};



                         nomes   = ruas;
                         roubos =  valores;
                         cores   =  coresRuas;
                         legenda  = legendaGrafico;



                         criarGraficos();

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

                transaction.replace(R.id.conteinerFragmentos,new GraficoRuaBarraFragment()).commit();



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

        FirebaseApp.initializeApp(GraficoRuaBarraGeralFragment.super.getContext());
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




        BarDataSet set1;


        set1 = new BarDataSet(yVals1,"Roubo");
        set1.setColor(Color.RED);
        set1.setValueTextSize(15);
        set1.setValueTextColor(Color.BLUE);






        BarData data = new BarData(set1);

        barChart.setData(data);



        axisX(barChart.getXAxis());


        barChart.getLegend().setEnabled(true);

        data.setBarWidth(0.45f);



        barChart.invalidate();


    }




}
