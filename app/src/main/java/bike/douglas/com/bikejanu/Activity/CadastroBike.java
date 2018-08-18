package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Entidades.Usuarios;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.R;

public class CadastroBike extends AppCompatActivity  {


    public  EditText numero_serie;
    private EditText marca;
    private EditText modelo;
    private EditText cor;
    private Button botaocadastrar;
    private EditText descricao;
    public Bike bike;
    private DatabaseReference firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bike);


        numero_serie = (EditText) findViewById(R.id.NomeID);
        marca = (EditText) findViewById(R.id.marcaID);
        modelo = (EditText) findViewById(R.id.modeloID);
        cor = (EditText) findViewById(R.id.corID);
        descricao = (EditText)findViewById(R.id.descricaoID);

        botaocadastrar = (Button) findViewById(R.id.finalizarID);




        // rebece o email passada pela tela cadastro
        Intent intent = getIntent();

        if(intent !=null){
            Bundle params = intent.getExtras();

            if (params !=null){

                String modelo = params.getString("modelo");
                TextView modeloText = (TextView) findViewById(R.id.modeloID);
                modeloText.setText(modelo);

            }
        }





        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (!numero_serie.getText().toString().equals("")  && !marca.getText().toString().equals("") &&
                        !modelo.getText().toString().equals("") && !cor.getText().toString().equals("")) {

                        inicializarElementos();
                        recuperarDadosUsuarioConectado();


               }else{


                    Toast.makeText(CadastroBike.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();

                    if(numero_serie.getText().toString().equals("")){

                        numero_serie.requestFocus();

                    }else {

                        if(marca.getText().toString().equals("")){

                            marca.requestFocus();

                    }else {
                            if(modelo.getText().toString().equals("")){

                                modelo.requestFocus();



                        }else {
                                if(cor.getText().toString().equals("")){

                                    cor.requestFocus();

                            }
                            }



                        }

                    }


                    }



            }


        });

    }







    private void inicializarElementos(){

         bike = new Bike();
         bike.setNumero_serie(numero_serie.getText().toString());
         bike.setMarca(marca.getText().toString());
         bike.setModelo(modelo.getText().toString());
         bike.setCor(cor.getText().toString());
         bike.setDescricao(descricao.getText().toString());

}

// volta pra tela usuario
    private void abrirAreaUsuario(){

        Intent intent = new Intent(CadastroBike.this ,AreaUsuario.class);
        startActivity(intent);
        finish();
    }



    // ficou assim


    // Bikes
    // +chave  usuario 1234  // isso significa q a bike addd nesse nó é do propietario de chave 1234
    //  +chave bike 321
    //    +dados bike

    // Usuarios
    //   +chave  usuario 1234
    //       +dados usuario
    private void recuperarDadosUsuarioConectado(){

        // recupera autenticão do usuario local

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String name = user.getDisplayName();
            String email = user.getEmail();

            // converte o email pra base 64
            String identificadorUsuario= Base64Custom.codificarBase64(email);

            //

            // o numero de serie virou id
          //  String identificadorBike = Base64Custom.codificarBase64(bike.getNumero_serie());

            // cadastra a bike no nó indicado

            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);

            Toast.makeText(CadastroBike.this, "Bicicleta cadastrado com sucesso!", Toast.LENGTH_LONG).show();

           // retorna a tela usuario

           abrirAreaUsuario();

        };
    }

    }