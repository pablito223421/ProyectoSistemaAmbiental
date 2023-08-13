package model;

import java.util.Date;

public class Humedad extends Plantas {
    private int id_humedad;
    private double suelo_humedo;
    private double suelo_seco;
    private double porcentaje;
    private Date fecha;
    private Plantas planta;

    public Humedad(){

    }

    public Humedad(int id_humedad, double suelo_humedo, double suelo_seco, double porcentaje, Date fecha, Plantas planta){
        this.id_humedad=id_humedad;
        this.suelo_humedo=suelo_humedo;
        this.suelo_seco=suelo_seco;
        this.porcentaje=porcentaje;
        this.fecha=fecha;
        this.planta=planta;
    }


    public String getId_humedad() {
        return id_humedad;
    }

    public void setId_humedad(int id_humedad) {
        this.id_humedad = id_humedad;
    }

    public double getSuelo_humedo() {
        return suelo_humedo;
    }

    public void setSuelo_humedo(double suelo_humedo) {
        this.suelo_humedo = suelo_humedo;
    }

    public double getSuelo_seco() {
        return suelo_seco;
    }

    public void setSuelo_seco(double suelo_seco) {
        this.suelo_seco = suelo_seco;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public Date getFecha() {
        return fecha;
    }

    @Override
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Plantas getPlanta() {
        return planta;
    }

    public void setPlanta(Plantas planta) {
        this.planta = planta;
    }
}
