package bike.douglas.com.bikejanu.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Bike;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.R;

public class CadastroBike extends AppCompatActivity {


    private EditText numero_serie;
    private EditText marca;
    private EditText modelo;
    private EditText cor;
    private Button botaocadastrar;
    private Bike bike;
   private DatabaseReference firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bike);


        numero_serie = (EditText) findViewById(R.id.numeroSerieID);
        marca = (EditText) findViewById(R.id.marcaID);
        modelo = (EditText) findViewById(R.id.modeloID);
        cor = (EditText) findViewById(R.id.corID);

        botaocadastrar = (Button) findViewById(R.id.finalizarID);

        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    bike = new Bike();
                    bike.setNumero_serie(numero_serie.getText().toString());
                    bike.setMarca(marca.getText().toString());
                    bike.setModelo(modelo.getText().toString());
                    bike.setCor(cor.getText().toString());


                    CadastrarBike(bike);




            }


        });

    }

    private boolean CadastrarBike(Bike bike) {

        try {

            firebase = Configuracao_Firebase.getFirebase().child("usuario");
            firebase.child(bike.getMarca()).setValue(bike);
            Toast.makeText(CadastroBike.this, "Bicicleta cadastrado com sucesso!", Toast.LENGTH_LONG).show();



            return true;


        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }


    }




}