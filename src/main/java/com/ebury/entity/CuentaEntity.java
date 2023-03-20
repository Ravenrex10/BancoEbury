package com.ebury.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Cuenta", schema = "BancoEbury", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "duenyo")
    private Integer duenyo;
    @Basic
    @Column(name = "estado")
    private Integer estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDuenyo() {
        return duenyo;
    }

    public void setDuenyo(Integer duenyo) {
        this.duenyo = duenyo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaEntity that = (CuentaEntity) o;

        if (id != that.id) return false;
        if (duenyo != null ? !duenyo.equals(that.duenyo) : that.duenyo != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (duenyo != null ? duenyo.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }
}
