package com.ebury.dto;

import java.util.Date;

public class MensajeDTO {
    private int id;
    private int chatId;
    private String contenido;
    private Date fecha;

    /**
     * True si el mensaje lo envió el usuario que está usando el chat (entonces se mostrará a la derecha)
     */
    private boolean enviadoPorUsuarioActual;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEnviadoPorUsuarioActual() {
        return enviadoPorUsuarioActual;
    }

    public void setEnviadoPorUsuarioActual(boolean enviadoPorUsuarioActual) {
        this.enviadoPorUsuarioActual = enviadoPorUsuarioActual;
    }

}
