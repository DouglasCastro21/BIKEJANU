package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bike.douglas.com.bikejanu.R;

public class SouMilitar extends AppCompatActivity {



    private EditText senhaAdm;
    private Button  buttonConfirmarSenha;
    private TextView senhaInvalida;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_militar);




        senhaAdm             = (EditText) findViewById(R.id.editTextSenhaAdmID);
        buttonConfirmarSenha = (Button)findViewById(R.id.button2ConfirmarSenhaAdmID);
        senhaInvalida        = (TextView) findViewById(R.id.senhaInvalidaAdmID);




        buttonConfirmarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(senhaAdm.getText().toString().equals("12")){


                       // senhaAdm    LBrandao


                    Bundle params = new Bundle();

                    // passa dados  para a tela Cadastro usuario

                    params.putString("validarMilitar","true");

                    Intent intent = new Intent(SouMilitar.this, CadastroUsuario.class);
                    intent.putExtras(params);
                    startActivity(intent);
                    finish();


                }else
                {

                    senhaInvalida.setVisibility(View.VISIBLE);

                }



            }
        });



    }
}
