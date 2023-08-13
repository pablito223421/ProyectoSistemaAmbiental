package model;

import java.util.Date;

public class Plantas
{
    private int id_planta;
    private String nombre_planta;
    private String caracteristicas;
    private String imagen_planta;
    private Date fecha;

    public Plantas(){

    }

    public Plantas(int id_planta, String nombre_planta, String caracteristicas, String imagen_planta, Date fecha){
        this.id_planta=id_planta;
        this.nombre_planta=nombre_planta;
        this.caracteristicas=caracteristicas;
        this.imagen_planta=imagen_planta;
        this.fecha=fecha;
    }

    public String getId_planta() {
        return id_planta;
    }

    public void setId_planta(int id_planta) {
        this.id_planta = id_planta;
    }

    public String getNombre_planta() {
        return nombre_planta;
    }

    public void setNombre_planta(String nombre_planta) {
        this.nombre_planta = nombre_planta;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getImagen_planta() {
        return imagen_planta;
    }

    public void setImagen_planta(String imagen_planta) {
        this.imagen_planta = imagen_planta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
