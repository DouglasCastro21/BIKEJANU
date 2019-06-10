package bike.douglas.com.bikejanu.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import de.hdodenhof.circleimageview.CircleImageView;

public class ConfirmarSenha extends AppCompatActivity {




    private Usuarios usuarios;
    DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference();


    private int validar=0;
    private FirebaseAuth usuarioFirebase;

    private Button   buttonconfirmarSenha;

    private EditText editTextConfirmarSenha;

    private CircleImageView imagemUsuario;

    private TextView txtAtencao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_senha);



        buttonconfirmarSenha =    (Button) findViewById(R.id.buttonConfirmarSenhaID);

        editTextConfirmarSenha = (EditText) findViewById(R.id.editTextConfirmarSenhaID);

        usuarioFirebase = Configuracao_Firebase.getFirebaseAutenticacao();

        imagemUsuario = (CircleImageView) findViewById(R.id.imageView5);

        txtAtencao = (TextView)findViewById(R.id.txtAtencaoSenhaID) ;


        imagemUsuario();


        Intent intent = getIntent();

        if (intent != null) {

            Bundle params = intent.getExtras();

            if (params != null) {

                //dados do alertaRua
                String confirmarExclusao = params.getString("confirmarExclusao");
                TextView confirmarExclusaoText = (TextView) findViewById(R.id.confirmadoSenhaaID);
                confirmarExclusaoText.setText(confirmarExclusao);


                if (confirmarExclusaoText.getText().toString().equals("confirmado")) {


                 validar =1;


                }

            }


        }





        if(validar ==1){

            buttonconfirmarSenha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    chamaTelaExluirConta();


                }
            });



        }else{

            buttonconfirmarSenha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    confirmarSenhaUsuarios();


                }
            });





        }





    }



    public void deslogarUsuario(){


        usuarioFirebase.signOut();
        Intent intent = new Intent(ConfirmarSenha.this ,MainActivity.class);
        startActivity(intent);
        finish();

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



                   // passa dados  para a tela editar usuario

                   if (editTextConfirmarSenha.getText().toString().equals(dados.getSenha())){



                       chamarTelaEditarUsuario();
                       finish();



                   }else
                   {


                       txtAtencao.setVisibility(View.VISIBLE);



                   }



               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });




    }


    public void chamarTelaEditarUsuario(){





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
                    params.putString("numeroPm",                           dados.getNumeroPm());
                    params.putString("validarUsuario",                     dados.getDigitoValidador());


                    Intent intent = new Intent(ConfirmarSenha.this, EditarUsuario.class);
                    intent.putExtras(params);
                    startActivity(intent);
                   // finish();





                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




    }


    public void excluirUsuario() {


        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(ConfirmarSenha.this);

        // configurando dialogo

        alertaDialog.setTitle("Confirmar Exclusão");
        alertaDialog.setIcon(R.drawable.ic_action_exit);


        alertaDialog.setMessage("Deseja realmente excluir sua conta ? ");
        alertaDialog.setCancelable(false);


        // conf botões

        alertaDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    deslogarUsuario();

                                }
                            }
                        });


                Toast.makeText(ConfirmarSenha.this, "Sua Conta Foi Excluida", Toast.LENGTH_LONG).show();


            }
        });

        alertaDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(ConfirmarSenha.this, "Operação Cancelada", Toast.LENGTH_LONG).show();


            }
        });

        alertaDialog.create();
        alertaDialog.show();



    }






    public void chamaTelaExluirConta() {




                    final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


                    String email = user1.getEmail();

                    // converte o email pra base 64
                    String identificadorUsuario= Base64Custom.codificarBase64(email);


                    DatabaseReference UsuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);

                    UsuarioReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Usuarios dados = dataSnapshot.getValue(Usuarios.class);



                            // passa dados  para a tela editar usuario

                            if (editTextConfirmarSenha.getText().toString().equals(dados.getSenha())){



                                excluirUsuario();



                            }else
                            {


                                txtAtencao.setVisibility(View.VISIBLE);
                            }



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });











            }


public void imagemUsuario(){


    FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


    String email = user1.getEmail();

    // converte o email pra base 64
    String identificadorUsuario= Base64Custom.codificarBase64(email);



    DatabaseReference UsuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);




    UsuarioReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if(dataSnapshot.exists()) {

                Usuarios dados = dataSnapshot.getValue(Usuarios.class);



                Glide.with(ConfirmarSenha.this).load(dados.getFotoPerfilURL()).into(imagemUsuario);






            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });





}




}
