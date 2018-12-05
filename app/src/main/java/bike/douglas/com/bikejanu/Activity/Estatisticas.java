package bike.douglas.com.bikejanu.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import bike.douglas.com.bikejanu.Fragments.GraficoAnoBarraFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoAnoLinhaFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoAnoMistoFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoBairroBarraFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoBairroLinhaFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoBairroMistoFragment;
import bike.douglas.com.bikejanu.Fragments.MapaCalorFragment;
import bike.douglas.com.bikejanu.Fragments.MapaFragment;
import bike.douglas.com.bikejanu.R;




public class Estatisticas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


     public  int resposta = 0;
     public  static int  myHorientacion=0;
     public  static int myItemMap=0;



    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int myInt = savedInstanceState.getInt("MyInt");
        int myMap = savedInstanceState.getInt("MyMap");
      //  Toast.makeText(Estatisticas.this, "MYiNT"+myInt, Toast.LENGTH_LONG).show();

        myHorientacion = myInt;
        myItemMap = myMap;


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


   //     Toast.makeText(Estatisticas.this, "horientacion"+myHorientacion, Toast.LENGTH_LONG).show();

        if(myHorientacion == 0){

              transaction.replace(R.id.conteinerFragmentos,new GraficoAnoBarraFragment()).commit();

        }

             myHorientacion=10;



}



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        if(myItemMap == 5 ){

            getMenuInflater().inflate(R.menu.opcoes_mapa, menu);

        }else
        {

            getMenuInflater().inflate(R.menu.estatisticas, menu);



        }
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }








    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        int idMenu = item.getItemId();



   if(resposta==1){


       if (idMenu == R.id.action_barras && resposta == 1) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoAnoBarraFragment()).commit();



       } else if (idMenu == R.id.action_linhas && resposta == 1) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoAnoLinhaFragment()).commit();



       } else if (idMenu == R.id.action_misto && resposta == 1) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoAnoMistoFragment()).commit();



       }


   }


   if(resposta==2){

       if (idMenu == R.id.action_barras && resposta == 2) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoBairroBarraFragment()).commit();



       } else if (idMenu == R.id.action_linhas && resposta == 2) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoBairroLinhaFragment()).commit();



       } else if (idMenu == R.id.action_misto && resposta == 2) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoBairroMistoFragment()).commit();



       }


   }

        if( resposta == 5  ){


            if(idMenu == R.id.action_barras && resposta == 5){

                transaction.replace(R.id.conteinerFragmentos,new MapaFragment()).commit();

            }   else if(idMenu == R.id.action_linhas && resposta == 5){


                transaction.replace(R.id.conteinerFragmentos,new MapaCalorFragment()).commit();


            }




         ///   item.setVisible(false);


        }




      //  resposta=0;
        //noinspection SimplifiableIfStatement




        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.



        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        int  id = item.getItemId();


         if (id == R.id.nav_grafico_ano  ) {

             myItemMap = 0;
            transaction.replace(R.id.conteinerFragmentos,new GraficoAnoBarraFragment()).commit();
            resposta=1;

        } else if (id == R.id.nav_grafico_bairro ) {
             myItemMap = 0;
            transaction.replace(R.id.conteinerFragmentos,new GraficoBairroBarraFragment()).commit();
            resposta=2;

        } else if (id == R.id.nav_grafico_ruas) {



        } else if (id == R.id.nav_share) {



        } else if (id == R.id.nav_map) {

             myItemMap = 5;
             transaction.replace(R.id.conteinerFragmentos,new MapaFragment()).commit();
             resposta = 5;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);


        savedInstanceState.putInt("MyInt", 10);
        savedInstanceState.putInt("MyMap",  myItemMap);

    }



}
