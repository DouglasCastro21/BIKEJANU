package bike.douglas.com.bikejanu.Activity;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import bike.douglas.com.bikejanu.R;


public class Tab1Entrar extends Fragment {


    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;
    private FirebaseAuth autenticacao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Viewroot = inflater.inflate(R.layout.tab1entrar, container, false);



        TextView edtEmail = (TextView) Viewroot.findViewById(R.id.EmailID);
        TextView edtSenha = (TextView) Viewroot.findViewById(R.id.SenhaID);
        Button btnLogar = (Button) Viewroot.findViewById(R.id.LogarID);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return Viewroot;

    }


}

