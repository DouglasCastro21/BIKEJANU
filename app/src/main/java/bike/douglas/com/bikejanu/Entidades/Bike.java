package bike.douglas.com.bikejanu.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;

public class Bike extends Usuarios{


    private String id;
    private String numero_serie;
    private String marca;
    private String modelo;
    private String cor;
    private String descricao;
    private String alertaNumero;




    public Bike() {

    }


    @Exclude
    public Map<String , Object> toMap() {
        HashMap<String , Object> hashMapBike = new HashMap<>();

        hashMapBike.put("id",getId());
        hashMapBike.put("numero_serie",getNumero_serie());
        hashMapBike.put("marca",getMarca());
        hashMapBike.put("modelo",getModelo());
        hashMapBike.put("cor",getCor());
        hashMapBike.put("descricao",getDescricao());
        hashMapBike.put("alertaNumero",getAlertaNumero());


        return hashMapBike;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) { this.descricao = descricao;
    }

    public String getAlertaNumero() {
        return alertaNumero;
    }

    public void setAlertaNumero(String alertaNumero) {
        this.alertaNumero = alertaNumero;
    }
}
