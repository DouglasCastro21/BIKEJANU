package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import bike.douglas.com.bikejanu.Fragments.Tab2Cadastrar;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.R;

public class ValidacaoToken extends AppCompatActivity {


   public    EditText codigoToken;
   private   Button validarToken ;
   private   TextView invisibleTelefone;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao_token);




        codigoToken = (EditText)findViewById(R.id.editTokenID);
        validarToken = (Button) findViewById(R.id.buttonValidarTokenID);
        invisibleTelefone = ( TextView)  findViewById(R.id.invisibleTelefone);




        mascaras();



        // rebece o e passada pela tela cadastro
        Intent intent = getIntent();

        if(intent !=null){
            Bundle params = intent.getExtras();

            if (params !=null){

                String telefone = params.getString("telefone");
                TextView telefoneText = (TextView) findViewById(R.id.invisibleTelefone);
                telefoneText.setText(telefone);

            }
        }











        validarToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preferencias preferencias = new Preferencias(ValidacaoToken.this);
                HashMap<String, String> usuario = preferencias.getTokenUsuario();



                String tokenGerado = usuario.get("token");
                String tokenDigitado = codigoToken.getText().toString();




                if(tokenDigitado.equals(tokenGerado)){



                    Toast.makeText(ValidacaoToken.this, "Código Válido", Toast.LENGTH_LONG).show();

                    // recebe o numero para passar para a tela cadastro usuario


                    Bundle params = new Bundle();
                    params.putString("telefone", invisibleTelefone.getText().toString());



                    Intent intent = new Intent(ValidacaoToken.this,CadastroUsuario.class);
                    intent.putExtras(params);
                    startActivity(intent);

                    finish();

                }else{


                    Toast.makeText(ValidacaoToken.this, "Código Inválido", Toast.LENGTH_LONG).show();



                }


            }
        });






    }

    private void mascaras() {

        SimpleMaskFormatter simpleMaskCodigoToken= new SimpleMaskFormatter("NNNN");
        MaskTextWatcher maskCodigoToken = new MaskTextWatcher(codigoToken, simpleMaskCodigoToken);
       codigoToken.addTextChangedListener(maskCodigoToken );


    }

}
