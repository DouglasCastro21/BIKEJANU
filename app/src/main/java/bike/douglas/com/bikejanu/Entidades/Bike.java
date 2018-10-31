package bike.douglas.com.bikejanu.Entidades;

import android.net.NetworkInfo;
import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;

public class Bike extends Usuarios{


    private String id;
    private String numero_serie;
    private String marca;
    private String modelo;
    private String cor;
    private String descricao;

    private String alertaEstado;
    private String alertaCidade;
    private String alertaBairro;
    private String alertaRua;
    private String latitude;
    private String longitude;

    private String alertaDate;
    private String alertaHora;
    private String Boletim;
    private String alertaDescricao;
    private String status;




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



        hashMapBike.put("alertaEstado",getAlertaEstado());
        hashMapBike.put("alertaCidade",getAlertaCidade());
        hashMapBike.put("alertaRua",getAlertaRua());
        hashMapBike.put("alertaBairro",getAlertaBairro());

        hashMapBike.put("alertaDate",getAlertaDate());
        hashMapBike.put("alertaHora",getAlertaHora());
        hashMapBike.put("Boletim",getBoletim());
        hashMapBike.put("alertaDescricao",getAlertaDescricao());
        hashMapBike.put("status",getStatus());

        hashMapBike.put("latitude",getLatitude());
        hashMapBike.put("longitude",getLongitude());





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

    public String getAlertaEstado() {
        return alertaEstado;
    }

    public void setAlertaEstado(String alertaEstado) {
        this.alertaEstado = alertaEstado;
    }

    public String getAlertaCidade() {
        return alertaCidade;
    }

    public void setAlertaCidade(String alertaCidade) {
        this.alertaCidade = alertaCidade;
    }

    public String getAlertaRua() {
        return alertaRua;
    }

    public void setAlertaRua(String alertaRua) {
        this.alertaRua = alertaRua;
    }

    public String getAlertaBairro() {
        return alertaBairro;
    }

    public void setAlertaBairro(String alertaBairro) {
        this.alertaBairro = alertaBairro;
    }

    public String getAlertaDate() {
        return alertaDate;
    }

    public void setAlertaDate(String alertaDate) {
        this.alertaDate = alertaDate;
    }

    public String getAlertaHora() {
        return alertaHora;
    }

    public void setAlertaHora(String alertaHora) {
        this.alertaHora = alertaHora;
    }

    public String getBoletim() {
        return Boletim;
    }

    public void setBoletim(String boletim) {
        Boletim = boletim;
    }

    public String getAlertaDescricao() {
        return alertaDescricao;
    }

    public void setAlertaDescricao(String alertaDescricao) {
        this.alertaDescricao = alertaDescricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
