package bike.douglas.com.bikejanu.Model;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;

public class Usuarios {

    private String idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String nascimento;
    private String imagem;




    public Usuarios() {

    }


    public Usuarios(String userName, String userEmail,String userSenha, String userTelefone,String userImagem) {
        this.nome = userName;
        this.email = userEmail;
        this.senha = userSenha;
        this.telefone = userTelefone;
        this.imagem = userImagem;

    }

// salvar usuarios
    public void Salvar(){


        Usuarios user = new Usuarios(nome, email,senha,telefone,imagem);

        DatabaseReference referenciaFirebase = Configuracao_Firebase.getFirebase();
        referenciaFirebase.child("Usuarios").child(String.valueOf(getIdUsuario())).setValue(user);

    }

    @Exclude
    public Map<String , Object> toMap() {
        HashMap <String , Object> hashMapUsuario = new HashMap<>();

        hashMapUsuario.put("id",getIdUsuario());
        hashMapUsuario.put("nome",getNome());
        hashMapUsuario.put("email",getEmail());
        hashMapUsuario.put("senha",getSenha());
        hashMapUsuario.put("telefone",getSenha());
        hashMapUsuario.put("nascimento",getNascimento());
        hashMapUsuario.put("fotoUsuario",getImagem());


        return hashMapUsuario;

    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
