package model;

import java.util.Date;

public class Agua extends Plantas{
    private int id_agua;
    private double cantidad;
    private Date fecha;
    private Plantas planta;

    public Agua(int id_agua, double cantidad, Date fecha, Plantas planta){
        this.setId_agua(id_agua);
        this.setCantidad(cantidad);
        this.setFecha(fecha);
        this.setPlanta(planta);
    }
    public Agua(){

    }
    public String getId_agua() {
        return id_agua;
    }

    public void setId_agua(int id_agua) {
        this.id_agua = id_agua;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
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
