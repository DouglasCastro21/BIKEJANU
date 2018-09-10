package bike.douglas.com.bikejanu.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;

public class CadastroBike extends AppCompatActivity  {


    public  EditText numero_serie;
    private EditText marca;
    private EditText modelo;
    private EditText cor;
    private EditText descricao;


    private EditText alertaNumero;
    private EditText alertaRua;
    private EditText alertaBairro;
    private EditText alertaDate;
    private EditText alertaHora;
    private EditText Boletim;
    private EditText alertaDescricao;


    private TextView txtmensagem1;
    private TextView txtmensagem2;
    private TextView txtRua;
    private TextView txtDataHora;
    private TextView txtBairro;
    private TextView txtNumero;
    private TextView txtObservacao;
    private TextView txtBoletim;
    private RadioButton radioButtonFurtada;
    private RadioButton radioButtonRoubada;
    private RadioGroup radioGroup;




    private Button   botaocadastrar;



    public  Bike bike;
    private DatabaseReference firebase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bike);


        numero_serie    = (EditText) findViewById(R.id.NumeroID);
        marca           = (EditText) findViewById(R.id.marcaID);
        modelo          = (EditText) findViewById(R.id.modeloID);
        cor             = (EditText) findViewById(R.id.corID);
        descricao       = (EditText)findViewById(R.id.descricaoID);

        alertaNumero    = (EditText)findViewById(R.id.alertaNumeroID);
        alertaRua       = (EditText)findViewById(R.id.alertaRuaID);
        alertaBairro    = (EditText)findViewById(R.id.alertaBairroID);
        alertaDate      = (EditText)findViewById(R.id.alertaDataID);
        alertaHora      = (EditText)findViewById(R.id.alertaHoraID);
        Boletim         = (EditText)findViewById(R.id.BoletimID);
        alertaDescricao = (EditText)findViewById(R.id.alertaDescricaoID);

        // campos txt

        txtRua           = (TextView)findViewById(R.id.txtRuaID) ;
        txtBairro        = (TextView)findViewById(R.id.txtBairroID);
        txtBoletim       = (TextView)findViewById(R.id.txtBoletimID);
        txtDataHora      = (TextView)findViewById(R.id.txtDataHoraID);
        txtNumero        = (TextView)findViewById(R.id.txtNumeroID);
        txtObservacao    = (TextView)findViewById(R.id.txtObservacaoID);
        txtmensagem1     = (TextView)findViewById(R.id.txtmensagem1ID);
        txtmensagem2     = (TextView)findViewById(R.id.txtmensagem2ID);



        radioButtonFurtada =(RadioButton)findViewById(R.id.radioButtonFurtadaID);
        radioButtonRoubada =(RadioButton)findViewById(R.id.alertaRoubadaID);
        radioGroup        = (RadioGroup)findViewById(R.id.radioGroupID);





        botaocadastrar = (Button) findViewById(R.id.finalizarID);
        final CheckBox  checkBox = (CheckBox) findViewById(R.id.checkBoxID);







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



        mascaras();

// faz aparecer e desaparecer os campos na tela de cadastro de bikes
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(checkBox.isChecked()){

                   alertaNumero.setVisibility(View.VISIBLE);
                   alertaRua.setVisibility(View.VISIBLE);
                   alertaBairro.setVisibility(View.VISIBLE);
                   alertaDescricao.setVisibility(View.VISIBLE);
                   alertaDate.setVisibility(View.VISIBLE);
                   alertaHora.setVisibility(View.VISIBLE);
                   Boletim.setVisibility(View.VISIBLE);



                   radioButtonFurtada.setVisibility(View.VISIBLE);
                   radioButtonRoubada.setVisibility(View.VISIBLE);
                   radioGroup.setVisibility(View.VISIBLE);

                   // campos txt
                   txtRua.setVisibility(View.VISIBLE);
                   txtBairro.setVisibility(View.VISIBLE);
                   txtNumero.setVisibility(View.VISIBLE);
                   txtDataHora.setVisibility(View.VISIBLE);
                   txtBoletim.setVisibility(View.VISIBLE);
                   txtObservacao.setVisibility(View.VISIBLE);
                   txtmensagem1.setVisibility(View.VISIBLE);
                   txtmensagem2.setVisibility(View.VISIBLE);





               }else{

                   alertaNumero.setVisibility(View.GONE);
                   alertaRua.setVisibility(View.GONE);
                   alertaBairro.setVisibility(View.GONE);
                   alertaDescricao.setVisibility(View.GONE);
                   alertaDate.setVisibility(View.GONE);
                   alertaHora.setVisibility(View.GONE);
                   Boletim.setVisibility(View.GONE);


                   radioButtonFurtada.setVisibility(View.GONE);
                   radioButtonRoubada.setVisibility(View.GONE);
                   radioGroup.setVisibility(View.GONE);

                   //campos txt

                   txtRua.setVisibility(View.GONE);
                   txtBairro.setVisibility(View.GONE);
                   txtNumero.setVisibility(View.GONE);
                   txtDataHora.setVisibility(View.GONE);
                   txtBoletim.setVisibility(View.GONE);
                   txtObservacao.setVisibility(View.GONE);
                   txtmensagem1.setVisibility(View.GONE);
                   txtmensagem2.setVisibility(View.GONE);



               }


            }
        });



        radioButtonFurtada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                caixaDialogoFurtada();


            }
        });



        radioButtonRoubada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                caixaDialogoRoubada();


            }
        });






        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if (!numero_serie.getText().toString().equals("") && !marca.getText().toString().equals("") &&
                            !modelo.getText().toString().equals("") && !cor.getText().toString().equals("")) {

                        inicializarElementos();
                        recuperarDadosUsuarioConectadoECadastra();


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


         bike.setAlertaNumero(alertaNumero.getText().toString());
         bike.setAlertaRua(alertaRua.getText().toString());
         bike.setAlertaBairro(alertaBairro.getText().toString());
         bike.setAlertaDate(alertaDate.getText().toString());
         bike.setAlertaHora(alertaHora.getText().toString());
         bike.setBoletim(Boletim.getText().toString());
         bike.setAlertaDescricao(alertaDescricao.getText().toString());

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
    private void recuperarDadosUsuarioConectadoECadastra(){

        // recupera autenticão do usuario local

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String name = user.getDisplayName();
            String email = user.getEmail();

            // converte o email pra base 64
            String identificadorUsuario= Base64Custom.codificarBase64(email);



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



    public void mascaras() {

        SimpleMaskFormatter simpleMaskData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskData = new MaskTextWatcher(alertaDate, simpleMaskData);
        alertaDate.addTextChangedListener(maskData);

        SimpleMaskFormatter simpleMaskHora = new SimpleMaskFormatter("NN:NN");
        MaskTextWatcher maskHora = new MaskTextWatcher(alertaHora, simpleMaskHora);
        alertaHora.addTextChangedListener(maskHora);
    }


    private void caixaDialogoRoubada(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(CadastroBike.this);

        // configurando dialogo

        alertaDialog.setTitle("Definição");


        alertaDialog.setMessage("Artigo 157 do código penal(1940) : O roubo pressupõe o emprego de violência ou grave ameaça à pessoa.");
        // alertaDialog.setCancelable(false);


        //conf botões
        alertaDialog.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        alertaDialog.setNegativeButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertaDialog.create();
        alertaDialog.show();
    }



    private void caixaDialogoFurtada(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(CadastroBike.this);

        // configurando dialogo

        alertaDialog.setTitle("Definição");


        alertaDialog.setMessage("Artigo 155 do código penal(1940) :  Furto é a subtração pura e simples de coisa móvel alheia, sem violência contra a pessoa.");
        // alertaDialog.setCancelable(false);


        //conf botões
        alertaDialog.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        alertaDialog.setNegativeButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertaDialog.create();
        alertaDialog.show();
    }



    }