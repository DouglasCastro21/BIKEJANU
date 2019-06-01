package bike.douglas.com.bikejanu.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import bike.douglas.com.bikejanu.DAO.UsuarioDAO;
import bike.douglas.com.bikejanu.Model.Usuarios;

public class LUsuario {

    private String key;
    private Usuarios usuario;

    public LUsuario(String key, Usuarios usuario) {
        this.key = key;
        this.usuario = usuario;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String obtenerFechaDeCreacion(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date(UsuarioDAO.getInstancia().fechaDeCreacionLong());
        return simpleDateFormat.format(date);
    }

    public String obtenerFechaDeUltimaVezQueSeLogeo(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date(UsuarioDAO.getInstancia().fechaDeUltimaVezQueSeLogeoLong());
        return simpleDateFormat.format(date);
    }


}
