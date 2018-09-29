package bike.douglas.com.bikejanu.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "BIKEJANU.Preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;


    private final String CHAVE_IDENTIFICADOR = "identificarUsuarioLogado";
    private final String CHAVE_NOME = "nomeUsuarioLogado";




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
