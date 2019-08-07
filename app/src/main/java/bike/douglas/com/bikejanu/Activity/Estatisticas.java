package bike.douglas.com.bikejanu.Activity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import bike.douglas.com.bikejanu.Fragments.GraficoAnoBarraFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoAnoLinhaFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoBairroBarraFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoBairroLinhaFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoHorarioAnoFragment;

import bike.douglas.com.bikejanu.Fragments.GraficoHorarioBarraFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoHorarioGeralPizzaFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoHorarioLinhaFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoPizzaTotalBikesFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoRuaBarraFragment;
import bike.douglas.com.bikejanu.Fragments.GraficoRuaLinhaFragment;
import bike.douglas.com.bikejanu.Fragments.MapaCalorFragment;
import bike.douglas.com.bikejanu.Fragments.MapaFragment;
import bike.douglas.com.bikejanu.R;




public class Estatisticas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


     public  int resposta = 0;
     public  static int  myHorientacion=0;
     public  static int myItemMap=0;

     Menu menu1;
     Menu menu2;





    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int myInt = savedInstanceState.getInt("MyInt");
        int myMap = savedInstanceState.getInt("MyMap");
        int myResp = savedInstanceState.getInt("MyResposta");
        int myUnir = savedInstanceState.getInt("MyUnir");
      //  Toast.makeText(Estatisticas.this, "MYiNT"+myInt, Toast.LENGTH_LONG).show();

        myHorientacion = myInt;
        myItemMap = myMap;
        resposta = myResp;




    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






                FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction transaction = fragmentManager.beginTransaction();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


   //     Toast.makeText(Estatisticas.this, "horientacion"+myHorientacion, Toast.LENGTH_LONG).show();





        if(myHorientacion == 0  ){

              transaction.replace(R.id.conteinerFragmentos,new GraficoAnoBarraFragment()).commit();


        }





             myHorientacion = 10;
             resposta=1;



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


        getMenuInflater().inflate(R.menu.estatisticas, menu);

          menu2 = menu;
        // Inflate the menu; this adds items to the action bar if it is present.



        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        FragmentManager fragmentManager = getSupportFragmentManager();
       FragmentTransaction transaction = fragmentManager.beginTransaction();

        int idMenu = item.getItemId();






        if (idMenu == R.id.action_barras && resposta == 1) {


            transaction.replace(R.id.conteinerFragmentos, new GraficoAnoBarraFragment()).commit();


        } else if (idMenu == R.id.action_linhas && resposta == 1) {

            transaction.replace(R.id.conteinerFragmentos, new GraficoAnoLinhaFragment()).commit();


        }






   if(resposta==2){

       if (idMenu == R.id.action_barras && resposta == 2) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoBairroBarraFragment()).commit();



       } else if (idMenu == R.id.action_linhas && resposta == 2) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoBairroLinhaFragment()).commit();



       }


   }


   if(resposta == 3){


       if (idMenu == R.id.action_barras && resposta == 3) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoRuaBarraFragment()).commit();



       } else if (idMenu == R.id.action_linhas && resposta == 3) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoRuaLinhaFragment()).commit();



       }


   }


   if (resposta == 4){





       if (idMenu == R.id.action_barras && resposta == 4) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoHorarioBarraFragment()).commit();



       } else if (idMenu == R.id.action_linhas && resposta == 4) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoHorarioLinhaFragment()).commit();



       }else if (idMenu == R.id.action_pizza && resposta == 4) {

           transaction.replace(R.id.conteinerFragmentos, new GraficoHorarioGeralPizzaFragment()).commit();


       }

   }



        if( resposta == 5 ){





            if(idMenu == R.id.action_mapaPontos && resposta == 5){

                transaction.replace(R.id.conteinerFragmentos,new MapaFragment()).commit();



            }   else if(idMenu == R.id.action_mapaCalor && resposta == 5){


                transaction.replace(R.id.conteinerFragmentos,new MapaCalorFragment()).commit();



            }



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
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        int  id = item.getItemId();



         if (id == R.id.nav_grafico_ano  ) {




             menu1 = menu2;
             MenuItem visivelMapaCalor = menu1.findItem(R.id.action_mapaCalor);
             visivelMapaCalor.setVisible(false);



             MenuItem visivel = menu1.findItem(R.id.action_mapaPontos);
             visivel.setVisible(false);




             MenuItem visivelGraficoBarras = menu1.findItem(R.id.action_barras);
             visivelGraficoBarras.setVisible(true);



             MenuItem visivelGraficoLinhas = menu1.findItem(R.id.action_linhas);
             visivelGraficoLinhas.setVisible(true);

             MenuItem visivelGraficoPizza = menu1.findItem(R.id.action_pizza);
             visivelGraficoPizza.setVisible(false);

             myItemMap = 0;
            transaction.replace(R.id.conteinerFragmentos,new GraficoAnoBarraFragment()).commit();

            resposta=1;




        } else if (id == R.id.nav_grafico_bairro ) {


             menu1 = menu2;
             MenuItem visivelMapaCalor = menu1.findItem(R.id.action_mapaCalor);
             visivelMapaCalor.setVisible(false);



             MenuItem visivel = menu1.findItem(R.id.action_mapaPontos);
             visivel.setVisible(false);




             MenuItem visivelGraficoBarras = menu1.findItem(R.id.action_barras);
             visivelGraficoBarras.setVisible(true);



             MenuItem visivelGraficoLinhas = menu1.findItem(R.id.action_linhas);
             visivelGraficoLinhas.setVisible(true);


             MenuItem visivelGraficoPizza = menu1.findItem(R.id.action_pizza);
             visivelGraficoPizza.setVisible(false);


             myItemMap = 0;
            transaction.replace(R.id.conteinerFragmentos,new GraficoBairroBarraFragment()).commit();
            resposta=2;

        } else if (id == R.id.nav_grafico_ruas) {


             menu1 = menu2;
             MenuItem visivelMapaCalor = menu1.findItem(R.id.action_mapaCalor);
             visivelMapaCalor.setVisible(false);



             MenuItem visivel = menu1.findItem(R.id.action_mapaPontos);
             visivel.setVisible(false);




             MenuItem visivelGraficoBarras = menu1.findItem(R.id.action_barras);
             visivelGraficoBarras.setVisible(true);



             MenuItem visivelGraficoLinhas = menu1.findItem(R.id.action_linhas);
             visivelGraficoLinhas.setVisible(true);

             MenuItem visivelGraficoPizza = menu1.findItem(R.id.action_pizza);
             visivelGraficoPizza.setVisible(false);



             myItemMap = 0;
             transaction.replace(R.id.conteinerFragmentos,new GraficoRuaBarraFragment()).commit();
             resposta=3;





        } else if (id == R.id.nav_grafico_Horario) {




             menu1 = menu2;
             MenuItem visivelMapaCalor = menu1.findItem(R.id.action_mapaCalor);
             visivelMapaCalor.setVisible(false);



             MenuItem visivel = menu1.findItem(R.id.action_mapaPontos);
             visivel.setVisible(false);




             MenuItem visivelGraficoBarras = menu1.findItem(R.id.action_barras);
             visivelGraficoBarras.setVisible(true);



             MenuItem visivelGraficoLinhas = menu1.findItem(R.id.action_linhas);
             visivelGraficoLinhas.setVisible(true);

             MenuItem visivelGraficoPizza = menu1.findItem(R.id.action_pizza);
             visivelGraficoPizza.setVisible(true);



             myItemMap = 0;

             transaction.replace(R.id.conteinerFragmentos,new GraficoHorarioBarraFragment()).commit();
             resposta=4;


         } else if (id == R.id.nav_grafico_Horario_ano) {


             menu1 = menu2;
             MenuItem visivelMapaCalor = menu1.findItem(R.id.action_mapaCalor);
             visivelMapaCalor.setVisible(false);


             MenuItem visivel = menu1.findItem(R.id.action_mapaPontos);
             visivel.setVisible(false);


             MenuItem visivelGraficoBarras = menu1.findItem(R.id.action_barras);
             visivelGraficoBarras.setVisible(false);


             MenuItem visivelGraficoLinhas = menu1.findItem(R.id.action_linhas);
             visivelGraficoLinhas.setVisible(false);

             MenuItem visivelGraficoPizza = menu1.findItem(R.id.action_pizza);
             visivelGraficoPizza.setVisible(false);


             myItemMap = 0;
             transaction.replace(R.id.conteinerFragmentos, new GraficoHorarioAnoFragment()).commit();
             resposta = 6;

         } else if(id == R.id.nav_dados_bikes){

             menu1 = menu2;
             MenuItem visivelMapaCalor = menu1.findItem(R.id.action_mapaCalor);
             visivelMapaCalor.setVisible(false);



             MenuItem visivelMapaPontos = menu1.findItem(R.id.action_mapaPontos);
             visivelMapaPontos.setVisible(false);




             MenuItem visivelGraficoBarras = menu1.findItem(R.id.action_barras);
             visivelGraficoBarras.setVisible(false);



             MenuItem visivelGraficoLinhas = menu1.findItem(R.id.action_linhas);
             visivelGraficoLinhas.setVisible(false);


             MenuItem visivelGraficoPizza = menu1.findItem(R.id.action_pizza);
             visivelGraficoPizza.setVisible(false);



             transaction.replace(R.id.conteinerFragmentos,new GraficoPizzaTotalBikesFragment()).commit();


          }  else if (id == R.id.nav_map) {


             menu1 = menu2;
             MenuItem visivelMapaCalor = menu1.findItem(R.id.action_mapaCalor);
             visivelMapaCalor.setVisible(true);



             MenuItem visivelMapaPontos = menu1.findItem(R.id.action_mapaPontos);
             visivelMapaPontos.setVisible(true);




             MenuItem visivelGraficoBarras = menu1.findItem(R.id.action_barras);
             visivelGraficoBarras.setVisible(false);



             MenuItem visivelGraficoLinhas = menu1.findItem(R.id.action_linhas);
             visivelGraficoLinhas.setVisible(false);

             MenuItem visivelGraficoPizza = menu1.findItem(R.id.action_pizza);
             visivelGraficoPizza.setVisible(false);

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
        savedInstanceState.putInt("MyResposta",  resposta);


    }


    @Override
    public void finish() {
        myHorientacion = 0;
        super.finish();
    }


}
