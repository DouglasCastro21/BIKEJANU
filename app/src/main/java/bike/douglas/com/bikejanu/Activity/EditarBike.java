package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;

public class EditarBike extends AppCompatActivity {


    public Bike bike;
    private DatabaseReference firebase;
    private Button btnEditar;
    public  TextView numero_serie;
    private EditText marca;
    private EditText modelo;
    private EditText cor;
    private EditText descricao;

    // se não repetir os dados da tela cadastro os dados são exluidos
    private TextView alertaNumero;
    private TextView alertaRua;
    private TextView alertaBairro;
    private TextView alertaDate;
    private TextView alertaHora;
    private TextView alertaBoletim;
    private TextView alertaDescricao;
    private ImageView spinnerImagem;






    private String marcasBike[] = new String[] {"CALOY","MONARK","MALAGRA","GALO","EXTREME","HOUSTON","OUTRA"};
    private Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_bike);


    btnEditar = (Button) findViewById(R.id.finalizarID);

    numero_serie     =  (TextView) findViewById(R.id.NumeroID);
   // marca            =  (EditText) findViewById(R.id.spinnerMarcaID);
    modelo           =  (EditText) findViewById(R.id.modeloID);
    cor              =  (EditText) findViewById(R.id.corID);
    descricao        =  (EditText) findViewById(R.id.descricaoID);
        // se não repetir os dados da tela cadastro os dados são exluidos
    alertaNumero     =  (TextView) findViewById(R.id.teste1ID);
    alertaRua        =  (TextView) findViewById(R.id.teste2ID);
    alertaBairro     =  (TextView) findViewById(R.id.teste3ID);
    alertaDate       =  (TextView) findViewById(R.id.teste4ID);
    alertaHora       =  (TextView) findViewById(R.id.teste5ID);
    alertaBoletim    =  (TextView) findViewById(R.id.teste6ID);
    alertaDescricao  =  (TextView) findViewById(R.id.teste7ID);





        //carrega os spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EditarBike.this, android.R.layout.simple_spinner_dropdown_item, marcasBike);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerImagem = (ImageView) findViewById(R.id.spinerButtonID);


        spinner = (Spinner) findViewById(R.id.spinnerMarcaID);
        spinner.setAdapter(arrayAdapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btnEditar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


                inicializarElementos();
                recuperarDadosUsuarioConectadoECadastra();
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
                Spinner marcaText = (Spinner) findViewById(R.id.spinnerMarcaID);


                    marcaText.getItemAtPosition(0);


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



                /// DADOS QUE NÃO vão ficar envisiveis na tela editar


                //dados do alertaNumero
                String alertaNumero = params.getString("alertaNumero");
                TextView alertaNumeroText = (TextView) findViewById(R.id.teste1ID);
                alertaNumeroText.setText(alertaNumero);


                //dados do alertaRua
                String alertaRua = params.getString("alertaRua");
                TextView alertaRuaText = (TextView) findViewById(R.id.teste2ID);
                alertaRuaText.setText(alertaRua);



                // dados do alertaBairro
                String alertaBairro = params.getString("alertaBairro");
                TextView alertaBairroText = (TextView) findViewById(R.id.teste3ID);
                alertaBairroText.setText(alertaBairro);



                // dados do alertaDate
                String alertaDate = params.getString("alertaData");
                TextView alertaDateText = (TextView) findViewById(R.id.teste4ID);
                alertaDateText.setText(alertaDate);


                // dados do alertaHora
                String alertaHora = params.getString("alertaHora");
                TextView alertaHoraText = (TextView) findViewById(R.id.teste5ID);
                alertaHoraText.setText(alertaHora);

                // dados do alertaHora
                String alertaBoletim = params.getString("alertaBoletim");
                TextView alertaBoletimText = (TextView) findViewById(R.id.teste6ID);
                alertaBoletimText.setText(alertaBoletim);


                // dados do alertaBairro
                String alertaDescricao = params.getString("alertadescricao");
                TextView alertaDescricaoText = (TextView) findViewById(R.id.teste7ID);
                alertaDescricaoText.setText(alertaDescricao);

            }
        }

    }


    private void inicializarElementos(){

        bike = new Bike();
        bike.setNumero_serie(numero_serie.getText().toString());
        bike.setMarca(spinner.getSelectedItem().toString());
        bike.setModelo(modelo.getText().toString());
        bike.setCor(cor.getText().toString());
        bike.setDescricao(descricao.getText().toString());
        bike.setAlertaNumero(alertaNumero.getText().toString());
        bike.setAlertaRua(alertaRua.getText().toString());
        bike.setAlertaBairro(alertaBairro.getText().toString());
        bike.setAlertaDate(alertaDate.getText().toString());
        bike.setAlertaHora(alertaHora.getText().toString());
        bike.setBoletim(alertaBoletim.getText().toString());
        bike.setAlertaDescricao(alertaDescricao.getText().toString());
    }

    // volta pra tela usuario
    private void abrirAreaUsuario(){

        Intent intent = new Intent(EditarBike.this ,AreaUsuario.class);
        startActivity(intent);
        finish();
    }


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

            Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();

            // retorna a tela usuario

            abrirAreaUsuario();

        };



    }


    public void ShowElemento(View view){


        String nome = (String)spinner.getSelectedItem();
        long id = spinner.getSelectedItemId();
        int posicao = spinner.getSelectedItemPosition();

    }
}
