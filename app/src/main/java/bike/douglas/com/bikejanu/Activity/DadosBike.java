package bike.douglas.com.bikejanu.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Fragments.Galeria_Bike;
import bike.douglas.com.bikejanu.Fragments.Imagem1;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class DadosBike extends AppCompatActivity  {
    String Numero_serie;
    String dadosImagem1;

    DatabaseReference databaseReferenceBike = FirebaseDatabase.getInstance().getReference();
   private CircleImageView imageViewAbrirImagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_bike);





       imageViewAbrirImagens = (CircleImageView) findViewById(R.id.imagemAbrirMaisImagensID);


        TextView dadosNumero_serie = (TextView) findViewById(R.id.dadosNumeroSerieID);
        TextView dadosMarca = (TextView) findViewById(R.id.dadosMarcaID);
        EditText dadosModelo = (EditText) findViewById(R.id.modeloID);
        EditText dadosDor = (EditText) findViewById(R.id.corID);
        TextView dadosDescricao = (TextView) findViewById(R.id.dadosDescricaoID);



        TextView dadosAlertaData = (TextView) findViewById(R.id.dadoDiaID);
        TextView dadosAlertaHora = (TextView) findViewById(R.id.dadoHoraID);
        TextView dadosEstado = (TextView) findViewById(R.id.dadosEstadoID);
        TextView dadosCidade = (TextView) findViewById(R.id.dadosCidadeID);
        TextView dadosBairro = (TextView) findViewById(R.id.dadosBairroID);
        TextView dadosRua = (TextView) findViewById(R.id.dadoRuaID);
       // TextView dadosNumero = (TextView) findViewById(R.id.dadosNumeroID);
        TextView dadosBoletim = (TextView) findViewById(R.id.dadosBoletimID);
        TextView dadosInfAuxiliar = (TextView) findViewById(R.id.dadosInformacaoID);
        TextView dadosStatus = (TextView) findViewById(R.id.dadosStatusID);








        //CAMPOS TEXTO

        TextView  txtmensagem1  = (TextView) findViewById(R.id.textTipoCrimeID);
        TextView  txtmensagem2  = (TextView) findViewById(R.id.textCasoVerifiqueID);
        TextView  txtRua        = (TextView) findViewById(R.id.textRuaID);
        TextView  txtEstado     = (TextView) findViewById(R.id.textDadosEstadoID);
        TextView  txtCidade    = (TextView) findViewById(R.id.textdadosCidadeID);
        TextView  txtBairro     = (TextView) findViewById(R.id.textBairroID);
       // TextView  txtNumero     = (TextView) findViewById(R.id.textNumeroID);
        TextView  txtDataHora   = (TextView) findViewById(R.id.textDataHoraID);
        TextView  txtInformacao = (TextView) findViewById(R.id.textInformacoesAuxiarID);
        TextView  txtBoletim    = (TextView) findViewById(R.id.textBoletimID);
        TextView  txt190        = (TextView) findViewById(R.id.text190ID);
        TextView  textFurtoRoubo = (TextView) findViewById(R.id.textInformacaoID);




        imagemBikePerfil();





        // rebece o dados do Bike Adapter por parametro passada pela tela cadastro
        Intent intent = getIntent();

        if (intent != null) {

            Bundle params = intent.getExtras();

            if (params != null) {



              //  dados da marca
                String marca = params.getString("dadosmarca");
                TextView marcaText = (TextView) findViewById(R.id.dadosMarcaID);
                marcaText.setText(marca);

                // dados do numero serie
                String numero_serie = params.getString("dadosnumero_serie");
                TextView numero_serieText = (TextView) findViewById(R.id.dadosNumeroSerieID);
                numero_serieText.setText(numero_serie);


                // dados da descriçaõ
                String descricao = params.getString("dadosdescricao");
                TextView descricaoText = (TextView) findViewById(R.id.dadosDescricaoID);
                descricaoText.setText(descricao);


                // dados do modelo
                String modelo = params.getString("dadosmodelo");
                TextView modeloText = (TextView) findViewById(R.id.dadosModeloID);
                modeloText.setText(modelo);

                // dados da cor
                String cor = params.getString("dadoscor");
                TextView corText = (TextView) findViewById(R.id.dadosCorID);
                corText.setText(cor);



                // dados do status
                String status = params.getString("status");
                TextView statusText = (TextView) findViewById(R.id.dadosStatusID);
                statusText.setText(status);

                // dados da data
                String alertaData = params.getString("alertaData");
                TextView alertaDataText = (TextView) findViewById(R.id.dadoDiaID);
                alertaDataText.setText(alertaData);


                // dados da Hora
                String alertaHora = params.getString("alertaHora");
                TextView alertaHoraText = (TextView) findViewById(R.id.dadoHoraID);
                alertaHoraText.setText(alertaHora);

                //dados do alertaEstado
                String alertaEstado = params.getString("alertaEstado");
                TextView alertaEstadoText = (TextView) findViewById(R.id.dadosEstadoID);
                alertaEstadoText.setText(alertaEstado);


                // dados do alertaCidade
                String alertaCidade = params.getString("alertaCidade");
                TextView alertaCidadeText = (TextView) findViewById(R.id.dadosCidadeID);
                alertaCidadeText.setText(alertaCidade);

                // dados da Bairro
                String alertaBairro = params.getString("alertaBairro");
                TextView alertaBairroText = (TextView) findViewById(R.id.dadosBairroID);
                alertaBairroText.setText(alertaBairro);


                // dados da Rua
                String alertaRua = params.getString("alertaRua");
                TextView alertaRuaText = (TextView) findViewById(R.id.dadoRuaID);
                alertaRuaText.setText(alertaRua);




                // dados da Boletim
                String alertaBoletim = params.getString("alertaBoletim");
                TextView alertaBoletimText = (TextView) findViewById(R.id.dadosBoletimID);
                alertaBoletimText.setText(alertaBoletim);


                // dados da alertadescricao
                String alertadescricao= params.getString("alertadescricao");
                TextView alertadescricaoText = (TextView) findViewById(R.id.dadosInformacaoID);
                alertadescricaoText.setText(alertadescricao);



                String proprietario = params.getString("proprietario");
                TextView proprietarioText = (TextView) findViewById(R.id.proprietarioID);
                proprietarioText.setText(proprietario);

// dadosda IMAGEM 1

                dadosImagem1 = params.getString("dadosImagem1");
                //TextView dadosImagem1Text = (TextView) findViewById(R.id.dadosBoletimID);
               // dadosImagem1Text.setText(dadosImagem1);



                Glide.with(DadosBike.this).load(dadosImagem1).into(imageViewAbrirImagens);


                Numero_serie = params.getString("Numero_serie");
                TextView Imagem1Text = (TextView) findViewById(R.id.Imagem1DadosBikesID);
                Imagem1Text.setText(Numero_serie);



                if( status.equals("Furtada")||status.equals("Roubada")){



                    dadosAlertaData.setVisibility(View.VISIBLE);
                    dadosAlertaHora.setVisibility(View.VISIBLE);
                    dadosBairro.setVisibility(View.VISIBLE);
                    dadosRua.setVisibility(View.VISIBLE);
                    dadosEstado.setVisibility(View.VISIBLE);
                    dadosCidade.setVisibility(View.VISIBLE);
                    dadosBoletim.setVisibility(View.VISIBLE);
                    dadosInfAuxiliar.setVisibility(View.VISIBLE);
                    dadosStatus.setVisibility(View.VISIBLE);


                    // campos txt
                    txtRua.setVisibility(View.VISIBLE);
                    txtBairro.setVisibility(View.VISIBLE);
                    txtEstado.setVisibility(View.VISIBLE);
                    txtCidade.setVisibility(View.VISIBLE);
                    txtDataHora.setVisibility(View.VISIBLE);
                    txtBoletim.setVisibility(View.VISIBLE);
                    txtInformacao.setVisibility(View.VISIBLE);
                    txtmensagem1.setVisibility(View.VISIBLE);
                    txtmensagem2.setVisibility(View.VISIBLE);
                    txt190.setVisibility(View.VISIBLE);
                    textFurtoRoubo.setVisibility(View.VISIBLE);





                }else{

                    dadosAlertaData.setVisibility(View.GONE);
                    dadosAlertaHora.setVisibility(View.GONE);
                    dadosBairro.setVisibility(View.GONE);
                    dadosRua.setVisibility(View.GONE);
                    dadosEstado.setVisibility(View.GONE);
                    dadosCidade.setVisibility(View.GONE);
                    dadosBoletim.setVisibility(View.GONE);
                    dadosInfAuxiliar.setVisibility(View.GONE);
                   // dadosStatus.setVisibility(View.GONE);




                    //campos txt


                    txtEstado.setVisibility(View.GONE);
                    txtCidade.setVisibility(View.GONE);
                    txtRua.setVisibility(View.GONE);
                    txtBairro.setVisibility(View.GONE);

                    txtDataHora.setVisibility(View.GONE);
                    txtBoletim.setVisibility(View.GONE);
                    txtInformacao.setVisibility(View.GONE);
                  //  txtmensagem1.setVisibility(View.GONE);
                    txtmensagem2.setVisibility(View.GONE);
                    txt190.setVisibility(View.GONE);
                    textFurtoRoubo.setVisibility(View.GONE);



                }


            }






        }




        imageViewAbrirImagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();


                params.putString("Numero_serie",Numero_serie);
                Intent intent = new Intent(DadosBike.this, Galeria_Bike.class);
                intent.putExtras(params);
                startActivity(intent);



            }
        });
    }








    public void imagemBikePerfil(){


        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


     //   String email = user1.getEmail();

        // converte o email pra base 64
     //   String identificadorUsuario= Base64Custom.codificarBase64(email);




        Intent intent = getIntent();
        if (intent != null) {

            Bundle params = intent.getExtras();

            if (params != null) {

                Numero_serie = params.getString("Numero_serie");


            }

        }


        DatabaseReference BikeReference = databaseReferenceBike.child("TodasBikes").child(Numero_serie);


        BikeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (getCallingActivity() == null) {
                    return;
                }



                    if (dataSnapshot.exists()) {

                        Bike dados = dataSnapshot.getValue(Bike.class);



                        if (dados.getFotoBikeUrl1().equals("")) {

                              Glide.with(DadosBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/nao_cadastrada.jpeg?alt=media&token=79bf19e5-7251-4343-bc8a-b172c2529fbe").into(imageViewAbrirImagens);


                        } else {


                              Glide.with(DadosBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/nao_cadastrada.jpeg?alt=media&token=79bf19e5-7251-4343-bc8a-b172c2529fbe").into(imageViewAbrirImagens);


                        }

                    }

                }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }


}
