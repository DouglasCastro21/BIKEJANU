package bike.douglas.com.bikejanu.Fragments;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.ChecarUsuario;
import bike.douglas.com.bikejanu.Activity.ConfirmarSenha;
import bike.douglas.com.bikejanu.Activity.Estatisticas;
import bike.douglas.com.bikejanu.Activity.MainActivity;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AreaUsuario extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


public static String paraOndeVou="0";



    private FirebaseAuth usuarioFirebase;
    private FirebaseUser usuario;
    private ImageView btnmais;
    private TextView textoCadastrar;
    private ImageView imagemSeta;

    private  static final int PICK_IMAGE_REQUEST = 1;



    private TextView nomeUsuario;
    static   int d=0;


    DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

     private int validarUsuario=0;
    static private int validarTeste;
    private StorageReference storageReference;

    private ListView listViewDados;



    public List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
    private List<Bike> listabikes = new ArrayList<Bike>();


    private ArrayAdapter <Bike> arrayAdapterBike;

    private ArrayAdapter <Usuarios> arrayAdapterUsuarios;





private DatabaseReference firebase;
private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_usuario);

        firebaseDatabase = FirebaseDatabase.getInstance();
    //  firebaseDatabase.setPersistenceEnabled(true);
       databaseReference = firebaseDatabase.getReference();

        databaseReferenceUsuario = firebaseDatabase.getReference();


        storageReference = FirebaseStorage.getInstance().getReference();

        usuarioFirebase = Configuracao_Firebase.getFirebaseAutenticacao();










     //   Glide.with(AreaUsuario.this).load(Constantes.URL_FOTO_POR_DEFECTO_USUARIOS).into(fotoPerfil);

        FirebaseMessaging.getInstance().subscribeToTopic("userABC");









        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        String email = user1.getEmail();

        // converte o email pra base 64
        String identificadorUsuario= Base64Custom.codificarBase64(email);



        btnmais =  (ImageView) findViewById(R.id.btnmaisID);


        DatabaseReference UsuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);




        ImageView setaLista    = (ImageView) findViewById(R.id.setaListaID);
        ImageView imagemLista  = (ImageView) findViewById(R.id.bikeListaID);
        TextView  texto        = (TextView)  findViewById(R.id.textoSomeID);


        btnmais.setVisibility(View.GONE);
        setaLista.setVisibility(View.GONE);
        imagemLista.setVisibility(View.GONE);
        texto.setVisibility(View.GONE);




        UsuarioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    Usuarios dados = dataSnapshot.getValue(Usuarios.class);


                  //  Toast.makeText(AreaUsuario.this, "Digito validador = "+dados.getDigitoValidador(), Toast.LENGTH_LONG).show();


                    if(dados.getDigitoValidador().equals("01")){





                            ImageView setaLista    = (ImageView) findViewById(R.id.setaListaID);
                            ImageView imagemLista  = (ImageView) findViewById(R.id.bikeListaID);
                            TextView  texto        = (TextView)  findViewById(R.id.textoSomeID);


                            btnmais.setVisibility(View.GONE);
                            setaLista.setVisibility(View.GONE);
                            imagemLista.setVisibility(View.GONE);
                            texto.setVisibility(View.GONE);





                        Query query;


                        //Instânciar objetos
                        listabikes = new ArrayList<>();

                        arrayAdapterBike = new BikeAdapter(AreaUsuario.this, (ArrayList<Bike>) listabikes);
                        listViewDados.setAdapter(arrayAdapterBike);

                        registerForContextMenu(listViewDados);





                        //Recuperar contatos do firebase
                        //  Preferencias preferencias = new Preferencias(AreaUsuario.this);


                        // recupera usuario pelo email
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                        String email = user.getEmail();


                        // converte o email pra base 64
                        String identificadorUsuario = Base64Custom.codificarBase64(email);





                        // escolhe os nós que vão ser listados
                      query =  databaseReference.child("TodasBikes").orderByChild("status");






                        //Listener para recuperar bikes
                        query.addValueEventListener(new ValueEventListener() {
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






                    }else{


                        btnmais.setVisibility(View.VISIBLE);

                        //Instânciar objetos
                        listabikes = new ArrayList<>();

                        arrayAdapterBike = new BikeAdapter(AreaUsuario.this, (ArrayList<Bike>) listabikes);
                        listViewDados.setAdapter(arrayAdapterBike);

                        registerForContextMenu(listViewDados);





                        //Recuperar contatos do firebase
                        //  Preferencias preferencias = new Preferencias(AreaUsuario.this);


                        // recupera usuario pelo email
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                        String email = user.getEmail();


                        // converte o email pra base 64
                        String identificadorUsuario = Base64Custom.codificarBase64(email);





                        // escolhe os nós que vão ser listados
                        databaseReference = Configuracao_Firebase.getFirebase()
                                .child("Bikes")
                                .child(identificadorUsuario);






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

                                ImageView setaLista    = (ImageView) findViewById(R.id.setaListaID);
                                ImageView imagemLista  = (ImageView) findViewById(R.id.bikeListaID);
                                TextView  texto        = (TextView)  findViewById(R.id.textoSomeID);


                                if(arrayAdapterBike.getCount() >=1) {

                                    setaLista.setVisibility(View.GONE);
                                    imagemLista.setVisibility(View.GONE);
                                    texto.setVisibility(View.GONE);


                                }else{

                                    setaLista.setVisibility(View.VISIBLE);
                                    imagemLista.setVisibility(View.VISIBLE);
                                    texto.setVisibility(View.VISIBLE);

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }

                }


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }



        });





         // Toast.makeText(AreaUsuario.this, "Digitoooo validador = "+d, Toast.LENGTH_LONG).show();


        //Recuperar bikes do firebase

        final  Preferencias preferencias = new Preferencias(AreaUsuario.this);
        final  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();








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

        listViewDados = (ListView) findViewById(R.id.listaBikesID);





        // lista todas as bikes do usuario








     //   listaBikes();







        btnmais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AreaUsuario.this,CadastroBike.class));

            }
        });







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



      FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


        String email = user1.getEmail();

        // converte o email pra base 64
        String identificadorUsuario= Base64Custom.codificarBase64(email);



        DatabaseReference UsuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);




            UsuarioReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.exists()) {

                        Usuarios dados = dataSnapshot.getValue(Usuarios.class);




                        TextView nomeUsuarioLogadoText = (TextView) findViewById(R.id.nomeUsuarioI01D);
                        nomeUsuarioLogadoText.setText(dados.getNome());

                        TextView emailUsuarioLogadoText = (TextView) findViewById(R.id.emailUsuarioID);
                        emailUsuarioLogadoText.setText(dados.getEmail());




                         CircleImageView  fotoPerfil    = (CircleImageView) findViewById(R.id.imagemPerfilIID);

                         Glide.with(AreaUsuario.this).load(dados.getFotoPerfilURL()).into(fotoPerfil);






                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });







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

        if (id == R.id.nav_bike) {


          paraOndeVou ="1";

             startActivity(new Intent(AreaUsuario.this, Consultar_Bike.class));


         } else if (id == R.id.nav_estatisticas) {

            startActivity(new Intent(AreaUsuario.this, Estatisticas.class));


        } else if (id == R.id.nav_configuracao) {



          //  startActivity(new Intent(AreaUsuario.this, CadastroUsuario.class));

            startActivity(new Intent(AreaUsuario.this, ChecarUsuario.class));


        } else if (id == R.id.nav_editar_perfil) {



            startActivity(new Intent(AreaUsuario.this, ConfirmarSenha.class));


             //   recuperaUsuarios();




        }else if (id == R.id.nav_sair) {
            caixaDialogoSair();

        }else if(id == R.id.nav_excluir_usuario){

            enviarDadosParaTelaConfirmarSenha();


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


if(validarUsuario  ==1){





    //Instânciar objetos
    listabikes = new ArrayList<>();

    arrayAdapterBike = new BikeAdapter(AreaUsuario.this, (ArrayList<Bike>) listabikes);
    listViewDados.setAdapter(arrayAdapterBike);

    registerForContextMenu(listViewDados);





    //Recuperar contatos do firebase
    //  Preferencias preferencias = new Preferencias(AreaUsuario.this);


    // recupera usuario pelo email
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    String email = user.getEmail();


    // converte o email pra base 64
    String identificadorUsuario = Base64Custom.codificarBase64(email);





    // escolhe os nós que vão ser listados
    databaseReference = Configuracao_Firebase.getFirebase()
            .child("TodasBikes");






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


























}else{




    //Instânciar objetos
    listabikes = new ArrayList<>();

    arrayAdapterBike = new BikeAdapter(AreaUsuario.this, (ArrayList<Bike>) listabikes);
    listViewDados.setAdapter(arrayAdapterBike);

    registerForContextMenu(listViewDados);





    //Recuperar contatos do firebase
    //  Preferencias preferencias = new Preferencias(AreaUsuario.this);


    // recupera usuario pelo email
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    String email = user.getEmail();


    // converte o email pra base 64
    String identificadorUsuario = Base64Custom.codificarBase64(email);





    // escolhe os nós que vão ser listados
    databaseReference = Configuracao_Firebase.getFirebase()
            .child("Bikes")
            .child(identificadorUsuario);






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

            ImageView setaLista    = (ImageView) findViewById(R.id.setaListaID);
            ImageView imagemLista  = (ImageView) findViewById(R.id.bikeListaID);
            TextView  texto        = (TextView)  findViewById(R.id.textoSomeID);


            if(arrayAdapterBike.getCount() >=1) {

                setaLista.setVisibility(View.GONE);
                imagemLista.setVisibility(View.GONE);
                texto.setVisibility(View.GONE);


            }else{
                setaLista.setVisibility(View.VISIBLE);
                imagemLista.setVisibility(View.VISIBLE);
                texto.setVisibility(View.VISIBLE);

            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });











}





    }





    private void abrirFotos(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }



    public void enviarDadosParaTelaConfirmarSenha(){




                Bundle params = new Bundle();

                // passa dados  para a tela confirmar senha usuario


                params.putString("confirmarExclusao",                        "confirmado");


                Intent intent = new Intent(AreaUsuario.this, ConfirmarSenha.class);
                intent.putExtras(params);
                startActivity(intent);







            }








}





