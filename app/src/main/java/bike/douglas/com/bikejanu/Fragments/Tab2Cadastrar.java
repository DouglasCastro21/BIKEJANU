package bike.douglas.com.bikejanu.Fragments;




import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

import bike.douglas.com.bikejanu.Activity.CadastroUsuario;
import bike.douglas.com.bikejanu.Activity.ValidacaoToken;
import bike.douglas.com.bikejanu.Helper.Permissao;
import bike.douglas.com.bikejanu.Helper.Preferencias;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

public class Tab2Cadastrar extends Fragment {




    private Button btnCadastrar;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    public EditText telefone;



    private String[] permissoesNecessarias = new String[]{

            android.Manifest.permission.SEND_SMS

    };


    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2cadastrar, container, false);



        Permissao.validaPermissoes(1,Tab2Cadastrar.super.getActivity(),permissoesNecessarias);


        Button btnCadastrar = (Button) rootView.findViewById(R.id.btnCadastrarID);
        telefone = (EditText) rootView.findViewById(R.id.verificarID);

        mascaras();

        btnCadastrar.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v) {


            if (!telefone.getText().toString().equals("")) {


                 gerarToken();

                // recebe o numero para passar para a tela cadastro usuario


                Bundle params = new Bundle();
                params.putString("telefone", telefone.getText().toString());




                Tab2Cadastrar.super.getActivity().finish();


                Intent intent = new Intent(Tab2Cadastrar.super.getContext(), ValidacaoToken.class);
                intent.putExtras(params);
                startActivity(intent);



                //// até aq




            } else {


                Toast.makeText(Tab2Cadastrar.super.getActivity(),"Preencha com um Número válido ", Toast.LENGTH_SHORT).show();


                }
            }



    });



        return rootView;
}


    public void abrirCadastroUsuario(){

        Intent intent = new Intent(getActivity(),CadastroUsuario.class);
        startActivity(intent);


    }



    private boolean envioSms(String telefone, String mensagem) {

        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone,null,mensagem,null,null);

            return  true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;


        }
    }


    public  void gerarToken(){

        String nomeUsuario = "";

        String telefoneCompleto = telefone.getText().toString();


        String telefoneSemFormatacao = telefoneCompleto.replace("(","");

        telefoneSemFormatacao = telefoneSemFormatacao.replace(")","");






        //Gerar Token

        Random randomico = new Random();
        int numeroRandomico = randomico.nextInt(9999-1000)+1000;

        String token = String.valueOf(numeroRandomico);


        String mensagemEnvio = "Bike-Janu  Código de Confirmação: " + token;


        // salvar dados

        Preferencias preferencias = new  Preferencias(Tab2Cadastrar.super.getContext());
        preferencias.salvarTokenPreferencias(nomeUsuario,telefoneSemFormatacao,token);

        // envio do sms

        boolean enviadoSms =  envioSms("+55" + telefoneSemFormatacao,mensagemEnvio);


        if(enviadoSms){

            Intent intent = new Intent(Tab2Cadastrar.super.getContext(),ValidacaoToken.class);
            startActivity(intent);


        }else {


            Toast.makeText(Tab2Cadastrar.super.getContext(), "Problema ao enviar SMS , verifique o pacote de dados da operadora", Toast.LENGTH_LONG).show();


        }

        // HashMap<String,String> usurio = preferencias.getTokenUsuario();

        //  Log.i("TOKEN",  " T "+ usurio.get(token));

    }


    public void onRequestPermissionsResult(int resquestCode, String[] permissions ,  int[] grantResult){

        super.onRequestPermissionsResult(resquestCode,permissions,grantResult);


        for (int resultado  : grantResult){

            if (resultado == PackageManager.PERMISSION_DENIED){

                alertaValidacaoPermissao();


            }



        }


    }


    public void alertaValidacaoPermissao(){

        caixaDialogoPermissaoNegada();


    }



    private void caixaDialogoPermissaoNegada(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(Tab2Cadastrar.this.getContext());

        // configurando dialogo

        alertaDialog.setTitle("Permissão Negada");


        alertaDialog.setMessage("Para ultilizar esse app , é necessário  aceitar as permissões");
        // alertaDialog.setCancelable(false);


        //conf botões
        alertaDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //  finish();


            }

        });


        alertaDialog.create();
        alertaDialog.show();



    }



    private void mascaras() {

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN)NNNNNNNNN");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        telefone.addTextChangedListener(maskTelefone);


    }


}