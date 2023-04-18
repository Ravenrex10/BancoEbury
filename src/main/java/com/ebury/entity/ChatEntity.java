package com.ebury.entity;

import jakarta.persistence.*;

import java.util.Collection;

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

    public int getId() {
        return id;
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
}
