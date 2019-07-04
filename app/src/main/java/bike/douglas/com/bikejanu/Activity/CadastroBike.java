package bike.douglas.com.bikejanu.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Handler;

import com.bumptech.glide.Glide;
import android.provider.MediaStore;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
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


import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Model.LocalBikesMaps;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;



public class CadastroBike extends AppCompatActivity  {






// imagens



    private  static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imagemBike1;
    private ImageView imagemBike2;
    private ImageView imagemBike3;
    private ImageView imagemBike4;
    private ImageView imagemBike5;
    private Uri uriImagem1;

    private Uri uriImagem2;

    private Uri uriImagem3;

    private Uri uriImagem4;

    private Uri uriImagem5;

    String url;




    TextView carregandoBike;

    //

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

    private StorageReference  storageReference;
    private DatabaseReference firebase;

    private DatabaseReference firebaseDataBaseImagem;

    public  Bike bike;
    public LocalBikesMaps localBikesMaps;

    private int dia,mes,ano,hora,minuto;



    private static  final int TIMER_RUNTINME = 100000;
    private boolean mbActive;
    private ProgressBar progressBar;
    DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference();






    private String marcasBike[] = new String[] {"BIANCHI","COLNAGO","CALOY","DROPP ","GARELLI","GALO","HOUSTON","KSW","GÖRICKE","MONARK","MALAGA","ORBEA","ÓRBITA","SIRLA","SOUTH","SUTTON","OUTRA"};
    private Spinner spinner;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bike);




        storageReference = FirebaseStorage.getInstance().getReference("ImagensBikes");





        /// da imagem




        //arrumar um jeito de nao rodar muito essa linha
      //  UsuarioDAO.getInstancia().añadirFotoDePerfilALosUsuariosQueNoTienenFoto();


        carregandoBike  = (TextView)    findViewById(R.id.carregandoBikeId);
        progressBar     = (ProgressBar) findViewById(R.id.progressBarCadastroBikeID);
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




        imagemBike1   = (ImageView)     findViewById(R.id.imageBikeID1);
        imagemBike2   = (ImageView)     findViewById(R.id.imageBikeID2);
        imagemBike3   = (ImageView)     findViewById(R.id.imageBikeID3);
        imagemBike4   = (ImageView)     findViewById(R.id.imageBikeID4);
        imagemBike5   = (ImageView)     findViewById(R.id.imageBikeID5);



         botaoBuscarMapa         = (Button)       findViewById(R.id.btnBuscarMapsID) ;
        botaocadastrar          = (Button)       findViewById(R.id.finalizarID);
        final CheckBox checkBox = (CheckBox)     findViewById(R.id.checkBoxID);




        //carrega os spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CadastroBike.this, android.R.layout.simple_spinner_dropdown_item, marcasBike);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerImagem = (ImageView) findViewById(R.id.spinerImagID);

        spinner = (Spinner) findViewById(R.id.spinnerMarcaID);
        spinner.setAdapter(arrayAdapter);


        openGaleria();



        numero_serie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caixaDialogoNumeroQUadro();


            }
        });




        if(uriImagem1==null) {


            Glide.with(CadastroBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagemBike1);

        }
        if(uriImagem2==null) {


            Glide.with(CadastroBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagemBike2);

        }

        if(uriImagem3==null) {


            Glide.with(CadastroBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagemBike3);

        }

        if(uriImagem4==null) {


            Glide.with(CadastroBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagemBike4);

        }

        if(uriImagem5==null) {


            Glide.with(CadastroBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagemBike5);

        }









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


                        if(uriImagem1 != null ){

                          progressBar();

                            progressBar.setVisibility(View.VISIBLE);

                            inicializarElementos();


                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user != null) {

                                Upload();

                            }




                        }else{

                            Toast.makeText(CadastroBike.this, "Adicione a primeira imagem da bicicleta", Toast.LENGTH_LONG).show();

                        }






                    } else {



                        progressBar.setVisibility(View.GONE);
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




                        if(uriImagem1 != null ){

                          progressBar();

                            progressBar.setVisibility(View.VISIBLE);

                            inicializarElementos();


                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user != null) {

                                Upload();

                            }




                        }else{

                            Toast.makeText(CadastroBike.this, "Adicione a primeira imagem da bicicleta", Toast.LENGTH_LONG).show();

                        }



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






// volta pra tela usuario
    private void abrirAreaUsuario(){


        Toast.makeText(CadastroBike.this, "Operação realizada com sucesso!", Toast.LENGTH_LONG).show();
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





    private void inicializarElementos(){

        bike = new Bike();
        localBikesMaps = new LocalBikesMaps();
        bike.setNumero_serie(numero_serie.getText().toString());
        bike.setMarca(spinner.getSelectedItem().toString());
        bike.setModelo(modelo.getText().toString());
        bike.setCor(cor.getText().toString());
        bike.setDescricao(descricao.getText().toString());

        bike.setFotoBikeUrl1(url);

        bike.setAlertaEstado(cadastroEstado.getText().toString());
        bike.setAlertaCidade(cadastroCidade.getText().toString());
        bike.setAlertaRua(cadastroRua.getText().toString());
        bike.setAlertaBairro(cadastroBairro.getText().toString());





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


                    bike.setProprietario(dados.getNome());






                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // OU
        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm");

        Date data = new Date();

        Calendar  cal = Calendar.getInstance();
        cal.setTime(data);

        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);

        String hora_atual = dateFormat_hora.format(data_atual);

        // alertaDate.setText(data_completa);
        // alertaHora.setText(hora_atual);



        bike.setAlertaDate(data_completa);
        bike.setAlertaHora(hora_atual);



        bike.setBoletim(Boletim.getText().toString());
        bike.setAlertaDescricao(alertaDescricao.getText().toString());
        bike.setStatus(status.getText().toString());


        bike.setLatitude(txtLatitude.getText().toString());
        bike.setLongitude(txtLongitude.getText().toString());
        bike.setProprietarioID(identificadorUsuario);
        localBikesMaps.setLatitude(txtLatitude.getText().toString());
        localBikesMaps.setLongitude(txtLongitude.getText().toString());




    }














    private void openGaleria() {

        imagemBike1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,1);


                }
            });






              /////




                imagemBike2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);



                }
            });


////



                imagemBike3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(intent,3);


                }
                  });



                imagemBike4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                                     Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                     startActivityForResult(intent,4);



                }
            });







                imagemBike5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(intent,5);


                }
            });

    }





    public void Upload(){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String email = user.getEmail();

        // converte o email pra base 64
        final String identificadorUsuario = Base64Custom.codificarBase64(email);

        if (uriImagem1!=null) {

            progressBar.setVisibility(View.VISIBLE);
            carregandoBike.setVisibility(View.VISIBLE);

            final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem1");
            UploadTask uploadTask = ref.putFile(uriImagem1);

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
                        bike.setFotoBikeUrl1(downloadUri.toString());


                        firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                        firebase.child(bike.getNumero_serie()).setValue(bike);



                        firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                        firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                        if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                            // cadastra a bikeROubada no Maps de roubos

                            firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                            firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
                        }


                        abrirAreaUsuario();
                        progressBar.setVisibility(View.GONE);
                        carregandoBike.setVisibility(View.GONE);

                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {



                    progressBar.setVisibility(View.GONE);
                    carregandoBike.setVisibility(View.GONE);

                }
            });


        }else{



            bike.setFotoBikeUrl1("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181");


            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);



            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                // cadastra a bikeROubada no Maps de roubos

                firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
            }



        }




        if (uriImagem2!=null) {

            progressBar.setVisibility(View.VISIBLE);
            carregandoBike.setVisibility(View.VISIBLE);

            final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem2");
            UploadTask uploadTask = ref.putFile(uriImagem2);

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


                        bike.setFotoBikeUrl2(downloadUri.toString());



                        firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                        firebase.child(bike.getNumero_serie()).setValue(bike);


                        firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                        firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                        if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                            // cadastra a bikeROubada no Maps de roubos

                            firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                            firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
                        }


                        abrirAreaUsuario();
                        progressBar.setVisibility(View.GONE);
                        carregandoBike.setVisibility(View.GONE);



                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progressBar.setVisibility(View.GONE);
                    carregandoBike.setVisibility(View.GONE);

                }
            });
        }else{


            bike.setFotoBikeUrl2("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181");


            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);



            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                // cadastra a bikeROubada no Maps de roubos

                firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
            }



        }



        if (uriImagem3!=null) {

            progressBar.setVisibility(View.VISIBLE);
            carregandoBike.setVisibility(View.VISIBLE);

            final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem3");
            UploadTask uploadTask = ref.putFile(uriImagem3);

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


                        bike.setFotoBikeUrl3(downloadUri.toString());


                        firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                        firebase.child(bike.getNumero_serie()).setValue(bike);


                        firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                        firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                        if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                            // cadastra a bikeROubada no Maps de roubos

                            firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                            firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
                        }



                        abrirAreaUsuario();
                        progressBar.setVisibility(View.GONE);
                        carregandoBike.setVisibility(View.GONE);


                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progressBar.setVisibility(View.GONE);
                    carregandoBike.setVisibility(View.GONE);

                }
            });
        }else{


            bike.setFotoBikeUrl3("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181");


            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);



            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                // cadastra a bikeROubada no Maps de roubos

                firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
            }



        }







        if (uriImagem4!=null) {



            progressBar.setVisibility(View.VISIBLE);
            carregandoBike.setVisibility(View.VISIBLE);

            final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem4");
            UploadTask uploadTask = ref.putFile(uriImagem4);

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


                        bike.setFotoBikeUrl4(downloadUri.toString());


                        firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                        firebase.child(bike.getNumero_serie()).setValue(bike);


                        firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                        firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                        if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                            // cadastra a bikeROubada no Maps de roubos

                            firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                            firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
                        }

                        abrirAreaUsuario();
                        progressBar.setVisibility(View.GONE);
                        carregandoBike.setVisibility(View.GONE);



                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progressBar.setVisibility(View.GONE);
                    carregandoBike.setVisibility(View.GONE);

                }
            });
        }else{


            bike.setFotoBikeUrl4("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181");


            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);



            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                // cadastra a bikeROubada no Maps de roubos

                firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
            }

        }





        if (uriImagem5!=null) {

            progressBar.setVisibility(View.VISIBLE);
            carregandoBike.setVisibility(View.VISIBLE);

            final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem5");
            UploadTask uploadTask = ref.putFile(uriImagem5);

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

                        bike.setFotoBikeUrl5(downloadUri.toString());


                        firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                        firebase.child(bike.getNumero_serie()).setValue(bike);


                        firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                        firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);




                        if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                            // cadastra a bikeROubada no Maps de roubos

                            firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                            firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
                        }



                        abrirAreaUsuario();
                        progressBar.setVisibility(View.GONE);
                        carregandoBike.setVisibility(View.GONE);




                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progressBar.setVisibility(View.GONE);
                    carregandoBike.setVisibility(View.GONE);

                }
            });
        }else{


            bike.setFotoBikeUrl5("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181");


            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);



            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            if (radioButtonRoubada.isChecked() || radioButtonFurtada.isChecked()) {

                // cadastra a bikeROubada no Maps de roubos

                firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);
            }



        }




    }



    public void progressBar(){



        final  Thread timerTheread = new Thread(){


            @Override
            public void  run(){

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

                }catch (InterruptedException e){


                }finally {


                   // Toast.makeText(CadastroBike.this, "Tudo pronto", Toast.LENGTH_SHORT).show();



                }

            }


        };
        timerTheread.start();


    }




    public void updateProgress(final int timePassed){

        if (null !=progressBar){

            final int progress = progressBar.getMax() * timePassed /TIMER_RUNTINME;
            progressBar.setProgress(progress);

        }

    }








    private String getExtension(Uri uri){

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem1=data.getData();
            imagemBike1.setImageURI(uriImagem1);



        }


        if(requestCode == 2 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem2=data.getData();
            imagemBike2.setImageURI(uriImagem2);



        }


        if(requestCode == 3 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem3=data.getData();
            imagemBike3.setImageURI(uriImagem3);



        }


        if(requestCode == 4 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem4=data.getData();
            imagemBike4.setImageURI(uriImagem4);



        }



        if(requestCode == 5 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem5=data.getData();
            imagemBike5.setImageURI(uriImagem5);



        }

    }




}