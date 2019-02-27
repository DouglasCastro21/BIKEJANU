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
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.CadastroUsuario;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;


public class GraficoHorarioBarraFragment extends Fragment {

    String anoProcura2019 = "2019";



    int spinnerCorrente = 0;
    int todosAnos = 0;
    int ano2018 = 1;
    int ano2019 = 2;


    String autoComplete;
    Bike b;
    String procuraBairro;
    String procuraAno;


    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[]{"Todos", "2018", "2019"};
    private Spinner spinner;


    private BarChart barChart;

    private String[] nomes = new String[]{};
    private int[] roubos = new int[]{};
    private int[] cores = new int[]{};
    private String[] legenda = new String[]{};
    private int[] furtos = new int[]{};

    String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
    int[] cor = new int[]{Color.YELLOW, Color.RED};  // ALTERAR a cor da legenda aq
    String[] legendaGrafico = new String[]{"Furto", "Roubo"};


    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;



    int madrugada = 0;
    int manha = 0;
    int tarde = 0;
    int noite = 0;








    //   public static List<String> l  axis.setPosition(XAxis.XAxisPosition.BOTTOM);istBairros = new ArrayList<String>();
    // public static ArrayAdapter<String> arrayAdapterBairro;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_horario_barra, container, false);

        barChart = (BarChart) rootView.findViewById(R.id.graficoHorarioBarra);

        inicializarFirebase();

        String[] countries = getResources().getStringArray(R.array.countries);


        //carrega os spinner
        final AutoCompleteTextView autoCompletegraficoRuaBarra = rootView.findViewById(R.id.autocompletegraficoruabarrageral);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GraficoHorarioBarraFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item, countries);

        autoCompletegraficoRuaBarra.setAdapter(adapter);
        autoComplete = autoCompletegraficoRuaBarra.getAdapter().toString();

        spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
        spinner.setAdapter(adapter);


        final android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();


        Query query;
        //Instânciar objetos
        listBikes = new ArrayList<>();
        query = databaseReference.child("TodasBikes");
        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                listBikes.clear();  //limpa lista

                // verifica itens da lista

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);


                    procuraAno = b.getAlertaDate();
                    procuraBairro = b.getAlertaBairro();
                    String procuraRua = b.getAlertaRua();

                    //---------------------------------------------------------------------------------------



                    String hora = b.getAlertaHora();
                    String h = hora.replace(":","");

                    int  horaDaOcorrencia=0;
                    if(!h.equals("")) {
                        horaDaOcorrencia= Integer.parseInt(h);
                    }
                    int   tardeMin =  1200;
                    int   tardeMax =  1800;
                    //SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
                    if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia < tardeMax ){
                        tarde++;
                        Toast.makeText(GraficoHorarioBarraFragment.super.getContext(), "Tarde "+tarde, Toast.LENGTH_LONG).show();
                    }


                 //---------------------------------------------------------------------------------------


                    //procura as ruas ...

                    // todos os anos


                    ruasBairroAeroportoTodosAnos();
                    ruasBairroJadeteTodosAnos();
                    ruasBairroAltoPocoesTodosAnos();
                    ruasBairroAlvoradaTodosAnos();
                    ruasBairroBandeirantesTodosAnos();
                    ruasBairroBoaEsperancaTodosAnos();
                    ruasBairroBoaVistaTodosAnos();
                    ruasBairroVilaBrasilandiaTodosAnos();
                    ruasBairroBrejoAmparoTodosAnos();
                    ruasBairroCentroTodosAnos();
                    ruasBairroCeramicaTodosAnos();
                    ruasBairroEldoradoTodosAnos();
                    ruasBairroFranklimTodosAnos();
                    ruasBairroJardimDanielTodosAnos();
                    ruasBairroJardimEstrelaTodosAnos();
                    ruasBairroJussaraTodosAnos();
                    ruasBairroVilaLevianopolisTodosAnos();
                    ruasBairroQuintasMangueirasTodosAnos();
                    ruasBairroRiachoCruzTodosAnos();
                    ruasBairroVilaSaoJoaoTodosAnos();
                    ruasBairroSaoMiguelTodosAnos();
                    ruasBairroSaoVicenteTodosAnos();
                    ruasBairroTejucoTodosAnos();
                    ruasBairroVilaFatimaTodosAnos();
                    ruasBairroPandeirosTodosAnos();
                    ruasBairroVilaPaulaTodosAnos();
                    ruasBairroVilaVerdeTodosAnos();
                    ruasBairroVilaVianaTodosAnos();


                    ruasBairroAeroporto2019();
                    ruasBairroJadete2019();
                    ruasBairroAltoPocoes2019();
                    ruasBairroAlvorada2019();
                    ruasBairroBandeirantes2019();
                    ruasBairroBoaEsperanca2019();
                    ruasBairroBoaVista2019();
                    ruasBairroVilaBrasilandia2019();
                    ruasBairroBrejoAmparo2019();
                    ruasBairroCentro2019();
                    ruasBairroCeramica2019();
                    ruasBairroEldorado2019();
                    ruasBairroFranklim2019();
                    ruasBairroJardimDaniel2019();
                    ruasBairroJardimEstrela2019();
                    ruasBairroJussara2019();
                    ruasBairroVilaLevianopolis2019();
                    ruasBairroQuintasMangueiras2019();
                    ruasBairroRiachoCruz2019();
                    ruasBairroVilaSaoJoao2019();
                    ruasBairroSaoJoaquimTodosAnos();
                    ruasBairroSaoJoaquim2019();
                    ruasBairroSaoMiguel2019();
                    ruasBairroSaoVicente2019();
                    ruasBairroTejuco2019();
                    ruasBairroVilaFatima2019();
                    ruasBairroPandeiros2019();
                    ruasBairroVilaPaula2019();
                    ruasBairroVilaVerde2019();
                    ruasBairroVilaViana2019();


                    autoCompletegraficoRuaBarra.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                        @Override
                        public void onDismiss() {


                            // opção para combinar com o spninner todos os anos

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto") && spinnerCorrente == todosAnos) {


                                aeroportoTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda") && spinnerCorrente == todosAnos) {


                                alameidaTodosAnos();


                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério") && spinnerCorrente == todosAnos) {


                                altoCemiterioTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões") && spinnerCorrente == todosAnos) {


                                altoPocoesTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada") && spinnerCorrente == todosAnos) {


                                alvoradaTodosAnos();


                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes") && spinnerCorrente == todosAnos) {


                                bandeirantesTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista") && spinnerCorrente == todosAnos) {


                                belaVistaTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança") && spinnerCorrente == todosAnos) {


                                boaEsperancaTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente == todosAnos) {


                                boaVistaTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia") && spinnerCorrente == todosAnos) {


                                brasilandiaTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo") && spinnerCorrente == todosAnos) {


                                brejoDoAmparoTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic") && spinnerCorrente == todosAnos) {


                                caicTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro") && spinnerCorrente == todosAnos) {


                                centroTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica") && spinnerCorrente == todosAnos) {


                                ceramicaTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente == todosAnos) {


                                eldoradoTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente == todosAnos) {


                                fabiaoTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim") && spinnerCorrente == todosAnos) {


                                franklimTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete") && spinnerCorrente == todosAnos) {


                                jadeteTodosAnos();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária") && spinnerCorrente == todosAnos) {


                                januariaTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel") && spinnerCorrente == todosAnos) {


                                jardimDanielTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela") && spinnerCorrente == todosAnos) {


                                jardimEstelaTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente == todosAnos) {


                                jatobaTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente == todosAnos) {


                                jussaraTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis") && spinnerCorrente == todosAnos) {


                                levianopolisTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras") && spinnerCorrente == todosAnos) {


                                mangueirasTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete") && spinnerCorrente == todosAnos) {


                                margareteTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras") && spinnerCorrente == todosAnos) {


                                moradeirasTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo") && spinnerCorrente == todosAnos) {


                                normaConsueloTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio") && spinnerCorrente == todosAnos) {


                                novoMilenioTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras") && spinnerCorrente == todosAnos) {


                                quintaMangabeirasTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente == todosAnos) {


                                riachoDaCruzTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao") && spinnerCorrente == todosAnos) {


                                saoJoaoTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente == todosAnos) {


                                saoJoaquimTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel") && spinnerCorrente == todosAnos) {


                                saoMiguelTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("São Miguel") && spinnerCorrente == todosAnos) {


                                saoMiguelTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente") && spinnerCorrente == todosAnos) {


                                saoVicenteTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família") && spinnerCorrente == todosAnos) {


                                sagradaFamiliaTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz") && spinnerCorrente == todosAnos) {


                                santaCruzTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel") && spinnerCorrente == todosAnos) {


                                santaIsabelTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Sede") && spinnerCorrente == todosAnos) {


                                sedeTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco") && spinnerCorrente == todosAnos) {


                                tejucoTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio") && spinnerCorrente == todosAnos) {


                                terceiroMilenioTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Tropical") && spinnerCorrente == todosAnos) {


                                tropicalTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões") && spinnerCorrente == todosAnos) {


                                varzeaDosPocoesTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima") && spinnerCorrente == todosAnos) {


                                vilaFatimaTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros") && spinnerCorrente == todosAnos) {


                                pandeirosTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula") && spinnerCorrente == todosAnos) {


                                vilaPaulaTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde") && spinnerCorrente == todosAnos) {


                                vilaVerdeTodosAnos();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana") && spinnerCorrente == todosAnos) {


                                vilaVianaTodosAnos();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural") && spinnerCorrente == todosAnos) {


                                zonaRuralTodosAnos();

                            }


                            // opção para combinar com o spninner 2018

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto") && spinnerCorrente == ano2018) {


                                aeroporto2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda") && spinnerCorrente == ano2018) {


                                alameida2018();


                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério") && spinnerCorrente == ano2018) {


                                altoCemiterio2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões") && spinnerCorrente == ano2018) {


                                altoPocoes2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada") && spinnerCorrente == ano2018) {


                                alvorada2018();


                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes") && spinnerCorrente == ano2018) {


                                bandeirantes2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista") && spinnerCorrente == ano2018) {


                                belaVista2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança") && spinnerCorrente == ano2018) {


                                boaEsperanca2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente == ano2018) {


                                boaVista2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia") && spinnerCorrente == ano2018) {


                                brasilandia2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo") && spinnerCorrente == ano2018) {


                                brejoDoAmparo2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic") && spinnerCorrente == ano2018) {


                                caic2018();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro") && spinnerCorrente == ano2018) {


                                centro2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica") && spinnerCorrente == ano2018) {


                                ceramica2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente == ano2018) {


                                eldorado2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente == ano2018) {


                                fabiao2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim") && spinnerCorrente == ano2018) {


                                franklim2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete") && spinnerCorrente == ano2018) {


                                jadete2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária") && spinnerCorrente == ano2018) {


                                januaria2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel") && spinnerCorrente == ano2018) {


                                jardimDaniel2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela") && spinnerCorrente == ano2018) {


                                jardimEstela2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente == ano2018) {


                                jatoba2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente == ano2018) {


                                jussara2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis") && spinnerCorrente == ano2018) {


                                levianopolis2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras") && spinnerCorrente == ano2018) {


                                mangueiras2018();
                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete") && spinnerCorrente == ano2018) {


                                margarete2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras") && spinnerCorrente == ano2018) {


                                moradeiras2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo") && spinnerCorrente == ano2018) {


                                normaConsuelo2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio") && spinnerCorrente == ano2018) {


                                novoMilenio2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras") && spinnerCorrente == ano2018) {


                                quintaMangabeiras2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente == ano2018) {


                                riachoDaCruz2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao") && spinnerCorrente == ano2018) {


                                saoJoao2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente == ano2018) {


                                saoJoaquim2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel") && spinnerCorrente == ano2018) {


                                saoMiguel2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente") && spinnerCorrente == ano2018) {


                                saoVicente2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família") && spinnerCorrente == ano2018) {


                                sagradaFamilia2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz") && spinnerCorrente == ano2018) {


                                santaCruz2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel") && spinnerCorrente == ano2018) {


                                santaIsabel2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Sede") && spinnerCorrente == ano2018) {


                                sede2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco") && spinnerCorrente == ano2018) {


                                tejuco2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio") && spinnerCorrente == ano2018) {


                                terceiroMilenio2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Tropical") && spinnerCorrente == ano2018) {


                                tropical2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões") && spinnerCorrente == ano2018) {


                                varzeaDosPocoes2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima") && spinnerCorrente == ano2018) {


                                vilaFatima2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros") && spinnerCorrente == ano2018) {


                                pandeiros2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula") && spinnerCorrente == ano2018) {


                                vilaPaula2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde") && spinnerCorrente == ano2018) {


                                vilaVerde2018();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana") && spinnerCorrente == ano2018) {


                                vilaViana2018();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural") && spinnerCorrente == ano2018) {


                                zonaRural2018();

                            }


                            /// 2019


                            // opção para combinar com o spninner 2019

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto") && spinnerCorrente == ano2019) {


                                aeroporto2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda") && spinnerCorrente == ano2019) {


                                alameida2019();


                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério") && spinnerCorrente == ano2019) {


                                altoCemiterio2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões") && spinnerCorrente == ano2019) {


                                altoPocoes2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada") && spinnerCorrente == ano2019) {


                                alvorada2019();


                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes") && spinnerCorrente == ano2019) {


                                bandeirantes2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista") && spinnerCorrente == ano2019) {


                                belaVista2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança") && spinnerCorrente == ano2019) {


                                boaEsperanca2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente == ano2019) {


                                boaVista2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia") && spinnerCorrente == ano2019) {


                                brasilandia2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo") && spinnerCorrente == ano2019) {


                                brejoDoAmparo2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic") && spinnerCorrente == ano2019) {


                                caic2019();


                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro") && spinnerCorrente == ano2019) {


                                centro2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica") && spinnerCorrente == ano2019) {


                                ceramica2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente == ano2019) {


                                eldorado2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente == ano2019) {


                                fabiao2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim") && spinnerCorrente == ano2019) {


                                franklim2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete") && spinnerCorrente == ano2019) {


                                jadete2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária") && spinnerCorrente == ano2019) {


                                januaria2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel") && spinnerCorrente == ano2019) {


                                jardimDaniel2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela") && spinnerCorrente == ano2019) {


                                jardimEstela2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente == ano2019) {


                                jatoba2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente == ano2019) {


                                jussara2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis") && spinnerCorrente == ano2019) {


                                levianopolis2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras") && spinnerCorrente == ano2019) {


                                mangueiras2019();
                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete") && spinnerCorrente == ano2019) {


                                margarete2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras") && spinnerCorrente == ano2019) {


                                moradeiras2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo") && spinnerCorrente == ano2019) {


                                normaConsuelo2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio") && spinnerCorrente == ano2019) {


                                novoMilenio2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras") && spinnerCorrente == ano2019) {


                                quintaMangabeiras2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente == ano2019) {


                                riachoDaCruz2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao") && spinnerCorrente == ano2019) {


                                saoJoao2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente == ano2019) {


                                saoJoaquim2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel") && spinnerCorrente == ano2019) {


                                saoMiguel2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente") && spinnerCorrente == ano2019) {


                                saoVicente2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Sagrada Família") && spinnerCorrente == ano2019) {


                                sagradaFamilia2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Cruz") && spinnerCorrente == ano2019) {


                                santaCruz2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Santa Isabel") && spinnerCorrente == ano2019) {


                                santaIsabel2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Sede") && spinnerCorrente == ano2019) {


                                sede2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Tejuco") && spinnerCorrente == ano2019) {


                                tejuco2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Terceiro Milênio") && spinnerCorrente == ano2019) {


                                terceiroMilenio2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Tropical") && spinnerCorrente == ano2019) {


                                tropical2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Várzea dos Pocões") && spinnerCorrente == ano2019) {


                                varzeaDosPocoes2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima") && spinnerCorrente == ano2019) {


                                vilaFatima2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Pandeiros") && spinnerCorrente == ano2019) {


                                pandeiros2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Paula") && spinnerCorrente == ano2019) {


                                vilaPaula2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Verde") && spinnerCorrente == ano2019) {


                                vilaVerde2019();

                            }

                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Viana") && spinnerCorrente == ano2019) {


                                vilaViana2019();

                            }


                            if (autoCompletegraficoRuaBarra.getText().toString().equals("Zona Rural") && spinnerCorrente == ano2019) {


                                zonaRural2019();

                            }


                        }
                    });


                    //carrega os spinner
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoHorarioBarraFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item, camposSpinner);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                    spinnerImagem = (ImageView) rootView.findViewById(R.id.imageViewSpinnerID);
                    spinner = (Spinner) rootView.findViewById(R.id.spinnerID);
                    spinner.setAdapter(arrayAdapter);


                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            if (position == 0) {

                                /////todos os anos e bairros
                                // posicion 0  todos os anos


                                spinnerCorrente = 0;

                                centroTodosAnos();


                                ///  opção spinner  todos
////                    /////////
                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto")) {


                                    aeroportoTodosAnos();


                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda")) {

                                    alameidaTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério")) {


                                    altoCemiterioTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões")) {


                                    altoPocoesTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada")) {

                                    alvoradaTodosAnos();
                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes")) {


                                    bandeirantesTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista")) {


                                    belaVistaTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança")) {


                                    boaEsperancaTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")) {


                                    boaVistaTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia")) {


                                    brasilandiaTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo")) {


                                    brejoDoAmparoTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic")) {


                                    caicTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro")) {


                                    centroTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica")) {


                                    ceramicaTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")) {


                                    eldoradoTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")) {


                                    fabiaoTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim")) {


                                    franklimTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete")) {


                                    jadeteTodosAnos();


                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária")) {


                                    januariaTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel")) {


                                    jardimDanielTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela")) {


                                    jardimEstelaTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá")) {


                                    jatobaTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Joventina Mesquita")) {


                                    joventinaMesquitaTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara")) {


                                    jussaraTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis")) {


                                    levianopolisTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras")) {

                                    mangueirasTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete")) {

                                    margareteTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras")) {

                                    moradeirasTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo")) {

                                    normaConsueloTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio")) {

                                    novoMilenioTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras")) {

                                    quintaMangabeirasTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")) {

                                    riachoDaCruzTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao")) {

                                    saoJoaoTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")) {

                                    saoJoaquimTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel")) {

                                    saoMiguelTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente")) {

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


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima")) {

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


                            } else if (position == 1) {
                                // opção spinner 2018


                                spinnerCorrente = 1;


                                centro2018();


                                // opção spinner 2018

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto")) {


                                    aeroporto2018();


                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda")) {

                                    alameida2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério")) {


                                    altoCemiterio2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões")) {


                                    altoPocoes2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada")) {

                                    alvorada2018();
                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes")) {


                                    bandeirantes2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista")) {


                                    belaVista2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança")) {


                                    boaEsperanca2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")) {


                                    boaVista2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia")) {


                                    brasilandia2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo")) {


                                    brejoDoAmparo2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic")) {


                                    caic2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro")) {


                                    centro2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica")) {


                                    ceramica2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")) {


                                    eldorado2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")) {


                                    fabiao2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim")) {


                                    franklim2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete")) {


                                    jadete2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária")) {


                                    januaria2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel")) {


                                    jardimDaniel2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela")) {


                                    jardimEstela2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá")) {


                                    jatoba2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Joventina Mesquita")) {


                                    joventinaMesquita2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara")) {


                                    jussara2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis")) {


                                    levianopolis2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras")) {

                                    mangueiras2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete")) {

                                    margarete2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras")) {

                                    moradeiras2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo")) {

                                    normaConsuelo2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio")) {

                                    novoMilenio2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras")) {

                                    quintaMangabeiras2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")) {

                                    riachoDaCruz2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao")) {

                                    saoJoao2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")) {

                                    saoJoaquim2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel")) {

                                    saoMiguel2018();


                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente")) {

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


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima")) {

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


                            } else if (position == 2) {  //2019


                                spinnerCorrente = 2;


                                centro2019();


                                // opção spinner 2019

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto")) {


                                    aeroporto2019();


                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alameda")) {

                                    alameida2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério")) {


                                    altoCemiterio2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alto dos Pocões")) {


                                    altoPocoes2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Alvorada")) {

                                    alvorada2019();
                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes")) {


                                    bandeirantes2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Bela Vista")) {


                                    belaVista2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Esperança")) {


                                    boaEsperanca2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")) {


                                    boaVista2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia")) {


                                    brasilandia2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Brejo do Amparo")) {


                                    brejoDoAmparo2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Caic")) {


                                    caic2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Centro")) {


                                    centro2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica")) {


                                    ceramica2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")) {


                                    eldorado2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")) {


                                    fabiao2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim")) {


                                    franklim2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete")) {


                                    jadete2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Januária")) {


                                    januaria2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Daniel")) {


                                    jardimDaniel2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela")) {


                                    jardimEstela2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá")) {


                                    jatoba2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Joventina Mesquita")) {


                                    joventinaMesquita2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jussara")) {


                                    jussara2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis")) {


                                    levianopolis2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Mangueiras")) {

                                    mangueiras2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Margarete")) {

                                    margarete2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Moradeiras")) {

                                    moradeiras2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Norma Consuelo")) {

                                    normaConsuelo2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Novo Milênio")) {

                                    novoMilenio2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras")) {

                                    quintaMangabeiras2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")) {

                                    riachoDaCruz2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao")) {

                                    saoJoao2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")) {

                                    saoJoaquim2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel")) {

                                    saoMiguel2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Vicente")) {

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


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima")) {

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
                            transaction.replace(R.id.conteinerFragmentos,new GraficoHorarioGeralBarraFragment()).commit();
                            return false;
                        }
                    });


                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });


        return rootView;


    }


    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoHorarioBarraFragment.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }


    private Chart getSameChart(Chart chart, String descricao, int textColor, int background, int animacaoY) {

        chart.getDescription().setText(descricao);
        chart.getDescription().setTextSize(24);
        chart.setBackgroundColor(background);
        chart.animateY(animacaoY);


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


        barChart = (BarChart) getSameChart(barChart, "", Color.RED, Color.WHITE, 3000);
        barChart.setDrawGridBackground(true);

        barChart.setActivated(true);


        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        for (int i = 0; i < roubos.length; i++) {
            yVals1.add(new BarEntry(i, roubos[i]));

        }


        ArrayList<BarEntry> yVals2 = new ArrayList<>();

        for (int i = 0; i < furtos.length; i++) {
            yVals2.add(new BarEntry(i, furtos[i]));

        }


        BarDataSet set1, set2;


        set1 = new BarDataSet(yVals1, "Roubo");
        set1.setColor(Color.RED);
        set1.setValueTextSize(15);
        set1.setValueTextColor(Color.BLUE);


        set2 = new BarDataSet(yVals2, "Furto");

        set2.setColor(Color.YELLOW);
        set2.setValueTextSize(15);
        set2.setValueTextColor(Color.BLUE);


        BarData data = new BarData(set1, set2);

        barChart.setData(data);


        axisX(barChart.getXAxis());


        barChart.getLegend().setEnabled(true);

        data.setBarWidth(0.45f);


    }


    /// opção todos os bairros no spinner
    // dados para geração de cada grafico


    public void aeroportoTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    public void alameidaTodosAnos() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    public void altoCemiterioTodosAnos() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }//Não consta no google

    public void altoPocoesTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    public void alvoradaTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    public void bandeirantesTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void belaVistaTodosAnos() {

        ///XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void boaEsperancaTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void boaVistaTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void brasilandiaTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    private void brejoDoAmparoTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void caicTodosAnos() {


        //XX  caic fica no Dom joão Batista

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    private void centroTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void ceramicaTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void eldoradoTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void fabiaoTodosAnos() {


        //XXX zona ruaral

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void franklimTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jadeteTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void januariaTodosAnos() {


        //xxx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void jardimDanielTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jardimEstelaTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jatobaTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }    //Não consta no google

    private void joventinaMesquitaTodosAnos() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void jussaraTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void levianopolisTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void mangueirasTodosAnos() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }   //Não consta no google

    private void margareteTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }   //Não consta no google

    private void moradeirasTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }   //Não consta no google

    private void normaConsueloTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }   //Não consta no google

    private void novoMilenioTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    private void quintaMangabeirasTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void riachoDaCruzTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoaoTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoaquimTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoMiguelTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoVicenteTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void sagradaFamiliaTodosAnos() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void santaCruzTodosAnos() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void santaIsabelTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void sedeTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void tejucoTodosAnos() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void terceiroMilenioTodosAnos() {

        // XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void tropicalTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void varzeaDosPocoesTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    private void vilaFatimaTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void pandeirosTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaPaulaTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaVerdeTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaVianaTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void zonaRuralTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  /// não fazer


    ///  /// opção 2018 no spinnner
    // dados para geração de cada grafico


/// alimentar manualmente


    public void aeroporto2018() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    public void alameida2018() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    public void altoCemiterio2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    } //Não consta no google

    public void altoPocoes2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    public void alvorada2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    public void bandeirantes2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    private void belaVista2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void boaEsperanca2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void boaVista2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void brasilandia2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void brejoDoAmparo2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void caic2018() {

        //XX  caic fica no Dom joão Batista

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void centro2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void ceramica2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void eldorado2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void fabiao2018() {


        //XXX zona ruaral

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void franklim2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jadete2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void januaria2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void jardimDaniel2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jardimEstela2018() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jatoba2018() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void joventinaMesquita2018() {

        ///XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void jussara2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void levianopolis2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void mangueiras2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void margarete2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void moradeiras2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void normaConsuelo2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    private void novoMilenio2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void quintaMangabeiras2018() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void riachoDaCruz2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoao2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoaquim2018() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoMiguel2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoVicente2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void sagradaFamilia2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    private void santaCruz2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void santaIsabel2018() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void sede2018() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }   //Não consta no google

    private void tejuco2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void terceiroMilenio2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  //Não consta no google

    private void tropical2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    private void varzeaDosPocoes2018() {

        ///xx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    private void vilaFatima2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void pandeiros2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaPaula2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaVerde2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaViana2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void zonaRural2018() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  /// não fazer


    ///

    ///2019

    ///  /// opção 2019 no spinnner bAirro
    // dados para geração de cada grafico


    public void aeroporto2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    public void alameida2019() {
        //xxx
        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } // não consta no google

    public void altoCemiterio2019() {


        //xxx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }  // não consta no google

    public void altoPocoes2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    public void alvorada2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    public void bandeirantes2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    private void belaVista2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void boaEsperanca2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void boaVista2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void brasilandia2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void brejoDoAmparo2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void caic2019() {

        //XX  caic fica no Dom joão Batista

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }   // não consta no google

    private void centro2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void ceramica2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void eldorado2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void fabiao2019() {


        //XXX zona ruaral

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void franklim2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jadete2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void januaria2019() {

        //XX
        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void jardimDaniel2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jardimEstela2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jatoba2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }  // não consta no google

    private void joventinaMesquita2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void jussara2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void levianopolis2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void mangueiras2019() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void margarete2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void moradeiras2019() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }// não consta no google

    private void normaConsuelo2019() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void novoMilenio2019() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void quintaMangabeiras2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void riachoDaCruz2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoao2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoaquim2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoMiguel2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoVicente2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void sagradaFamilia2019() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void santaCruz2019() {
        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void santaIsabel2019() {

        //xx   O google não mostra ruas nesse bairro

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void sede2019() {
        ///XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }   // não consta no google

    private void tejuco2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void terceiroMilenio2019() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void tropical2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void varzeaDosPocoes2019() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }  // não consta no google

    private void vilaFatima2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void pandeiros2019() {

        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaPaula2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaVerde2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaViana2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void zonaRural2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};

        int[] furtoss = new int[]{5, 10, 6, 10};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    } /// não fazer


    ///  /// opção no spinnner Ruas
    // contagem de furtos e roubos por rua


    public void ruasBairroAeroportoTodosAnos() {


        if (procuraBairro.contains("Aeroporto") && b.getStatus().equals("Roubada")) {



            // fazer 4 condiçooes com os turnos

        }


        if (procuraBairro.contains("Aeroporto") && b.getStatus().equals("Furtada")) {




            // fazer 4 condiçooes com os turnos


        }


    }

    public void ruasBairroAeroporto2019() {


        if (procuraBairro.contains("Aeroporto") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Aeroporto") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroAltoPocoesTodosAnos() {


        if (procuraBairro.contains("Alto dos Pocões") && b.getStatus().equals("Roubada")) {





        }


        if (procuraBairro.contains("Alto dos Pocões") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroAltoPocoes2019() {


        if (procuraBairro.contains("Alto dos Pocões") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Alto dos Pocões") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {




        }
    }

    public void ruasBairroAlvoradaTodosAnos() {


        if (procuraBairro.contains("Alvorada") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Alvorada") && b.getStatus().equals("Furtada")) {




        }


    }

    public void ruasBairroAlvorada2019() {


        if (procuraBairro.contains("Alvorada") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Alvorada") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroBandeirantesTodosAnos() {


        if (procuraBairro.contains("Bandeirantes") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Bandeirantes") && b.getStatus().equals("Furtada")) {




        }


    }

    public void ruasBairroBandeirantes2019() {


        if (procuraBairro.contains("Bandeirantes") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Bandeirantes") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {




        }
    }

    public void ruasBairroBoaEsperancaTodosAnos() {


        if (procuraBairro.contains("Boa Esperança") && b.getStatus().equals("Roubada")) {





        }


        if (procuraBairro.contains("Boa Esperança") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroBoaEsperanca2019() {


        if (procuraBairro.contains("Boa Esperança") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }
    }

    public void ruasBairroBoaVistaTodosAnos() {


        if (procuraBairro.contains("Boa Vista") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Boa Vista") && b.getStatus().equals("Furtada")) {



        }

    }

    public void ruasBairroBoaVista2019() {


        if (procuraBairro.contains("Boa Vista") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Boa Vista") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroVilaBrasilandiaTodosAnos() {


        if (procuraBairro.contains("Vila Brasilandia") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Vila Brasilandia") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroVilaBrasilandia2019() {


        if (procuraBairro.contains("Vila Brasilandia") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Vila Brasilandia") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroBrejoAmparoTodosAnos() {


        if (procuraBairro.contains("Brejo do Amparo") && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Brejo do Amparo") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroBrejoAmparo2019() {


        if (procuraBairro.contains("Brejo do Amparo") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Brejo do Amparo") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroCentroTodosAnos() {


        if (procuraBairro.contains("Centro") && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Centro") && b.getStatus().equals("Furtada")) {


        }


    }

    public void ruasBairroCentro2019() {


        if (procuraBairro.contains("Centro") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Centro") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroCeramicaTodosAnos() {


        if (procuraBairro.contains("Ceramica") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Ceramica") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroCeramica2019() {


        if (procuraBairro.contains("Ceramica") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Ceramica") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


        }
    }

    public void ruasBairroEldoradoTodosAnos() {


        if (procuraBairro.contains("Eldorado") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Eldorado") && b.getStatus().equals("Furtada")) {


        }


    }

    public void ruasBairroEldorado2019() {


        if (procuraBairro.contains("Eldorado") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Eldorado") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroFranklimTodosAnos() {


        if (procuraBairro.contains("Franklim") && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Franklim") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroFranklim2019() {


        if (procuraBairro.contains("Franklim") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Franklim") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroJardimDanielTodosAnos() {


        if (procuraBairro.contains("Jardim Daniel") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Jardim Daniel") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroJardimDaniel2019() {


        if (procuraBairro.contains("Jardim Daniel") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Jardim Daniel") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroJardimEstrelaTodosAnos() {


        if (procuraBairro.contains("Jardim Estrela") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Jardim Estrela") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroJardimEstrela2019() {


        if (procuraBairro.contains("Jardim Estrela") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Jardim Estrela") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroJussaraTodosAnos() {


        if (procuraBairro.contains("Jussara") && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Jussara") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroJussara2019() {


        if (procuraBairro.contains("Jussara") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Jussara") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroVilaLevianopolisTodosAnos() {


        if (procuraBairro.contains("Vila Levianopolis") && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Vila Levianopolis") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroVilaLevianopolis2019() {


        if (procuraBairro.contains("Vila Levianopolis") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Vila Levianopolis") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroQuintasMangueirasTodosAnos() {


        if (procuraBairro.contains("Quintas das Mangueiras") && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Quintas das Mangueiras") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroQuintasMangueiras2019() {


        if (procuraBairro.contains("Quintas das Mangueiras") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Quintas das Mangueiras") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


        }
    }

    public void ruasBairroRiachoCruzTodosAnos() {


        if (procuraBairro.contains("Riacho da Cruz") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Riacho da Cruz") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroRiachoCruz2019() {


        if (procuraBairro.contains("Riacho da Cruz") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Riacho da Cruz") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroVilaSaoJoaoTodosAnos() {


        if (procuraBairro.contains("Vila Sao Joao") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Vila Sao Joao") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroVilaSaoJoao2019() {


        if (procuraBairro.contains("Vila Sao Joao") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Vila Sao Joao") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroSaoJoaquimTodosAnos() {


        if (procuraBairro.contains("São Joaquim") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("São Joaquim") && b.getStatus().equals("Furtada")) {




        }


    }

    public void ruasBairroSaoJoaquim2019() {


        if (procuraBairro.contains("São Joaquim") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("São Joaquim") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


        }
    }

    public void ruasBairroSaoMiguelTodosAnos() {


        if (procuraBairro.contains("Vila Sao Miguel") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Vila Sao Miguel") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroSaoMiguel2019() {


        if (procuraBairro.contains("Vila Sao Miguel") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Vila Sao Miguel") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {







        }
    }

    public void ruasBairroSaoVicenteTodosAnos() {


        if (procuraBairro.contains("São Vicente") && b.getStatus().equals("Roubada")) {





        }


        if (procuraBairro.contains("São Vicente") && b.getStatus().equals("Furtada")) {




        }


    }

    public void ruasBairroSaoVicente2019() {


        if (procuraBairro.contains("São Vicente") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("São Vicente") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroTejucoTodosAnos() {


        if (procuraBairro.contains("Tejuco") && b.getStatus().equals("Roubada")) {





        }


        if (procuraBairro.contains("Tejuco") && b.getStatus().equals("Furtada")) {


        }


    }

    public void ruasBairroTejuco2019() {


        if (procuraBairro.contains("Tejuco") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Tejuco") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroVilaFatimaTodosAnos() {


        if (procuraBairro.contains("Vila Fatima") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Vila Fatima") && b.getStatus().equals("Furtada")) {




        }


    }

    public void ruasBairroVilaFatima2019() {


        if (procuraBairro.contains("Vila Fatima") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Vila Fatima") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


        }
    }

    public void ruasBairroPandeirosTodosAnos() {


        if (procuraBairro.contains("Pandeiros") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Pandeiros") && b.getStatus().equals("Furtada")) {


        }


    }

    public void ruasBairroPandeiros2019() {


        if (procuraBairro.contains("Pandeiros") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Pandeiros") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


        }
    }

    public void ruasBairroVilaPaulaTodosAnos() {


        if (procuraBairro.contains("Vila Paula") && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Vila Paula") && b.getStatus().equals("Furtada")) {




        }


    }

    public void ruasBairroVilaPaula2019() {


        if (procuraBairro.contains("Vila Paula") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


        }


        if (procuraBairro.contains("Vila Paula") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


        }
    }

    public void ruasBairroVilaVerdeTodosAnos() {


        if (procuraBairro.contains("Vila Verde") && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Vila Verde") && b.getStatus().equals("Furtada")) {




        }


    }

    public void ruasBairroVilaVerde2019() {


        if (procuraBairro.contains("Vila Verde") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Vila Verde") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



        }
    }

    public void ruasBairroVilaVianaTodosAnos() {


        if (procuraBairro.contains("Vila Viana") && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Vila Viana") && b.getStatus().equals("Furtada")) {



        }


    }

    public void ruasBairroVilaViana2019() {


        if (procuraBairro.contains("Vila Viana") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



        }


        if (procuraBairro.contains("Vila Viana") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


        }
    }

    public void ruasBairroJadeteTodosAnos() {


        if (procuraBairro.contains("Vila Jadete") && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Vila Jadete") && b.getStatus().equals("Furtada")) {




        }


    }

    public void ruasBairroJadete2019() {


        if (procuraBairro.contains("Vila Jadete") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {




        }


        if (procuraBairro.contains("Vila Jadete") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {





        }


    }

}



