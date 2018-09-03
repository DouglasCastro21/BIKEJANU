package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import bike.douglas.com.bikejanu.R;

public class DadosBike extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_bike);


      //  TextView dadosNumero_serie = (TextView) findViewById(R.id.dadosNumeroSerieID);
    //    TextView dadosMarca = (TextView) findViewById(R.id.dadosMarcaID);
        //   EditText dadosModelo = (EditText) findViewById(R.id.modeloID);
        //   EditText dadosDor = (EditText) findViewById(R.id.corID);
      //   TextView dadosDescricao = (TextView) findViewById(R.id.dadosDescricaoID);


        // rebece o dados do Bike Adapter por parametro passada pela tela cadastro
        Intent intent = getIntent();

        if (intent != null) {

            Bundle params = intent.getExtras();

            if (params != null) {



                //dados da marca
                String marca = params.getString("dadosmarca");
              //  TextView marcaText = (TextView) findViewById(R.id.dadosMarcaID);
             //   marcaText.setText(marca);

                // dados do numero serie
                String numero_serie = params.getString("dadosnumero_serie");
             //   TextView numero_serieText = (TextView) findViewById(R.id.dadosNumeroSerieID);
            //    numero_serieText.setText(numero_serie);


                // dados da descriçaõ
                String descricao = params.getString("dadosdescricao");
             //   TextView descricaoText = (TextView) findViewById(R.id.dadosDescricaoID);
             ///   descricaoText.setText(descricao);


                // dados do modelo
                String modelo = params.getString("dadosmodelo");
             //   TextView modeloText = (TextView) findViewById(R.id.dadosModeloID);
             //   modeloText.setText(modelo);

                // dados da cor
                String cor = params.getString("dadoscor");
             //   TextView corText = (TextView) findViewById(R.id.dadosCorID);
             //   corText.setText(cor);


            }
        }
    }

    }
