package bike.douglas.com.bikejanu.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "BIKEJANU.Preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;


    private final String CHAVE_IDENTIFICADOR = "identificarUsuarioLogado";
    private final String CHAVE_NOME = "nomeUsuarioLogado";



    private final String CHAVE_TELEFONE  =  "telefone";
    private final String CHAVE_TOKEN     =  "token";




    public Preferencias(Context context) {
        this.context = context;

        preferences = context.getSharedPreferences(NOME_ARQUIVO,MODE);

        editor = preferences.edit();


    }




    public  void salvarUsuarioPreferencias(String identificadorUsuario, String nomeUsuario){
        editor.putString(CHAVE_IDENTIFICADOR,identificadorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }



    public  void  salvarTokenPreferencias(String nome , String telefone , String token){

        editor.putString(CHAVE_NOME, nome);
        editor.putString(CHAVE_TELEFONE, telefone);
        editor.putString(CHAVE_TOKEN, token);
        editor.commit();


    }



    public HashMap<String,String> getTokenUsuario(){

   HashMap<String, String> tokenUsuario = new HashMap<>();
   tokenUsuario.put(CHAVE_NOME,preferences.getString(CHAVE_NOME,null));
   tokenUsuario.put(CHAVE_TELEFONE,preferences.getString(CHAVE_TELEFONE,null));
   tokenUsuario.put(CHAVE_TOKEN,preferences.getString(CHAVE_TOKEN,null));


   return  tokenUsuario;


    }

    public  void salvarBikePreferencias(String identificadorBike, String nomeBike){
        editor.putString(CHAVE_IDENTIFICADOR,identificadorBike);
        editor.putString(CHAVE_NOME, nomeBike);
        editor.commit();
    }


    public String getIdentificador(){

        return  preferences.getString(CHAVE_IDENTIFICADOR, null);

    }

    public String getNome(){

        return  preferences.getString(CHAVE_NOME, null);

    }

}
