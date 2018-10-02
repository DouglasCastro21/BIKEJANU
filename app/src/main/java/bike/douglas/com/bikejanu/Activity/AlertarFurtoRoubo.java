package bike.douglas.com.bikejanu.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.security.acl.Group;
import java.time.Duration;
import java.util.Calendar;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Fragments.Tab2Data;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;




public class AlertarFurtoRoubo extends AppCompatActivity implements View.OnClickListener {


    private EditText alertaNumero;
    private EditText alertaRua;
    private EditText alertaBairro;
    private EditText alertaDate;
    private EditText alertaHora;
    private EditText Boletim;
    private EditText alertaDescricao;
    private  RadioButton radioButtonFurtada;
    private RadioButton   status;



// se não repetir os dados da tela cadastro os dados são exluidos

    public  TextView numero_serie;
    private TextView marca;
    private TextView modelo;
    private TextView cor;
    private TextView descricao;
    private String statusBike;
    private TextView txtStatus;



    // campos texto

    private TextView txtmensagem1;
    private TextView txtmensagem2;
    private TextView txtRua;
    private TextView txtDataHora;
    private TextView txtBairro;
    private TextView txtNumero;
    private TextView txtObservacao;
    private TextView txtBoletim;


    public  Bike bike;
    private DatabaseReference firebase;
    private int dia,mes,ano,hora,minuto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertar_furto_roubo);

// botoes


        radioButtonFurtada    =(RadioButton)findViewById(R.id.alertaFurtadaID);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);
       RadioButton radioButtonRoubada     =(RadioButton)findViewById(R.id.alertaRoubadaID);
       RadioButton radioButtonNadaConsta  =(RadioButton)findViewById(R.id.alertaNadaConstaID);
       Button  finalizar                  = (Button)  findViewById(R.id.finalizarID);




        alertaNumero    = (EditText)findViewById(R.id.alertaNumeroID);
        alertaRua       = (EditText)findViewById(R.id.alertaRuaID);
        alertaBairro    = (EditText)findViewById(R.id.alertaBairroID);
        alertaDate      = (EditText)findViewById(R.id.alertaDataID);
        alertaHora      = (EditText)findViewById(R.id.alertaHoraID);
        Boletim         = (EditText)findViewById(R.id.BoletimID);
        alertaDescricao = (EditText)findViewById(R.id.alertaDescricaoID);



        numero_serie     =  (TextView) findViewById(R.id.test1ID);
        marca            =  (TextView) findViewById(R.id.test2ID);
        modelo           =  (TextView) findViewById(R.id.test3ID);
        cor              =  (TextView) findViewById(R.id.test4ID);
        descricao        =  (TextView) findViewById(R.id.test5ID);
        txtStatus        =   (TextView) findViewById(R.id.statusSituacaoID);



        // campos txt

        txtRua           = (TextView) findViewById(R.id.txtRuaID);
        txtBairro        = (TextView) findViewById(R.id.txtBairroID);
        txtBoletim       = (TextView) findViewById(R.id.txtBoletimID);
        txtDataHora      = (TextView) findViewById(R.id.txtDataHoraID);
        txtNumero        = (TextView) findViewById(R.id.txtNumeroID);
        txtObservacao    = (TextView) findViewById(R.id.txtObservacaoID);
        txtmensagem1     = (TextView) findViewById(R.id.casoID);
      //  txtmensagem2     = (TextView) findViewById(R.id.txtmensagem2ID);


        alertaDate.setOnClickListener(AlertarFurtoRoubo.this);
        alertaHora.setOnClickListener(AlertarFurtoRoubo.this);





        // rebece o dados do Bike Adapter por parametro passada pela tela cadastro
        Intent intent = getIntent();

        if(intent !=null){

            Bundle params = intent.getExtras();

            if (params !=null){

                //dados do alertaRua
                String alertaRua = params.getString("alertaRua");
                TextView alertaRuaText = (TextView) findViewById(R.id.alertaRuaID);
                alertaRuaText.setText(alertaRua);

                //dados do alertaNumero
                String alertaNumero = params.getString("alertaNumero");
                TextView alertaNumeroText = (TextView) findViewById(R.id.alertaNumeroID);
                alertaNumeroText.setText(alertaNumero);

                // dados do alertaBairro
                String alertaBairro = params.getString("alertaBairro");
                TextView alertaBairroText = (TextView) findViewById(R.id.alertaBairroID);
                alertaBairroText.setText(alertaBairro);


                // dados do alertaBairro
                String alertaDescricao = params.getString("alertadescricao");
                TextView alertaDescricaoText = (TextView) findViewById(R.id.alertaDescricaoID);
                alertaDescricaoText.setText(alertaDescricao);

                // dados do alertaDate
                String alertaDate = params.getString("alertaData");
                TextView alertaDateText = (TextView) findViewById(R.id.alertaDataID);
                alertaDateText.setText(alertaDate);


                // dados do alertaHora
                String alertaHora = params.getString("alertaHora");
                TextView alertaHoraText = (TextView) findViewById(R.id.alertaHoraID);
                alertaHoraText.setText(alertaHora);

                // dados do alertaHora
                String alertaBoletim = params.getString("alertaBoletim");
                TextView alertaBoletimText = (TextView) findViewById(R.id.BoletimID);
                alertaBoletimText.setText(alertaBoletim);



                /// DADOS QUE NÃO vão ficar envisiveis na tela Alerta furto e roubo

                // dados do numero serie
                String numero_serie = params.getString("numero_serie");
                TextView numero_serieText = (TextView) findViewById(R.id.test1ID);
                numero_serieText.setText(numero_serie);


                //dados da marca
                String marca = params.getString("marca");
                TextView marcaText = (TextView) findViewById(R.id.test2ID);
                marcaText.setText(marca);



                //dados do modelo
                String modelo = params.getString("modelo");
                TextView modeloText = (TextView) findViewById(R.id.test3ID);
                modeloText.setText(modelo);


                // dados da cor
                String cor = params.getString("cor");
                TextView corText = (TextView) findViewById(R.id.test4ID);
                corText.setText(cor);

                // dados do numero serie
                String descricao = params.getString("descricao");
                TextView descricaoText = (TextView) findViewById(R.id.test5ID);
                descricaoText.setText(descricao);

                String statuss = params.getString("status");
                TextView statussText = (TextView) findViewById(R.id.statusSituacaoID);
                statussText.setText(statuss);


                statusBike = statuss;


            }
        }



  //  radioButtonNadaConsta.toggle();




       if(statusBike.equals("Furtada")){

           radioButtonFurtada.toggle();
           status = (RadioButton)findViewById(R.id.alertaFurtadaID);


           alertaNumero.setVisibility(View.VISIBLE);
           alertaRua.setVisibility(View.VISIBLE);
           alertaBairro.setVisibility(View.VISIBLE);
           alertaDescricao.setVisibility(View.VISIBLE);
           alertaDate.setVisibility(View.VISIBLE);
           alertaHora.setVisibility(View.VISIBLE);
           Boletim.setVisibility(View.VISIBLE);



           // campos txt
           txtRua.setVisibility(View.VISIBLE);
           txtBairro.setVisibility(View.VISIBLE);
           txtNumero.setVisibility(View.VISIBLE);
           txtDataHora.setVisibility(View.VISIBLE);
           txtBoletim.setVisibility(View.VISIBLE);
           txtObservacao.setVisibility(View.VISIBLE);
           txtmensagem1.setVisibility(View.VISIBLE);


       }

       if(statusBike.equals("Roubada")){

           radioButtonRoubada.toggle();
           status = (RadioButton)findViewById(R.id.alertaRoubadaID);




           alertaNumero.setVisibility(View.VISIBLE);
           alertaRua.setVisibility(View.VISIBLE);
           alertaBairro.setVisibility(View.VISIBLE);
           alertaDescricao.setVisibility(View.VISIBLE);
           alertaDate.setVisibility(View.VISIBLE);
           alertaHora.setVisibility(View.VISIBLE);
           Boletim.setVisibility(View.VISIBLE);



           // campos txt
           txtRua.setVisibility(View.VISIBLE);
           txtBairro.setVisibility(View.VISIBLE);
           txtNumero.setVisibility(View.VISIBLE);
           txtDataHora.setVisibility(View.VISIBLE);
           txtBoletim.setVisibility(View.VISIBLE);
           txtObservacao.setVisibility(View.VISIBLE);
           txtmensagem1.setVisibility(View.VISIBLE);


       }

       if(statusBike.equals("Sem Restrições")){

           radioButtonNadaConsta.toggle();
           status = (RadioButton)findViewById(R.id.alertaNadaConstaID);

           alertaNumero.setVisibility(View.GONE);
           alertaRua.setVisibility(View.GONE);
           alertaBairro.setVisibility(View.GONE);
           alertaDescricao.setVisibility(View.GONE);
           alertaDate.setVisibility(View.GONE);
           alertaHora.setVisibility(View.GONE);
           Boletim.setVisibility(View.GONE);

           txtRua.setVisibility(View.GONE);
           txtBairro.setVisibility(View.GONE);
           txtNumero.setVisibility(View.GONE);
           txtDataHora.setVisibility(View.GONE);
           txtBoletim.setVisibility(View.GONE);
           txtObservacao.setVisibility(View.GONE);
           txtmensagem1.setVisibility(View.GONE);



       }




        radioButtonNadaConsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            caixaDialogoNadaConsta();
                status = (RadioButton)findViewById(R.id.alertaNadaConstaID);


                alertaNumero.setVisibility(View.GONE);
                alertaRua.setVisibility(View.GONE);
                alertaBairro.setVisibility(View.GONE);
                alertaDescricao.setVisibility(View.GONE);
                alertaDate.setVisibility(View.GONE);
                alertaHora.setVisibility(View.GONE);
                Boletim.setVisibility(View.GONE);

                txtRua.setVisibility(View.GONE);
                txtBairro.setVisibility(View.GONE);
                txtNumero.setVisibility(View.GONE);
                txtDataHora.setVisibility(View.GONE);
                txtBoletim.setVisibility(View.GONE);
                txtObservacao.setVisibility(View.GONE);
                txtmensagem1.setVisibility(View.GONE);


            }
        });


        radioButtonFurtada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                caixaDialogoFurtada();
                status = (RadioButton)findViewById(R.id.alertaFurtadaID);



                alertaNumero.setVisibility(View.VISIBLE);
                alertaRua.setVisibility(View.VISIBLE);
                alertaBairro.setVisibility(View.VISIBLE);
                alertaDescricao.setVisibility(View.VISIBLE);
                alertaDate.setVisibility(View.VISIBLE);
                alertaHora.setVisibility(View.VISIBLE);
                Boletim.setVisibility(View.VISIBLE);



                // campos txt
                txtRua.setVisibility(View.VISIBLE);
                txtBairro.setVisibility(View.VISIBLE);
                txtNumero.setVisibility(View.VISIBLE);
                txtDataHora.setVisibility(View.VISIBLE);
                txtBoletim.setVisibility(View.VISIBLE);
                txtObservacao.setVisibility(View.VISIBLE);
                txtmensagem1.setVisibility(View.VISIBLE);

                alertaDate.requestFocus();



            }
        });



        radioButtonRoubada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                caixaDialogoRoubada();
                status = (RadioButton)findViewById(R.id.alertaRoubadaID);



                alertaNumero.setVisibility(View.VISIBLE);
                alertaRua.setVisibility(View.VISIBLE);
                alertaBairro.setVisibility(View.VISIBLE);
                alertaDescricao.setVisibility(View.VISIBLE);
                alertaDate.setVisibility(View.VISIBLE);
                alertaHora.setVisibility(View.VISIBLE);
                Boletim.setVisibility(View.VISIBLE);



                // campos txt
                txtRua.setVisibility(View.VISIBLE);
                txtBairro.setVisibility(View.VISIBLE);
                txtNumero.setVisibility(View.VISIBLE);
                txtDataHora.setVisibility(View.VISIBLE);
                txtBoletim.setVisibility(View.VISIBLE);
                txtObservacao.setVisibility(View.VISIBLE);
                txtmensagem1.setVisibility(View.VISIBLE);

                alertaDate.requestFocus();


            }
        });




        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                inicializarElementos();
                caixaDialogoConfirmarFurtoRoubo();




            }
        });

    }






    private void inicializarElementos(){


        bike = new Bike();
        bike.setAlertaNumero(alertaNumero.getText().toString());
        bike.setAlertaRua(alertaRua.getText().toString());
        bike.setAlertaBairro(alertaBairro.getText().toString());
        bike.setAlertaDate(alertaDate.getText().toString());
        bike.setAlertaHora(alertaHora.getText().toString());
        bike.setBoletim(Boletim.getText().toString());
        bike.setAlertaDescricao(alertaDescricao.getText().toString());
        bike.setStatus(status.getText().toString());


////
        bike.setNumero_serie(numero_serie.getText().toString());
        bike.setMarca(marca.getText().toString());
        bike.setModelo(modelo.getText().toString());
        bike.setCor(cor.getText().toString());
        bike.setDescricao(descricao.getText().toString());


    }

    private void abrirAreaUsuario(){

        Intent intent = new Intent(AlertarFurtoRoubo.this ,AreaUsuario.class);
        startActivity(intent);
        finish();
    }





    private void caixaDialogoRoubada(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(AlertarFurtoRoubo.this);

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

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(AlertarFurtoRoubo.this);


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


    private void caixaDialogoNadaConsta(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(AlertarFurtoRoubo.this);

        // configurando dialogo

        alertaDialog.setTitle("Definição");


        alertaDialog.setMessage("Que possui ausência de regras, limitações ou imposições. Liberdade Total.");
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




    private void caixaDialogoConfirmarFurtoRoubo(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(AlertarFurtoRoubo.this);

        // configurando dialogo

        alertaDialog.setTitle("");


        alertaDialog.setMessage("Sua Bike será dada como " + bike.getStatus()+ ".Deseja continuar?");
        // alertaDialog.setCancelable(false);


        //conf botões
        alertaDialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {




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

                        Toast.makeText(AlertarFurtoRoubo.this, "A bike foi marcada como :" +bike.getStatus(), Toast.LENGTH_LONG).show();

                        // retorna a tela usuario

                        abrirAreaUsuario();

                    };


            }
        });

        alertaDialog.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertaDialog.create();
        alertaDialog.show();
    }


    public void onClick(View v) {

        if(v==alertaDate){

            final Calendar calendar = Calendar.getInstance();

            dia = calendar.get(Calendar.DAY_OF_MONTH);
            mes = calendar.get(Calendar.MONTH);
            ano = calendar.get(Calendar.YEAR);


            final DatePickerDialog datePickerDialog = new  DatePickerDialog(AlertarFurtoRoubo.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    alertaDate.setText(dayOfMonth +"/"+(month+1)+"/"+year);


                }
            }

                    ,ano,mes,dia);
            datePickerDialog.show();
            alertaHora.requestFocus();

        }
        if(v==alertaHora){

            final Calendar calendar = Calendar.getInstance();
            hora = calendar.get(Calendar.HOUR_OF_DAY);
            minuto = calendar.get(Calendar.MINUTE);

            final TimePickerDialog timePickerDialog= new TimePickerDialog(AlertarFurtoRoubo.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                    alertaHora.setText(hourOfDay +":"+minute);

                }
            },hora,minuto,false);
            timePickerDialog.show();



            }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }





}
