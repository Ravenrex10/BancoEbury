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
    @Column(name = "cuentaOrigen")
    private Integer cuentaOrigen;
    @Basic
    @Column(name = "cuentaDestino")
    private Integer cuentaDestino;
    @Basic
    @Column(name = "divisaOrigen")
    private Integer divisaOrigen;
    @Basic
    @Column(name = "cantidad")
    private Double cantidad;
    @Basic
    @Column(name = "divisaDestino")
    private Integer divisaDestino;
    @Basic
    @Column(name = "fecha")
    private Date fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Integer cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Integer getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Integer cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Integer getDivisaOrigen() {
        return divisaOrigen;
    }

    public void setDivisaOrigen(Integer divisaOrigen) {
        this.divisaOrigen = divisaOrigen;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getDivisaDestino() {
        return divisaDestino;
    }

    public void setDivisaDestino(Integer divisaDestino) {
        this.divisaDestino = divisaDestino;
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
        if (cuentaOrigen != null ? !cuentaOrigen.equals(that.cuentaOrigen) : that.cuentaOrigen != null) return false;
        if (cuentaDestino != null ? !cuentaDestino.equals(that.cuentaDestino) : that.cuentaDestino != null)
            return false;
        if (divisaOrigen != null ? !divisaOrigen.equals(that.divisaOrigen) : that.divisaOrigen != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (divisaDestino != null ? !divisaDestino.equals(that.divisaDestino) : that.divisaDestino != null)
            return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cuentaOrigen != null ? cuentaOrigen.hashCode() : 0);
        result = 31 * result + (cuentaDestino != null ? cuentaDestino.hashCode() : 0);
        result = 31 * result + (divisaOrigen != null ? divisaOrigen.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (divisaDestino != null ? divisaDestino.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
