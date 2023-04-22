package com.ebury.entity;

import com.ebury.dto.MensajeDTO;
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
    @Column(name = "fecha")
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "chat", referencedColumnName = "id")
    private ChatEntity chatByChat;

    public int getId() {
        return id;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (contenido != null ? contenido.hashCode() : 0);
        result = 31 * result + (enviadoPorA != null ? enviadoPorA.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public ChatEntity getChatByChat() {
        return chatByChat;
    }

    public void setChatByChat(ChatEntity chatByChat) {
        this.chatByChat = chatByChat;
    }

    public MensajeDTO toDto() {
        MensajeDTO dto = new MensajeDTO();
        dto.setId(this.getId());
        dto.setChatId(this.getChatByChat().getId());
        dto.setFecha(this.fecha);
        dto.setContenido(this.getContenido());
        // dto.enviadoPorUsuarioActual se calcula en chatService
        return dto;
    }
}
