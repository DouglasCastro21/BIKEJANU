package bike.douglas.com.bikejanu.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Model.LocalBikesMaps;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;

public class CadastroBike extends AppCompatActivity  {


    public  EditText numero_serie;
    private EditText marca;
    private EditText modelo;
    private EditText cor;
    private EditText descricao;


   protected static EditText cadastroEstado;
   public    static EditText cadastroCidade;

    static EditText cadastroRua;
    static EditText cadastroBairro;
    static TextView txtLatitude;
    static TextView txtLongitude;

    private EditText alertaDate;
    private EditText alertaHora;
    private EditText Boletim;
    private EditText alertaDescricao;


    private TextView txtmensagem1;
    private TextView txtmensagem2;
    private TextView txtRua;
    private TextView txtDataHora;
    private TextView txtCadastroBikeEstado;
    private TextView txtCadastroBikeCidade;
    private TextView txtBairro;


    private TextView    txtObservacao;
    private TextView    txtBoletim;
    private TextView    txtMensagemBoletim;
    private RadioButton radioButtonFurtada;
    private RadioButton radioButtonRoubada;
    private RadioGroup  radioGroup;
    private RadioButton status;




   protected Button     botaoBuscarMapa;
   protected Button     botaocadastrar;
   protected ImageView  spinnerImagem;



    public  Bike bike;
    public LocalBikesMaps localBikesMaps;
    private DatabaseReference firebase;
    private int dia,mes,ano,hora,minuto;




    private String marcasBike[] = new String[] {"CALOY","MONARK","MALAGRA","GALO","EXTREME","HOUSTON","OUTRA"};
    private Spinner spinner;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bike);


        numero_serie    = (EditText)    findViewById(R.id.NumeroID);
        modelo          = (EditText)    findViewById(R.id.modeloID);
        cor             = (EditText)    findViewById(R.id.corID);
        descricao       = (EditText)    findViewById(R.id.descricaoID);

        cadastroEstado  = (EditText)    findViewById(R.id.cadastroEstadoID);
        cadastroCidade  = (EditText)    findViewById(R.id.cadastroCidadeID);
        cadastroBairro  = (EditText)    findViewById(R.id.cadastroBairroID);
        cadastroRua     = (EditText)    findViewById(R.id.cadastroRuaID);
        alertaDate      = (EditText)    findViewById(R.id.alertaDataID);
        alertaHora      = (EditText)    findViewById(R.id.alertaHoraID);
        Boletim         = (EditText)    findViewById(R.id.BoletimID);
        alertaDescricao = (EditText)    findViewById(R.id.alertaDescricaoID);

        // campos txt

        txtLatitude             = (TextView)  findViewById(R.id.txCadastroLatitudeID);
        txtLongitude            = (TextView)  findViewById(R.id.txCadastroLongitudeID);
        txtCadastroBikeEstado   = (TextView)  findViewById(R.id.txtcadastroBikeEstadoID);
        txtCadastroBikeCidade   = (TextView)  findViewById(R.id.txtcadastroBikeCidadeID);
        txtRua                  = (TextView)  findViewById(R.id.txtRuaID);
        txtBairro               = (TextView)  findViewById(R.id.txtBairroID);
        txtBoletim              = (TextView)  findViewById(R.id.txtBoletimID);
        txtDataHora             = (TextView)  findViewById(R.id.txtDataHoraID);
        txtMensagemBoletim      = (TextView)  findViewById(R.id.txtMensagemBoletimID);

        txtObservacao           = (TextView)  findViewById(R.id.txtObservacaoID);
        txtmensagem1            = (TextView)  findViewById(R.id.txtmensagem1ID);
        txtmensagem2            = (TextView)  findViewById(R.id.txtmensagem2ID);


        radioButtonFurtada      = (RadioButton) findViewById(R.id.radioButtonFurtadaID);
        radioButtonRoubada      = (RadioButton) findViewById(R.id.alertaRoubadaID);
        radioGroup              = (RadioGroup)  findViewById(R.id.radioGroupID);



        botaoBuscarMapa         = (Button)       findViewById(R.id.btnBuscarMapsID) ;
        botaocadastrar          = (Button)       findViewById(R.id.finalizarID);
        final CheckBox checkBox = (CheckBox)     findViewById(R.id.checkBoxID);


        //carrega os spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CadastroBike.this, android.R.layout.simple_spinner_dropdown_item, marcasBike);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerImagem = (ImageView) findViewById(R.id.spinerImagID);




        spinner = (Spinner) findViewById(R.id.spinnerMarcaID);
        spinner.setAdapter(arrayAdapter);

        numero_serie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caixaDialogoNumeroQUadro();


            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
                TextView marcaText = (TextView) findViewById(R.id.spinnerMarcaID);
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


        status = (RadioButton)findViewById(R.id.radioButtonNadaConstaID);



// faz aparecer e desaparecer os campos na tela de cadastro de bikes
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(checkBox.isChecked()){

                   radioButtonFurtada.toggle();
                   status = (RadioButton)findViewById(R.id.radioButtonFurtadaID);

                   cadastroEstado.setVisibility(View.VISIBLE);
                   cadastroCidade.setVisibility(View.VISIBLE);
                   cadastroBairro.setVisibility(View.VISIBLE);
                   cadastroRua.setVisibility(View.VISIBLE);
                   alertaDescricao.setVisibility(View.VISIBLE);
                   alertaDate.setVisibility(View.VISIBLE);
                   alertaHora.setVisibility(View.VISIBLE);
                   Boletim.setVisibility(View.VISIBLE);
                   radioButtonFurtada.setVisibility(View.VISIBLE);
                   radioButtonRoubada.setVisibility(View.VISIBLE);
                   radioGroup.setVisibility(View.VISIBLE);
                   botaoBuscarMapa.setVisibility(View.VISIBLE);


                   // campos txt

                   txtMensagemBoletim.setVisibility(View.VISIBLE);
                   txtCadastroBikeEstado.setVisibility(View.VISIBLE);
                   txtCadastroBikeCidade.setVisibility(View.VISIBLE);
                   txtRua.setVisibility(View.VISIBLE);
                   txtBairro.setVisibility(View.VISIBLE);

                   txtDataHora.setVisibility(View.VISIBLE);
                   txtBoletim.setVisibility(View.VISIBLE);
                   txtObservacao.setVisibility(View.VISIBLE);
                   txtmensagem1.setVisibility(View.VISIBLE);
                   txtmensagem2.setVisibility(View.VISIBLE);



//preenche os campos datas

                   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                   // OU
                   SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm");

                   Date data = new Date();

                   Calendar  cal = Calendar.getInstance();
                   cal.setTime(data);

                   Date data_atual = cal.getTime();

                   String data_completa = dateFormat.format(data_atual);

                   String hora_atual = dateFormat_hora.format(data_atual);

                   //  Log.i("data_completa", data_completa);
                   // Log.i("data_atual", data_atual.toString());
                   // Log.i("hora_atual", hora_atual);

                   alertaDate.setText(data_completa);
                   alertaHora.setText(hora_atual);


               }else{

                   status = (RadioButton)findViewById(R.id.radioButtonNadaConstaID);
                   cadastroEstado.setVisibility(View.GONE);
                   cadastroCidade.setVisibility(View.GONE);
                   cadastroRua.setVisibility(View.GONE);
                   cadastroBairro.setVisibility(View.GONE);
                   alertaDescricao.setVisibility(View.GONE);
                   alertaDate.setVisibility(View.GONE);
                   alertaHora.setVisibility(View.GONE);
                   Boletim.setVisibility(View.GONE);
                   botaoBuscarMapa.setVisibility(View.GONE);

                   radioButtonFurtada.setVisibility(View.GONE);
                   radioButtonRoubada.setVisibility(View.GONE);
                   radioGroup.setVisibility(View.GONE);

                   //campos txt

                   txtMensagemBoletim.setVisibility(View.GONE);
                   txtCadastroBikeEstado.setVisibility(View.GONE);
                   txtCadastroBikeCidade.setVisibility(View.GONE);
                   txtRua.setVisibility(View.GONE);
                   txtBairro.setVisibility(View.GONE);

                   txtDataHora.setVisibility(View.GONE);
                   txtBoletim.setVisibility(View.GONE);
                   txtObservacao.setVisibility(View.GONE);
                   txtmensagem1.setVisibility(View.GONE);
                   txtmensagem2.setVisibility(View.GONE);


                   alertaDate.setText("");
                   alertaHora.setText("");



               }


            }
        });









        alertaDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                ano = calendar.get(Calendar.YEAR);

                final DatePickerDialog datePickerDialog = new  DatePickerDialog(CadastroBike.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        alertaDate.setText(dayOfMonth +"/"+(month+1)+"/"+year);

                    }
                }

                        ,ano,mes,dia);
                datePickerDialog.show();
                alertaHora.requestFocus();

            }
        });



        alertaHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar calendar = Calendar.getInstance();
                hora = calendar.get(Calendar.HOUR_OF_DAY);
                minuto = calendar.get(Calendar.MINUTE);

                final TimePickerDialog timePickerDialog= new TimePickerDialog(CadastroBike.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                        alertaHora.setText(hourOfDay +":"+minute);

                    }
                },hora,minuto,false);
                timePickerDialog.show();


            }
        });





        radioButtonFurtada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caixaDialogoFurtada();
                status = (RadioButton)findViewById(R.id.radioButtonFurtadaID);

            }
        });



        radioButtonRoubada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caixaDialogoRoubada();
                status = (RadioButton)findViewById(R.id.alertaRoubadaID);

            }
        });


        botaoBuscarMapa.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent intent = new Intent(CadastroBike.this ,MapsActivity2.class);
        startActivity(intent);

        }
        });



        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkBox.isChecked()) {



                    if (!numero_serie.getText().toString().equals("")  &&
                            !modelo.getText().toString().equals("") && !cor.getText().toString().equals("")&& !cadastroBairro.getText().toString().equals("")
                            && !cadastroRua.getText().toString().equals("")
                            && !alertaDate.getText().toString().equals("")&& !alertaHora.getText().toString().equals("")) {

                        inicializarElementos();
                        recuperarDadosUsuarioConectadoECadastra();


                    } else {


                        Toast.makeText(CadastroBike.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();

                        if (numero_serie.getText().toString().equals("")) {

                            numero_serie.requestFocus();

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



                }else{


                    if (!numero_serie.getText().toString().equals("")  &&
                            !modelo.getText().toString().equals("") && !cor.getText().toString().equals("")) {

                        inicializarElementos();
                        recuperarDadosUsuarioConectadoECadastra();


                    } else {


                        Toast.makeText(CadastroBike.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();

                        if (numero_serie.getText().toString().equals("")) {

                            numero_serie.requestFocus();



                           // if (marca.getText().toString().equals("")) {

                           //     marca.requestFocus();

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
         localBikesMaps = new LocalBikesMaps();
         bike.setNumero_serie(numero_serie.getText().toString());
         bike.setMarca(spinner.getSelectedItem().toString());
         bike.setModelo(modelo.getText().toString());
         bike.setCor(cor.getText().toString());
         bike.setDescricao(descricao.getText().toString());

         bike.setAlertaEstado(cadastroEstado.getText().toString());
         bike.setAlertaCidade(cadastroCidade.getText().toString());
         bike.setAlertaRua(cadastroRua.getText().toString());
         bike.setAlertaBairro(cadastroBairro.getText().toString());
         bike.setAlertaDate(alertaDate.getText().toString());
         bike.setAlertaHora(alertaHora.getText().toString());
         bike.setBoletim(Boletim.getText().toString());
         bike.setAlertaDescricao(alertaDescricao.getText().toString());
         bike.setStatus(status.getText().toString());

         bike.setLatitude(txtLatitude.getText().toString());
         bike.setLongitude(txtLongitude.getText().toString());
         localBikesMaps.setLatitude(txtLatitude.getText().toString());
         localBikesMaps.setLongitude(txtLongitude.getText().toString());



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

            String email = user.getEmail();

            // converte o email pra base 64
            String identificadorUsuario= Base64Custom.codificarBase64(email);



            // cadastra a bike no nó todas as bikes
            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);



                if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                    // cadastra a bikeROubada no Maps de roubos

                    firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                    firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
                }



            // cadastra no nó usuario logado
            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


            Toast.makeText(CadastroBike.this, "Operação realizada com sucesso!", Toast.LENGTH_LONG).show();

           // retorna a tela usuario

           abrirAreaUsuario();

        };



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

    public void ShowElemento(View view){


        String nome = (String)spinner.getSelectedItem();
        long id = spinner.getSelectedItemId();
        int posicao = spinner.getSelectedItemPosition();

    }


    private void caixaDialogoNumeroQUadro(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(CadastroBike.this);

        // configurando dialogo

        alertaDialog.setTitle("Definição");


        alertaDialog.setMessage("O número do quadro geralmente se encontra na parte central da bike, junto ao ponto de fixação dos pedais ou perto da presilha que prende o acento.");
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