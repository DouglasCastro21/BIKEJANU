package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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




public class AlertarFurtoRoubo extends AppCompatActivity {


    private EditText alertaNumero;
    private EditText alertaRua;
    private EditText alertaBairro;
    private EditText alertaDate;
    private EditText alertaHora;
    private EditText Boletim;
    private EditText alertaDescricao;
    private Button   finalizar;


    public  Bike bike;
    private DatabaseReference firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertar_furto_roubo);



        alertaNumero    = (EditText)findViewById(R.id.alertaNumeroID);
        alertaRua       = (EditText)findViewById(R.id.alertaRuaID);
        alertaBairro    = (EditText)findViewById(R.id.alertaBairroID);
        alertaDate      = (EditText)findViewById(R.id.alertaDataID);
        alertaHora      = (EditText)findViewById(R.id.alertaHoraID);
        Boletim         = (EditText)findViewById(R.id.BoletimID);
        alertaDescricao = (EditText)findViewById(R.id.alertaDescricaoID);
        finalizar       = (Button)  findViewById(R.id.finalizarID);



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



            }
        }

                mascaras();

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                inicializarElementos();
                recuperarDadosUsuarioConectadoECadastra();

            }
        });

    }




    public void mascaras() {

        SimpleMaskFormatter simpleMaskData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskData = new MaskTextWatcher(alertaDate, simpleMaskData);
        alertaDate.addTextChangedListener(maskData);

        SimpleMaskFormatter simpleMaskHora = new SimpleMaskFormatter("NN:NN");
        MaskTextWatcher maskHora = new MaskTextWatcher(alertaHora, simpleMaskHora);
        alertaHora.addTextChangedListener(maskHora);
    }


    private void inicializarElementos(){




        bike.setAlertaNumero(alertaNumero.getText().toString());
        bike.setAlertaRua(alertaRua.getText().toString());
        bike.setAlertaBairro(alertaBairro.getText().toString());
        bike.setAlertaDate(alertaDate.getText().toString());
        bike.setAlertaHora(alertaHora.getText().toString());
        bike.setBoletim(Boletim.getText().toString());
        bike.setAlertaDescricao(alertaDescricao.getText().toString());




    }

    private void abrirAreaUsuario(){

        Intent intent = new Intent(AlertarFurtoRoubo.this ,AreaUsuario.class);
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

            Toast.makeText(AlertarFurtoRoubo.this, "Operação realizada com sucesso!", Toast.LENGTH_LONG).show();

            // retorna a tela usuario

            abrirAreaUsuario();

        };
    }
}
