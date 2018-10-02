package bike.douglas.com.bikejanu.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.api.model.GetTokenResponse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.CadastroUsuario;
import bike.douglas.com.bikejanu.Activity.Dialogo_Personalizado;
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
    private FirebaseUser usuario;
    private ImageButton btnmais;
    private ImageView ImagemUsuario;
    private  static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imagemPerfil;
    private Uri uriImagem;
    private StorageReference storageReference;





    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private ListView listViewDados;


    private List<Bike> listabikes = new ArrayList<Bike>();
    private ArrayAdapter <Bike> arrayAdapterBike;

   // private Usuarios usuarios;

  //  private AlertDialog alerta;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_usuario);

        firebaseDatabase = FirebaseDatabase.getInstance();
    //  firebaseDatabase.setPersistenceEnabled(true);
       databaseReference = firebaseDatabase.getReference();



       //Recuperar bikes do firebase

        final  Preferencias preferencias = new Preferencias(AreaUsuario.this);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        usuarioFirebase = Configuracao_Firebase.getFirebaseAutenticacao();
        final TextView receceNome = (TextView) findViewById(R.id.nomeUsuarioID);
        ImagemUsuario = (ImageView) findViewById(R.id.ImagemUsuarioID);





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
        listViewDados = (ListView) findViewById(R.id.listaBikesID);



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

        } else if (id == R.id.nav_galeria) {

            abrirFotos();

        } else if (id == R.id.nav_bike) {


           // startActivity(new Intent(AreaUsuario.this, Consultar_Bike.class));


        } else if (id == R.id.nav_indices) {

         //   startActivity(new Intent(AreaUsuario.this, Consultar_Indice.class));

        } else if (id == R.id.nav_configuracao) {

                excluirUsuario();


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
        alertaDialog.setIcon(R.drawable.ic_action_exit);

        alertaDialog.setMessage("Deseja realmente sair ? ");
        alertaDialog.setCancelable(false);


        //conf botões
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
        listabikes = new ArrayList<>();

        arrayAdapterBike = new BikeAdapter(AreaUsuario.this, (ArrayList<Bike>) listabikes);
        listViewDados.setAdapter(arrayAdapterBike);

        registerForContextMenu(listViewDados);




        //Recuperar contatos do firebase
      //  Preferencias preferencias = new Preferencias(AreaUsuario.this);


        // recupera usuario pelo email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String name = user.getDisplayName();
            String email = user.getEmail();


            // converte o email pra base 64
            String identificadorUsuario = Base64Custom.codificarBase64(email);


            Toast.makeText(AreaUsuario.this, "Bem Vindo!  :", Toast.LENGTH_LONG).show();


            // escolhe os nós que vão ser listados
            databaseReference = Configuracao_Firebase.getFirebase()
                    .child("Bikes")
                    .child(identificadorUsuario);


             }



        //Listener para recuperar bikes
       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar lista

                   listabikes.clear();

                //Listar bikes
                for (DataSnapshot dados: dataSnapshot.getChildren() ){

                    Bike b = dados.getValue( Bike.class );
                    listabikes.add( b );


                }

                arrayAdapterBike.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public void excluirUsuario() {


            AlertDialog.Builder alertaDialog = new AlertDialog.Builder(AreaUsuario.this);

            // configurando dialogo

            alertaDialog.setTitle("Confirmar Exclusão");
            alertaDialog.setIcon(R.drawable.ic_action_exit);


            alertaDialog.setMessage("Deseja realmente excluir sua conta ? ");
            alertaDialog.setCancelable(false);


            // conf botões

            alertaDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                       deslogarUsuario();

                                    }
                                }
                            });


                    Toast.makeText(AreaUsuario.this, "Sua Conta Foi Excluida", Toast.LENGTH_LONG).show();


                }
            });

            alertaDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(AreaUsuario.this, "Operação Cancelada", Toast.LENGTH_LONG).show();


                }
            });

            alertaDialog.create();
            alertaDialog.show();



    }



    private void abrirFotos(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

}





