package com.cdp.agenda.entidades;

public class Prestamos {

    private int id;
    private int id_contacto;
    private int monto;
    private int cantidad_cuota;
    private int porcentaje;
    private String fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getCantidad_cuota() {
        return cantidad_cuota;
    }

    public void setCantidad_cuota(int cantidad_cuota) {
        this.cantidad_cuota = cantidad_cuota;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
