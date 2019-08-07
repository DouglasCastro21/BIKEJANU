package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


import bike.douglas.com.bikejanu.R;

public class EsqueciSenha extends AppCompatActivity {


    private TextView mostrareEmail;
    private Button btnenviarEmail;
    private  String email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);


        mostrareEmail = (TextView)findViewById(R.id.textViewMostrarEmailID);
        btnenviarEmail =(Button)findViewById(R.id.buttonEnviarEmail);

        firebaseAuth = FirebaseAuth.getInstance();


        Intent intent = getIntent();

        if(intent !=null) {

            Bundle params = intent.getExtras();

            if (params != null) {

                //dados do modelo
                email = params.getString("emailRedefinirSenha");
                TextView emailText = (TextView) findViewById(R.id.textViewMostrarEmailID);
                emailText.setText(email);

            }

        }

            btnenviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.sendPasswordResetEmail( email ).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if( task.isSuccessful() ){



                                    Toast.makeText(EsqueciSenha.this, "Recuperação de acesso iniciada. Email enviado.", Toast.LENGTH_SHORT).show();


                                              Intent intent = new Intent(EsqueciSenha.this, SouMilitar.class);
                                              startActivity(intent);


                                }
                                else{

                                    Toast.makeText(EsqueciSenha.this, "Falhou! Tente novamente", Toast.LENGTH_SHORT).show();

                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {



                            }
                        });





            }
        });

    }
}
