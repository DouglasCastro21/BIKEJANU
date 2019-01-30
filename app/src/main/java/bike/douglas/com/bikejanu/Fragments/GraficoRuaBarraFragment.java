package bike.douglas.com.bikejanu.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.util.List;

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;






public class GraficoRuaBarraFragment extends Fragment {


    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;
    int AeroportoRuaTal=0;



    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;




    private BarChart barChart;



    private String[] nomes   = new String[]{"Rua 7","Rua 10","Centro","Rua 12"};
    private int[]    roubos = new int   []{10,15,14,20};
    private int []   cores   = new int   []{Color.DKGRAY,Color.RED};
    private String[] legenda  = new String[]{"Furto","Roubo"};
    private int[]    furtos = new int   []{5,10,6,10};


    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;
    CharSequence	convertSelectionToString;

    String  autoComplete;

    int spinnerCorrente =10;
    int todosAnos=0;
    int ano2018=1;
    int ano2019=2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_rua_barra, container, false);



        inicializarFirebase();
        barChart =  (BarChart) rootView.findViewById(R.id.graficoRuaBarra);


        String[] countries =  getResources().getStringArray(R.array.countries);

        final AutoCompleteTextView autoCompletegraficoRuaBarra = rootView.findViewById(R.id.autocompletegraficoruabarra);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GraficoRuaBarraFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,countries);
        autoCompletegraficoRuaBarra.setAdapter(adapter);

        autoComplete = autoCompletegraficoRuaBarra.getAdapter().toString();




      autoCompletegraficoRuaBarra.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
          @Override
          public void onDismiss() {



              // opção para combinar com o spninner todos os anos

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto") && spinnerCorrente ==todosAnos){


                aeroportoTodosAnos();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alameda") && spinnerCorrente ==todosAnos) {


                  alameidaTodosAnos();


              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério") && spinnerCorrente ==todosAnos) {


                  altoCemitérioTodosAnos();


              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões") && spinnerCorrente ==todosAnos){


                  altoPocoesTodosAnos();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada") && spinnerCorrente ==todosAnos) {


                  alvoradaTodosAnos();


              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirante") && spinnerCorrente ==todosAnos) {


                  bandeirantesTodosAnos();


              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista") && spinnerCorrente ==todosAnos) {


                  belaVistaTodosAnos();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança") && spinnerCorrente ==todosAnos) {


                  boaEsperançaTodosAnos();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente ==todosAnos) {


                 boaVistaTodosAnos();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Brasilina") && spinnerCorrente ==todosAnos) {


                  brasilinaTodosAnos();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo") && spinnerCorrente ==todosAnos) {


                  brejoDoAmparoTodosAnos();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Caic") && spinnerCorrente ==todosAnos) {


                  caicTodosAnos();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Centro") && spinnerCorrente ==todosAnos) {


                  centroTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Cerâmica") && spinnerCorrente ==todosAnos) {


                  ceramicaTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente ==todosAnos) {


                 eldoradoTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente ==todosAnos) {


                fabiaoTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Franklin") && spinnerCorrente ==todosAnos) {


                 franklinTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jadete") && spinnerCorrente ==todosAnos) {


                  jadeteTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Januária") && spinnerCorrente ==todosAnos) {


                  januariaTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel") && spinnerCorrente ==todosAnos) {


                  jardimDanielTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estela") && spinnerCorrente ==todosAnos) {


                  jardimEstelaTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente ==todosAnos) {


                  jatobaTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente ==todosAnos) {


                  jussaraTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Levianopolis") && spinnerCorrente ==todosAnos) {


                levianopolisTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras") && spinnerCorrente ==todosAnos) {


                 mangueirasTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Margarete") && spinnerCorrente ==todosAnos) {


                  margareteTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras") && spinnerCorrente ==todosAnos) {


                moradeirasTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo") && spinnerCorrente ==todosAnos) {


                 normaConsueloTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio") && spinnerCorrente ==todosAnos) {


                  novoMilenioTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Quinta Mangabeiras") && spinnerCorrente ==todosAnos) {


                  quintaMangabeirasTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente ==todosAnos) {


                  riachoDaCruzTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("São João") && spinnerCorrente ==todosAnos) {


                  saoJoaoTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente ==todosAnos) {


                  saoJoaquimTodosAnos();

              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Miguel") && spinnerCorrente ==todosAnos) {


                  saoMiguelTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Miguel") && spinnerCorrente ==todosAnos) {


                  saoMiguelTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente") && spinnerCorrente ==todosAnos) {


                  saoVicenteTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família") && spinnerCorrente ==todosAnos) {


                  sagradaFamiliaTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz") && spinnerCorrente ==todosAnos) {


                  santaCruzTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel") && spinnerCorrente ==todosAnos) {


                  santaIsabelTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Sede") && spinnerCorrente ==todosAnos) {


                  sedeTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco") && spinnerCorrente ==todosAnos) {


                  tejucoTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio") && spinnerCorrente ==todosAnos) {


                  terceiroMilenioTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Tropical") && spinnerCorrente ==todosAnos) {


                  tropicalTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões") && spinnerCorrente ==todosAnos) {


                  varzeaDosPocoesTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fátima") && spinnerCorrente ==todosAnos) {


                  vilaFatimaTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros") && spinnerCorrente ==todosAnos) {


                  pandeirosTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula") && spinnerCorrente ==todosAnos) {


                  vilaPaulaTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde") && spinnerCorrente ==todosAnos) {


                  vilaVerdeTodosAnos();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana") && spinnerCorrente ==todosAnos) {


                  vilaVianaTodosAnos();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural") && spinnerCorrente ==todosAnos) {


                  zonaRuralTodosAnos();

              }








              // opção para combinar com o spninner 2018

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto") && spinnerCorrente ==ano2018){


                  aeroporto2018();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alameda") && spinnerCorrente ==ano2018) {


                  alameida2018();


              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério") && spinnerCorrente ==ano2018) {


                  altoCemitério2018();


              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões") && spinnerCorrente ==ano2018){


                  altoPocoes2018();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada") && spinnerCorrente ==ano2018) {


                  alvorada2018();


              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirante") && spinnerCorrente ==ano2018) {


                  bandeirantes2018();


              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista") && spinnerCorrente ==ano2018) {


                  belaVista2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança") && spinnerCorrente ==ano2018) {


                  boaEsperança2018();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente ==ano2018) {


                  boaVista2018();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Brasilina") && spinnerCorrente ==ano2018) {


                  brasilina2018();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo") && spinnerCorrente ==ano2018) {


                  brejoDoAmparo2018();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Caic") && spinnerCorrente ==ano2018) {


                  caic2018();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Centro") && spinnerCorrente ==ano2018) {


                  centro2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Cerâmica") && spinnerCorrente ==ano2018) {


                  ceramica2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente ==ano2018) {


                  eldorado2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente ==ano2018) {


                  fabiao2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Franklin") && spinnerCorrente ==ano2018) {


                  franklin2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jadete") && spinnerCorrente ==ano2018) {


                  jadete2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Januária") && spinnerCorrente ==ano2018) {


                  januaria2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel") && spinnerCorrente ==ano2018) {


                  jardimDaniel2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estela") && spinnerCorrente ==ano2018) {


                  jardimEstela2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente ==ano2018) {


                  jatoba2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente ==ano2018) {


                  jussara2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Levianopolis") && spinnerCorrente ==ano2018) {


                  levianopolis2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras") && spinnerCorrente ==ano2018) {


                  mangueiras2018();
              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Margarete") && spinnerCorrente ==ano2018) {


                  margarete2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras") && spinnerCorrente ==ano2018) {


                  moradeiras2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo") && spinnerCorrente ==ano2018) {


                  normaConsuelo2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio") && spinnerCorrente ==ano2018) {


                  novoMilenio2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Quinta Mangabeiras") && spinnerCorrente ==ano2018) {


                  quintaMangabeiras2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente ==ano2018) {


                  riachoDaCruz2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("São João") && spinnerCorrente ==ano2018) {


                  saoJoao2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente ==ano2018) {


                  saoJoaquim2018();

              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Miguel") && spinnerCorrente ==ano2018) {


                  saoMiguel2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente") && spinnerCorrente ==ano2018) {


                  saoVicente2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família") && spinnerCorrente ==ano2018) {


                  sagradaFamilia2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz") && spinnerCorrente ==ano2018) {


                  santaCruz2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel") && spinnerCorrente ==ano2018) {


                  santaIsabel2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Sede") && spinnerCorrente ==ano2018) {


                  sede2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco") && spinnerCorrente ==ano2018) {


                  tejuco2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio") && spinnerCorrente ==ano2018) {


                  terceiroMilenio2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Tropical") && spinnerCorrente ==ano2018) {


                  tropical2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões") && spinnerCorrente ==ano2018) {


                  varzeaDosPocoes2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fátima") && spinnerCorrente ==ano2018) {


                  vilaFatima2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros") && spinnerCorrente ==ano2018) {


                  pandeiros2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula") && spinnerCorrente ==ano2018) {


                  vilaPaula2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde") && spinnerCorrente ==ano2018) {


                  vilaVerde2018();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana") && spinnerCorrente ==ano2018) {


                  vilaViana2018();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural") && spinnerCorrente ==ano2018) {


                  zonaRural2018();

              }


                /// 2019




              // opção para combinar com o spninner 2019

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto") && spinnerCorrente ==ano2019){


                  aeroporto2019();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alameda") && spinnerCorrente ==ano2019) {


                  alameida2019();


              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério") && spinnerCorrente ==ano2019) {


                  altoCemitério2019();


              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões") && spinnerCorrente ==ano2019){


                  altoPocoes2019();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada") && spinnerCorrente ==ano2019) {


                  alvorada2019();


              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirante") && spinnerCorrente ==ano2019) {


                  bandeirantes2019();


              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista") && spinnerCorrente ==ano2019) {


                  belaVista2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança") && spinnerCorrente ==ano2019) {


                  boaEsperança2019();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente ==ano2019) {


                  boaVista2019();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Brasilina") && spinnerCorrente ==ano2019) {


                  brasilina2019();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo") && spinnerCorrente ==ano2019) {


                  brejoDoAmparo2019();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Caic") && spinnerCorrente ==ano2019) {


                  caic2019();


              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Centro") && spinnerCorrente ==ano2019) {


                  centro2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Cerâmica") && spinnerCorrente ==ano2019) {


                  ceramica2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente ==ano2019) {


                  eldorado2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente ==ano2019) {


                  fabiao2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Franklin") && spinnerCorrente ==ano2019) {


                  franklin2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jadete") && spinnerCorrente ==ano2019) {


                  jadete2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Januária") && spinnerCorrente ==ano2019) {


                  januaria2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel") && spinnerCorrente ==ano2019) {


                  jardimDaniel2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estela") && spinnerCorrente ==ano2019) {


                  jardimEstela2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente ==ano2019) {


                  jatoba2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente ==ano2019) {


                  jussara2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Levianopolis") && spinnerCorrente ==ano2019) {


                  levianopolis2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras") && spinnerCorrente ==ano2019) {


                  mangueiras2019();
              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Margarete") && spinnerCorrente ==ano2019) {


                  margarete2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras") && spinnerCorrente ==ano2019) {


                  moradeiras2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo") && spinnerCorrente ==ano2019) {


                  normaConsuelo2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio") && spinnerCorrente ==ano2019) {


                  novoMilenio2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Quinta Mangabeiras") && spinnerCorrente ==ano2019) {


                  quintaMangabeiras2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente ==ano2019) {


                  riachoDaCruz2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("São João") && spinnerCorrente ==ano2019) {


                  saoJoao2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente ==ano2019) {


                  saoJoaquim2019();

              }



              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Miguel") && spinnerCorrente ==ano2019) {


                  saoMiguel2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente") && spinnerCorrente ==ano2019) {


                  saoVicente2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família") && spinnerCorrente ==ano2019) {


                  sagradaFamilia2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz") && spinnerCorrente ==ano2019) {


                  santaCruz2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel") && spinnerCorrente ==ano2019) {


                  santaIsabel2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Sede") && spinnerCorrente ==ano2019) {


                  sede2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco") && spinnerCorrente ==ano2019) {


                  tejuco2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio") && spinnerCorrente ==ano2019) {


                  terceiroMilenio2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Tropical") && spinnerCorrente ==ano2019) {


                  tropical2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões") && spinnerCorrente ==ano2019) {


                  varzeaDosPocoes2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fátima") && spinnerCorrente ==ano2019) {


                  vilaFatima2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros") && spinnerCorrente ==ano2019) {


                  pandeiros2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula") && spinnerCorrente ==ano2019) {


                  vilaPaula2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde") && spinnerCorrente ==ano2019) {


                  vilaVerde2019();

              }

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana") && spinnerCorrente ==ano2019) {


                  vilaViana2019();

              }


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural") && spinnerCorrente ==ano2019) {


                  zonaRural2019();

              }









          }
      });











      //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoRuaBarraFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);




             spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                     if(position==0){

                         /////todos os anos e bairros
                         // posicion 0  todos os anos

                          String[] todosRuas  = new String[]{"Rua 7","Rua 10","Centro","Rua 12"};
                          int[]    rob = new int   []{1,1,1,1};
                           int []   cor   = new int   []{Color.DKGRAY,Color.RED};
                          String[] leg  = new String[]{"Furto","Roubo"};
                          int[]    furt = new int   []{5,10,6,10};


                         nomes   = todosRuas;
                         roubos =  rob;
                         cores   =  cor;
                         legenda  = leg;
                         furtos = furt;

                         spinnerCorrente = 0;

                         criarGraficos();



                         ///  opção spinner  todos
////                    /////////
                           if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto")){


                            aeroportoTodosAnos();


                             }

                             if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda")){

                                alameidaTodosAnos();

                             }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério")){


                            altoCemitérioTodosAnos();

                             }

                           if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões")){


                            altoPocoesTodosAnos();

                             }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada")){

                                 alvoradaTodosAnos();
                             }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirante")){


                             bandeirantesTodosAnos();

                            }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista")){


                             belaVistaTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança")){


                          boaEsperançaTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")){


                             boaVistaTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Brasilina")){


                       brasilinaTodosAnos();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo")){


                             brejoDoAmparoTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic")){


                             caicTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro")){


                            centroTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Cerâmica")){


                             ceramicaTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")){


                             eldoradoTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")){


                             fabiaoTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklin")){


                             franklinTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jadete")){


                             jadeteTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária")){


                             januariaTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel")){


                             jardimDanielTodosAnos();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estela")){


                             jardimEstelaTodosAnos();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá")){


                             jatobaTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Joventina Mesquita")){


                             joventinaMesquitaTodosAnos();

                         }




                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara")){


                             jussaraTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Levianopolis")){


                             levianopolisTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras")){

                               mangueirasTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete")){

                             margareteTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras")){

                             moradeirasTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo")){

                             normaConsueloTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio")){

                             novoMilenioTodosAnos();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Quinta Mangabeiras")){

                            quintaMangabeirasTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")){

                             riachoDaCruzTodosAnos();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São João")){

                             saoJoaoTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")){

                             saoJoaquimTodosAnos();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Miguel")){

                             saoMiguelTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente")){

                             saoVicenteTodosAnos();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família")) {

                               sagradaFamiliaTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz")) {

                             santaCruzTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel")) {

                             santaIsabelTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Sede")) {

                             sedeTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco")) {

                             tejucoTodosAnos();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio")) {

                             terceiroMilenioTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Tropical")) {

                             tropicalTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões")) {

                            varzeaDosPocoesTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fátima")) {

                             vilaFatimaTodosAnos();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros")) {


                               pandeirosTodosAnos();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula")) {


                             vilaPaulaTodosAnos();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde")) {


                             vilaVerdeTodosAnos();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana")) {


                             vilaVianaTodosAnos();
                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural")) {


                            zonaRuralTodosAnos();
                         }


                     } else if(position == 1){
                         // opção spinner 2018


                         String[] bairros  = new String[]{"Rua 7","Rua 10","spineer 2018","Rua 12"};
                         int[]    rob = new int   []{10,15,14,20};
                         int []   cor   = new int   []{Color.DKGRAY,Color.RED};
                         String[] leg  = new String[]{"Furto","Roubo"};
                         int[]    furt = new int   []{5,10,6,10};



                         nomes   = bairros;
                         roubos =  rob;
                         cores   =  cor;
                         legenda  = leg;
                         furtos = furt;

                         spinnerCorrente =1;

                         criarGraficos();



                         // opção spinner 2018

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto")){


                             aeroporto2018();


                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda")){

                             alameida2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério")){


                             altoCemitério2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões")){


                             altoPocoes2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada")){

                             alvorada2018();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirante")){


                             bandeirantes2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista")){


                             belaVista2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança")){


                             boaEsperança2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")){


                             boaVista2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Brasilina")){


                             brasilina2018();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo")){


                             brejoDoAmparo2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic")){


                             caic2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro")){


                             centro2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Cerâmica")){


                             ceramica2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")){


                             eldorado2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")){


                             fabiao2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklin")){


                             franklin2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jadete")){


                             jadete2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária")){


                             januaria2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel")){


                             jardimDaniel2018();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estela")){


                             jardimEstela2018();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá")){


                             jatoba2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Joventina Mesquita")){


                             joventinaMesquita2018();

                         }




                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara")){


                             jussara2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Levianopolis")){


                             levianopolis2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras")){

                             mangueiras2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete")){

                             margarete2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras")){

                             moradeiras2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo")){

                             normaConsuelo2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio")){

                             novoMilenio2018();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Quinta Mangabeiras")){

                             quintaMangabeiras2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")){

                             riachoDaCruz2018();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São João")){

                             saoJoao2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")){

                             saoJoaquim2018();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Miguel")){

                             saoMiguel2018();


                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente")){

                             saoVicente2018();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família")) {

                             sagradaFamilia2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz")) {

                             santaCruz2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel")) {

                             santaIsabel2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Sede")) {

                             sede2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco")) {

                             tejuco2018();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio")) {

                             terceiroMilenio2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Tropical")) {

                             tropical2018();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões")) {

                             varzeaDosPocoes2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fátima")) {

                             vilaFatima2018();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros")) {


                             pandeiros2018();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula")) {


                             vilaPaula2018();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde")) {


                             vilaVerde2018();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana")) {


                             vilaViana2018();
                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural")) {


                             zonaRural2018();
                         }



                     }else if (position==2){  //2019



                         String[] bairros  = new String[]{"Rua 7","Rua 10","Alvorada","Rua 12"};
                         int[]    rob = new int   []{10,15,14,20};
                         int []   cor   = new int   []{Color.DKGRAY,Color.RED};
                         String[] leg  = new String[]{"Furto","Roubo"};
                         int[]    furt = new int   []{5,10,6,10};



                         nomes   = bairros;
                         roubos =  rob;
                         cores   =  cor;
                         legenda  = leg;
                         furtos = furt;

                         spinnerCorrente = 2;

                         criarGraficos();







                         // opção spinner 2019

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto")){


                             aeroporto2019();


                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda")){

                             alameida2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério")){


                             altoCemitério2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões")){


                             altoPocoes2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada")){

                             alvorada2019();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirante")){


                             bandeirantes2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista")){


                             belaVista2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança")){


                             boaEsperança2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")){


                             boaVista2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Brasilina")){


                             brasilina2019();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo")){


                             brejoDoAmparo2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic")){


                             caic2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro")){


                             centro2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Cerâmica")){


                             ceramica2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")){


                             eldorado2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")){


                             fabiao2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklin")){


                             franklin2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jadete")){


                             jadete2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária")){


                             januaria2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel")){


                             jardimDaniel2019();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estela")){


                             jardimEstela2019();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá")){


                             jatoba2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Joventina Mesquita")){


                             joventinaMesquita2019();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara")){


                             jussara2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Levianopolis")){


                             levianopolis2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras")){

                             mangueiras2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete")){

                             margarete2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras")){

                             moradeiras2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo")){

                             normaConsuelo2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio")){

                             novoMilenio2019();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Quinta Mangabeiras")){

                             quintaMangabeiras2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")){

                             riachoDaCruz2019();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São João")){

                             saoJoao2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")){

                             saoJoaquim2019();

                         }



                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Miguel")){

                             saoMiguel2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente")){

                             saoVicente2019();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família")) {

                             sagradaFamilia2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz")) {

                             santaCruz2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel")) {

                             santaIsabel2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Sede")) {

                             sede2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco")) {

                             tejuco2019();

                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio")) {

                             terceiroMilenio2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Tropical")) {

                             tropical2019();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões")) {

                             varzeaDosPocoes2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fátima")) {

                             vilaFatima2019();

                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros")) {


                             pandeiros2019();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula")) {


                             vilaPaula2019();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde")) {


                             vilaVerde2019();
                         }


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana")) {


                             vilaViana2019();
                         }

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural")) {


                             zonaRural2019();
                         }






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

                transaction.replace(R.id.conteinerFragmentos,new GraficoRuaBarraGeralFragment()).commit();



                return false;
            }
        });










        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();




        query = databaseReference.child("TodasBikes").orderByChild("numero_serie");


        //  query = databaseReference.child("TodasBikes")
        //  .orderByChild("numero_serie").startAt(palavra).endAt(palavra+"\uf8ff");



        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                listBikes.clear();  //limpa lista

                // verifica itens da lista

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Bike b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);



                    String procuraAno = b.getAlertaDate();
                    String procuraBairro = b.getAlertaBairro();
                    String procuraRua = b.getAlertaRua();





                    // todos os anos
                    if (b.getAlertaRua().equals("Rua Sete")){

                        AeroportoRuaTal++;

                        Toast.makeText(GraficoRuaBarraFragment.super.getContext(), "Rua sete" +AeroportoRuaTal, Toast.LENGTH_LONG).show();

                    }


                    if (procuraBairro.contains("Aeoroporto") && b.getAlertaRua().equals("teco teco")){

                        contandoBikesAno2018++;
                        //Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "" + texto.toLowerCase().contains(procurarPor.toLowerCase()), Toast.LENGTH_LONG).show();


                    }


                    if (procuraBairro.contains("Aeoroporto") && b.getAlertaRua().equals("tico tico")){

                        contandoBikesAno2018++;
                        //Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "" + texto.toLowerCase().contains(procurarPor.toLowerCase()), Toast.LENGTH_LONG).show();


                    }


                    ///apenas 2019
                    if (procuraBairro.contains("2019") && b.getStatus().equals("Furtada")){

                        contandoBikesAno2018++;
                        //Toast.makeText(GraficoAnoBarraFragment.super.getContext(), "" + texto.toLowerCase().contains(procurarPor.toLowerCase()), Toast.LENGTH_LONG).show();


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

        FirebaseApp.initializeApp(GraficoRuaBarraFragment.super.getContext());
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

        // axisRight(barChart.getAxisRight());


        barChart.getLegend().setEnabled(true);

        data.setBarWidth(0.45f);



        barChart.invalidate();


    }


    public void aeroportoTodosAnos(){


        String[] bairros  = new String[]{"RUA 7","spinnerCorrente 10","Aeroporto","Rua 12","RUA 7","spinnerCorrente 10","Aeroporto","Rua 12"};
        int[]    rob = new int   []{AeroportoRuaTal,12,10,12,10,12,10,12};
        int []   cor   = new int   []{Color.DKGRAY,Color.RED};
        String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{1,10,6,10,12,10,12,10,12};


        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;


        criarGraficos();

    }


    /// opção todos os bairros no spinner

    public void alameidaTodosAnos(){

        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};
        int[] corAlameda = new int[]{Color.DKGRAY, Color.RED};
        String[] legAlameda = new String[]{"Furto", "Roubo"};
        int[] furtAlameda = new int[]{5, 10, 6, 10};


        nomes = alameda;
        roubos = robAlameda;
        cores = corAlameda;
        legenda = legAlameda;
        furtos = furtAlameda;


        criarGraficos();



    }


    public void altoCemitérioTodosAnos(){

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério", "Rua 12"};
        int[] robAltoCemitério = new int[]{10, 10, 10, 10};
        int[] corAltoCemitério = new int[]{Color.DKGRAY, Color.RED};
        String[] legAltoCemitério = new String[]{"Furto", "Roubo"};
        int[] furtAltoCemitério = new int[]{5, 10, 6, 10};


        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores = corAltoCemitério;
        legenda = legAltoCemitério;
        furtos = furtAltoCemitério;


        criarGraficos();

    }


    public void altoPocoesTodosAnos(){

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Alto dos Pocões","Rua 12"};
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

    public void alvoradaTodosAnos(){

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Alvorada","Rua 12"};
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


    public void bandeirantesTodosAnos(){


        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Bandeirante","Rua 12"};
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

    private void belaVistaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista","Rua 12"};
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





    private void boaEsperançaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Boa Esperança","Rua 12"};
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


    private void boaVistaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Boa Vista","Rua 12"};
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


    private void brasilinaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Brasilina","Rua 12"};
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

    private void brejoDoAmparoTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Brejo do Amparo","Rua 12"};
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






    private void caicTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic","Rua 12"};
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




    private void centroTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Centro","Rua 12"};
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




    private void ceramicaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Cerâmica","Rua 12"};
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





    private void eldoradoTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Eldorado","Rua 12"};
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



    private void fabiaoTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião","Rua 12"};
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

    private void franklinTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Franklin","Rua 12"};
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



    private void jadeteTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jadete","Rua 12"};
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



    private void januariaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária","Rua 12"};
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



    private void jardimDanielTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jardim Daniel","Rua 12"};
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



    private void jardimEstelaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jardim Estela","Rua 12"};
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


    private void jatobaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá","Rua 12"};
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


    private void joventinaMesquitaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita","Rua 12"};
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


    private void jussaraTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jussara","Rua 12"};
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


    private void levianopolisTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Levianopolis","Rua 12"};
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

    private void mangueirasTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras","Rua 12"};
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


    private void margareteTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete","Rua 12"};
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



    private void moradeirasTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras","Rua 12"};
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


    private void normaConsueloTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo","Rua 12"};
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



    private void novoMilenioTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio","Rua 12"};
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


    private void quintaMangabeirasTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Quinta Mangabeiras","Rua 12"};
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


    private void riachoDaCruzTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Riacho da Cruz","Rua 12"};
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

    private void saoJoaoTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São João","Rua 12"};
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


    private void saoJoaquimTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Joaquim","Rua 12"};
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

    private void saoMiguelTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Miguel","Rua 12"};
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


    private void saoVicenteTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Vicente","Rua 12"};
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


    private void sagradaFamiliaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sagrada Família","Rua 12"};
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


    private void santaCruzTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Cruz","Rua 12"};
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



    private void santaIsabelTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Isabel","Rua 12"};
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


    private void sedeTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sede","Rua 12"};
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

    private void tejucoTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tejuco","Rua 12"};
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


    private void terceiroMilenioTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Terceiro Milênio","Rua 12"};
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


    private void tropicalTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tropical","Rua 12"};
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


    private void varzeaDosPocoesTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Várzea dos Pocões","Rua 12"};
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


    private void vilaFatimaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Fátima","Rua 12"};
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

    private void pandeirosTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Pandeiros","Rua 12"};
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

    private void vilaPaulaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Paula","Rua 12"};
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

    private void vilaVerdeTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Verde","Rua 12"};
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


    private void vilaVianaTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Viana","Rua 12"};
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

    private void zonaRuralTodosAnos() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Zona Rural","Rua 12"};
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

    //


    //

    //

      /// opção 2018 no spinnner



    public void aeroporto2018(){


        String[] bairros  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Aeroporto2018","Rua 12"};
        int[]    rob = new int   []{12,12,10,12};
        int []   cor   = new int   []{Color.DKGRAY,Color.RED};
        String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{5,10,6,10};


        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;


        criarGraficos();

    }


    public void alameida2018(){

        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda2018", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};
        int[] corAlameda = new int[]{Color.DKGRAY, Color.RED};
        String[] legAlameda = new String[]{"Furto", "Roubo"};
        int[] furtAlameda = new int[]{5, 10, 6, 10};


        nomes = alameda;
        roubos = robAlameda;
        cores = corAlameda;
        legenda = legAlameda;
        furtos = furtAlameda;


        criarGraficos();



    }


    public void altoCemitério2018(){

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério2018", "Rua 12"};
        int[] robAltoCemitério = new int[]{10, 10, 10, 10};
        int[] corAltoCemitério = new int[]{Color.DKGRAY, Color.RED};
        String[] legAltoCemitério = new String[]{"Furto", "Roubo"};
        int[] furtAltoCemitério = new int[]{5, 10, 6, 10};


        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores = corAltoCemitério;
        legenda = legAltoCemitério;
        furtos = furtAltoCemitério;


        criarGraficos();

    }


    public void altoPocoes2018(){

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Alto dos Pocões2018","Rua 12"};
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

    public void alvorada2018(){

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Alvorada2018","Rua 12"};
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


    public void bandeirantes2018(){


        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Bandeirante2018","Rua 12"};
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

    private void belaVista2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista2018","Rua 12"};
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





    private void boaEsperança2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Boa Esperança2018","Rua 12"};
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


    private void boaVista2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Boa Vista2018","Rua 12"};
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


    private void brasilina2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Brasilina2018","Rua 12"};
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

    private void brejoDoAmparo2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Brejo do Amparo2018","Rua 12"};
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






    private void caic2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic2018","Rua 12"};
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




    private void centro2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Centro2018","Rua 12"};
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




    private void ceramica2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Cerâmica2018","Rua 12"};
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





    private void eldorado2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Eldorado2018","Rua 12"};
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



    private void fabiao2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião2018","Rua 12"};
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

    private void franklin2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Franklin2018","Rua 12"};
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



    private void jadete2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jadete2018","Rua 12"};
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



    private void januaria2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária2018","Rua 12"};
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



    private void jardimDaniel2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jardim Daniel2018","Rua 12"};
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



    private void jardimEstela2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jardim Estela2018","Rua 12"};
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


    private void jatoba2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá2018","Rua 12"};
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


    private void joventinaMesquita2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita2018","Rua 12"};
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


    private void jussara2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jussara2018","Rua 12"};
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


    private void levianopolis2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Levianopolis2018","Rua 12"};
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

    private void mangueiras2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras2018","Rua 12"};
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


    private void margarete2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete2018","Rua 12"};
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



    private void moradeiras2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras2018","Rua 12"};
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


    private void normaConsuelo2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo2018","Rua 12"};
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



    private void novoMilenio2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio2018","Rua 12"};
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


    private void quintaMangabeiras2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Quinta Mangabeiras2018","Rua 12"};
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


    private void riachoDaCruz2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Riacho da Cruz2018","Rua 12"};
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

    private void saoJoao2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São João2018","Rua 12"};
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


    private void saoJoaquim2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Joaquim2018","Rua 12"};
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

    private void saoMiguel2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Miguel2018","Rua 12"};
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


    private void saoVicente2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Vicente2018","Rua 12"};
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


    private void sagradaFamilia2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sagrada Família2018","Rua 12"};
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


    private void santaCruz2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Cruz2018","Rua 12"};
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



    private void santaIsabel2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Isabel2018","Rua 12"};
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


    private void sede2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sede2018","Rua 12"};
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

    private void tejuco2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tejuco2018","Rua 12"};
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


    private void terceiroMilenio2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Terceiro Milênio2018","Rua 12"};
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


    private void tropical2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tropical2018","Rua 12"};
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


    private void varzeaDosPocoes2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Várzea dos Pocões2018","Rua 12"};
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


    private void vilaFatima2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Fátima2018","Rua 12"};
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

    private void pandeiros2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Pandeiros2018","Rua 12"};
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

    private void vilaPaula2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Paula2018","Rua 12"};
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

    private void vilaVerde2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Verde2018","Rua 12"};
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


    private void vilaViana2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Viana2018","Rua 12"};
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

    private void zonaRural2018() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Zona Rural2018","Rua 12"};
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



    ///

    ///2019


    /// opção 2019 no spinnner



    public void aeroporto2019(){


        String[] bairros  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Aeroporto2019","Rua 12"};
        int[]    rob = new int   []{12,12,10,12};
        int []   cor   = new int   []{Color.DKGRAY,Color.RED};
        String[] leg  = new String[]{"Furto","Roubo"};
        int[]    furt = new int   []{5,10,6,10};


        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = leg;
        furtos = furt;


        criarGraficos();

    }


    public void alameida2019(){

        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda2019", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};
        int[] corAlameda = new int[]{Color.DKGRAY, Color.RED};
        String[] legAlameda = new String[]{"Furto", "Roubo"};
        int[] furtAlameda = new int[]{5, 10, 6, 10};


        nomes = alameda;
        roubos = robAlameda;
        cores = corAlameda;
        legenda = legAlameda;
        furtos = furtAlameda;


        criarGraficos();



    }


    public void altoCemitério2019(){

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério2019", "Rua 12"};
        int[] robAltoCemitério = new int[]{10, 10, 10, 10};
        int[] corAltoCemitério = new int[]{Color.DKGRAY, Color.RED};
        String[] legAltoCemitério = new String[]{"Furto", "Roubo"};
        int[] furtAltoCemitério = new int[]{5, 10, 6, 10};


        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores = corAltoCemitério;
        legenda = legAltoCemitério;
        furtos = furtAltoCemitério;


        criarGraficos();

    }


    public void altoPocoes2019(){

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Alto dos Pocões2019","Rua 12"};
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

    public void alvorada2019(){

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Alvorada2019","Rua 12"};
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


    public void bandeirantes2019(){


        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Bandeirante2019","Rua 12"};
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

    private void belaVista2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista2019","Rua 12"};
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





    private void boaEsperança2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Boa Esperança2019","Rua 12"};
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


    private void boaVista2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Boa Vista2019","Rua 12"};
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


    private void brasilina2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Brasilina2019","Rua 12"};
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

    private void brejoDoAmparo2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Brejo do Amparo2019","Rua 12"};
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






    private void caic2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic2019","Rua 12"};
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




    private void centro2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Centro2019","Rua 12"};
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




    private void ceramica2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Cerâmica2019","Rua 12"};
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





    private void eldorado2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Eldorado2019","Rua 12"};
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



    private void fabiao2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião2019","Rua 12"};
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

    private void franklin2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Franklin2019","Rua 12"};
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



    private void jadete2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jadete2019","Rua 12"};
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



    private void januaria2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária2019","Rua 12"};
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



    private void jardimDaniel2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jardim Daniel2019","Rua 12"};
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



    private void jardimEstela2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jardim Estela2019","Rua 12"};
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


    private void jatoba2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá2019","Rua 12"};
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


    private void joventinaMesquita2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita2019","Rua 12"};
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


    private void jussara2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jussara2019","Rua 12"};
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


    private void levianopolis2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Levianopolis2019","Rua 12"};
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

    private void mangueiras2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras2019","Rua 12"};
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


    private void margarete2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete2019","Rua 12"};
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



    private void moradeiras2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras2019","Rua 12"};
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


    private void normaConsuelo2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo2019","Rua 12"};
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



    private void novoMilenio2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio2019","Rua 12"};
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


    private void quintaMangabeiras2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Quinta Mangabeiras2019","Rua 12"};
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


    private void riachoDaCruz2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Riacho da Cruz2019","Rua 12"};
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

    private void saoJoao2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São João2019","Rua 12"};
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


    private void saoJoaquim2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Joaquim2019","Rua 12"};
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

    private void saoMiguel2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Miguel2019","Rua 12"};
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


    private void saoVicente2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","São Vicente2019","Rua 12"};
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


    private void sagradaFamilia2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sagrada Família2019","Rua 12"};
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


    private void santaCruz2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Cruz2019","Rua 12"};
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



    private void santaIsabel2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Isabel2019","Rua 12"};
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


    private void sede2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sede2019","Rua 12"};
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

    private void tejuco2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tejuco2019","Rua 12"};
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


    private void terceiroMilenio2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Terceiro Milênio2019","Rua 12"};
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


    private void tropical2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tropical2019","Rua 12"};
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


    private void varzeaDosPocoes2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Várzea dos Pocões2019","Rua 12"};
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


    private void vilaFatima2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Fátima2019","Rua 12"};
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

    private void pandeiros2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Pandeiros2019","Rua 12"};
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

    private void vilaPaula2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Paula2019","Rua 12"};
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

    private void vilaVerde2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Verde2019","Rua 12"};
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


    private void vilaViana2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Vila Viana2019","Rua 12"};
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

    private void zonaRural2019() {

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Zona Rural2019","Rua 12"};
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
