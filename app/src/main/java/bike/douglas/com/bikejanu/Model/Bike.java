package bike.douglas.com.bikejanu.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

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
    private String fotoBikeUrl1;
    private String fotoBikeUrl2;
    private String fotoBikeUrl3;
    private String fotoBikeUrl4;
    private String fotoBikeUrl5;
    private String proprietario ;




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
        hashMapBike.put("fotoBikeUrl1",getFotoBikeUrl1());
        hashMapBike.put("fotoBikeUrl2",getFotoBikeUrl2());
        hashMapBike.put("fotoBikeUrl3",getFotoBikeUrl3());
        hashMapBike.put("fotoBikeUrl4",getFotoBikeUrl4());
        hashMapBike.put("fotoBikeUrl5",getFotoBikeUrl5());







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
        hashMapBike.put("proprietario",getProprietario());



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


    public String getFotoBikeUrl1() {
        return fotoBikeUrl1;
    }

    public void setFotoBikeUrl1(String fotoBikeUrl1) {
        this.fotoBikeUrl1 = fotoBikeUrl1;
    }

    public String getFotoBikeUrl2() {
        return fotoBikeUrl2;
    }

    public void setFotoBikeUrl2(String fotoBikeUrl2) {
        this.fotoBikeUrl2 = fotoBikeUrl2;
    }

    public String getFotoBikeUrl3() {
        return fotoBikeUrl3;
    }

    public void setFotoBikeUrl3(String fotoBikeUrl3) {
        this.fotoBikeUrl3 = fotoBikeUrl3;
    }

    public String getFotoBikeUrl4() {
        return fotoBikeUrl4;
    }

    public void setFotoBikeUrl4(String fotoBikeUrl4) {
        this.fotoBikeUrl4 = fotoBikeUrl4;
    }

    public String getFotoBikeUrl5() {
        return fotoBikeUrl5;
    }

    public void setFotoBikeUrl5(String fotoBikeUrl5) {
        this.fotoBikeUrl5 = fotoBikeUrl5;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }
}
