package com.ebury.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Mensaje", schema = "BancoEbury", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "contenido")
    private String contenido;
    @Basic
    @Column(name = "enviadoPorA")
    private Byte enviadoPorA;
    @Basic
    @Column(name = "chat")
    private Integer chat;
    @Basic
    @Column(name = "fecha")
    private Date fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Byte getEnviadoPorA() {
        return enviadoPorA;
    }

    public void setEnviadoPorA(Byte enviadoPorA) {
        this.enviadoPorA = enviadoPorA;
    }

    public Integer getChat() {
        return chat;
    }

    public void setChat(Integer chat) {
        this.chat = chat;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MensajeEntity that = (MensajeEntity) o;

        if (id != that.id) return false;
        if (contenido != null ? !contenido.equals(that.contenido) : that.contenido != null) return false;
        if (enviadoPorA != null ? !enviadoPorA.equals(that.enviadoPorA) : that.enviadoPorA != null) return false;
        if (chat != null ? !chat.equals(that.chat) : that.chat != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        result = 31 * result + (enviadoPorA != null ? enviadoPorA.hashCode() : 0);
        result = 31 * result + (chat != null ? chat.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
