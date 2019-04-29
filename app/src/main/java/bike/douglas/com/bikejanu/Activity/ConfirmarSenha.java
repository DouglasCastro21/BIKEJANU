package bike.douglas.com.bikejanu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

public class ConfirmarSenha extends AppCompatActivity {



    String senhaDigitada;

    private Usuarios usuarios;
    DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference();


    private Button   buttonconfirmarSenha;

    private EditText editTextConfirmarSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_senha);



        buttonconfirmarSenha =    (Button) findViewById(R.id.buttonConfirmarSenhaID);

        editTextConfirmarSenha = (EditText) findViewById(R.id.editTextConfirmarSenhaID);






buttonconfirmarSenha.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        confirmarSenhaUsuarios();

    }
});





    }



    public void confirmarSenhaUsuarios(){


        // recupera usuario

        final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


        String email = user1.getEmail();

        // converte o email pra base 64
        String identificadorUsuario= Base64Custom.codificarBase64(email);


        DatabaseReference UsuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);

        UsuarioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuarios dados = dataSnapshot.getValue(Usuarios.class);




                Bundle params = new Bundle();

                // passa dados  para a tela editar usuario

        if (editTextConfirmarSenha.getText().toString().equals(dados.getSenha())){


            recuperaUsuarios();


        }else
        {
            Toast.makeText(ConfirmarSenha.this,"A Senha é invalida ",Toast.LENGTH_LONG ).show();

        }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }



    public void recuperaUsuarios(){


        // recupera usuario

        final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


        String email = user1.getEmail();

        // converte o email pra base 64
        String identificadorUsuario= Base64Custom.codificarBase64(email);



        DatabaseReference UsuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);


        UsuarioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuarios dados = dataSnapshot.getValue(Usuarios.class);




                Bundle params = new Bundle();

                // passa dados  para a tela editar usuario


                params.putString("nomeUsuario",                        dados.getNome());
                params.putString("telefoneUsuario",                    dados.getTelefone());
                params.putString("emailUsuario",                       dados.getEmail());
                params.putString("confirmaremailUsuario",              dados.getEmail());
                params.putString("senhaUsuario",                       dados.getSenha());
                params.putString("confirmarsenhaUsuario",              dados.getSenha());


                Intent intent = new Intent(ConfirmarSenha.this, EditarUsuario.class);
                intent.putExtras(params);
                startActivity(intent);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
