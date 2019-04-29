package bike.douglas.com.bikejanu.Fragments;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.CadastroUsuario;
import bike.douglas.com.bikejanu.Activity.ConfirmarSenha;
import bike.douglas.com.bikejanu.Activity.DadosBike;
import bike.douglas.com.bikejanu.Activity.EditarUsuario;
import bike.douglas.com.bikejanu.Activity.Estatisticas;
import bike.douglas.com.bikejanu.Activity.MainActivity;
import bike.douglas.com.bikejanu.Activity.Upload;
import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Adapter.UsuarioAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

public class AreaUsuario extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private FirebaseAuth usuarioFirebase;
    private FirebaseUser usuario;
    private ImageView btnmais;
    private ImageView ImagemUsuario;
    private  static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imagemPerfil;
    private Uri uriImagem;

    private TextView nomeUsuario;


    DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;



    private StorageReference storageReference;

    private ListView listViewDados;



    public List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
    private List<Bike> listabikes = new ArrayList<Bike>();


    private ArrayAdapter <Bike> arrayAdapterBike;

    private ArrayAdapter <Usuarios> arrayAdapterUsuarios;

   // private Usuarios usuarios;

  //  private AlertDialog alerta;




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








       //Recuperar bikes do firebase

        final  Preferencias preferencias = new Preferencias(AreaUsuario.this);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();







        usuarioFirebase = Configuracao_Firebase.getFirebaseAutenticacao();
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
        btnmais =  (ImageView) findViewById(R.id.btnmaisID);
        listViewDados = (ListView) findViewById(R.id.listaBikesID);


        // lista todas as bikes do usuario

        listaBikes();










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


            //final  ImageView fotoUsuarioLogado = (ImageView)findViewById(R.id.ImagemUsuarioID);
           //   fotoUsuarioLogado.setImageURI(Uri.parse("1555035815608.jpg"));




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

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_galeria) {

            abrirFotos();

        } else if (id == R.id.nav_bike) {





            startActivity(new Intent(AreaUsuario.this, Consultar_Bike.class));


        } else if (id == R.id.nav_estatisticas) {

            startActivity(new Intent(AreaUsuario.this, Estatisticas.class));


        } else if (id == R.id.nav_configuracao) {

               // excluirUsuario();

            startActivity(new Intent(AreaUsuario.this, CadastroUsuario.class));


        } else if (id == R.id.nav_editar_perfil) {



            startActivity(new Intent(AreaUsuario.this, ConfirmarSenha.class));

             //   recuperaUsuarios();




        }else if (id == R.id.nav_sair) {
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




    public void recuperaUsuarios(){


        // recupera usuario

        final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


            String email = user1.getEmail();

            // converte o email pra base 64
            String identificadorUsuario= Base64Custom.codificarBase64(email);



         DatabaseReference UsuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);


    UsuarioReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
       Usuarios dados = dataSnapshot.getValue(Usuarios.class);




        Bundle params = new Bundle();

        // passa dados  para a tela editar usuario


        params.putString("nomeUsuario",                        dados.getNome());
        params.putString("telefoneUsuario",                    dados.getTelefone());
        params.putString("emailUsuario",                       dados.getEmail());
        params.putString("confirmaremailUsuario",              dados.getEmail());
        params.putString("senhaUsuario",                       dados.getSenha());
        params.putString("confirmarsenhaUsuario",              dados.getSenha());


        Intent intent = new Intent(AreaUsuario.this, EditarUsuario.class);
        intent.putExtras(params);
        startActivity(intent);



    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});




    }



    public void BuscaUsuriosParaPerfil(){


        // recupera usuario



    }





        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null){


                uriImagem   = data.getData();

                StorageReference filePath = storageReference.child("FotosPerfil").child(uriImagem.getLastPathSegment());

                Picasso.with(this)
                        .load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/FotosPerfil%2F1555450560048.jpg?alt=media&token=99f2467a-2ab5-4abf-b03c-ad52747eb709")
                        .into(imagemPerfil);
                        imagemPerfil.setImageURI(uriImagem);

            }
        }



}





