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
import android.widget.AutoCompleteTextView;
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


    int contandoRuaUmBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaDoisBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaTresBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaQuatroBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaCincoBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaSeisBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaTrezeMaioBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaLuisTupinaBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaHermilioTupinaBoaEsperancaFurtoTodosAnos=0;
    int contandoRuaSaoGeraldoBoaEsperancaFurtoTodosAnos=0;


    int contandoRuaUmBoaEsperancaRouboTodosAnos=0;
    int contandoRuaDoisBoaEsperancaRouboTodosAnos=0;
    int contandoRuaTresBoaEsperancaRouboTodosAnos=0;
    int contandoRuaQuatroBoaEsperancaRouboTodosAnos=0;
    int contandoRuaCincoBoaEsperancaRouboTodosAnos=0;
    int contandoRuaSeisBoaEsperancaRouboTodosAnos=0;
    int contandoRuaTrezeMaioBoaEsperancaRouboTodosAnos=0;
    int contandoRuaLuisTupinaBoaEsperancaRouboTodosAnos=0;
    int contandoRuaHermilioTupinaBoaEsperancaRouboTodosAnos=0;
    int contandoRuaSaoGeraldoBoaEsperancaRouboTodosAnos=0;



    int contandoRuaUmBoaEsperancaFurto2019=0;
    int contandoRuaDoisBoaEsperancaFurto2019=0;
    int contandoRuaTresBoaEsperancaFurto2019=0;
    int contandoRuaQuatroBoaEsperancaFurto2019=0;
    int contandoRuaCincoBoaEsperancaFurto2019=0;
    int contandoRuaSeisBoaEsperancaFurto2019=0;
    int contandoRuaTrezeMaioBoaEsperancaFurto2019=0;
    int contandoRuaLuisTupinaBoaEsperancaFurto2019=0;
    int contandoRuaHermilioTupinaBoaEsperancaFurto2019=0;
    int contandoRuaSaoGeraldoBoaEsperancaFurto2019=0;


    int contandoRuaUmBoaEsperancaRoubo2019=0;
    int contandoRuaDoisBoaEsperancaRoubo2019=0;
    int contandoRuaTresBoaEsperancaRoubo2019=0;
    int contandoRuaQuatroBoaEsperancaRoubo2019=0;
    int contandoRuaCincoBoaEsperancaRoubo2019=0;
    int contandoRuaSeisBoaEsperancaRoubo2019=0;
    int contandoRuaTrezeMaioBoaEsperancaRoubo2019=0;
    int contandoRuaLuisTupinaBoaEsperancaRoubo2019=0;
    int contandoRuaHermilioTupinaBoaEsperancaRoubo2019=0;
    int contandoRuaSaoGeraldoBoaEsperancaRoubo2019=0;


    int contandoRuaABoaVistaFurtoTodosAnos=0;
    int contandoRuaBBoaVistaFurtoTodosAnos=0;
    int contandoRuaCBoaVistaFurtoTodosAnos=0;
    int contandoRuaDBoaVistaFurtoTodosAnos=0;
    int contandoRuaEBoaVistaFurtoTodosAnos=0;
    int contandoRuaFBoaVistaFurtoTodosAnos=0;
    int contandoRuaGBoaVistaFurtoTodosAnos=0;
    int contandoRuaHBoaVistaFurtoTodosAnos=0;
    int contandoRuaIBoaVistaFurtoTodosAnos=0;
    int contandoRuaJBoaVistaFurtoTodosAnos=0;
    int contandoRuaLBoaVistaFurtoTodosAnos=0;


    int contandoRuaABoaVistaRouboTodosAnos=0;
    int contandoRuaBBoaVistaRouboTodosAnos=0;
    int contandoRuaCBoaVistaRouboTodosAnos=0;
    int contandoRuaDBoaVistaRouboTodosAnos=0;
    int contandoRuaEBoaVistaRouboTodosAnos=0;
    int contandoRuaFBoaVistaRouboTodosAnos=0;
    int contandoRuaGBoaVistaRouboTodosAnos=0;
    int contandoRuaHBoaVistaRouboTodosAnos=0;
    int contandoRuaIBoaVistaRouboTodosAnos=0;
    int contandoRuaJBoaVistaRouboTodosAnos=0;
    int contandoRuaLBoaVistaRouboTodosAnos=0;



    int contandoRuaABoaVistaFurto2019=0;
    int contandoRuaBBoaVistaFurto2019=0;
    int contandoRuaCBoaVistaFurto2019=0;
    int contandoRuaDBoaVistaFurto2019=0;
    int contandoRuaEBoaVistaFurto2019=0;
    int contandoRuaFBoaVistaFurto2019=0;
    int contandoRuaGBoaVistaFurto2019=0;
    int contandoRuaHBoaVistaFurto2019=0;
    int contandoRuaIBoaVistaFurto2019=0;
    int contandoRuaJBoaVistaFurto2019=0;
    int contandoRuaLBoaVistaFurto2019=0;


    int contandoRuaABoaVistaRoubo2019=0;
    int contandoRuaBBoaVistaRoubo2019=0;
    int contandoRuaCBoaVistaRoubo2019=0;
    int contandoRuaDBoaVistaRoubo2019=0;
    int contandoRuaEBoaVistaRoubo2019=0;
    int contandoRuaFBoaVistaRoubo2019=0;
    int contandoRuaGBoaVistaRoubo2019=0;
    int contandoRuaHBoaVistaRoubo2019=0;
    int contandoRuaIBoaVistaRoubo2019=0;
    int contandoRuaJBoaVistaRoubo2019=0;
    int contandoRuaLBoaVistaRoubo2019=0;


    int contandoRuaUmVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaDoisVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaTresVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaQuatroVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaCincoVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaSeisVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaSeteVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaMinasGeraisVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaPiraporaVilaBrasilandiaFurtoTodosAnos=0;
    int contandoRuaSaoLuizVilaBrasilandiaFurtoTodosAnos=0;



    int contandoRuaUmVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaDoisVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaTresVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaQuatroVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaCincoVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaSeisVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaSeteVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaMinasGeraisVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaPiraporaVilaBrasilandiaRouboTodosAnos=0;
    int contandoRuaSaoLuizVilaBrasilandiaRouboTodosAnos=0;



    int contandoRuaUmVilaBrasilandiaFurto2019=0;
    int contandoRuaDoisVilaBrasilandiaFurto2019=0;
    int contandoRuaTresVilaBrasilandiaFurto2019=0;
    int contandoRuaQuatroVilaBrasilandiaFurto2019=0;
    int contandoRuaCincoVilaBrasilandiaFurto2019=0;
    int contandoRuaSeisVilaBrasilandiaFurto2019=0;
    int contandoRuaSeteVilaBrasilandiaFurto2019=0;
    int contandoRuaMinasGeraisVilaBrasilandiaFurto2019=0;
    int contandoRuaPiraporaVilaBrasilandiaFurto2019=0;
    int contandoRuaSaoLuizVilaBrasilandiaFurto2019=0;



    int contandoRuaUmVilaBrasilandiaRoubo2019=0;
    int contandoRuaDoisVilaBrasilandiaRoubo2019=0;
    int contandoRuaTresVilaBrasilandiaRoubo2019=0;
    int contandoRuaQuatroVilaBrasilandiaRoubo2019=0;
    int contandoRuaCincoVilaBrasilandiaRoubo2019=0;
    int contandoRuaSeisVilaBrasilandiaRoubo2019=0;
    int contandoRuaSeteVilaBrasilandiaRoubo2019=0;
    int contandoRuaMinasGeraisVilaBrasilandiaRoubo2019=0;
    int contandoRuaPiraporaVilaBrasilandiaRoubo2019=0;
    int contandoRuaSaoLuizVilaBrasilandiaRoubo2019=0;


    int contandoAlamedaRiachoBrejoAmparoFurtoTodosAnos=0;
    int contandoRuaBelaVistaBrejoAmparoFurtoTodosAnos=0;
    int contandoRuaSerraBrejoAmparoFurtoTodosAnos=0;
    int contandoRuaFloresBrejoAmparoFurtoTodosAnos=0;
    int contandoRuaPadreJosinoBrejoAmparoFurtoTodosAnos=0;
    int contandoRuaPadreRamiroAmparoFurtoTodosAnos=0;
    int contandoRuaTabatingaBrejoAmparoFurtoTodosAnos=0;
    int contandoRuaTabocaBrejoAmparoFurtoTodosAnos=0;
    int contandoRuaTrintaSeteBrejoAmparoFurtoTodosAnos=0;
    int contandoRuaTrintaOitoBrejoAmparoFurtoTodosAnos=0;

    int contandoAlamedaRiachoBrejoAmparoRouboTodosAnos=0;
    int contandoRuaBelaVistaBrejoAmparoRouboTodosAnos=0;
    int contandoRuaSerraBrejoAmparoRouboTodosAnos=0;
    int contandoRuaFloresBrejoAmparoRouboTodosAnos=0;
    int contandoRuaPadreJosinoBrejoAmparoRouboTodosAnos=0;
    int contandoRuaPadreRamiroAmparoRouboTodosAnos=0;
    int contandoRuaTabatingaBrejoAmparoRouboTodosAnos=0;
    int contandoRuaTabocaBrejoAmparoRouboTodosAnos=0;
    int contandoRuaTrintaSeteBrejoAmparoRouboTodosAnos=0;
    int contandoRuaTrintaOitoBrejoAmparoRouboTodosAnos=0;



    int contandoAlamedaRiachoBrejoAmparoFurto2019=0;
    int contandoRuaBelaVistaBrejoAmparoFurto2019=0;
    int contandoRuaSerraBrejoAmparoFurto2019=0;
    int contandoRuaFloresBrejoAmparoFurto2019=0;
    int contandoRuaPadreJosinoBrejoAmparoFurto2019=0;
    int contandoRuaPadreRamiroAmparoFurto2019=0;
    int contandoRuaTabatingaBrejoAmparoFurto2019=0;
    int contandoRuaTabocaBrejoAmparoFurto2019=0;
    int contandoRuaTrintaSeteBrejoAmparoFurto2019=0;
    int contandoRuaTrintaOitoBrejoAmparoFurto2019=0;

    int contandoAlamedaRiachoBrejoAmparoRoubo2019=0;
    int contandoRuaBelaVistaBrejoAmparoRoubo2019=0;
    int contandoRuaSerraBrejoAmparoRoubo2019=0;
    int contandoRuaFloresBrejoAmparoRoubo2019=0;
    int contandoRuaPadreJosinoBrejoAmparoRoubo2019=0;
    int contandoRuaPadreRamiroAmparoRoubo2019=0;
    int contandoRuaTabatingaBrejoAmparoRoubo2019=0;
    int contandoRuaTabocaBrejoAmparoRoubo2019=0;
    int contandoRuaTrintaSeteBrejoAmparoRoubo2019=0;
    int contandoRuaTrintaOitoBrejoAmparoRoubo2019=0;


    int contandoArturBernardesCentroFurtoTodosAnos=0;
    int contandoBaraoSaoRomaoCentroFurtoTodosAnos=0;
    int contandoBaraoRioBrancoCentroFurtoTodosAnos=0;
    int contandoConegoRamiroLeiteCentroFurtoTodosAnos=0;
    int contandoCelCassianoCentroFurtoTodosAnos=0;
    int contandoCelSerraoCentroFurtoTodosAnos=0;
    int contandoDomDanielCentroFurtoTodosAnos=0;
    int contandoGetulioVargasCentroFurtoTodosAnos=0;
    int contandoPadreHenriqueCentroFurtoTodosAnos=0;
    int contandoPracaTiradentesCentroFurtoTodosAnos=0;

    int contandoArturBernardesCentroRouboTodosAnos=0;
    int contandoBaraoSaoRomaoCentroRouboTodosAnos=0;
    int contandoBaraoRioBrancoCentroRouboTodosAnos=0;
    int contandoConegoRamiroLeiteCentroRouboTodosAnos=0;
    int contandoCelCassianoCentroRouboTodosAnos=0;
    int contandoCelSerraoCentroRouboTodosAnos=0;
    int contandoDomDanielCentroRouboTodosAnos=0;
    int contandoGetulioVargasCentroRouboTodosAnos=0;
    int contandoPadreHenriqueCentroRouboTodosAnos=0;
    int contandoPracaTiradentesCentroRouboTodosAnos=0;



    int contandoArturBernardesCentroFurto2019=0;
    int contandoBaraoSaoRomaoCentroFurto2019=0;
    int contandoBaraoRioBrancoCentroFurto2019=0;
    int contandoConegoRamiroLeiteCentroFurto2019=0;
    int contandoCelCassianoCentroFurto2019=0;
    int contandoCelSerraoCentroFurto2019=0;
    int contandoDomDanielCentroFurto2019=0;
    int contandoGetulioVargasCentroFurto2019=0;
    int contandoPadreHenriqueCentroFurto2019=0;
    int contandoPracaTiradentesCentroFurto2019=0;

    int contandoArturBernardesCentroRoubo2019=0;
    int contandoBaraoSaoRomaoCentroRoubo2019=0;
    int contandoBaraoRioBrancoCentroRoubo2019=0;
    int contandoConegoRamiroLeiteCentroRoubo2019=0;
    int contandoCelCassianoCentroRoubo2019=0;
    int contandoCelSerraoCentroRoubo2019=0;
    int contandoDomDanielCentroRoubo2019=0;
    int contandoGetulioVargasCentroRoubo2019=0;
    int contandoPadreHenriqueCentroRoubo2019=0;
    int contandoPracaTiradentesCentroRoubo2019=0;



    int contandoRuaAterroCeramicaFurtoTodosAnos=0;
    int contandoRuaBrasiliaMinasCeramicaFurtoTodosAnos=0;
    int contandoRuaItacarambiCeramicaFurtoTodosAnos=0;
    int contandoRuaItapiracabaCeramicaFurtoTodosAnos=0;
    int contandoRuaMangaCeramicaFurtoTodosAnos=0;
    int contandoRuaMontesClarosCeramicaFurtoTodosAnos=0;
    int contandoRuaMontalvaniaCeramicaFurtoTodosAnos=0;
    int contandoRuaVarzelandiaCeramicaFurtoTodosAnos=0;
    int contandoRuaMiltonCamposCeramicaFurtoTodosAnos=0;
    int contandoRuaMiltonSaCeramicaFurtoTodosAnos=0;


    int contandoRuaAterroCeramicaRouboTodosAnos=0;
    int contandoRuaBrasiliaMinasCeramicaRouboTodosAnos=0;
    int contandoRuaItacarambiCeramicaRouboTodosAnos=0;
    int contandoRuaItapiracabaCeramicaRouboTodosAnos=0;
    int contandoRuaMangaCeramicaRouboTodosAnos=0;
    int contandoRuaMontesClarosCeramicaRouboTodosAnos=0;
    int contandoRuaMontalvaniaCeramicaRouboTodosAnos=0;
    int contandoRuaVarzelandiaCeramicaRouboTodosAnos=0;
    int contandoRuaMiltonCamposCeramicaRouboTodosAnos=0;
    int contandoRuaMiltonSaCeramicaRouboTodosAnos=0;


    int contandoRuaAterroCeramicaFurto2019=0;
    int contandoRuaBrasiliaMinasCeramicaFurto2019=0;
    int contandoRuaItacarambiCeramicaFurto2019=0;
    int contandoRuaItapiracabaCeramicaFurto2019=0;
    int contandoRuaMangaCeramicaFurto2019=0;
    int contandoRuaMontesClarosCeramicaFurto2019=0;
    int contandoRuaMontalvaniaCeramicaFurto2019=0;
    int contandoRuaVarzelandiaCeramicaFurto2019=0;
    int contandoRuaMiltonCamposCeramicaFurto2019=0;
    int contandoRuaMiltonSaCeramicaFurto2019=0;


    int contandoRuaAterroCeramicaRoubo2019=0;
    int contandoRuaBrasiliaMinasCeramicaRoubo2019=0;
    int contandoRuaItacarambiCeramicaRoubo2019=0;
    int contandoRuaItapiracabaCeramicaRoubo2019=0;
    int contandoRuaMangaCeramicaRoubo2019=0;
    int contandoRuaMontesClarosCeramicaRoubo2019=0;
    int contandoRuaMontalvaniaCeramicaRoubo2019=0;
    int contandoRuaVarzelandiaCeramicaRoubo2019=0;
    int contandoRuaMiltonCamposCeramicaRoubo2019=0;
    int contandoRuaMiltonSaCeramicaRoubo2019=0;



    int contandoAvSeisEldoradoFurtoTodosAnos=0;
    int contandoRuaAEldoradoFurtoTodosAnos=0;
    int contandoRuaBEldoradoFurtoTodosAnos=0;
    int contandoRuaCEldoradoFurtoTodosAnos=0;
    int contandoRuaDEldoradoFurtoTodosAnos=0;
    int contandoRuaEEldoradoFurtoTodosAnos=0;
    int contandoRuaGEldoradoFurtoTodosAnos=0;
    int contandoRuaJEldoradoFurtoTodosAnos=0;
    int contandoRuaLEldoradoFurtoTodosAnos=0;
    int contandoRuaMEldoradoFurtoTodosAnos=0;

    int contandoAvSeisEldoradoRouboTodosAnos=0;
    int contandoRuaAEldoradoRouboTodosAnos=0;
    int contandoRuaBEldoradoRouboTodosAnos=0;
    int contandoRuaCEldoradoRouboTodosAnos=0;
    int contandoRuaDEldoradoRouboTodosAnos=0;
    int contandoRuaEEldoradoRouboTodosAnos=0;
    int contandoRuaGEldoradoRouboTodosAnos=0;
    int contandoRuaJEldoradoRouboTodosAnos=0;
    int contandoRuaLEldoradoRouboTodosAnos=0;
    int contandoRuaMEldoradoRouboTodosAnos=0;



    int contandoAvSeisEldoradoFurto2019=0;
    int contandoRuaAEldoradoFurto2019=0;
    int contandoRuaBEldoradoFurto2019=0;
    int contandoRuaCEldoradoFurto2019=0;
    int contandoRuaDEldoradoFurto2019=0;
    int contandoRuaEEldoradoFurto2019=0;
    int contandoRuaGEldoradoFurto2019=0;
    int contandoRuaJEldoradoFurto2019=0;
    int contandoRuaLEldoradoFurto2019=0;
    int contandoRuaMEldoradoFurto2019=0;

    int contandoAvSeisEldoradoRoubo2019=0;
    int contandoRuaAEldoradoRoubo2019=0;
    int contandoRuaBEldoradoRoubo2019=0;
    int contandoRuaCEldoradoRoubo2019=0;
    int contandoRuaDEldoradoRoubo2019=0;
    int contandoRuaEEldoradoRoubo2019=0;
    int contandoRuaGEldoradoRoubo2019=0;
    int contandoRuaJEldoradoRoubo2019=0;
    int contandoRuaLEldoradoRoubo2019=0;
    int contandoRuaMEldoradoRoubo2019=0;



    int contandoRuaAntonioValeFilhoFranklimFurtoTodosAnos=0;
    int contandoRuaLFranklimFurtoTodosAnos=0;
    int contandoRuaVinteTresFranklimFurtoTodosAnos=0;
    int contandoRuaVinteQuatroFranklimFurtoTodosAnos=0;
    int contandoRuaR25FranklimdoFurtoTodosAnos=0;
    int contandoRuaVinteOitoFranklimFurtoTodosAnos=0;

    int contandoRuaAntonioValeFilhoFranklimRouboTodosAnos=0;
    int contandoRuaLFranklimRouboTodosAnos=0;
    int contandoRuaVinteTresFranklimRouboTodosAnos=0;
    int contandoRuaVinteQuatroFranklimRouboTodosAnos=0;
    int contandoRuaR25FranklimdoRouboTodosAnos=0;
    int contandoRuaVinteOitoFranklimRouboTodosAnos=0;


    int contandoRuaAntonioValeFilhoFranklimFurto2019=0;
    int contandoRuaLFranklimFurto2019=0;
    int contandoRuaVinteTresFranklimFurto2019=0;
    int contandoRuaVinteQuatroFranklimFurto2019=0;
    int contandoRuaR25FranklimdoFurto2019=0;
    int contandoRuaVinteOitoFranklimFurto2019=0;

    int contandoRuaAntonioValeFilhoFranklimRoubo2019=0;
    int contandoRuaLFranklimRoubo2019=0;
    int contandoRuaVinteTresFranklimRoubo2019=0;
    int contandoRuaVinteQuatroFranklimRoubo2019=0;
    int contandoRuaR25FranklimdoRoubo2019=0;
    int contandoRuaVinteOitoFranklimRoubo2019=0;



    int contandoRuaUmJardimDanielFurtoTodosAnos=0;
    int contandoRuaDoisDanielFurtoTodosAnos=0;
    int contandoRuaTresJardimDanielFurtoTodosAnos=0;
    int contandoRuaQuatroJardimDanielFurtoTodosAnos=0;
    int contandoRuaAlagoasJardimDanielFurtoTodosAnos=0;
    int contandoRuaBelaVistaJardimDanielFurtoTodosAnos=0;
    int contandoRuaConegoRamiroLeiteJardimDanielFurtoTodosAnos=0;
    int contandoRuaInezitaAlvesFerreiraJardimDanielFurtoTodosAnos=0;
    int contandoRuaMinasGeraisJardimDanielFurtoTodosAnos=0;


    int contandoRuaUmJardimDanielRouboTodosAnos=0;
    int contandoRuaDoisDanielRouboTodosAnos=0;
    int contandoRuaTresJardimDanielRouboTodosAnos=0;
    int contandoRuaQuatroJardimDanielRouboTodosAnos=0;
    int contandoRuaAlagoasJardimDanielRouboTodosAnos=0;
    int contandoRuaBelaVistaJardimDanielRouboTodosAnos=0;
    int contandoRuaConegoRamiroLeiteJardimDanielRouboTodosAnos=0;
    int contandoRuaInezitaAlvesFerreiraJardimDanielRouboTodosAnos=0;
    int contandoRuaMinasGeraisJardimDanielRouboTodosAnos=0;

    int contandoRuaUmJardimDanielFurto2019=0;
    int contandoRuaDoisDanielFurto2019=0;
    int contandoRuaTresJardimDanielFurto2019=0;
    int contandoRuaQuatroJardimDanielFurto2019=0;
    int contandoRuaAlagoasJardimDanielFurto2019=0;
    int contandoRuaBelaVistaJardimDanielFurto2019=0;
    int contandoRuaConegoRamiroLeiteJardimDanielFurto2019=0;
    int contandoRuaInezitaAlvesFerreiraJardimDanielFurto2019=0;
    int contandoRuaMinasGeraisJardimDanielFurto2019=0;


    int contandoRuaUmJardimDanielRoubo2019=0;
    int contandoRuaDoisDanielRoubo2019=0;
    int contandoRuaTresJardimDanielRoubo2019=0;
    int contandoRuaQuatroJardimDanielRoubo2019=0;
    int contandoRuaAlagoasJardimDanielRoubo2019=0;
    int contandoRuaBelaVistaJardimDanielRoubo2019=0;
    int contandoRuaConegoRamiroLeiteJardimDanielRoubo2019=0;
    int contandoRuaInezitaAlvesFerreiraJardimDanielRoubo2019=0;
    int contandoRuaMinasGeraisJardimDanielRoubo2019=0;



    int contandoRuaAJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaBJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaCJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaDJardimEstrelalFurtoTodosAnos=0;
    int contandoRuaEJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaGJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaHJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaBaraoSaoRomaoJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaJoaoGasparinoJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaVinteDoisJardimEstrelaFurtoTodosAnos=0;
    int contandoRuaVinteTresJardimEstrelaFurtoTodosAnos=0;

    int contandoRuaAJardimEstrelaRouboTodosAnos=0;
    int contandoRuaBJardimEstrelaRouboTodosAnos=0;
    int contandoRuaCJardimEstrelaRouboTodosAnos=0;
    int contandoRuaDJardimEstrelalRouboTodosAnos=0;
    int contandoRuaEJardimEstrelaRouboTodosAnos=0;
    int contandoRuaGJardimEstrelaRouboTodosAnos=0;
    int contandoRuaHJardimEstrelaRouboTodosAnos=0;
    int contandoRuaBaraoSaoRomaoJardimEstrelaRouboTodosAnos=0;
    int contandoRuaJoaoGasparinoJardimEstrelaRouboTodosAnos=0;
    int contandoRuaVinteDoisJardimEstrelaRouboTodosAnos=0;
    int contandoRuaVinteTresJardimEstrelaRouboTodosAnos=0;




    int contandoRuaAJardimEstrelaFurto2019=0;
    int contandoRuaBJardimEstrelaFurto2019=0;
    int contandoRuaCJardimEstrelaFurto2019=0;
    int contandoRuaDJardimEstrelalFurto2019=0;
    int contandoRuaEJardimEstrelaFurto2019=0;
    int contandoRuaGJardimEstrelaFurto2019=0;
    int contandoRuaHJardimEstrelaFurto2019=0;
    int contandoRuaBaraoSaoRomaoJardimEstrelaFurto2019=0;
    int contandoRuaJoaoGasparinoJardimEstrelaFurto2019=0;
    int contandoRuaVinteDoisJardimEstrelaFurto2019=0;
    int contandoRuaVinteTresJardimEstrelaFurto2019=0;

    int contandoRuaAJardimEstrelaRoubo2019=0;
    int contandoRuaBJardimEstrelaRoubo2019=0;
    int contandoRuaCJardimEstrelaRoubo2019=0;
    int contandoRuaDJardimEstrelalRoubo2019=0;
    int contandoRuaEJardimEstrelaRoubo2019=0;
    int contandoRuaGJardimEstrelaRoubo2019=0;
    int contandoRuaHJardimEstrelaRoubo2019=0;
    int contandoRuaBaraoSaoRomaoJardimEstrelaRoubo2019=0;
    int contandoRuaJoaoGasparinoJardimEstrelaRoubo2019=0;
    int contandoRuaVinteDoisJardimEstrelaRoubo2019=0;
    int contandoRuaVinteTresJardimEstrelaRoubo2019=0;


    int contandoRuaUmJussaraFurtoTodosAnos=0;
    int contandoRuaDoisJussaraFurtoTodosAnos=0;
    int contandoRuaTresJussaraFurtoTodosAnos=0;
    int contandoRuaQuatroJussaralFurtoTodosAnos=0;
    int contandoRuaCincoJussaraFurtoTodosAnos=0;
    int contandoRuaSeisJussaraFurtoTodosAnos=0;
    int contandoRuaSeteJussaraFurtoTodosAnos=0;
    int contandoRuaOitoJussaraFurtoTodosAnos=0;
    int contandoRuaNoveJussaraFurtoTodosAnos=0;
    int contandoRuaDezJussaraFurtoTodosAnos=0;


    int contandoRuaUmJussaraRouboTodosAnos=0;
    int contandoRuaDoisJussaraRouboTodosAnos=0;
    int contandoRuaTresJussaraRouboTodosAnos=0;
    int contandoRuaQuatroJussaralRouboTodosAnos=0;
    int contandoRuaCincoJussaraRouboTodosAnos=0;
    int contandoRuaSeisJussaraRouboTodosAnos=0;
    int contandoRuaSeteJussaraRouboTodosAnos=0;
    int contandoRuaOitoJussaraRouboTodosAnos=0;
    int contandoRuaNoveJussaraRouboTodosAnos=0;
    int contandoRuaDezJussaraRouboTodosAnos=0;

    int contandoRuaUmJussaraFurto2019=0;
    int contandoRuaDoisJussaraFurto2019=0;
    int contandoRuaTresJussaraFurto2019=0;
    int contandoRuaQuatroJussaralFurto2019=0;
    int contandoRuaCincoJussaraFurto2019=0;
    int contandoRuaSeisJussaraFurto2019=0;
    int contandoRuaSeteJussaraFurto2019=0;
    int contandoRuaOitoJussaraFurto2019=0;
    int contandoRuaNoveJussaraFurto2019=0;
    int contandoRuaDezJussaraFurto2019=0;


    int contandoRuaUmJussaraRoubo2019=0;
    int contandoRuaDoisJussaraRoubo2019=0;
    int contandoRuaTresJussaraRoubo2019=0;
    int contandoRuaQuatroJussaralRoubo2019=0;
    int contandoRuaCincoJussaraRoubo2019=0;
    int contandoRuaSeisJussaraRoubo2019=0;
    int contandoRuaSeteJussaraRoubo2019=0;
    int contandoRuaOitoJussaraRoubo2019=0;
    int contandoRuaNoveJussaraRoubo2019=0;
    int contandoRuaDezJussaraRoubo2019=0;


    int contandoRuaSeisVilaLevianopolisFurtoTodosAnos=0;
    int contandoRuaSeteVilaLevianopolisFurtoTodosAnos=0;
    int contandoRuaOitoVilaLevianopolisFurtoTodosAnos=0;
    int contandoRuaNoveVilaLevianopolisFurtoTodosAnos=0;
    int contandoRuaVinteVilaLevianopolisFurtoTodosAnos=0;
    int contandoRuaAnizioGoncalvezMoreiraVilaLevianopolisFurtoTodosAnos =0;
    int contandoRuaHonorCaciquinhoVilaLevianopolisFurtoTodosAnos=0;
    int contandoRuaEmilioMatosVilaLevianopolisFurtoTodosAnos=0;
    int contandoRuaJoseAntonioValeFilhoVilaLevianopolisFurtoTodosAnos=0;
    int contandoRuaInezitaAlvesFerreiraLevianopolisFurtoTodosAnos=0;


    int contandoRuaSeisVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaSeteVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaOitoVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaNoveVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaVinteVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaAnizioGoncalvezMoreiraVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaHonorCaciquinhoVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaEmilioMatosVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaJoseAntonioValeFilhoVilaLevianopolisRouboTodosAnos=0;
    int contandoRuaInezitaAlvesFerreiraLevianopolisRouboTodosAnos=0;





    int contandoRuaSeisVilaLevianopolisFurto2019=0;
    int contandoRuaSeteVilaLevianopolisFurto2019=0;
    int contandoRuaOitoVilaLevianopolisFurto2019=0;
    int contandoRuaNoveVilaLevianopolisFurto2019=0;
    int contandoRuaVinteVilaLevianopolisFurto2019=0;
    int contandoRuaAnizioGoncalvezMoreiraVilaLevianopolisFurto2019 =0;
    int contandoRuaHonorCaciquinhoVilaLevianopolisFurto2019=0;
    int contandoRuaEmilioMatosVilaLevianopolisFurto2019=0;
    int contandoRuaJoseAntonioValeFilhoVilaLevianopolisFurto2019=0;
    int contandoRuaInezitaAlvesFerreiraLevianopolisFurto2019=0;


    int contandoRuaSeisVilaLevianopolisRoubo2019=0;
    int contandoRuaSeteVilaLevianopolisRoubo2019=0;
    int contandoRuaOitoVilaLevianopolisRoubo2019=0;
    int contandoRuaNoveVilaLevianopolisRoubo2019=0;
    int contandoRuaVinteVilaLevianopolisRoubo2019=0;
    int contandoRuaAnizioGoncalvezMoreiraVilaLevianopolisRoubo2019=0;
    int contandoRuaHonorCaciquinhoVilaLevianopolisRoubo2019=0;
    int contandoRuaEmilioMatosVilaLevianopolisRoubo2019=0;
    int contandoRuaJoseAntonioValeFilhoVilaLevianopolisRoubo2019=0;
    int contandoRuaInezitaAlvesFerreiraLevianopolisRoubo2019=0;



    int contandoRuaAQuintasMangueirasFurtoTodosAnos=0;
    int contandoRuaBQuintasMangueirasFurtoTodosAnos=0;
    int contandoRuaDQuintasMangueirasFurtoTodosAnos=0;
    int contandoRuaEQuintasMangueirasFurtoTodosAnos=0;
    int contandoRuaBondeQuintasMangueirasFurtoTodosAnos=0;
    int contandoRuaTutaBastosQuintasMangueirasFurtoTodosAnos=0;


    int contandoRuaAQuintasMangueirasRouboTodosAnos=0;
    int contandoRuaBQuintasMangueirasRouboTodosAnos=0;
    int contandoRuaDQuintasMangueirasRouboTodosAnos=0;
    int contandoRuaEQuintasMangueirasRouboTodosAnos=0;
    int contandoRuaBondeQuintasMangueirasRouboTodosAnos=0;
    int contandoRuaTutaBastosQuintasMangueirasRouboTodosAnos=0;


    int contandoRuaAQuintasMangueirasFurto2019=0;
    int contandoRuaBQuintasMangueirasFurto2019=0;
    int contandoRuaDQuintasMangueirasFurto2019=0;
    int contandoRuaEQuintasMangueirasFurto2019=0;
    int contandoRuaBondeQuintasMangueirasFurto2019=0;
    int contandoRuaTutaBastosQuintasMangueirasFurto2019=0;


    int contandoRuaAQuintasMangueirasRoubo2019=0;
    int contandoRuaBQuintasMangueirasRoubo2019=0;
    int contandoRuaDQuintasMangueirasRoubo2019=0;
    int contandoRuaEQuintasMangueirasRoubo2019=0;
    int contandoRuaBondeQuintasMangueirasRoubo2019=0;
    int contandoRuaTutaBastosQuintasMangueirasRoubo2019=0;



    int contandoRuaMuriciRiachoCruzFurtoTodosAnos=0;
    int contandoRuaCafeMineiroRiachoCruzFurtoTodosAnos=0;
    int contandoRuaAntonioSilvaRiachoCruzFurtoTodosAnos=0;
    int contandoRuaManoelJSouzaRiachoCruzFurtoTodosAnos=0;
    int contandoRuaOliveiraPortoRiachoCruzFurtoTodosAnos=0;
    int contandoRuaTertulianoRPortoRiachoCruzFurtoTodosAnos=0;
    int contandoRuaJFMeloRiachoCruzFurtoTodosAnos=0;



    int contandoRuaMuriciRiachoCruzRouboTodosAnos=0;
    int contandoRuaCafeMineiroRiachoCruzRouboTodosAnos=0;
    int contandoRuaAntonioSilvaRiachoCruzRouboTodosAnos=0;
    int contandoRuaManoelJSouzaRiachoCruzRouboTodosAnos=0;
    int contandoRuaOliveiraPortoRiachoCruzRouboTodosAnos=0;
    int contandoRuaTertulianoRPortoRiachoCruzRouboTodosAnos=0;
    int contandoRuaJFMeloRiachoCruzRouboTodosAnos=0;



    int contandoRuaMuriciRiachoCruzFurto2019=0;
    int contandoRuaCafeMineiroRiachoCruzFurto2019=0;
    int contandoRuaAntonioSilvaRiachoCruzFurto2019=0;
    int contandoRuaManoelJSouzaRiachoCruzFurto2019=0;
    int contandoRuaOliveiraPortoRiachoCruzFurto2019=0;
    int contandoRuaTertulianoRPortoRiachoCruzFurto2019=0;
    int contandoRuaJFMeloRiachoCruzFurto2019=0;


    int contandoRuaMuriciRiachoCruzRoubo2019=0;
    int contandoRuaCafeMineiroRiachoCruzRoubo2019=0;
    int contandoRuaAntonioSilvaRiachoCruzRoubo2019=0;
    int contandoRuaManoelJSouzaRiachoCruzRoubo2019=0;
    int contandoRuaOliveiraPortoRiachoCruzRoubo2019=0;
    int contandoRuaTertulianoRPortoRiachoCruzRoubo2019=0;
    int contandoRuaJFMeloRiachoCruzRoubo2019=0;


    int contandoRuaCelCassianoVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaLeaoTrezeVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaUmVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaDoisVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaBenicioJoseFerreiraVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaCurtumeVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaTrintaMarcoVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaJoseAugustoVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaMalFlorianoPeixotoVilaSaoJoaoFurtoTodosAnos=0;
    int contandoRuaWVilaSaoJoaoFurtoTodosAnos=0;


    int contandoRuaCelCassianoVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaLeaoTrezeVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaUmVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaDoisVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaBenicioJoseFerreiraVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaCurtumeVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaTrintaMarcoVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaJoseAugustoVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaMalFlorianoPeixotoVilaSaoJoaoRouboTodosAnos=0;
    int contandoRuaWVilaSaoJoaoRouboTodosAnos=0;

    int contandoRuaCelCassianoVilaSaoJoaoFurto2019=0;
    int contandoRuaLeaoTrezeVilaSaoJoaoFurto2019=0;
    int contandoRuaUmVilaSaoJoaoFurto2019=0;
    int contandoRuaDoisVilaSaoJoaoFurto2019=0;
    int contandoRuaBenicioJoseFerreiraVilaSaoJoaoFurto2019=0;
    int contandoRuaCurtumeVilaSaoJoaoFurto2019=0;
    int contandoRuaTrintaMarcoVilaSaoJoaoFurto2019=0;
    int contandoRuaJoseAugustoVilaSaoJoaoFurto2019=0;
    int contandoRuaMalFlorianoPeixotoVilaSaoJoaoFurto2019=0;
    int contandoRuaWVilaSaoJoaoFurto2019=0;


    int contandoRuaCelCassianoVilaSaoJoaoRoubo2019=0;
    int contandoRuaLeaoTrezeVilaSaoJoaoRoubo2019=0;
    int contandoRuaUmVilaSaoJoaoRoubo2019=0;
    int contandoRuaDoisVilaSaoJoaoRoubo2019=0;
    int contandoRuaBenicioJoseFerreiraVilaSaoJoaoRoubo2019=0;
    int contandoRuaCurtumeVilaSaoJoaoRoubo2019=0;
    int contandoRuaTrintaMarcoVilaSaoJoaoRoubo2019=0;
    int contandoRuaJoseAugustoVilaSaoJoaoRoubo2019=0;
    int contandoRuaMalFlorianoPeixotoVilaSaoJoaoRoubo2019=0;
    int contandoRuaWVilaSaoJoaoRoubo2019=0;



    int contandoRuaUmSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaDoisSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaTresSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaSeisSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaOitoSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaNoveSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaDezSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaDozeSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaSantaInesSaoJoaquimFurtoTodosAnos=0;
    int contandoRuaSantaMariaSaoJoaquimFurtoTodosAnos=0;



    int contandoRuaUmSaoJoaquimRouboTodosAnos=0;
    int contandoRuaDoisSaoJoaquimRouboTodosAnos=0;
    int contandoRuaTresSaoJoaquimRouboTodosAnos=0;
    int contandoRuaSeisSaoJoaquimRouboTodosAnos=0;
    int contandoRuaOitoSaoJoaquimRouboTodosAnos=0;
    int contandoRuaNoveSaoJoaquimRouboTodosAnos=0;
    int contandoRuaDezSaoJoaquimRouboTodosAnos=0;
    int contandoRuaDozeSaoJoaquimRouboTodosAnos=0;
    int contandoRuaSantaInesSaoJoaquimRouboTodosAnos=0;
    int contandoRuaSantaMariaSaoJoaquimRouboTodosAnos=0;



    int contandoRuaUmSaoJoaquimFurto2019=0;
    int contandoRuaDoisSaoJoaquimFurto2019=0;
    int contandoRuaTresSaoJoaquimFurto2019=0;
    int contandoRuaSeisSaoJoaquimFurto2019=0;
    int contandoRuaOitoSaoJoaquimFurto2019=0;
    int contandoRuaNoveSaoJoaquimFurto2019=0;
    int contandoRuaDezSaoJoaquimFurto2019=0;
    int contandoRuaDozeSaoJoaquimFurto2019=0;
    int contandoRuaSantaInesSaoJoaquimFurto2019=0;
    int contandoRuaSantaMariaSaoJoaquimFurto2019=0;



    int contandoRuaUmSaoJoaquimRoubo2019=0;
    int contandoRuaDoisSaoJoaquimRoubo2019=0;
    int contandoRuaTresSaoJoaquimRoubo2019=0;
    int contandoRuaSeisSaoJoaquimRoubo2019=0;
    int contandoRuaOitoSaoJoaquimRoubo2019=0;
    int contandoRuaNoveSaoJoaquimRoubo2019=0;
    int contandoRuaDezSaoJoaquimRoubo2019=0;
    int contandoRuaDozeSaoJoaquimRoubo2019=0;
    int contandoRuaSantaInesSaoJoaquimRoubo2019=0;
    int contandoRuaSantaMariaSaoJoaquimRoubo2019=0;




    int contandoRuaSaoFranciscoSaoMiguelFurtoTodosAnos=0;
    int contandoRuaUmSaoMiguelFurtoTodosAnos=0;
    int contandoRuaDoisSaoMiguelFurtoTodosAnos=0;
    int contandoRuaTresSaoMiguelFurtoTodosAnos=0;
    int contandoRuaASaoMiguelFurtoTodosAnos=0;
    int contandoRuaBSaoMiguelFurtoTodosAnos=0;
    int contandoRuaDSaoMiguelFurtoTodosAnos=0;
    int contandoRuaPiraporaSaoMiguelFurtoTodosAnos=0;
    int contandoRuaMontalvaniaSaoMiguelFurtoTodosAnos=0;
    int contandoTvGalileiaSaoMiguelFurtoTodosAnos=0;



    int contandoRuaSaoFranciscoSaoMiguelRouboTodosAnos=0;
    int contandoRuaUmSaoMiguelRouboTodosAnos=0;
    int contandoRuaDoisSaoMiguelRouboTodosAnos=0;
    int contandoRuaTresSaoMiguelRouboTodosAnos=0;
    int contandoRuaASaoMiguelRouboTodosAnos=0;
    int contandoRuaBSaoMiguelRouboTodosAnos=0;
    int contandoRuaDSaoMiguelRouboTodosAnos=0;
    int contandoRuaPiraporaSaoMiguelRouboTodosAnos=0;
    int contandoRuaMontalvaniaSaoMiguelRouboTodosAnos=0;
    int contandoTvGalileiaSaoMiguelRouboTodosAnos=0;


    int contandoRuaSaoFranciscoSaoMiguelFurto2019=0;
    int contandoRuaUmSaoMiguelFurto2019=0;
    int contandoRuaDoisSaoMiguelFurto2019=0;
    int contandoRuaTresSaoMiguelFurto2019=0;
    int contandoRuaASaoMiguelFurto2019=0;
    int contandoRuaBSaoMiguelFurto2019=0;
    int contandoRuaDSaoMiguelFurto2019=0;
    int contandoRuaPiraporaSaoMiguelFurto2019=0;
    int contandoRuaMontalvaniaSaoMiguelFurto2019=0;
    int contandoTvGalileiaSaoMiguelFurto2019=0;

    int contandoRuaSaoFranciscoSaoMiguelRoubo2019=0;
    int contandoRuaUmSaoMiguelRoubo2019=0;
    int contandoRuaDoisSaoMiguelRoubo2019=0;
    int contandoRuaTresSaoMiguelRoubo2019=0;
    int contandoRuaASaoMiguelRoubo2019=0;
    int contandoRuaBSaoMiguelRoubo2019=0;
    int contandoRuaDSaoMiguelRoubo2019=0;
    int contandoRuaPiraporaSaoMiguelRoubo2019=0;
    int contandoRuaMontalvaniaSaoMiguelRoubo2019=0;
    int contandoTvGalileiaSaoMiguelRoubo2019=0;


    int contandoRuaASaoVicenteFurtoTodosAnos=0;
    int contandoRuaBSaoVicenteFurtoTodosAnos=0;
    int contandoRuaDSaoVicenteFurtoTodosAnos=0;
    int contandoRuaESaoVicenteFurtoTodosAnos=0;
    int contandoRuaTutaBastosSaoVicenteFurtoTodosAnos=0;
    int contandoRuaLeonelNogueiraNetoSaoVicenteFurtoTodosAnos=0;
    int contandoRuaMariaCarneiroCarvalhoSaoVicenteFurtoTodosAnos=0;
    int contandoRuaOlibrioLimaSaoVicenteFurtoTodosAnos=0;
    int contandoRuaTerencioTorresSaoVicenteFurtoTodosAnos=0;
    int contandoRuaSebastiaoFerreiraLimaSaoVicenteFurtoTodosAnos=0;


    int contandoRuaASaoVicenteRouboTodosAnos=0;
    int contandoRuaBSaoVicenteRouboTodosAnos=0;
    int contandoRuaDSaoVicenteRouboTodosAnos=0;
    int contandoRuaESaoVicenteRouboTodosAnos=0;
    int contandoRuaTutaBastosSaoVicenteRouboTodosAnos=0;
    int contandoRuaLeonelNogueiraNetoSaoVicenteRouboTodosAnos=0;
    int contandoRuaMariaCarneiroCarvalhoSaoVicenteRouboTodosAnos=0;
    int contandoRuaOlibrioLimaSaoVicenteRouboTodosAnos=0;
    int contandoRuaTerencioTorresSaoVicenteRouboTodosAnos=0;
    int contandoRuaSebastiaoFerreiraLimaSaoVicenteRouboTodosAnos=0;



    int contandoRuaASaoVicenteFurto2019=0;
    int contandoRuaBSaoVicenteFurto2019=0;
    int contandoRuaDSaoVicenteFurto2019=0;
    int contandoRuaESaoVicenteFurto2019=0;
    int contandoRuaTutaBastosSaoVicenteFurto2019=0;
    int contandoRuaLeonelNogueiraNetoSaoVicenteFurto2019=0;
    int contandoRuaMariaCarneiroCarvalhoSaoVicenteFurto2019=0;
    int contandoRuaOlibrioLimaSaoVicenteFurto2019=0;
    int contandoRuaTerencioTorresSaoVicenteFurto2019=0;
    int contandoRuaSebastiaoFerreiraLimaSaoVicenteFurto2019=0;


    int contandoRuaASaoVicenteRoubo2019=0;
    int contandoRuaBSaoVicenteRoubo2019=0;
    int contandoRuaDSaoVicenteRoubo2019=0;
    int contandoRuaESaoVicenteRoubo2019=0;
    int contandoRuaTutaBastosSaoVicenteRoubo2019=0;
    int contandoRuaLeonelNogueiraNetoSaoVicenteRoubo2019=0;
    int contandoRuaMariaCarneiroCarvalhoSaoVicenteRoubo2019=0;
    int contandoRuaOlibrioLimaSaoVicenteRoubo2019=0;
    int contandoRuaTerencioTorresSaoVicenteRoubo2019=0;
    int contandoRuaSebastiaoFerreiraLimaSaoVicenteRoubo2019=0;



    int contandoRuaCasteloBrancoTejucoFurtoTodosAnos=0;
    int contandoRuaJucelinoKubitscheckTejucoFurtoTodosAnos=0;
    int contandoRuaTancredoNevesTejucoFurtoTodosAnos=0;
    int contandoRuaTejucoTejucoFurtoTodosAnos=0;
    int contandoRuaTropicalTejucoVicenteFurtoTodosAnos=0;
    int contandoRuaSaoJoaoTejucoFurtoTodosAnos=0;
    int contandoRuaSaoJoseTejucoFurtoTodosAnos =0;
    int contandoRuaManoelAlexandrinodeCarvalhoTejucoFurtoTodosAnos=0;


    int contandoRuaCasteloBrancoTejucoRouboTodosAnos=0;
    int contandoRuaJucelinoKubitscheckTejucoRouboTodosAnos=0;
    int contandoRuaTancredoNevesTejucoRouboTodosAnos=0;
    int contandoRuaTejucoTejucoRouboTodosAnos=0;
    int contandoRuaTropicalTejucoVicenteRouboTodosAnos=0;
    int contandoRuaSaoJoaoTejucoRouboTodosAnos=0;
    int contandoRuaSaoJoseTejucoRouboTodosAnos =0;
    int contandoRuaManoelAlexandrinodeCarvalhoTejucoRouboTodosAnos=0;




    int contandoRuaCasteloBrancoTejucoFurto2019=0;
    int contandoRuaJucelinoKubitscheckTejucoFurto2019=0;
    int contandoRuaTancredoNevesTejucoFurto2019=0;
    int contandoRuaTejucoTejucoFurto2019=0;
    int contandoRuaTropicalTejucoVicenteFurto2019=0;
    int contandoRuaSaoJoaoTejucoFurto2019=0;
    int contandoRuaSaoJoseTejucoFurto2019 =0;
    int contandoRuaManoelAlexandrinodeCarvalhoTejucoFurto2019=0;


    int contandoRuaCasteloBrancoTejucoRoubo2019=0;
    int contandoRuaJucelinoKubitscheckTejucoRoubo2019=0;
    int contandoRuaTancredoNevesTejucoRoubo2019=0;
    int contandoRuaTejucoTejucoRoubo2019=0;
    int contandoRuaTropicalTejucoVicenteRoubo2019=0;
    int contandoRuaSaoJoaoTejucoRoubo2019=0;
    int contandoRuaSaoJoseTejucoRoubo2019 =0;
    int contandoRuaManoelAlexandrinodeCarvalhoTejucoRoubo2019=0;



    int contandoRuaAVilaFatimaFurtoTodosAnos=0;
    int contandoRuaBFatimaFurtoTodosAnos=0;
    int contandoRuaCVilaFatimaFurtoTodosAnos=0;
    int contandoRuaDVilaFatimaFurtoTodosAnos=0;
    int contandoRuaEVilaFatimaVicenteFurtoTodosAnos=0;
    int contandoRuaFVilaFatimaFurtoTodosAnos=0;
    int contandoRuaGilmarPereiraRochaVilaFatimaFurtoTodosAnos=0;
    int contandoRuaJoaoPimentaCarvalhoVilaFatimaFurtoTodosAnos=0;
    int contandoRuaJoaquimFernandesoVilaFatimaFurtoTodosAnos=0;
    int contandoRuaJoseAugustoVilaFatimaFurtoTodosAnos=0;


    int contandoRuaAVilaFatimaRouboTodosAnos=0;
    int contandoRuaBFatimaRouboTodosAnos=0;
    int contandoRuaCVilaFatimaRouboTodosAnos=0;
    int contandoRuaDVilaFatimaRouboTodosAnos=0;
    int contandoRuaEVilaFatimaVicenteRouboTodosAnos=0;
    int contandoRuaFVilaFatimaRouboTodosAnos=0;
    int contandoRuaGilmarPereiraRochaVilaFatimaRouboTodosAnos=0;
    int contandoRuaJoaoPimentaCarvalhoVilaFatimaRouboTodosAnos=0;
    int contandoRuaJoaquimFernandesoVilaFatimaRouboTodosAnos=0;
    int contandoRuaJoseAugustoVilaFatimaRouboTodosAnos=0;


    int contandoRuaAVilaFatimaFurto2019=0;
    int contandoRuaBFatimaFurto2019=0;
    int contandoRuaCVilaFatimaFurto2019=0;
    int contandoRuaDVilaFatimaFurto2019=0;
    int contandoRuaEVilaFatimaVicenteFurto2019=0;
    int contandoRuaFVilaFatimaFurto2019=0;
    int contandoRuaGilmarPereiraRochaVilaFatimaFurto2019=0;
    int contandoRuaJoaoPimentaCarvalhoVilaFatimaFurto2019=0;
    int contandoRuaJoaquimFernandesoVilaFatimaFurto2019=0;
    int contandoRuaJoseAugustoVilaFatimaFurto2019=0;


    int contandoRuaAVilaFatimaRoubo2019=0;
    int contandoRuaBFatimaRoubo2019=0;
    int contandoRuaCVilaFatimaRoubo2019=0;
    int contandoRuaDVilaFatimaRoubo2019=0;
    int contandoRuaEVilaFatimaVicenteRoubo2019=0;
    int contandoRuaFVilaFatimaRoubo2019=0;
    int contandoRuaGilmarPereiraRochaVilaFatimaRoubo2019=0;
    int contandoRuaJoaoPimentaCarvalhoVilaFatimaRoubo2019=0;
    int contandoRuaJoaquimFernandesoVilaFatimaRoubo2019=0;
    int contandoRuaJoseAugustoVilaFatimaRoubo2019=0;



    int contandoRuaJoaquimBarbosaGobiraPandeirosFurtoTodosAnos=0;
    int contandoRuaMariaMoreiraPandeirosFurtoTodosAnos=0;

    int contandoRuaJoaquimBarbosaGobiraPandeirosRouboTodosAnos=0;
    int contandoRuaMariaMoreiraPandeirosRouboTodosAnos=0;


    int contandoRuaJoaquimBarbosaGobiraPandeirosFurto2019=0;
    int contandoRuaMariaMoreiraPandeirosFurto2019=0;

    int contandoRuaJoaquimBarbosaGobiraPandeirosRoubo2019=0;
    int contandoRuaMariaMoreiraPandeirosRoubo2019 =0;



    int contandoRuaSaoFranciscoVilaPaulaFurtoTodosAnos=0;
    int contandoTvJoseNunesVilaPaulaFurtoTodosAnos =0;
    int contandoTvJulioMouraVilaPaulaFurtoTodosAnos =0;
    int contandoRuaLeonelNogueiraNetoVilaPaulaFurtoTodosAnos=0;
    int contandoRuaAnizioJoseRochaVilaPaulaFurtoTodosAnos=0;
    int contandoRuaJulioMouraVilaPaulaFurtoTodosAnos=0;
    int contandoRuaJoseNunesVilaPaulaFurtoTodosAnos=0;
    int contandoRuaSebastiaoFerreiraLimaVilaPaulaFurtoTodosAnos=0;
    int contandoRuaSrgMozarVilaPaulaFurtoTodosAnos=0;



    int contandoRuaSaoFranciscoVilaPaulaRouboTodosAnos=0;
    int contandoTvJoseNunesVilaPaulaRouboTodosAnos =0;
    int contandoTvJulioMouraVilaPaulaRouboTodosAnos =0;
    int contandoRuaLeonelNogueiraNetoVilaPaulaRouboTodosAnos=0;
    int contandoRuaAnizioJoseRochaVilaPaulaRouboTodosAnos=0;
    int contandoRuaJulioMouraVilaPaulaRouboTodosAnos=0;
    int contandoRuaJoseNunesVilaPaulaRouboTodosAnos=0;
    int contandoRuaSebastiaoFerreiraLimaVilaPaulaRouboTodosAnos=0;
    int contandoRuaSrgMozarVilaPaulaRouboTodosAnos=0;



    int contandoRuaSaoFranciscoVilaPaulaFurto2019=0;
    int contandoTvJoseNunesVilaPaulaFurto2019 =0;
    int contandoTvJulioMouraVilaPaulaFurto2019 =0;
    int contandoRuaLeonelNogueiraNetoVilaPaulaFurto2019=0;
    int contandoRuaAnizioJoseRochaVilaPaulaFurto2019=0;
    int contandoRuaJulioMouraVilaPaulaFurto2019=0;
    int contandoRuaJoseNunesVilaPaulaFurto2019=0;
    int contandoRuaSebastiaoFerreiraLimaVilaPaulaFurto2019=0;
    int contandoRuaSrgMozarVilaPaulaFurto2019=0;



    int contandoRuaSaoFranciscoVilaPaulaRoubo2019=0;
    int contandoTvJoseNunesVilaPaulaRoubo2019 =0;
    int contandoTvJulioMouraVilaPaulaRoubo2019 =0;
    int contandoRuaLeonelNogueiraNetoVilaPaulaRoubo2019=0;
    int contandoRuaAnizioJoseRochaVilaPaulaRoubo2019=0;
    int contandoRuaJulioMouraVilaPaulaRoubo2019=0;
    int contandoRuaJoseNunesVilaPaulaRoubo2019=0;
    int contandoRuaSebastiaoFerreiraLimaVilaPaulaRoubo2019=0;
    int contandoRuaSrgMozarVilaPaulaRoubo2019=0;


    int contandoAvMalDeodoroFonsecaVilaVerdeFurtoTodosAnos=0;
    int contandoRAVilaVerdeFurtoTodosAnos=0;
    int contandoRuaAterroVilaVerdeFurtoTodosAnos=0;
    int contandoRuaBVilaVerdeFurtoTodosAnos=0;
    int contandoRuaDVilaVerdeFurtoTodosAnos=0;
    int contandoRuaOVilaVerdeFurtoTodosAnos=0;
    int contandoRuaRVilaVerdeFurtoTodosAnos=0;
    int contandoRuaTVilaVerdeFurtoTodosAnos=0;
    int contandoRuaUVilaVerdeFurtoTodosAnos=0;
    int contandoRuaVVilaVerdeFurtoTodosAnos=0;


    int contandoAvMalDeodoroFonsecaVilaVerdeRouboTodosAnos=0;
    int contandoRAVilaVerdeRouboTodosAnos=0;
    int contandoRuaAterroVilaVerdeRouboTodosAnos=0;
    int contandoRuaBVilaVerdeRouboTodosAnos=0;
    int contandoRuaDVilaVerdeRouboTodosAnos=0;
    int contandoRuaOVilaVerdeRouboTodosAnos=0;
    int contandoRuaRVilaVerdeRouboTodosAnos=0;
    int contandoRuaTVilaVerdeRouboTodosAnos=0;
    int contandoRuaUVilaVerdeRouboTodosAnos=0;
    int contandoRuaVVilaVerdeRouboTodosAnos=0;




    int contandoAvMalDeodoroFonsecaVilaVerdeFurto2019=0;
    int contandoRAVilaVerdeFurto2019=0;
    int contandoRuaAterroVilaVerdeFurto2019=0;
    int contandoRuaBVilaVerdeFurto2019=0;
    int contandoRuaDVilaVerdeFurto2019=0;
    int contandoRuaOVilaVerdeFurto2019=0;
    int contandoRuaRVilaVerdeFurto2019=0;
    int contandoRuaTVilaVerdeFurto2019=0;
    int contandoRuaUVilaVerdeFurto2019=0;
    int contandoRuaVVilaVerdeFurto2019=0;


    int contandoAvMalDeodoroFonsecaVilaVerdeRoubo2019=0;
    int contandoRAVilaVerdeRoubo2019=0;
    int contandoRuaAterroVilaVerdeRoubo2019=0;
    int contandoRuaBVilaVerdeRoubo2019=0;
    int contandoRuaDVilaVerdeRoubo2019=0;
    int contandoRuaOVilaVerdeRoubo2019=0;
    int contandoRuaRVilaVerdeRoubo2019=0;
    int contandoRuaTVilaVerdeRoubo2019=0;
    int contandoRuaUVilaVerdeRoubo2019=0;
    int contandoRuaVVilaVerdeRoubo2019=0;


    int contandoRuaAVilaVianaFurtoTodosAnos=0;
    int contandoRuaArthurLopesPimentaVilaVianaFurtoTodosAnos=0;
    int contandoRuaBVilaVianaFurtoTodosAnos=0;
    int contandoRuaDVilaVianaFurtoTodosAnos=0;
    int contandoRuaEVilaVianaFurtoTodosAnos=0;
    int contandoRuaFVilaVianaFurtoTodosAnos=0;
    int contandoRuaGVilaVianaFurtoTodosAnos=0;
    int contandoRuaRVilaVianaFurtoTodosAnos=0;
    int contandoRuaXVilaVianaFurtoTodosAnos=0;

    int contandoRuaAVilaVianaRouboTodosAnos=0;
    int contandoRuaArthurLopesPimentaVilaVianaRouboTodosAnos=0;
    int contandoRuaBVilaVianaRouboTodosAnos=0;
    int contandoRuaDVilaVianaRouboTodosAnos=0;
    int contandoRuaEVilaVianaRouboTodosAnos=0;
    int contandoRuaFVilaVianaRouboTodosAnos=0;
    int contandoRuaGVilaVianaRouboTodosAnos=0;
    int contandoRuaRVilaVianaRouboTodosAnos=0;
    int contandoRuaXVilaVianaRouboTodosAnos=0;


    int contandoRuaAVilaVianaFurto2019=0;
    int contandoRuaArthurLopesPimentaVilaVianaFurto2019=0;
    int contandoRuaBVilaVianaFurto2019=0;
    int contandoRuaDVilaVianaFurto2019=0;
    int contandoRuaEVilaVianaFurto2019=0;
    int contandoRuaFVilaVianaFurto2019=0;
    int contandoRuaGVilaVianaFurto2019=0;
    int contandoRuaRVilaVianaFurto2019=0;
    int contandoRuaXVilaVianaFurto2019=0;

    int contandoRuaAVilaVianaRoubo2019=0;
    int contandoRuaArthurLopesPimentaVilaVianaRoubo2019=0;
    int contandoRuaBVilaVianaRoubo2019=0;
    int contandoRuaDVilaVianaRoubo2019=0;
    int contandoRuaEVilaVianaRoubo2019=0;
    int contandoRuaFVilaVianaRoubo2019=0;
    int contandoRuaGVilaVianaRoubo2019=0;
    int contandoRuaRVilaVianaRoubo2019=0;
    int contandoRuaXVilaVianaRoubo2019=0;




    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;


    public static List<Bike> listBikes = new ArrayList<Bike>();
    public static ArrayAdapter<Bike> arrayAdapterBike;
    CharSequence	convertSelectionToString;



    protected ImageView spinnerImagem;
    private String camposSpinner[] = new String[] {"Todos","2018","2019"};
    private Spinner spinner;





    String  autoComplete;

    int spinnerCorrente = 0;
    int todosAnos=0;
    int ano2018=1;
    int ano2019=2;


    Bike b;
    String procuraBairro;
    String procuraAno;



    private BarChart barChart;



    String[] nomes  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};

    private int[]    roubos = new int   []{10,15,14,20};
    private int []   cores   = new int   []{Color.RED};
    int []   cor   = new int   []{Color.RED};  // ALTERAR a cor da legenda aq
    String[] legendaGrafico  = new String[]{"Furto/Roubo"}; // ALTERAR a nome da legenda aq


    private String[] legenda   = new String[]{"Furto/Roubo"};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_grafico_rua_barra_geral, container, false);


        inicializarFirebase();



        barChart =  (BarChart) rootView.findViewById(R.id.graficoRuaBarraGeral);
        String[] countries =  getResources().getStringArray(R.array.countries);


        //carrega os spinner
        final AutoCompleteTextView autoCompletegraficoRuaBarra = rootView.findViewById(R.id.autocompletegraficoruabarrageral);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GraficoRuaBarraGeralFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,countries);

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

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Aeroporto") && spinnerCorrente == todosAnos){



                                aeroportoTodosAnos();


                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Alameda") && spinnerCorrente ==todosAnos) {


                                alameidaTodosAnos();


                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Alto Cemitério") && spinnerCorrente ==todosAnos) {


                                altoCemiterioTodosAnos();


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


                                boaEsperancaTodosAnos();


                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente ==todosAnos) {


                                boaVistaTodosAnos();


                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia") && spinnerCorrente ==todosAnos) {


                                brasilandiaTodosAnos();


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


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica") && spinnerCorrente ==todosAnos) {


                                ceramicaTodosAnos();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente ==todosAnos) {


                                eldoradoTodosAnos();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente ==todosAnos) {


                                fabiaoTodosAnos();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Franklim") && spinnerCorrente ==todosAnos) {


                                franklimTodosAnos();

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

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela") && spinnerCorrente ==todosAnos) {


                                jardimEstelaTodosAnos();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente ==todosAnos) {


                                jatobaTodosAnos();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente ==todosAnos) {


                                jussaraTodosAnos();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis") && spinnerCorrente ==todosAnos) {


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

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras") && spinnerCorrente ==todosAnos) {


                                quintaMangabeirasTodosAnos();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente ==todosAnos) {


                                riachoDaCruzTodosAnos();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao") && spinnerCorrente ==todosAnos) {


                                saoJoaoTodosAnos();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente ==todosAnos) {


                                saoJoaquimTodosAnos();

                            }



                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel") && spinnerCorrente ==todosAnos) {


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


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima") && spinnerCorrente ==todosAnos) {


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


                                altoCemiterio2018();


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


                                boaEsperanca2018();


                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente ==ano2018) {


                                boaVista2018();


                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia") && spinnerCorrente ==ano2018) {


                                brasilandia2018();


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


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica") && spinnerCorrente ==ano2018) {


                                ceramica2018();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente ==ano2018) {


                                eldorado2018();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente ==ano2018) {


                                fabiao2018();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Franklim") && spinnerCorrente ==ano2018) {


                                franklim2018();

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

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela") && spinnerCorrente ==ano2018) {


                                jardimEstela2018();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente ==ano2018) {


                                jatoba2018();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente ==ano2018) {


                                jussara2018();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis") && spinnerCorrente ==ano2018) {


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

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras") && spinnerCorrente ==ano2018) {


                                quintaMangabeiras2018();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente ==ano2018) {


                                riachoDaCruz2018();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao") && spinnerCorrente ==ano2018) {


                                saoJoao2018();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente ==ano2018) {


                                saoJoaquim2018();

                            }



                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel") && spinnerCorrente ==ano2018) {


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


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima") && spinnerCorrente ==ano2018) {


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


                                altoCemiterio2019();


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


                                boaEsperanca2019();


                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista") && spinnerCorrente ==ano2019) {


                                boaVista2019();


                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia") && spinnerCorrente ==ano2019) {


                                brasilandia2019();


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


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica") && spinnerCorrente ==ano2019) {


                                ceramica2019();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado") && spinnerCorrente ==ano2019) {


                                eldorado2019();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Fabião") && spinnerCorrente ==ano2019) {


                                fabiao2019();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Franklim") && spinnerCorrente ==ano2019) {


                                franklim2019();

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

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela") && spinnerCorrente ==ano2019) {


                                jardimEstela2019();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jatobá") && spinnerCorrente ==ano2019) {


                                jatoba2019();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Jussara") && spinnerCorrente ==ano2019) {


                                jussara2019();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis") && spinnerCorrente ==ano2019) {


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

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras") && spinnerCorrente ==ano2019) {


                                quintaMangabeiras2019();

                            }


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz") && spinnerCorrente ==ano2019) {


                                riachoDaCruz2019();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao") && spinnerCorrente ==ano2019) {


                                saoJoao2019();

                            }

                            if(autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim") && spinnerCorrente ==ano2019) {


                                saoJoaquim2019();

                            }



                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel") && spinnerCorrente ==ano2019) {


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


                            if(autoCompletegraficoRuaBarra.getText().toString().equals("Vila Fatima") && spinnerCorrente ==ano2019) {


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
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GraficoRuaBarraGeralFragment.super.getContext(), android.R.layout.simple_spinner_dropdown_item,camposSpinner);
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


                                    altoCemiterioTodosAnos();

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


                                    boaEsperancaTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")){


                                    boaVistaTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia")){


                                    brasilandiaTodosAnos();

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

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica")){


                                    ceramicaTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")){


                                    eldoradoTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")){


                                    fabiaoTodosAnos();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim")){


                                    franklimTodosAnos();

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



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela")){


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


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis")){


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



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras")){

                                    quintaMangabeirasTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")){

                                    riachoDaCruzTodosAnos();

                                }



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao")){

                                    saoJoaoTodosAnos();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")){

                                    saoJoaquimTodosAnos();

                                }



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel")){

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


                                    altoCemiterio2018();

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


                                    boaEsperanca2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")){


                                    boaVista2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia")){


                                    brasilandia2018();

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

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica")){


                                    ceramica2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")){


                                    eldorado2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")){


                                    fabiao2018();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim")){


                                    franklim2018();

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



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela")){


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


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis")){


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



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras")){

                                    quintaMangabeiras2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")){

                                    riachoDaCruz2018();

                                }



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao")){

                                    saoJoao2018();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")){

                                    saoJoaquim2018();

                                }



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel")){

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


                                    altoCemiterio2019();

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


                                    boaEsperanca2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Boa Vista")){


                                    boaVista2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Brasilandia")){


                                    brasilandia2019();

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

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Ceramica")){


                                    ceramica2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Eldorado")){


                                    eldorado2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Fabião")){


                                    fabiao2019();

                                }

                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Franklim")){


                                    franklim2019();

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



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Jardim Estrela")){


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


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Levianopolis")){


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



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Quintas das Mangueiras")){

                                    quintaMangabeiras2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Riacho da Cruz")){

                                    riachoDaCruz2019();

                                }



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Joao")){

                                    saoJoao2019();

                                }


                                if (autoCompletegraficoRuaBarra.getText().toString().equals("São Joaquim")){

                                    saoJoaquim2019();

                                }



                                if (autoCompletegraficoRuaBarra.getText().toString().equals("Vila Sao Miguel")){

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
                            transaction.replace(R.id.conteinerFragmentos,new GraficoRuaBarraFragment()).commit();
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


        barChart = (BarChart) getSameChart(barChart,"",Color.RED,Color.WHITE,2000);
        barChart.setDrawGridBackground(true);
        barChart.setActivated(true);


        barChart.zoom(2,0,2,0);


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

       // barChart.invalidate();


    }











    /// opção todos os bairros no spinner
    // dados para geração de cada grafico


    public void aeroportoTodosAnos(){





        String[] bairros  = new String[]{"Av. Aeroporto","Av. Seis","R. Um ","R. Dois","R. Três","R. Quatro","R. Vinte e Três","R. XI"};
        int[]    rob = new int   []{contandoAvAeroportoAeroportoRouboTodosAnos,
                contandoAvSeisAeroportoRouboTodosAnos,
                contandoRuaRUmAeroportoRouboTodosAnos,
                contandoRuaRDoisAeroportoRouboTodosAnos,
                contandoRuaRTresAeroportoRouboTodosAnos,
                contandoRuaRQuatroAeroportoRouboTodosAnos,
                contandoRuavinteTresAeroportoRouboTodosAnos,
                contandoRuaRXIAeroportoRouboTodosAnos};




        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }

    public void alameidaTodosAnos(){

        //xx

        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};





        nomes = alameda;
        roubos = robAlameda;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();



    } //Não consta no google

    public void altoCemiterioTodosAnos(){

        //xx

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério", "Rua 12"};
        int[] robAltoCemitério = new int[]{10, 10, 10, 10};



        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }//Não consta no google

    public void altoPocoesTodosAnos(){

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","Av. Um","Av. Dois", "Av. Três", "Av. Quatro", "Av. Cinco"};
        int[]   valores = new int   []{contandoRuaUmAltoPocoesRouboTodosAnos,
                contandoRuaDoisAltoPocoesRouboTodosAnos,
                contandoRuaTresAltoPocoesRouboTodosAnos,
                contandoRuaQuatroAltoPocoesRouboTodosAnos,
                contandoRuaCincoAltoPocoesRouboTodosAnos,
                contandoAvUmAltoPocoesRouboTodosAnos,
                contandoAvDoisRuaAltoPocoesRouboTodosAnos,
                contandoAvTresAltoPocoesRouboTodosAnos,
                contandoAvQuatroAltoPocoesRouboTodosAnos,
                contandoAvCincoAltoPocoesRouboTodosAnos};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



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




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



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




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }

    private void belaVistaTodosAnos() {

        ///XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void boaEsperancaTodosAnos() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Treze de Maio","R. Luís Tupiná","R. Hermílio Tupiná ","R. São Geraldo"};
        int[]    valores = new int   []{contandoRuaUmBoaEsperancaRouboTodosAnos,
                contandoRuaDoisBoaEsperancaRouboTodosAnos,
                contandoRuaTresBoaEsperancaRouboTodosAnos,
                contandoRuaQuatroBoaEsperancaRouboTodosAnos,
                contandoRuaCincoBoaEsperancaRouboTodosAnos,
                contandoRuaSeisBoaEsperancaRouboTodosAnos,
                contandoRuaTrezeMaioBoaEsperancaRouboTodosAnos,
                contandoRuaLuisTupinaBoaEsperancaRouboTodosAnos,
                contandoRuaHermilioTupinaBoaEsperancaRouboTodosAnos,
                contandoRuaSaoGeraldoBoaEsperancaRouboTodosAnos};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void boaVistaTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G","R. H","R. I","R. J","R. L"};
        int[]    valores = new int   []{ contandoRuaABoaVistaRouboTodosAnos,
                contandoRuaBBoaVistaRouboTodosAnos,
                contandoRuaCBoaVistaRouboTodosAnos,
                contandoRuaDBoaVistaRouboTodosAnos,
                contandoRuaEBoaVistaRouboTodosAnos,
                contandoRuaFBoaVistaRouboTodosAnos,
                contandoRuaGBoaVistaRouboTodosAnos,
                contandoRuaHBoaVistaRouboTodosAnos,
                contandoRuaIBoaVistaRouboTodosAnos,
                contandoRuaJBoaVistaRouboTodosAnos,
                contandoRuaLBoaVistaRouboTodosAnos};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void brasilandiaTodosAnos() {


        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinto","R. Seis","R. Sete","R. Minas Gerais","R. Pirapora","R. São Luiz"};
        int[]    valores = new int   []{contandoRuaUmVilaBrasilandiaRouboTodosAnos,
                contandoRuaDoisVilaBrasilandiaRouboTodosAnos,
                contandoRuaTresVilaBrasilandiaRouboTodosAnos,
                contandoRuaQuatroVilaBrasilandiaRouboTodosAnos,
                contandoRuaCincoVilaBrasilandiaRouboTodosAnos,
                contandoRuaSeisVilaBrasilandiaRouboTodosAnos,
                contandoRuaSeteVilaBrasilandiaRouboTodosAnos,
                contandoRuaMinasGeraisVilaBrasilandiaRouboTodosAnos,
                contandoRuaPiraporaVilaBrasilandiaRouboTodosAnos,
                contandoRuaSaoLuizVilaBrasilandiaRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }

    private void brejoDoAmparoTodosAnos() {

        String[] ruas  = new String[]{"Alameda do Riacho","R. Bela Vista","R. da Serra","R. Flôres","R. Padre Josino","R. Padre Ramiro","R. Tabatinga","R. Taboca","R. Trinta e Sete","R. Trinta e Oito"};
        int[]    valores = new int   []{contandoAlamedaRiachoBrejoAmparoRouboTodosAnos,
                contandoRuaBelaVistaBrejoAmparoRouboTodosAnos,
                contandoRuaSerraBrejoAmparoRouboTodosAnos,
                contandoRuaFloresBrejoAmparoRouboTodosAnos,
                contandoRuaPadreJosinoBrejoAmparoRouboTodosAnos,
                contandoRuaPadreRamiroAmparoRouboTodosAnos,
                contandoRuaTabatingaBrejoAmparoRouboTodosAnos,
                contandoRuaTabocaBrejoAmparoRouboTodosAnos,
                contandoRuaTrintaSeteBrejoAmparoRouboTodosAnos,
                contandoRuaTrintaOitoBrejoAmparoRouboTodosAnos};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void caicTodosAnos() {


        //XX  caic fica no Dom joão Batista

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;


        criarGraficos();


    } //Não consta no google

    private void centroTodosAnos() {

        String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};
        int[]    valores = new int    []{contandoArturBernardesCentroRouboTodosAnos,
                contandoBaraoSaoRomaoCentroRouboTodosAnos,
                contandoBaraoRioBrancoCentroRouboTodosAnos,
                contandoConegoRamiroLeiteCentroRouboTodosAnos,
                contandoCelCassianoCentroRouboTodosAnos,
                contandoCelSerraoCentroRouboTodosAnos,
                contandoDomDanielCentroRouboTodosAnos,
                contandoGetulioVargasCentroRouboTodosAnos,
                contandoPadreHenriqueCentroRouboTodosAnos,
                contandoPracaTiradentesCentroRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void ceramicaTodosAnos() {

        String[] ruas  = new String[]{"R. Aterro","R. Brasília de Minas","R. Itacarambi","R. Itapiraçaba","R. Manga","R. Montes Claros","R. Montalvânia","R. Vazelândia","R. Milton Campos", "R. Milton Sá"};
        int[]    valores = new int   []{ contandoRuaAterroCeramicaRouboTodosAnos,
                contandoRuaBrasiliaMinasCeramicaRouboTodosAnos,
                contandoRuaItacarambiCeramicaRouboTodosAnos,
                contandoRuaItapiracabaCeramicaRouboTodosAnos,
                contandoRuaMangaCeramicaRouboTodosAnos,
                contandoRuaMontesClarosCeramicaRouboTodosAnos,
                contandoRuaMontalvaniaCeramicaRouboTodosAnos,
                contandoRuaVarzelandiaCeramicaRouboTodosAnos,
                contandoRuaMiltonCamposCeramicaRouboTodosAnos,
                contandoRuaMiltonSaCeramicaRouboTodosAnos,};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void eldoradoTodosAnos() {

        String[] ruas  = new String[]{"Av. Seis","R. A","R. B","R. C","R. D","R. E","R. G","R. J","R. L","R. M"};
        int[]    valores = new int   []{contandoAvSeisEldoradoRouboTodosAnos,
                contandoRuaAEldoradoRouboTodosAnos,
                contandoRuaBEldoradoRouboTodosAnos,
                contandoRuaCEldoradoRouboTodosAnos,
                contandoRuaDEldoradoRouboTodosAnos,
                contandoRuaEEldoradoRouboTodosAnos,
                contandoRuaGEldoradoRouboTodosAnos,
                contandoRuaJEldoradoRouboTodosAnos,
                contandoRuaLEldoradoRouboTodosAnos,
                contandoRuaMEldoradoRouboTodosAnos};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;


        criarGraficos();


    }

    private void fabiaoTodosAnos() {


        //XXX zona ruaral

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void franklimTodosAnos() {


        String[] ruas  = new String[]{"R. J. Antônio do Vale Filho","R. L","R. Vinte e Três","R. Vinte e Quatro","R. Vinte e Cinco","R. Vinte e Oito"};
        int[]    valores = new int   []{contandoRuaAntonioValeFilhoFranklimRouboTodosAnos,
                contandoRuaLFranklimRouboTodosAnos,
                contandoRuaVinteTresFranklimRouboTodosAnos,
                contandoRuaVinteQuatroFranklimRouboTodosAnos,
                contandoRuaR25FranklimdoRouboTodosAnos,
                contandoRuaVinteOitoFranklimRouboTodosAnos,};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jadeteTodosAnos() {

        String[] ruas  = new String[]{"Av. Cônego R. Leite","Alameda V","R. Inezita A. Ferreira","R. Isla H. Azevedo","R. S. Antônio","R. Viana","R. vinte e Dois","R. Ver João Alkimim","Tv. Viana"};

        int[]    valores = new int   []{contandoRuaRamiroLeiteJadeteRouboTodosAnos,
                contandoRuaAlameidaVianaJadeteRouboTodosAnos,
                contandoRuaInezitaJadeteRouboTodosAnos,
                contandoRuaIslaHJadeteRouboTodosAnos,
                contandoRuaSantoAntJadeteRouboTodosAnos
                ,contandoRuaVianaJadeteRouboTodosAnos,
                contandoRuavinteDoisJadeteRouboTodosAnos,
                contandoRuaJoaoAlkimimJadeteRouboTodosAnos,
                contandoTvVianaJadeteRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void januariaTodosAnos() {


        //xxx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void jardimDanielTodosAnos() {


        String[] ruas  = new String[]{"R. Um","Rua Dois","R. Três","R. Quatro","R. Alagoas","R. Bela Vista","Av. Cônego Ramiro Leite","R. Inezita A Ferreira","R. Minas Gerais"};
        int[]    valores = new int   []{contandoRuaUmJardimDanielRouboTodosAnos,
                contandoRuaDoisDanielRouboTodosAnos,
                contandoRuaTresJardimDanielRouboTodosAnos,
                contandoRuaQuatroJardimDanielRouboTodosAnos,
                contandoRuaAlagoasJardimDanielRouboTodosAnos,
                contandoRuaBelaVistaJardimDanielRouboTodosAnos,
                contandoRuaConegoRamiroLeiteJardimDanielRouboTodosAnos,
                contandoRuaInezitaAlvesFerreiraJardimDanielRouboTodosAnos,
                contandoRuaMinasGeraisJardimDanielRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jardimEstelaTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. G","R. H","R. Barão de São Romão","R. João Gasparino","R. Vinte e Dois","R. Vinte e Três"};
        int[]    valores = new int   []{contandoRuaAJardimEstrelaRouboTodosAnos,
                contandoRuaBJardimEstrelaRouboTodosAnos,
                contandoRuaCJardimEstrelaRouboTodosAnos,
                contandoRuaDJardimEstrelalRouboTodosAnos,
                contandoRuaEJardimEstrelaRouboTodosAnos,
                contandoRuaGJardimEstrelaRouboTodosAnos,
                contandoRuaHJardimEstrelaRouboTodosAnos,
                contandoRuaBaraoSaoRomaoJardimEstrelaRouboTodosAnos,
                contandoRuaJoaoGasparinoJardimEstrelaRouboTodosAnos,
                contandoRuaVinteDoisJardimEstrelaRouboTodosAnos,
                contandoRuaVinteTresJardimEstrelaRouboTodosAnos,};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jatobaTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }    //Não consta no google

    private void joventinaMesquitaTodosAnos() {


        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void jussaraTodosAnos() {



        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Sete","R. Oito","R. Nove","R. Dez"};
        int[]    valores = new int   []{contandoRuaUmJussaraRouboTodosAnos,
                contandoRuaDoisJussaraRouboTodosAnos,
                contandoRuaTresJussaraRouboTodosAnos,
                contandoRuaQuatroJussaralRouboTodosAnos,
                contandoRuaCincoJussaraRouboTodosAnos,
                contandoRuaSeisJussaraRouboTodosAnos,
                contandoRuaSeteJussaraRouboTodosAnos,
                contandoRuaOitoJussaraRouboTodosAnos,
                contandoRuaNoveJussaraRouboTodosAnos,
                contandoRuaDezJussaraRouboTodosAnos,};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void levianopolisTodosAnos() {

        String[] ruas  = new String[]{"R. Seis", "R. Sete", "R. Oito","R. Nove","R. Vinte", "R. Anízio G Moreira ","R. H Caciquinho","P. Emilio de Matos","R. J.A do vale Filho","R. Inezita A. Ferreira"};
        int[]    valores = new int   []{contandoRuaSeisVilaLevianopolisRouboTodosAnos,
                contandoRuaSeteVilaLevianopolisRouboTodosAnos,
                contandoRuaOitoVilaLevianopolisRouboTodosAnos,
                contandoRuaNoveVilaLevianopolisRouboTodosAnos,
                contandoRuaVinteVilaLevianopolisRouboTodosAnos,
                contandoRuaAnizioGoncalvezMoreiraVilaLevianopolisRouboTodosAnos,
                contandoRuaHonorCaciquinhoVilaLevianopolisRouboTodosAnos,
                contandoRuaEmilioMatosVilaLevianopolisRouboTodosAnos,
                contandoRuaJoseAntonioValeFilhoVilaLevianopolisRouboTodosAnos,
                contandoRuaInezitaAlvesFerreiraLevianopolisRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void mangueirasTodosAnos() {


        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }   //Não consta no google

    private void margareteTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }   //Não consta no google

    private void moradeirasTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }   //Não consta no google

    private void normaConsueloTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }   //Não consta no google

    private void novoMilenioTodosAnos() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    } //Não consta no google

    private void quintaMangabeirasTodosAnos() {


        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. do Bonde","R. T.Bastos"};
        int[]    valores = new int   []{contandoRuaAQuintasMangueirasRouboTodosAnos,
                contandoRuaBQuintasMangueirasRouboTodosAnos,
                contandoRuaDQuintasMangueirasRouboTodosAnos,
                contandoRuaEQuintasMangueirasRouboTodosAnos,
                contandoRuaBondeQuintasMangueirasRouboTodosAnos,
                contandoRuaTutaBastosQuintasMangueirasRouboTodosAnos};






        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void riachoDaCruzTodosAnos() {

        String[] ruas  = new String[]{"R. Murici","R. Café Mineiro","R. Antônio C.da Silva","R. Manoel J.de Souza","R. Oliveira Pôrto","R. Tertuliano R.Pôrto","Tv. J.F.Melo"};
        int[]    valores = new int   []{


                contandoRuaMuriciRiachoCruzRouboTodosAnos,
                contandoRuaCafeMineiroRiachoCruzRouboTodosAnos,
                contandoRuaAntonioSilvaRiachoCruzRouboTodosAnos,
                contandoRuaManoelJSouzaRiachoCruzRouboTodosAnos,
                contandoRuaOliveiraPortoRiachoCruzRouboTodosAnos,
                contandoRuaTertulianoRPortoRiachoCruzRouboTodosAnos,
                contandoRuaJFMeloRiachoCruzRouboTodosAnos};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoJoaoTodosAnos() {

        String[] ruas  = new String[]{"Av.Cel.Cassiano","Tv.Leão Treze","R. Um","R. Dois","R. B.J Ferreira","R. do Curtume","R. Trinta de Maio","R. J.Augusto","R. Mal.Floriano Peixoto"};
        int[]    valores = new int   []{
                contandoRuaCelCassianoVilaSaoJoaoRouboTodosAnos,
                contandoRuaLeaoTrezeVilaSaoJoaoRouboTodosAnos,
                contandoRuaUmVilaSaoJoaoRouboTodosAnos,
                contandoRuaDoisVilaSaoJoaoRouboTodosAnos,
                contandoRuaBenicioJoseFerreiraVilaSaoJoaoRouboTodosAnos,
                contandoRuaCurtumeVilaSaoJoaoRouboTodosAnos,
                contandoRuaTrintaMarcoVilaSaoJoaoRouboTodosAnos,
                contandoRuaJoseAugustoVilaSaoJoaoRouboTodosAnos,
                contandoRuaMalFlorianoPeixotoVilaSaoJoaoRouboTodosAnos,
                contandoRuaWVilaSaoJoaoRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoJoaquimTodosAnos() {



        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Seis","R. Oito","R. Nove","R. Dez","R. Doze","R. S.Inês","R. S.Maria"};
        int[]    valores = new int   []{contandoRuaUmSaoJoaquimRouboTodosAnos,
                contandoRuaDoisSaoJoaquimRouboTodosAnos,
                contandoRuaTresSaoJoaquimRouboTodosAnos,
                contandoRuaSeisSaoJoaquimRouboTodosAnos,
                contandoRuaOitoSaoJoaquimRouboTodosAnos,
                contandoRuaNoveSaoJoaquimRouboTodosAnos,
                contandoRuaDezSaoJoaquimRouboTodosAnos,
                contandoRuaDozeSaoJoaquimRouboTodosAnos,
                contandoRuaSantaInesSaoJoaquimRouboTodosAnos,
                contandoRuaSantaMariaSaoJoaquimRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoMiguelTodosAnos() {


        String[] ruas  = new String[]{"Av.São Francisco","R. Um", "R. Dois","R. Três", "R. A", "R. B","R. D","R. Piraporã","R. Montavânia","Tv.Galiléia"};
        int[]    valores = new int   []{contandoRuaSaoFranciscoSaoMiguelRouboTodosAnos,
                contandoRuaUmSaoMiguelRouboTodosAnos,
                contandoRuaDoisSaoMiguelRouboTodosAnos,
                contandoRuaTresSaoMiguelRouboTodosAnos,
                contandoRuaASaoMiguelRouboTodosAnos,
                contandoRuaBSaoMiguelRouboTodosAnos,
                contandoRuaDSaoMiguelRouboTodosAnos,
                contandoRuaPiraporaSaoMiguelRouboTodosAnos,
                contandoRuaMontalvaniaSaoMiguelRouboTodosAnos,
                contandoTvGalileiaSaoMiguelRouboTodosAnos};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoVicenteTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. E.T.Bastos","R. L.N.Neto","R. Maria C.Carvalho","R. Olibrio Lima","R. T.Torres","R. Sebastião F.Lima"};
        int[]    valores = new int   []{
                contandoRuaASaoVicenteRouboTodosAnos,
                contandoRuaBSaoVicenteRouboTodosAnos,
                contandoRuaDSaoVicenteRouboTodosAnos,
                contandoRuaESaoVicenteRouboTodosAnos,
                contandoRuaTutaBastosSaoVicenteRouboTodosAnos,
                contandoRuaLeonelNogueiraNetoSaoVicenteRouboTodosAnos,
                contandoRuaMariaCarneiroCarvalhoSaoVicenteRouboTodosAnos,
                contandoRuaOlibrioLimaSaoVicenteRouboTodosAnos,
                contandoRuaTerencioTorresSaoVicenteRouboTodosAnos,
                contandoRuaSebastiaoFerreiraLimaSaoVicenteRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



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



        criarGraficos();


    }  //Não consta no google

    private void tejucoTodosAnos() {



        String[] ruas  = new String[]{"R. Castelo Branco","R. J.kubitschek","R. Tancredo Neves","R. Tejuco","R. Tropical","R. São João","R. São José","R. M.A.Carvalho"};
        int[]    valores = new int   []{


                contandoRuaCasteloBrancoTejucoRouboTodosAnos,
                contandoRuaJucelinoKubitscheckTejucoRouboTodosAnos,
                contandoRuaTancredoNevesTejucoRouboTodosAnos,
                contandoRuaTejucoTejucoRouboTodosAnos,
                contandoRuaTropicalTejucoVicenteRouboTodosAnos,
                contandoRuaSaoJoaoTejucoRouboTodosAnos,
                contandoRuaSaoJoseTejucoRouboTodosAnos ,
                contandoRuaManoelAlexandrinodeCarvalhoTejucoRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



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



        criarGraficos();


    } //Não consta no google

    private void vilaFatimaTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G.P.Rocha","R. J.P.Carvalho","R. Joaquim Fernandes","R. J.Augusto"};
        int[]    valores = new int   []{ contandoRuaAVilaFatimaRouboTodosAnos,
                contandoRuaBFatimaRouboTodosAnos,
                contandoRuaCVilaFatimaRouboTodosAnos,
                contandoRuaDVilaFatimaRouboTodosAnos,
                contandoRuaEVilaFatimaVicenteRouboTodosAnos,
                contandoRuaFVilaFatimaRouboTodosAnos,
                contandoRuaGilmarPereiraRochaVilaFatimaRouboTodosAnos,
                contandoRuaJoaoPimentaCarvalhoVilaFatimaRouboTodosAnos,
                contandoRuaJoaquimFernandesoVilaFatimaRouboTodosAnos,
                contandoRuaJoseAugustoVilaFatimaRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void pandeirosTodosAnos() {

        String[] ruas  = new String[]{"R. J.B.Gobira","R. M.Moreira"};
        int[]    valores = new int   []{
                contandoRuaJoaquimBarbosaGobiraPandeirosRouboTodosAnos,
                contandoRuaMariaMoreiraPandeirosRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaPaulaTodosAnos() {

        String[] ruas  = new String[]{"Av.São Fransisco","Tv.J.Nunes","Tv.J.Moura","R. L.N.Neto","R. A.J.Rocha","R. J.Moura","R. J.Nunes","R. S.F.Lima","R. Srg.Monzart"};
        int[]    valores = new int   []{contandoRuaSaoFranciscoVilaPaulaRouboTodosAnos,
                contandoTvJoseNunesVilaPaulaRouboTodosAnos ,
                contandoTvJulioMouraVilaPaulaRouboTodosAnos ,
                contandoRuaLeonelNogueiraNetoVilaPaulaRouboTodosAnos,
                contandoRuaAnizioJoseRochaVilaPaulaRouboTodosAnos,
                contandoRuaJulioMouraVilaPaulaRouboTodosAnos,
                contandoRuaJoseNunesVilaPaulaRouboTodosAnos,
                contandoRuaSebastiaoFerreiraLimaVilaPaulaRouboTodosAnos,
                contandoRuaSrgMozarVilaPaulaRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaVerdeTodosAnos() {

        String[] ruas  = new String[]{"Av.Mal.Deodoro da Fonseca","R. A","Rua Aterro","R. B","R. D","R. O","R. R","Rua T","R. U","R. V"};
        int[]    valores = new int   []{contandoAvMalDeodoroFonsecaVilaVerdeRouboTodosAnos,
                contandoRAVilaVerdeRouboTodosAnos,
                contandoRuaAterroVilaVerdeRouboTodosAnos,
                contandoRuaBVilaVerdeRouboTodosAnos,
                contandoRuaDVilaVerdeRouboTodosAnos,
                contandoRuaOVilaVerdeRouboTodosAnos,
                contandoRuaRVilaVerdeRouboTodosAnos,
                contandoRuaTVilaVerdeRouboTodosAnos,
                contandoRuaUVilaVerdeRouboTodosAnos,
                contandoRuaVVilaVerdeRouboTodosAnos};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaVianaTodosAnos() {

        String[] ruas  = new String[]{"R. A","R. Arthur L.Pimenta","R. B","R. D","R. E","R. F","R. G","R. R","R. X"};
        int[]    valores = new int   []{ contandoRuaAVilaVianaRouboTodosAnos,
                contandoRuaArthurLopesPimentaVilaVianaRouboTodosAnos,
                contandoRuaBVilaVianaRouboTodosAnos,
                contandoRuaDVilaVianaRouboTodosAnos,
                contandoRuaEVilaVianaRouboTodosAnos,
                contandoRuaFVilaVianaRouboTodosAnos,
                contandoRuaGVilaVianaRouboTodosAnos,
                contandoRuaRVilaVianaRouboTodosAnos,
                contandoRuaXVilaVianaRouboTodosAnos};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void zonaRuralTodosAnos() {

        String[] ruas  = new String[]{"Zona Rural"};
        int[]    valores = new int   []{10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  /// não fazer








    ///  /// opção 2018 no spinnner
    // dados para geração de cada grafico


/// alimentar manualmente


    public void aeroporto2018(){


        String[] bairros  = new String[]{"Av. Aeroporto","Av. Seis","R. Um ","R. Dois","R. Três","R. Quatro","R. Vinte e Três","R. XI"};
        int[]    rob = new int   []{12,12,10,12};




        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }

    public void alameida2018(){
        //XX

        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda2018", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};




        nomes = alameda;
        roubos = robAlameda;
        cores   =  cor;
        legenda  = legendaGrafico;


        criarGraficos();



    } //Não consta no google

    public void altoCemiterio2018(){

        //XX

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério2018", "Rua 12"};
        int[] robAltoCemitério = new int[]{10, 10, 10, 10};




        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    } //Não consta no google

    public void altoPocoes2018(){

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","Av. Um","Av. Dois", "Av. Três", "Av. Quatro", "Av. Cinco"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();



    }

    public void alvorada2018(){

        String[] ruas  = new String[]{"R. do Aterro","R. Um ","R. A","R. E","R. J","R. K","R. L","R. M","R. N","R. O"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }

    public void bandeirantes2018(){


        String[] ruas  = new String[]{"R. Um","R. Dois","R. Dez","R. Brasília de Minas","R. 15 de Novembro","R. Mirabela","Tv. Mirabela","Tv. Júlio Magalhães"};
        int[]    valores = new int   []{10,10,10,10};

      ;



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }

    private void belaVista2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void boaEsperanca2018() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Treze de Maio","R. Luís Tupiná","R. Hermílio Tupiná ","R. São Geraldo"};
        int[]    valores = new int   []{10,10,10,10};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void boaVista2018() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G","R. H","R. I","R. J","R. L"};
        int[]    valores = new int   []{10,10,10,10};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void brasilandia2018() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinto",". Seis","R. Sete","R. Minas Gerais","R. Pirapora","R. São Luiz"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void brejoDoAmparo2018() {

        String[] ruas  = new String[]{"Alameda do Riacho","R. Bela Vista","R. da Serra","R. Flôres","R. Padre Josino","R. Padre Ramiro","R. Tabatinga","R. Taboca","R. Trinta e Sete","R. Trinta e Oito"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void caic2018() {

        //XX  caic fica no Dom joão Batista

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void centro2018() {

        String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void ceramica2018() {

        String[] ruas  = new String[]{"R. Aterro","R. Brasília de Minas","R. Itacarambi","R. Itapiraçaba","R. Manga","R. Montes Claros","R. Montalvânia","R. Vazelândia","R. Milton Campos", "R. Milton Sá"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void eldorado2018() {

        String[] ruas  = new String[]{"Av. Seis","R. A","R. B","R. C","R. D","R. E","R. G","R. J","R. L","R. M"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void fabiao2018() {


        //XXX zona ruaral

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void franklim2018() {

        String[] ruas  = new String[]{"R. J. Antônio do Vale Filho","R. L","Rua Vinte e Três","R. Vinte e Quatro","R. Vinte e Cinco","R. Vinte e Oito"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jadete2018() {

        String[] ruas  = new String[]{"Av. Cônego R. Leite","Alameda V","R. Inezita A. Ferreira","R. Isla H. Azevedo","R. S. Antônio","R. Viana","R. vinte e Dois","R. Ver João Alkimim","Tv. Viana"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void januaria2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void jardimDaniel2018() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Alagoas","R. Bela Vista","Av. Cônego Ramiro Leite","R. Inezita A Ferreira","R. Minas Gerais"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jardimEstela2018() {





        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. G","R. H","R. Barão de São Romão","R. João Gasparino","R. Vinte e Dois","R. Vinte e Três"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jatoba2018() {


        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void joventinaMesquita2018() {

        ///XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void jussara2018() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Sete","R. Oito","R. Nove","R. Dez"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void levianopolis2018() {

        String[] ruas  = new String[]{"R. Seis", "R. Sete", "R. Oito","R. Nove","R. Vinte", "R. Anízio G Moreira ","R. H Caciquinho","P. Emilio de Matos","R. J.A do vale Filho","R. Inezita A. Ferreira"};

        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void mangueiras2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};

       ;



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void margarete2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void moradeiras2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void normaConsuelo2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    } //Não consta no google

    private void novoMilenio2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void quintaMangabeiras2018() {



        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. do Bonde","R. T.Bastos"};

        int[]    valores = new int   []{10,10,10,10};
        ;




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void riachoDaCruz2018() {

        String[] ruas  = new String[]{"R. Murici","R. Café Mineiro","R. Antônio C.da Silva","R. Manoel J.de Souza","R. Oliveira Pôrto","R. Tertuliano R.Pôrto","Tv. J.F.Melo"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoJoao2018() {

        String[] ruas  = new String[]{"Av. Cel.Cassiano","Tv. Leão Treze","R. Um","R. Dois","R. B.J Ferreira","R. do Curtume","R. Trinta de Maio","R. J.Augusto","R. Mal.Floriano Peixoto"};
        int[]    valores = new int   []{10,10,10,10};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoJoaquim2018() {



        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Seis","R. Oito","R. Nove","R. Dez","R. Doze","R. S.Inês","R. S.Maria"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoMiguel2018() {

        String[] ruas  = new String[]{"Av. São Francisco","R. Um", "R. Dois","R. Três", "R. A", "R. B","R. D","R. Piraporã","R. Montavânia","Tv. Galiléia"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;


        criarGraficos();


    }

    private void saoVicente2018() {


        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. E.T.Bastos","R. L.N.Neto","R. Maria C.Carvalho","R. Olibrio Lima","R. T.Torres","R. Sebastião F.Lima"};
        int[]    valores = new int   []{10,10,10,10};

       ;



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void sagradaFamilia2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sagrada Família2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    } //Não consta no google

    private void santaCruz2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Cruz2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void santaIsabel2018() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Isabel2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void sede2018() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sede2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }   //Não consta no google

    private void tejuco2018() {

        String[] ruas  = new String[]{"R. Castelo Branco","R. J.kubitschek","R. Tancredo Neves","R. Tejuco","R. Tropical","R. São João","R. São José","R. M.A.Carvalho"};

        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void terceiroMilenio2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Terceiro Milênio2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  //Não consta no google

    private void tropical2018() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tropical2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    } //Não consta no google

    private void varzeaDosPocoes2018() {

        ///xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Várzea dos Pocões2018","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    } //Não consta no google

    private void vilaFatima2018() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G.P.Rocha","R. J.P.Carvalho","R. Joaquim Fernandes","R. J.Augusto"};

        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void pandeiros2018() {

        String[] ruas  = new String[]{"R. J.B.Gobira","R. M.Moreira"};

        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaPaula2018() {

        String[] ruas  = new String[]{"Av. São Fransisco","Tv. J.Nunes","Tv. J.Moura","R. L.N.Neto","R. A.J.Rocha","R .J.Moura","R. J.Nunes","R. S.F.Lima","R. Srg.Monzart"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaVerde2018() {

        String[] ruas  = new String[]{"Av. Mal.Deodoro da Fonseca","R. A","R. Aterro","R. B","R. D","R. O","R. R","R. T","R. U","R. V"};

        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaViana2018() {

        String[] ruas  = new String[]{"R. A","R. Arthur L.Pimenta","R. B","R. D","R. E","R. F","R. G","R. R","R. X"};

        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void zonaRural2018() {

        String[] ruas  = new String[]{"Zona Rural"};

        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  /// não fazer



    ///

    ///2019

    ///  /// opção 2019 no spinnner bAirro
    // dados para geração de cada grafico




    public void aeroporto2019(){


        String[] bairros  = new String[]{"Av. Aeroporto","Av. Seis","R. Um ","R. Dois","R. Três","R. Quatro","R. Vinte e Três","R. XI"};
        int[]    rob = new int   []{contandoAvAeroportoAeroportoRoubo2019,
                contandoAvSeisAeroportoRoubo2019,
                contandoRuaRUmAeroportoRoubo2019,
                contandoRuaRDoisAeroportoRoubo2019,
                contandoRuaRTresAeroportoRoubo2019,
                contandoRuaRQuatroAeroportoRoubo2019,
                contandoRuavinteTresAeroportoRoubo2019,
                contandoRuaRXIAeroportoRoubo2019};




        nomes   = bairros;
        roubos =  rob;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }

    public void alameida2019(){
        //xxx
        String[] alameda = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alameda2019", "Rua 12"};
        int[] robAlameda = new int[]{10, 10, 10, 10};



        nomes = alameda;
        roubos = robAlameda;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();



    } // não consta no google

    public void altoCemiterio2019(){


        //xxx

        String[] altoCemitério = new String[]{"spinnerCorrente 7", "spinnerCorrente 10", "Alto Cemitério2019", "Rua 12"};int[] robAltoCemitério = new int[]{10, 10, 10, 10};




        nomes = altoCemitério;
        roubos = robAltoCemitério;
        cores   =  cor;
        legenda  = legendaGrafico;


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






        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



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



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



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





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();

    }

    private void belaVista2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","belaVista2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void boaEsperanca2019() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Treze de Maio","R. Luís Tupiná","R. Hermílio Tupiná ","R. São Geraldo"};

        int[]    valores = new int   []{contandoRuaUmBoaEsperancaRoubo2019,
                contandoRuaDoisBoaEsperancaRoubo2019,
                contandoRuaTresBoaEsperancaRoubo2019,
                contandoRuaQuatroBoaEsperancaRoubo2019,
                contandoRuaCincoBoaEsperancaRoubo2019,
                contandoRuaSeisBoaEsperancaRoubo2019,
                contandoRuaTrezeMaioBoaEsperancaRoubo2019,
                contandoRuaLuisTupinaBoaEsperancaRoubo2019,
                contandoRuaHermilioTupinaBoaEsperancaRoubo2019,
                contandoRuaSaoGeraldoBoaEsperancaRoubo2019};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void boaVista2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G","R. H","R. I","R. J","R. L"};

        int[]    valores = new int   []{ contandoRuaABoaVistaRoubo2019,
                contandoRuaBBoaVistaRoubo2019,
                contandoRuaCBoaVistaRoubo2019,
                contandoRuaDBoaVistaRoubo2019,
                contandoRuaEBoaVistaRoubo2019,
                contandoRuaFBoaVistaRoubo2019,
                contandoRuaGBoaVistaRoubo2019,
                contandoRuaHBoaVistaRoubo2019,
                contandoRuaIBoaVistaRoubo2019,
                contandoRuaJBoaVistaRoubo2019,
                contandoRuaLBoaVistaRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void brasilandia2019() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinto","R. Seis","R. Sete","R. Minas Gerais","R. Pirapora","R. São Luiz"};

        int[]    valores = new int   []{contandoRuaUmVilaBrasilandiaRoubo2019,
                contandoRuaDoisVilaBrasilandiaRoubo2019,
                contandoRuaTresVilaBrasilandiaRoubo2019,
                contandoRuaQuatroVilaBrasilandiaRoubo2019,
                contandoRuaCincoVilaBrasilandiaRoubo2019,
                contandoRuaSeisVilaBrasilandiaRoubo2019,
                contandoRuaSeteVilaBrasilandiaRoubo2019,
                contandoRuaMinasGeraisVilaBrasilandiaRoubo2019,
                contandoRuaPiraporaVilaBrasilandiaRoubo2019,
                contandoRuaSaoLuizVilaBrasilandiaRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void brejoDoAmparo2019() {

        String[] ruas  = new String[]{"Alameda do Riacho","R. Bela Vista","R. da Serra","R. Flôres","R. Padre Josino","R. Padre Ramiro","R. Tabatinga","R. Taboca","R. Trinta e Sete","R. Trinta e Oito"};

        int[]    valores = new int   []{contandoAlamedaRiachoBrejoAmparoRoubo2019,
                contandoRuaBelaVistaBrejoAmparoRoubo2019,
                contandoRuaSerraBrejoAmparoRoubo2019,
                contandoRuaFloresBrejoAmparoRoubo2019,
                contandoRuaPadreJosinoBrejoAmparoRoubo2019,
                contandoRuaPadreRamiroAmparoRoubo2019,
                contandoRuaTabatingaBrejoAmparoRoubo2019,
                contandoRuaTabocaBrejoAmparoRoubo2019,
                contandoRuaTrintaSeteBrejoAmparoRoubo2019,
                contandoRuaTrintaOitoBrejoAmparoRoubo2019};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void caic2019() {

        //XX  caic fica no Dom joão Batista

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Caic2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }   // não consta no google

    private void centro2019() {

        String[] ruas  = new String[]{"Artur Bernades","Barão São Romão","Barão Rio Branco","Conego Ramiro Leite","Coronel Cassiano","Coronel Serrão","Dom Daniel","Getulio Vagas","Padre Henrique","Tiradentes"};

        int[]    valores = new int    []{contandoArturBernardesCentroRouboTodosAnos,
                contandoBaraoSaoRomaoCentroRouboTodosAnos,
                contandoBaraoRioBrancoCentroRouboTodosAnos,
                contandoConegoRamiroLeiteCentroRouboTodosAnos,
                contandoCelCassianoCentroRouboTodosAnos,
                contandoCelSerraoCentroRouboTodosAnos,
                contandoDomDanielCentroRouboTodosAnos,
                contandoGetulioVargasCentroRouboTodosAnos,
                contandoPadreHenriqueCentroRouboTodosAnos,
                contandoPracaTiradentesCentroRouboTodosAnos};






        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void ceramica2019() {


        String[] ruas  = new String[]{"R. Aterro","R. Brasília de Minas","R. Itacarambi","R. Itapiraçaba","R. Manga","R. Montes Claros","R. Montalvânia","R. Vazelândia","R. Milton Campos", "R. Milton Sá"};

        int[]    valores = new int   []{ contandoRuaAterroCeramicaRoubo2019,
                contandoRuaBrasiliaMinasCeramicaRoubo2019,
                contandoRuaItacarambiCeramicaRoubo2019,
                contandoRuaItapiracabaCeramicaRoubo2019,
                contandoRuaMangaCeramicaRoubo2019,
                contandoRuaMontesClarosCeramicaRoubo2019,
                contandoRuaMontalvaniaCeramicaRoubo2019,
                contandoRuaVarzelandiaCeramicaRoubo2019,
                contandoRuaMiltonCamposCeramicaRoubo2019,
                contandoRuaMiltonSaCeramicaRoubo2019,};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void eldorado2019() {


        String[] ruas  = new String[]{"Av. Seis","R. A","R. B","R. C","R. D","R. E","R. G","R. J","R. L","R. M"};

        int[]    valores = new int   []{contandoAvSeisEldoradoRoubo2019,
                contandoRuaAEldoradoRoubo2019,
                contandoRuaBEldoradoRoubo2019,
                contandoRuaCEldoradoRoubo2019,
                contandoRuaDEldoradoRoubo2019,
                contandoRuaEEldoradoRoubo2019,
                contandoRuaGEldoradoRoubo2019,
                contandoRuaJEldoradoRoubo2019,
                contandoRuaLEldoradoRoubo2019,
                contandoRuaMEldoradoRoubo2019};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void fabiao2019() {


        //XXX zona ruaral


        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Fabião2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void franklim2019() {

        String[] ruas  = new String[]{"R. J. Antônio do Vale Filho","R. L","R. Vinte e Três","R. Vinte e Quatro","R. Vinte e Cinco","R. Vinte e Oito"};

        int[]    valores = new int   []{contandoRuaAntonioValeFilhoFranklimRoubo2019,
                contandoRuaLFranklimRoubo2019,
                contandoRuaVinteTresFranklimRoubo2019,
                contandoRuaVinteQuatroFranklimRoubo2019,
                contandoRuaR25FranklimdoRoubo2019,
                contandoRuaVinteOitoFranklimRoubo2019,};






        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jadete2019() {

        String[] ruas  = new String[]{"Av. Cônego R. Leite","Alameda V","R. Inezita A. Ferreira","R. Isla H. Azevedo","R. S. Antônio","R. Viana","R. vinte e Dois","R. Ver João Alkimim","Tv. Viana"};

        int[]    valores =  new int   []{contandoRuaRamiroLeiteJadeteRouboAno2019,
                contandoRuaAlameidaVianaJadeteRouboAno2019,
                contandoRuaInezitaJadeteRouboAno2019,
                contandoRuaIslaHJadeteRouboAno2019,
                contandoRuaSantoAntJadeteRouboAno2019
                , contandoRuaVianaJadeteRouboAno2019,
                contandoRuavinteDoisJadeteRouboAno2019,
                contandoRuaJoaoAlkimimJadeteRouboAno2019,
                contandoTvVianaJadeteRouboAno2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void januaria2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Januária2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void jardimDaniel2019() {


        String[] ruas  = new String[]{"R. Um","R. Dois","Rua Três","R. Quatro","R. Alagoas","R. Bela Vista","Av. Cônego R. Leite","Rua Inezita A. Ferreira","R. Minas Gerais"};

        int[]    valores = new int   []{contandoRuaUmJardimDanielRoubo2019,
                contandoRuaDoisDanielRoubo2019,
                contandoRuaTresJardimDanielRoubo2019,
                contandoRuaQuatroJardimDanielRoubo2019,
                contandoRuaAlagoasJardimDanielRoubo2019,
                contandoRuaBelaVistaJardimDanielRoubo2019,
                contandoRuaConegoRamiroLeiteJardimDanielRoubo2019,
                contandoRuaInezitaAlvesFerreiraJardimDanielRoubo2019,
                contandoRuaMinasGeraisJardimDanielRoubo2019};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jardimEstela2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","Rua G","R. H","R. Barão de São Romão","R. João Gasparino","R. Vinte e Dois","R. Vinte e Três"};

        int[]    valores = new int   []{contandoRuaAJardimEstrelaRoubo2019,
                contandoRuaBJardimEstrelaRoubo2019,
                contandoRuaCJardimEstrelaRoubo2019,
                contandoRuaDJardimEstrelalRoubo2019,
                contandoRuaEJardimEstrelaRoubo2019,
                contandoRuaGJardimEstrelaRoubo2019,
                contandoRuaHJardimEstrelaRoubo2019,
                contandoRuaBaraoSaoRomaoJardimEstrelaRoubo2019,
                contandoRuaJoaoGasparinoJardimEstrelaRoubo2019,
                contandoRuaVinteDoisJardimEstrelaRoubo2019,
                contandoRuaVinteTresJardimEstrelaRoubo2019,};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void jatoba2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Jatobá2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void joventinaMesquita2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Joventina Mesquita2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void jussara2019() {


        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Quatro","R. Cinco","R. Seis","R. Sete","R. Oito","R. Nove","R. Dez"};

        int[]    valores = new int   []{contandoRuaUmJussaraRoubo2019,
                contandoRuaDoisJussaraRoubo2019,
                contandoRuaTresJussaraRoubo2019,
                contandoRuaQuatroJussaralRoubo2019,
                contandoRuaCincoJussaraRoubo2019,
                contandoRuaSeisJussaraRoubo2019,
                contandoRuaSeteJussaraRoubo2019,
                contandoRuaOitoJussaraRoubo2019,
                contandoRuaNoveJussaraRoubo2019,
                contandoRuaDezJussaraRoubo2019,};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void levianopolis2019() {

        String[] ruas  = new String[]{"R. Seis", "R. Sete", "R. Oito","R. Nove","R. Vinte", "R. Anízio G. Moreira ","R. H. Caciquinho","P. Emilio de Matos","R. J.A Vale Filho","R. Inezita A. Ferreira"};

        int[]    valores = new int   []{contandoRuaSeisVilaLevianopolisRoubo2019,
                contandoRuaSeteVilaLevianopolisRoubo2019,
                contandoRuaOitoVilaLevianopolisRoubo2019,
                contandoRuaNoveVilaLevianopolisRoubo2019,
                contandoRuaVinteVilaLevianopolisRoubo2019,
                contandoRuaAnizioGoncalvezMoreiraVilaLevianopolisRoubo2019,
                contandoRuaHonorCaciquinhoVilaLevianopolisRoubo2019,
                contandoRuaEmilioMatosVilaLevianopolisRoubo2019,
                contandoRuaJoseAntonioValeFilhoVilaLevianopolisRoubo2019,
                contandoRuaInezitaAlvesFerreiraLevianopolisRoubo2019};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void mangueiras2019() {

        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Mangueiras2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void margarete2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Margarete2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void moradeiras2019() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Moradeiras2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }// não consta no google

    private void normaConsuelo2019() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Norma Consuelo2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void novoMilenio2019() {

        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Novo Milênio2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void quintaMangabeiras2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. do Bonde","R. T.Bastos"};

        int[]    valores = new int   []{contandoRuaAQuintasMangueirasRoubo2019,
                contandoRuaBQuintasMangueirasRoubo2019,
                contandoRuaDQuintasMangueirasRoubo2019,
                contandoRuaEQuintasMangueirasRoubo2019,
                contandoRuaBondeQuintasMangueirasRoubo2019,
                contandoRuaTutaBastosQuintasMangueirasRoubo2019};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void riachoDaCruz2019() {

        String[] ruas  = new String[]{"R. Murici","R. Café Mineiro","R. Antônio C.da Silva","R. Manoel J.de Souza","R. Oliveira Pôrto","R. Tertuliano R.Pôrto","Tv. J.F.Melo"};

        int[]    valores = new int   []{


                contandoRuaMuriciRiachoCruzRoubo2019,
                contandoRuaCafeMineiroRiachoCruzRoubo2019,
                contandoRuaAntonioSilvaRiachoCruzRoubo2019,
                contandoRuaManoelJSouzaRiachoCruzRoubo2019,
                contandoRuaOliveiraPortoRiachoCruzRoubo2019,
                contandoRuaTertulianoRPortoRiachoCruzRoubo2019,
                contandoRuaJFMeloRiachoCruzRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoJoao2019() {


        String[] ruas  = new String[]{"Av.Cel.Cassiano","Tv.Leão Treze","R. Um","R. Dois","R. B.J Ferreira","R. do Curtume","R. Trinta de Maio","R. J.Augusto","R. Mal.Floriano Peixoto"};
        int[]    valores = new int   []{
                contandoRuaCelCassianoVilaSaoJoaoRoubo2019,
                contandoRuaLeaoTrezeVilaSaoJoaoRoubo2019,
                contandoRuaUmVilaSaoJoaoRoubo2019,
                contandoRuaDoisVilaSaoJoaoRoubo2019,
                contandoRuaBenicioJoseFerreiraVilaSaoJoaoRoubo2019,
                contandoRuaCurtumeVilaSaoJoaoRoubo2019,
                contandoRuaTrintaMarcoVilaSaoJoaoRoubo2019,
                contandoRuaJoseAugustoVilaSaoJoaoRoubo2019,
                contandoRuaMalFlorianoPeixotoVilaSaoJoaoRoubo2019,
                contandoRuaWVilaSaoJoaoRoubo2019};






        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoJoaquim2019() {

        String[] ruas  = new String[]{"R. Um","R. Dois","R. Três","R. Seis","R. Oito","R. Nove","R. Dez","R. Doze","R. S.Inês","R. S.Maria"};


        int[]    valores = new int   []{contandoRuaUmSaoJoaquimRoubo2019,
                contandoRuaDoisSaoJoaquimRoubo2019,
                contandoRuaTresSaoJoaquimRoubo2019,
                contandoRuaSeisSaoJoaquimRoubo2019,
                contandoRuaOitoSaoJoaquimRoubo2019,
                contandoRuaNoveSaoJoaquimRoubo2019,
                contandoRuaDezSaoJoaquimRoubo2019,
                contandoRuaDozeSaoJoaquimRoubo2019,
                contandoRuaSantaInesSaoJoaquimRoubo2019,
                contandoRuaSantaMariaSaoJoaquimRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;


        criarGraficos();


    }

    private void saoMiguel2019() {

        String[] ruas  = new String[]{"Av.São Francisco","R. Um", "R. Dois","Rua Três", "R. A", "R. B","R. D","R. Piraporã","R. Montavânia","Tv.Galiléia"};
        int[]    valores = new int   []{contandoRuaSaoFranciscoSaoMiguelRoubo2019,
                contandoRuaUmSaoMiguelRoubo2019,
                contandoRuaDoisSaoMiguelRoubo2019,
                contandoRuaTresSaoMiguelRoubo2019,
                contandoRuaASaoMiguelRoubo2019,
                contandoRuaBSaoMiguelRoubo2019,
                contandoRuaDSaoMiguelRoubo2019,
                contandoRuaPiraporaSaoMiguelRoubo2019,
                contandoRuaMontalvaniaSaoMiguelRoubo2019,
                contandoTvGalileiaSaoMiguelRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void saoVicente2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. D","R. E","R. E.T.Bastos","R. L.N.Neto","R. Maria C.Carvalho","R. Olibrio Lima","R. T.Torres","R. Sebastião F.Lima"};

        int[]    valores = new int   []{
                contandoRuaASaoVicenteRoubo2019,
                contandoRuaBSaoVicenteRoubo2019,
                contandoRuaDSaoVicenteRoubo2019,
                contandoRuaESaoVicenteRoubo2019,
                contandoRuaTutaBastosSaoVicenteRoubo2019,
                contandoRuaLeonelNogueiraNetoSaoVicenteRoubo2019,
                contandoRuaMariaCarneiroCarvalhoSaoVicenteRoubo2019,
                contandoRuaOlibrioLimaSaoVicenteRoubo2019,
                contandoRuaTerencioTorresSaoVicenteRoubo2019,
                contandoRuaSebastiaoFerreiraLimaSaoVicenteRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void sagradaFamilia2019() {

        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sagrada Família2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void santaCruz2019() {
        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Cruz2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void santaIsabel2019() {
        //xx   O google não mostra ruas nesse bairro

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Santa Isabel2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void sede2019() {
        ///XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Sede2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }   // não consta no google

    private void tejuco2019() {

        String[] ruas  = new String[]{"R. Castelo Branco","R. J.kubitschek","R. Tancredo Neves","R. Tejuco","R. Tropical","R. São João","R. São José","R. M.A.Carvalho"};

        int[]    valores = new int   []{


                contandoRuaCasteloBrancoTejucoRoubo2019,
                contandoRuaJucelinoKubitscheckTejucoRoubo2019,
                contandoRuaTancredoNevesTejucoRoubo2019,
                contandoRuaTejucoTejucoRoubo2019,
                contandoRuaTropicalTejucoVicenteRoubo2019,
                contandoRuaSaoJoaoTejucoRoubo2019,
                contandoRuaSaoJoseTejucoRoubo2019 ,
                contandoRuaManoelAlexandrinodeCarvalhoTejucoRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void terceiroMilenio2019() {

        //xx

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Terceiro Milênio2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void tropical2019() {

        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Tropical2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void varzeaDosPocoes2019() {
        //XX

        String[] ruas  = new String[]{"spinnerCorrente 7","spinnerCorrente 10","Várzea dos Pocões2019","Rua 12"};
        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }  // não consta no google

    private void vilaFatima2019() {

        String[] ruas  = new String[]{"R. A","R. B","R. C","R. D","R. E","R. F","R. G.P.Rocha","R. J.P.Carvalho","R. Joaquim Fernandes","R. J.Augusto"};

        int[]    valores = new int   []{ contandoRuaAVilaFatimaRoubo2019,
                contandoRuaBFatimaRoubo2019,
                contandoRuaCVilaFatimaRoubo2019,
                contandoRuaDVilaFatimaRoubo2019,
                contandoRuaEVilaFatimaVicenteRoubo2019,
                contandoRuaFVilaFatimaRoubo2019,
                contandoRuaGilmarPereiraRochaVilaFatimaRoubo2019,
                contandoRuaJoaoPimentaCarvalhoVilaFatimaRoubo2019,
                contandoRuaJoaquimFernandesoVilaFatimaRoubo2019,
                contandoRuaJoseAugustoVilaFatimaRoubo2019};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void pandeiros2019() {

        String[] ruas  = new String[]{"R. J.B.Gobira","R. M.Moreira"};

        int[]    valores = new int   []{
                contandoRuaJoaquimBarbosaGobiraPandeirosRoubo2019,
                contandoRuaMariaMoreiraPandeirosRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaPaula2019() {

        String[] ruas  = new String[]{"Av.São Fransisco","Tv.J.Nunes","Tv.J.Moura","R. L.N.Neto","R. A.J.Rocha","R. J.Moura","R. J.Nunes","R. S.F.Lima","R. Srg.Monzart"};

        int[]    valores = new int   []{contandoRuaSaoFranciscoVilaPaulaRoubo2019,
                contandoTvJoseNunesVilaPaulaRoubo2019 ,
                contandoTvJulioMouraVilaPaulaRoubo2019 ,
                contandoRuaLeonelNogueiraNetoVilaPaulaRoubo2019,
                contandoRuaAnizioJoseRochaVilaPaulaRoubo2019,
                contandoRuaJulioMouraVilaPaulaRoubo2019,
                contandoRuaJoseNunesVilaPaulaRoubo2019,
                contandoRuaSebastiaoFerreiraLimaVilaPaulaRoubo2019,
                contandoRuaSrgMozarVilaPaulaRoubo2019};



        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaVerde2019() {

        String[] ruas  = new String[]{"Av.Mal.Deodoro da Fonseca","R. A","R. Aterro","R. B","R. D","R. O","R. R","R. T","R. U","R. V"};

        int[]    valores = new int   []{contandoAvMalDeodoroFonsecaVilaVerdeRoubo2019,
                contandoRAVilaVerdeRoubo2019,
                contandoRuaAterroVilaVerdeRoubo2019,
                contandoRuaBVilaVerdeRoubo2019,
                contandoRuaDVilaVerdeRoubo2019,
                contandoRuaOVilaVerdeRoubo2019,
                contandoRuaRVilaVerdeRoubo2019,
                contandoRuaTVilaVerdeRoubo2019,
                contandoRuaUVilaVerdeRoubo2019,
                contandoRuaVVilaVerdeRoubo2019};




        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void vilaViana2019() {

        String[] ruas  = new String[]{"R. A","Rua Arthur L.Pimenta","R. B","R. D","R. E","R. F","R. G","R. R","R. X"};

        int[]    valores = new int   []{ contandoRuaAVilaVianaRoubo2019,
                contandoRuaArthurLopesPimentaVilaVianaRoubo2019,
                contandoRuaBVilaVianaRoubo2019,
                contandoRuaDVilaVianaRoubo2019,
                contandoRuaEVilaVianaRoubo2019,
                contandoRuaFVilaVianaRoubo2019,
                contandoRuaGVilaVianaRoubo2019,
                contandoRuaRVilaVianaRoubo2019,
                contandoRuaXVilaVianaRoubo2019};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    }

    private void zonaRural2019() {


        String[] ruas  = new String[]{"Zona Rural"};

        int[]    valores = new int   []{10,10,10,10};





        nomes   = ruas;
        roubos =  valores;
        cores   =  cor;
        legenda  = legendaGrafico;



        criarGraficos();


    } /// não fazer




    ///  /// opção no spinnner Ruas
    // contagem de furtos e roubos por rua



    public void ruasBairroAeroportoTodosAnos(){



        if (procuraBairro.contains("Aeroporto") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



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


    }

    public void ruasBairroAeroporto2019(){




        if (procuraBairro.contains("Aeroporto") && procuraAno.contains(anoProcura2019)&&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



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
    }

    public void ruasBairroAltoPocoesTodosAnos(){




        if (procuraBairro.contains("Alto dos Pocões") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




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



    }

    public void ruasBairroAltoPocoes2019(){




        if (procuraBairro.contains("Alto dos Pocões") && procuraAno.contains(anoProcura2019)&&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




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

    }

    public void ruasBairroAlvoradaTodosAnos(){





        if (procuraBairro.contains("Alvorada") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



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


    }

    public void ruasBairroAlvorada2019(){




        if (procuraBairro.contains("Alvorada") && procuraAno.contains(anoProcura2019)&&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



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
    }

    public void ruasBairroBandeirantesTodosAnos(){




        if (procuraBairro.contains("Bandeirantes") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




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

    }

    public void ruasBairroBandeirantes2019(){




        if (procuraBairro.contains("Bandeirantes") && procuraAno.contains(anoProcura2019)&&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




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

    }

    public void ruasBairroBoaEsperancaTodosAnos(){



        if (procuraBairro.contains("Boa Esperança") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmBoaEsperancaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisBoaEsperancaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresBoaEsperancaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroBoaEsperancaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Cinco")){

                contandoRuaCincoBoaEsperancaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisBoaEsperancaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Treze de Maio")){

                contandoRuaTrezeMaioBoaEsperancaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Luís Tupiná")){

                contandoRuaLuisTupinaBoaEsperancaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Hermílio Tupiná")){


                contandoRuaHermilioTupinaBoaEsperancaRouboTodosAnos++;


            }
            if (b.getAlertaRua().equals("R. São Geraldo")){

                contandoRuaSaoGeraldoBoaEsperancaRouboTodosAnos++;


            }


        }

    }

    public void ruasBairroBoaEsperanca2019(){




        if (procuraBairro.contains("Boa Esperança") && procuraAno.contains(anoProcura2019)&&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){





            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmBoaEsperancaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisBoaEsperancaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresBoaEsperancaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroBoaEsperancaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Cinco")){

                contandoRuaCincoBoaEsperancaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisBoaEsperancaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Treze de Maio")){

                contandoRuaTrezeMaioBoaEsperancaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Luís Tupiná")){

                contandoRuaLuisTupinaBoaEsperancaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Hermílio Tupiná")){


                contandoRuaHermilioTupinaBoaEsperancaRoubo2019++;


            }
            if (b.getAlertaRua().equals("R. São Geraldo")){

                contandoRuaSaoGeraldoBoaEsperancaRoubo2019++;


            }
        }

    }

    public void ruasBairroBoaVistaTodosAnos(){





        if (procuraBairro.contains("Boa Vista") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("R. A")){

                contandoRuaABoaVistaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBBoaVistaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. C")){

                contandoRuaCBoaVistaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDBoaVistaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEBoaVistaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. F")){

                contandoRuaFBoaVistaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. G")){

                contandoRuaGBoaVistaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. H")){

                contandoRuaHBoaVistaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. I")){


                contandoRuaIBoaVistaRouboTodosAnos++;


            }
            if (b.getAlertaRua().equals("R. J")){

                contandoRuaJBoaVistaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLBoaVistaRouboTodosAnos++;


            }


        }


    }

    public void ruasBairroBoaVista2019(){




        if (procuraBairro.contains("Boa Vista") && procuraAno.contains(anoProcura2019)&& (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){






            if (b.getAlertaRua().equals("R. A")){

                contandoRuaABoaVistaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBBoaVistaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. C")){

                contandoRuaCBoaVistaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDBoaVistaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEBoaVistaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. F")){

                contandoRuaFBoaVistaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. G")){

                contandoRuaGBoaVistaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. H")){

                contandoRuaHBoaVistaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. I")){


                contandoRuaIBoaVistaRoubo2019++;


            }
            if (b.getAlertaRua().equals("R. J")){

                contandoRuaJBoaVistaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLBoaVistaRoubo2019++;


            }

        }

    }

    public void ruasBairroVilaBrasilandiaTodosAnos(){






        if (procuraBairro.contains("Vila Brasilandia") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmVilaBrasilandiaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisVilaBrasilandiaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresVilaBrasilandiaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroVilaBrasilandiaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Cinto")){

                contandoRuaCincoVilaBrasilandiaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisVilaBrasilandiaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Sete")){

                contandoRuaSeteVilaBrasilandiaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Minas Gerais")){

                contandoRuaMinasGeraisVilaBrasilandiaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Pirapora")){


                contandoRuaPiraporaVilaBrasilandiaRouboTodosAnos++;


            }
            if (b.getAlertaRua().equals("R. São Luiz")){

                contandoRuaSaoLuizVilaBrasilandiaRouboTodosAnos++;


            }

        }

    }

    public void ruasBairroVilaBrasilandia2019(){




        if (procuraBairro.contains("Vila Brasilandia") && procuraAno.contains(anoProcura2019)&&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmVilaBrasilandiaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisVilaBrasilandiaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresVilaBrasilandiaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroVilaBrasilandiaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Cinto")){

                contandoRuaCincoVilaBrasilandiaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisVilaBrasilandiaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Sete")){

                contandoRuaSeteVilaBrasilandiaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Minas Gerais")){

                contandoRuaMinasGeraisVilaBrasilandiaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Pirapora")){


                contandoRuaPiraporaVilaBrasilandiaRoubo2019++;


            }
            if (b.getAlertaRua().equals("R. São Luiz")){

                contandoRuaSaoLuizVilaBrasilandiaRoubo2019++;


            }

        }

    }

    public void ruasBairroBrejoAmparoTodosAnos(){



        if (procuraBairro.contains("Brejo do Amparo") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("Alameda do Riacho")){

                contandoAlamedaRiachoBrejoAmparoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Bela Vista")){

                contandoRuaBelaVistaBrejoAmparoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. da Serra")){

                contandoRuaSerraBrejoAmparoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Flôres")){

                contandoRuaFloresBrejoAmparoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Padre Josino")){

                contandoRuaPadreJosinoBrejoAmparoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Padre Ramiro")){

                contandoRuaPadreRamiroAmparoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Tabatinga")){

                contandoRuaTabatingaBrejoAmparoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Taboca")){

                contandoRuaTabocaBrejoAmparoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Trinta e Sete")){


                contandoRuaTrintaSeteBrejoAmparoRouboTodosAnos++;


            }
            if (b.getAlertaRua().equals("R. Trinta e Oito")){

                contandoRuaTrintaOitoBrejoAmparoRouboTodosAnos++;


            }

        }


    }

    public void ruasBairroBrejoAmparo2019(){




        if (procuraBairro.contains("Brejo do Amparo") && procuraAno.contains(anoProcura2019)&&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("Alameda do Riacho")){

                contandoAlamedaRiachoBrejoAmparoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Bela Vista")){

                contandoRuaBelaVistaBrejoAmparoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. da Serra")){

                contandoRuaSerraBrejoAmparoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Flôres")){

                contandoRuaFloresBrejoAmparoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Padre Josino")){

                contandoRuaPadreJosinoBrejoAmparoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Padre Ramiro")){

                contandoRuaPadreRamiroAmparoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Tabatinga")){

                contandoRuaTabatingaBrejoAmparoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Taboca")){

                contandoRuaTabocaBrejoAmparoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Trinta e Sete")){


                contandoRuaTrintaSeteBrejoAmparoRoubo2019++;


            }
            if (b.getAlertaRua().equals("R. Trinta e Oito")){

                contandoRuaTrintaOitoBrejoAmparoRoubo2019++;


            }


        }

    }

    public void ruasBairroCentroTodosAnos(){




        if (procuraBairro.contains("Centro") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("Praça Artur Bernardes")){

                contandoArturBernardesCentroRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Barão de São Romão")){

                contandoBaraoSaoRomaoCentroRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Barão do Rio Branco")){

                contandoBaraoRioBrancoCentroRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Av. Cônego Ramiro Leite")){

                contandoConegoRamiroLeiteCentroRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Av. Cel. Cassiano")){

                contandoCelCassianoCentroRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Cel. Serrão")){

                contandoCelSerraoCentroRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("Praça Dom Daniel")){

                contandoDomDanielCentroRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Praça Pres. Getúlio Vargas")){

                contandoGetulioVargasCentroRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Padre Henrique")){

                contandoPadreHenriqueCentroRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Praça Tiradentes")){


                contandoPracaTiradentesCentroRouboTodosAnos++;


            }


        }


    }

    public void ruasBairroCentro2019() {


        if (procuraBairro.contains("Centro") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {


            if (b.getAlertaRua().equals("Praça Artur Bernardes")) {

                contandoArturBernardesCentroRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Barão de São Romão")) {

                contandoBaraoSaoRomaoCentroRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Barão do Rio Branco")) {

                contandoBaraoRioBrancoCentroRoubo2019++;


            }

            if (b.getAlertaRua().equals("Av. Cônego Ramiro Leite")) {

                contandoConegoRamiroLeiteCentroRoubo2019++;


            }


            if (b.getAlertaRua().equals("Av. Cel. Cassiano")) {

                contandoCelCassianoCentroRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Cel. Serrão")) {

                contandoCelSerraoCentroRoubo2019++;


            }


            if (b.getAlertaRua().equals("Praça Dom Daniel")) {

                contandoDomDanielCentroRoubo2019++;


            }

            if (b.getAlertaRua().equals("Praça Pres. Getúlio Vargas")) {

                contandoGetulioVargasCentroRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Padre Henrique")) {

                contandoPadreHenriqueCentroRoubo2019++;


            }


            if (b.getAlertaRua().equals("Praça Tiradentes")) {


                contandoPracaTiradentesCentroRoubo2019++;


            }

        }

    }

    public void ruasBairroCeramicaTodosAnos(){




        if (procuraBairro.contains("Ceramica") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. do Aterro")){

                contandoRuaAterroCeramicaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Brasília de Minas")){

                contandoRuaBrasiliaMinasCeramicaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Itacarambi")){

                contandoRuaItacarambiCeramicaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Itapiracaba")){

                contandoRuaItapiracabaCeramicaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Manga")){

                contandoRuaMangaCeramicaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Montes Claros")){

                contandoRuaMontesClarosCeramicaRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. Montalvânia")){

                contandoRuaMontalvaniaCeramicaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Varzelândia")){

                contandoRuaVarzelandiaCeramicaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Milton Campos")){

                contandoRuaMiltonCamposCeramicaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Milton Sá")){


                contandoRuaMiltonSaCeramicaRouboTodosAnos++;


            }



        }





    }

    public void ruasBairroCeramica2019() {


        if (procuraBairro.contains("Ceramica") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {



            if (b.getAlertaRua().equals("R. do Aterro")){

                contandoRuaAterroCeramicaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Brasília de Minas")){

                contandoRuaBrasiliaMinasCeramicaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Itacarambi")){

                contandoRuaItacarambiCeramicaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Itapiracaba")){

                contandoRuaItapiracabaCeramicaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Manga")){

                contandoRuaMangaCeramicaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Montes Claros")){

                contandoRuaMontesClarosCeramicaRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. Montalvânia")){

                contandoRuaMontalvaniaCeramicaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Varzelândia")){

                contandoRuaVarzelandiaCeramicaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Milton Campos")){

                contandoRuaMiltonCamposCeramicaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Milton Sá")){


                contandoRuaMiltonSaCeramicaRoubo2019++;


            }
        }

    }

    public void ruasBairroEldoradoTodosAnos(){




        if (procuraBairro.contains("Eldorado") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("Av. Seis")){

                contandoAvSeisEldoradoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAEldoradoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBEldoradoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. C")){

                contandoRuaCEldoradoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDEldoradoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEEldoradoRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. G")){

                contandoRuaGEldoradoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. J")){

                contandoRuaJEldoradoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLEldoradoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. M")){


                contandoRuaMEldoradoRouboTodosAnos++;


            }



        }



    }

    public void ruasBairroEldorado2019() {


        if (procuraBairro.contains("Eldorado") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {




            if (b.getAlertaRua().equals("Av. Seis")){

                contandoAvSeisEldoradoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAEldoradoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBEldoradoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. C")){

                contandoRuaCEldoradoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDEldoradoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEEldoradoRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. G")){

                contandoRuaGEldoradoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. J")){

                contandoRuaJEldoradoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLEldoradoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. M")){


                contandoRuaMEldoradoRoubo2019++;


            }
        }





    }

    public void ruasBairroFranklimTodosAnos(){





        if (procuraBairro.contains("Franklim") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("R. José Antônio do Vale Filho")){

                contandoRuaAntonioValeFilhoFranklimRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLFranklimRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Três")){

                contandoRuaVinteTresFranklimRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Quatro")){

                contandoRuaVinteQuatroFranklimRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. 25")){

                contandoRuaR25FranklimdoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Vinte e Oito")){

                contandoRuaVinteOitoFranklimRouboTodosAnos++;


            }






        }




    }

    public void ruasBairroFranklim2019() {


        if (procuraBairro.contains("Franklim") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {





            if (b.getAlertaRua().equals("R. José Antônio do Vale Filho")){

                contandoRuaAntonioValeFilhoFranklimRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. L")){

                contandoRuaLFranklimRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Três")){

                contandoRuaVinteTresFranklimRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Quatro")){

                contandoRuaVinteQuatroFranklimRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. 25")){

                contandoRuaR25FranklimdoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Vinte e Oito")){

                contandoRuaVinteOitoFranklimRoubo2019++;


            }
        }





    }

    public void ruasBairroJardimDanielTodosAnos(){






        if (procuraBairro.contains("Jardim Daniel") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmJardimDanielRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisDanielRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Rua Três")){

                contandoRuaTresJardimDanielRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroJardimDanielRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Alagoas")){

                contandoRuaAlagoasJardimDanielRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Bela Vista")){

                contandoRuaBelaVistaJardimDanielRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Av. Cônego Ramiro Leite")){

                contandoRuaConegoRamiroLeiteJardimDanielRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Inezita Alves Ferreira")){

                contandoRuaInezitaAlvesFerreiraJardimDanielRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Minas Gerais")){

                contandoRuaMinasGeraisJardimDanielRouboTodosAnos++;


            }

        }

    }

    public void ruasBairroJardimDaniel2019() {


        if (procuraBairro.contains("Jardim Daniel") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {





            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmJardimDanielRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisDanielRoubo2019++;


            }

            if (b.getAlertaRua().equals("Rua Três")){

                contandoRuaTresJardimDanielRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroJardimDanielRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Alagoas")){

                contandoRuaAlagoasJardimDanielRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Bela Vista")){

                contandoRuaBelaVistaJardimDanielRoubo2019++;


            }


            if (b.getAlertaRua().equals("Av. Cônego Ramiro Leite")){

                contandoRuaConegoRamiroLeiteJardimDanielRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Inezita Alves Ferreira")){

                contandoRuaInezitaAlvesFerreiraJardimDanielRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Minas Gerais")){

                contandoRuaMinasGeraisJardimDanielRoubo2019++;


            }

        }


    }

    public void ruasBairroJardimEstrelaTodosAnos(){




        if (procuraBairro.contains("Jardim Estrela") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){





            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAJardimEstrelaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBJardimEstrelaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. C")){

                contandoRuaCJardimEstrelaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDJardimEstrelalRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEJardimEstrelaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Rua G")){

                contandoRuaGJardimEstrelaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. H")){

                contandoRuaHJardimEstrelaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Barão de São Romão")){

                contandoRuaBaraoSaoRomaoJardimEstrelaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. João Gasparino")){

                contandoRuaJoaoGasparinoJardimEstrelaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Dois")){

                contandoRuaVinteDoisJardimEstrelaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Três")){

                contandoRuaVinteTresJardimEstrelaRouboTodosAnos++;

            }

        }


    }

    public void ruasBairroJardimEstrela2019() {


        if (procuraBairro.contains("Jardim Estrela") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {




            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAJardimEstrelaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBJardimEstrelaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. C")){

                contandoRuaCJardimEstrelaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDJardimEstrelalRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEJardimEstrelaRoubo2019++;


            }


            if (b.getAlertaRua().equals("Rua G")){

                contandoRuaGJardimEstrelaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. H")){

                contandoRuaHJardimEstrelaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Barão de São Romão")){

                contandoRuaBaraoSaoRomaoJardimEstrelaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. João Gasparino")){

                contandoRuaJoaoGasparinoJardimEstrelaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Dois")){

                contandoRuaVinteDoisJardimEstrelaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Vinte e Três")){

                contandoRuaVinteTresJardimEstrelaRoubo2019++;


            }

        }


    }

    public void ruasBairroJussaraTodosAnos(){




        if (procuraBairro.contains("Jussara") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){





            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmJussaraRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisJussaraRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresJussaraRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroJussaralRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Cinco")){

                contandoRuaCincoJussaraRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisJussaraRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Sete")){

                contandoRuaSeteJussaraRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Oito")){

                contandoRuaOitoJussaraRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Nove")){

                contandoRuaNoveJussaraRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dez")){

                contandoRuaDezJussaraRouboTodosAnos++;


            }



        }


    }

    public void ruasBairroJussara2019() {


        if (procuraBairro.contains("Jussara") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {






            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmJussaraRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisJussaraRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresJussaraRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Quatro")){

                contandoRuaQuatroJussaralRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Cinco")){

                contandoRuaCincoJussaraRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisJussaraRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Sete")){

                contandoRuaSeteJussaraRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Oito")){

                contandoRuaOitoJussaraRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Nove")){

                contandoRuaNoveJussaraRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dez")){

                contandoRuaDezJussaraRoubo2019++;


            }
        }

    }

    public void ruasBairroVilaLevianopolisTodosAnos(){



        if (procuraBairro.contains("Vila Levianopolis") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisVilaLevianopolisRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Sete")){

                contandoRuaSeteVilaLevianopolisRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Oito")){

                contandoRuaOitoVilaLevianopolisRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Nove")){

                contandoRuaNoveVilaLevianopolisRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Vinte")){

                contandoRuaVinteVilaLevianopolisRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Anízio Gonçalvez Moreira,")){

                contandoRuaAnizioGoncalvezMoreiraVilaLevianopolisRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Honor Caciquinho")){

                contandoRuaHonorCaciquinhoVilaLevianopolisRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Praça Emilio de Matos")){

                contandoRuaEmilioMatosVilaLevianopolisRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. José Antônio do Vale Filho")){

                contandoRuaJoseAntonioValeFilhoVilaLevianopolisRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Inezita Alves Ferreira")){

                contandoRuaInezitaAlvesFerreiraLevianopolisRouboTodosAnos++;


            }



        }




    }

    public void ruasBairroVilaLevianopolis2019() {


        if (procuraBairro.contains("Vila Levianopolis") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {






            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisVilaLevianopolisRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Sete")){

                contandoRuaSeteVilaLevianopolisRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Oito")){

                contandoRuaOitoVilaLevianopolisRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Nove")){

                contandoRuaNoveVilaLevianopolisRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Vinte")){

                contandoRuaVinteVilaLevianopolisRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Anízio Gonçalvez Moreira,")){

                contandoRuaAnizioGoncalvezMoreiraVilaLevianopolisRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Honor Caciquinho")){

                contandoRuaHonorCaciquinhoVilaLevianopolisRoubo2019++;


            }

            if (b.getAlertaRua().equals("Praça Emilio de Matos")){

                contandoRuaEmilioMatosVilaLevianopolisRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. José Antônio do Vale Filho")){

                contandoRuaJoseAntonioValeFilhoVilaLevianopolisRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Inezita Alves Ferreira")){

                contandoRuaInezitaAlvesFerreiraLevianopolisRoubo2019++;


            }
        }

    }

    public void ruasBairroQuintasMangueirasTodosAnos(){



        if (procuraBairro.contains("Quintas das Mangueiras") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAQuintasMangueirasRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBQuintasMangueirasRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDQuintasMangueirasRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEQuintasMangueirasRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. do Bonde")){

                contandoRuaBondeQuintasMangueirasRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Rua Tuta Bastos")){

                contandoRuaTutaBastosQuintasMangueirasRouboTodosAnos++;


            }

        }

    }

    public void ruasBairroQuintasMangueiras2019() {


        if (procuraBairro.contains("Quintas das Mangueiras") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {







            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAQuintasMangueirasRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBQuintasMangueirasRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDQuintasMangueirasRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEQuintasMangueirasRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. do Bonde")){

                contandoRuaBondeQuintasMangueirasRoubo2019++;


            }


            if (b.getAlertaRua().equals("Rua Tuta Bastos")){

                contandoRuaTutaBastosQuintasMangueirasRoubo2019++;


            }
        }




    }

    public void ruasBairroRiachoCruzTodosAnos(){


        if (procuraBairro.contains("Riacho da Cruz") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. Murici")){

                contandoRuaMuriciRiachoCruzRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Café Mineiro")){

                contandoRuaCafeMineiroRiachoCruzRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Antônio C.da Silva")){

                contandoRuaAntonioSilvaRiachoCruzRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Manoel J.de Souza")){

                contandoRuaManoelJSouzaRiachoCruzRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Oliveira Pôrto")){

                contandoRuaOliveiraPortoRiachoCruzRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Tertuliano R.Pôrto")){

                contandoRuaTertulianoRPortoRiachoCruzRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Tv. J.F.Melo")){

                contandoRuaJFMeloRiachoCruzRouboTodosAnos++;


            }

        }

    }

    public void ruasBairroRiachoCruz2019() {


        if (procuraBairro.contains("Riacho da Cruz") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {



            if (b.getAlertaRua().equals("R. Murici")){

                contandoRuaMuriciRiachoCruzRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Café Mineiro")){

                contandoRuaCafeMineiroRiachoCruzRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Antônio C.da Silva")){

                contandoRuaAntonioSilvaRiachoCruzRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Manoel J.de Souza")){

                contandoRuaManoelJSouzaRiachoCruzRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Oliveira Pôrto")){

                contandoRuaOliveiraPortoRiachoCruzRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Tertuliano R.Pôrto")){

                contandoRuaTertulianoRPortoRiachoCruzRoubo2019++;


            }


            if (b.getAlertaRua().equals("Tv. J.F.Melo")){

                contandoRuaJFMeloRiachoCruzRoubo2019++;


            }


        }



    }

    public void ruasBairroVilaSaoJoaoTodosAnos(){



        if (procuraBairro.contains("Vila Sao Joao") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("Av. Cel. Cassiano")){

                contandoRuaCelCassianoVilaSaoJoaoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Tv. Leão Treze")){

                contandoRuaLeaoTrezeVilaSaoJoaoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmVilaSaoJoaoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisVilaSaoJoaoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Benício José Ferreira")){

                contandoRuaBenicioJoseFerreiraVilaSaoJoaoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. do Curtume")){

                contandoRuaCurtumeVilaSaoJoaoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Trinta de Março")){

                contandoRuaTrintaMarcoVilaSaoJoaoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. José Augusto")){

                contandoRuaJoseAugustoVilaSaoJoaoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Mal. Floriano Peixoto")){

                contandoRuaMalFlorianoPeixotoVilaSaoJoaoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. W")){

                contandoRuaWVilaSaoJoaoRouboTodosAnos++;


            }


        }



    }

    public void ruasBairroVilaSaoJoao2019() {


        if (procuraBairro.contains("Vila Sao Joao") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {






            if (b.getAlertaRua().equals("Av. Cel. Cassiano")){

                contandoRuaCelCassianoVilaSaoJoaoRoubo2019++;


            }

            if (b.getAlertaRua().equals("Tv. Leão Treze")){

                contandoRuaLeaoTrezeVilaSaoJoaoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmVilaSaoJoaoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisVilaSaoJoaoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Benício José Ferreira")){

                contandoRuaBenicioJoseFerreiraVilaSaoJoaoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. do Curtume")){

                contandoRuaCurtumeVilaSaoJoaoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Trinta de Março")){

                contandoRuaTrintaMarcoVilaSaoJoaoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. José Augusto")){

                contandoRuaJoseAugustoVilaSaoJoaoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Mal. Floriano Peixoto")){

                contandoRuaMalFlorianoPeixotoVilaSaoJoaoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. W")){

                contandoRuaWVilaSaoJoaoRoubo2019++;


            }
        }

    }

    public void ruasBairroSaoJoaquimTodosAnos(){






        if (procuraBairro.contains("São Joaquim") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmSaoJoaquimRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisSaoJoaquimRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresSaoJoaquimRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisSaoJoaquimRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Oito")){

                contandoRuaOitoSaoJoaquimRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Nove")){

                contandoRuaNoveSaoJoaquimRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Dez")){

                contandoRuaDezSaoJoaquimRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Doze")){

                contandoRuaDozeSaoJoaquimRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("Rua Santa Inês")){

                contandoRuaSantaInesSaoJoaquimRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Rua Santa Maria")){

                contandoRuaSantaMariaSaoJoaquimRouboTodosAnos++;


            }

        }

    }

    public void ruasBairroSaoJoaquim2019() {


        if (procuraBairro.contains("São Joaquim") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {





            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmSaoJoaquimRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisSaoJoaquimRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Três")){

                contandoRuaTresSaoJoaquimRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Seis")){

                contandoRuaSeisSaoJoaquimRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Oito")){

                contandoRuaOitoSaoJoaquimRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Nove")){

                contandoRuaNoveSaoJoaquimRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Dez")){

                contandoRuaDezSaoJoaquimRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Doze")){

                contandoRuaDozeSaoJoaquimRoubo2019++;


            }



            if (b.getAlertaRua().equals("Rua Santa Inês")){

                contandoRuaSantaInesSaoJoaquimRoubo2019++;


            }

            if (b.getAlertaRua().equals("Rua Santa Maria")){

                contandoRuaSantaMariaSaoJoaquimRoubo2019++;


            }
        }


    }

    public void ruasBairroSaoMiguelTodosAnos(){



        if (procuraBairro.contains("Vila Sao Miguel") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("Av. São Francisco")){

                contandoRuaSaoFranciscoSaoMiguelRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmSaoMiguelRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisSaoMiguelRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Rua Três")){

                contandoRuaTresSaoMiguelRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. A")){

                contandoRuaASaoMiguelRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBSaoMiguelRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDSaoMiguelRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Piraporã")){

                contandoRuaPiraporaSaoMiguelRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. Montalvânia")){

                contandoRuaMontalvaniaSaoMiguelRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Tv. Galiléia")){

                contandoTvGalileiaSaoMiguelRouboTodosAnos++;


            }



        }


    }

    public void ruasBairroSaoMiguel2019() {


        if (procuraBairro.contains("Vila Sao Miguel") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {



            if (b.getAlertaRua().equals("Av. São Francisco")){

                contandoRuaSaoFranciscoSaoMiguelRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Um")){

                contandoRuaUmSaoMiguelRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Dois")){

                contandoRuaDoisSaoMiguelRoubo2019++;


            }

            if (b.getAlertaRua().equals("Rua Três")){

                contandoRuaTresSaoMiguelRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. A")){

                contandoRuaASaoMiguelRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBSaoMiguelRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDSaoMiguelRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Piraporã")){

                contandoRuaPiraporaSaoMiguelRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. Montalvânia")){

                contandoRuaMontalvaniaSaoMiguelRoubo2019++;


            }

            if (b.getAlertaRua().equals("Tv. Galiléia")){

                contandoTvGalileiaSaoMiguelRoubo2019++;


            }

        }




    }

    public void ruasBairroSaoVicenteTodosAnos(){




        if (procuraBairro.contains("São Vicente") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. A")){

                contandoRuaASaoVicenteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBSaoVicenteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDSaoVicenteRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. e")){

                contandoRuaESaoVicenteRouboTodosAnos++;


            }




            if (b.getAlertaRua().equals("Rua Tuta Bastos")){

                contandoRuaTutaBastosSaoVicenteRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Leonel Nogueira Neto")){

                contandoRuaLeonelNogueiraNetoSaoVicenteRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Maria Carneiro Carvalho")){

                contandoRuaMariaCarneiroCarvalhoSaoVicenteRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Olibrio Lima")){

                contandoRuaOlibrioLimaSaoVicenteRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Terêncio Tôrres")){

                contandoRuaTerencioTorresSaoVicenteRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. Sebastião Ferreira Lima")){

                contandoRuaSebastiaoFerreiraLimaSaoVicenteRouboTodosAnos++;


            }





        }



    }

    public void ruasBairroSaoVicente2019() {


        if (procuraBairro.contains("São Vicente") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {




            if (b.getAlertaRua().equals("R. A")){

                contandoRuaASaoVicenteRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBSaoVicenteRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDSaoVicenteRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. e")){

                contandoRuaESaoVicenteRoubo2019++;


            }


            if (b.getAlertaRua().equals("Rua Tuta Bastos")){

                contandoRuaTutaBastosSaoVicenteRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Leonel Nogueira Neto")){

                contandoRuaLeonelNogueiraNetoSaoVicenteRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Maria Carneiro Carvalho")){

                contandoRuaMariaCarneiroCarvalhoSaoVicenteRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Olibrio Lima")){

                contandoRuaOlibrioLimaSaoVicenteRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Terêncio Tôrres")){

                contandoRuaTerencioTorresSaoVicenteRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. Sebastião Ferreira Lima")){

                contandoRuaSebastiaoFerreiraLimaSaoVicenteRoubo2019++;


            }
        }



    }

    public void ruasBairroTejucoTodosAnos(){




        if (procuraBairro.contains("Tejuco") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("R. Castelo Branco")){

                contandoRuaCasteloBrancoTejucoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Jucelino Kubitscheck")){

                contandoRuaJucelinoKubitscheckTejucoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Tancredo Neves")){

                contandoRuaTancredoNevesTejucoRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Tejuco")){

                contandoRuaTejucoTejucoRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. Tropical")){

                contandoRuaTropicalTejucoVicenteRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. São João")){

                contandoRuaSaoJoaoTejucoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. São José")){

                contandoRuaSaoJoseTejucoRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Manoel Alexandrino de Carvalho")){

                contandoRuaManoelAlexandrinodeCarvalhoTejucoRouboTodosAnos++;


            }


        }



    }

    public void ruasBairroTejuco2019() {


        if (procuraBairro.contains("Tejuco") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {





            if (b.getAlertaRua().equals("R. Castelo Branco")){

                contandoRuaCasteloBrancoTejucoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Jucelino Kubitscheck")){

                contandoRuaJucelinoKubitscheckTejucoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Tancredo Neves")){

                contandoRuaTancredoNevesTejucoRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Tejuco")){

                contandoRuaTejucoTejucoRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. Tropical")){

                contandoRuaTropicalTejucoVicenteRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. São João")){

                contandoRuaSaoJoaoTejucoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. São José")){

                contandoRuaSaoJoseTejucoRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Manoel Alexandrino de Carvalho")){

                contandoRuaManoelAlexandrinodeCarvalhoTejucoRoubo2019++;


            }
        }


    }

    public void ruasBairroVilaFatimaTodosAnos(){



        if (procuraBairro.contains("Vila Fatima") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAVilaFatimaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBFatimaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. C")){

                contandoRuaCVilaFatimaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDVilaFatimaRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEVilaFatimaVicenteRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. F")){

                contandoRuaFVilaFatimaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("Rua Gilmar Pereira Rocha")){

                contandoRuaGilmarPereiraRochaVilaFatimaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. João Pimenta de Carvalho")){

                contandoRuaJoaoPimentaCarvalhoVilaFatimaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Joaquim Fernandes")){

                contandoRuaJoaquimFernandesoVilaFatimaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. José Augusto")){

                contandoRuaJoseAugustoVilaFatimaRouboTodosAnos++;


            }



        }


    }

    public void ruasBairroVilaFatima2019() {


        if (procuraBairro.contains("Vila Fatima") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {





            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAVilaFatimaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBFatimaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. C")){

                contandoRuaCVilaFatimaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDVilaFatimaRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEVilaFatimaVicenteRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. F")){

                contandoRuaFVilaFatimaRoubo2019++;


            }


            if (b.getAlertaRua().equals("Rua Gilmar Pereira Rocha")){

                contandoRuaGilmarPereiraRochaVilaFatimaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. João Pimenta de Carvalho")){

                contandoRuaJoaoPimentaCarvalhoVilaFatimaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Joaquim Fernandes")){

                contandoRuaJoaquimFernandesoVilaFatimaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. José Augusto")){

                contandoRuaJoseAugustoVilaFatimaRoubo2019++;


            }

        }


    }

    public void ruasBairroPandeirosTodosAnos(){



        if (procuraBairro.contains("Pandeiros") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("Rua Joaquim Barbosa Gobira")){

                contandoRuaJoaquimBarbosaGobiraPandeirosRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Rua Maria Moreira")){

                contandoRuaMariaMoreiraPandeirosRouboTodosAnos++;


            }


        }


    }

    public void ruasBairroPandeiros2019() {


        if (procuraBairro.contains("Pandeiros") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {




            if (b.getAlertaRua().equals("Rua Joaquim Barbosa Gobira")){

                contandoRuaJoaquimBarbosaGobiraPandeirosRoubo2019++;


            }

            if (b.getAlertaRua().equals("Rua Maria Moreira")){

                contandoRuaMariaMoreiraPandeirosRoubo2019++;


            }

        }


    }

    public void ruasBairroVilaPaulaTodosAnos(){






        if (procuraBairro.contains("Vila Paula") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){




            if (b.getAlertaRua().equals("Av. São Francisco")){

                contandoRuaSaoFranciscoVilaPaulaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Tv. José Nunes")){

                contandoTvJoseNunesVilaPaulaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("Tv. Júlio de Moura")){

                contandoTvJulioMouraVilaPaulaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Leonel Nogueira Neto")){

                contandoRuaLeonelNogueiraNetoVilaPaulaRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. Anizio Jose Da Rocha")){

                contandoRuaAnizioJoseRochaVilaPaulaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Júlio de Moura")){

                contandoRuaJulioMouraVilaPaulaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. José Nunes")){

                contandoRuaJoseNunesVilaPaulaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Sebastião Ferreira Lima")){

                contandoRuaSebastiaoFerreiraLimaVilaPaulaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. Srg. Mozar")){

                contandoRuaSrgMozarVilaPaulaRouboTodosAnos++;


            }

        }


    }

    public void ruasBairroVilaPaula2019() {


        if (procuraBairro.contains("Vila Paula") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {






            if (b.getAlertaRua().equals("Av. São Francisco")){

                contandoRuaSaoFranciscoVilaPaulaRoubo2019++;


            }

            if (b.getAlertaRua().equals("Tv. José Nunes")){

                contandoTvJoseNunesVilaPaulaRoubo2019++;


            }

            if (b.getAlertaRua().equals("Tv. Júlio de Moura")){

                contandoTvJulioMouraVilaPaulaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Leonel Nogueira Neto")){

                contandoRuaLeonelNogueiraNetoVilaPaulaRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. Anizio Jose Da Rocha")){

                contandoRuaAnizioJoseRochaVilaPaulaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Júlio de Moura")){

                contandoRuaJulioMouraVilaPaulaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. José Nunes")){

                contandoRuaJoseNunesVilaPaulaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Sebastião Ferreira Lima")){

                contandoRuaSebastiaoFerreiraLimaVilaPaulaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. Srg. Mozar")){

                contandoRuaSrgMozarVilaPaulaRoubo2019++;


            }
        }

    }

    public void ruasBairroVilaVerdeTodosAnos(){






        if (procuraBairro.contains("Vila Verde") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("Av. Mal. Deodoro da Fonseca")){

                contandoAvMalDeodoroFonsecaVilaVerdeRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. A")){

                contandoRAVilaVerdeRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. do Aterro")){

                contandoRuaAterroVilaVerdeRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBVilaVerdeRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDVilaVerdeRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. O")){

                contandoRuaOVilaVerdeRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. R")){

                contandoRuaRVilaVerdeRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. T")){

                contandoRuaTVilaVerdeRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. U")){

                contandoRuaUVilaVerdeRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. V")){

                contandoRuaVVilaVerdeRouboTodosAnos++;


            }


        }

    }

    public void ruasBairroVilaVerde2019() {


        if (procuraBairro.contains("Vila Verde") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {





            if (b.getAlertaRua().equals("Av. Mal. Deodoro da Fonseca")){

                contandoAvMalDeodoroFonsecaVilaVerdeRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. A")){

                contandoRAVilaVerdeRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. do Aterro")){

                contandoRuaAterroVilaVerdeRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBVilaVerdeRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDVilaVerdeRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. O")){

                contandoRuaOVilaVerdeRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. R")){

                contandoRuaRVilaVerdeRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. T")){

                contandoRuaTVilaVerdeRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. U")){

                contandoRuaUVilaVerdeRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. V")){

                contandoRuaVVilaVerdeRoubo2019++;


            }

        }


    }

    public void ruasBairroVilaVianaTodosAnos(){







        if (procuraBairro.contains("Vila Viana") &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAVilaVianaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. Arthur Lopes Pimenta")){

                contandoRuaArthurLopesPimentaVilaVianaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBVilaVianaRouboTodosAnos++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDVilaVianaRouboTodosAnos++;


            }



            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEVilaVianaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. F")){

                contandoRuaFVilaVianaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. G")){

                contandoRuaGVilaVianaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. R")){

                contandoRuaRVilaVianaRouboTodosAnos++;


            }


            if (b.getAlertaRua().equals("R. X")){

                contandoRuaXVilaVianaRouboTodosAnos++;


            }

        }

    }

    public void ruasBairroVilaViana2019() {


        if (procuraBairro.contains("Vila Viana") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))) {





            if (b.getAlertaRua().equals("R. A")){

                contandoRuaAVilaVianaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. Arthur Lopes Pimenta")){

                contandoRuaArthurLopesPimentaVilaVianaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. B")){

                contandoRuaBVilaVianaRoubo2019++;


            }

            if (b.getAlertaRua().equals("R. D")){

                contandoRuaDVilaVianaRoubo2019++;


            }



            if (b.getAlertaRua().equals("R. E")){

                contandoRuaEVilaVianaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. F")){

                contandoRuaFVilaVianaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. G")){

                contandoRuaGVilaVianaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. R")){

                contandoRuaRVilaVianaRoubo2019++;


            }


            if (b.getAlertaRua().equals("R. X")){

                contandoRuaXVilaVianaRoubo2019++;


            }

        }


    }

    public void ruasBairroJadeteTodosAnos(){



        if (procuraBairro.contains("Vila Jadete") && (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



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



    }

    public void ruasBairroJadete2019(){



        if (procuraBairro.contains("Vila Jadete") && procuraAno.contains(anoProcura2019) &&  (b.getStatus().equals("Roubada")|| b.getStatus().equals("Furtada"))){



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

    }

}
