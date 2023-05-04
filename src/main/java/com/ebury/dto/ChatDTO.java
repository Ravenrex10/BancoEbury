package com.ebury.dto;

import java.util.Date;
/**
 * @author Daniel
 */
public class ChatDTO {

    private int id;
    private String nombreUsuarioA;
    private String nombreUsuarioB;

    private MensajeDTO ultimoMensaje;
    private boolean cerrado;

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

    public MensajeDTO getUltimoMensaje() {
        return ultimoMensaje;
    }

    public void setUltimoMensaje(MensajeDTO ultimoMensaje) {
        this.ultimoMensaje = ultimoMensaje;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }
}
