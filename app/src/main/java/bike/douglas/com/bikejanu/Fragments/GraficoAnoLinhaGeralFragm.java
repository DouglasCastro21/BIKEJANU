package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class GraficoAnoLinhaGeralFragm extends Fragment {


    int ano;
    int contandoBikesAnoFurtosRoubos2018 = 0;
    int contandoBikesFurtosRoubosAno2019 = 56;
    int contandoBikesFurtosRoubosAno2020 = 0;

    String data_ano;


    private LineChart lineChart;


    private String[] nomes = new String[]{};
    private int[] roubos = new int[]{};
    private int[] cores = new int[]{};
    private String[] legenda = new String[]{};


    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_ano_linha_geral, container, false);


        inicializarFirebase();
        lineChart = (LineChart) rootView.findViewById(R.id.graficoAnoLinhaGeral);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");


        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        final Date data_atual = cal.getTime();

        data_ano = dateFormat.format(data_atual);


        graficoANOS();


        lineChart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.conteinerFragmentos, new GraficoAnoLinhaFragment()).commit();


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

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);


                    String anoDeBusca = b.getAlertaDate();
                    //	String procurarPor = "2018";
                    if (anoDeBusca.contains("2018") && (b.getStatus().equals("Furtada") || b.getStatus().equals("Roubada"))) {

                        contandoBikesAnoFurtosRoubos2018++;


                    }


                    if (anoDeBusca.contains("2019") && (b.getStatus().equals("Furtada") || b.getStatus().equals("Roubada"))) {

                        contandoBikesFurtosRoubosAno2019++;


                    }


                    if (data_ano.equals("2020")) {

                        if (anoDeBusca.contains("2020") && (b.getStatus().equals("Furtada") || b.getStatus().equals("Roubada"))) {

                            contandoBikesFurtosRoubosAno2020++;


                        }


                    }

                    graficoANOS();


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

        FirebaseApp.initializeApp(GraficoAnoLinhaGeralFragm.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    private Chart getSameChart(Chart chart, String descricao, int textColor, int background, int animacaoX) {

        chart.getDescription().setText(descricao);
        chart.getDescription().setTextSize(24);
        chart.setBackgroundColor(background);
        chart.animateX(animacaoX);


        legend(chart);

        return chart;
    }


    public void legend(Chart chart) {

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextSize(15);


        ArrayList<LegendEntry> entries = new ArrayList<>();

        for (int i = 0; i < legenda.length; i++) {

            LegendEntry entry = new LegendEntry();
            entry.formColor = cores[i];
            entry.label = legenda[i];
            entries.add(entry);

        }


        legend.setCustom(entries);

    }


    private void axisX(XAxis axis) {

        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        axis.setValueFormatter(new IndexAxisValueFormatter(nomes));


    }


    private void criarGraficos() {


        lineChart = (LineChart) getSameChart(lineChart, "", Color.RED, Color.TRANSPARENT, 2000);
        lineChart.setDrawGridBackground(true);

        lineChart.setActivated(true);


        ArrayList<Entry> yVals1 = new ArrayList<>();
        for (int i = 0; i < roubos.length; i++) {
            yVals1.add(new Entry(i, roubos[i]));

        }


        LineDataSet set1;


        set1 = new LineDataSet(yVals1, "Roubo");
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


    public void graficoANOS() {


        if (data_ano.equals("2019")) {

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


        if (data_ano.equals("2020")) {


            String[] bairros = new String[]{"2018", "2019", "2020"};
            int[] rob = new int[]{131, contandoBikesFurtosRoubosAno2019, contandoBikesFurtosRoubosAno2020};
            int[] cor = new int[]{Color.RED};
            String[] leg = new String[]{"Furto/Roubo"};


            nomes = bairros;
            roubos = rob;
            cores = cor;
            legenda = leg;


            criarGraficos();


        }
    }
}