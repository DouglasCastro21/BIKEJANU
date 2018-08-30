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

    public  EditText numero_serie;
    private EditText  alertaData;
    private EditText  alertaHora;
    private EditText  alertaNumero;
    private Button btnAlertaFurtoRoubo;

    public  Bike bike;
    private DatabaseReference firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertar_furto_roubo);




       //   numero_serie = (EditText) findViewById(R.id.NumeroID);
          alertaData = (EditText) findViewById(R.id.alertaDataID);
          alertaHora = (EditText) findViewById(R.id.alertaHoraID);
          alertaNumero = (EditText) findViewById(R.id.alertaNumeroID);
          btnAlertaFurtoRoubo = (Button) findViewById(R.id.btnAlertaFurtoRouboID);


         mascaras();


        // rebece o dados do Bike Adapter por parametro
        Intent intent = getIntent();

        if(intent !=null){

            Bundle params = intent.getExtras();

            if (params !=null){

                //dados do modelo
                String Alertamodelo = params.getString("Alertamodelo");
                TextView AlertamodeloText = (TextView) findViewById(R.id.alertaNumeroID);
                AlertamodeloText.setText(Alertamodelo);




            }
        }






        btnAlertaFurtoRoubo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {




                  inicializarElementos();

                  recuperarDadosUsuarioConectadoECadastra();

                  abrirAreaUsuario();

             }
         });



    }

    private void mascaras() {

        SimpleMaskFormatter simpleMaskHora = new SimpleMaskFormatter("NN:NN");
        MaskTextWatcher maskHora = new MaskTextWatcher(alertaHora, simpleMaskHora);
        alertaHora.addTextChangedListener(maskHora);


        SimpleMaskFormatter simpleMaskData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskData = new MaskTextWatcher(alertaData, simpleMaskData);
        alertaData.addTextChangedListener(maskData);

    }

    private void inicializarElementos(){

        bike = new Bike();
//        bike.setNumero_serie(numero_serie.getText().toString());
        bike.setAlertaNumero(alertaNumero.getText().toString());

    }


    private void abrirAreaUsuario(){

        Intent intent = new Intent(AlertarFurtoRoubo.this ,AreaUsuario.class);
        startActivity(intent);
       // finish();
    }


    private void recuperarDadosUsuarioConectadoECadastra(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            String name = user.getDisplayName();
            String email = user.getEmail();

            // converte o email pra base 64
            String identificadorUsuario= Base64Custom.codificarBase64(email);

            //



            // cadastra a bike no nó todas as bikes
         //   firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
         //   firebase.child(bike.getNumero_serie()).setValue(bike);

            // cadastra no nó usuario logado
           // firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            //firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);
//
            Toast.makeText(AlertarFurtoRoubo.this, "A bicicleta foi marcada como furtada!"+bike.getNumero_serie(), Toast.LENGTH_LONG).show();

            // retorna a tela usuario

        //    abrirAreaUsuario();

        };
    }

}
