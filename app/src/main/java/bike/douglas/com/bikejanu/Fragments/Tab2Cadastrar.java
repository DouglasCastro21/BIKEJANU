package bike.douglas.com.bikejanu.Fragments;




import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Bundle;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;



import bike.douglas.com.bikejanu.R;
public class Tab2Cadastrar extends Fragment {



    FirebaseAuth mAuth;

    static String  codeSent;



    public EditText telefone;





    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2cadastrar, container, false);



        Button btnCadastrar = (Button) rootView.findViewById(R.id.btnCadastrarID);
        telefone = (EditText) rootView.findViewById(R.id.verificarTelefoneID);

        mascaras();

        btnCadastrar.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v) {


            if (!telefone.getText().toString().equals("")) {



                String mobile = telefone.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    telefone.setError("Insira um número válido ");
                    telefone.requestFocus();
                    return;
                }

                Intent intent = new Intent(Tab2Cadastrar.super.getContext(), VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);


            } else {


                Toast.makeText(Tab2Cadastrar.super.getActivity(),"Preencha com um Número válido ", Toast.LENGTH_SHORT).show();


                }
            }



    });



        return rootView;
}



    private void mascaras() {

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN)N-NNNNNNNN");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        telefone.addTextChangedListener(maskTelefone);


    }


}