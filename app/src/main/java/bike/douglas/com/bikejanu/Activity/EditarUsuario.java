package bike.douglas.com.bikejanu.Activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kbeanie.multipicker.api.CacheLocation;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.UsuarioAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.DAO.UsuarioDAO;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;
import bike.douglas.com.bikejanu.Utilidades.Constantes;
import de.hdodenhof.circleimageview.CircleImageView;

 public  class  EditarUsuario extends AppCompatActivity {


     DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference();


     //foto perfil

     private CircleImageView fotoPerfil;


     private Uri fotoPerfilUri;

     int d = 0;


     private static final int TIMER_RUNTINME = 100000;
     private boolean mbActive;
     private ProgressBar progressBar;
     private TextView criando;


     private EditText nome;
     private TextView email;
     private TextView confirmaremail;
     private TextInputEditText senha;
     private TextInputEditText confirmarsenha;
     private EditText telefone;
     private TextView txtNumeroPm;
     private TextView numeroValidador;


     private TextView numeroPm;

     //  private EditText  nascimento;
     private Button botaocadastrar;


     private Usuarios usuarios;

     int militarValidado = 0;


     private List<Usuarios> listaUsuario = new ArrayList<Usuarios>();
     private ArrayAdapter<Usuarios> arrayAdapterUsuario;
     private ListView listViewDados;


     private FirebaseAuth autenticacao;
     private StorageReference storageReference;
     private DatabaseReference firebase;
     private DatabaseReference firebaseMilitar;

     String fotoUsuario;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_editar_usuario);


         FirebaseApp.initializeApp(this);


         storageReference = FirebaseStorage.getInstance().getReference("ImagensUsuarios");


         autenticacao = FirebaseAuth.getInstance();

         FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


         String emaill = user1.getEmail();

         // converte o email pra base 64
         String identificadorUsuario = Base64Custom.codificarBase64(emaill);


         DatabaseReference usuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);


         usuarioReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                 Usuarios dados = dataSnapshot.getValue(Usuarios.class);

                 CircleImageView imagemPerfil = (CircleImageView) findViewById(R.id.imagemPerfilID01);

                 if (dados.getFotoPerfilURL() == null) {


                     Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/imagem_perfil.jpg?alt=media&token=85252837-3ac9-4931-ac58-df3e78e30875").into(imagemPerfil);


                 } else {


                     Glide.with(getApplicationContext()).load(dados.getFotoPerfilURL()).into(imagemPerfil);

                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


         //fotoPerfil
         fotoPerfil = (CircleImageView) findViewById(R.id.imagemPerfilID01);


         fotoPerfil.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {


                 Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 startActivityForResult(intent, 1);


             }
         });


         //Instânciar objetos
         listaUsuario = new ArrayList<>();

         arrayAdapterUsuario = new UsuarioAdapter(EditarUsuario.this, (ArrayList<Usuarios>) listaUsuario);
         //   listViewDados.setAdapter(arrayAdapterUsuario);

         //registerForContextMenu(listViewDados);


         /// da imagem

         storageReference = FirebaseStorage.getInstance().getReference();


         //  databaseReference = FirebaseDatabase.getInstance().getReference("uploads");


         nome = (EditText) findViewById(R.id.NomeID01);
         email = (TextView) findViewById(R.id.EmailtextID01);
         confirmaremail = (TextView) findViewById(R.id.confirmarEmailID01);
         senha = (TextInputEditText) findViewById(R.id.senhaID01);
         confirmarsenha = (TextInputEditText) findViewById(R.id.confirmarSenhaID01);
         telefone = (EditText) findViewById(R.id.telefoneeID01);
         //  nascimento = (EditText)findViewById(R.id.dataID);


         numeroPm = (TextView) findViewById(R.id.numeroPmID01);
         txtNumeroPm = (TextView) findViewById(R.id.txtNumeroPmID01);


         botaocadastrar = (Button) findViewById(R.id.btnEditarID01);


         progressBar = (ProgressBar) findViewById(R.id.progressBarCdastroID01);
         // fundo       = (ImageView)findViewById(R.id.fundoID01);
         criando = (TextView) findViewById(R.id.criandoID01);


         numeroValidador = (TextView) findViewById(R.id.digitoValidadorEditarID);

         mascaras();


         // rebece o dados do Bike Adapter por parametro
         Intent intent = getIntent();

         if (intent != null) {

             Bundle params = intent.getExtras();

             if (params != null) {

                 //dados do nome
                 String nomeUsuario = params.getString("nomeUsuario");
                 TextView nomeUsuarioText = (TextView) findViewById(R.id.NomeID01);
                 nomeUsuarioText.setText(nomeUsuario);

                 //dados do telefone
                 String telefoneUsuario = params.getString("telefoneUsuario");
                 TextView telefoneUsuarioText = (TextView) findViewById(R.id.telefoneeID01);
                 telefoneUsuarioText.setText(telefoneUsuario);


                 // dados do email
                 String emailUsuario = params.getString("emailUsuario");
                 TextView emailUsuarioText = (TextView) findViewById(R.id.EmailtextID01);
                 emailUsuarioText.setText(emailUsuario);


                 // dados do email
                 String confirmaremailUsuario = params.getString("confirmaremailUsuario");
                 TextView confirmaremailUsuarioText = (TextView) findViewById(R.id.confirmarEmailID01);
                 confirmaremailUsuarioText.setText(confirmaremailUsuario);


                 // dados da senha
                 String cor = params.getString("senhaUsuario");
                 TextView corText = (TextView) findViewById(R.id.senhaID01);
                 corText.setText(cor);

                 // dados  confirmarsenha
                 String confirmarsenhaUsuario = params.getString("confirmarsenhaUsuario");
                 TextView confirmarsenhaUsuarioText = (TextView) findViewById(R.id.confirmarSenhaID01);
                 confirmarsenhaUsuarioText.setText(confirmarsenhaUsuario);


                 // dados  numeroPm
                 String numeroPmUsuario = params.getString("numeroPm");
                 TextView numeroPmUsuarioText = (TextView) findViewById(R.id.numeroPmID01);
                 numeroPmUsuarioText.setText(numeroPmUsuario);


                 // dados  numeroPm
                 String validarUsuario = params.getString("validarUsuario");
                 TextView validarUsuarioText = (TextView) findViewById(R.id.digitoValidadorEditarID);
                 validarUsuarioText.setText(validarUsuario);


                 fotoUsuario = params.getString("fotoUsuario");


                 if (validarUsuarioText.getText().toString().equals("01")) {

                     militarValidado = 1;

                     txtNumeroPm.setVisibility(View.VISIBLE);
                     numeroPm.setVisibility(View.VISIBLE);

                 } else {

                     militarValidado = 2;

                     txtNumeroPm.setVisibility(View.INVISIBLE);
                     numeroPm.setVisibility(View.INVISIBLE);

                 }

             }


         }









         botaocadastrar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if (militarValidado == 1) {

                     if (!numeroPm.getText().toString().equals("") && !nome.getText().toString().equals("") && !email.getText().toString().equals("") &&
                             !confirmaremail.getText().toString().equals("") && !senha.getText().toString().equals("") &&
                             !confirmarsenha.getText().toString().equals("") && !telefone.getText().toString().equals("")) {


                         if (senha.getText().toString().equals(confirmarsenha.getText().toString())) {
                             if (email.getText().toString().equals(confirmaremail.getText().toString())) {


                                 progressBar.setVisibility(View.VISIBLE);
                                 //   fundo.setVisibility(View.VISIBLE);
                                 criando.setVisibility(View.VISIBLE);

                                 progressBar();
                                 inicializarElementos();
                                 editarUsuario();



                             } else {

                                 Toast.makeText(EditarUsuario.this, "Os E-mail não são correspondentes", Toast.LENGTH_LONG).show();
                                 email.requestFocus();
                                 progressBar.setVisibility(View.GONE);
                                 // fundo.setVisibility(View.GONE);
                                 criando.setVisibility(View.GONE);

                             }

                         } else {


                             Toast.makeText(EditarUsuario.this, "As senhas não são correspondentes", Toast.LENGTH_LONG).show();
                             senha.requestFocus();
                             progressBar.setVisibility(View.GONE);
                             //  fundo.setVisibility(View.GONE);
                             criando.setVisibility(View.GONE);

                         }

                     } else {


                         Toast.makeText(EditarUsuario.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();

                         progressBar.setVisibility(View.GONE);
                         //  fundo.setVisibility(View.GONE);
                         criando.setVisibility(View.GONE);


                     }





                 } else {


                     if (!nome.getText().toString().equals("") && !email.getText().toString().equals("") &&
                             !confirmaremail.getText().toString().equals("") && !senha.getText().toString().equals("") &&
                             !confirmarsenha.getText().toString().equals("") && !telefone.getText().toString().equals("")) {


                         if (senha.getText().toString().equals(confirmarsenha.getText().toString())) {
                             if (email.getText().toString().equals(confirmaremail.getText().toString())) {


                                 progressBar.setVisibility(View.VISIBLE);
                                 //   fundo.setVisibility(View.VISIBLE);
                                 criando.setVisibility(View.VISIBLE);


                                 progressBar();
                                 inicializarElementos();
                                 editarUsuario();



                             } else {

                                 Toast.makeText(EditarUsuario.this, "Os E-mail não são correspondentes", Toast.LENGTH_LONG).show();
                                 email.requestFocus();
                                 progressBar.setVisibility(View.GONE);
                                 //fundo.setVisibility(View.GONE);
                                 criando.setVisibility(View.GONE);


                             }

                         } else {


                             Toast.makeText(EditarUsuario.this, "As senhas não são correspondentes", Toast.LENGTH_LONG).show();
                             senha.requestFocus();
                             progressBar.setVisibility(View.GONE);
                             //   fundo.setVisibility(View.GONE);
                             criando.setVisibility(View.GONE);

                         }

                     } else {


                         Toast.makeText(EditarUsuario.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();

                         progressBar.setVisibility(View.GONE);
                         //  fundo.setVisibility(View.GONE);
                         criando.setVisibility(View.GONE);


                     }

                 }

             }

         });


     }



     private void abrirAreaUsuario() {

         Intent intent = new Intent(EditarUsuario.this, AreaUsuario.class);
         startActivity(intent);
         finish();

     }

     private void mascaras() {

         SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN)NNNNNNNNN");
         MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
         telefone.addTextChangedListener(maskTelefone);


     }







     public void progressBar() {


         final Thread timerTheread = new Thread() {


             @Override
             public void run() {

                 mbActive = true;
                 try {

                     int waited = 0;

                     while (mbActive && (waited < TIMER_RUNTINME)) {

                         sleep(200);

                         if (mbActive) {

                             waited += 200;
                             updateProgress(waited);

                         }


                     }

                 } catch (InterruptedException e) {


                 } finally {


                     // Toast.makeText(CadastroBike.this, "Tudo pronto", Toast.LENGTH_SHORT).show();


                 }

             }


         };
         timerTheread.start();


     }


     public void updateProgress(final int timePassed) {

         if (null != progressBar) {

             final int progress = progressBar.getMax() * timePassed / TIMER_RUNTINME;
             progressBar.setProgress(progress);

         }

     }


     private void inicializarElementos() {

         usuarios = new Usuarios();
         usuarios.setNome(nome.getText().toString());
         usuarios.setEmail(email.getText().toString());
         usuarios.setSenha(senha.getText().toString());
         usuarios.setTelefone(telefone.getText().toString());

         // usuarios.setImagem(imagemPerfil.getScaleType().toString());

         if (militarValidado == 1) {

             usuarios.setNumeroPm(numeroPm.getText().toString());
             usuarios.setDigitoValidador("01");


         } else {


             usuarios.setDigitoValidador("02");


         }
     }


     private void editarUsuario() {


         FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


         if (user != null) {

             String email = user.getEmail();

             // converte o email pra base 64
             final String identificadorUsuario = Base64Custom.codificarBase64(email);


             if (fotoPerfilUri != null) {

                 progressBar.setVisibility(View.VISIBLE);
                 criando.setVisibility(View.VISIBLE);


                 final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagemPerfil");
                 UploadTask uploadTask = ref.putFile(fotoPerfilUri);


                 Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                     @Override
                     public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                         if (!task.isSuccessful()) {
                             throw task.getException();
                         }

                         return ref.getDownloadUrl();
                     }
                 }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                     @Override
                     public void onComplete(@NonNull Task<Uri> task) {
                         if (task.isSuccessful()) {


                             Uri downloadUri = task.getResult();

                             if (militarValidado == 1) {


                                 usuarios.setFotoPerfilURL(downloadUri.toString());


                                 // EDITA a bike usuario
                                 firebase = Configuracao_Firebase.getFirebase().child("Militares");
                                 firebase.child(identificadorUsuario).setValue(usuarios);


                                 // EDITA a bike usuario
                                 firebase = Configuracao_Firebase.getFirebase().child("Usuarios");
                                 firebase.child(identificadorUsuario).setValue(usuarios);


                             } else {


                                 usuarios.setFotoPerfilURL(downloadUri.toString());

                                 // EDITA a bike usuario
                                 firebase = Configuracao_Firebase.getFirebase().child("Usuarios");
                                 firebase.child(identificadorUsuario).setValue(usuarios);


                             }


                         }


                     }
                 }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                     @Override
                     public void onSuccess(Uri uri) {


                  dialog();


                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {

                         Toast.makeText(EditarUsuario.this, "Falha na conexão!", Toast.LENGTH_LONG).show();
                         progressBar.setVisibility(View.GONE);
                         criando.setVisibility(View.GONE);

                     }
                 });

             }


             if (fotoPerfilUri == null) {

                 progressBar.setVisibility(View.VISIBLE);
                 criando.setVisibility(View.VISIBLE);


                 usuarios.setFotoPerfilURL(fotoUsuario);


                 if (militarValidado == 1) {

                     // EDITA a bike usuario
                     firebase = Configuracao_Firebase.getFirebase().child("Militares");
                     firebase.child(identificadorUsuario).setValue(usuarios);


                     // EDITA a bike usuario
                     firebase = Configuracao_Firebase.getFirebase().child("Usuarios");
                     firebase.child(identificadorUsuario).setValue(usuarios);


                 } else {


                     // EDITA a bike usuario
                     firebase = Configuracao_Firebase.getFirebase().child("Usuarios");
                     firebase.child(identificadorUsuario).setValue(usuarios);


                 }



                 progressBar.setVisibility(View.GONE);
                 criando.setVisibility(View.GONE);

                 dialog();


             }

         }

     }


     public void dialog() {




         AlertDialog.Builder alertaDialog = new AlertDialog.Builder(EditarUsuario.this);

         // configurando dialogo

         alertaDialog.setTitle("Confirmar Edição");


         alertaDialog.setMessage("Deseja Realmente editar o seu Perfil ?");
         // alertaDialog.setCancelable(false);


         //conf botões
         alertaDialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {



                 Toast.makeText(EditarUsuario.this, " Perfil alterado com sucesso", Toast.LENGTH_LONG).show();
                 abrirAreaUsuario();


             }
         });

         alertaDialog.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

                 Toast.makeText(EditarUsuario.this, " Edite Novamente", Toast.LENGTH_LONG).show();

             }
         });

         alertaDialog.create();
         alertaDialog.show();



     }




     private String getExtension(Uri uri){

         ContentResolver contentResolver = getContentResolver();
         MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
         return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);


         if(requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null) {

             fotoPerfilUri = data.getData();
             fotoPerfil.setImageURI(fotoPerfilUri);


         }
         }



 }

