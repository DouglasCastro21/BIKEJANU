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



// mostra roubos e furtos de forma dividida

public class GraficoRuaBarraFragment extends Fragment {




    String anoProcura2019 ="2019";

    int  ano ;
    int contandoBikesAno2018=0;
    int contandoBikesAno2019=0;
    int AeroportoRuaTal=0;




    // variaveis do bairro Aeroporto ,ruas   todos os anos   /// alimentar aq os anos de 2018


    int contandoAvAeroportoAeroportoRouboTodosAnos =0;
    int contandoAvSeisAeroportoRouboTodosAnos =0;
    int contandoRuaRUmAeroportoRouboTodosAnos=0;
    int contandoRuaRDoisAeroportoRouboTodosAnos=0;
    int contandoRuaRTresAeroportoRouboTodosAnos=0;
    int contandoRuaRQuatroAeroportoRouboTodosAnos=0;
    int contandoRuavinteTresAeroportoRouboTodosAnos=0;
    int contandoRuaRXIAeroportoRouboTodosAnos=0;

    int  contandoAvAeroportoAeroportoFurtoTodosAnos =0;
    int contandoAvSeisAeroportoFurtoTodosAnos =0;
    int contandoRuaRUmAeroportoFurtoTodosAnos=0;
    int contandoRuaRDoisAeroportoFurtoTodosAnos=0;
    int contandoRuaRTresAeroportoFurtoTodosAnos=0;
    int contandoRuaRQuatroAeroportoFurtoTodosAnos=0;
    int contandoRuavinteTresAeroportoFurtoTodosAnos=0;
    int contandoRuaRXIAeroportoFurtoTodosAnos=0;




    // variaveis do bairro Aeroporo ,ruas   ano 2019


    int contandoAvAeroportoAeroportoRoubo2019=0;
    int  contandoAvSeisAeroportoRoubo2019=0;
    int contandoRuaRUmAeroportoRoubo2019=0;
    int contandoRuaRDoisAeroportoRoubo2019=0;
    int  contandoRuaRTresAeroportoRoubo2019=0;
    int contandoRuaRQuatroAeroportoRoubo2019=0;
    int contandoRuavinteTresAeroportoRoubo2019=0;
    int contandoRuaRXIAeroportoRoubo2019=0;

    int contandoAvAeroportoAeroportoFurtada2019=0;
    int  contandoAvSeisAeroportoFurtada2019=0;
    int contandoRuaRUmAeroportoFurtada2019=0;
    int contandoRuaRDoisAeroportoFurtada2019=0;
    int  contandoRuaRTresAeroportoFurtada2019=0;
    int contandoRuaRQuatroAeroportoFurtada2019=0;
    int contandoRuavinteTresAeroportoFurtada2019=0;
    int contandoRuaRXIAeroportoFurtada2019=0;




    // variaveis do bairro jussara ,ruas   todos os anos   /// alimentar aq os anos de 2018
    int contandoRuaRamiroLeiteJadeteRouboTodosAnos =0;
    int contandoRuaAlameidaVianaJadeteRouboTodosAnos =0;
    int contandoRuaInezitaJadeteRouboTodosAnos =0;
    int contandoRuaIslaHJadeteRouboTodosAnos =0;
    int contandoRuaSantoAntJadeteRouboTodosAnos =0;
    int contandoRuaVianaJadeteRouboTodosAnos =0;
    int contandoRuavinteDoisJadeteRouboTodosAnos =0;
    int contandoRuaJoaoAlkimimJadeteRouboTodosAnos =0;
    int contandoTvVianaJadeteRouboTodosAnos =0;


    int contandoRuaRamiroLeiteJadeteFurtoTodosAnos =0;
    int contandoRuaAlameidaVianaJadeteFurtoTodosAnos =0;
    int contandoRuaInezitaJadeteFurtoTodosAnos =0;
    int contandoRuaIslaHJadeteFurtoTodosAnos =0;
    int contandoRuaSantoAntJadeteFurtoTodosAnos =0;
    int contandoRuaVianaJadeteFurtoTodosAnos =0;
    int contandoRuavinteDoisJadeteFurtoTodosAnos =0;
    int contandoRuaJoaoAlkimimJadeteFurtoTodosAnos =0;
    int contandoTvVianaJadeteFurtoTodosAnos =0;


    // variaveis do bairro jussara ,ruas   ano 2019
    int contandoRuaRamiroLeiteJadeteRouboAno2019 =0;
    int contandoRuaAlameidaVianaJadeteRouboAno2019 =0;
    int contandoRuaInezitaJadeteRouboAno2019 =0;
    int contandoRuaIslaHJadeteRouboAno2019 =0;
    int contandoRuaSantoAntJadeteRouboAno2019 =0;
    int contandoRuaVianaJadeteRouboAno2019 =0;
    int contandoRuavinteDoisJadeteRouboAno2019 =0;
    int contandoRuaJoaoAlkimimJadeteRouboAno2019 =0;
    int contandoTvVianaJadeteRouboAno2019 =0;

    int contandoRuaRamiroLeiteJadeteFurtoAno2019 =0;
    int contandoRuaAlameidaVianaJadeteFurtoAno2019 =0;
    int contandoRuaInezitaJadeteFurtoAno2019 =0;
    int contandoRuaIslaHJadeteFurtoAno2019 =0;
    int contandoRuaSantoAntJadeteFurtoAno2019 =0;
    int contandoRuaVianaJadeteFurtoAno2019 =0;
    int contandoRuavinteDoisJadeteFurtoAno2019 =0;
    int contandoRuaJoaoAlkimimJadeteFurtoAno2019 =0;
    int contandoTvVianaJadeteFurtoAno2019 =0;



    int contandoRuaUmAltoPocoesFurtoTodosAnos=0;
    int contandoRuaDoisAltoPocoesFurtoTodosAnos=0;
    int  contandoRuaTresAltoPocoesFurtoTodosAnos=0;
    int contandoRuaQuatroAltoPocoesFurtoTodosAnos=0;
    int contandoRuaCincoAltoPocoesFurtoTodosAnos=0;
    int  contandoAvUmAltoPocoesFurtoTodosAnos=0;
    int contandoAvDoisRuaAltoPocoesFurtoTodosAnos=0;
    int contandoAvTresAltoPocoesFurtoTodosAnos=0;
    int contandoAvQuatroAltoPocoesFurtoTodosAnos=0;
    int contandoAvCincoAltoPocoesFurtoTodosAnos=0;



    int contandoRuaUmAltoPocoesRouboTodosAnos=0;
    int contandoRuaDoisAltoPocoesRouboTodosAnos=0;
    int  contandoRuaTresAltoPocoesRouboTodosAnos=0;
    int contandoRuaQuatroAltoPocoesRouboTodosAnos=0;
    int contandoRuaCincoAltoPocoesRouboTodosAnos=0;
    int  contandoAvUmAltoPocoesRouboTodosAnos=0;
    int contandoAvDoisRuaAltoPocoesRouboTodosAnos=0;
    int contandoAvTresAltoPocoesRouboTodosAnos=0;
    int contandoAvQuatroAltoPocoesRouboTodosAnos=0;
    int contandoAvCincoAltoPocoesRouboTodosAnos=0;



    int contandoRuaUmAltoPocoesFurto2019=0;
    int contandoRuaDoisAltoPocoesFurto2019=0;
    int  contandoRuaTresAltoPocoesFurto2019=0;
    int contandoRuaQuatroAltoPocoesFurto2019=0;
    int contandoRuaCincoAltoPocoesFurto2019=0;
    int  contandoAvUmAltoPocoesFurto2019=0;
    int contandoAvDoisRuaAltoPocoesFurto2019=0;
    int contandoAvTresAltoPocoesFurto2019=0;
    int contandoAvQuatroAltoPocoesFurto2019=0;
    int contandoAvCincoAltoPocoesFurto2019=0;



    int contandoRuaUmAltoPocoesRoubo2019=0;
    int contandoRuaDoisAltoPocoesRoubo2019=0;
    int  contandoRuaTresAltoPocoesRoubo2019=0;
    int contandoRuaQuatroAltoPocoesRoubo2019=0;
    int contandoRuaCincoAltoPocoesRoubo2019=0;
    int  contandoAvUmAltoPocoesRoubo2019=0;
    int contandoAvDoisRuaAltoPocoesRoubo2019=0;
    int contandoAvTresAltoPocoesRoubo2019=0;
    int contandoAvQuatroAltoPocoesRoubo2019=0;
    int contandoAvCincoAltoPocoesRoubo2019=0;



    int contandoRuaAterroAlvoradaFurtoTodosAnos=0;
    int contandoRuaUmAlvoradaFurtoTodosAnos=0;
    int contandoRuaAAlvoradaFurtoTodosAnos=0;
    int contandoRuaEAlvoradaFurtoTodosAnos=0;
    int contandoRuaJAlvoradaFurtoTodosAnos=0;
    int contandoRuaKAlvoradaFurtoTodosAnos=0;
    int contandoRuaLAlvoradaFurtoTodosAnos=0;
    int contandoRuaMAlvoradaFurtoTodosAnos=0;
    int contandoRuaNAlvoradaFurtoTodosAnos=0;
    int contandoRuaOAlvoradaFurtoTodosAnos=0;


    int contandoRuaAterroAlvoradaRouboTodosAnos=0;
    int contandoRuaUmAlvoradaRouboTodosAnos=0;
    int contandoRuaAAlvoradaRouboTodosAnos=0;
    int contandoRuaEAlvoradaRouboTodosAnos=0;
    int contandoRuaJAlvoradaRouboTodosAnos=0;
    int contandoRuaKAlvoradaRouboTodosAnos=0;
    int contandoRuaLAlvoradaRouboTodosAnos=0;
    int contandoRuaMAlvoradaRouboTodosAnos=0;
    int contandoRuaNAlvoradaRouboTodosAnos=0;
    int contandoRuaOAlvoradaRouboTodosAnos=0;



    int contandoRuaAterroAlvoradaFurto2019=0;
    int contandoRuaUmAlvoradaFurto2019=0;
    int contandoRuaAAlvoradaFurto2019=0;
    int contandoRuaEAlvoradaFurto2019=0;
    int contandoRuaJAlvoradaFurto2019=0;
    int contandoRuaKAlvoradaFurto2019=0;
    int contandoRuaLAlvoradaFurto2019=0;
    int contandoRuaMAlvoradaFurto2019=0;
    int contandoRuaNAlvoradaFurto2019=0;
    int contandoRuaOAlvoradaFurto2019=0;


    int contandoRuaAterroAlvoradaRoubo2019=0;
    int contandoRuaUmAlvoradaRoubo2019=0;
    int contandoRuaAAlvoradaRoubo2019=0;
    int contandoRuaEAlvoradaRoubo2019=0;
    int contandoRuaJAlvoradaRoubo2019=0;
    int contandoRuaKAlvoradaRoubo2019=0;
    int contandoRuaLAlvoradaRoubo2019=0;
    int contandoRuaMAlvoradaRoubo2019=0;
    int contandoRuaNAlvoradaRoubo2019=0;
    int contandoRuaOAlvoradaRoubo2019=0;




    int contandoRuaUmBandeirantesFurtoTodosAnos=0;
    int contandoRuaABandeirantesFurtoTodosAnos=0;
    int contandoRuaEBandeirantesFurtoTodosAnos=0;
    int contandoRuaJBandeirantesFurtoTodosAnos=0;
    int contandoRuaKBandeirantesFurtoTodosAnos=0;
    int contandoRuaMirabelaBandeirantesFurtoTodosAnos=0;
    int contandoTvMirabelaBandeirantesFurtoTodosAnos=0;
    int contandoTvJulioBandeirantesFurtoTodosAnos=0;

    int contandoRuaUmBandeirantesRouboTodosAnos=0;
    int contandoRuaABandeirantesRouboTodosAnos=0;
    int contandoRuaEBandeirantesRouboTodosAnos=0;
    int contandoRuaJBandeirantesRouboTodosAnos=0;
    int contandoRuaKBandeirantesRouboTodosAnos=0;
    int contandoRuaMirabelaBandeirantesRouboTodosAnos=0;
    int contandoTvMirabelaBandeirantesRouboTodosAnos=0;
    int contandoTvJulioBandeirantesRouboTodosAnos=0;




    int contandoRuaUmBandeirantesFurto2019=0;
    int contandoRuaABandeirantesFurto2019=0;
    int contandoRuaEBandeirantesFurto2019=0;
    int contandoRuaJBandeirantesFurto2019=0;
    int contandoRuaKBandeirantesFurto2019=0;
    int contandoRuaMirabelaBandeirantesFurto2019=0;
    int contandoTvMirabelaBandeirantesFurto2019=0;
    int contandoTvJulioBandeirantesFurto2019=0;

    int contandoRuaUmBandeirantesRoubo2019=0;
    int contandoRuaABandeirantesRoubo2019=0;
    int contandoRuaEBandeirantesRoubo2019=0;
    int contandoRuaJBandeirantesRoubo2019=0;
    int contandoRuaKBandeirantesRoubo2019=0;
    int contandoRuaMirabelaBandeirantesRoubo2019=0;
    int contandoTvMirabelaBandeirantesRoubo2019=0;
    int contandoTvJulioBandeirantesRoubo2019=0;




    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;




    private BarChart barChart;



    private String[] nomes   = new String []{};
    private int[]    roubos = new int   []{};
    private int []   cores   = new int   []{};
    private String[] legenda  = new String[]{};
    private int[]    furtos = new int   []{};
    int []   cor   = new int   []{Color.YELLOW,Color.RED};  // ALTERAR a cor da legenda aq
    String[] legendaGrafico  = new String[]{"Furto","Roubo"}; // ALTERAR a nome da legenda aq




    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;
    CharSequence	convertSelectionToString;

    String  autoComplete;

    int spinnerCorrente = 0;
    int todosAnos=0;
    int ano2018=1;
    int ano2019=2;



    Bike b;
    String procuraBairro;
    String procuraAno;

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





        Query query;


        //Instânciar objetos
        listBikes = new ArrayList<>();




        query = databaseReference.child("TodasBikes");


        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                listBikes.clear();  //limpa lista

                // verifica itens da lista

                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    b = objSnapshot.getValue(Bike.class);
                    listBikes.add(b);



                     procuraAno = b.getAlertaDate();
                     procuraBairro = b.getAlertaBairro();
                    String procuraRua = b.getAlertaRua();



//procura as ruas ...

                    // todos os anos



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





                    ruasBairroAeroportoTodosAnos();
                    ruasBairroJadeteTodosAnos();
                    ruasBairroAltoPocoesTodosAnos();
                    ruasBairroAlvoradaTodosAnos();
                    ruasBairroBandeirantesTodosAnos();





                    ruasBairroAeroporto2019();
                    ruasBairroJadete2019();
                    ruasBairroAltoPocoes2019();
                    ruasBairroAlvorada2019();
                    ruasBairroBandeirantes2019();

















        autoCompletegraficoRuaBarra.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
          @Override
          public void onDismiss() {



              // opção para combinar com o spninner todos os anos

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto") && spinnerCorrente == todosAnos){



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

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes") && spinnerCorrente ==todosAnos) {


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


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete") && spinnerCorrente ==todosAnos) {




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

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes") && spinnerCorrente ==ano2018) {


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


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete") && spinnerCorrente ==ano2018) {


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

              if(autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes") && spinnerCorrente ==ano2019) {


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


              if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete") && spinnerCorrente ==ano2019) {


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


                         spinnerCorrente =0;

                         centroTodosAnos();





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


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes")){


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

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete")){




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


                         spinnerCorrente =1;


                         centro2018();




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


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes")){


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

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete")){


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





                     }else if (position==2) {  //2019




                         spinnerCorrente=2;


                         centro2019();


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


                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Bandeirantes")){


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

                         if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Jadete")){


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




                }


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







    // CRIA OS GRAFICOS

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

        set2.setColor(Color.YELLOW);
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








    /// opção todos os bairros no spinner
    // dados para geração de cada grafico


    public void aeroportoTodosAnos(){





        String[] bairros  = new String[]{"Av. Aeroporto","Av. Seis","R. Um ","R. Dois","R. Três","R. Quatro","R. Vinte e Três","R. XI"};
        int[]    rob = new int   []{contandoAvAeroportoAeroportoRouboTodosAnos,contandoAvSeisAeroportoRouboTodosAnos,contandoRuaRUmAeroportoRouboTodosAnos,contandoRuaRDoisAeroportoRouboTodosAnos,contandoRuaRTresAeroportoRouboTodosAnos,
                contandoRuaRQuatroAeroportoRouboTodosAnos,contandoRuavinteTresAeroportoRouboTodosAnos,contandoRuaRXIAeroportoRouboTodosAnos};

        int[]    furt =  new int   []{contandoAvAeroportoAeroportoFurtoTodosAnos,contandoAvSeisAeroportoFurtoTodosAnos,contandoRuaRUmAeroportoFurtoTodosAnos,contandoRuaRDoisAeroportoFurtoTodosAnos,contandoRuaRTresAeroportoFurtoTodosAnos,
                contandoRuaRQuatroAeroportoFurtoTodosAnos,contandoRuavinteTresAeroportoFurtoTodosAnos,contandoRuaRXIAeroportoFurtoTodosAnos};


        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furt;


        criarGraficos();

    }

    public void alameidaTodosAnos(){

        //xx

        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};


        int[] furtAlameda = new int[]{5, 10, 6, 10};


        nomes = alameda;
        roubos = robAlameda;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtAlameda;


        criarGraficos();



    } //Não consta no google

    public void altoCemitérioTodosAnos(){

        //xx

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério", "Rua 12"};
        int[] robAltoCemitério = new int[]{10, 10, 10, 10};
        int[] furtAltoCemitério = new int[]{5, 10, 6, 10};


        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtAltoCemitério;


        criarGraficos();

    }//Não consta no google

    public void altoPocoesTodosAnos(){

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","Av. Um","Av. Dois", "Av. Três", "Av. Quatro", "Av. Cinco"};
        int[]    valores = new int   []{contandoRuaUmAltoPocoesRouboTodosAnos,
                contandoRuaDoisAltoPocoesRouboTodosAnos,
                contandoRuaTresAltoPocoesRouboTodosAnos,
                contandoRuaQuatroAltoPocoesRouboTodosAnos,
                contandoRuaCincoAltoPocoesRouboTodosAnos,
                contandoAvUmAltoPocoesRouboTodosAnos,
                contandoAvDoisRuaAltoPocoesRouboTodosAnos,
                contandoAvTresAltoPocoesRouboTodosAnos,
                contandoAvQuatroAltoPocoesRouboTodosAnos,
                contandoAvCincoAltoPocoesRouboTodosAnos};


        int[]    furtosRuas =  new int   []{contandoRuaUmAltoPocoesFurtoTodosAnos,
                contandoRuaDoisAltoPocoesFurtoTodosAnos,
                contandoRuaTresAltoPocoesFurtoTodosAnos
                ,contandoRuaQuatroAltoPocoesFurtoTodosAnos,
                contandoRuaCincoAltoPocoesFurtoTodosAnos,
                contandoAvUmAltoPocoesFurtoTodosAnos,
                contandoAvDoisRuaAltoPocoesFurtoTodosAnos,
                contandoAvTresAltoPocoesFurtoTodosAnos,
                contandoAvQuatroAltoPocoesFurtoTodosAnos,
                contandoAvCincoAltoPocoesFurtoTodosAnos};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();



    }

    public void alvoradaTodosAnos(){

        String[] ruas  = new String[]{"R. do Aterro","R. Um ","R. A","R. E","R. J","R. K","R. L","R. M","R. N","R. O"};
        int[]    valores = new int   []{contandoRuaAterroAlvoradaRouboTodosAnos,
                contandoRuaUmAlvoradaRouboTodosAnos,
                contandoRuaAAlvoradaRouboTodosAnos,
                contandoRuaEAlvoradaRouboTodosAnos,
                contandoRuaJAlvoradaRouboTodosAnos,
                contandoRuaKAlvoradaRouboTodosAnos,
                contandoRuaLAlvoradaRouboTodosAnos,
                contandoRuaMAlvoradaRouboTodosAnos,
                contandoRuaNAlvoradaRouboTodosAnos,
                contandoRuaOAlvoradaRouboTodosAnos};

        int[]    furtosRuas = new int   []{contandoRuaAterroAlvoradaFurtoTodosAnos,
                contandoRuaUmAlvoradaFurtoTodosAnos,
                contandoRuaAAlvoradaFurtoTodosAnos,
                contandoRuaEAlvoradaFurtoTodosAnos,
                contandoRuaJAlvoradaFurtoTodosAnos,
                contandoRuaKAlvoradaFurtoTodosAnos,
                contandoRuaLAlvoradaFurtoTodosAnos,
                contandoRuaMAlvoradaFurtoTodosAnos,
                contandoRuaNAlvoradaFurtoTodosAnos,
                contandoRuaOAlvoradaFurtoTodosAnos};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();

    }

    public void bandeirantesTodosAnos(){


        String[] ruas  = new String[]{"R. Um","R. Dois","R. Dez","R. Brasília de Minas","R. 15 de Novembro","R. Mirabela","Tv.Mirabela","Tv. Júlio Magalhães"};
        int[]    valores = new int   []{ contandoRuaUmBandeirantesRouboTodosAnos,
                contandoRuaABandeirantesRouboTodosAnos,
                contandoRuaEBandeirantesRouboTodosAnos,
                contandoRuaJBandeirantesRouboTodosAnos,
                contandoRuaKBandeirantesRouboTodosAnos,
                contandoRuaMirabelaBandeirantesRouboTodosAnos,
                contandoTvMirabelaBandeirantesRouboTodosAnos,
                contandoTvJulioBandeirantesRouboTodosAnos};

        int[]    furtosRuas = new int   []{ contandoRuaUmBandeirantesFurtoTodosAnos,
                contandoRuaABandeirantesFurtoTodosAnos,
                contandoRuaEBandeirantesFurtoTodosAnos,
                contandoRuaJBandeirantesFurtoTodosAnos,
                contandoRuaKBandeirantesFurtoTodosAnos,
                contandoRuaMirabelaBandeirantesFurtoTodosAnos,
                contandoTvMirabelaBandeirantesFurtoTodosAnos,
                contandoTvJulioBandeirantesFurtoTodosAnos};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();

    }

    private void belaVistaTodosAnos() {

        ///XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void boaEsperançaTodosAnos() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Treze de Maio","R. Luís Tupiná","R. Hermílio Tupiná ","R. São Geraldo"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void boaVistaTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G","R. H","R. I","R. J","R. L"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void brasilinaTodosAnos() {


        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinto","R. Seis","R. Sete","R. Minas Gerais","R. Pirapora","R. São Luiz"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();

    }

    private void brejoDoAmparoTodosAnos() {

        String[] ruas  = new String[]{"Alameda do Riacho","R. Bela Vista","R. da Serra","R. Flôres","R. Padre Josino","R. Padre Ramiro","R. Tabatinga","R. Taboca","R. Trinta e Sete","R. Trinta e Oito"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void caicTodosAnos() {


        //XX  caic fica no Dom joão Batista

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    } //Não consta no google

    private void centroTodosAnos() {

        String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};
        int[]    valores = new int    []{10,15,14,20,10,15,14,20,23,65};

        int[]    furtosRuas = new int   []{5,10,6,10,5,10,6,10,65,34};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void ceramicaTodosAnos() {

        String[] ruas  = new String[]{"R. Aterro","R. Brasília de Minas","R. Itacarambi","R. Itapiraçaba","R. Manga","R. Montes Claros","R. Montalvânia","R. Vazelândia","R. Milton Campos", "R. Milton Sá"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void eldoradoTodosAnos() {

        String[] ruas  = new String[]{"Av. Seis","R. A","R. B","R. C","R. D","R. E","R. G","R. J","R. L","R. M"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void fabiaoTodosAnos() {


        //XXX zona ruaral

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void franklinTodosAnos() {


        String[] ruas  = new String[]{"R. J. Antônio do Vale Filho","R. L","R. Vinte e Três","R. Vinte e Quatro","R. Vinte e Cinco","R. Vinte e Oito"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jadeteTodosAnos() {

        String[] ruas  = new String[]{"Av. Cônego R. Leite","Alameda V","R. Inezita A. Ferreira","R. Isla H. Azevedo","R. S. Antônio","R. Viana","R. vinte e Dois","R. Ver João Alkimim","Tv. Viana"};
        int[]    valores = new int   []{contandoRuaRamiroLeiteJadeteRouboTodosAnos, contandoRuaAlameidaVianaJadeteRouboTodosAnos, contandoRuaInezitaJadeteRouboTodosAnos,
                contandoRuaIslaHJadeteRouboTodosAnos, contandoRuaSantoAntJadeteRouboTodosAnos
        , contandoRuaVianaJadeteRouboTodosAnos, contandoRuavinteDoisJadeteRouboTodosAnos, contandoRuaJoaoAlkimimJadeteRouboTodosAnos, contandoTvVianaJadeteRouboTodosAnos};


        int[]    furtosRuas =new int   []{contandoRuaRamiroLeiteJadeteFurtoTodosAnos, contandoRuaAlameidaVianaJadeteFurtoTodosAnos, contandoRuaInezitaJadeteFurtoTodosAnos,
                contandoRuaIslaHJadeteFurtoTodosAnos, contandoRuaSantoAntJadeteFurtoTodosAnos
                , contandoRuaVianaJadeteFurtoTodosAnos, contandoRuavinteDoisJadeteFurtoTodosAnos, contandoRuaJoaoAlkimimJadeteFurtoTodosAnos, contandoTvVianaJadeteFurtoTodosAnos};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void januariaTodosAnos() {


        //xxx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void jardimDanielTodosAnos() {


        String[] ruas  = new String[]{"R. Um","Rua Dois","R. Três","R. Quatro","R. Alagoas","R. Bela Vista","Av. Cônego Ramiro Leite","R. Inezita A Ferreira","R. Minas Gerais"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jardimEstelaTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. G","R. H","R. Barão de São Romão","R. João Gasparino","R. Vinte e Dois","R. Vinte e Três"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jatobaTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }    //Não consta no google

    private void joventinaMesquitaTodosAnos() {


        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void jussaraTodosAnos() {



        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Sete","R. Oito","R. Nove","R. Dez"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void levianopolisTodosAnos() {

        String[] ruas  = new String[]{"R. Seis", "R. Sete", "R. Oito","R. Nove","R. Vinte", "R. Anízio G Moreira ","R. H Caciquinho","P. Emilio de Matos","R. J.A do vale Filho"};
        int[]    valores = new int   []{10,10,10,10};
        cores   =  cor;
        legenda  = legendaGrafico;
        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void mangueirasTodosAnos() {


        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }   //Não consta no google

    private void margareteTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }   //Não consta no google

    private void moradeirasTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }   //Não consta no google

    private void normaConsueloTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }   //Não consta no google

    private void novoMilenioTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    } //Não consta no google

    private void quintaMangabeirasTodosAnos() {


        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. do Bonde","R. T.Bastos"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void riachoDaCruzTodosAnos() {

        String[] ruas  = new String[]{"R. Murici","R. Café Mineiro","R. Antônio C.da Silva","R. Manoel J.de Souza","R. Oliveira Pôrto","R. Tertuliano R.Pôrto","Tv. J.F.Melo"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoJoaoTodosAnos() {

        String[] ruas  = new String[]{"Av.Cel.Cassiano","Tv.Leão Treze","R. Um","R. Dois","R. B.J Ferreira","R. do Curtume","R. Trinta de Maio","R. J.Augusto","R. Mal.Floriano Peixoto"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoJoaquimTodosAnos() {



        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Seis","R. Oito","R. Nove","R. Dez","R. Doze","R. S.Inês}","R. S.Maria"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoMiguelTodosAnos() {


        String[] ruas  = new String[]{"Av.São Francisco","R. Um", "R. Dois","R. Três", "R. A", "R. B","R. D","R. Pirapora","R. Montavânia","Tv.Galiléia"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoVicenteTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. B","R. E.T.Bastos","R. L.N.Neto","R. Maria C.Carvalho","R. Olibrio Lima","R. T.Torres","R. Sebastião F.Lima"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void sagradaFamiliaTodosAnos() {


        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sagrada Família","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void santaCruzTodosAnos() {


        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Cruz","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void santaIsabelTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Isabel","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void sedeTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sede","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void tejucoTodosAnos() {



        String[] ruas  = new String[]{"R. Castelo Branco","R. J.kubitschek","R. Tancredo Neves","R. Tejuco","R. Tropical","R. São João","R. São José","R. M.A.Carvalho"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void terceiroMilenioTodosAnos() {

        // XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Terceiro Milênio","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void tropicalTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tropical","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void varzeaDosPocoesTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Várzea dos Pocões","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    } //Não consta no google

    private void vilaFatimaTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G.P.Rocha","R. J.P.Carvalho","R. Joaquim Fernandes","R. J.Augusto"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void pandeirosTodosAnos() {

        String[] ruas  = new String[]{"R. J.B.Gobira","R. M.Moreira"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaPaulaTodosAnos() {

        String[] ruas  = new String[]{"Av.São Fransisco","Tv.J.Nunes","Tv.J.Moura","R. L.N.Neto","R. A.J.Rocha","R. J.Moura","R. J.Nunes","R. S.F.Lima","R. Srg.Monzart"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaVerdeTodosAnos() {

        String[] ruas  = new String[]{"Av.Mal.Deodoro da Fonseca","R. A","Rua Aterro","R. B","R. D","R. O","R. R","Rua T","R. U","R. V"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaVianaTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. Arthur L.Pimenta","R. B","R. D","R. E","R. F","R. G","R. R","R. X"};
        int[]    valores = new int   []{10,10,10,10};
        ;
        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void zonaRuralTodosAnos() {

        String[] ruas  = new String[]{"Zona Rural"};
        int[]    valores = new int   []{10};

        int[]    furtosRuas = new int   []{5};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }








    ///  /// opção 2018 no spinnner
    // dados para geração de cada grafico


/// alimentar manualmente


    public void aeroporto2018(){


        String[] bairros  = new String[]{"Av. Aeroporto","Av. Seis","R. Um ","R. Dois","R. Três","R. Quatro","R. Vinte e Três","R. XI"};
        int[]    rob = new int   []{12,12,10,12};

        int[]    furt = new int   []{5,10,6,10};



        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furt;


        criarGraficos();

    }

    public void alameida2018(){
        //XX

        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda2018", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};

        int[] furtAlameda = new int[]{5, 10, 6, 10};


        nomes = alameda;
        roubos = robAlameda;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtAlameda;


        criarGraficos();



    } //Não consta no google

    public void altoCemitério2018(){

        //XX

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério2018", "Rua 12"};
        int[] robAltoCemitério = new int[]{10, 10, 10, 10};

        int[] furtAltoCemitério = new int[]{5, 10, 6, 10};


        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtAltoCemitério;


        criarGraficos();

    } //Não consta no google

    public void altoPocoes2018(){

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","Av. Um","Av. Dois", "Av. Três", "Av. Quatro", "Av. Cinco"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();



    }

    public void alvorada2018(){

        String[] ruas  = new String[]{"R. do Aterro","R. Um ","R. A","R. E","R. J","R. K","R. L","R. M","R. N","R. O"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();

    }

    public void bandeirantes2018(){


        String[] ruas  = new String[]{"R. Um","R. Dois","R. Dez","R. Brasília de Minas","R. 15 de Novembro","R. Mirabela","Tv. Mirabela","Tv. Júlio Magalhães"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();

    }

    private void belaVista2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void boaEsperança2018() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Treze de Maio","R. Luís Tupiná","R. Hermílio Tupiná ","R. São Geraldo"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void boaVista2018() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G","R. H","R. I","R. J","R. L"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void brasilina2018() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinto",". Seis","R. Sete","R. Minas Gerais","R. Pirapora","R. São Luiz"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void brejoDoAmparo2018() {

        String[] ruas  = new String[]{"Alameda do Riacho","R. Bela Vista","R. da Serra","R. Flôres","R. Padre Josino","R. Padre Ramiro","R. Tabatinga","R. Taboca","R. Trinta e Sete","R. Trinta e Oito"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void caic2018() {

        //XX  caic fica no Dom joão Batista

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void centro2018() {

        String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void ceramica2018() {

        String[] ruas  = new String[]{"R. Aterro","R. Brasília de Minas","R. Itacarambi","R. Itapiraçaba","R. Manga","R. Montes Claros","R. Montalvânia","R. Vazelândia","R. Milton Campos", "R. Milton Sá"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void eldorado2018() {

        String[] ruas  = new String[]{"Av. Seis","R. A","R. B","R. C","R. D","R. E","R. G","R. J","R. L","R. M"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void fabiao2018() {


        //XXX zona ruaral

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void franklin2018() {

        String[] ruas  = new String[]{"R. J. Antônio do Vale Filho","R. L","Rua Vinte e Três","R. Vinte e Quatro","R. Vinte e Cinco","R. Vinte e Oito"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jadete2018() {

        String[] ruas  = new String[]{"Av. Cônego R. Leite","Alameda V","R. Inezita A. Ferreira","R. Isla H. Azevedo","R. S. Antônio","R. Viana","R. vinte e Dois","R. Ver João Alkimim","Tv. Viana"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void januaria2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void jardimDaniel2018() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Alagoas","R. Bela Vista","Av. Cônego Ramiro Leite","R. Inezita A Ferreira","R. Minas Gerais"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jardimEstela2018() {





        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. G","R. H","R. Barão de São Romão","R. João Gasparino","R. Vinte e Dois","R. Vinte e Três"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jatoba2018() {


        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void joventinaMesquita2018() {

        ///XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};
     ;
        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void jussara2018() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Sete","R. Oito","R. Nove","R. Dez"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void levianopolis2018() {

        String[] ruas  = new String[]{"R. Seis", "R. Sete", "R. Oito","R. Nove","R. Vinte", "R. Anízio G Moreira ","R. H Caciquinho","P. Emilio de Matos","R. J.A do vale Filho"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void mangueiras2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void margarete2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void moradeiras2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void normaConsuelo2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    } //Não consta no google

    private void novoMilenio2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void quintaMangabeiras2018() {



        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. do Bonde","R. T.Bastos"};

        int[]    valores = new int   []{10,10,10,10};
      ;
        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void riachoDaCruz2018() {

        String[] ruas  = new String[]{"R. Murici","R. Café Mineiro","R. Antônio C.da Silva","R. Manoel J.de Souza","R. Oliveira Pôrto","R. Tertuliano R.Pôrto","Tv. J.F.Melo"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoJoao2018() {

        String[] ruas  = new String[]{"Av. Cel.Cassiano","Tv. Leão Treze","R. Um","R. Dois","R. B.J Ferreira","R. do Curtume","R. Trinta de Maio","R. J.Augusto","R. Mal.Floriano Peixoto"};
        int[]    valores = new int   []{10,10,10,10};
    ;
        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoJoaquim2018() {



        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Seis","R. Oito","R. Nove","R. Dez","R. Doze","R. S.Inês}","R. S.Maria"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoMiguel2018() {

        String[] ruas  = new String[]{"Av. São Francisco","R. Um", "R. Dois","R. Três", "R. A", "R. B","R. D","R. Pirapora","R. Montavânia","Tv. Galiléia"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoVicente2018() {


        String[] ruas  = new String[]{"R. A","R. B","R. E.T.Bastos","R. L.N.Neto","R. Maria C.Carvalho","R. Olibrio Lima","R. T.Torres","R. Sebastião F.Lima"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void sagradaFamilia2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sagrada Família2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    } //Não consta no google

    private void santaCruz2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Cruz2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void santaIsabel2018() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Isabel2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void sede2018() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sede2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }   //Não consta no google

    private void tejuco2018() {

        String[] ruas  = new String[]{"R. Castelo Branco","R. J.kubitschek","R. Tancredo Neves","R. Tejuco","R. Tropical","R. São João","R. São José","R. M.A.Carvalho"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void terceiroMilenio2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Terceiro Milênio2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  //Não consta no google

    private void tropical2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tropical2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    } //Não consta no google

    private void varzeaDosPocoes2018() {

        ///xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Várzea dos Pocões2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    } //Não consta no google

    private void vilaFatima2018() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G.P.Rocha","R. J.P.Carvalho","R. Joaquim Fernandes","R. J.Augusto"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void pandeiros2018() {

        String[] ruas  = new String[]{"R. J.B.Gobira","R. M.Moreira"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaPaula2018() {

        String[] ruas  = new String[]{"Av. São Fransisco","Tv. J.Nunes","Tv. J.Moura","R. L.N.Neto","R. A.J.Rocha","R .J.Moura","R. J.Nunes","R. S.F.Lima","R. Srg.Monzart"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaVerde2018() {

        String[] ruas  = new String[]{"Av. Mal.Deodoro da Fonseca","R. A","R. Aterro","R. B","R. D","R. O","R. R","R. T","R. U","R. V"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaViana2018() {

        String[] ruas  = new String[]{"R. A","R. Arthur L.Pimenta","R. B","R. D","R. E","R. F","R. G","R. R","R. X"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void zonaRural2018() {

        String[] ruas  = new String[]{"Zona Rural"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }



    ///

    ///2019

    ///  /// opção 2019 no spinnner bAirro
    // dados para geração de cada grafico




    public void aeroporto2019(){


        String[] bairros  = new String[]{"Av. Aeroporto","Av. Seis","R. Um ","R. Dois","R. Três","R. Quatro","R. Vinte e Três","R. XI"};
        int[]    rob = new int   []{contandoAvAeroportoAeroportoRoubo2019,contandoAvSeisAeroportoRoubo2019,contandoRuaRUmAeroportoRoubo2019,contandoRuaRDoisAeroportoRoubo2019,contandoRuaRTresAeroportoRoubo2019,
                contandoRuaRQuatroAeroportoRoubo2019,contandoRuavinteTresAeroportoRoubo2019,contandoRuaRXIAeroportoRoubo2019};

        int[]    furt = new int   []{contandoAvAeroportoAeroportoFurtada2019,contandoAvSeisAeroportoFurtada2019,contandoRuaRUmAeroportoFurtada2019,contandoRuaRDoisAeroportoFurtada2019,contandoRuaRTresAeroportoFurtada2019,
                contandoRuaRQuatroAeroportoFurtada2019,contandoRuavinteTresAeroportoFurtada2019,contandoRuaRXIAeroportoFurtada2019};


        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furt;


        criarGraficos();

    }

    public void alameida2019(){
 //xxx
        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda2019", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};

        int[] furtAlameda = new int[]{5, 10, 6, 10};


        nomes = alameda;
        roubos = robAlameda;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtAlameda;


        criarGraficos();



    } // não consta no google

    public void altoCemitério2019(){


        //xxx

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério2019", "Rua 12"};
        int[] robAltoCemitério = new int[]{10, 10, 10, 10};

        int[] furtAltoCemitério = new int[]{5, 10, 6, 10};


        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtAltoCemitério;


        criarGraficos();

    }  // não consta no google

    public void altoPocoes2019(){

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","Av. Um","Av. Dois", "Av. Três", "Av. Quatro", "Av. Cinco"};

        int[]    valores = new int   []{contandoRuaUmAltoPocoesRoubo2019,
                contandoRuaDoisAltoPocoesRoubo2019,
                contandoRuaTresAltoPocoesRoubo2019,
                contandoRuaQuatroAltoPocoesRoubo2019,
                contandoRuaCincoAltoPocoesRoubo2019,
                contandoAvUmAltoPocoesRoubo2019,
                contandoAvDoisRuaAltoPocoesRoubo2019,
                contandoAvTresAltoPocoesRoubo2019,
                contandoAvQuatroAltoPocoesRoubo2019,
                contandoAvCincoAltoPocoesRoubo2019};


        int[]    furtosRuas =  new int   []{contandoRuaUmAltoPocoesFurto2019,
                contandoRuaDoisAltoPocoesFurto2019,
                contandoRuaTresAltoPocoesFurto2019,
                contandoRuaQuatroAltoPocoesFurto2019,
                contandoRuaCincoAltoPocoesFurto2019,
                contandoAvUmAltoPocoesFurto2019,
                contandoAvDoisRuaAltoPocoesFurto2019,
                contandoAvTresAltoPocoesFurto2019,
                contandoAvQuatroAltoPocoesFurto2019,
                contandoAvCincoAltoPocoesFurto2019};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();



    }

    public void alvorada2019(){


        String[] ruas  = new String[]{"R. do Aterro","R. Um ","R. A","R. E","R. J","R. K","R. L","R. M","R. N","R. O"};
        int[]    valores = new int   []{contandoRuaAterroAlvoradaRoubo2019,
                contandoRuaUmAlvoradaRoubo2019,
                contandoRuaAAlvoradaRoubo2019,
                contandoRuaEAlvoradaRoubo2019,
                contandoRuaJAlvoradaRoubo2019,
                contandoRuaKAlvoradaRoubo2019,
                contandoRuaLAlvoradaRoubo2019,
                contandoRuaMAlvoradaRoubo2019,
                contandoRuaNAlvoradaRoubo2019,
                contandoRuaOAlvoradaRoubo2019};

        int[]    furtosRuas = new int   []{contandoRuaAterroAlvoradaFurto2019,
                contandoRuaUmAlvoradaFurto2019,
                contandoRuaAAlvoradaFurto2019,
                contandoRuaEAlvoradaFurto2019,
                contandoRuaJAlvoradaFurto2019,
                contandoRuaKAlvoradaFurto2019,
                contandoRuaLAlvoradaFurto2019,
                contandoRuaMAlvoradaFurto2019,
                contandoRuaNAlvoradaFurto2019,
                contandoRuaOAlvoradaFurto2019};


        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();

    }

    public void bandeirantes2019(){


        String[] ruas  = new String[]{"R. Um","R. Dois","R. Dez","R. Brasília de Minas","R. 15 de Novembro","R. Mirabela","Tv.Mirabela","Tv. Júlio Magalhães"};

        int[]    valores = new int   []{ contandoRuaUmBandeirantesRoubo2019,
                contandoRuaABandeirantesRoubo2019,
                contandoRuaEBandeirantesRoubo2019,
                contandoRuaJBandeirantesRoubo2019,
                contandoRuaKBandeirantesRoubo2019,
                contandoRuaMirabelaBandeirantesRoubo2019,
                contandoTvMirabelaBandeirantesRoubo2019,
                contandoTvJulioBandeirantesRoubo2019};

        int[]    furtosRuas = new int   []{ contandoRuaUmBandeirantesFurto2019,
                contandoRuaABandeirantesFurto2019,
                contandoRuaEBandeirantesFurto2019,
                contandoRuaJBandeirantesFurto2019,
                contandoRuaKBandeirantesFurto2019,
                contandoRuaMirabelaBandeirantesFurto2019,
                contandoTvMirabelaBandeirantesFurto2019,
                contandoTvJulioBandeirantesFurto2019};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();

    }

    private void belaVista2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void boaEsperança2019() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Treze de Maio","R. Luís Tupiná","R. Hermílio Tupiná ","R. São Geraldo"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void boaVista2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G","R. H","R. I","R. J","R. L"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void brasilina2019() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinto","R. Seis","R. Sete","R. Minas Gerais","R. Pirapora","R. São Luiz"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void brejoDoAmparo2019() {

        String[] ruas  = new String[]{"Alameda do Riacho","R. Bela Vista","R. da Serra","R. Flôres","R. Padre Josino","R. Padre Ramiro","R. Tabatinga","R. Taboca","R. Trinta e Sete","R. Trinta e Oito"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void caic2019() {

        //XX  caic fica no Dom joão Batista

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }   // não consta no google

    private void centro2019() {

        String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void ceramica2019() {


        String[] ruas  = new String[]{"R. Aterro","R. Brasília de Minas","R. Itacarambi","R. Itapiraçaba","R. Manga","R. Montes Claros","R. Montalvânia","R. Vazelândia","R. Milton Campos", "R. Milton Sá"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void eldorado2019() {


        String[] ruas  = new String[]{"Av. Seis","R. A","R. B","R. C","R. D","R. E","R. G","R. J","R. L","R. M"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void fabiao2019() {


        //XXX zona ruaral


        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void franklin2019() {

        String[] ruas  = new String[]{"R. J. Antônio do Vale Filho","R. L","R. Vinte e Três","R. Vinte e Quatro","R. Vinte e Cinco","R. Vinte e Oito"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jadete2019() {

        String[] ruas  = new String[]{"Av. Cônego R. Leite","Alameda V","R. Inezita A. Ferreira","R. Isla H. Azevedo","R. S. Antônio","R. Viana","R. vinte e Dois","R. Ver João Alkimim","Tv. Viana"};

        int[]    valores =  new int   []{contandoRuaRamiroLeiteJadeteRouboAno2019, contandoRuaAlameidaVianaJadeteRouboAno2019, contandoRuaInezitaJadeteRouboAno2019,
                contandoRuaIslaHJadeteRouboAno2019, contandoRuaSantoAntJadeteRouboAno2019
                , contandoRuaVianaJadeteRouboAno2019, contandoRuavinteDoisJadeteRouboAno2019, contandoRuaJoaoAlkimimJadeteRouboAno2019, contandoTvVianaJadeteRouboAno2019};

        int[]    furtosRuas = new int   []{contandoRuaRamiroLeiteJadeteFurtoAno2019, contandoRuaAlameidaVianaJadeteFurtoAno2019, contandoRuaInezitaJadeteFurtoAno2019,
                contandoRuaIslaHJadeteFurtoAno2019, contandoRuaSantoAntJadeteFurtoAno2019
                , contandoRuaVianaJadeteFurtoAno2019, contandoRuavinteDoisJadeteFurtoAno2019, contandoRuaJoaoAlkimimJadeteFurtoAno2019, contandoTvVianaJadeteFurtoAno2019};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void januaria2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void jardimDaniel2019() {


        String[] ruas  = new String[]{"R. Um","R. Dois","Rua Três","R. Quatro","R. Alagoas","R. Bela Vista","Av. Cônego R. Leite","Rua Inezita A. Ferreira","R. Minas Gerais"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jardimEstela2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","Rua G","R. H","R. Barão de São Romão","R. João Gasparino","R. Vinte e Dois","R. Vinte e Três"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void jatoba2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void joventinaMesquita2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};
 ;
        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void jussara2019() {


        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Sete","R. Oito","R. Nove","R. Dez"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void levianopolis2019() {

        String[] ruas  = new String[]{"R. Seis", "R. Sete", "R. Oito","R. Nove","R. Vinte", "R. Anízio G. Moreira ","R. H. Caciquinho","P. Emilio de Matos","R. J.A Vale Filho"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void mangueiras2019() {

        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void margarete2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void moradeiras2019() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }// não consta no google

    private void normaConsuelo2019() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void novoMilenio2019() {

        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void quintaMangabeiras2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. do Bonde","R. T.Bastos"};

        int[]    valores = new int   []{10,10,10,10};
       ;
        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void riachoDaCruz2019() {

        String[] ruas  = new String[]{"R. Murici","R. Café Mineiro","R. Antônio C.da Silva","R. Manoel J.de Souza","R. Oliveira Pôrto","R. Tertuliano R.Pôrto","Tv. J.F.Melo"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoJoao2019() {


        String[] ruas  = new String[]{"Av.Cel.Cassiano","Tv.Leão Treze","R. Um","R. Dois","R. B.J Ferreira","R. do Curtume","R. Trinta de Maio","R. J.Augusto","R. Mal.Floriano Peixoto"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoJoaquim2019() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Seis","R. Oito","R. Nove","R. Dez","R. Doze","R. S.Inês}","R. S.Maria"};


        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoMiguel2019() {

        String[] ruas  = new String[]{"Av.São Francisco","R. Um", "R. Dois","Rua Três", "R. A", "R. B","R. D","R. Pirapora","R. Montavânia","Tv.Galiléia"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void saoVicente2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. E.T.Bastos","R. L.N.Neto","R. Maria C.Carvalho","R. Olibrio Lima","R. T.Torres","R. Sebastião F.Lima"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void sagradaFamilia2019() {

        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sagrada Família2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void santaCruz2019() {
        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Cruz2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void santaIsabel2019() {
        //xx   O google não mostra ruas nesse bairro

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Isabel2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void sede2019() {
        ///XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sede2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }   // não consta no google

    private void tejuco2019() {

        String[] ruas  = new String[]{"R. Castelo Branco","R. J.kubitschek","R. Tancredo Neves","R. Tejuco","R. Tropical","R. São João","R. São José","R. M.A.Carvalho"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void terceiroMilenio2019() {

        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Terceiro Milênio2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void tropical2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tropical2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void varzeaDosPocoes2019() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Várzea dos Pocões2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }  // não consta no google

    private void vilaFatima2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G.P.Rocha","R. J.P.Carvalho","R. Joaquim Fernandes","R. J.Augusto"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void pandeiros2019() {

        String[] ruas  = new String[]{"R. J.B.Gobira","R. M.Moreira"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaPaula2019() {

        String[] ruas  = new String[]{"Av.São Fransisco","Tv.J.Nunes","Tv.J.Moura","R. L.N.Neto","R. A.J.Rocha","R. J.Moura","R. J.Nunes","R. S.F.Lima","R. Srg.Monzart"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaVerde2019() {

        String[] ruas  = new String[]{"Av.Mal.Deodoro da Fonseca","R. A","R. Aterro","R. B","R. D","R. O","R. R","R. T","R. U","R. V"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void vilaViana2019() {

        String[] ruas  = new String[]{"R. A","Rua Arthur L.Pimenta","R. B","R. D","R. E","R. F","R. G","R. R","R. X"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }

    private void zonaRural2019() {


        String[] ruas  = new String[]{"Zona Rural"};

        int[]    valores = new int   []{10,10,10,10};

        int[]    furtosRuas = new int   []{5,10,6,10};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;
        furtos = furtosRuas;


        criarGraficos();


    }





    ///  /// opção no spinnner Ruas
    // contagem de furtos e roubos por rua



    public void ruasBairroAeroportoTodosAnos(){




        if (procuraBairro.contains("Aeroporto") && b.getStatus().equals("Roubada")){



            if (b.getAlertaRua().equals("Avenida Aeroporto")){

                contandoAvAeroportoAeroportoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Av. Seis")){

                contandoAvSeisAeroportoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaRUmAeroportoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaRDoisAeroportoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaRTresAeroportoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaRQuatroAeroportoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Três")){

                contandoRuavinteTresAeroportoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. XI")){

                contandoRuaRXIAeroportoRouboTodosAnos++;


            }



        }




        if (procuraBairro.contains("Aeroporto") && b.getStatus().equals("Furtada")){



            if (b.getAlertaRua().equals("Avenida Aeroporto")){

                contandoAvAeroportoAeroportoFurtoTodosAnos++;



            }


            if (b.getAlertaRua().equals("Av. Seis")){

                contandoAvSeisAeroportoFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaRUmAeroportoFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaRDoisAeroportoFurtoTodosAnos++;




            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaRTresAeroportoFurtoTodosAnos++;



            }


            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaRQuatroAeroportoFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Vinte e Três")){

                contandoRuavinteTresAeroportoFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. XI")){

                contandoRuaRXIAeroportoFurtoTodosAnos++;


            }



        }


    }

    public void ruasBairroAeroporto2019(){




        if (procuraBairro.contains("Aeroporto") && procuraAno.contains(anoProcura2019)&& b.getStatus().equals("Roubada")){



            if (b.getAlertaRua().equals("Avenida Aeroporto")){

                contandoAvAeroportoAeroportoRoubo2019++;


            }


            if (b.getAlertaRua().equals("Av. Seis")){

                contandoAvSeisAeroportoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaRUmAeroportoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaRDoisAeroportoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaRTresAeroportoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaRQuatroAeroportoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Três")){

                contandoRuavinteTresAeroportoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. XI")){

                contandoRuaRXIAeroportoRoubo2019++;


            }



        }






        if (procuraBairro.contains("Aeroporto") && procuraAno.contains(anoProcura2019)&& b.getStatus().equals("Furtada")){



            if (b.getAlertaRua().equals("Avenida Aeroporto")){

                contandoAvAeroportoAeroportoFurtada2019++;



            }


            if (b.getAlertaRua().equals("Av. Seis")){

                contandoAvSeisAeroportoFurtada2019++;




            }

            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaRUmAeroportoFurtada2019++;



            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaRDoisAeroportoFurtada2019++;



            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaRTresAeroportoFurtada2019++;


            }


            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaRQuatroAeroportoFurtada2019++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Três")){

                contandoRuavinteTresAeroportoFurtada2019++;



            }

            if (b.getAlertaRua().equals("R. XI")){

                contandoRuaRXIAeroportoFurtada2019++;


            }



        }
    }

    public void ruasBairroAltoPocoesTodosAnos(){




        if (procuraBairro.contains("Alto dos Pocões") && b.getStatus().equals("Roubada")){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmAltoPocoesRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisAltoPocoesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresAltoPocoesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroAltoPocoesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Cinco")){

                contandoRuaCincoAltoPocoesRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Av. Um")){

                contandoAvUmAltoPocoesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Av. Dois")){

                contandoAvDoisRuaAltoPocoesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Av. Três")){

                contandoAvTresAltoPocoesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Av. Quatro")){

                contandoAvQuatroAltoPocoesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Av. Cinco")){

                contandoAvCincoAltoPocoesRouboTodosAnos++;


            }



        }




        if (procuraBairro.contains("Alto dos Pocões") && b.getStatus().equals("Furtada")){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmAltoPocoesFurtoTodosAnos++;



            }


            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisAltoPocoesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresAltoPocoesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroAltoPocoesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Cinco")){

                contandoRuaCincoAltoPocoesFurtoTodosAnos++;



            }


            if (b.getAlertaRua().equals("Av. Um")){

                contandoAvUmAltoPocoesFurtoTodosAnos++;


            }

            if (b.getAlertaRua().equals("Av. Dois")){

                contandoAvDoisRuaAltoPocoesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("Av. Três")){

                contandoAvTresAltoPocoesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("Av. Quatro")){

                contandoAvQuatroAltoPocoesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("Av. Cinco")){

                contandoAvCincoAltoPocoesFurtoTodosAnos++;


            }



        }


    }

    public void ruasBairroAltoPocoes2019(){




        if (procuraBairro.contains("Alto dos Pocões") && procuraAno.contains(anoProcura2019)&& b.getStatus().equals("Roubada")){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmAltoPocoesRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisAltoPocoesRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresAltoPocoesRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroAltoPocoesRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Cinco")){

                contandoRuaCincoAltoPocoesRoubo2019++;


            }


            if (b.getAlertaRua().equals("Av. Um")){

                contandoAvUmAltoPocoesRoubo2019++;


            }

            if (b.getAlertaRua().equals("Av. Dois")){

                contandoAvDoisRuaAltoPocoesRoubo2019++;


            }

            if (b.getAlertaRua().equals("Av. Três")){

                contandoAvTresAltoPocoesRoubo2019++;


            }

            if (b.getAlertaRua().equals("Av. Quatro")){

                contandoAvQuatroAltoPocoesRoubo2019++;


            }

            if (b.getAlertaRua().equals("Av. Cinco")){

                contandoAvCincoAltoPocoesRoubo2019++;


            }




        }






        if (procuraBairro.contains("Alto dos Pocões") && procuraAno.contains(anoProcura2019)&& b.getStatus().equals("Furtada")){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmAltoPocoesFurto2019++;


            }


            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisAltoPocoesFurto2019++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresAltoPocoesFurto2019++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroAltoPocoesFurto2019++;


            }

            if (b.getAlertaRua().equals("R. Cinco")){

                contandoRuaCincoAltoPocoesFurto2019++;


            }


            if (b.getAlertaRua().equals("Av. Um")){

                contandoAvUmAltoPocoesFurto2019++;


            }

            if (b.getAlertaRua().equals("Av. Dois")){

                contandoAvDoisRuaAltoPocoesFurto2019++;


            }

            if (b.getAlertaRua().equals("Av. Três")){

                contandoAvTresAltoPocoesFurto2019++;


            }

            if (b.getAlertaRua().equals("Av. Quatro")){

                contandoAvQuatroAltoPocoesFurto2019++;


            }

            if (b.getAlertaRua().equals("Av. Cinco")){

                contandoAvCincoAltoPocoesFurto2019++;


            }



        }
    }

    public void ruasBairroAlvoradaTodosAnos(){





        if (procuraBairro.contains("Alvorada") && b.getStatus().equals("Roubada")){



            if (b.getAlertaRua().equals("R. do Aterro")){

                contandoRuaAterroAlvoradaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmAlvoradaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAAlvoradaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEAlvoradaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. J")){

                contandoRuaJAlvoradaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. K")){

                contandoRuaKAlvoradaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLAlvoradaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. M")){

                contandoRuaMAlvoradaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. N")){

                contandoRuaNAlvoradaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. O")){

                contandoRuaOAlvoradaRouboTodosAnos++;


            }



        }




        if (procuraBairro.contains("Alvorada") && b.getStatus().equals("Furtada")){




            if (b.getAlertaRua().equals("R. do Aterro")){

                contandoRuaAterroAlvoradaFurtoTodosAnos++;



            }


            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmAlvoradaFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAAlvoradaFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEAlvoradaFurtoTodosAnos++;




            }

            if (b.getAlertaRua().equals("R. J")){

                contandoRuaJAlvoradaFurtoTodosAnos++;




            }


            if (b.getAlertaRua().equals("R. K")){

                contandoRuaKAlvoradaFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLAlvoradaFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. M")){

                contandoRuaMAlvoradaFurtoTodosAnos++;



            }


            if (b.getAlertaRua().equals("R. N")){

                contandoRuaNAlvoradaFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. O")){

                contandoRuaOAlvoradaFurtoTodosAnos++;


            }

        }


    }

    public void ruasBairroAlvorada2019(){




        if (procuraBairro.contains("Alvorada") && procuraAno.contains(anoProcura2019)&& b.getStatus().equals("Roubada")){



            if (b.getAlertaRua().equals("R. do Aterro")){

                contandoRuaAterroAlvoradaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmAlvoradaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAAlvoradaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEAlvoradaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. J")){

                contandoRuaJAlvoradaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. K")){

                contandoRuaKAlvoradaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLAlvoradaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. M")){

                contandoRuaMAlvoradaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. N")){

                contandoRuaNAlvoradaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. O")){

                contandoRuaOAlvoradaRoubo2019++;


            }


        }






        if (procuraBairro.contains("Alvorada") && procuraAno.contains(anoProcura2019)&& b.getStatus().equals("Furtada")){



            if (b.getAlertaRua().equals("R. do Aterro")){

                contandoRuaAterroAlvoradaFurto2019++;


            }


            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmAlvoradaFurto2019++;


            }

            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAAlvoradaFurto2019++;


            }

            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEAlvoradaFurto2019++;


            }

            if (b.getAlertaRua().equals("R. J")){

                contandoRuaJAlvoradaFurto2019++;


            }


            if (b.getAlertaRua().equals("R. K")){

                contandoRuaKAlvoradaFurto2019++;


            }

            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLAlvoradaFurto2019++;


            }

            if (b.getAlertaRua().equals("R. M")){

                contandoRuaMAlvoradaFurto2019++;


            }


            if (b.getAlertaRua().equals("R. N")){

                contandoRuaNAlvoradaFurto2019++;


            }

            if (b.getAlertaRua().equals("R. O")){

                contandoRuaOAlvoradaFurto2019++;


            }
        }
    }

    public void ruasBairroBandeirantesTodosAnos(){




        if (procuraBairro.contains("Bandeirantes") && b.getStatus().equals("Roubada")){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmBandeirantesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaABandeirantesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dez")){

                contandoRuaEBandeirantesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Brasília de Minas")){

                contandoRuaJBandeirantesRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. 15 de Novembro")){

                contandoRuaKBandeirantesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Mirabela")){

                contandoRuaMirabelaBandeirantesRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Tv.Mirabela")){

                contandoTvMirabelaBandeirantesRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Tv. Júlio Magalhães")){

                contandoTvJulioBandeirantesRouboTodosAnos++;


            }





        }




        if (procuraBairro.contains("Bandeirantes") && b.getStatus().equals("Furtada")){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmBandeirantesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaABandeirantesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Dez")){

                contandoRuaEBandeirantesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Brasília de Minas")){

                contandoRuaJBandeirantesFurtoTodosAnos++;



            }


            if (b.getAlertaRua().equals("R. 15 de Novembro")){

                contandoRuaKBandeirantesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("R. Mirabela")){

                contandoRuaMirabelaBandeirantesFurtoTodosAnos++;



            }

            if (b.getAlertaRua().equals("Tv.Mirabela")){

                contandoTvMirabelaBandeirantesFurtoTodosAnos++;



            }


            if (b.getAlertaRua().equals("Tv. Júlio Magalhães")){

                contandoTvJulioBandeirantesFurtoTodosAnos++;


            }



        }


    }

    public void ruasBairroBandeirantes2019(){




        if (procuraBairro.contains("Bandeirantes") && procuraAno.contains(anoProcura2019)&& b.getStatus().equals("Roubada")){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmBandeirantesRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaABandeirantesRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dez")){

                contandoRuaEBandeirantesRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Brasília de Minas")){

                contandoRuaJBandeirantesRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. 15 de Novembro")){

                contandoRuaKBandeirantesRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Mirabela")){

                contandoRuaMirabelaBandeirantesRoubo2019++;


            }

            if (b.getAlertaRua().equals("Tv.Mirabela")){

                contandoTvMirabelaBandeirantesRoubo2019++;


            }


            if (b.getAlertaRua().equals("Tv. Júlio Magalhães")){

                contandoTvJulioBandeirantesRoubo2019++;


            }

        }






        if (procuraBairro.contains("Bandeirantes") && procuraAno.contains(anoProcura2019)&& b.getStatus().equals("Furtada")){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmBandeirantesFurto2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaABandeirantesFurto2019++;


            }

            if (b.getAlertaRua().equals("R. Dez")){

                contandoRuaEBandeirantesFurto2019++;


            }

            if (b.getAlertaRua().equals("R. Brasília de Minas")){

                contandoRuaJBandeirantesFurto2019++;


            }


            if (b.getAlertaRua().equals("R. 15 de Novembro")){

                contandoRuaKBandeirantesFurto2019++;


            }

            if (b.getAlertaRua().equals("R. Mirabela")){

                contandoRuaMirabelaBandeirantesFurto2019++;


            }

            if (b.getAlertaRua().equals("Tv.Mirabela")){

                contandoTvMirabelaBandeirantesFurto2019++;


            }


            if (b.getAlertaRua().equals("Tv. Júlio Magalhães")){

                contandoTvJulioBandeirantesFurto2019++;


            }

        }
    }

    public void ruasBairroJadeteTodosAnos(){



        if (procuraBairro.contains("Vila Jadete") && b.getStatus().equals("Roubada")){



            if (b.getAlertaRua().equals("Avenida Cônego Ramiro Leite")){

                contandoRuaRamiroLeiteJadeteRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Alameda Viana")){

                contandoRuaAlameidaVianaJadeteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Inezita Alves Ferreira")){

                contandoRuaInezitaJadeteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Isla Helena Azevedo")){

                contandoRuaIslaHJadeteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Santo Antônio")){

                contandoRuaSantoAntJadeteRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Viana")){

                contandoRuaVianaJadeteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. vinte e Dois")){

                contandoRuavinteDoisJadeteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Ver. João Alkimim")){

                contandoRuaJoaoAlkimimJadeteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Tv. Viana")){

                contandoTvVianaJadeteRouboTodosAnos++;


            }



        }




        if (procuraBairro.contains("Vila Jadete") && b.getStatus().equals("Furtada")){



            if (b.getAlertaRua().equals("Avenida Cônego Ramiro Leite")){

                contandoRuaRamiroLeiteJadeteFurtoTodosAnos++;


            }


            if (b.getAlertaRua().equals("Alameda Viana")){

                contandoRuaAlameidaVianaJadeteFurtoTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Inezita Alves Ferreira")){

                contandoRuaInezitaJadeteFurtoTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Isla Helena Azevedo")){

                contandoRuaIslaHJadeteFurtoTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Santo Antônio")){

                contandoRuaSantoAntJadeteFurtoTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Viana")){

                contandoRuaVianaJadeteFurtoTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. vinte e Dois")){

                contandoRuavinteDoisJadeteFurtoTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Ver. João Alkimim")){

                contandoRuaJoaoAlkimimJadeteFurtoTodosAnos++;


            }

            if (b.getAlertaRua().equals("Tv. Viana")){

                contandoTvVianaJadeteFurtoTodosAnos++;


            }



        }



    }

    public void ruasBairroJadete2019(){



        if (procuraBairro.contains("Vila Jadete") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")){



            if (b.getAlertaRua().equals("Avenida Cônego Ramiro Leite")){

                contandoRuaRamiroLeiteJadeteRouboAno2019++;


            }


            if (b.getAlertaRua().equals("Alameda Viana")){

                contandoRuaAlameidaVianaJadeteRouboAno2019++;


            }

            if (b.getAlertaRua().equals("R. Inezita Alves Ferreira")){

                contandoRuaInezitaJadeteRouboAno2019++;


            }

            if (b.getAlertaRua().equals("R. Isla Helena Azevedo")){

                contandoRuaIslaHJadeteRouboAno2019++;


            }

            if (b.getAlertaRua().equals("R. Santo Antônio")){

                contandoRuaSantoAntJadeteRouboAno2019++;


            }


            if (b.getAlertaRua().equals("R. Viana")){

                contandoRuaVianaJadeteRouboAno2019++;


            }

            if (b.getAlertaRua().equals("R. vinte e Dois")){

                contandoRuavinteDoisJadeteRouboAno2019++;


            }

            if (b.getAlertaRua().equals("R. Ver. João Alkimim")){

                contandoRuaJoaoAlkimimJadeteRouboAno2019++;


            }

            if (b.getAlertaRua().equals("Tv. Viana")){

                contandoTvVianaJadeteRouboAno2019++;


            }



        }




        if (procuraBairro.contains("Vila Jadete") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")){



            if (b.getAlertaRua().equals("Avenida Cônego Ramiro Leite")){

                contandoRuaRamiroLeiteJadeteFurtoAno2019++;


            }


            if (b.getAlertaRua().equals("Alameda Viana")){

                contandoRuaAlameidaVianaJadeteFurtoAno2019++;


            }

            if (b.getAlertaRua().equals("R. Inezita Alves Ferreira")){

                contandoRuaInezitaJadeteFurtoAno2019++;


            }

            if (b.getAlertaRua().equals("R. Isla Helena Azevedo")){

                contandoRuaIslaHJadeteFurtoAno2019++;


            }

            if (b.getAlertaRua().equals("R. Santo Antônio")){

                contandoRuaSantoAntJadeteFurtoAno2019++;


            }


            if (b.getAlertaRua().equals("R. Viana")){

                contandoRuaVianaJadeteFurtoAno2019++;


            }

            if (b.getAlertaRua().equals("R. vinte e Dois")){

                contandoRuavinteDoisJadeteFurtoAno2019++;


            }

            if (b.getAlertaRua().equals("R. Ver. João Alkimim")){

                contandoRuaJoaoAlkimimJadeteFurtoAno2019++;


            }

            if (b.getAlertaRua().equals("Tv. Viana")){

                contandoTvVianaJadeteFurtoAno2019++;


            }



        }



    }





}
