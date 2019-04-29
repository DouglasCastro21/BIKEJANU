package bike.douglas.com.bikejanu.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
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

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.UsuarioAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;
public class EditarUsuario extends AppCompatActivity {





    private  static final int PICK_IMAGE_REQUEST = 1;
    private Button botaoBuscarImagem;
    private ImageView imagemPerfil;
    private Uri uriImagem;
    private CheckBox checkBox;
    private ProgressBar progressBar;
    private ImageView fundo;
    private TextView criando;



    private EditText nome;
    private TextView  email;
    private TextView confirmaremail;
    private EditText  senha;
    private EditText  confirmarsenha;
    private EditText  telefone;
    private String    imagem;
    private TextView txtNumeroPm;


    private  EditText numeroPm;

    //  private EditText  nascimento;
    private Button botaocadastrar;


    private Usuarios usuarios;


    private List<Usuarios> listaUsuario = new ArrayList<Usuarios>();
    private ArrayAdapter<Usuarios> arrayAdapterUsuario;
    private ListView listViewDados;



    private FirebaseAuth autenticacao;
    private StorageReference storageReference;
    private DatabaseReference firebase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);





        autenticacao = FirebaseAuth.getInstance();






        //Instânciar objetos
        listaUsuario = new ArrayList<>();

        arrayAdapterUsuario = new UsuarioAdapter(EditarUsuario.this, (ArrayList<Usuarios>) listaUsuario);
        //   listViewDados.setAdapter(arrayAdapterUsuario);

        //registerForContextMenu(listViewDados);










        /// da imagem

        storageReference = FirebaseStorage.getInstance().getReference();


        //  databaseReference = FirebaseDatabase.getInstance().getReference("uploads");


        nome = (EditText)findViewById(R.id.NomeID01);
        email = (TextView)findViewById(R.id.EmailtextID01);
        confirmaremail = (TextView)findViewById(R.id.confirmarEmailID01);
        senha = (EditText)findViewById(R.id.senhaID01);
        confirmarsenha = (EditText)findViewById(R.id.confirmarSenhaID01);
        telefone = (EditText)findViewById(R.id.telefoneeID01);
        //  nascimento = (EditText)findViewById(R.id.dataID);
        imagemPerfil = (ImageView) findViewById(R.id.imagemPerfilID01);

        numeroPm = (EditText)findViewById(R.id.numeroPmID01);
        txtNumeroPm =   (TextView) findViewById(R.id.txtNumeroPmID01);


        botaocadastrar = (Button) findViewById(R.id.btnEditarID01);
        botaoBuscarImagem = (Button) findViewById(R.id.btnBuscarImagemID01);
        checkBox = (CheckBox) findViewById(R.id.checkBoxMilitarID01);

        progressBar = (ProgressBar)findViewById(R.id.progressBarCdastroID01);
        fundo       = (ImageView)findViewById(R.id.fundoID01);
        criando     = (TextView) findViewById(R.id.criandoID01);


        mascaras();



        // rebece o dados do Bike Adapter por parametro
        Intent intent = getIntent();

        if(intent !=null) {

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


            }


        }




                // faz aparecer e desaparecer os campos numero pm
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox.isChecked()){

                    txtNumeroPm.setVisibility(View.VISIBLE);
                    numeroPm.setVisibility(View.VISIBLE);


                }else{


                    txtNumeroPm.setVisibility(View.GONE);
                    numeroPm.setVisibility(View.GONE);



                }


            }
        });


        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                fundo.setVisibility(View.VISIBLE);
                criando.setVisibility(View.VISIBLE);




                if (!nome.getText().toString().equals("") && !email.getText().toString().equals("") &&
                        !confirmaremail.getText().toString().equals("") && !senha.getText().toString().equals("") &&
                        !confirmarsenha.getText().toString().equals("") && !telefone.getText().toString().equals("")){


                    if (senha.getText().toString().equals(confirmarsenha.getText().toString())) {
                        if (email.getText().toString().equals(confirmaremail.getText().toString())) {


                            inicializarElementos();
                            addImagem();
                            cadastrarUsuario();



                        }else{

                            Toast.makeText(EditarUsuario.this, "Os E-mail não são correspondentes", Toast.LENGTH_LONG).show();
                            email.requestFocus();
                            progressBar.setVisibility(View.GONE);
                            fundo.setVisibility(View.GONE);
                            criando.setVisibility(View.GONE);


                        }

                    } else {


                        Toast.makeText(EditarUsuario.this, "As senhas não são correspondentes", Toast.LENGTH_LONG).show();
                        senha.requestFocus();
                        progressBar.setVisibility(View.GONE);
                        fundo.setVisibility(View.GONE);
                        criando.setVisibility(View.GONE);

                    }

                }else {


                    Toast.makeText(EditarUsuario.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);
                    fundo.setVisibility(View.GONE);
                    criando.setVisibility(View.GONE);


                }
            }

        });

        botaoBuscarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                abrirFotos();

            }
        });

    }




    private void mascaras() {

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN)NNNNNNNNN");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        telefone.addTextChangedListener(maskTelefone);


    }






    private void inicializarElementos(){


        usuarios = new Usuarios();
        usuarios.setNome(nome.getText().toString());
        usuarios.setEmail(email.getText().toString());
        usuarios.setSenha(senha.getText().toString());
        usuarios.setTelefone(telefone.getText().toString());
        //   usuarios.setNascimento(nascimento.getText().toString());
        usuarios.setImagem(imagemPerfil.getScaleType().toString());

    }


    private void cadastrarUsuario(){

        // recupera autenticão do usuario local

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String name = user.getDisplayName();
            String email = user.getEmail();

            // converte o email pra base 64
            String identificadorUsuario= Base64Custom.codificarBase64(email);



            // EDITA a bike no nó todas as bikes
            firebase = Configuracao_Firebase.getFirebase().child("Usuarios");
            firebase.child(identificadorUsuario).setValue(usuarios);



            Toast.makeText(EditarUsuario.this, "Seu Perfil foi Alterado!", Toast.LENGTH_LONG).show();

            // retorna a tela usuario

            abrirAreaUsuario();

        }



    }





    private void abrirAreaUsuario() {


        Intent intent = new Intent(EditarUsuario.this ,AreaUsuario.class);
        startActivity(intent);
        finish();


    }


    // buscar as fotos no celular
    private void abrirFotos(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

// codigo da imagem

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null){


            uriImagem   = data.getData();

            StorageReference filePath = storageReference.child("FotosPerfil").child(uriImagem.getLastPathSegment());

            Picasso.with(this).load(uriImagem).into(imagemPerfil);
            imagemPerfil.setImageURI(uriImagem);

        }
    }


    private String getExtension(Uri uri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();


        return mime.getExtensionFromMimeType(cr.getType(uri));


    }

    public void addImagem(){

        if(uriImagem != null){

            StorageReference fileRederencia  = storageReference.child("FotosPerfil").child( System.currentTimeMillis()+ "." + getExtension(uriImagem));


            fileRederencia.putFile(uriImagem).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(EditarUsuario.this, "sucesso imagem", Toast.LENGTH_LONG).show();

                    Upload upload = new Upload();


                    Uri descarregarFoto = taskSnapshot.getDownloadUrl();
                    Picasso.with(EditarUsuario.this)
                            .load(descarregarFoto)
                            .centerCrop()
                            .into(imagemPerfil);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });


        }else {

            Toast.makeText(EditarUsuario.this, "Arquivo de foto não selecionado", Toast.LENGTH_LONG).show();



        }


    }







}

