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

import bike.douglas.com.bikejanu.Activity.CadastroUsuario;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Entidades.Usuarios;
import bike.douglas.com.bikejanu.R;

public class Tab2Cadastrar extends Fragment {





    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnCadastrar;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2cadastrar, container, false);

        final TextView edtEmail = (TextView) rootView.findViewById(R.id.verificarEmailID);
        Button btnCadastrar = (Button) rootView.findViewById(R.id.btnCadastrarID);





 btnCadastrar.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v) {


            if (!edtEmail.getText().toString().equals("")) {


                      abrirCadastroUsuario();

            } else {


                Toast.makeText(Tab2Cadastrar.super.getActivity(),"Preencha com     E-mail", Toast.LENGTH_SHORT).show();

                    String erroExcecao = "";

                    try {

                    } catch (Exception e) {

                        erroExcecao = "Erro ao efetuar o cadastro";
                        e.printStackTrace();


                    }
                    Toast.makeText(Tab2Cadastrar.super.getActivity(), "Erro" + erroExcecao, Toast.LENGTH_LONG).show();


                }
            }



    });



        return rootView;
}

    public void abrirCadastroUsuario(){

        Intent intent = new Intent(getActivity(),CadastroUsuario.class);
        startActivity(intent);

        //  Intent intentabrirTElaPrincipal = new Intent(Tab1Entrar.this,CadastroUsuario.class);
//startActivity(intentabrirTElaPrincipal);

    }




}