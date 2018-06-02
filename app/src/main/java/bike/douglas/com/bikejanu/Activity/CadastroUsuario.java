package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Usuarios;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.R;

public class CadastroUsuario extends AppCompatActivity {



    private EditText  nome;
    private EditText  email;
    private EditText  confirmaremail;
    private EditText  senha;
    private EditText  confirmarsenha;
    private EditText  telefone;
    private EditText  nascimento;

    private Button botaocadastrar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);




        nome = (EditText)findViewById(R.id.NomeID);
        email = (EditText)findViewById(R.id.EmailtextID);
        confirmaremail = (EditText)findViewById(R.id.verificarEmailID);
        senha = (EditText)findViewById(R.id.senhaID);
        confirmarsenha = (EditText)findViewById(R.id.confirmarSenhaID);
        telefone = (EditText)findViewById(R.id.telefoneID);
        nascimento = (EditText)findViewById(R.id.dataID);

        botaocadastrar = (Button) findViewById(R.id.btnCadastrarID);




        mascaras();

        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nome.getText().toString().equals("") && !email.getText().toString().equals("") &&
                        !confirmaremail.getText().toString().equals("") && !senha.getText().toString().equals("") &&
                        !confirmarsenha.getText().toString().equals("") && !telefone.getText().toString().equals("")
                                && !nascimento.getText().toString().equals("")){

                    if (senha.getText().toString().equals(confirmarsenha.getText().toString())) {

                        inicializarElementos();
                        cadastrarUsuario();


                    } else {


                        Toast.makeText(CadastroUsuario.this, "As senhas não são correspondentes", Toast.LENGTH_LONG).show();
                        senha.requestFocus();
                    }

            }else {


                    Toast.makeText(CadastroUsuario.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();



                                        }
                                    }



        });

    }




    private void mascaras() {

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN)-N-NNNNNNNN");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        telefone.addTextChangedListener(maskTelefone);

        SimpleMaskFormatter simpleMaskNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskNascimento = new MaskTextWatcher(nascimento, simpleMaskNascimento);
        nascimento.addTextChangedListener(maskNascimento);
    }



   private void inicializarElementos(){

        usuarios = new Usuarios();
        usuarios.setNome(nome.getText().toString());
        usuarios.setEmail(email.getText().toString());
        usuarios.setSenha(senha.getText().toString());
        usuarios.setTelefone(telefone.getText().toString());
        usuarios.setNascimento(nascimento.getText().toString());


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

                    Toast.makeText(CadastroUsuario.this,"Usuário cadastrado com sucesso!",Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setIdUsuario(identificadorUsuario);
                    usuarios.Salvar();

                    Preferencias preferencias = new Preferencias(CadastroUsuario.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario,usuarios.getNome());


                    abrirAreaUsuario();

                }else{

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
                        erroExcecao = "Erro ao efetuar o cadastro";
                        e.printStackTrace();

                    }




                    Toast.makeText(CadastroUsuario.this,"Erro : " + erroExcecao,Toast.LENGTH_LONG ).show();

                }
            }
        });



    }

    private void abrirAreaUsuario(){


        Intent intent = new Intent(CadastroUsuario.this ,AreaUsuario.class);
        startActivity(intent);
        finish();
    }
}
