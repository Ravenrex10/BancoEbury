package com.ebury.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Divisa", schema = "BancoEbury", catalog = "")
public class DivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "valor")
    private Double valor;
    @OneToMany(mappedBy = "divisaByDivisa")
    private Collection<SaldoEntity> saldosById;
    @OneToMany(mappedBy = "divisaByDivisaOrigen")
    private Collection<TransferenciaEntity> transferenciasById;
    @OneToMany(mappedBy = "divisaByDivisaDestino")
    private Collection<TransferenciaEntity> transferenciasById_0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivisaEntity that = (DivisaEntity) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (valor != null ? !valor.equals(that.valor) : that.valor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }

    public Collection<SaldoEntity> getSaldosById() {
        return saldosById;
    }

    public void setSaldosById(Collection<SaldoEntity> saldosById) {
        this.saldosById = saldosById;
    }

    public Collection<TransferenciaEntity> getTransferenciasById() {
        return transferenciasById;
    }

    public void setTransferenciasById(Collection<TransferenciaEntity> transferenciasById) {
        this.transferenciasById = transferenciasById;
    }

    public Collection<TransferenciaEntity> getTransferenciasById_0() {
        return transferenciasById_0;
    }

    public void setTransferenciasById_0(Collection<TransferenciaEntity> transferenciasById_0) {
        this.transferenciasById_0 = transferenciasById_0;
    }
}
