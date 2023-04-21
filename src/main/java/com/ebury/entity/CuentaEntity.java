package com.ebury.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Cuenta", schema = "BancoEbury", catalog = "")
public class CuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "iban")
    private String iban;

    @ManyToOne
    @JoinColumn(name = "duenyo", referencedColumnName = "id")
    private UsuarioEntity usuarioByDuenyo;
    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id")
    private EstadoCuentaEntity estadoCuentaByEstado;
    @OneToMany(mappedBy = "cuentaByCuenta")
    private Collection<SaldoEntity> saldosById;
    @OneToMany(mappedBy = "cuentaByCuentaOrigen")
    private Collection<TransferenciaEntity> transferenciasById;
    @OneToMany(mappedBy = "cuentaByCuentaDestino")
    private Collection<TransferenciaEntity> transferenciasById_0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaEntity that = (CuentaEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }

    public UsuarioEntity getUsuarioByDuenyo() {
        return usuarioByDuenyo;
    }

    public void setUsuarioByDuenyo(UsuarioEntity usuarioByDuenyo) {
        this.usuarioByDuenyo = usuarioByDuenyo;
    }

    public EstadoCuentaEntity getEstadoCuentaByEstado() {
        return estadoCuentaByEstado;
    }

    public void setEstadoCuentaByEstado(EstadoCuentaEntity estadoCuentaByEstado) {
        this.estadoCuentaByEstado = estadoCuentaByEstado;
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
