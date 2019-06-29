package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Fragments.Consultar_Bike;
import bike.douglas.com.bikejanu.Fragments.Galeria_Bike;
import bike.douglas.com.bikejanu.Fragments.Entrar;
import bike.douglas.com.bikejanu.R;


public class MainActivity extends AppCompatActivity {




    private Button btn_Entrar;
    private Button btn_consultar_Indice;
    private Button btn_Consultar_Bike;
    private FirebaseAuth autenticacao;







    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //arrumar um jeito de nao rodar muito essa linha
       // UsuarioDAO.getInstancia().añadirFotoDePerfilALosUsuariosQueNoTienenFoto();



// add depois  testar ...
     autenticacao = FirebaseAuth.getInstance();

 //banco

    FirebaseDatabase firebaseDatabase = Configuracao_Firebase.getFireb();

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


                  startActivity(new Intent(MainActivity.this, Estatisticas.class));



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
        Toast.makeText(MainActivity.this, "Bem Vindo!", Toast.LENGTH_LONG).show();


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
