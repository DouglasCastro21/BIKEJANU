package bike.douglas.com.bikejanu.Fragments;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import bike.douglas.com.bikejanu.Activity.ConfirmarSenha;
import bike.douglas.com.bikejanu.Activity.EditarUsuario;
import bike.douglas.com.bikejanu.Activity.EsqueciSenha;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;


public class Tab1Entrar extends Fragment  {




    private FirebaseAuth autenticacao;
    private Usuarios usuarios;
    private Entrar.SectionsPagerAdapter entrar;
    private ProgressBar progressBar;
    private TextView carregando;
    private TextView usarioInvado;
    private TextView enviarEmailParaEsqueciSenha;


    DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1entrar, container, false);


        verificarUsuarioLogado();

        final TextView edtEmail = (TextView) rootView.findViewById(R.id.EmailID);
        final TextInputEditText edtSenha = (TextInputEditText) rootView.findViewById(R.id.SenhaID);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBarEntrarID);
        carregando  = (TextView)rootView.findViewById(R.id.carregandoID);
        usarioInvado = (TextView)rootView.findViewById(R.id.texUsuarioInvalidoID) ;
        enviarEmailParaEsqueciSenha = (TextView)rootView.findViewById(R.id.enviarParaEsqueciSenhaID) ;


        Button btnLogar = (Button) rootView.findViewById(R.id.LogarID);




        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {


                    usuarios = new Usuarios();
                    usuarios.setEmail(edtEmail.getText().toString());
                    usuarios.setSenha(edtSenha.getText().toString());

                    progressBar.setVisibility(View.VISIBLE);
                    carregando.setVisibility(View.VISIBLE);

                    validarLogin();


                } else {

                    Toast.makeText(Tab1Entrar.super.getActivity(), "Preencha os campos de E-mail e senha", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    carregando.setVisibility(View.GONE);
                }

            }


        });






        enviarEmailParaEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle params = new Bundle();
                params.putString("emailRedefinirSenha",edtEmail.getText().toString());

                Intent intent = new Intent(Tab1Entrar.super.getContext(), EsqueciSenha.class);
                intent.putExtras(params);
                startActivity(intent);


            }
        });







        return rootView;

    }











    private void validarLogin(){

    autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();
    autenticacao.signInWithEmailAndPassword(usuarios.getEmail(),usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {


            if (task.isSuccessful()){
                Tab1Entrar.super.getActivity().finish();

               abrirTElaPrincipal();



                Toast.makeText(Tab1Entrar.super.getActivity(),"Login Efetuado com Sucesso", Toast.LENGTH_SHORT).show();
                Toast.makeText(Tab1Entrar.super.getActivity(),"Bem Vindo!", Toast.LENGTH_SHORT).show();


            }else {

                //mensagem atençaõ ...
                usarioInvado.setVisibility(View.VISIBLE);
                enviarEmailParaEsqueciSenha.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                carregando.setVisibility(View.GONE);
            }

        }
    });


    }






    public void abrirTElaPrincipal(){



        Intent intent = new Intent(getActivity(), AreaUsuario.class);
        startActivity(intent);


        //  Intent intentabrirTElaPrincipal = new Intent(Tab1Entrar.this,CadastroUsuario.class);
//startActivity(intentabrirTElaPrincipal);

    }




    private  void verificarUsuarioLogado() {
        autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {



          abrirTElaPrincipal();



        }
    }



}

