package com.ebury.ui;

public class FiltroTransferencias {
    /**
     * Filtro usado para filtrar las transferencias.
     * @author Diego
     */
    private String filtro;
    private String divisa;
    private Integer usuario;
    private Integer cuenta;
    private String ordenPorFecha;

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getOrdenPorFecha() {
        return ordenPorFecha;
    }

    public void setOrdenPorFecha(String ordenPorFecha) {
        this.ordenPorFecha = ordenPorFecha;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }
}
