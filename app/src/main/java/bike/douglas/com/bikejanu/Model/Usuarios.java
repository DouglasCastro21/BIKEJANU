package bike.douglas.com.bikejanu.Model;

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
    private String fotoPerfilURL;
    private String numeroPm;
    private String digitoValidador;





    public Usuarios() {

    }


    public Usuarios(String userName, String userEmail,String userSenha, String userTelefone,String userImagem,String userDigitoValidador) {
        this.nome = userName;
        this.email = userEmail;
        this.senha = userSenha;
        this.telefone = userTelefone;
        this.fotoPerfilURL = userImagem;
        this.digitoValidador = userDigitoValidador;


    }


    public Usuarios(String userName, String userEmail,String userSenha, String userTelefone,String userImagem,String userPm,String userDigitoValidador) {
        this.nome = userName;
        this.email = userEmail;
        this.senha = userSenha;
        this.telefone = userTelefone;
        this.fotoPerfilURL = userImagem;
        this.numeroPm = userPm;
        this.digitoValidador = userDigitoValidador;

    }



// salvar usuarios
    public void Salvar(){


        Usuarios user = new Usuarios(nome, email,senha,telefone, fotoPerfilURL,numeroPm,digitoValidador);

        DatabaseReference referenciaFirebase = Configuracao_Firebase.getFirebase();
        referenciaFirebase.child("Usuarios").child(String.valueOf(getIdUsuario())).setValue(user);

    }



    // salvar usuarios
    public void SalvarUsuariosMilitares(){


        Usuarios user = new Usuarios(nome, email,senha,telefone, fotoPerfilURL,numeroPm,digitoValidador);

        DatabaseReference referenciaFirebase = Configuracao_Firebase.getFirebase();
        referenciaFirebase.child("Militares").child(String.valueOf(getIdUsuario())).setValue(user);

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
        hashMapUsuario.put("fotoPerfilURL",getFotoPerfilURL());
        hashMapUsuario.put("numeroPm",getNumeroPm());
        hashMapUsuario.put("digitoValidador",getDigitoValidador());


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

    public String getFotoPerfilURL() {
        return fotoPerfilURL;
    }

    public void setFotoPerfilURL(String imagem) {
        this.fotoPerfilURL = imagem;
    }

    public String getNumeroPm() {
        return numeroPm;
    }

    public void setNumeroPm(String numeroPm) {
        this.numeroPm = numeroPm;
    }


    public String getDigitoValidador() {
        return digitoValidador;
    }

    public void setDigitoValidador(String digitoValidador) {
        this.digitoValidador = digitoValidador;
    }
}
