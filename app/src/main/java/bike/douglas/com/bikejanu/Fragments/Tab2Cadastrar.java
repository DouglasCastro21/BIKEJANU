package bike.douglas.com.bikejanu.Fragments;




import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import bike.douglas.com.bikejanu.Activity.CadastroUsuario;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

public class Tab2Cadastrar extends Fragment {






    private Button btnCadastrar;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;


    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2cadastrar, container, false);

        final TextView edtEmail = (TextView) rootView.findViewById(R.id.verificarID);
        Button btnCadastrar = (Button) rootView.findViewById(R.id.btnCadastrarID);
        final EditText emailEnviar = (EditText) rootView.findViewById(R.id.verificarID);


        btnCadastrar.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v) {


            if (!edtEmail.getText().toString().equals("")) {


                // recebe o email para passar para a tela cadastro usuario

                Bundle params = new Bundle();
                params.putString("email", emailEnviar.getText().toString());



                Tab2Cadastrar.super.getActivity().finish();
                Intent intent = new Intent(Tab2Cadastrar.super.getContext(), CadastroUsuario.class);
                intent.putExtras(params);

                startActivity(intent);

                //// até aq




            } else {


                Toast.makeText(Tab2Cadastrar.super.getActivity(),"Preencha com E-mail valido", Toast.LENGTH_SHORT).show();


                }
            }



    });



        return rootView;
}


    public void abrirCadastroUsuario(){

        Intent intent = new Intent(getActivity(),CadastroUsuario.class);
        startActivity(intent);


    }


}