package model;

import java.util.Date;

public class Temperatura extends Plantas {

    private int id_temperatura;
    private double temperatura_inicial;
    private  double temperatura_base;
    private double temperatura_final;
    private double temperatura_total;
    private Date fecha;
    private Plantas planta;


    public Temperatura(){
    }

    public Temperatura( int id_temperatura,double temperatura_inicial, double temperatura_base, double temperatura_final, double temperatura_total,  Date fecha, Plantas planta){
        this.id_temperatura=id_temperatura;
        this.temperatura_inicial=temperatura_inicial;
        this.temperatura_base=temperatura_base;
        this.temperatura_final=temperatura_final;
        this.fecha=fecha;
        this.planta=planta;
    }

    public String getId_temperatura() {
        return id_temperatura;
    }

    public void setId_temperatura(int id_temperatura) {
        this.id_temperatura = id_temperatura;
    }

    public double getTemperatura_inicial() {
        return temperatura_inicial;
    }

    public void setTemperatura_inicial(double temperatura_inicial) {
        this.temperatura_inicial = temperatura_inicial;
    }

    public double getTemperatura_base() {
        return temperatura_base;
    }

    public void setTemperatura_base(double temperatura_base) {
        this.temperatura_base = temperatura_base;
    }

    public double getTemperatura_final() {
        return temperatura_final;
    }

    public void setTemperatura_final(double temperatura_final) {
        this.temperatura_final = temperatura_final;
    }

    public double getTemperatura_total() {
        return temperatura_total;
    }

    public void setTemperatura_total(double temperatura_total) {
        this.temperatura_total = temperatura_total;
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
