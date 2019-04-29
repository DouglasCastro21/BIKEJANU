package bike.douglas.com.bikejanu.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.auth.UserRecoverableAuthException;
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
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Adapter.UsuarioAdapter;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.R;

public class CadastroUsuario extends AppCompatActivity {


    private  static final int PICK_IMAGE_REQUEST = 1;
    private Button botaoBuscarImagem;
    private ImageView imagemPerfil;
    private Uri uriImagem;
    private CheckBox checkBox;
    private ProgressBar progressBar;
    private ImageView fundo;
    private  TextView criando;



    private EditText  nome;
    private EditText  email;
    private EditText  confirmaremail;
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
        setContentView(R.layout.activity_cadastro_usuario);





        autenticacao = FirebaseAuth.getInstance();






        //Instânciar objetos
        listaUsuario = new ArrayList<>();

        arrayAdapterUsuario = new UsuarioAdapter(CadastroUsuario.this, (ArrayList<Usuarios>) listaUsuario);
     //   listViewDados.setAdapter(arrayAdapterUsuario);

        //registerForContextMenu(listViewDados);







        // rebece o e passada pela tela cadastro
            Intent intent = getIntent();

            if(intent !=null){
            Bundle params = intent.getExtras();

            if (params !=null){

                String emails = params.getString("emailUsuario");
                TextView emailsText = (TextView) findViewById(R.id.EmailtextID);
                emailsText.setText(emails);


                String no= params.getString("nomeUsuario");
                TextView noText  = (TextView) findViewById(R.id.NumeroID);
                noText.setText(no);

            }
        }






    /// da imagem

        storageReference = FirebaseStorage.getInstance().getReference();


     //  databaseReference = FirebaseDatabase.getInstance().getReference("uploads");


        nome = (EditText)findViewById(R.id.NumeroID);
        email = (EditText)findViewById(R.id.EmailtextID);
        confirmaremail = (EditText)findViewById(R.id.confirmarEmailID);
        senha = (EditText)findViewById(R.id.senhaID);
        confirmarsenha = (EditText)findViewById(R.id.confirmarSenhaID);
        telefone = (EditText)findViewById(R.id.telefoneeID);
      //  nascimento = (EditText)findViewById(R.id.dataID);
        imagemPerfil = (ImageView) findViewById(R.id.imagemPerfilID);

        numeroPm = (EditText)findViewById(R.id.numeroPmID);
        txtNumeroPm =   (TextView) findViewById(R.id.txtNumeroPmID);


        botaocadastrar = (Button) findViewById(R.id.btnCadastrarID);
        botaoBuscarImagem = (Button) findViewById(R.id.btnBuscarImagemID);
        checkBox = (CheckBox) findViewById(R.id.checkBoxMilitarID);

        progressBar = (ProgressBar)findViewById(R.id.progressBarCdastroID);
        fundo       = (ImageView)findViewById(R.id.fundoID);
        criando     = (TextView) findViewById(R.id.criandoID);


        mascaras();






        // faz aparecer e desaparecer os campos na tela de cadastro de bikes
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

                            Toast.makeText(CadastroUsuario.this, "Os E-mail não são correspondentes", Toast.LENGTH_LONG).show();
                            email.requestFocus();
                              progressBar.setVisibility(View.GONE);
                                fundo.setVisibility(View.GONE);
                                 criando.setVisibility(View.GONE);


                        }

                    } else {


                        Toast.makeText(CadastroUsuario.this, "As senhas não são correspondentes", Toast.LENGTH_LONG).show();
                        senha.requestFocus();
                        progressBar.setVisibility(View.GONE);
                         fundo.setVisibility(View.GONE);
                         criando.setVisibility(View.GONE);

                     }

            }else {


                    Toast.makeText(CadastroUsuario.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();

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


        autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(

                usuarios.getEmail(),
                usuarios.getSenha()




        ).addOnCompleteListener(CadastroUsuario.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {




                    if (task.isSuccessful()){

                        progressBar.setVisibility(View.GONE);
                        fundo.setVisibility(View.GONE);
                        criando.setVisibility(View.GONE);


                         String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                         FirebaseUser usuarioFirebase = task.getResult().getUser();
                         usuarios.setIdUsuario(identificadorUsuario);



                         usuarios.Salvar();

                         Preferencias preferencias = new Preferencias(CadastroUsuario.this);
                         preferencias.salvarUsuarioPreferencias(identificadorUsuario,usuarios.getNome());

                         FirebaseUser user = autenticacao.getCurrentUser();


                        Toast.makeText(CadastroUsuario.this,"Usuário cadastrado com sucesso!",Toast.LENGTH_LONG).show();

                        abrirAreaUsuario();

                }else{
                         progressBar.setVisibility(View.GONE);
                         String erroExcecao = "";

                    try {
                        throw  task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){

                         erroExcecao = "Digite uma senha contendo no mínimo 8 caracteres entre letras e numeros";
                         senha.requestFocus();
                    }catch (FirebaseAuthUserCollisionException e){
                         erroExcecao = "Email já cadastrado   ";
                         email.requestFocus();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "O campo de email está mal formado  ";
                        email.requestFocus();

                    } catch (Exception e){
                        erroExcecao = "Erro ao efetuar cadastro,verifique a Conexão com a internet";
                        e.printStackTrace();

                    }




                    Toast.makeText(CadastroUsuario.this,"Erro : " + erroExcecao,Toast.LENGTH_LONG ).show();

                }
            }
        });



    }

    private void abrirAreaUsuario() {


        Intent intent = new Intent(CadastroUsuario.this ,AreaUsuario.class);
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



            StorageReference filePath = storageReference.child("fotosPerfil").child(uriImagem.getLastPathSegment());

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



                Toast.makeText(CadastroUsuario.this, "Sucesso imagem", Toast.LENGTH_LONG).show();

                Upload upload = new Upload();





            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        }else {

            Toast.makeText(CadastroUsuario.this, "Arquivo de foto não selecionado", Toast.LENGTH_LONG).show();


        }


    }





}
