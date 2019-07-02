package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import bike.douglas.com.bikejanu.R;

public class ChecarUsuario extends AppCompatActivity {


    private RadioGroup radioGroupMilitar;
    private RadioButton radioButtonMilitar;
    private RadioButton radioButtonNaoMilitar;
    private Button  buttonConfirmarMilitar;
    String telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checar_usuario);


        radioGroupMilitar = (RadioGroup) findViewById(R.id.radioGroupConfirmarMilitarID);
        radioButtonMilitar = (RadioButton) findViewById(R.id.radioButtonMilitarID);
        radioButtonNaoMilitar = (RadioButton) findViewById(R.id.radioButtoNaoMilitarID);

        buttonConfirmarMilitar = (Button) findViewById(R.id.buttonConfirmarMilitarID);


        Intent intent = getIntent();

        if (intent != null) {
            Bundle params = intent.getExtras();

            if (params != null) {



                 telefone = params.getString("mobile");



            }
        }


        buttonConfirmarMilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButtonMilitar.isChecked()) {

                    radioButtonMilitar.toggle();
                    Bundle params = new Bundle();
                    Intent intent = new Intent(ChecarUsuario.this, SouMilitar.class);

                    params.putString("mobile",  telefone);
                    intent.putExtras(params);
                    startActivity(intent);




                }


                if (radioButtonNaoMilitar.isChecked()) {

                    radioButtonNaoMilitar.toggle();

                    Bundle params = new Bundle();
                    Intent intent = new Intent(ChecarUsuario.this, CadastroUsuario.class);

                    params.putString("mobile",  telefone);
                    intent.putExtras(params);
                    startActivity(intent);


                }


            }
        });
    }



}












