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


                // dados do alertaDate
                String alertaDate = params.getString("alertaDate");
                TextView alertaDateText = (TextView) findViewById(R.id.alertaDataID);
                alertaDateText.setText(alertaDate);

                // dados do alertaHora
                String alertaHora = params.getString("alertaHora");
                TextView alertaHoraText = (TextView) findViewById(R.id.alertaHoraID);
                alertaHoraText.setText(alertaHora);

                // dados do Boletim
                String Boletim = params.getString("Boletim");
                TextView BoletimText = (TextView) findViewById(R.id.BoletimID);
                BoletimText.setText(Boletim);

                // dados alerta descrição
                String alertaDescricao = params.getString("alertaDescricao");
                TextView alertadescricaoText = (TextView) findViewById(R.id.alertaDescricaoID);
                alertadescricaoText.setText(alertaDescricao);


            }
        }

                mascaras();
    }

    public void mascaras() {

        SimpleMaskFormatter simpleMaskData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskData = new MaskTextWatcher(alertaDate, simpleMaskData);
        alertaDate.addTextChangedListener(maskData);

        SimpleMaskFormatter simpleMaskHora = new SimpleMaskFormatter("NN:NN");
        MaskTextWatcher maskHora = new MaskTextWatcher(alertaHora, simpleMaskHora);
        alertaHora.addTextChangedListener(maskHora);
    }
}
