package com.ebury.entity;

import com.ebury.dto.ChatDTO;
import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Chat", schema = "BancoEbury", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "clienteA", referencedColumnName = "id")
    private UsuarioEntity usuarioByClienteA;
    @ManyToOne
    @JoinColumn(name = "clienteB", referencedColumnName = "id")
    private UsuarioEntity usuarioByClienteB;
    @OneToMany(mappedBy = "chatByChat")
    private Collection<MensajeEntity> mensajesById;

    @Basic
    @Column(name = "cerrado")
    private Boolean cerrado;

    public int getId() {
        return id;
    }

    //TODO: borrar este metodo (es solo para testing)
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntity that = (ChatEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }

    public UsuarioEntity getUsuarioByClienteA() {
        return usuarioByClienteA;
    }

    public void setUsuarioByClienteA(UsuarioEntity usuarioByClienteA) {
        this.usuarioByClienteA = usuarioByClienteA;
    }

    public UsuarioEntity getUsuarioByClienteB() {
        return usuarioByClienteB;
    }

    public void setUsuarioByClienteB(UsuarioEntity usuarioByClienteB) {
        this.usuarioByClienteB = usuarioByClienteB;
    }

    public Collection<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(Collection<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    /**
     * @author Daniel
     * Devuelve la entidad Chat convertida a DTO:
     * Nota: el atributo ultimoMensaje se asigna en el controller
     */
    public ChatDTO toDTO() {
        ChatDTO dto = new ChatDTO();
        dto.setId(id);
        dto.setNombreUsuarioA(usuarioByClienteA.getEmail());
        dto.setNombreUsuarioB(usuarioByClienteB.getEmail());
        // TODO: hay que inicializar correctamente estos atributos
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        dto.setCerrado(this.getCerrado());
        return dto;
    }
}
