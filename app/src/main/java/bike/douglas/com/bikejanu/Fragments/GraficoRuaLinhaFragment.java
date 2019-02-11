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
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
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


public class GraficoRuaLinhaFragment extends Fragment {

    int cont=0;
    int jatoba=0;
    int boaVista=0;

    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;



    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;



    private static String  bairro;

    private LineChart lineChart;

    private String[] nomes   = new String[]{"Rua1","Rua2","Rua3","Rua4"};
    private int[]     roubos = new int   []{20,16,20,11};
    private int []   cores   = new int   []{Color.DKGRAY,Color.RED};
    private String[] legenda  = new String[]{"Furto","Roubo"};
    private int[]    furtos = new int   []{5,10,6,10};


    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;


    //   public static List<String> listBairros = new ArrayList<String>();
    // public static ArrayAdapter<String> arrayAdapterBairro;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_rua_linha, container, false);

        lineChart =  (LineChart) rootView.findViewById(R.id.graficoRuaLinha);

        inicializarFirebase();


        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoRuaLinhaFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);

        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    furtos = furtosRuas;


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
                    furtos = furtosRuas;


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
                    furtos = furtosRuas;


                    criarGraficos();

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

                transaction.replace(R.id.conteinerFragmentos,new GraficoRuaLinhaGeralFragment()).commit();


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





                    if (!b.getAlertaBairro().equals("") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        //  Toast.makeText(GraficoBairroBarraFragment.super.getContext(), " Bairro :"+bairro, Toast.LENGTH_LONG).show();

                        bairro = b.getAlertaBairro();


                        if(bairro.equals("Jatoba") ){

                            jatoba++;


                        }else if(bairro.equals("Boa Vista") ){

                            boaVista++;


                        }



                        cont++;


                    }





                    if (texto.contains("2018") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        contandoBikesAno2018++;

                    }


                    if (texto.contains("2019") && b.getStatus().equals("Furtada")||b.getStatus().equals("Roubada")){

                        contandoBikesAno2019++;


                    }

                }




/////fim do grafico



// simula a lista

                arrayAdapterBike = new BikeAdapter(GraficoRuaLinhaFragment.super.getContext(), (ArrayList<Bike>) listBikes);
                //   listPesquisa.setAdapter(arrayAdapterBike);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {



            }

        });




        return rootView;

    }


    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoRuaLinhaFragment.super.getContext());
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



        lineChart = (LineChart) getSameChart(lineChart,"",Color.RED,Color.WHITE,3000);
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
        set2.setColor(Color.DKGRAY);
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






}
