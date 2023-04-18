package com.ebury.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Transferencia", schema = "BancoEbury", catalog = "")
public class TransferenciaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "cantidad")
    private Double cantidad;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "cuentaOrigen", referencedColumnName = "id")
    private CuentaEntity cuentaByCuentaOrigen;
    @ManyToOne
    @JoinColumn(name = "cuentaDestino", referencedColumnName = "id")
    private CuentaEntity cuentaByCuentaDestino;
    @ManyToOne
    @JoinColumn(name = "divisaOrigen", referencedColumnName = "id")
    private DivisaEntity divisaByDivisaOrigen;
    @ManyToOne
    @JoinColumn(name = "divisaDestino", referencedColumnName = "id")
    private DivisaEntity divisaByDivisaDestino;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
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

        TransferenciaEntity that = (TransferenciaEntity) o;

        if (id != that.id) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    public CuentaEntity getCuentaByCuentaOrigen() {
        return cuentaByCuentaOrigen;
    }

    public void setCuentaByCuentaOrigen(CuentaEntity cuentaByCuentaOrigen) {
        this.cuentaByCuentaOrigen = cuentaByCuentaOrigen;
    }

    public CuentaEntity getCuentaByCuentaDestino() {
        return cuentaByCuentaDestino;
    }

    public void setCuentaByCuentaDestino(CuentaEntity cuentaByCuentaDestino) {
        this.cuentaByCuentaDestino = cuentaByCuentaDestino;
    }

    public DivisaEntity getDivisaByDivisaOrigen() {
        return divisaByDivisaOrigen;
    }

    public void setDivisaByDivisaOrigen(DivisaEntity divisaByDivisaOrigen) {
        this.divisaByDivisaOrigen = divisaByDivisaOrigen;
    }

    public DivisaEntity getDivisaByDivisaDestino() {
        return divisaByDivisaDestino;
    }

    public void setDivisaByDivisaDestino(DivisaEntity divisaByDivisaDestino) {
        this.divisaByDivisaDestino = divisaByDivisaDestino;
    }
}
