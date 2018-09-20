package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bike.douglas.com.bikejanu.Adapter.BikeAdapter;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.R;

public class DadosBike extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_bike);


        TextView dadosNumero_serie = (TextView) findViewById(R.id.dadosNumeroSerieID);
        TextView dadosMarca = (TextView) findViewById(R.id.dadosMarcaID);
        EditText dadosModelo = (EditText) findViewById(R.id.modeloID);
        EditText dadosDor = (EditText) findViewById(R.id.corID);
        TextView dadosDescricao = (TextView) findViewById(R.id.dadosDescricaoID);



        TextView dadosAlertaData = (TextView) findViewById(R.id.dadoDiaID);
        TextView dadosAlertaHora = (TextView) findViewById(R.id.dadoHoraID);
        TextView dadosBairro = (TextView) findViewById(R.id.dadosBairroID);
        TextView dadosRua = (TextView) findViewById(R.id.dadoRuaID);
        TextView dadosNumero = (TextView) findViewById(R.id.dadosNumeroID);
        TextView dadosBoletim = (TextView) findViewById(R.id.dadosBoletimID);
        TextView dadosInfAuxiliar = (TextView) findViewById(R.id.dadosInformacaoID);
        TextView dadosStatus = (TextView) findViewById(R.id.dadosStatusID);








        //CAMPOS TEXTO

        TextView  txtmensagem1  = (TextView) findViewById(R.id.textTipoCrimeID);
        TextView  txtmensagem2  = (TextView) findViewById(R.id.textCasoVerifiqueID);
        TextView  txtRua        = (TextView) findViewById(R.id.textRuaID);
        TextView  txtBairro     = (TextView) findViewById(R.id.textBairroID);
        TextView  txtNumero     = (TextView) findViewById(R.id.textNumeroID);
        TextView  txtDataHora   = (TextView) findViewById(R.id.textDataHoraID);
        TextView  txtInformacao = (TextView) findViewById(R.id.textInformacoesAuxiarID);
        TextView  txtBoletim    = (TextView) findViewById(R.id.textBoletimID);
        TextView  txt190        = (TextView) findViewById(R.id.text190ID);
        TextView  textFurtoRoubo = (TextView) findViewById(R.id.textInformacaoID);














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


                // dados da Bairro
                String alertaBairro = params.getString("alertaBairro");
                TextView alertaBairroText = (TextView) findViewById(R.id.dadosBairroID);
                alertaBairroText.setText(alertaBairro);


                // dados da Rua
                String alertaRua = params.getString("alertaRua");
                TextView alertaRuaText = (TextView) findViewById(R.id.dadoRuaID);
                alertaRuaText.setText(alertaRua);


                // dados do numero
                String alertaNumero = params.getString("alertaNumero");
                TextView alertaNumeroText = (TextView) findViewById(R.id.dadosNumeroID);
                alertaNumeroText.setText(alertaNumero);

                // dados da Boletim
                String alertaBoletim = params.getString("alertaBoletim");
                TextView alertaBoletimText = (TextView) findViewById(R.id.dadosBoletimID);
                alertaBoletimText.setText(alertaBoletim);


                // dados da alertadescricao
                String alertadescricao= params.getString("alertadescricao");
                TextView alertadescricaoText = (TextView) findViewById(R.id.dadosInformacaoID);
                alertadescricaoText.setText(alertadescricao);






                if( status.equals("Furtada")||status.equals("Roubada")){





                    dadosAlertaData.setVisibility(View.VISIBLE);
                    dadosAlertaHora.setVisibility(View.VISIBLE);
                    dadosBairro.setVisibility(View.VISIBLE);
                    dadosRua.setVisibility(View.VISIBLE);
                    dadosNumero.setVisibility(View.VISIBLE);
                    dadosBoletim.setVisibility(View.VISIBLE);
                    dadosInfAuxiliar.setVisibility(View.VISIBLE);
                    dadosStatus.setVisibility(View.VISIBLE);


                    // campos txt
                    txtRua.setVisibility(View.VISIBLE);
                    txtBairro.setVisibility(View.VISIBLE);
                    txtNumero.setVisibility(View.VISIBLE);
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
                    dadosNumero.setVisibility(View.GONE);
                    dadosBoletim.setVisibility(View.GONE);
                    dadosInfAuxiliar.setVisibility(View.GONE);
                   // dadosStatus.setVisibility(View.GONE);




                    //campos txt

                    txtRua.setVisibility(View.GONE);
                    txtBairro.setVisibility(View.GONE);
                    txtNumero.setVisibility(View.GONE);
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
    }

    }
