package com.ebury.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Chat", schema = "BancoEbury", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "clienteA")
    private Integer clienteA;
    @Basic
    @Column(name = "clienteB")
    private Integer clienteB;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getClienteA() {
        return clienteA;
    }

    public void setClienteA(Integer clienteA) {
        this.clienteA = clienteA;
    }

    public Integer getClienteB() {
        return clienteB;
    }

    public void setClienteB(Integer clienteB) {
        this.clienteB = clienteB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntity that = (ChatEntity) o;

        if (id != that.id) return false;
        if (clienteA != null ? !clienteA.equals(that.clienteA) : that.clienteA != null) return false;
        if (clienteB != null ? !clienteB.equals(that.clienteB) : that.clienteB != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (clienteA != null ? clienteA.hashCode() : 0);
        result = 31 * result + (clienteB != null ? clienteB.hashCode() : 0);
        return result;
    }
}
