package bike.douglas.com.bikejanu.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;

public class Usuarios {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String nascimento;






    public Usuarios() {

    }

// salvar usuarios
    public void Salvar(){

        DatabaseReference referenciaFirebase = Configuracao_Firebase.getFirebase();
        referenciaFirebase.child("usuario").child(String.valueOf(getId())).setValue(this);


    }

    @Exclude
    public Map<String , Object> toMap() {
        HashMap <String , Object> hashMapUsuario = new HashMap<>();

        hashMapUsuario.put("id",getId());
        hashMapUsuario.put("nome",getNome());
        hashMapUsuario.put("email",getEmail());
        hashMapUsuario.put("senha",getSenha());
        hashMapUsuario.put("telefone",getSenha());
        hashMapUsuario.put("nascimento",getNascimento());

        return hashMapUsuario;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }



}
