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


public class GraficoHorarioGeralPizzaFragment extends Fragment {


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




    private String[] nomes = new String[]{};
    private   int[]  valores  = new int   []{120,125,112,314,};
    private int[] cores = new int[]{};



    String[] turnos = new String[]{"Madrug'", "Manhã", "Tarde", "Noite"};
    int[] cor = new int   []{Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN}; // ALTERAR a cor da legenda aq






    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;


    String h;
    int  horaDaOcorrencia=0;


    int   madrugadaMin =  0000;
    int   madrugadaMax =  559;

    int   manhaMin =  600;
    int   manhaMax =  1159;


    int   tardeMin =  1200;
    int   tardeMax =  1759;

    int   noiteMin =  1800 ;
    int   noiteMax =  2359;









    int madrugadaAeroportoRoubo=0;
    int manhaAeroportoRoubo=0;
    int tardeAeroportoRoubo=0;
    int noiteAeroportoRoubo=0;






    int madrugadaAeroportoRoubo2018=0;
    int manhaAeroportoRoubo2018=0;
    int tardeAeroportoRoubo2018=0;
    int noiteAeroportoRoubo2018=0;



    int madrugadaAeroportoRoubo2019=0;
    int manhaAeroportoRoubo2019=0;
    int tardeAeroportoRoubo2019=0;
    int noiteAeroportoRoubo2019=0;


    //----



    int madrugadaAltoPocoesRoubo=0;
    int manhaAltoPocoesRoubo=0;
    int tardeAltoPocoesRoubo=0;
    int noiteAltoPocoesRoubo=0;







    int madrugadaAltoPocoesRoubo2018=0;
    int manhaAltoPocoesRoubo2018=0;
    int tardeAltoPocoesRoubo2018=0;
    int noiteAltoPocoesRoubo2018=0;




    int madrugadaAltoPocoesRoubo2019=0;
    int manhaAltoPocoesRoubo2019=0;
    int tardeAltoPocoesRoubo2019=0;
    int noiteAltoPocoesRoubo2019=0;


//---




    int madrugadaAlvoradaRoubo=0;
    int manhaAlvoradaRoubo=0;
    int tardeAlvoradaRoubo=0;
    int noiteAlvoradaRoubo=0;





    int madrugadaAlvoradaRoubo2018=0;
    int manhaAlvoradaRoubo2018=0;
    int tardeAlvoradaRoubo2018=0;
    int noiteAlvoradaRoubo2018=0;



    int madrugadaAlvoradaRoubo2019=0;
    int manhaAlvoradaRoubo2019=0;
    int tardeAlvoradaRoubo2019=0;
    int noiteAlvoradaRoubo2019=0;


///---


    int madrugadaBandeirantesRoubo=0;
    int manhaBandeirantesRoubo=0;
    int tardeBandeirantesRoubo=0;
    int noiteBandeirantesRoubo=0;




    int madrugadaBandeirantesRoubo2018=0;
    int manhaBandeirantesRoubo2018=0;
    int tardeBandeirantesRoubo2018=0;
    int noiteBandeirantesRoubo2018=0;



    int madrugadaBandeirantesRoubo2019=0;
    int manhaBandeirantesRoubo2019=0;
    int tardeBandeirantesRoubo2019=0;
    int noiteBandeirantesRoubo2019=0;


//----



    int madrugadaBoaEsperancaRoubo=0;
    int manhaBoaEsperancaRoubo=0;
    int tardeBoaEsperancaRoubo=0;
    int noiteBoaEsperancaRoubo=0;





    int madrugadaBoaEsperancaRoubo2018=0;
    int manhaBoaEsperancaRoubo2018=0;
    int tardeBoaEsperancaRoubo2018=0;
    int noiteBoaEsperancaRoubo2018=0;




    int madrugadaBoaEsperancaRoubo2019=0;
    int manhaBoaEsperancaRoubo2019=0;
    int tardeBoaEsperancaRoubo2019=0;
    int noiteBoaEsperancaRoubo2019=0;


//---



    int madrugadaBoaVistaRoubo=0;
    int manhaBoaVistaRoubo=0;
    int tardeBoaVistaRoubo=0;
    int noiteBoaVistaRoubo=0;





    int madrugadaBoaVistaRoubo2018=0;
    int manhaBoaVistaRoubo2018=0;
    int tardeBoaVistaRoubo2018=0;
    int noiteBoaVistaRoubo2018=0;




    int madrugadaBoaVistaRoubo2019=0;
    int manhaBoaVistaRoubo2019=0;
    int tardeBoaVistaRoubo2019=0;
    int noiteBoaVistaRoubo2019=0;

//---



    int madrugadaVilaBrasilandiaRoubo=0;
    int manhaVilaBrasilandiaRoubo=0;
    int tardeVilaBrasilandiaRoubo=0;
    int noiteVilaBrasilandiaRoubo=0;





    int madrugadaVilaBrasilandiaRoubo2018=0;
    int manhaVilaBrasilandiaRoubo2018=0;
    int tardeVilaBrasilandiaRoubo2018=0;
    int noiteVilaBrasilandiaRoubo2018=0;



    int madrugadaVilaBrasilandiaRoubo2019=0;
    int manhaVilaBrasilandiaRoubo2019=0;
    int tardeVilaBrasilandiaRoubo2019=0;
    int noiteVilaBrasilandiaRoubo2019=0;



//---




    int madrugadaBrejoAmparoRoubo=0;
    int manhaBrejoAmparoRoubo=0;
    int tardeBrejoAmparoRoubo=0;
    int noiteBrejoAmparoRoubo=0;





    int madrugadaBrejoAmparoRoubo2018=0;
    int manhaBrejoAmparoRoubo2018=0;
    int tardeBrejoAmparoRoubo2018=0;
    int noiteBrejoAmparoRoubo2018=0;



    int madrugadaBrejoAmparoRoubo2019=0;
    int manhaBrejoAmparoRoubo2019=0;
    int tardeBrejoAmparoRoubo2019=0;
    int noiteBrejoAmparoRoubo2019=0;

//--




    int madrugadaCentroRoubo=0;
    int manhaCentroRoubo=0;
    int tardeCentroRoubo=0;
    int noiteCentroRoubo=0;





    int madrugadaCentroRoubo2018=0;
    int manhaCentroRoubo2018=0;
    int tardeCentroRoubo2018=0;
    int noiteCentroRoubo2018=0;


    int madrugadaCentroRoubo2019=0;
    int manhaCentroRoubo2019=0;
    int tardeCentroRoubo2019=0;
    int noiteCentroRoubo2019=0;

//---




    int madrugadaCeramicaRoubo=0;
    int manhaCeramicaRoubo=0;
    int tardeCeramicaRoubo=0;
    int noiteCeramicaRoubo=0;






    int madrugadaCeramicaRoubo2018=0;
    int manhaCeramicaRoubo2018=0;
    int tardeCeramicaRoubo2018=0;
    int noiteCeramicaRoubo2018=0;


    int madrugadaCeramicaRoubo2019=0;
    int manhaCeramicaRoubo2019=0;
    int tardeCeramicaRoubo2019=0;
    int noiteCeramicaRoubo2019=0;

//--



    int madrugadaEldoradoRoubo=0;
    int manhaEldoradoRoubo=0;
    int tardeEldoradoRoubo=0;
    int noiteEldoradoRoubo=0;





    int madrugadaEldoradoRoubo2018=0;
    int manhaEldoradoRoubo2018=0;
    int tardeEldoradoRoubo2018=0;
    int noiteEldoradoRoubo2018=0;




    int madrugadaEldoradoRoubo2019=0;
    int manhaEldoradoRoubo2019=0;
    int tardeEldoradoRoubo2019=0;
    int noiteEldoradoRoubo2019=0;

//---




    int madrugadaFranklimRoubo=0;
    int manhaFranklimRoubo=0;
    int tardeFranklimRoubo=0;
    int noiteFranklimRoubo=0;






    int madrugadaFranklimRoubo2018=0;
    int manhaFranklimRoubo2018=0;
    int tardeFranklimRoubo2018=0;
    int noiteFranklimRoubo2018=0;






    int madrugadaFranklimRoubo2019=0;
    int manhaFranklimRoubo2019=0;
    int tardeFranklimRoubo2019=0;
    int noiteFranklimRoubo2019=0;

//---



    int madrugadaJardimDanielRoubo=0;
    int manhaJardimDanielRoubo=0;
    int tardeJardimDanielRoubo=0;
    int noiteJardimDanielRoubo=0;




    int madrugadaJardimDanielRoubo2018=0;
    int manhaJardimDanielRoubo2018=0;
    int tardeJardimDanielRoubo2018=0;
    int noiteJardimDanielRoubo2018=0;




    int madrugadaJardimDanielRoubo2019=0;
    int manhaJardimDanielRoubo2019=0;
    int tardeJardimDanielRoubo2019=0;
    int noiteJardimDanielRoubo2019=0;

//--



    int madrugadaJardimEstrelaRoubo=0;
    int manhaJardimEstrelaRoubo=0;
    int tardeJardimEstrelaRoubo=0;
    int noiteJardimEstrelaRoubo=0;






    int madrugadaJardimEstrelaRoubo2018=0;
    int manhaJardimEstrelaRoubo2018=0;
    int tardeJardimEstrelaRoubo2018=0;
    int noiteJardimEstrelaRoubo2018=0;




    int madrugadaJardimEstrelaRoubo2019=0;
    int manhaJardimEstrelaRoubo2019=0;
    int tardeJardimEstrelaRoubo2019=0;
    int noiteJardimEstrelaRoubo2019=0;


//---


    int madrugadaJussaraRoubo=0;
    int manhaJussaraRoubo=0;
    int tardeJussaraRoubo=0;
    int noiteJussaraRoubo=0;





    int madrugadaJussaraRoubo2018=0;
    int manhaJussaraRoubo2018=0;
    int tardeJussaraRoubo2018=0;
    int noiteJussaraRoubo2018=0;



    int madrugadaJussaraRoubo2019=0;
    int manhaJussaraRoubo2019=0;
    int tardeJussaraRoubo2019=0;
    int noiteJussaraRoubo2019=0;

//---



    int madrugadaVilaLevianopolisRoubo=0;
    int manhaVilaLevianopolisRoubo=0;
    int tardeVilaLevianopolisRoubo=0;
    int noiteVilaLevianopolisRoubo=0;





    int madrugadaVilaLevianopolisRoubo2018=0;
    int manhaVilaLevianopolisRoubo2018=0;
    int tardeVilaLevianopolisRoubo2018=0;
    int noiteVilaLevianopolisRoubo2018=0;




    int madrugadaVilaLevianopolisRoubo2019=0;
    int manhaVilaLevianopolisRoubo2019=0;
    int tardeVilaLevianopolisRoubo2019=0;
    int noiteVilaLevianopolisRoubo2019=0;

//---




    int madrugadaQuintasMangueirasRoubo=0;
    int manhaQuintasMangueirasRoubo=0;
    int tardeQuintasMangueirasRoubo=0;
    int noiteQuintasMangueirasRoubo=0;




    int madrugadaQuintasMangueirasRoubo2018=0;
    int manhaQuintasMangueirasRoubo2018=0;
    int tardeQuintasMangueirasRoubo2018=0;
    int noiteQuintasMangueirasRoubo2018=0;





    int madrugadaQuintasMangueirasRoubo2019=0;
    int manhaQuintasMangueirasRoubo2019=0;
    int tardeQuintasMangueirasRoubo2019=0;
    int noiteQuintasMangueirasRoubo2019=0;

//----




    int madrugadaRiachoCruzRoubo=0;
    int manhaRiachoCruzRoubo=0;
    int tardeRiachoCruzRoubo=0;
    int noiteRiachoCruzRoubo=0;





    int madrugadaRiachoCruzRoubo2018=0;
    int manhaRiachoCruzRoubo2018=0;
    int tardeRiachoCruzRoubo2018=0;
    int noiteRiachoCruzRoubo2018=0;



    int madrugadaRiachoCruzRoubo2019=0;
    int manhaRiachoCruzRoubo2019=0;
    int tardeRiachoCruzRoubo2019=0;
    int noiteRiachoCruzRoubo2019=0;

//---




    int madrugadaVilaSaoJoaoRoubo=0;
    int manhaVilaSaoJoaoRoubo=0;
    int tardeVilaSaoJoaoRoubo=0;
    int noiteVilaSaoJoaoRoubo=0;






    int madrugadaVilaSaoJoaoRoubo2018=0;
    int manhaVilaSaoJoaoRoubo2018=0;
    int tardeVilaSaoJoaoRoubo2018=0;
    int noiteVilaSaoJoaoRoubo2018=0;




    int madrugadaVilaSaoJoaoRoubo2019=0;
    int manhaVilaSaoJoaoRoubo2019=0;
    int tardeVilaSaoJoaoRoubo2019=0;
    int noiteVilaSaoJoaoRoubo2019=0;



    //---



    int madrugadaSaoJoaquimRoubo=0;
    int manhaSaoJoaquimRoubo=0;
    int tardeSaoJoaquimRoubo=0;
    int noiteSaoJoaquimRoubo=0;






    int madrugadaSaoJoaquimRoubo2018=0;
    int manhaSaoJoaquimRoubo2018=0;
    int tardeSaoJoaquimRoubo2018=0;
    int noiteSaoJoaquimRoubo2018=0;



    int madrugadaSaoJoaquimRoubo2019=0;
    int manhaSaoJoaquimRoubo2019=0;
    int tardeSaoJoaquimRoubo2019=0;
    int noiteSaoJoaquimRoubo2019=0;


    //--



    int madrugadaSaoMiguelRoubo=0;
    int manhaSaoMiguelRoubo=0;
    int tardeSaoMiguelRoubo=0;
    int noiteSaoMiguelRoubo=0;





    int madrugadaSaoMiguelRoubo2018=0;
    int manhaSaoMiguelRoubo2018=0;
    int tardeSaoMiguelRoubo2018=0;
    int noiteSaoMiguelRoubo2018=0;



    int madrugadaSaoMiguelRoubo2019=0;
    int manhaSaoMiguelRoubo2019=0;
    int tardeSaoMiguelRoubo2019=0;
    int noiteSaoMiguelRoubo2019=0;

//---




    int madrugadaSaoVicenteRoubo=0;
    int manhaSaoVicenteRoubo=0;
    int tardeSaoVicenteRoubo=0;
    int noiteSaoVicenteRoubo=0;







    int madrugadaSaoVicenteRoubo2018=0;
    int manhaSaoVicenteRoubo2018=0;
    int tardeSaoVicenteRoubo2018=0;
    int noiteSaoVicenteRoubo2018=0;



    int madrugadaSaoVicenteRoubo2019=0;
    int manhaSaoVicenteRoubo2019=0;
    int tardeSaoVicenteRoubo2019=0;
    int noiteSaoVicenteRoubo2019=0;


//---




    int madrugadaTejucoRoubo=0;
    int manhaTejucoRoubo=0;
    int tardeTejucoRoubo=0;
    int noiteTejucoRoubo=0;




    int madrugadaTejucoRoubo2018=0;
    int manhaTejucoRoubo2018=0;
    int tardeTejucoRoubo2018=0;
    int noiteTejucoRoubo2018=0;



    int madrugadaTejucoRoubo2019=0;
    int manhaTejucoRoubo2019=0;
    int tardeTejucoRoubo2019=0;
    int noiteTejucoRoubo2019=0;

//---



    int madrugadaVilaFatimaRoubo=0;
    int manhaVilaFatimaRoubo=0;
    int tardeVilaFatimaRoubo=0;
    int noiteVilaFatimaRoubo=0;





    int madrugadaVilaFatimaRoubo2018=0;
    int manhaVilaFatimaRoubo2018=0;
    int tardeVilaFatimaRoubo2018=0;
    int noiteVilaFatimaRoubo2018=0;



    int madrugadaVilaFatimaRoubo2019=0;
    int manhaVilaFatimaRoubo2019=0;
    int tardeVilaFatimaRoubo2019=0;
    int noiteVilaFatimaRoubo2019=0;


//---



    int madrugadaPandeirosRoubo=0;
    int manhaPandeirosRoubo=0;
    int tardePandeirosRoubo=0;
    int noitePandeirosRoubo=0;





    int madrugadaPandeirosRoubo2018=0;
    int manhaPandeirosRoubo2018=0;
    int tardePandeirosRoubo2018=0;
    int noitePandeirosRoubo2018=0;




    int madrugadaPandeirosRoubo2019=0;
    int manhaPandeirosRoubo2019=0;
    int tardePandeirosRoubo2019=0;
    int noitePandeirosRoubo2019=0;

//---




    int madrugadaVilaPaulaRoubo=0;
    int manhaVilaPaulaRoubo=0;
    int tardeVilaPaulaRoubo=0;
    int noiteVilaPaulaRoubo=0;






    int madrugadaVilaPaulaRoubo2018=0;
    int manhaVilaPaulaRoubo2018=0;
    int tardeVilaPaulaRoubo2018=0;
    int noiteVilaPaulaRoubo2018=0;




    int madrugadaVilaPaulaRoubo2019=0;
    int manhaVilaPaulaRoubo2019=0;
    int tardeVilaPaulaRoubo2019=0;
    int noiteVilaPaulaRoubo2019=0;


//--




    int madrugadaVilaVerdeRoubo=0;
    int manhaVilaVerdeRoubo=0;
    int tardeVilaVerdeRoubo=0;
    int noiteVilaVerdeRoubo=0;




    int madrugadaVilaVerdeRoubo2018=0;
    int manhaVilaVerdeRoubo2018=0;
    int tardeVilaVerdeRoubo2018=0;
    int noiteVilaVerdeRoubo2018=0;



    int madrugadaVilaVerdeRoubo2019=0;
    int manhaVilaVerdeRoubo2019=0;
    int tardeVilaVerdeRoubo2019=0;
    int noiteVilaVerdeRoubo2019=0;

//--




    int madrugadaVilaVianaRoubo=0;
    int manhaVilaVianaRoubo=0;
    int tardeVilaVianaRoubo=0;
    int noiteVilaVianaRoubo=0;







    int madrugadaVilaVianaRoubo2018=0;
    int manhaVilaVianaRoubo2018=0;
    int tardeVilaVianaRoubo2018=0;
    int noiteVilaVianaRoubo2018=0;



    int madrugadaVilaVianaRoubo2019=0;
    int manhaVilaVianaRoubo2019=0;
    int tardeVilaVianaRoubo2019=0;
    int noiteVilaVianaRoubo2019=0;

//---




    int madrugadaVilaJadeteRoubo=0;
    int manhaVilaJadeteRoubo=0;
    int tardeVilaJadeteRoubo=0;
    int noiteVilaJadeteRoubo=0;



    int madrugadaVilaJadeteRoubo2018=0;
    int manhaVilaJadeteRoubo2018=0;
    int tardeVilaJadeteRoubo2018=0;
    int noiteVilaJadeteRoubo2018=0;



    int madrugadaVilaJadeteRoubo2019=0;
    int manhaVilaJadeteRoubo2019=0;
    int tardeVilaJadeteRoubo2019=0;
    int noiteVilaJadeteRoubo2019=0;


    private PieChart pieChart;









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_grafico_horario_geral_pizza, container, false);


        inicializarFirebase();

        pieChart =  (PieChart) rootView.findViewById(R.id.graficoHorarioGeralPizza);
        final String[] countries = getResources().getStringArray(R.array.countries);


        //carrega os spinner
        final AutoCompleteTextView autoCompletegraficoRuaBarra = rootView.findViewById(R.id.autocompletegraficoruabarrageral);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GraficoHorarioGeralPizzaFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item, countries);



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


                    //---------------------------------------------------------------------------------------

                    String hora = b.getAlertaHora();


                    h = hora.replace(":","");

                    if(!h.equals("")) {
                        horaDaOcorrencia = Integer.parseInt(h);
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
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoHorarioGeralPizzaFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item, camposSpinner);
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


                    pieChart.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            transaction.replace(R.id.conteinerFragmentos,new GraficoHorarioBarraFragment()).commit();
                            return false;
                        }
                    });


                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });









        madrugadaAeroportoRoubo          = madrugadaAeroportoRoubo2018    +    madrugadaAeroportoRoubo2019 ;
        manhaAeroportoRoubo              = manhaAeroportoRoubo2018        +    manhaAeroportoRoubo2019;
        tardeAeroportoRoubo              = tardeAeroportoRoubo2018        +    tardeAeroportoRoubo2019 ;
        noiteAeroportoRoubo              = noiteAeroportoRoubo2018        +    noiteAeroportoRoubo2019;




        madrugadaAltoPocoesRoubo        =  madrugadaAltoPocoesRoubo2018    +   madrugadaAltoPocoesRoubo2019 ;
        manhaAltoPocoesRoubo            =  manhaAltoPocoesRoubo2018        +   manhaAltoPocoesRoubo2019 ;
        tardeAltoPocoesRoubo            =  tardeAltoPocoesRoubo2018        +   tardeAltoPocoesRoubo2019 ;
        noiteAltoPocoesRoubo            =  noiteAltoPocoesRoubo2018        +   noiteAltoPocoesRoubo2019 ;



        madrugadaAlvoradaRoubo          =  madrugadaAlvoradaRoubo2018      +   madrugadaAlvoradaRoubo2019  ;
        manhaAlvoradaRoubo              =  manhaAlvoradaRoubo2018          +   manhaAlvoradaRoubo2019 ;
        tardeAlvoradaRoubo              =  tardeAlvoradaRoubo2018          +   tardeAlvoradaRoubo2019 ;
        noiteAlvoradaRoubo              =  noiteAlvoradaRoubo2018          +   noiteAlvoradaRoubo2019  ;


        madrugadaBandeirantesRoubo      =  madrugadaBandeirantesRoubo2018  +   madrugadaBandeirantesRoubo2019;
        manhaBandeirantesRoubo          =  manhaBandeirantesRoubo2018      +   manhaBandeirantesRoubo2019 ;
        tardeBandeirantesRoubo          =  tardeBandeirantesRoubo2018      +   tardeBandeirantesRoubo2019;
        noiteBandeirantesRoubo          =  noiteBandeirantesRoubo2018      +   noiteBandeirantesRoubo2019 ;



        madrugadaBoaEsperancaRoubo     =   madrugadaBoaEsperancaRoubo2018  +   madrugadaBoaEsperancaRoubo2019;
        manhaBoaEsperancaRoubo         =   manhaBoaEsperancaRoubo2018      +   manhaBoaEsperancaRoubo2019;
        tardeBoaEsperancaRoubo         =   tardeBoaEsperancaRoubo2018      +   tardeBoaEsperancaRoubo2019 ;
        noiteBoaEsperancaRoubo         =   noiteBoaEsperancaRoubo2018      +   noiteBoaEsperancaRoubo2019 ;





        madrugadaBoaVistaRoubo         =   madrugadaBoaVistaRoubo2018      +   madrugadaBoaVistaRoubo2019 ;
        manhaBoaVistaRoubo             =   manhaBoaVistaRoubo2018          +   manhaBoaVistaRoubo2019 ;
        tardeBoaVistaRoubo             =   tardeBoaVistaRoubo2018          +   tardeBoaVistaRoubo2019;
        noiteBoaVistaRoubo             =   noiteBoaVistaRoubo2018          +   noiteBoaVistaRoubo2019 ;



        madrugadaVilaBrasilandiaRoubo  =   madrugadaVilaBrasilandiaRoubo2018+  madrugadaVilaBrasilandiaRoubo2019;
        manhaVilaBrasilandiaRoubo      =   manhaVilaBrasilandiaRoubo2018    +  manhaVilaBrasilandiaRoubo2019;
        tardeVilaBrasilandiaRoubo      =   tardeVilaBrasilandiaRoubo2018    +  tardeVilaBrasilandiaRoubo2019 ;
        noiteVilaBrasilandiaRoubo      =   noiteVilaBrasilandiaRoubo2018    +  noiteVilaBrasilandiaRoubo2019 ;



        madrugadaBrejoAmparoRoubo      =   madrugadaBrejoAmparoRoubo2018    +  madrugadaBrejoAmparoRoubo2019;
        manhaBrejoAmparoRoubo          =   manhaBrejoAmparoRoubo2018        +  manhaBrejoAmparoRoubo2019;
        tardeBrejoAmparoRoubo          =   tardeBrejoAmparoRoubo2018        +  tardeBrejoAmparoRoubo2019 ;
        noiteBrejoAmparoRoubo          =   noiteBrejoAmparoRoubo2018        +  noiteBrejoAmparoRoubo2019  ;



        madrugadaCentroRoubo           =   madrugadaCentroRoubo2018         +  madrugadaCentroRoubo2019;
        manhaCentroRoubo               =   manhaCentroRoubo2018             +  manhaCentroRoubo2019;
        tardeCentroRoubo               =   tardeCentroRoubo2018             +  tardeCentroRoubo2019;
        noiteCentroRoubo               =   noiteCentroRoubo2018             +  noiteCentroRoubo2019 ;



        madrugadaCeramicaRoubo         =   madrugadaCeramicaRoubo2018       +  madrugadaCeramicaRoubo2019 ;
        manhaCeramicaRoubo             =   manhaCeramicaRoubo2018           +  manhaCeramicaRoubo2019;
        tardeCeramicaRoubo             =   tardeCeramicaRoubo2018           +  tardeCeramicaRoubo2019;
        noiteCeramicaRoubo             =   noiteCeramicaRoubo2018           +  noiteCeramicaRoubo2019;



        madrugadaEldoradoRoubo         =   madrugadaEldoradoRoubo2018       +  madrugadaEldoradoRoubo2019;
        manhaEldoradoRoubo             =   manhaEldoradoRoubo2018           +  manhaEldoradoRoubo2019;
        tardeEldoradoRoubo             =   tardeEldoradoRoubo2018           +  tardeEldoradoRoubo2019;
        noiteEldoradoRoubo             =   noiteEldoradoRoubo2018           +  noiteEldoradoRoubo2019;



        madrugadaFranklimRoubo        =    madrugadaFranklimRoubo2018       +  madrugadaFranklimRoubo2019;
        manhaFranklimRoubo            =    manhaFranklimRoubo2018           +  manhaFranklimRoubo2019;
        tardeFranklimRoubo            =    tardeFranklimRoubo2018           +  tardeFranklimRoubo2019;
        noiteFranklimRoubo            =    noiteFranklimRoubo2018           +  noiteFranklimRoubo2019;


        madrugadaJardimDanielRoubo    =    madrugadaJardimDanielRoubo2018   +  madrugadaJardimDanielRoubo2019;
        manhaJardimDanielRoubo        =    manhaJardimDanielRoubo2018       +  manhaJardimDanielRoubo2019;
        tardeJardimDanielRoubo        =    tardeJardimDanielRoubo2018       +  tardeJardimDanielRoubo2019 ;
        noiteJardimDanielRoubo        =    noiteJardimDanielRoubo2018       +  noiteJardimDanielRoubo2019;




        madrugadaJardimEstrelaRoubo   =    madrugadaJardimEstrelaRoubo2018  +  madrugadaJardimEstrelaRoubo2019;
        manhaJardimEstrelaRoubo       =    manhaJardimEstrelaRoubo2018      +  manhaJardimEstrelaRoubo2019;
        tardeJardimEstrelaRoubo       =    tardeJardimEstrelaRoubo2018      +  tardeJardimEstrelaRoubo2019;
        noiteJardimEstrelaRoubo       =    noiteJardimEstrelaRoubo2018      +  noiteJardimEstrelaRoubo2019;


        madrugadaJussaraRoubo         =    madrugadaJussaraRoubo2018        +  madrugadaJussaraRoubo2019;
        manhaJussaraRoubo             =    manhaJussaraRoubo2018            +  manhaJussaraRoubo2019;
        tardeJussaraRoubo             =    tardeJussaraRoubo2018            +  tardeJussaraRoubo2019;
        noiteJussaraRoubo             =    noiteJussaraRoubo2018            +  noiteJussaraRoubo2019;



        madrugadaVilaLevianopolisRoubo=    madrugadaVilaLevianopolisRoubo2018+ madrugadaVilaLevianopolisRoubo2019;
        manhaVilaLevianopolisRoubo    =    manhaVilaLevianopolisRoubo2018    + manhaVilaLevianopolisRoubo2019;
        tardeVilaLevianopolisRoubo    =    tardeVilaLevianopolisRoubo2018    + tardeVilaLevianopolisRoubo2019;
        noiteVilaLevianopolisRoubo    =    noiteVilaLevianopolisRoubo2018    + noiteVilaLevianopolisRoubo2019 ;


        madrugadaQuintasMangueirasRoubo=   madrugadaQuintasMangueirasRoubo2018+    madrugadaQuintasMangueirasRoubo2019;
        manhaQuintasMangueirasRoubo    =   manhaQuintasMangueirasRoubo2018    +    manhaQuintasMangueirasRoubo2019;
        tardeQuintasMangueirasRoubo    =   tardeQuintasMangueirasRoubo2018    +    tardeQuintasMangueirasRoubo2019;
        noiteQuintasMangueirasRoubo    =   noiteQuintasMangueirasRoubo2018    +    noiteQuintasMangueirasRoubo2019;


        madrugadaRiachoCruzRoubo       =   madrugadaRiachoCruzRoubo2018       +    madrugadaRiachoCruzRoubo2019;
        manhaRiachoCruzRoubo           =   manhaRiachoCruzRoubo2018           +    manhaRiachoCruzRoubo2019;
        tardeRiachoCruzRoubo           =   tardeRiachoCruzRoubo2018           +    tardeRiachoCruzRoubo2019;
        noiteRiachoCruzRoubo           =   noiteRiachoCruzRoubo2018           +    noiteRiachoCruzRoubo2019;



        madrugadaVilaSaoJoaoRoubo      =   madrugadaVilaSaoJoaoRoubo2018      +    madrugadaVilaSaoJoaoRoubo2019;
        manhaVilaSaoJoaoRoubo          =   manhaVilaSaoJoaoRoubo2018          +    manhaVilaSaoJoaoRoubo2019;
        tardeVilaSaoJoaoRoubo          =   tardeVilaSaoJoaoRoubo2018          +    tardeVilaSaoJoaoRoubo2019;
        noiteVilaSaoJoaoRoubo          =   noiteVilaSaoJoaoRoubo2018          +    noiteVilaSaoJoaoRoubo2019;



        madrugadaSaoJoaquimRoubo       =   madrugadaSaoJoaquimRoubo2018       +    madrugadaSaoJoaquimRoubo2019;
        manhaSaoJoaquimRoubo           =   manhaSaoJoaquimRoubo2018           +    manhaSaoJoaquimRoubo2019;
        tardeSaoJoaquimRoubo           =   tardeSaoJoaquimRoubo2018           +    tardeSaoJoaquimRoubo2019;
        noiteSaoJoaquimRoubo           =   noiteSaoJoaquimRoubo2018           +    noiteSaoJoaquimRoubo2019;

        madrugadaSaoMiguelRoubo        =   madrugadaSaoMiguelRoubo2018        +    madrugadaSaoMiguelRoubo2019;
        manhaSaoMiguelRoubo            =   manhaSaoMiguelRoubo2018            +    manhaSaoMiguelRoubo2019;
        tardeSaoMiguelRoubo            =   tardeSaoMiguelRoubo2018            +    tardeSaoMiguelRoubo2019;
        noiteSaoMiguelRoubo            =   noiteSaoMiguelRoubo2018            +    noiteSaoMiguelRoubo2019;


        madrugadaSaoVicenteRoubo       =   madrugadaSaoVicenteRoubo2018       +    madrugadaSaoVicenteRoubo2019;
        manhaSaoVicenteRoubo           =   manhaSaoVicenteRoubo2018           +    manhaSaoVicenteRoubo2019;
        tardeSaoVicenteRoubo           =   tardeSaoVicenteRoubo2018           +    tardeSaoVicenteRoubo2019;
        noiteSaoVicenteRoubo           =   noiteSaoVicenteRoubo2018           +    noiteSaoVicenteRoubo2019;


        madrugadaTejucoRoubo           =   madrugadaTejucoRoubo2018           +    madrugadaTejucoRoubo2019;
        manhaTejucoRoubo               =   manhaTejucoRoubo2018               +    manhaTejucoRoubo2019;
        tardeTejucoRoubo               =   tardeTejucoRoubo2018               +    tardeTejucoRoubo2019;
        noiteTejucoRoubo               =   noiteTejucoRoubo2018               +    noiteTejucoRoubo2019;



        madrugadaVilaFatimaRoubo       =   madrugadaVilaFatimaRoubo2018       +    madrugadaVilaFatimaRoubo2019;
        manhaVilaFatimaRoubo           =   manhaVilaFatimaRoubo2018           +    manhaVilaFatimaRoubo2019;
        tardeVilaFatimaRoubo           =   tardeVilaFatimaRoubo2018           +    tardeVilaFatimaRoubo2019;
        noiteVilaFatimaRoubo           =   noiteVilaFatimaRoubo2018           +    noiteVilaFatimaRoubo2019;


        madrugadaPandeirosRoubo        =   madrugadaPandeirosRoubo2018        +    madrugadaPandeirosRoubo2019;
        manhaPandeirosRoubo            =   manhaPandeirosRoubo2018            +    manhaPandeirosRoubo2019;
        tardePandeirosRoubo            =   tardePandeirosRoubo2018            +    tardePandeirosRoubo2019;
        noitePandeirosRoubo            =   noitePandeirosRoubo2018            +    noitePandeirosRoubo2019;



        madrugadaVilaPaulaRoubo        =   madrugadaVilaPaulaRoubo2018        +    madrugadaVilaPaulaRoubo2019;
        manhaVilaPaulaRoubo            =   manhaVilaPaulaRoubo2018            +    manhaVilaPaulaRoubo2019;
        tardeVilaPaulaRoubo            =   tardeVilaPaulaRoubo2018            +    tardeVilaPaulaRoubo2019;
        noiteVilaPaulaRoubo            =   noiteVilaPaulaRoubo2018            +    noiteVilaPaulaRoubo2019;



        madrugadaVilaVerdeRoubo        =   madrugadaVilaVerdeRoubo2018        +    madrugadaVilaVerdeRoubo2019;
        manhaVilaVerdeRoubo            =   manhaVilaVerdeRoubo2018            +    manhaVilaVerdeRoubo2019;
        tardeVilaVerdeRoubo            =   tardeVilaVerdeRoubo2018            +    tardeVilaVerdeRoubo2019;
        noiteVilaVerdeRoubo            =   noiteVilaVerdeRoubo2018            +    noiteVilaVerdeRoubo2019;

        madrugadaVilaVianaRoubo        =   madrugadaVilaVianaRoubo2018        +    madrugadaVilaVianaRoubo2019;
        manhaVilaVianaRoubo            =   manhaVilaVianaRoubo2018            +    manhaVilaVianaRoubo2019;
        tardeVilaVianaRoubo            =   tardeVilaVianaRoubo2018            +    tardeVilaVianaRoubo2019;
        noiteVilaVianaRoubo            =   noiteVilaVianaRoubo2018            +    noiteVilaVianaRoubo2019;


        madrugadaVilaJadeteRoubo       =   madrugadaVilaJadeteRoubo2018       +    madrugadaVilaJadeteRoubo2019;
        manhaVilaJadeteRoubo           =   manhaVilaJadeteRoubo2018           +    manhaVilaJadeteRoubo2019;
        tardeVilaJadeteRoubo           =   tardeVilaJadeteRoubo2018           +    tardeVilaJadeteRoubo2019;
        noiteVilaJadeteRoubo           =   noiteVilaJadeteRoubo2018           +    noiteVilaJadeteRoubo2019;








        return rootView;
    }








    private void inicializarFirebase() {

        FirebaseApp.initializeApp(GraficoHorarioGeralPizzaFragment.super.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }

    private Chart getSameChart(Chart chart, String descricao, int textColor, int background, int animacaoY){

        chart.getDescription().setText(descricao);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animacaoY);


        legend(chart);

        return  chart;
    }

    public void legend(Chart chart){
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);


        ArrayList<LegendEntry> entries = new ArrayList<>();

        for(int i=0;i<nomes.length;i++){

            LegendEntry entry = new LegendEntry();
            entry.formColor = cores[i];
            entry.label = nomes[i];
            entries.add(entry);

        }
        legend.setCustom(entries);

    }


    private void axisX(XAxis axis){

        axis.setGranularityEnabled(true);

        axis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        axis.setValueFormatter(new IndexAxisValueFormatter(nomes));




    }


    private ArrayList<BarEntry> getBarEntries(){


        ArrayList<BarEntry> entries = new ArrayList<>();


        for(int i=0;i <valores.length;i++)
            entries.add(new BarEntry(i,valores[i]));
        return entries;



    }


    private ArrayList<PieEntry> getPieEntries(){

        ArrayList<PieEntry> entries = new ArrayList<>();


        for(int i=0;i <valores.length;i++)
            entries.add(new PieEntry(valores[i]));
        return entries;


    }


    public void criarGraficos(){



        pieChart = (PieChart) getSameChart(pieChart,"",Color.RED,Color.TRANSPARENT,2000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setDrawHoleEnabled(false);


        pieChart.setData(getPieDate());
        pieChart.invalidate();



    }

    private DataSet getDate(DataSet dataSet){

        dataSet.setColors(cores);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(15);




        return dataSet;
    }

    private BarData getBarDate(){
        BarDataSet barDataSet = (BarDataSet)getDate(new BarDataSet(getBarEntries(),""));

        barDataSet.setBarShadowColor(Color.YELLOW);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);




        return new BarData(barDataSet);
    }


    private PieData getPieDate(){
        PieDataSet pieDataSet = (PieDataSet)getDate(new PieDataSet(getPieEntries(),""));

        pieDataSet.setSliceSpace(2);

        // passa para poercentagem
        //pieDataSet.setValueFormatter(new PercentFormatter());





        return new PieData(pieDataSet);
    }







    public void aeroportoTodosAnos() {


        int[] rouboss = new int[]{madrugadaAeroportoRoubo, manhaAeroportoRoubo, tardeAeroportoRoubo,noiteAeroportoRoubo};


        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }

    public void alameidaTodosAnos() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    } //Não consta no google

    public void altoCemiterioTodosAnos() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }//Não consta no google

    public void altoPocoesTodosAnos() {


        int[] rouboss = new int[]{madrugadaAltoPocoesRoubo, manhaAltoPocoesRoubo, tardeAltoPocoesRoubo,noiteAltoPocoesRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    public void alvoradaTodosAnos() {


        int[] rouboss = new int[]{madrugadaAlvoradaRoubo, manhaAlvoradaRoubo, tardeAlvoradaRoubo,noiteAlvoradaRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    public void bandeirantesTodosAnos() {


        int[] rouboss = new int[]{madrugadaBandeirantesRoubo, manhaBandeirantesRoubo, tardeBandeirantesRoubo,noiteBandeirantesRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void belaVistaTodosAnos() {

        ///XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void boaEsperancaTodosAnos() {


        int[] rouboss = new int[]{madrugadaBoaEsperancaRoubo, manhaBoaEsperancaRoubo, tardeBoaEsperancaRoubo,noiteBoaEsperancaRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void boaVistaTodosAnos() {


        int[] rouboss = new int[]{madrugadaBoaVistaRoubo, manhaBoaVistaRoubo, tardeBoaVistaRoubo,noiteBoaVistaRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void brasilandiaTodosAnos() {


        int[] rouboss = new int[]{madrugadaVilaBrasilandiaRoubo, manhaVilaBrasilandiaRoubo, tardeVilaBrasilandiaRoubo,noiteVilaBrasilandiaRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }

    private void brejoDoAmparoTodosAnos() {

        int[] rouboss = new int[]{madrugadaBrejoAmparoRoubo, manhaBrejoAmparoRoubo, tardeBrejoAmparoRoubo,noiteBrejoAmparoRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void caicTodosAnos() {


        //XX  caic fica no Dom joão Batista

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    } //Não consta no google

    private void centroTodosAnos() {

        int[] rouboss = new int[]{madrugadaCentroRoubo, manhaCentroRoubo, tardeCentroRoubo,noiteCentroRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void ceramicaTodosAnos() {

        int[] rouboss = new int[]{madrugadaCeramicaRoubo, manhaCeramicaRoubo, tardeCeramicaRoubo,noiteCeramicaRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void eldoradoTodosAnos() {

        int[] rouboss = new int[]{madrugadaEldoradoRoubo, manhaEldoradoRoubo, tardeEldoradoRoubo,noiteEldoradoRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void fabiaoTodosAnos() {


        //XXX zona ruaral

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void franklimTodosAnos() {


        int[] rouboss = new int[]{madrugadaFranklimRoubo, manhaFranklimRoubo, tardeFranklimRoubo,noiteFranklimRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void jadeteTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaJadeteRoubo, manhaVilaJadeteRoubo, tardeVilaJadeteRoubo,noiteVilaJadeteRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void januariaTodosAnos() {


        //xxx

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void jardimDanielTodosAnos() {


        int[] rouboss = new int[]{madrugadaJardimDanielRoubo, manhaJardimDanielRoubo, tardeJardimDanielRoubo,noiteJardimDanielRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void jardimEstelaTodosAnos() {

        int[] rouboss = new int[]{madrugadaJardimEstrelaRoubo, manhaJardimEstrelaRoubo, tardeJardimEstrelaRoubo,noiteJardimEstrelaRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void jatobaTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }    //Não consta no google

    private void joventinaMesquitaTodosAnos() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void jussaraTodosAnos() {


        int[] rouboss = new int[]{madrugadaJussaraRoubo, manhaJussaraRoubo, tardeJussaraRoubo,noiteJussaraRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void levianopolisTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaLevianopolisRoubo, manhaVilaLevianopolisRoubo, tardeVilaLevianopolisRoubo,noiteVilaLevianopolisRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void mangueirasTodosAnos() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }   //Não consta no google

    private void margareteTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }   //Não consta no google

    private void moradeirasTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }   //Não consta no google

    private void normaConsueloTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }   //Não consta no google

    private void novoMilenioTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    } //Não consta no google

    private void quintaMangabeirasTodosAnos() {

        int[] rouboss = new int[]{madrugadaQuintasMangueirasRoubo, manhaQuintasMangueirasRoubo, tardeQuintasMangueirasRoubo,noiteQuintasMangueirasRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void riachoDaCruzTodosAnos() {

        int[] rouboss = new int[]{madrugadaRiachoCruzRoubo, manhaRiachoCruzRoubo, tardeRiachoCruzRoubo,noiteRiachoCruzRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoJoaoTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaSaoJoaoRoubo, manhaVilaSaoJoaoRoubo, tardeVilaSaoJoaoRoubo,noiteVilaSaoJoaoRoubo};


        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }

    private void saoJoaquimTodosAnos() {


        int[] rouboss = new int[]{madrugadaSaoJoaquimRoubo, manhaSaoJoaquimRoubo, tardeSaoJoaquimRoubo,noiteSaoJoaquimRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoMiguelTodosAnos() {


        int[] rouboss = new int[]{madrugadaSaoMiguelRoubo, manhaSaoMiguelRoubo, tardeSaoMiguelRoubo,noiteSaoMiguelRoubo};



        nomes = turnos;
        valores = rouboss;
        cores = cor;


        criarGraficos();


    }

    private void saoVicenteTodosAnos() {

        int[] rouboss = new int[]{madrugadaSaoVicenteRoubo, manhaSaoVicenteRoubo, tardeSaoVicenteRoubo,noiteSaoVicenteRoubo};



        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }

    private void sagradaFamiliaTodosAnos() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void santaCruzTodosAnos() {


        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void santaIsabelTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void sedeTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void tejucoTodosAnos() {


        int[] rouboss = new int[]{madrugadaTejucoRoubo, manhaTejucoRoubo, tardeTejucoRoubo,noiteTejucoRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void terceiroMilenioTodosAnos() {

        // XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void tropicalTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  //Não consta no google

    private void varzeaDosPocoesTodosAnos() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    } //Não consta no google

    private void vilaFatimaTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaFatimaRoubo, manhaVilaFatimaRoubo, tardeVilaFatimaRoubo,noiteVilaFatimaRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void pandeirosTodosAnos() {

        int[] rouboss = new int[]{madrugadaPandeirosRoubo, manhaPandeirosRoubo, tardePandeirosRoubo,noitePandeirosRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void vilaPaulaTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaPaulaRoubo, manhaVilaPaulaRoubo, tardeVilaPaulaRoubo,noiteVilaPaulaRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void vilaVerdeTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaVerdeRoubo, manhaVilaVerdeRoubo, tardeVilaVerdeRoubo,noiteVilaVerdeRoubo};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void vilaVianaTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaVianaRoubo, manhaVilaVianaRoubo, tardeVilaVianaRoubo,noiteVilaVianaRoubo};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void zonaRuralTodosAnos() {

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  /// não fazer


    ///  /// opção 2018 no spinnner
    // dados para geração de cada grafico


/// alimentar manualmente


    public void aeroporto2018() {


        int[] rouboss = new int[]{madrugadaAeroportoRoubo2018, manhaAeroportoRoubo2018, tardeAeroportoRoubo2018,noiteAeroportoRoubo2018};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }

    public void alameida2018() {
        //xxx
        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    } // não consta no google

    public void altoCemiterio2018() {


        //xxx

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }  // não consta no google

    public void altoPocoes2018() {

        int[] rouboss = new int[]{madrugadaAltoPocoesRoubo2018, manhaAltoPocoesRoubo2018, tardeAltoPocoesRoubo2018,noiteAltoPocoesRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    public void alvorada2018() {


        int[] rouboss = new int[]{madrugadaAlvoradaRoubo2018, manhaAlvoradaRoubo2018, tardeAlvoradaRoubo2018,noiteAlvoradaRoubo2018};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }

    public void bandeirantes2018() {



        int[] rouboss = new int[]{madrugadaBandeirantesRoubo2018, manhaBandeirantesRoubo2018, tardeBandeirantesRoubo2018,noiteBandeirantesRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }

    private void belaVista2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void boaEsperanca2018() {

        int[] rouboss = new int[]{madrugadaBoaEsperancaRoubo2018, manhaBoaEsperancaRoubo2018, tardeBoaEsperancaRoubo2018,noiteBoaEsperancaRoubo2018};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void boaVista2018() {


        int[] rouboss = new int[]{madrugadaBoaVistaRoubo2018, manhaBoaVistaRoubo2018, tardeBoaVistaRoubo2018,noiteBoaVistaRoubo2018};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void brasilandia2018() {

        int[] rouboss = new int[]{madrugadaVilaBrasilandiaRoubo2018, manhaVilaBrasilandiaRoubo2018, tardeVilaBrasilandiaRoubo2018,noiteVilaBrasilandiaRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void brejoDoAmparo2018() {

        int[] rouboss = new int[]{madrugadaBrejoAmparoRoubo2018, manhaBrejoAmparoRoubo2018, tardeBrejoAmparoRoubo2018,noiteBrejoAmparoRoubo2018};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void caic2018() {

        //XX  caic fica no Dom joão Batista

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }   // não consta no google

    private void centro2018() {


        int[] rouboss = new int[]{madrugadaCentroRoubo2018, manhaCentroRoubo2018, tardeCentroRoubo2018,noiteCentroRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void ceramica2018() {


        int[] rouboss = new int[]{madrugadaCeramicaRoubo2018, manhaCeramicaRoubo2018, tardeCeramicaRoubo2018,noiteCeramicaRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;


        criarGraficos();


    }

    private void eldorado2018() {


        int[] rouboss = new int[]{madrugadaEldoradoRoubo2018, manhaEldoradoRoubo2018, tardeEldoradoRoubo2018,noiteEldoradoRoubo2018};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void fabiao2018() {


        //XXX zona ruaral

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void franklim2018() {

        int[] rouboss = new int[]{madrugadaFranklimRoubo2018, manhaFranklimRoubo2018, tardeFranklimRoubo2018,noiteFranklimRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void jadete2018() {

        int[] rouboss = new int[]{madrugadaVilaJadeteRoubo2018, manhaVilaJadeteRoubo2018, tardeVilaJadeteRoubo2018,noiteVilaJadeteRoubo2018};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void januaria2018() {

        //XX
        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void jardimDaniel2018() {


        int[] rouboss = new int[]{madrugadaJardimDanielRoubo2018, manhaJardimDanielRoubo2018, tardeJardimDanielRoubo2018,noiteJardimDanielRoubo2018};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void jardimEstela2018() {


        int[] rouboss = new int[]{madrugadaJardimEstrelaRoubo2018, manhaJardimEstrelaRoubo2018, tardeJardimEstrelaRoubo2018,noiteJardimEstrelaRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;


        criarGraficos();


    }

    private void jatoba2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }  // não consta no google

    private void joventinaMesquita2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void jussara2018() {


        int[] rouboss = new int[]{madrugadaJussaraRoubo2018, manhaJussaraRoubo2018, tardeJussaraRoubo2018,noiteJussaraRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void levianopolis2018() {

        int[] rouboss = new int[]{madrugadaVilaLevianopolisRoubo2018, manhaVilaLevianopolisRoubo2018, tardeVilaLevianopolisRoubo2018,noiteVilaLevianopolisRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void mangueiras2018() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void margarete2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void moradeiras2018() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }// não consta no google

    private void normaConsuelo2018() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void novoMilenio2018() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void quintaMangabeiras2018() {

        int[] rouboss = new int[]{madrugadaQuintasMangueirasRoubo2018, manhaQuintasMangueirasRoubo2018, tardeQuintasMangueirasRoubo2018,noiteQuintasMangueirasRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void riachoDaCruz2018() {

        int[] rouboss = new int[]{madrugadaRiachoCruzRoubo2018, manhaRiachoCruzRoubo2018, tardeRiachoCruzRoubo2018,noiteRiachoCruzRoubo2018};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoJoao2018() {


        int[] rouboss = new int[]{madrugadaVilaSaoJoaoRoubo2018, manhaVilaSaoJoaoRoubo2018, tardeVilaSaoJoaoRoubo2018,noiteVilaSaoJoaoRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoJoaquim2018() {


        int[] rouboss = new int[]{madrugadaSaoJoaquimRoubo2018, manhaSaoJoaquimRoubo2018, tardeSaoJoaquimRoubo2018,noiteSaoJoaquimRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoMiguel2018() {

        int[] rouboss = new int[]{madrugadaSaoMiguelRoubo2018, manhaSaoMiguelRoubo2018, tardeSaoMiguelRoubo2018,noiteSaoMiguelRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoVicente2018() {

        int[] rouboss = new int[]{madrugadaSaoVicenteRoubo2018, manhaSaoVicenteRoubo2018, tardeSaoVicenteRoubo2018,noiteSaoVicenteRoubo2018};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void sagradaFamilia2018() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void santaCruz2018() {
        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void santaIsabel2018() {

        //xx   O google não mostra ruas nesse bairro

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void sede2018() {
        ///XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }   // não consta no google

    private void tejuco2018() {


        int[] rouboss = new int[]{madrugadaTejucoRoubo2018, manhaTejucoRoubo2018, tardeTejucoRoubo2018,noiteTejucoRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;


        criarGraficos();


    }

    private void terceiroMilenio2018() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void tropical2018() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void varzeaDosPocoes2018() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void vilaFatima2018() {

        int[] rouboss = new int[]{madrugadaVilaFatimaRoubo2018, manhaVilaFatimaRoubo2018, tardeVilaFatimaRoubo2018,noiteVilaFatimaRoubo2018};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void pandeiros2018() {

        int[] rouboss = new int[]{madrugadaPandeirosRoubo2018, manhaPandeirosRoubo2018, tardePandeirosRoubo2018,noitePandeirosRoubo2018};


        nomes = turnos;

        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void vilaPaula2018() {

        int[] rouboss = new int[]{madrugadaVilaPaulaRoubo2018, manhaVilaPaulaRoubo2018, tardeVilaPaulaRoubo2018,noiteVilaPaulaRoubo2018};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void vilaVerde2018() {

        int[] rouboss = new int[]{madrugadaVilaVerdeRoubo2018, manhaVilaVerdeRoubo2018, tardeVilaVerdeRoubo2018,noiteVilaVerdeRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void vilaViana2018() {


        int[] rouboss = new int[]{madrugadaVilaVianaRoubo2018, manhaVilaVianaRoubo2018, tardeVilaVianaRoubo2018,noiteVilaVianaRoubo2018};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void zonaRural2018() {


        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    } /// não fazer

    ///

    ///2019

    ///  /// opção 2019 no spinnner bAirro
    // dados para geração de cada grafico


    public void aeroporto2019() {


        int[] rouboss = new int[]{madrugadaAeroportoRoubo2019, manhaAeroportoRoubo2019, tardeAeroportoRoubo2019,noiteAeroportoRoubo2019};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }

    public void alameida2019() {
        //xxx
        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    } // não consta no google

    public void altoCemiterio2019() {


        //xxx

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }  // não consta no google

    public void altoPocoes2019() {

        int[] rouboss = new int[]{madrugadaAltoPocoesRoubo2019, manhaAltoPocoesRoubo2019, tardeAltoPocoesRoubo2019,noiteAltoPocoesRoubo2019};



        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }

    public void alvorada2019() {


        int[] rouboss = new int[]{madrugadaAlvoradaRoubo2019, manhaAlvoradaRoubo2019, tardeAlvoradaRoubo2019,noiteAlvoradaRoubo2019};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }

    public void bandeirantes2019() {



        int[] rouboss = new int[]{madrugadaBandeirantesRoubo2019, manhaBandeirantesRoubo2019, tardeBandeirantesRoubo2019,noiteBandeirantesRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }

    private void belaVista2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};

        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void boaEsperanca2019() {

        int[] rouboss = new int[]{madrugadaBoaEsperancaRoubo2019, manhaBoaEsperancaRoubo2019, tardeBoaEsperancaRoubo2019,noiteBoaEsperancaRoubo2019};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void boaVista2019() {


        int[] rouboss = new int[]{madrugadaBoaVistaRoubo2019, manhaBoaVistaRoubo2019, tardeBoaVistaRoubo2019,noiteBoaVistaRoubo2019};

        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void brasilandia2019() {

        int[] rouboss = new int[]{madrugadaVilaBrasilandiaRoubo2019, manhaVilaBrasilandiaRoubo2019, tardeVilaBrasilandiaRoubo2019,noiteVilaBrasilandiaRoubo2019};



        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }

    private void brejoDoAmparo2019() {

        int[] rouboss = new int[]{madrugadaBrejoAmparoRoubo2019, manhaBrejoAmparoRoubo2019, tardeBrejoAmparoRoubo2019,noiteBrejoAmparoRoubo2019};




        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }

    private void caic2019() {

        //XX  caic fica no Dom joão Batista

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }   // não consta no google

    private void centro2019() {


        int[] rouboss = new int[]{madrugadaCentroRoubo2019, manhaCentroRoubo2019, tardeCentroRoubo2019,noiteCentroRoubo2019};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void ceramica2019() {


        int[] rouboss = new int[]{madrugadaCeramicaRoubo2019, manhaCeramicaRoubo2019, tardeCeramicaRoubo2019,noiteCeramicaRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void eldorado2019() {


        int[] rouboss = new int[]{madrugadaEldoradoRoubo2019, manhaEldoradoRoubo2019, tardeEldoradoRoubo2019,noiteEldoradoRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void fabiao2019() {


        //XXX zona ruaral

        int[] rouboss = new int[]{10, 15, 12, 34};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void franklim2019() {

        int[] rouboss = new int[]{madrugadaFranklimRoubo2019, manhaFranklimRoubo2019, tardeFranklimRoubo2019,noiteFranklimRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void jadete2019() {

        int[] rouboss = new int[]{madrugadaVilaJadeteRoubo2019, manhaVilaJadeteRoubo2019, tardeVilaJadeteRoubo2019,noiteVilaJadeteRoubo2019};

        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void januaria2019() {

        //XX
        int[] rouboss = new int[]{10, 15, 12, 34};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void jardimDaniel2019() {


        int[] rouboss = new int[]{madrugadaJardimDanielRoubo2019, manhaJardimDanielRoubo2019, tardeJardimDanielRoubo2019,noiteJardimDanielRoubo2019};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void jardimEstela2019() {


        int[] rouboss = new int[]{madrugadaJardimEstrelaRoubo2019, manhaJardimEstrelaRoubo2019, tardeJardimEstrelaRoubo2019,noiteJardimEstrelaRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void jatoba2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();

    }  // não consta no google

    private void joventinaMesquita2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void jussara2019() {


        int[] rouboss = new int[]{madrugadaJussaraRoubo2019, manhaJussaraRoubo2019, tardeJussaraRoubo2019,noiteJussaraRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void levianopolis2019() {

        int[] rouboss = new int[]{madrugadaVilaLevianopolisRoubo2019, manhaVilaLevianopolisRoubo2019, tardeVilaLevianopolisRoubo2019,noiteVilaLevianopolisRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void mangueiras2019() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void margarete2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void moradeiras2019() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34};



        nomes = turnos;
        valores= rouboss;
        cores = cor;



        criarGraficos();


    }// não consta no google

    private void normaConsuelo2019() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void novoMilenio2019() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void quintaMangabeiras2019() {

        int[] rouboss = new int[]{madrugadaQuintasMangueirasRoubo2019, manhaQuintasMangueirasRoubo2019, tardeQuintasMangueirasRoubo2019,noiteQuintasMangueirasRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void riachoDaCruz2019() {

        int[] rouboss = new int[]{madrugadaRiachoCruzRoubo2019, manhaRiachoCruzRoubo2019, tardeRiachoCruzRoubo2019,noiteRiachoCruzRoubo2019};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoJoao2019() {


        int[] rouboss = new int[]{madrugadaVilaSaoJoaoRoubo2019, manhaVilaSaoJoaoRoubo2019, tardeVilaSaoJoaoRoubo2019,noiteVilaSaoJoaoRoubo2019};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoJoaquim2019() {


        int[] rouboss = new int[]{madrugadaSaoJoaquimRoubo2019, manhaSaoJoaquimRoubo2019, tardeSaoJoaquimRoubo2019,noiteSaoJoaquimRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoMiguel2019() {

        int[] rouboss = new int[]{madrugadaSaoMiguelRoubo2019, manhaSaoMiguelRoubo2019, tardeSaoMiguelRoubo2019,noiteSaoMiguelRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void saoVicente2019() {

        int[] rouboss = new int[]{madrugadaSaoVicenteRoubo2019, manhaSaoVicenteRoubo2019, tardeSaoVicenteRoubo2019,noiteSaoVicenteRoubo2019};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void sagradaFamilia2019() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void santaCruz2019() {
        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void santaIsabel2019() {

        //xx   O google não mostra ruas nesse bairro

        int[] rouboss = new int[]{10, 15, 12, 34,};


        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void sede2019() {
        ///XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }   // não consta no google

    private void tejuco2019() {


        int[] rouboss = new int[]{madrugadaTejucoRoubo2019, manhaTejucoRoubo2019, tardeTejucoRoubo2019,noiteTejucoRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void terceiroMilenio2019() {

        //xx

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void tropical2019() {

        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void varzeaDosPocoes2019() {
        //XX

        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }  // não consta no google

    private void vilaFatima2019() {

        int[] rouboss = new int[]{madrugadaVilaFatimaRoubo2019, manhaVilaFatimaRoubo2019, tardeVilaFatimaRoubo2019,noiteVilaFatimaRoubo2019};




        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void pandeiros2019() {

        int[] rouboss = new int[]{madrugadaPandeirosRoubo2019, manhaPandeirosRoubo2019, tardePandeirosRoubo2019,noitePandeirosRoubo2019};


        nomes = turnos;

        nomes = turnos;
        valores = rouboss;
        cores = cor;



        criarGraficos();


    }

    private void vilaPaula2019() {

        int[] rouboss = new int[]{madrugadaVilaPaulaRoubo2019, manhaVilaPaulaRoubo2019, tardeVilaPaulaRoubo2019,noiteVilaPaulaRoubo2019};

        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void vilaVerde2019() {

        int[] rouboss = new int[]{madrugadaVilaVerdeRoubo2019, manhaVilaVerdeRoubo2019, tardeVilaVerdeRoubo2019,noiteVilaVerdeRoubo2019};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    }

    private void vilaViana2019() {


        int[] rouboss = new int[]{madrugadaVilaVianaRoubo2019, manhaVilaVianaRoubo2019, tardeVilaVianaRoubo2019,noiteVilaVianaRoubo2019};



        nomes = turnos;
        valores = rouboss;
        cores = cor;


        criarGraficos();


    }

    private void zonaRural2019() {


        int[] rouboss = new int[]{10, 15, 12, 34,};



        nomes = turnos;
        valores = rouboss;

        cores = cor;



        criarGraficos();


    } /// não fazer


    ///  /// opção no spinnner Ruas
    // contagem de furtos e roubos por rua


    public void ruasBairroAeroportoTodosAnos() {


        if (procuraBairro.contains("Aeroporto") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAeroportoRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAeroportoRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAeroportoRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAeroportoRoubo++;
            }


        }



    }

    public void ruasBairroAeroporto2019() {


        if (procuraBairro.contains("Aeroporto") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAeroportoRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAeroportoRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAeroportoRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAeroportoRoubo2019++;
            }



        }


    }

    public void ruasBairroAltoPocoesTodosAnos() {


        if (procuraBairro.contains("Alto dos Pocões") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAltoPocoesRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAltoPocoesRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAltoPocoesRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAltoPocoesRoubo++;
            }



        }


    }

    public void ruasBairroAltoPocoes2019() {


        if (procuraBairro.contains("Alto dos Pocões") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAltoPocoesRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAltoPocoesRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAltoPocoesRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAltoPocoesRoubo2019++;
            }


        }


    }

    public void ruasBairroAlvoradaTodosAnos() {


        if (procuraBairro.contains("Alvorada") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAlvoradaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAlvoradaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAlvoradaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAlvoradaRoubo++;
            }




        }

    }

    public void ruasBairroAlvorada2019() {


        if (procuraBairro.contains("Alvorada") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAlvoradaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAlvoradaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAlvoradaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAlvoradaRoubo2019++;
            }




        }


    }

    public void ruasBairroBandeirantesTodosAnos() {


        if (procuraBairro.contains("Bandeirantes") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBandeirantesRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBandeirantesRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBandeirantesRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBandeirantesRoubo++;
            }



        }


    }

    public void ruasBairroBandeirantes2019() {


        if (procuraBairro.contains("Bandeirantes") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBandeirantesRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBandeirantesRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBandeirantesRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBandeirantesRoubo2019++;
            }



        }


    }

    public void ruasBairroBoaEsperancaTodosAnos() {


        if (procuraBairro.contains("Boa Esperança") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBoaEsperancaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBoaEsperancaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBoaEsperancaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBoaEsperancaRoubo++;
            }



        }


    }

    public void ruasBairroBoaEsperanca2019() {


        if (procuraBairro.contains("Boa Esperança") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBoaEsperancaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBoaEsperancaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBoaEsperancaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBoaEsperancaRoubo2019++;
            }

        }


    }

    public void ruasBairroBoaVistaTodosAnos() {


        if (procuraBairro.contains("Boa Vista") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBoaVistaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBoaVistaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBoaVistaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBoaVistaRoubo++;
            }


        }


    }

    public void ruasBairroBoaVista2019() {


        if (procuraBairro.contains("Boa Vista") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBoaVistaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBoaVistaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBoaVistaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBoaVistaRoubo2019++;
            }


        }



    }

    public void ruasBairroVilaBrasilandiaTodosAnos() {


        if (procuraBairro.contains("Vila Brasilandia") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaBrasilandiaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaBrasilandiaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaBrasilandiaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaBrasilandiaRoubo++;
            }

        }




    }

    public void ruasBairroVilaBrasilandia2019() {


        if (procuraBairro.contains("Vila Brasilandia") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaBrasilandiaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaBrasilandiaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaBrasilandiaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaBrasilandiaRoubo2019++;
            }


        }


    }

    public void ruasBairroBrejoAmparoTodosAnos() {


        if (procuraBairro.contains("Brejo do Amparo") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {




            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBrejoAmparoRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBrejoAmparoRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBrejoAmparoRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBrejoAmparoRoubo++;
            }

        }


    }

    public void ruasBairroBrejoAmparo2019() {


        if (procuraBairro.contains("Brejo do Amparo") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBrejoAmparoRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBrejoAmparoRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBrejoAmparoRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBrejoAmparoRoubo2019++;
            }

        }



    }

    public void ruasBairroCentroTodosAnos() {


        if (procuraBairro.contains("Centro") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaCentroRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaCentroRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeCentroRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteCentroRoubo++;
            }

        }




    }

    public void ruasBairroCentro2019() {


        if (procuraBairro.contains("Centro") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaCentroRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaCentroRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeCentroRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteCentroRoubo2019++;
            }


        }


    }

    public void ruasBairroCeramicaTodosAnos() {


        if (procuraBairro.contains("Ceramica") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaCeramicaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaCeramicaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeCeramicaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteCeramicaRoubo++;
            }


        }



    }

    public void ruasBairroCeramica2019() {


        if (procuraBairro.contains("Ceramica") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaCeramicaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaCeramicaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeCeramicaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteCeramicaRoubo2019++;
            }


        }


    }

    public void ruasBairroEldoradoTodosAnos() {


        if (procuraBairro.contains("Eldorado") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaEldoradoRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaEldoradoRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeEldoradoRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteEldoradoRoubo++;
            }

        }


    }

    public void ruasBairroEldorado2019() {


        if (procuraBairro.contains("Eldorado") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaEldoradoRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaEldoradoRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeEldoradoRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteEldoradoRoubo2019++;
            }

        }


    }

    public void ruasBairroFranklimTodosAnos() {


        if (procuraBairro.contains("Franklim") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaFranklimRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaFranklimRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeFranklimRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteFranklimRoubo++;
            }

        }


    }

    public void ruasBairroFranklim2019() {


        if (procuraBairro.contains("Franklim") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaFranklimRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaFranklimRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeFranklimRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteFranklimRoubo2019++;
            }

        }



    }

    public void ruasBairroJardimDanielTodosAnos() {


        if (procuraBairro.contains("Jardim Daniel") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJardimDanielRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJardimDanielRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJardimDanielRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJardimDanielRoubo++;
            }


        }


    }

    public void ruasBairroJardimDaniel2019() {


        if (procuraBairro.contains("Jardim Daniel") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJardimDanielRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJardimDanielRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJardimDanielRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJardimDanielRoubo2019++;
            }


        }



    }

    public void ruasBairroJardimEstrelaTodosAnos() {


        if (procuraBairro.contains("Jardim Estrela") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJardimEstrelaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJardimEstrelaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJardimEstrelaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJardimEstrelaRoubo++;
            }


        }


    }

    public void ruasBairroJardimEstrela2019() {


        if (procuraBairro.contains("Jardim Estrela") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJardimEstrelaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJardimEstrelaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJardimEstrelaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJardimEstrelaRoubo2019++;
            }

        }


    }

    public void ruasBairroJussaraTodosAnos() {


        if (procuraBairro.contains("Jussara") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJussaraRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJussaraRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJussaraRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJussaraRoubo++;
            }

        }





    }

    public void ruasBairroJussara2019() {


        if (procuraBairro.contains("Jussara") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJussaraRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJussaraRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJussaraRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJussaraRoubo2019++;
            }



        }



    }

    public void ruasBairroVilaLevianopolisTodosAnos() {


        if (procuraBairro.contains("Vila Levianopolis") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaLevianopolisRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaLevianopolisRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaLevianopolisRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaLevianopolisRoubo++;
            }


        }


    }

    public void ruasBairroVilaLevianopolis2019() {


        if (procuraBairro.contains("Vila Levianopolis") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaLevianopolisRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaLevianopolisRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaLevianopolisRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaLevianopolisRoubo2019++;
            }


        }


    }

    public void ruasBairroQuintasMangueirasTodosAnos() {


        if (procuraBairro.contains("Quintas das Mangueiras") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaQuintasMangueirasRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaQuintasMangueirasRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeQuintasMangueirasRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteQuintasMangueirasRoubo++;
            }

        }


    }

    public void ruasBairroQuintasMangueiras2019() {


        if (procuraBairro.contains("Quintas das Mangueiras") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaQuintasMangueirasRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaQuintasMangueirasRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeQuintasMangueirasRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteQuintasMangueirasRoubo2019++;
            }


        }


    }

    public void ruasBairroRiachoCruzTodosAnos() {


        if (procuraBairro.contains("Riacho da Cruz") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaRiachoCruzRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaRiachoCruzRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeRiachoCruzRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteRiachoCruzRoubo++;
            }


        }



    }

    public void ruasBairroRiachoCruz2019() {


        if (procuraBairro.contains("Riacho da Cruz") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){




            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaRiachoCruzRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaRiachoCruzRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeRiachoCruzRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteRiachoCruzRoubo2019++;
            }


        }


    }

    public void ruasBairroVilaSaoJoaoTodosAnos() {


        if (procuraBairro.contains("Vila Sao Joao") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaSaoJoaoRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaSaoJoaoRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaSaoJoaoRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaSaoJoaoRoubo++;
            }

        }


    }

    public void ruasBairroVilaSaoJoao2019() {


        if (procuraBairro.contains("Vila Sao Joao") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaSaoJoaoRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaSaoJoaoRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaSaoJoaoRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaSaoJoaoRoubo2019++;
            }



        }


    }

    public void ruasBairroSaoJoaquimTodosAnos() {


        if (procuraBairro.contains("São Joaquim") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoJoaquimRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoJoaquimRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoJoaquimRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoJoaquimRoubo++;
            }


        }


    }

    public void ruasBairroSaoJoaquim2019() {


        if (procuraBairro.contains("São Joaquim") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoJoaquimRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoJoaquimRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoJoaquimRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoJoaquimRoubo2019++;
            }

        }


    }

    public void ruasBairroSaoMiguelTodosAnos() {


        if (procuraBairro.contains("Vila Sao Miguel") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoMiguelRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoMiguelRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoMiguelRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoMiguelRoubo++;
            }


        }




    }

    public void ruasBairroSaoMiguel2019() {


        if (procuraBairro.contains("Vila Sao Miguel") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoMiguelRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoMiguelRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoMiguelRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoMiguelRoubo2019++;
            }

        }



    }

    public void ruasBairroSaoVicenteTodosAnos() {


        if (procuraBairro.contains("São Vicente") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoVicenteRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoVicenteRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoVicenteRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoVicenteRoubo++;
            }


        }


    }

    public void ruasBairroSaoVicente2019() {


        if (procuraBairro.contains("São Vicente") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoVicenteRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoVicenteRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoVicenteRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoVicenteRoubo2019++;
            }

        }


    }

    public void ruasBairroTejucoTodosAnos() {


        if (procuraBairro.contains("Tejuco") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaTejucoRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaTejucoRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeTejucoRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteTejucoRoubo++;
            }


        }



    }

    public void ruasBairroTejuco2019() {


        if (procuraBairro.contains("Tejuco") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaTejucoRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaTejucoRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeTejucoRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteTejucoRoubo2019++;
            }

        }


    }

    public void ruasBairroVilaFatimaTodosAnos() {


        if (procuraBairro.contains("Vila Fatima") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaFatimaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaFatimaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaFatimaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaFatimaRoubo++;
            }


        }



    }

    public void ruasBairroVilaFatima2019() {


        if (procuraBairro.contains("Vila Fatima") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaFatimaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaFatimaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaFatimaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaFatimaRoubo2019++;
            }

        }


    }

    public void ruasBairroPandeirosTodosAnos() {


        if (procuraBairro.contains("Pandeiros") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaPandeirosRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaPandeirosRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardePandeirosRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noitePandeirosRoubo++;
            }


        }



    }

    public void ruasBairroPandeiros2019() {


        if (procuraBairro.contains("Pandeiros") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaPandeirosRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaPandeirosRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardePandeirosRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noitePandeirosRoubo2019++;
            }

        }


    }

    public void ruasBairroVilaPaulaTodosAnos() {


        if (procuraBairro.contains("Vila Paula") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaPaulaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaPaulaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaPaulaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaPaulaRoubo++;
            }

        }


    }

    public void ruasBairroVilaPaula2019() {


        if (procuraBairro.contains("Vila Paula") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaPaulaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaPaulaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaPaulaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaPaulaRoubo2019++;
            }
        }


    }

    public void ruasBairroVilaVerdeTodosAnos() {


        if (procuraBairro.contains("Vila Verde") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaVerdeRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaVerdeRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaVerdeRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaVerdeRoubo++;
            }

        }




    }

    public void ruasBairroVilaVerde2019() {


        if (procuraBairro.contains("Vila Verde") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaVerdeRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaVerdeRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaVerdeRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaVerdeRoubo2019++;
            }


        }


    }

    public void ruasBairroVilaVianaTodosAnos() {


        if (procuraBairro.contains("Vila Viana") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))){

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaVianaRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaVianaRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaVianaRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaVianaRoubo++;
            }

        }


    }

    public void ruasBairroVilaViana2019() {


        if (procuraBairro.equals("Vila Viana") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaVianaRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaVianaRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaVianaRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaVianaRoubo2019++;
            }



        }


    }

    public void ruasBairroJadeteTodosAnos() {


        if (procuraBairro.equals("Vila Jadete") && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaJadeteRoubo++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaJadeteRoubo++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaJadeteRoubo++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaJadeteRoubo++;
            }


        }






    }

    public void ruasBairroJadete2019() {


        if (procuraBairro.contains("Vila Jadete") && procuraAno.contains(anoProcura2019) && (b.getStatus().equals("Roubada") || b.getStatus().equals("Furtada"))) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaJadeteRoubo2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaJadeteRoubo2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaJadeteRoubo2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaJadeteRoubo2019++;
            }

        }



    }






}
