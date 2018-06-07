package bike.douglas.com.bikejanu.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.MainActivity;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Entidades.Usuarios;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.R;

public class AreaUsuario extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private FirebaseAuth usuarioFirebase;
    private ImageButton btnmais;




    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Bike> contatos;
    private DatabaseReference firebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_usuario);




        usuarioFirebase = Configuracao_Firebase.getFirebaseAutenticacao();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        usuarioFirebase = Configuracao_Firebase.getFirebaseAutenticacao();
        btnmais =  (ImageButton)findViewById(R.id.btnmaisID);

        btnmais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AreaUsuario.this,CadastroBike.class));
            }
        });

        // lista todas as bikes do usuario
        listaBikes();



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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.area_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.



        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_galeria) {

        } else if (id == R.id.nav_bike) {



        } else if (id == R.id.nav_indices) {

        } else if (id == R.id.nav_configuracao) {



        } else if (id == R.id.nav_sair) {
            caixaDialogoSair();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void deslogarUsuario(){

        usuarioFirebase.signOut();
        Intent intent = new Intent(AreaUsuario.this ,MainActivity.class);
        startActivity(intent);
        finish();


    }


    private void caixaDialogoSair(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(AreaUsuario.this);

        // configurando dialogo

        alertaDialog.setTitle("Sair");
        alertaDialog.setMessage("Deseja realmente sair ? ");
        alertaDialog.setCancelable(false);



        // conf botões

        alertaDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deslogarUsuario();


            }
        });

        alertaDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertaDialog.create();
        alertaDialog.show();
    }



    //lista todas as bikes dos usuarios

    public void listaBikes(){


        //Instânciar objetos
        contatos = new ArrayList<>();

        // Inflate the layout for this fragment


        //Monta listview e adapter
        listView = (ListView) findViewById(R.id.listaBikesID);

        ////////////////////////////////////////////////////////////////////

/*
        adapter = new ArrayAdapter(
                AreaUsuario.this,android.R.layout.simple_list_item_1,
                contatos
        );
*/

        ////////////////////////////////////////////
        adapter = new BikeAdapter(AreaUsuario.this,contatos);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);


        //Recuperar contatos do firebase
        Preferencias preferencias = new Preferencias(AreaUsuario.this);

// recupera usuario pelo email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String name = user.getDisplayName();
            String email = user.getEmail();

            // converte o email pra base 64
            String identificadorUsuario = Base64Custom.codificarBase64(email);

// escolhe os nós que vão ser listados
            firebase = Configuracao_Firebase.getFirebase()
                    .child("Bikes")
                    .child(identificadorUsuario);
        }

        //Listener para recuperar contatos
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar lista

                contatos.clear();

                //Listar contatos
                for (DataSnapshot dados: dataSnapshot.getChildren() ){

                    Bike contato = dados.getValue( Bike.class );
                    contatos.add( contato );


                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_bike,menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo  info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            switch (item.getItemId()){



                case R.id.statusBikeID:

                case R.id.editarBikeID:

                case R.id.excluirBikeID:





                    return true;
                    default:
                        return super.onContextItemSelected(item);

            }


       // return super.onContextItemSelected(item);

    }
}





