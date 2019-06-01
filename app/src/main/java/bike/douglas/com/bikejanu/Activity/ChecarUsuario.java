package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import bike.douglas.com.bikejanu.R;

public class ChecarUsuario extends AppCompatActivity {


    private RadioGroup radioGroupMilitar;
    private RadioButton radioButtonMilitar;
    private RadioButton radioButtonNaoMilitar;
    private Button  buttonConfirmarMilitar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checar_usuario);



        radioGroupMilitar = (RadioGroup)findViewById(R.id.radioGroupConfirmarMilitarID);
        radioButtonMilitar = (RadioButton)findViewById(R.id.radioButtonMilitarID);
        radioButtonNaoMilitar = (RadioButton)findViewById(R.id.radioButtoNaoMilitarID);

        buttonConfirmarMilitar = (Button)findViewById(R.id.buttonConfirmarMilitarID);








        buttonConfirmarMilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButtonMilitar.isChecked()){

                    radioButtonMilitar.toggle();

                    startActivity(new Intent(ChecarUsuario.this,SouMilitar.class));




                }



                if (radioButtonNaoMilitar.isChecked()){

                    radioButtonNaoMilitar.toggle();


                    startActivity(new Intent(ChecarUsuario.this,CadastroUsuario.class));



                }



            }
        });

    }





}
