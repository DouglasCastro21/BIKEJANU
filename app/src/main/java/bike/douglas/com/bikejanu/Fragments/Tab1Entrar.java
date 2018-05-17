package bike.douglas.com.bikejanu.Fragments;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import bike.douglas.com.bikejanu.Activity.TelaCadastro;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Usuarios;
import bike.douglas.com.bikejanu.R;


public class Tab1Entrar extends Fragment  {


    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1entrar, container, false);


        verificarUsuarioLogado();

        final TextView edtEmail = (TextView) rootView.findViewById(R.id.EmailID);
        final TextView edtSenha = (TextView) rootView.findViewById(R.id.SenhaID);
        Button btnLogar = (Button) rootView.findViewById(R.id.LogarID);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {


                    usuarios = new Usuarios();
                    usuarios.setEmail(edtEmail.getText().toString());
                    usuarios.setSenha(edtSenha.getText().toString());

                    validarLogin();

                } else {

                    Toast.makeText(Tab1Entrar.super.getActivity(), "Preencha os campos de E-mail e senha", Toast.LENGTH_SHORT).show();

                }


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

                abrirTElaPrincipal();


                Toast.makeText(Tab1Entrar.super.getActivity(),"Login Efetuado com Sucesso", Toast.LENGTH_SHORT).show();

            }else {

                Toast.makeText(Tab1Entrar.super.getActivity(), "Usuário ou senha invalidos", Toast.LENGTH_SHORT).show();


            }

        }
    });



    }


    public void abrirTElaPrincipal(){

        Intent intent = new Intent(getActivity(), AreaUsuario.class);
        startActivity(intent);

      //  Intent intentabrirTElaPrincipal = new Intent(Tab1Entrar.this,TelaCadastro.class);
//startActivity(intentabrirTElaPrincipal);

    }



    private  void verificarUsuarioLogado() {
        autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {

            abrirTElaPrincipal();
        }
    }



}

