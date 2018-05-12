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


import bike.douglas.com.bikejanu.Activity.TelaCadastro;
import bike.douglas.com.bikejanu.R;

public class Tab2Cadastrar extends Fragment {





    private EditText edtEmail;
    private Button btnCadastrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2cadastrar, container, false);

        final TextView edtEmail = (TextView) rootView.findViewById(R.id.verificarEmailID);
        Button btnCadastrar = (Button) rootView.findViewById(R.id.btnCadastrarID);


 btnCadastrar.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         if (!edtEmail.getText().toString().equals("") ){

             abrirTElaPrincipal();


         }else {



             Toast.makeText(Tab2Cadastrar.super.getActivity(), "Preencha com um E-mail valido", Toast.LENGTH_SHORT).show();


         }


     }
 });


        return rootView;
    }
    public void abrirTElaPrincipal(){

        Intent intent = new Intent(getActivity(), TelaCadastro.class);
        startActivity(intent);

        //  Intent intentabrirTElaPrincipal = new Intent(Tab1Entrar.this,TelaCadastro.class);
//startActivity(intentabrirTElaPrincipal);

    }


}
