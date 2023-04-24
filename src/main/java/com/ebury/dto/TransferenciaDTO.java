package com.ebury.dto;
import java.sql.Date;
public class TransferenciaDTO {
    private Double cantidad;
    private Date fecha;
    private String divisaOrigen;
    private String divisaDestino;
    private CuentaDTO cuentaOrigen;
    private CuentaDTO cuentaDestino;

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

    public String getDivisaOrigen() {
        return divisaOrigen;
    }

    public void setDivisaOrigen(String divisaOrigen) {
        this.divisaOrigen = divisaOrigen;
    }

    public String getDivisaDestino() {
        return divisaDestino;
    }

    public void setDivisaDestino(String divisaDestino) {
        this.divisaDestino = divisaDestino;
    }

    public CuentaDTO getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(CuentaDTO cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public CuentaDTO getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(CuentaDTO cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
