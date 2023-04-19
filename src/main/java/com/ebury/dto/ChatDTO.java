package com.ebury.dto;

import java.util.Date;

public class ChatDTO {

    private int id;
    private String nombreUsuarioA;
    private String nombreUsuarioB;
    private Date fechaUltimoMensaje;
    private String ultimoMensaje;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuarioA() {
        return nombreUsuarioA;
    }

    public void setNombreUsuarioA(String nombreUsuarioA) {
        this.nombreUsuarioA = nombreUsuarioA;
    }

    public String getNombreUsuarioB() {
        return nombreUsuarioB;
    }

    public void setNombreUsuarioB(String nombreUsuarioB) {
        this.nombreUsuarioB = nombreUsuarioB;
    }

    public Date getFechaUltimoMensaje() {
        return fechaUltimoMensaje;
    }

    public void setFechaUltimoMensaje(Date fechaUltimoMensaje) {
        this.fechaUltimoMensaje = fechaUltimoMensaje;
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(String ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }
}
