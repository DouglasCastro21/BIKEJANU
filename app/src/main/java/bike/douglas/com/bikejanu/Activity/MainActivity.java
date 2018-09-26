package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Fragments.Consultar_Bike;
import bike.douglas.com.bikejanu.Fragments.Consultar_Indice;
import bike.douglas.com.bikejanu.Fragments.Entrar;
import bike.douglas.com.bikejanu.R;


public class MainActivity extends AppCompatActivity {

    private Button btn_Entrar;
    private Button btn_consultar_Indice;
    private Button btn_Consultar_Bike;
    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificarUsuarioLogado();



        btn_Entrar = (Button) findViewById(R.id.btn_loginID);



            btn_Entrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, Entrar.class));
                }
            });

        btn_consultar_Indice = (Button) findViewById(R.id.btn_IndicesID);
        btn_consultar_Indice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this, Consultar_Indice.class));
            }
        });

        btn_Consultar_Bike = (Button) findViewById(R.id.btn_ConsultarBikeID);

        btn_Consultar_Bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Consultar_Bike.class));
            }
        });




    }


    public void abrirTElaPrincipal() {

        Intent intent = new Intent(MainActivity.this, AreaUsuario.class);
        startActivity(intent);



        //  Intent intentabrirTElaPrincipal = new Intent(Tab1Entrar.this,CadastroUsuario.class);
//startActivity(intentabrirTElaPrincipal);

    }


    private void verificarUsuarioLogado() {
        autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {

            abrirTElaPrincipal();

           finish();

        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
