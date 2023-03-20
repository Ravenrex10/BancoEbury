package com.ebury.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Saldo", schema = "BancoEbury", catalog = "")
public class SaldoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "cuenta")
    private Integer cuenta;
    @Basic
    @Column(name = "cantidad")
    private Double cantidad;
    @Basic
    @Column(name = "divisa")
    private Integer divisa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getDivisa() {
        return divisa;
    }

    public void setDivisa(Integer divisa) {
        this.divisa = divisa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaldoEntity that = (SaldoEntity) o;

        if (id != that.id) return false;
        if (cuenta != null ? !cuenta.equals(that.cuenta) : that.cuenta != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (divisa != null ? !divisa.equals(that.divisa) : that.divisa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cuenta != null ? cuenta.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (divisa != null ? divisa.hashCode() : 0);
        return result;
    }
}
