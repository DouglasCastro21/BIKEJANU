package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.R;



public class TelaNotificacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_notificacao);


        Button btnokNotificacao = (Button) findViewById(R.id.btnNotificacaoOKID);



        btnokNotificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(TelaNotificacao.this ,AreaUsuario.class);
                startActivity(intent);
                finish();



            }
        });
    }
}
