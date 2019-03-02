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









    int madrugadaAeroportoFurto=0;
    int manhaAeroportoFurto=0;
    int tardeAeroportoFurto=0;
    int noiteAeroportoFurto=0;

    int madrugadaAeroportoRoubo=0;
    int manhaAeroportoRoubo=0;
    int tardeAeroportoRoubo=0;
    int noiteAeroportoRoubo=0;


    int madrugadaAeroportoFurto2019=0;
    int manhaAeroportoFurto2019=0;
    int tardeAeroportoFurto2019=0;
    int noiteAeroportoFurto2019=0;

    int madrugadaAeroportoRoubo2019=0;
    int manhaAeroportoRoubo2019=0;
    int tardeAeroportoRoubo2019=0;
    int noiteAeroportoRoubo2019=0;


    int madrugadaAltoPocoesFurto=0;
    int manhaAltoPocoesFurto=0;
    int tardeAltoPocoesFurto=0;
    int noiteAltoPocoesFurto=0;

    int madrugadaAltoPocoesRoubo=0;
    int manhaAltoPocoesRoubo=0;
    int tardeAltoPocoesRoubo=0;
    int noiteAltoPocoesRoubo=0;


    int madrugadaAltoPocoesFurto2019=0;
    int manhaAltoPocoesFurto2019=0;
    int tardeAltoPocoesFurto2019=0;
    int noiteAltoPocoesFurto2019=0;

    int madrugadaAltoPocoesRoubo2019=0;
    int manhaAltoPocoesRoubo2019=0;
    int tardeAltoPocoesRoubo2019=0;
    int noiteAltoPocoesRoubo2019=0;






    int madrugadaAlvoradaFurto=0;
    int manhaAlvoradaFurto=0;
    int tardeAlvoradaFurto=0;
    int noiteAlvoradaFurto=0;

    int madrugadaAlvoradaRoubo=0;
    int manhaAlvoradaRoubo=0;
    int tardeAlvoradaRoubo=0;
    int noiteAlvoradaRoubo=0;


    int madrugadaAlvoradaFurto2019=0;
    int manhaAlvoradaFurto2019=0;
    int tardeAlvoradaFurto2019=0;
    int noiteAlvoradaFurto2019=0;

    int madrugadaAlvoradaRoubo2019=0;
    int manhaAlvoradaRoubo2019=0;
    int tardeAlvoradaRoubo2019=0;
    int noiteAlvoradaRoubo2019=0;




    int madrugadaBandeirantesFurto=0;
    int manhaBandeirantesFurto=0;
    int tardeBandeirantesFurto=0;
    int noiteBandeirantesFurto=0;

    int madrugadaBandeirantesRoubo=0;
    int manhaBandeirantesRoubo=0;
    int tardeBandeirantesRoubo=0;
    int noiteBandeirantesRoubo=0;


    int madrugadaBandeirantesFurto2019=0;
    int manhaBandeirantesFurto2019=0;
    int tardeBandeirantesFurto2019=0;
    int noiteBandeirantesFurto2019=0;

    int madrugadaBandeirantesRoubo2019=0;
    int manhaBandeirantesRoubo2019=0;
    int tardeBandeirantesRoubo2019=0;
    int noiteBandeirantesRoubo2019=0;





    int madrugadaBoaEsperancaFurto=0;
    int manhaBoaEsperancaFurto=0;
    int tardeBoaEsperancaFurto=0;
    int noiteBoaEsperancaFurto=0;

    int madrugadaBoaEsperancaRoubo=0;
    int manhaBoaEsperancaRoubo=0;
    int tardeBoaEsperancaRoubo=0;
    int noiteBoaEsperancaRoubo=0;


    int madrugadaBoaEsperancaFurto2019=0;
    int manhaBoaEsperancaFurto2019=0;
    int tardeBoaEsperancaFurto2019=0;
    int noiteBoaEsperancaFurto2019=0;

    int madrugadaBoaEsperancaRoubo2019=0;
    int manhaBoaEsperancaRoubo2019=0;
    int tardeBoaEsperancaRoubo2019=0;
    int noiteBoaEsperancaRoubo2019=0;






    int madrugadaBoaVistaFurto=0;
    int manhaBoaVistaFurto=0;
    int tardeBoaVistaFurto=0;
    int noiteBoaVistaFurto=0;

    int madrugadaBoaVistaRoubo=0;
    int manhaBoaVistaRoubo=0;
    int tardeBoaVistaRoubo=0;
    int noiteBoaVistaRoubo=0;


    int madrugadaBoaVistaFurto2019=0;
    int manhaBoaVistaFurto2019=0;
    int tardeBoaVistaFurto2019=0;
    int noiteBoaVistaFurto2019=0;

    int madrugadaBoaVistaRoubo2019=0;
    int manhaBoaVistaRoubo2019=0;
    int tardeBoaVistaRoubo2019=0;
    int noiteBoaVistaRoubo2019=0;




    int madrugadaVilaBrasilandiaFurto=0;
    int manhaVilaBrasilandiaFurto=0;
    int tardeVilaBrasilandiaFurto=0;
    int noiteVilaBrasilandiaFurto=0;

    int madrugadaVilaBrasilandiaRoubo=0;
    int manhaVilaBrasilandiaRoubo=0;
    int tardeVilaBrasilandiaRoubo=0;
    int noiteVilaBrasilandiaRoubo=0;


    int madrugadaVilaBrasilandiaFurto2019=0;
    int manhaVilaBrasilandiaFurto2019=0;
    int tardeVilaBrasilandiaFurto2019=0;
    int noiteVilaBrasilandiaFurto2019=0;

    int madrugadaVilaBrasilandiaRoubo2019=0;
    int manhaVilaBrasilandiaRoubo2019=0;
    int tardeVilaBrasilandiaRoubo2019=0;
    int noiteVilaBrasilandiaRoubo2019=0;






    int madrugadaBrejoAmparoFurto=0;
    int manhaBrejoAmparoFurto=0;
    int tardeBrejoAmparoFurto=0;
    int noiteBrejoAmparoFurto=0;

    int madrugadaBrejoAmparoRoubo=0;
    int manhaBrejoAmparoRoubo=0;
    int tardeBrejoAmparoRoubo=0;
    int noiteBrejoAmparoRoubo=0;


    int madrugadaBrejoAmparoFurto2019=0;
    int manhaBrejoAmparoFurto2019=0;
    int tardeBrejoAmparoFurto2019=0;
    int noiteBrejoAmparoFurto2019=0;

    int madrugadaBrejoAmparoRoubo2019=0;
    int manhaBrejoAmparoRoubo2019=0;
    int tardeBrejoAmparoRoubo2019=0;
    int noiteBrejoAmparoRoubo2019=0;




    int madrugadaCentroFurto=0;
    int manhaCentroFurto=0;
    int tardeCentroFurto=0;
    int noiteCentroFurto=0;

    int madrugadaCentroRoubo=0;
    int manhaCentroRoubo=0;
    int tardeCentroRoubo=0;
    int noiteCentroRoubo=0;


    int madrugadaCentroFurto2019=0;
    int manhaCentroFurto2019=0;
    int tardeCentroFurto2019=0;
    int noiteCentroFurto2019=0;

    int madrugadaCentroRoubo2019=0;
    int manhaCentroRoubo2019=0;
    int tardeCentroRoubo2019=0;
    int noiteCentroRoubo2019=0;




    int madrugadaCeramicaFurto=0;
    int manhaCeramicaFurto=0;
    int tardeCeramicaFurto=0;
    int noiteCeramicaFurto=0;

    int madrugadaCeramicaRoubo=0;
    int manhaCeramicaRoubo=0;
    int tardeCeramicaRoubo=0;
    int noiteCeramicaRoubo=0;


    int madrugadaCeramicaFurto2019=0;
    int manhaCeramicaFurto2019=0;
    int tardeCeramicaFurto2019=0;
    int noiteCeramicaFurto2019=0;

    int madrugadaCeramicaRoubo2019=0;
    int manhaCeramicaRoubo2019=0;
    int tardeCeramicaRoubo2019=0;
    int noiteCeramicaRoubo2019=0;




    int madrugadaEldoradoFurto=0;
    int manhaEldoradoFurto=0;
    int tardeEldoradoFurto=0;
    int noiteEldoradoFurto=0;

    int madrugadaEldoradoRoubo=0;
    int manhaEldoradoRoubo=0;
    int tardeEldoradoRoubo=0;
    int noiteEldoradoRoubo=0;


    int madrugadaEldoradoFurto2019=0;
    int manhaEldoradoFurto2019=0;
    int tardeEldoradoFurto2019=0;
    int noiteEldoradoFurto2019=0;

    int madrugadaEldoradoRoubo2019=0;
    int manhaEldoradoRoubo2019=0;
    int tardeEldoradoRoubo2019=0;
    int noiteEldoradoRoubo2019=0;




    int madrugadaFranklimFurto=0;
    int manhaFranklimFurto=0;
    int tardeFranklimFurto=0;
    int noiteFranklimFurto=0;

    int madrugadaFranklimRoubo=0;
    int manhaFranklimRoubo=0;
    int tardeFranklimRoubo=0;
    int noiteFranklimRoubo=0;


    int madrugadaFranklimFurto2019=0;
    int manhaFranklimFurto2019=0;
    int tardeFranklimFurto2019=0;
    int noiteFranklimFurto2019=0;

    int madrugadaFranklimRoubo2019=0;
    int manhaFranklimRoubo2019=0;
    int tardeFranklimRoubo2019=0;
    int noiteFranklimRoubo2019=0;




    int madrugadaJardimDanielFurto=0;
    int manhaJardimDanielFurto=0;
    int tardeJardimDanielFurto=0;
    int noiteJardimDanielFurto=0;

    int madrugadaJardimDanielRoubo=0;
    int manhaJardimDanielRoubo=0;
    int tardeJardimDanielRoubo=0;
    int noiteJardimDanielRoubo=0;


    int madrugadaJardimDanielFurto2019=0;
    int manhaJardimDanielFurto2019=0;
    int tardeJardimDanielFurto2019=0;
    int noiteJardimDanielFurto2019=0;

    int madrugadaJardimDanielRoubo2019=0;
    int manhaJardimDanielRoubo2019=0;
    int tardeJardimDanielRoubo2019=0;
    int noiteJardimDanielRoubo2019=0;



    int madrugadaJardimEstrelaFurto=0;
    int manhaJardimEstrelaFurto=0;
    int tardeJardimEstrelaFurto=0;
    int noiteJardimEstrelaFurto=0;

    int madrugadaJardimEstrelaRoubo=0;
    int manhaJardimEstrelaRoubo=0;
    int tardeJardimEstrelaRoubo=0;
    int noiteJardimEstrelaRoubo=0;


    int madrugadaJardimEstrelaFurto2019=0;
    int manhaJardimEstrelaFurto2019=0;
    int tardeJardimEstrelaFurto2019=0;
    int noiteJardimEstrelaFurto2019=0;

    int madrugadaJardimEstrelaRoubo2019=0;
    int manhaJardimEstrelaRoubo2019=0;
    int tardeJardimEstrelaRoubo2019=0;
    int noiteJardimEstrelaRoubo2019=0;




    int madrugadaJussaraFurto=0;
    int manhaJussaraFurto=0;
    int tardeJussaraFurto=0;
    int noiteJussaraFurto=0;

    int madrugadaJussaraRoubo=0;
    int manhaJussaraRoubo=0;
    int tardeJussaraRoubo=0;
    int noiteJussaraRoubo=0;

    int madrugadaJussaraFurto2019=0;
    int manhaJussaraFurto2019=0;
    int tardeJussaraFurto2019=0;
    int noiteJussaraFurto2019=0;

    int madrugadaJussaraRoubo2019=0;
    int manhaJussaraRoubo2019=0;
    int tardeJussaraRoubo2019=0;
    int noiteJussaraRoubo2019=0;




    int madrugadaVilaLevianopolisFurto=0;
    int manhaVilaLevianopolisFurto=0;
    int tardeVilaLevianopolisFurto=0;
    int noiteVilaLevianopolisFurto=0;

    int madrugadaVilaLevianopolisRoubo=0;
    int manhaVilaLevianopolisRoubo=0;
    int tardeVilaLevianopolisRoubo=0;
    int noiteVilaLevianopolisRoubo=0;

    int madrugadaVilaLevianopolisFurto2019=0;
    int manhaVilaLevianopolisFurto2019=0;
    int tardeVilaLevianopolisFurto2019=0;
    int noiteVilaLevianopolisFurto2019=0;

    int madrugadaVilaLevianopolisRoubo2019=0;
    int manhaVilaLevianopolisRoubo2019=0;
    int tardeVilaLevianopolisRoubo2019=0;
    int noiteVilaLevianopolisRoubo2019=0;




    int madrugadaQuintasMangueirasFurto=0;
    int manhaQuintasMangueirasFurto=0;
    int tardeQuintasMangueirasFurto=0;
    int noiteQuintasMangueirasFurto=0;

    int madrugadaQuintasMangueirasRoubo=0;
    int manhaQuintasMangueirasRoubo=0;
    int tardeQuintasMangueirasRoubo=0;
    int noiteQuintasMangueirasRoubo=0;

    int madrugadaQuintasMangueirasFurto2019=0;
    int manhaQuintasMangueirasFurto2019=0;
    int tardeQuintasMangueirasFurto2019=0;
    int noiteQuintasMangueirasFurto2019=0;

    int madrugadaQuintasMangueirasRoubo2019=0;
    int manhaQuintasMangueirasRoubo2019=0;
    int tardeQuintasMangueirasRoubo2019=0;
    int noiteQuintasMangueirasRoubo2019=0;




    int madrugadaRiachoCruzFurto=0;
    int manhaRiachoCruzFurto=0;
    int tardeRiachoCruzFurto=0;
    int noiteRiachoCruzFurto=0;

    int madrugadaRiachoCruzRoubo=0;
    int manhaRiachoCruzRoubo=0;
    int tardeRiachoCruzRoubo=0;
    int noiteRiachoCruzRoubo=0;

    int madrugadaRiachoCruzFurto2019=0;
    int manhaRiachoCruzFurto2019=0;
    int tardeRiachoCruzFurto2019=0;
    int noiteRiachoCruzFurto2019=0;

    int madrugadaRiachoCruzRoubo2019=0;
    int manhaRiachoCruzRoubo2019=0;
    int tardeRiachoCruzRoubo2019=0;
    int noiteRiachoCruzRoubo2019=0;





    int madrugadaVilaSaoJoaoFurto=0;
    int manhaVilaSaoJoaoFurto=0;
    int tardeVilaSaoJoaoFurto=0;
    int noiteVilaSaoJoaoFurto=0;

    int madrugadaVilaSaoJoaoRoubo=0;
    int manhaVilaSaoJoaoRoubo=0;
    int tardeVilaSaoJoaoRoubo=0;
    int noiteVilaSaoJoaoRoubo=0;

    int madrugadaVilaSaoJoaoFurto2019=0;
    int manhaVilaSaoJoaoFurto2019=0;
    int tardeVilaSaoJoaoFurto2019=0;
    int noiteVilaSaoJoaoFurto2019=0;

    int madrugadaVilaSaoJoaoRoubo2019=0;
    int manhaVilaSaoJoaoRoubo2019=0;
    int tardeVilaSaoJoaoRoubo2019=0;
    int noiteVilaSaoJoaoRoubo2019=0;



    int madrugadaSaoJoaquimFurto=0;
    int manhaSaoJoaquimFurto=0;
    int tardeSaoJoaquimFurto=0;
    int noiteSaoJoaquimFurto=0;

    int madrugadaSaoJoaquimRoubo=0;
    int manhaSaoJoaquimRoubo=0;
    int tardeSaoJoaquimRoubo=0;
    int noiteSaoJoaquimRoubo=0;

    int madrugadaSaoJoaquimFurto2019=0;
    int manhaSaoJoaquimFurto2019=0;
    int tardeSaoJoaquimFurto2019=0;
    int noiteSaoJoaquimFurto2019=0;

    int madrugadaSaoJoaquimRoubo2019=0;
    int manhaSaoJoaquimRoubo2019=0;
    int tardeSaoJoaquimRoubo2019=0;
    int noiteSaoJoaquimRoubo2019=0;


    int madrugadaSaoMiguelFurto=0;
    int manhaSaoMiguelFurto=0;
    int tardeSaoMiguelFurto=0;
    int noiteSaoMiguelFurto=0;

    int madrugadaSaoMiguelRoubo=0;
    int manhaSaoMiguelRoubo=0;
    int tardeSaoMiguelRoubo=0;
    int noiteSaoMiguelRoubo=0;

    int madrugadaSaoMiguelFurto2019=0;
    int manhaSaoMiguelFurto2019=0;
    int tardeSaoMiguelFurto2019=0;
    int noiteSaoMiguelFurto2019=0;

    int madrugadaSaoMiguelRoubo2019=0;
    int manhaSaoMiguelRoubo2019=0;
    int tardeSaoMiguelRoubo2019=0;
    int noiteSaoMiguelRoubo2019=0;




    int madrugadaSaoVicenteFurto=0;
    int manhaSaoVicenteFurto=0;
    int tardeSaoVicenteFurto=0;
    int noiteSaoVicenteFurto=0;

    int madrugadaSaoVicenteRoubo=0;
    int manhaSaoVicenteRoubo=0;
    int tardeSaoVicenteRoubo=0;
    int noiteSaoVicenteRoubo=0;

    int madrugadaSaoVicenteFurto2019=0;
    int manhaSaoVicenteFurto2019=0;
    int tardeSaoVicenteFurto2019=0;
    int noiteSaoVicenteFurto2019=0;

    int madrugadaSaoVicenteRoubo2019=0;
    int manhaSaoVicenteRoubo2019=0;
    int tardeSaoVicenteRoubo2019=0;
    int noiteSaoVicenteRoubo2019=0;





    int madrugadaTejucoFurto=0;
    int manhaTejucoFurto=0;
    int tardeTejucoFurto=0;
    int noiteTejucoFurto=0;

    int madrugadaTejucoRoubo=0;
    int manhaTejucoRoubo=0;
    int tardeTejucoRoubo=0;
    int noiteTejucoRoubo=0;

    int madrugadaTejucoFurto2019=0;
    int manhaTejucoFurto2019=0;
    int tardeTejucoFurto2019=0;
    int noiteTejucoFurto2019=0;

    int madrugadaTejucoRoubo2019=0;
    int manhaTejucoRoubo2019=0;
    int tardeTejucoRoubo2019=0;
    int noiteTejucoRoubo2019=0;



    int madrugadaVilaFatimaFurto=0;
    int manhaVilaFatimaFurto=0;
    int tardeVilaFatimaFurto=0;
    int noiteVilaFatimaFurto=0;

    int madrugadaVilaFatimaRoubo=0;
    int manhaVilaFatimaRoubo=0;
    int tardeVilaFatimaRoubo=0;
    int noiteVilaFatimaRoubo=0;

    int madrugadaVilaFatimaFurto2019=0;
    int manhaVilaFatimaFurto2019=0;
    int tardeVilaFatimaFurto2019=0;
    int noiteVilaFatimaFurto2019=0;

    int madrugadaVilaFatimaRoubo2019=0;
    int manhaVilaFatimaRoubo2019=0;
    int tardeVilaFatimaRoubo2019=0;
    int noiteVilaFatimaRoubo2019=0;





    int madrugadaPandeirosFurto=0;
    int manhaPandeirosFurto=0;
    int tardePandeirosFurto=0;
    int noitePandeirosFurto=0;

    int madrugadaPandeirosRoubo=0;
    int manhaPandeirosRoubo=0;
    int tardePandeirosRoubo=0;
    int noitePandeirosRoubo=0;

    int madrugadaPandeirosFurto2019=0;
    int manhaPandeirosFurto2019=0;
    int tardePandeirosFurto2019=0;
    int noitePandeirosFurto2019=0;

    int madrugadaPandeirosRoubo2019=0;
    int manhaPandeirosRoubo2019=0;
    int tardePandeirosRoubo2019=0;
    int noitePandeirosRoubo2019=0;




    int madrugadaVilaPaulaFurto=0;
    int manhaVilaPaulaFurto=0;
    int tardeVilaPaulaFurto=0;
    int noiteVilaPaulaFurto=0;

    int madrugadaVilaPaulaRoubo=0;
    int manhaVilaPaulaRoubo=0;
    int tardeVilaPaulaRoubo=0;
    int noiteVilaPaulaRoubo=0;

    int madrugadaVilaPaulaFurto2019=0;
    int manhaVilaPaulaFurto2019=0;
    int tardeVilaPaulaFurto2019=0;
    int noiteVilaPaulaFurto2019=0;

    int madrugadaVilaPaulaRoubo2019=0;
    int manhaVilaPaulaRoubo2019=0;
    int tardeVilaPaulaRoubo2019=0;
    int noiteVilaPaulaRoubo2019=0;





    int madrugadaVilaVerdeFurto=0;
    int manhaVilaVerdeFurto=0;
    int tardeVilaVerdeFurto=0;
    int noiteVilaVerdeFurto=0;

    int madrugadaVilaVerdeRoubo=0;
    int manhaVilaVerdeRoubo=0;
    int tardeVilaVerdeRoubo=0;
    int noiteVilaVerdeRoubo=0;

    int madrugadaVilaVerdeFurto2019=0;
    int manhaVilaVerdeFurto2019=0;
    int tardeVilaVerdeFurto2019=0;
    int noiteVilaVerdeFurto2019=0;

    int madrugadaVilaVerdeRoubo2019=0;
    int manhaVilaVerdeRoubo2019=0;
    int tardeVilaVerdeRoubo2019=0;
    int noiteVilaVerdeRoubo2019=0;




    int madrugadaVilaVianaFurto=0;
    int manhaVilaVianaFurto=0;
    int tardeVilaVianaFurto=0;
    int noiteVilaVianaFurto=0;

    int madrugadaVilaVianaRoubo=0;
    int manhaVilaVianaRoubo=0;
    int tardeVilaVianaRoubo=0;
    int noiteVilaVianaRoubo=0;

    int madrugadaVilaVianaFurto2019=0;
    int manhaVilaVianaFurto2019=0;
    int tardeVilaVianaFurto2019=0;
    int noiteVilaVianaFurto2019=0;

    int madrugadaVilaVianaRoubo2019=0;
    int manhaVilaVianaRoubo2019=0;
    int tardeVilaVianaRoubo2019=0;
    int noiteVilaVianaRoubo2019=0;




    int madrugadaVilaJadeteFurto=0;
    int manhaVilaJadeteFurto=0;
    int tardeVilaJadeteFurto=0;
    int noiteVilaJadeteFurto=0;

    int madrugadaVilaJadeteRoubo=0;
    int manhaVilaJadeteRoubo=0;
    int tardeVilaJadeteRoubo=0;
    int noiteVilaJadeteRoubo=0;

    int madrugadaVilaJadeteFurto2019=0;
    int manhaVilaJadeteFurto2019=0;
    int tardeVilaJadeteFurto2019=0;
    int noiteVilaJadeteFurto2019=0;

    int madrugadaVilaJadeteRoubo2019=0;
    int manhaVilaJadeteRoubo2019=0;
    int tardeVilaJadeteRoubo2019=0;
    int noiteVilaJadeteRoubo2019=0;

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


        barChart = (BarChart) getSameChart(barChart, "", Color.RED, Color.TRANSPARENT, 3000);
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


        int[] rouboss = new int[]{madrugadaAeroportoRoubo, manhaAeroportoRoubo, tardeAeroportoRoubo,noiteAeroportoRoubo};

        int[] furtoss = new int[]{madrugadaAeroportoFurto, manhaAeroportoFurto, tardeAeroportoFurto,noiteAeroportoFurto};

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


        int[] rouboss = new int[]{madrugadaAltoPocoesRoubo, manhaAltoPocoesRoubo, tardeAltoPocoesRoubo,noiteAltoPocoesRoubo};

        int[] furtoss = new int[]{madrugadaAltoPocoesFurto, manhaAltoPocoesFurto, tardeAltoPocoesFurto,noiteAltoPocoesFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    public void alvoradaTodosAnos() {


        int[] rouboss = new int[]{madrugadaAlvoradaRoubo, manhaAlvoradaRoubo, tardeAlvoradaRoubo,noiteAlvoradaRoubo};

        int[] furtoss = new int[]{madrugadaAlvoradaFurto, manhaAlvoradaFurto, tardeAlvoradaFurto,noiteAlvoradaFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    public void bandeirantesTodosAnos() {


        int[] rouboss = new int[]{madrugadaBandeirantesRoubo, manhaBandeirantesRoubo, tardeBandeirantesRoubo,noiteBandeirantesRoubo};

        int[] furtoss = new int[]{madrugadaBandeirantesFurto, manhaBandeirantesFurto, tardeBandeirantesFurto,noiteBandeirantesFurto};


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


        int[] rouboss = new int[]{madrugadaBoaEsperancaRoubo, manhaBoaEsperancaRoubo, tardeBoaEsperancaRoubo,noiteBoaEsperancaRoubo};

        int[] furtoss = new int[]{madrugadaBoaEsperancaFurto, manhaBoaEsperancaFurto, tardeBoaEsperancaFurto,noiteBoaEsperancaFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void boaVistaTodosAnos() {


        int[] rouboss = new int[]{madrugadaBoaVistaRoubo, manhaBoaVistaRoubo, tardeBoaVistaRoubo,noiteBoaVistaRoubo};

        int[] furtoss = new int[]{madrugadaBoaVistaFurto, manhaBoaVistaFurto, tardeBoaVistaFurto,noiteBoaVistaFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void brasilandiaTodosAnos() {


        int[] rouboss = new int[]{madrugadaVilaBrasilandiaRoubo, manhaVilaBrasilandiaRoubo, tardeVilaBrasilandiaRoubo,noiteVilaBrasilandiaRoubo};

        int[] furtoss = new int[]{madrugadaVilaBrasilandiaFurto, manhaVilaBrasilandiaFurto, tardeVilaBrasilandiaFurto,noiteVilaBrasilandiaFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    private void brejoDoAmparoTodosAnos() {

        int[] rouboss = new int[]{madrugadaBrejoAmparoRoubo, manhaBrejoAmparoRoubo, tardeBrejoAmparoRoubo,noiteBrejoAmparoRoubo};

        int[] furtoss = new int[]{madrugadaBrejoAmparoFurto, manhaBrejoAmparoFurto, tardeBrejoAmparoFurto,noiteBrejoAmparoFurto};


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

        int[] rouboss = new int[]{madrugadaCentroRoubo, manhaCentroRoubo, tardeCentroRoubo,noiteCentroRoubo};

        int[] furtoss = new int[]{madrugadaCentroFurto, manhaCentroFurto, tardeCentroFurto,noiteCentroFurto};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void ceramicaTodosAnos() {

        int[] rouboss = new int[]{madrugadaCeramicaRoubo, manhaCeramicaRoubo, tardeCeramicaRoubo,noiteCeramicaRoubo};

        int[] furtoss = new int[]{madrugadaCeramicaFurto, manhaCeramicaFurto, tardeCeramicaFurto,noiteCeramicaFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void eldoradoTodosAnos() {

        int[] rouboss = new int[]{madrugadaEldoradoRoubo, manhaEldoradoRoubo, tardeEldoradoRoubo,noiteEldoradoRoubo};

        int[] furtoss = new int[]{madrugadaEldoradoFurto, manhaEldoradoFurto, tardeEldoradoFurto,noiteEldoradoFurto};


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


        int[] rouboss = new int[]{madrugadaFranklimRoubo, manhaFranklimRoubo, tardeFranklimRoubo,noiteFranklimRoubo};

        int[] furtoss = new int[]{madrugadaFranklimFurto, manhaFranklimFurto, tardeFranklimFurto,noiteFranklimFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jadeteTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaJadeteRoubo, manhaVilaJadeteRoubo, tardeVilaJadeteRoubo,noiteVilaJadeteRoubo};

        int[] furtoss = new int[]{madrugadaVilaJadeteFurto, manhaVilaJadeteFurto, tardeVilaJadeteFurto,noiteVilaJadeteFurto};


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


        int[] rouboss = new int[]{madrugadaJardimDanielRoubo, manhaJardimDanielRoubo, tardeJardimDanielRoubo,noiteJardimDanielRoubo};

        int[] furtoss = new int[]{madrugadaJardimDanielFurto, manhaJardimDanielFurto, tardeJardimDanielFurto,noiteJardimDanielFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jardimEstelaTodosAnos() {

        int[] rouboss = new int[]{madrugadaJardimEstrelaRoubo, manhaJardimEstrelaRoubo, tardeJardimEstrelaRoubo,noiteJardimEstrelaRoubo};

        int[] furtoss = new int[]{madrugadaJardimEstrelaFurto, manhaJardimEstrelaFurto, tardeJardimEstrelaFurto,noiteJardimEstrelaFurto};


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


        int[] rouboss = new int[]{madrugadaJussaraRoubo, manhaJussaraRoubo, tardeJussaraRoubo,noiteJussaraRoubo};

        int[] furtoss = new int[]{madrugadaJussaraFurto, manhaJussaraFurto, tardeJussaraFurto,noiteJussaraFurto};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void levianopolisTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaLevianopolisRoubo, manhaVilaLevianopolisRoubo, tardeVilaLevianopolisRoubo,noiteVilaLevianopolisRoubo};

        int[] furtoss = new int[]{madrugadaVilaLevianopolisFurto, manhaVilaLevianopolisFurto, tardeVilaLevianopolisFurto,noiteVilaLevianopolisFurto};


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

        int[] rouboss = new int[]{madrugadaQuintasMangueirasRoubo, manhaQuintasMangueirasRoubo, tardeQuintasMangueirasRoubo,noiteQuintasMangueirasRoubo};

        int[] furtoss = new int[]{madrugadaQuintasMangueirasFurto, manhaQuintasMangueirasFurto, tardeQuintasMangueirasFurto,noiteQuintasMangueirasFurto};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void riachoDaCruzTodosAnos() {

        int[] rouboss = new int[]{madrugadaRiachoCruzRoubo, manhaRiachoCruzRoubo, tardeRiachoCruzRoubo,noiteRiachoCruzRoubo};

        int[] furtoss = new int[]{madrugadaRiachoCruzFurto, manhaRiachoCruzFurto, tardeRiachoCruzFurto,noiteRiachoCruzFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoaoTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaSaoJoaoRoubo, manhaVilaSaoJoaoRoubo, tardeVilaSaoJoaoRoubo,noiteVilaSaoJoaoRoubo};

        int[] furtoss = new int[]{madrugadaVilaSaoJoaoFurto, manhaVilaSaoJoaoFurto, tardeVilaSaoJoaoFurto,noiteVilaSaoJoaoFurto};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoaquimTodosAnos() {


        int[] rouboss = new int[]{madrugadaSaoJoaquimRoubo, manhaSaoJoaquimRoubo, tardeSaoJoaquimRoubo,noiteSaoJoaquimRoubo};

        int[] furtoss = new int[]{madrugadaSaoJoaquimFurto, manhaSaoJoaquimFurto, tardeSaoJoaquimFurto,noiteSaoJoaquimFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoMiguelTodosAnos() {


        int[] rouboss = new int[]{madrugadaSaoMiguelRoubo, manhaSaoMiguelRoubo, tardeSaoMiguelRoubo,noiteSaoMiguelRoubo};

        int[] furtoss = new int[]{madrugadaSaoMiguelFurto, manhaSaoMiguelFurto, tardeSaoMiguelFurto,noiteSaoMiguelFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoVicenteTodosAnos() {

        int[] rouboss = new int[]{madrugadaSaoVicenteRoubo, manhaSaoVicenteRoubo, tardeSaoVicenteRoubo,noiteSaoVicenteRoubo};

        int[] furtoss = new int[]{madrugadaSaoVicenteFurto, manhaSaoVicenteFurto, tardeSaoVicenteFurto,noiteSaoVicenteFurto};


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


        int[] rouboss = new int[]{madrugadaTejucoRoubo, manhaTejucoRoubo, tardeTejucoRoubo,noiteTejucoRoubo};

        int[] furtoss = new int[]{madrugadaTejucoFurto, manhaTejucoFurto, tardeTejucoFurto,noiteTejucoFurto};

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

        int[] rouboss = new int[]{madrugadaVilaFatimaRoubo, manhaVilaFatimaRoubo, tardeVilaFatimaRoubo,noiteVilaFatimaRoubo};

        int[] furtoss = new int[]{madrugadaVilaFatimaFurto, manhaVilaFatimaFurto, tardeVilaFatimaFurto,noiteVilaFatimaFurto};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void pandeirosTodosAnos() {

        int[] rouboss = new int[]{madrugadaPandeirosRoubo, manhaPandeirosRoubo, tardePandeirosRoubo,noitePandeirosRoubo};

        int[] furtoss = new int[]{madrugadaPandeirosFurto, manhaPandeirosFurto, tardePandeirosFurto,noitePandeirosFurto};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaPaulaTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaPaulaRoubo, manhaVilaPaulaRoubo, tardeVilaPaulaRoubo,noiteVilaPaulaRoubo};

        int[] furtoss = new int[]{madrugadaVilaPaulaFurto, manhaVilaPaulaFurto, tardeVilaPaulaFurto,noiteVilaPaulaFurto};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaVerdeTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaVerdeRoubo, manhaVilaVerdeRoubo, tardeVilaVerdeRoubo,noiteVilaVerdeRoubo};

        int[] furtoss = new int[]{madrugadaVilaVerdeFurto, manhaVilaVerdeFurto, tardeVilaVerdeFurto,noiteVilaVerdeFurto};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaVianaTodosAnos() {

        int[] rouboss = new int[]{madrugadaVilaVianaRoubo, manhaVilaVianaRoubo, tardeVilaVianaRoubo,noiteVilaVianaRoubo};

        int[] furtoss = new int[]{madrugadaVilaVianaFurto, manhaVilaVianaFurto, tardeVilaVianaFurto,noiteVilaVianaFurto};


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


        int[] rouboss = new int[]{madrugadaAeroportoRoubo2019, manhaAeroportoRoubo2019, tardeAeroportoRoubo2019,noiteAeroportoRoubo2019};

        int[] furtoss = new int[]{madrugadaAeroportoFurto2019, manhaAeroportoFurto2019, tardeAeroportoFurto2019,noiteAeroportoFurto2019};

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

        int[] rouboss = new int[]{madrugadaAltoPocoesRoubo2019, manhaAltoPocoesRoubo2019, tardeAltoPocoesRoubo2019,noiteAltoPocoesRoubo2019};

        int[] furtoss = new int[]{madrugadaAltoPocoesFurto2019, manhaAltoPocoesFurto2019, tardeAltoPocoesFurto2019,noiteAltoPocoesFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    public void alvorada2019() {


        int[] rouboss = new int[]{madrugadaAlvoradaRoubo2019, manhaAlvoradaRoubo2019, tardeAlvoradaRoubo2019,noiteAlvoradaRoubo2019};

        int[] furtoss = new int[]{madrugadaAlvoradaFurto2019, manhaAlvoradaFurto2019, tardeAlvoradaFurto2019,noiteAlvoradaFurto2019};



        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();

    }

    public void bandeirantes2019() {



        int[] rouboss = new int[]{madrugadaBandeirantesRoubo2019, manhaBandeirantesRoubo2019, tardeBandeirantesRoubo2019,noiteBandeirantesRoubo2019};

        int[] furtoss = new int[]{madrugadaBandeirantesFurto2019, manhaBandeirantesFurto2019, tardeBandeirantesFurto2019,noiteBandeirantesFurto2019};


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

        int[] rouboss = new int[]{madrugadaBoaEsperancaRoubo2019, manhaBoaEsperancaRoubo2019, tardeBoaEsperancaRoubo2019,noiteBoaEsperancaRoubo2019};

        int[] furtoss = new int[]{madrugadaBoaEsperancaFurto2019, manhaBoaEsperancaFurto2019, tardeBoaEsperancaFurto2019,noiteBoaEsperancaFurto2019};



        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void boaVista2019() {


        int[] rouboss = new int[]{madrugadaBoaVistaRoubo2019, manhaBoaVistaRoubo2019, tardeBoaVistaRoubo2019,noiteBoaVistaRoubo2019};

        int[] furtoss = new int[]{madrugadaBoaVistaFurto2019, manhaBoaVistaFurto2019, tardeBoaVistaFurto2019,noiteBoaVistaFurto2019};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void brasilandia2019() {

        int[] rouboss = new int[]{madrugadaVilaBrasilandiaRoubo2019, manhaVilaBrasilandiaRoubo2019, tardeVilaBrasilandiaRoubo2019,noiteVilaBrasilandiaRoubo2019};

        int[] furtoss = new int[]{madrugadaVilaBrasilandiaFurto2019, manhaVilaBrasilandiaFurto2019, tardeVilaBrasilandiaFurto2019,noiteVilaBrasilandiaFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void brejoDoAmparo2019() {

        int[] rouboss = new int[]{madrugadaBrejoAmparoRoubo2019, manhaBrejoAmparoRoubo2019, tardeBrejoAmparoRoubo2019,noiteBrejoAmparoRoubo2019};

        int[] furtoss = new int[]{madrugadaBrejoAmparoFurto2019, manhaBrejoAmparoFurto2019, tardeBrejoAmparoFurto2019,noiteBrejoAmparoFurto2019};



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


        int[] rouboss = new int[]{madrugadaCentroRoubo2019, manhaCentroRoubo2019, tardeCentroRoubo2019,noiteCentroRoubo2019};

        int[] furtoss = new int[]{madrugadaCentroFurto2019, manhaCentroFurto2019, tardeCentroFurto2019,noiteCentroFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void ceramica2019() {


        int[] rouboss = new int[]{madrugadaCeramicaRoubo2019, manhaCeramicaRoubo2019, tardeCeramicaRoubo2019,noiteCeramicaRoubo2019};

        int[] furtoss = new int[]{madrugadaCeramicaFurto2019, manhaCeramicaFurto2019, tardeCeramicaFurto2019,noiteCeramicaFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void eldorado2019() {


        int[] rouboss = new int[]{madrugadaEldoradoRoubo2019, manhaEldoradoRoubo2019, tardeEldoradoRoubo2019,noiteEldoradoRoubo2019};

        int[] furtoss = new int[]{madrugadaEldoradoFurto2019, manhaEldoradoFurto2019, tardeEldoradoFurto2019,noiteEldoradoFurto2019};



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

        int[] rouboss = new int[]{madrugadaFranklimRoubo2019, manhaFranklimRoubo2019, tardeFranklimRoubo2019,noiteFranklimRoubo2019};

        int[] furtoss = new int[]{madrugadaFranklimFurto2019, manhaFranklimFurto2019, tardeFranklimFurto2019,noiteFranklimFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jadete2019() {

        int[] rouboss = new int[]{madrugadaVilaJadeteRoubo2019, manhaVilaJadeteRoubo2019, tardeVilaJadeteRoubo2019,noiteVilaJadeteRoubo2019};

        int[] furtoss = new int[]{madrugadaVilaJadeteFurto2019, manhaVilaJadeteFurto2019, tardeVilaJadeteFurto2019,noiteVilaJadeteFurto2019};

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


        int[] rouboss = new int[]{madrugadaJardimDanielRoubo2019, manhaJardimDanielRoubo2019, tardeJardimDanielRoubo2019,noiteJardimDanielRoubo2019};

        int[] furtoss = new int[]{madrugadaJardimDanielFurto2019, manhaJardimDanielFurto2019, tardeJardimDanielFurto2019,noiteJardimDanielFurto2019};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void jardimEstela2019() {


        int[] rouboss = new int[]{madrugadaJardimEstrelaRoubo2019, manhaJardimEstrelaRoubo2019, tardeJardimEstrelaRoubo2019,noiteJardimEstrelaRoubo2019};

        int[] furtoss = new int[]{madrugadaJardimEstrelaFurto2019, manhaJardimEstrelaFurto2019, tardeJardimEstrelaFurto2019,noiteJardimEstrelaFurto2019};


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


        int[] rouboss = new int[]{madrugadaJussaraRoubo2019, manhaJussaraRoubo2019, tardeJussaraRoubo2019,noiteJussaraRoubo2019};

        int[] furtoss = new int[]{madrugadaJussaraFurto2019, manhaJussaraFurto2019, tardeJussaraFurto2019,noiteJussaraFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void levianopolis2019() {

        int[] rouboss = new int[]{madrugadaVilaLevianopolisRoubo2019, manhaVilaLevianopolisRoubo2019, tardeVilaLevianopolisRoubo2019,noiteVilaLevianopolisRoubo2019};

        int[] furtoss = new int[]{madrugadaVilaLevianopolisFurto2019, manhaVilaLevianopolisFurto2019, tardeVilaLevianopolisFurto2019,noiteVilaLevianopolisFurto2019};


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

        int[] rouboss = new int[]{madrugadaQuintasMangueirasRoubo2019, manhaQuintasMangueirasRoubo2019, tardeQuintasMangueirasRoubo2019,noiteQuintasMangueirasRoubo2019};

        int[] furtoss = new int[]{madrugadaQuintasMangueirasFurto2019, manhaQuintasMangueirasFurto2019, tardeQuintasMangueirasFurto2019,noiteQuintasMangueirasFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void riachoDaCruz2019() {

        int[] rouboss = new int[]{madrugadaRiachoCruzRoubo2019, manhaRiachoCruzRoubo2019, tardeRiachoCruzRoubo2019,noiteRiachoCruzRoubo2019};

        int[] furtoss = new int[]{madrugadaRiachoCruzFurto2019, manhaRiachoCruzFurto2019, tardeRiachoCruzFurto2019,noiteRiachoCruzFurto2019};



        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoao2019() {


        int[] rouboss = new int[]{madrugadaVilaSaoJoaoRoubo2019, manhaVilaSaoJoaoRoubo2019, tardeVilaSaoJoaoRoubo2019,noiteVilaSaoJoaoRoubo2019};

        int[] furtoss = new int[]{madrugadaVilaSaoJoaoFurto2019, manhaVilaSaoJoaoFurto2019, tardeVilaSaoJoaoFurto2019,noiteVilaSaoJoaoFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoJoaquim2019() {


        int[] rouboss = new int[]{madrugadaSaoJoaquimRoubo2019, manhaSaoJoaquimRoubo2019, tardeSaoJoaquimRoubo2019,noiteSaoJoaquimRoubo2019};

        int[] furtoss = new int[]{madrugadaSaoJoaquimFurto2019, manhaSaoJoaquimFurto2019, tardeSaoJoaquimFurto2019,noiteSaoJoaquimFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoMiguel2019() {

        int[] rouboss = new int[]{madrugadaSaoMiguelRoubo2019, manhaSaoMiguelRoubo2019, tardeSaoMiguelRoubo2019,noiteSaoMiguelRoubo2019};

        int[] furtoss = new int[]{madrugadaSaoMiguelFurto2019, manhaSaoMiguelFurto2019, tardeSaoMiguelFurto2019,noiteSaoMiguelFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void saoVicente2019() {

        int[] rouboss = new int[]{madrugadaSaoVicenteRoubo2019, manhaSaoVicenteRoubo2019, tardeSaoVicenteRoubo2019,noiteSaoVicenteRoubo2019};

        int[] furtoss = new int[]{madrugadaSaoVicenteFurto2019, manhaSaoVicenteFurto2019, tardeSaoVicenteFurto2019,noiteSaoVicenteFurto2019};



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


        int[] rouboss = new int[]{madrugadaTejucoRoubo2019, manhaTejucoRoubo2019, tardeTejucoRoubo2019,noiteTejucoRoubo2019};

        int[] furtoss = new int[]{madrugadaTejucoFurto2019, manhaTejucoFurto2019, tardeTejucoFurto2019,noiteTejucoFurto2019};


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

        int[] rouboss = new int[]{madrugadaVilaFatimaRoubo2019, manhaVilaFatimaRoubo2019, tardeVilaFatimaRoubo2019,noiteVilaFatimaRoubo2019};

        int[] furtoss = new int[]{madrugadaVilaFatimaFurto2019, manhaVilaFatimaFurto2019, tardeVilaFatimaFurto2019,noiteVilaFatimaFurto2019};



        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void pandeiros2019() {

        int[] rouboss = new int[]{madrugadaPandeirosRoubo2019, manhaPandeirosRoubo2019, tardePandeirosRoubo2019,noitePandeirosRoubo2019};

        int[] furtoss = new int[]{madrugadaPandeirosFurto2019, manhaPandeirosFurto2019, tardePandeirosFurto2019,noitePandeirosFurto2019};

        nomes = turnos;

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaPaula2019() {

        int[] rouboss = new int[]{madrugadaVilaPaulaRoubo2019, manhaVilaPaulaRoubo2019, tardeVilaPaulaRoubo2019,noiteVilaPaulaRoubo2019};

        int[] furtoss = new int[]{madrugadaVilaPaulaFurto2019, manhaVilaPaulaFurto2019, tardeVilaPaulaFurto2019,noiteVilaPaulaFurto2019};

        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaVerde2019() {

        int[] rouboss = new int[]{madrugadaVilaVerdeRoubo2019, manhaVilaVerdeRoubo2019, tardeVilaVerdeRoubo2019,noiteVilaVerdeRoubo2019};

        int[] furtoss = new int[]{madrugadaVilaVerdeFurto2019, manhaVilaVerdeFurto2019, tardeVilaVerdeFurto2019,noiteVilaVerdeFurto2019};


        nomes = turnos;
        roubos = rouboss;
        furtos = furtoss;
        cores = cor;
        legenda = legendaGrafico;


        criarGraficos();


    }

    private void vilaViana2019() {


        int[] rouboss = new int[]{madrugadaVilaVianaRoubo2019, manhaVilaVianaRoubo2019, tardeVilaVianaRoubo2019,noiteVilaVianaRoubo2019};

        int[] furtoss = new int[]{madrugadaVilaVianaFurto2019, manhaVilaVianaFurto2019, tardeVilaVianaFurto2019,noiteVilaVianaFurto2019};


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


        if (procuraBairro.contains("Aeroporto") && b.getStatus().equals("Furtada")) {




            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAeroportoFurto++;

            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAeroportoFurto++;

            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAeroportoFurto++;

            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAeroportoFurto++;
            }

        }


    }

    public void ruasBairroAeroporto2019() {


        if (procuraBairro.contains("Aeroporto") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Aeroporto") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {




            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAeroportoFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAeroportoFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAeroportoFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAeroportoFurto2019++;
            }


        }
    }

    public void ruasBairroAltoPocoesTodosAnos() {


        if (procuraBairro.contains("Alto dos Pocões") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Alto dos Pocões") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAltoPocoesFurto++;

            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAltoPocoesFurto++;

            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAltoPocoesFurto++;

            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAltoPocoesFurto++;
            }

        }


    }

    public void ruasBairroAltoPocoes2019() {


        if (procuraBairro.contains("Alto dos Pocões") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Alto dos Pocões") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAltoPocoesFurto2019++;

            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAltoPocoesFurto2019++;

            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAltoPocoesFurto2019++;

            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAltoPocoesFurto2019++;
            }


        }
    }

    public void ruasBairroAlvoradaTodosAnos() {


        if (procuraBairro.contains("Alvorada") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Alvorada") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAlvoradaFurto++;

            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAlvoradaFurto++;

            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAlvoradaFurto++;

            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAlvoradaFurto++;
            }


        }


    }

    public void ruasBairroAlvorada2019() {


        if (procuraBairro.contains("Alvorada") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Alvorada") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaAlvoradaFurto2019++;

            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaAlvoradaFurto2019++;

            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeAlvoradaFurto2019++;

            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteAlvoradaFurto2019++;
            }



        }
    }

    public void ruasBairroBandeirantesTodosAnos() {


        if (procuraBairro.contains("Bandeirantes") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Bandeirantes") && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBandeirantesFurto++;

            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBandeirantesFurto++;

            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBandeirantesFurto++;

            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBandeirantesFurto++;
            }



        }


    }

    public void ruasBairroBandeirantes2019() {


        if (procuraBairro.contains("Bandeirantes") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Bandeirantes") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBandeirantesFurto2019++;

            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBandeirantesFurto2019++;

            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBandeirantesFurto2019++;

            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBandeirantesFurto2019++;
            }




        }
    }

    public void ruasBairroBoaEsperancaTodosAnos() {


        if (procuraBairro.contains("Boa Esperança") && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Boa Esperança") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBoaEsperancaFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBoaEsperancaFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBoaEsperancaFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBoaEsperancaFurto++;
            }


        }


    }

    public void ruasBairroBoaEsperanca2019() {


        if (procuraBairro.contains("Boa Esperança") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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



        if (procuraBairro.contains("Boa Esperança") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBoaEsperancaFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBoaEsperancaFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBoaEsperancaFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBoaEsperancaFurto2019++;
            }

        }
    }

    public void ruasBairroBoaVistaTodosAnos() {


        if (procuraBairro.contains("Boa Vista") && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Boa Vista") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBoaVistaFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBoaVistaFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBoaVistaFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBoaVistaFurto++;
            }

        }

    }

    public void ruasBairroBoaVista2019() {


        if (procuraBairro.contains("Boa Vista") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Boa Vista") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBoaVistaFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBoaVistaFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBoaVistaFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBoaVistaFurto2019++;
            }

        }
    }

    public void ruasBairroVilaBrasilandiaTodosAnos() {


        if (procuraBairro.contains("Vila Brasilandia") && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Vila Brasilandia") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaBrasilandiaFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaBrasilandiaFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaBrasilandiaFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaBrasilandiaFurto++;
            }

        }


    }

    public void ruasBairroVilaBrasilandia2019() {


        if (procuraBairro.contains("Vila Brasilandia") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Vila Brasilandia") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaBrasilandiaFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaBrasilandiaFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaBrasilandiaFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaBrasilandiaFurto2019++;
            }


        }
    }

    public void ruasBairroBrejoAmparoTodosAnos() {


        if (procuraBairro.contains("Brejo do Amparo") && b.getStatus().equals("Roubada")) {




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


        if (procuraBairro.contains("Brejo do Amparo") && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBrejoAmparoFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBrejoAmparoFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBrejoAmparoFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBrejoAmparoFurto++;
            }

        }


    }

    public void ruasBairroBrejoAmparo2019() {


        if (procuraBairro.contains("Brejo do Amparo") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Brejo do Amparo") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaBrejoAmparoFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaBrejoAmparoFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeBrejoAmparoFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteBrejoAmparoFurto2019++;
            }

        }
    }

    public void ruasBairroCentroTodosAnos() {


        if (procuraBairro.contains("Centro") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Centro") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaCentroFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaCentroFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeCentroFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteCentroFurto++;
            }


        }


    }

    public void ruasBairroCentro2019() {


        if (procuraBairro.contains("Centro") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Centro") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaCentroFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaCentroFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeCentroFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteCentroFurto2019++;
            }

        }
    }

    public void ruasBairroCeramicaTodosAnos() {


        if (procuraBairro.contains("Ceramica") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Ceramica") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaCeramicaFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaCeramicaFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeCeramicaFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteCeramicaFurto++;
            }

        }


    }

    public void ruasBairroCeramica2019() {


        if (procuraBairro.contains("Ceramica") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Ceramica") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaCeramicaFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaCeramicaFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeCeramicaFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteCeramicaFurto2019++;
            }

        }
    }

    public void ruasBairroEldoradoTodosAnos() {


        if (procuraBairro.contains("Eldorado") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Eldorado") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaEldoradoFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaEldoradoFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeEldoradoFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteEldoradoFurto++;
            }


        }


    }

    public void ruasBairroEldorado2019() {


        if (procuraBairro.contains("Eldorado") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Eldorado") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {




            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaEldoradoFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaEldoradoFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeEldoradoFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteEldoradoFurto2019++;
            }

        }
    }

    public void ruasBairroFranklimTodosAnos() {


        if (procuraBairro.contains("Franklim") && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Franklim") && b.getStatus().equals("Furtada")) {




            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaFranklimFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaFranklimFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeFranklimFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteFranklimFurto++;
            }

        }


    }

    public void ruasBairroFranklim2019() {


        if (procuraBairro.contains("Franklim") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Franklim") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaFranklimFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaFranklimFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeFranklimFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteFranklimFurto2019++;
            }

        }
    }

    public void ruasBairroJardimDanielTodosAnos() {


        if (procuraBairro.contains("Jardim Daniel") && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Jardim Daniel") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJardimDanielFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJardimDanielFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJardimDanielFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJardimDanielFurto++;
            }

        }


    }

    public void ruasBairroJardimDaniel2019() {


        if (procuraBairro.contains("Jardim Daniel") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Jardim Daniel") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJardimDanielFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJardimDanielFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJardimDanielFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJardimDanielFurto2019++;
            }


        }
    }

    public void ruasBairroJardimEstrelaTodosAnos() {


        if (procuraBairro.contains("Jardim Estrela") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Jardim Estrela") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJardimEstrelaFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJardimEstrelaFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJardimEstrelaFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJardimEstrelaFurto++;
            }

        }


    }

    public void ruasBairroJardimEstrela2019() {


        if (procuraBairro.contains("Jardim Estrela") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Jardim Estrela") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJardimEstrelaFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJardimEstrelaFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJardimEstrelaFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJardimEstrelaFurto2019++;
            }

        }
    }

    public void ruasBairroJussaraTodosAnos() {


        if (procuraBairro.contains("Jussara") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Jussara") && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJussaraFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJussaraFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJussaraFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJussaraFurto++;
            }

        }


    }

    public void ruasBairroJussara2019() {


        if (procuraBairro.contains("Jussara") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Jussara") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaJussaraFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaJussaraFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeJussaraFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteJussaraFurto2019++;
            }

        }
    }

    public void ruasBairroVilaLevianopolisTodosAnos() {


        if (procuraBairro.contains("Vila Levianopolis") && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Vila Levianopolis") && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaLevianopolisFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaLevianopolisFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaLevianopolisFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaLevianopolisFurto++;
            }

        }


    }

    public void ruasBairroVilaLevianopolis2019() {


        if (procuraBairro.contains("Vila Levianopolis") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Vila Levianopolis") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaLevianopolisFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaLevianopolisFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaLevianopolisFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaLevianopolisFurto2019++;
            }

        }
    }

    public void ruasBairroQuintasMangueirasTodosAnos() {


        if (procuraBairro.contains("Quintas das Mangueiras") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Quintas das Mangueiras") && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaQuintasMangueirasFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaQuintasMangueirasFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeQuintasMangueirasFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteQuintasMangueirasFurto++;
            }

        }


    }

    public void ruasBairroQuintasMangueiras2019() {


        if (procuraBairro.contains("Quintas das Mangueiras") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Quintas das Mangueiras") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaQuintasMangueirasFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaQuintasMangueirasFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeQuintasMangueirasFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteQuintasMangueirasFurto2019++;
            }

        }
    }

    public void ruasBairroRiachoCruzTodosAnos() {


        if (procuraBairro.contains("Riacho da Cruz") && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Riacho da Cruz") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaRiachoCruzFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaRiachoCruzFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeRiachoCruzFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteRiachoCruzFurto++;
            }

        }


    }

    public void ruasBairroRiachoCruz2019() {


        if (procuraBairro.contains("Riacho da Cruz") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {




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


        if (procuraBairro.contains("Riacho da Cruz") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaRiachoCruzFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaRiachoCruzFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeRiachoCruzFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteRiachoCruzFurto2019++;
            }

        }
    }

    public void ruasBairroVilaSaoJoaoTodosAnos() {


        if (procuraBairro.contains("Vila Sao Joao") && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Vila Sao Joao") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaSaoJoaoFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaSaoJoaoFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaSaoJoaoFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaSaoJoaoFurto++;
            }



        }


    }

    public void ruasBairroVilaSaoJoao2019() {


        if (procuraBairro.contains("Vila Sao Joao") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Vila Sao Joao") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaSaoJoaoFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaSaoJoaoFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaSaoJoaoFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaSaoJoaoFurto2019++;
            }

        }
    }

    public void ruasBairroSaoJoaquimTodosAnos() {


        if (procuraBairro.contains("São Joaquim") && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("São Joaquim") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoJoaquimFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoJoaquimFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoJoaquimFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoJoaquimFurto++;
            }


        }


    }

    public void ruasBairroSaoJoaquim2019() {


        if (procuraBairro.contains("São Joaquim") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("São Joaquim") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoJoaquimFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoJoaquimFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoJoaquimFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoJoaquimFurto2019++;
            }


        }
    }

    public void ruasBairroSaoMiguelTodosAnos() {


        if (procuraBairro.contains("Vila Sao Miguel") && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Vila Sao Miguel") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoMiguelFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoMiguelFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoMiguelFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoMiguelFurto++;
            }

        }


    }

    public void ruasBairroSaoMiguel2019() {


        if (procuraBairro.contains("Vila Sao Miguel") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Vila Sao Miguel") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoMiguelFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoMiguelFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoMiguelFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoMiguelFurto2019++;
            }



        }
    }

    public void ruasBairroSaoVicenteTodosAnos() {


        if (procuraBairro.contains("São Vicente") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("São Vicente") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoVicenteFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoVicenteFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoVicenteFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoVicenteFurto++;
            }

        }


    }

    public void ruasBairroSaoVicente2019() {


        if (procuraBairro.contains("São Vicente") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("São Vicente") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaSaoVicenteFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaSaoVicenteFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeSaoVicenteFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteSaoVicenteFurto2019++;
            }


        }
    }

    public void ruasBairroTejucoTodosAnos() {


        if (procuraBairro.contains("Tejuco") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Tejuco") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaTejucoFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaTejucoFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeTejucoFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteTejucoFurto++;
            }

        }


    }

    public void ruasBairroTejuco2019() {


        if (procuraBairro.contains("Tejuco") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Tejuco") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaTejucoFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaTejucoFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeTejucoFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteTejucoFurto2019++;
            }

        }
    }

    public void ruasBairroVilaFatimaTodosAnos() {


        if (procuraBairro.contains("Vila Fatima") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Vila Fatima") && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaFatimaFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaFatimaFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaFatimaFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaFatimaFurto++;
            }

        }


    }

    public void ruasBairroVilaFatima2019() {


        if (procuraBairro.contains("Vila Fatima") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Vila Fatima") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaFatimaFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaFatimaFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaFatimaFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaFatimaFurto2019++;
            }

        }
    }

    public void ruasBairroPandeirosTodosAnos() {


        if (procuraBairro.contains("Pandeiros") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Pandeiros") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaPandeirosFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaPandeirosFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardePandeirosFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noitePandeirosFurto++;
            }

        }


    }

    public void ruasBairroPandeiros2019() {


        if (procuraBairro.contains("Pandeiros") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Pandeiros") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {



            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaPandeirosFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaPandeirosFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardePandeirosFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noitePandeirosFurto2019++;
            }

        }
    }

    public void ruasBairroVilaPaulaTodosAnos() {


        if (procuraBairro.contains("Vila Paula") && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Vila Paula") && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaPaulaFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaPaulaFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaPaulaFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaPaulaFurto++;
            }


        }


    }

    public void ruasBairroVilaPaula2019() {


        if (procuraBairro.contains("Vila Paula") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Vila Paula") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaPaulaFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaPaulaFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaPaulaFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaPaulaFurto2019++;
            }
        }
    }

    public void ruasBairroVilaVerdeTodosAnos() {


        if (procuraBairro.contains("Vila Verde") && b.getStatus().equals("Roubada")) {



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


        if (procuraBairro.contains("Vila Verde") && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaVerdeFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaVerdeFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaVerdeFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaVerdeFurto++;
            }



        }


    }

    public void ruasBairroVilaVerde2019() {


        if (procuraBairro.contains("Vila Verde") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Vila Verde") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaVerdeFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaVerdeFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaVerdeFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaVerdeFurto2019++;
            }

        }
    }

    public void ruasBairroVilaVianaTodosAnos() {


        if (procuraBairro.contains("Vila Viana") && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.contains("Vila Viana") && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaVianaFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaVianaFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaVianaFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaVianaFurto++;
            }


        }


    }

    public void ruasBairroVilaViana2019() {


        if (procuraBairro.equals("Vila Viana") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.equals("Vila Viana") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaVianaFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaVianaFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaVianaFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaVianaFurto2019++;
            }

        }
    }

    public void ruasBairroJadeteTodosAnos() {


        if (procuraBairro.equals("Vila Jadete") && b.getStatus().equals("Roubada")) {

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


        if (procuraBairro.equals("Vila Jadete") && b.getStatus().equals("Furtada")) {

            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaJadeteFurto++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaJadeteFurto++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaJadeteFurto++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaJadeteFurto++;
            }


        }


    }

    public void ruasBairroJadete2019() {


        if (procuraBairro.contains("Vila Jadete") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Roubada")) {


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


        if (procuraBairro.contains("Vila Jadete") && procuraAno.contains(anoProcura2019) && b.getStatus().equals("Furtada")) {


            if (horaDaOcorrencia >= madrugadaMin && horaDaOcorrencia <= madrugadaMax ){
                madrugadaVilaJadeteFurto2019++;
            }


            if (horaDaOcorrencia >= manhaMin && horaDaOcorrencia <= manhaMax ){
                manhaVilaJadeteFurto2019++;
            }

            if (horaDaOcorrencia >= tardeMin && horaDaOcorrencia <= tardeMax ){
                tardeVilaJadeteFurto2019++;
            }

            if (horaDaOcorrencia >= noiteMin && horaDaOcorrencia <= noiteMax ){
                noiteVilaJadeteFurto2019++;
            }


        }


    }

}



