package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Entidades.Usuarios;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;

public class CadastroBike extends AppCompatActivity  {


    public  EditText numero_serie;
    private EditText marca;
    private EditText modelo;
    private EditText cor;
    private Button botaocadastrar;
    private EditText descricao;
    public  Bike bike;
    private DatabaseReference firebase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bike);


        numero_serie = (EditText) findViewById(R.id.NumeroID);
        marca = (EditText) findViewById(R.id.marcaID);
        modelo = (EditText) findViewById(R.id.modeloID);
        cor = (EditText) findViewById(R.id.corID);
        descricao = (EditText)findViewById(R.id.descricaoID);

        botaocadastrar = (Button) findViewById(R.id.finalizarID);




        // rebece o dados do Bike Adapter por parametro passada pela tela cadastro
        Intent intent = getIntent();

        if(intent !=null){

            Bundle params = intent.getExtras();

            if (params !=null){

                //dados do modelo
                String modelo = params.getString("modelo");
                TextView modeloText = (TextView) findViewById(R.id.modeloID);
                modeloText.setText(modelo);

                //dados da marca
                String marca = params.getString("marca");
                TextView marcaText = (TextView) findViewById(R.id.marcaID);
                marcaText.setText(marca);

                // dados do numero serie
                String numero_serie = params.getString("numero_serie");
                TextView numero_serieText = (TextView) findViewById(R.id.NumeroID);
                numero_serieText.setText(numero_serie);


                // dados da cor
                String cor = params.getString("cor");
                TextView corText = (TextView) findViewById(R.id.corID);
                corText.setText(cor);

                // dados do numero serie
                String descricao = params.getString("descricao");
                TextView descricaoText = (TextView) findViewById(R.id.descricaoID);
                descricaoText.setText(descricao);



            }
        }





        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    if (!numero_serie.getText().toString().equals("") && !marca.getText().toString().equals("") &&
                            !modelo.getText().toString().equals("") && !cor.getText().toString().equals("")) {

                        inicializarElementos();
                        recuperarDadosUsuarioConectado();


                    } else {


                        Toast.makeText(CadastroBike.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();

                        if (numero_serie.getText().toString().equals("")) {

                            numero_serie.requestFocus();

                        } else {

                            if (marca.getText().toString().equals("")) {

                                marca.requestFocus();

                            } else {
                                if (modelo.getText().toString().equals("")) {

                                    modelo.requestFocus();


                                } else {
                                    if (cor.getText().toString().equals("")) {

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
           //String identificadorBike = Base64Custom.codificarBase64(bike.getNumero_serie());

            // cadastra a bike no nó todas as bikes
            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);

            // cadastra no nó usuario logado
            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);

            Toast.makeText(CadastroBike.this, "Operação realizada com sucesso!", Toast.LENGTH_LONG).show();

           // retorna a tela usuario

           abrirAreaUsuario();

        };
    }

    }