package bike.douglas.com.bikejanu.Activity;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.messaging.FirebaseMessaging;



import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;

import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Model.LocalBikesMaps;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;


public class AlertarFurtoRoubo extends AppCompatActivity implements View.OnClickListener {


    // notificaçaõ




    EditText edtTitle;
    EditText edtMessage;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAwk_VIUs:APA91bHiUb_rIjPC5V7mL3lRXEtY8lToNhP8kTTwAruQIYbQ8V9PwogTRu7pxRedG8bBAcU9VVBqckTV-KkUSnnHiJCRo0AWiIu0EJmuiw8W00HdfOWee0Tl4hVie8WvPX5Vno4sOrBY";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;





    // ate aq


    private EditText alertaNumero;


    public static  EditText alertaEstado;
     static     EditText alertaCidade;
     static     EditText alertaBairro;
     static     EditText alertaRua;



    private     EditText alertaDate;
    private     EditText alertaHora;
    private     EditText Boletim;
    private     EditText alertaDescricao;
    private     RadioButton radioButtonFurtada;
    private     RadioButton radioButtonRoubada;
    private     RadioButton radioButtonSemImpedimento;
    private     RadioButton radioButtonRecuperada;
    private     RadioButton   status;
    private     Button abrirMapa;

    // private static final String TAG = "AlertaFurtoRoubo";

   //se não repetir os dados da tela cadastro os dados são excluidos

    public  TextView numero_serie;
    private TextView marca;
    private TextView modelo;
    private TextView cor;
    private TextView descricao;
    private String statusBike;
    private TextView txtStatus;


    // campos texto

    private TextView txtmensagem1;
    private TextView procurePolicia;

    private TextView txtRua;
    private TextView txtDataHora;
    private TextView txtDataHora2;
    private TextView txtBoletim2;
    private TextView txtBairro;
    private TextView txtCidade;
    private TextView txtEstado;
    private TextView txtNumero;
    private TextView txtObservacao;
    private TextView txtBoletim;
    static String ativa = "negativa";


    private TextView imagem1;
    private TextView imagem2;
    private TextView imagem3;
    private TextView imagem4;
    private TextView imagem5;
    private TextView idUsuario;
    String numero_serie_Topic;
    String chaveUsuario;

    private TextView proprietario;
    private  TextView proprietarioID;

    public  Bike bike;
    public LocalBikesMaps localBikesMaps;

     static  TextView localLatitude;
     static  TextView localLongitude;


      int digitoMilitar=0;



    private DatabaseReference firebase;
    private int dia,mes,ano,hora,minuto;





    public int checarDeOndeVenho=0;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertar_furto_roubo);

// botoes


// sair do topic userABC
        FirebaseMessaging.getInstance().unsubscribeFromTopic("userABC");




        //    edtTitle = findViewById(R.id.editTituloID);
        //    edtMessage = findViewById(R.id.editMensagemID);

        Button btnSend = findViewById(R.id.finalizarID);





        abrirMapa = (Button) findViewById(R.id.abrirMapaID);
        radioButtonFurtada    =(RadioButton)findViewById(R.id.alertaFurtadaID);

        radioButtonSemImpedimento   =(RadioButton)findViewById(R.id.alertaSemImpedimentoID);


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);
        radioButtonRoubada     =(RadioButton)findViewById(R.id.alertaRoubadaID);
        radioButtonRecuperada  =(RadioButton)findViewById(R.id.alertaNadaConstaID);
        Button  finalizar                  = (Button)  findViewById(R.id.finalizarID);




       // alertaNumero    = (EditText)findViewById(R.id.alertaCidadeID);
        alertaEstado    = (EditText) findViewById(R.id.alertaEstadoID);
        alertaCidade    = (EditText) findViewById(R.id.alertacidadeID);
        alertaBairro    = (EditText)findViewById(R.id.alertaBairroID);
        alertaRua       = (EditText)findViewById(R.id.alertaRuaID);


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

        localLatitude    =  (TextView) findViewById(R.id.alertaLatitudeID);
        localLongitude    =  (TextView) findViewById(R.id.alertaLongitudeID);


        imagem1    =  (TextView) findViewById(R.id.alertaImagem1ID);
        imagem2    =  (TextView) findViewById(R.id.alertaImagem2ID);
        imagem3    =  (TextView) findViewById(R.id.alertaImagem3ID);
        imagem4    =  (TextView) findViewById(R.id.alertaImagem4ID);
        imagem5   =  (TextView) findViewById(R.id.alertaImagem5ID);

        // campos txt

        txtEstado          = (TextView) findViewById(R.id.textEstadoID);
        txtCidade           = (TextView) findViewById(R.id.textCidade);
        txtRua           = (TextView) findViewById(R.id.textalertaRuaID);
        txtBairro        = (TextView) findViewById(R.id.textAlertaBairroID);
        txtBoletim       = (TextView) findViewById(R.id.txtBoletimID);
        txtDataHora      = (TextView) findViewById(R.id.txtDataHoraID);
        txtNumero        = (TextView) findViewById(R.id.txtcadastroBikeCidadeID);
        txtObservacao    = (TextView) findViewById(R.id.txtObservacaoID);
        txtmensagem1     = (TextView) findViewById(R.id.casoID);



      txtBoletim2       = (TextView) findViewById(R.id.txtBoletimID2);
      txtDataHora2      = (TextView) findViewById(R.id.txtDataHoraID2);


        procurePolicia = (TextView) findViewById(R.id.procurePoliciaID);

        alertaDate.setOnClickListener(AlertarFurtoRoubo.this);
        alertaHora.setOnClickListener(AlertarFurtoRoubo.this);


        proprietario      = (TextView) findViewById(R.id.alertaProprietarioID);
        proprietarioID      = (TextView) findViewById(R.id.alertaChaveProprietarioID);




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
              //  String alertaNumero = params.getString("alertaNumero");
             //   TextView alertaNumeroText = (TextView) findViewById(R.id.alertaNumeroID);
              //  alertaNumeroText.setText(alertaNumero);

                // dados do alertaEstado
                String alertaEstado = params.getString("alertaEstado");
                TextView alertaEstadoText = (TextView) findViewById(R.id.alertaEstadoID);
                alertaEstadoText.setText(alertaEstado);


                // dados do alertaCidade
                String alertaCidade = params.getString("alertaCidade");
                TextView alertaCidadeText = (TextView) findViewById(R.id.alertacidadeID);
                alertaCidadeText.setText(alertaCidade);


                // dados do alertaBairro
                String alertaBairro = params.getString("alertaBairro");
                TextView alertaBairroText = (TextView) findViewById(R.id.alertaBairroID);
                alertaBairroText.setText(alertaBairro);


                // dados do alertaBairro
                String alertaDescricao = params.getString("alertadescricao");
                TextView alertaDescricaoText = (TextView) findViewById(R.id.alertaDescricaoID);
                alertaDescricaoText.setText(alertaDescricao);

                 // dados do alertaDate
                // String alertaDate = params.getString("alertaData");
               //  TextView alertaDateText = (TextView) findViewById(R.id.alertaDataID);
              // alertaDateText.setText(alertaDate);


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
                numero_serie_Topic = params.getString("numero_serie");
                TextView numero_serieText = (TextView) findViewById(R.id.test1ID);
                numero_serieText.setText(numero_serie_Topic);


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


                String Imagem1 = params.getString("Imagem1");
                TextView Imagem1Text = (TextView) findViewById(R.id.alertaImagem1ID);
                Imagem1Text.setText(Imagem1);

                String Imagem2 = params.getString("Imagem2");
                TextView Imagem2Text = (TextView) findViewById(R.id.alertaImagem2ID);
                Imagem2Text.setText(Imagem2);

                String Imagem3 = params.getString("Imagem3");
                TextView Imagem3Text = (TextView) findViewById(R.id.alertaImagem3ID);
                Imagem3Text.setText(Imagem3);

                String Imagem4 = params.getString("Imagem4");
                TextView Imagem4Text = (TextView) findViewById(R.id.alertaImagem4ID);
                Imagem4Text.setText(Imagem4);

                String Imagem5 = params.getString("Imagem5");
                TextView Imagem5Text = (TextView) findViewById(R.id.alertaImagem5ID);
                Imagem5Text.setText(Imagem5);



                String proprietario = params.getString("proprietario");
                TextView proprietarioText = (TextView) findViewById(R.id.alertaProprietarioID);
                proprietarioText.setText(proprietario);


                String proprietarioID = params.getString("proprietarioID");
                TextView proprietarioIDText = (TextView) findViewById(R.id.alertaChaveProprietarioID);
                proprietarioIDText.setText(proprietarioID);
                chaveUsuario = proprietarioID;


                String latitude = params.getString("latitude");
                TextView latitudeText = (TextView) findViewById(R.id.alertaLatitudeID);
                latitudeText.setText(latitude);


                String longitude = params.getString("longitude");
                TextView longitudeText = (TextView) findViewById(R.id.alertaLongitudeID);
                longitudeText.setText(longitude);



                String digitoValidador = params.getString("digitoValidador");
                TextView digitoValidadorText = (TextView) findViewById(R.id.digitoValidadorMudarStatusID);
                digitoValidadorText.setText(digitoValidador);


                if(digitoValidadorText.getText().toString().equals("01")){

                    digitoMilitar =1;


                }




                    statusBike = statuss;


            }
        }




        // sair do topic userABC
        FirebaseMessaging.getInstance().unsubscribeFromTopic("userABC");




        // inclui o usuario no topico que tem o numero da bike do mesmo // ou seja apenas ele esta nesse grupo

        if(digitoMilitar !=1){

             FirebaseMessaging.getInstance().subscribeToTopic(numero_serie_Topic);

        }





        if(digitoMilitar ==1){

            abrirMapa.setVisibility(View.GONE);
            alertaEstado.setVisibility(View.GONE);
            alertaCidade.setVisibility(View.GONE);
            alertaRua.setVisibility(View.GONE);
            alertaBairro.setVisibility(View.GONE);
            alertaDescricao.setVisibility(View.GONE);
            alertaDate.setVisibility(View.GONE);
            alertaHora.setVisibility(View.GONE);
            Boletim.setVisibility(View.GONE);


            txtRua.setVisibility(View.GONE);
            txtBairro.setVisibility(View.GONE);

            txtDataHora.setVisibility(View.GONE);
            txtBoletim.setVisibility(View.GONE);
            txtObservacao.setVisibility(View.GONE);
            txtmensagem1.setVisibility(View.GONE);
            txtEstado.setVisibility(View.GONE);
            txtCidade.setVisibility(View.GONE);


        //    Toast.makeText(AlertarFurtoRoubo.this, "DIGITO :" +digitoMilitar, Toast.LENGTH_LONG).show();







        }












        //abrir mapa


        abrirMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ativa = "positiva";

                Intent intent = new Intent(AlertarFurtoRoubo.this ,MapsActivity2.class);
                startActivity(intent);

            }
        });


  //  radioButtonNadaConsta.toggle();




       if(statusBike.equals("Furtada")){

           radioButtonFurtada.toggle();

           radioButtonRoubada.setVisibility(View.GONE);
           radioButtonSemImpedimento.setVisibility(View.GONE);
           status = (RadioButton)findViewById(R.id.alertaFurtadaID);

          procurePolicia.setVisibility(View.GONE);

          abrirMapa.setVisibility(View.VISIBLE);

          // alertaNumero.setVisibility(View.VISIBLE);
           alertaEstado.setVisibility(View.VISIBLE);
           alertaCidade.setVisibility(View.VISIBLE);
           alertaRua.setVisibility(View.VISIBLE);
           alertaBairro.setVisibility(View.VISIBLE);
           alertaDescricao.setVisibility(View.VISIBLE);
           alertaDate.setVisibility(View.VISIBLE);
           alertaHora.setVisibility(View.VISIBLE);
           Boletim.setVisibility(View.VISIBLE);



           // campos txt
           txtRua.setVisibility(View.VISIBLE);
           txtBairro.setVisibility(View.VISIBLE);
       //    txtNumero.setVisibility(View.VISIBLE);
           txtDataHora.setVisibility(View.VISIBLE);
           txtBoletim.setVisibility(View.VISIBLE);
           txtObservacao.setVisibility(View.VISIBLE);
           txtmensagem1.setVisibility(View.VISIBLE);
           txtEstado.setVisibility(View.VISIBLE);
           txtCidade.setVisibility(View.VISIBLE);




       }

       if(statusBike.equals("Roubada")){

           radioButtonRoubada.toggle();
           radioButtonFurtada.setVisibility(View.GONE);
           radioButtonSemImpedimento.setVisibility(View.GONE);

           procurePolicia.setVisibility(View.GONE);


           status = (RadioButton)findViewById(R.id.alertaRoubadaID);


           abrirMapa.setVisibility(View.VISIBLE);

         //  alertaNumero.setVisibility(View.VISIBLE);
          alertaEstado.setVisibility(View.VISIBLE);
          alertaCidade.setVisibility(View.VISIBLE);
           alertaRua.setVisibility(View.VISIBLE);
           alertaBairro.setVisibility(View.VISIBLE);
           alertaDescricao.setVisibility(View.VISIBLE);
           alertaDate.setVisibility(View.VISIBLE);
           alertaHora.setVisibility(View.VISIBLE);
           Boletim.setVisibility(View.VISIBLE);




           // campos txt
           txtRua.setVisibility(View.VISIBLE);
           txtBairro.setVisibility(View.VISIBLE);
        //   txtNumero.setVisibility(View.VISIBLE);
           txtDataHora.setVisibility(View.VISIBLE);
           txtBoletim.setVisibility(View.VISIBLE);
           txtObservacao.setVisibility(View.VISIBLE);
           txtmensagem1.setVisibility(View.VISIBLE);
           txtEstado.setVisibility(View.VISIBLE);
           txtCidade.setVisibility(View.VISIBLE);



        //sem restriçÕ Ja foi roubada um dia


       }

       // tirar Sem Restrições depois  ..

       if(statusBike.equals("Recuperada")){

           radioButtonRecuperada.toggle();
           status = (RadioButton)findViewById(R.id.alertaNadaConstaID);


           radioButtonSemImpedimento.setVisibility(View.VISIBLE);
           radioButtonRoubada.setVisibility(View.GONE);
           radioButtonFurtada.setVisibility(View.GONE);



           abrirMapa.setVisibility(View.GONE);
           alertaEstado.setVisibility(View.GONE);
           alertaCidade.setVisibility(View.GONE);
           alertaRua.setVisibility(View.GONE);
           alertaBairro.setVisibility(View.GONE);
           alertaDescricao.setVisibility(View.GONE);
           alertaDate.setVisibility(View.GONE);
           alertaHora.setVisibility(View.GONE);
           Boletim.setVisibility(View.GONE);


           txtRua.setVisibility(View.GONE);
           txtBairro.setVisibility(View.GONE);

           txtDataHora.setVisibility(View.GONE);
           txtBoletim.setVisibility(View.GONE);
           txtObservacao.setVisibility(View.GONE);
           txtmensagem1.setVisibility(View.GONE);
           txtEstado.setVisibility(View.GONE);
           txtCidade.setVisibility(View.GONE);



           // exclui usuario no topico que tem o numero da bike do mesmo // ou seja apenas ele esta nesse grupo

           if(digitoMilitar !=1){

               FirebaseMessaging.getInstance().unsubscribeFromTopic(numero_serie_Topic);

           }



       }




        if(statusBike.equals("Sem Impedimento")){

            radioButtonSemImpedimento.toggle();
            status = (RadioButton)findViewById(R.id.alertaSemImpedimentoID);
            radioButtonRecuperada.setVisibility(View.GONE);


            procurePolicia.setVisibility(View.GONE);

            abrirMapa.setVisibility(View.GONE);
            // alertaNumero.setVisibility(View.VISIBLE);
            alertaEstado.setVisibility(View.GONE);
            alertaCidade.setVisibility(View.GONE);
            alertaRua.setVisibility(View.GONE);
            alertaBairro.setVisibility(View.GONE);
            alertaDescricao.setVisibility(View.GONE);
            alertaDate.setVisibility(View.GONE);
            alertaHora.setVisibility(View.GONE);
            Boletim.setVisibility(View.GONE);



            // campos txt
            txtRua.setVisibility(View.GONE);
            txtBairro.setVisibility(View.GONE);
            //    txtNumero.setVisibility(View.VISIBLE);
            txtDataHora.setVisibility(View.GONE);
            txtBoletim.setVisibility(View.GONE);
            txtObservacao.setVisibility(View.GONE);
            txtmensagem1.setVisibility(View.GONE);
            txtEstado.setVisibility(View.GONE);
            txtCidade.setVisibility(View.GONE);




        }


        radioButtonRecuperada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 caixaDialogoNadaConsta();
                status = (RadioButton)findViewById(R.id.alertaNadaConstaID);

                procurePolicia.setVisibility(View.GONE);

                abrirMapa.setVisibility(View.GONE);
                alertaEstado.setVisibility(View.GONE);
                alertaCidade.setVisibility(View.GONE);
                alertaRua.setVisibility(View.GONE);
                alertaBairro.setVisibility(View.GONE);
                alertaDescricao.setVisibility(View.GONE);
                alertaDate.setVisibility(View.VISIBLE);
                alertaHora.setVisibility(View.VISIBLE);
                Boletim.setVisibility(View.VISIBLE);

                txtRua.setVisibility(View.GONE);
                txtBairro.setVisibility(View.GONE);
             //   txtNumero.setVisibility(View.GONE);
                txtDataHora.setVisibility(View.GONE);

                txtBoletim.setVisibility(View.GONE);
                txtObservacao.setVisibility(View.GONE);
                txtmensagem1.setVisibility(View.GONE);
                txtEstado.setVisibility(View.GONE);
                txtCidade.setVisibility(View.GONE);





                txtDataHora2.setVisibility(View.VISIBLE);
                txtBoletim2.setVisibility(View.VISIBLE);
                alertaDate.requestFocus();






                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                // OU
                SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm");

                Date data = new Date();

                Calendar  cal = Calendar.getInstance();
                cal.setTime(data);

                Date data_atual = cal.getTime();

                String  data_completa = dateFormat.format(data_atual);

                String hora_atual = dateFormat_hora.format(data_atual);

                Log.i("data_completa", data_completa);
                Log.i("data_atual", data_atual.toString());
                Log.i("hora_atual", hora_atual);

                alertaDate.setText(data_completa);
                alertaHora.setText(hora_atual);








                if(digitoMilitar !=1){

                    FirebaseMessaging.getInstance().unsubscribeFromTopic(numero_serie_Topic);

                }








            }
        });







        radioButtonSemImpedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caixaDialogoSemImpedimento();
                status = (RadioButton)findViewById(R.id.alertaSemImpedimentoID);

                procurePolicia.setVisibility(View.GONE);

                abrirMapa.setVisibility(View.GONE);
                alertaEstado.setVisibility(View.GONE);
                alertaCidade.setVisibility(View.GONE);
                alertaRua.setVisibility(View.GONE);
                alertaBairro.setVisibility(View.GONE);
                alertaDescricao.setVisibility(View.GONE);
                alertaDate.setVisibility(View.GONE);
                alertaHora.setVisibility(View.GONE);
                Boletim.setVisibility(View.GONE);

                txtRua.setVisibility(View.GONE);
                txtBairro.setVisibility(View.GONE);
                //   txtNumero.setVisibility(View.GONE);
                txtDataHora.setVisibility(View.GONE);
                txtBoletim.setVisibility(View.GONE);
                txtObservacao.setVisibility(View.GONE);
                txtmensagem1.setVisibility(View.GONE);
                txtEstado.setVisibility(View.GONE);
                txtCidade.setVisibility(View.GONE);





                txtDataHora2.setVisibility(View.GONE);
                txtBoletim2.setVisibility(View.GONE);



            }
        });





        radioButtonFurtada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                caixaDialogoFurtada();
                status = (RadioButton)findViewById(R.id.alertaFurtadaID);

                procurePolicia.setVisibility(View.GONE);
                abrirMapa.setVisibility(View.VISIBLE);
             //   alertaNumero.setVisibility(View.VISIBLE);
                alertaEstado.setVisibility(View.VISIBLE);
                alertaCidade.setVisibility(View.VISIBLE);
                alertaRua.setVisibility(View.VISIBLE);
                alertaBairro.setVisibility(View.VISIBLE);
                alertaDescricao.setVisibility(View.VISIBLE);
                alertaDate.setVisibility(View.VISIBLE);
                alertaHora.setVisibility(View.VISIBLE);
                Boletim.setVisibility(View.VISIBLE);



                // campos txt
                txtEstado.setVisibility(View.VISIBLE);
                txtCidade.setVisibility(View.VISIBLE);
                txtRua.setVisibility(View.VISIBLE);
                txtBairro.setVisibility(View.VISIBLE);
              //  txtNumero.setVisibility(View.VISIBLE);
                txtDataHora.setVisibility(View.VISIBLE);
                txtBoletim.setVisibility(View.VISIBLE);
                txtObservacao.setVisibility(View.VISIBLE);
                txtmensagem1.setVisibility(View.VISIBLE);


                txtDataHora2.setVisibility(View.GONE);
                txtBoletim2.setVisibility(View.GONE);

                alertaDate.requestFocus();



                SimpleDateFormat dateFormat =      new SimpleDateFormat("dd/MM/yyyy");
                // OU
                SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm");

                Date data = new Date();

                Calendar  cal = Calendar.getInstance();
                cal.setTime(data);

                Date data_atual = cal.getTime();

                String data_completa = dateFormat.format(data_atual);

                String hora_atual = dateFormat_hora.format(data_atual);

                Log.i("data_completa", data_completa);
                Log.i("data_atual", data_atual.toString());
                Log.i("hora_atual", hora_atual);

                alertaDate.setText(data_completa);
                alertaHora.setText(hora_atual);



                // sai do topic userABC

                FirebaseMessaging.getInstance().unsubscribeFromTopic("userABC");



            }
        });



        radioButtonRoubada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        procurePolicia.setVisibility(View.GONE);

                caixaDialogoRoubada();
                status = (RadioButton)findViewById(R.id.alertaRoubadaID);


                abrirMapa.setVisibility(View.VISIBLE);
              //  alertaNumero.setVisibility(View.VISIBLE);
                alertaEstado.setVisibility(View.VISIBLE);
                alertaCidade.setVisibility(View.VISIBLE);
                alertaRua.setVisibility(View.VISIBLE);
                alertaBairro.setVisibility(View.VISIBLE);
                alertaDescricao.setVisibility(View.VISIBLE);
                alertaDate.setVisibility(View.VISIBLE);
                alertaHora.setVisibility(View.VISIBLE);
                Boletim.setVisibility(View.VISIBLE);



                // campos txt
                txtEstado.setVisibility(View.VISIBLE);
                txtCidade.setVisibility(View.VISIBLE);
                txtRua.setVisibility(View.VISIBLE);
                txtBairro.setVisibility(View.VISIBLE);
              //  txtNumero.setVisibility(View.VISIBLE);
                txtDataHora.setVisibility(View.VISIBLE);
                txtBoletim.setVisibility(View.VISIBLE);
                txtObservacao.setVisibility(View.VISIBLE);
                txtmensagem1.setVisibility(View.VISIBLE);


                txtDataHora2.setVisibility(View.GONE);
                txtBoletim2.setVisibility(View.GONE);

                alertaDate.requestFocus();


                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
               // OU
                SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm");

                Date data = new Date();

                Calendar  cal = Calendar.getInstance();
                cal.setTime(data);

                Date data_atual = cal.getTime();

                String  data_completa = dateFormat.format(data_atual);

                String hora_atual = dateFormat_hora.format(data_atual);

                Log.i("data_completa", data_completa);
            //    Log.i("data_atual", data_atual.toString());
                Log.i("hora_atual", hora_atual);

                   alertaDate.setText(data_completa);
                   alertaHora.setText(hora_atual);



                // sai do topic userABC

                FirebaseMessaging.getInstance().unsubscribeFromTopic("userABC");



            }
        });



        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   FirebaseMessaging.getInstance().unsubscribeFromTopic("userABC");

                inicializarElementos();
                caixaDialogoConfirmarFurtoRoubo();



            }
        });

    }





    public void enviarNotificacao(){



        TOPIC = "/topics/userABC"; //topic must match with what the receiver subscribed to
        NOTIFICATION_TITLE =   "Bike Furtada/Roubada";
        NOTIFICATION_MESSAGE = "Click para mais detalhes";

        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();

        try {
            notifcationBody.put("title",   NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

                notification.put("to", TOPIC);


            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }

        sendNotification(notification);


    }



    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                       // edtTitle.setText("");
                       // edtMessage.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AlertarFurtoRoubo.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }



    // aqui a classe


    private void inicializarElementos(){


        bike = new Bike();
        localBikesMaps = new LocalBikesMaps();

        bike.setAlertaEstado(alertaEstado.getText().toString());
        bike.setAlertaCidade(alertaCidade.getText().toString());

        bike.setAlertaRua(alertaRua.getText().toString());
        bike.setAlertaBairro(alertaBairro.getText().toString());
        bike.setAlertaDate(alertaDate.getText().toString());
        bike.setAlertaHora(alertaHora.getText().toString());
        bike.setBoletim(Boletim.getText().toString());
        bike.setAlertaDescricao(alertaDescricao.getText().toString());


        bike.setStatus(status.getText().toString());

        bike.setProprietario(proprietario.getText().toString());
        bike.setProprietarioID(proprietarioID.getText().toString());



////
        bike.setNumero_serie(numero_serie.getText().toString());
        bike.setMarca(marca.getText().toString());
        bike.setModelo(modelo.getText().toString());
        bike.setCor(cor.getText().toString());
        bike.setDescricao(descricao.getText().toString());

        bike.setLatitude(localLatitude.getText().toString());
        bike.setLongitude(localLongitude.getText().toString());

        localBikesMaps.setLatitude(localLatitude.getText().toString().trim());
        localBikesMaps.setLongitude(localLongitude.getText().toString().trim());

        bike.setFotoBikeUrl1(imagem1.getText().toString());
        bike.setFotoBikeUrl2(imagem2.getText().toString());
        bike.setFotoBikeUrl3(imagem3.getText().toString());
        bike.setFotoBikeUrl4(imagem4.getText().toString());
        bike.setFotoBikeUrl5(imagem5.getText().toString());



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






    private void caixaDialogoSemImpedimento(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(AlertarFurtoRoubo.this);

        // configurando dialogo

        alertaDialog.setTitle("Definição");


        alertaDialog.setMessage("Condição ou qualidade de algo que se encontra disponível, ou seja, livre e Desempedido");
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


        alertaDialog.setMessage("Algo que se recuperou, que se adquiriu de novo.");
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


        // sai do topic userABC  E numero de serie

        FirebaseMessaging.getInstance().unsubscribeFromTopic("userABC");




        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(AlertarFurtoRoubo.this);

        // configurando dialogo

        alertaDialog.setTitle("");


        alertaDialog.setMessage("Sua Bike será dada como " + bike.getStatus()+ ".Deseja continuar?");
        // alertaDialog.setCancelable(false);


        //conf botões
        alertaDialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FirebaseMessaging.getInstance().unsubscribeFromTopic("userABC");


                //  incluirUsuarioEmTopico();


                     notificacaoRoubadaFurtada();
                     notificacaoRecuperada();


                    // recupera autenticão do usuario local

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user != null) {



                        String name = user.getDisplayName();
                        String email = user.getEmail();

                        // converte o email pra base 64
                        String identificadorUsuario= Base64Custom.codificarBase64(email);




                        if(digitoMilitar ==1){


                            // cadastra no nó usuario dono da bike
                            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                            firebase.child(chaveUsuario).child(bike.getNumero_serie()).setValue(bike);


                        }else

                        {

                            // cadastra no nó usuario logado
                            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                        }

                        // cadastra a bike no nó todas as bikes
                        firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                        firebase.child(bike.getNumero_serie()).setValue(bike);

                        // cadastra a localização ndo roubo

                        firebase = Configuracao_Firebase.getFirebase().child("LocalMaps");
                        firebase.child(bike.getNumero_serie()).setValue(localBikesMaps);


                        Toast.makeText(AlertarFurtoRoubo.this, "A bike foi marcada como :" +bike.getStatus(), Toast.LENGTH_LONG).show();

                        // retorna a tela usuario

                          abrirAreaUsuario();

                    }


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







// inclui o usuario no topico que tem o numero da bike do mesmo // ou seja apenas ele esta nesse grupo
public void incluirUsuarioEmTopico(){



   // FirebaseMessaging.getInstance().subscribeToTopic(numero_serie.toString());


}



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



    public void notificacaoRoubadaFurtada(){

       if(radioButtonFurtada.isChecked() || radioButtonRoubada.isChecked()){

           enviarNotificacao();

       }

    }


   public void notificacaoRecuperada() {


       if (radioButtonRecuperada.isChecked()) {


           // exclui usuario no topico que tem o numero da bike do mesmo // ou seja apenas ele esta nesse grupo

           if(digitoMilitar !=1){

               FirebaseMessaging.getInstance().unsubscribeFromTopic(numero_serie_Topic);



               TOPIC = "/topics/"+0000; //topic must match with what the receiver subscribed to

           }else{

               TOPIC = "/topics/"+numero_serie_Topic; //topic must match with what the receiver subscribed to



           }



           NOTIFICATION_TITLE = "Bike Recuperada";
           NOTIFICATION_MESSAGE = "Click para mais detalhes";

           JSONObject notification = new JSONObject();
           JSONObject notifcationBody = new JSONObject();

           try {

               notifcationBody.put("title", NOTIFICATION_TITLE);
               notifcationBody.put("message", NOTIFICATION_MESSAGE);
               notifcationBody.put("","");

               notification.put("to", TOPIC);


               notification.put("data", notifcationBody);
           } catch (JSONException e) {
               Log.e(TAG, "onCreate: " + e.getMessage());
           }

           sendNotificationRecuperada(notification);

       }

   }





    private void sendNotificationRecuperada(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        // edtTitle.setText("");
                        // edtMessage.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AlertarFurtoRoubo.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    public static class MySingleton {
        private static MySingleton instance;
        private RequestQueue requestQueue;
        private Context ctx;

        private MySingleton(Context context) {
            ctx = context;
            requestQueue = getRequestQueue();
        }

        public static synchronized MySingleton getInstance(Context context) {
            if (instance == null) {
                instance = new MySingleton(context);
            }
            return instance;
        }

        public RequestQueue getRequestQueue() {
            if (requestQueue == null) {
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
            }
            return requestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }

    }


}
