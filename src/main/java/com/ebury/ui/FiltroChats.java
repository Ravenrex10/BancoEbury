package com.ebury.ui;

public class FiltroChats {
    private String criterioOrdenacion;

    private boolean mostrarCerrados;

    private boolean ascendente;

    private boolean mostrarSoloPropios;

    public String getCriterioOrdenacion() {
        return criterioOrdenacion;
    }

    public void setCriterioOrdenacion(String criterioOrdenacion) {
        this.criterioOrdenacion = criterioOrdenacion;
    }

    public boolean isMostrarCerrados() {
        return mostrarCerrados;
    }

    public void setMostrarCerrados(boolean mostrarCerrados) {
        this.mostrarCerrados = mostrarCerrados;
    }

    public boolean isAscendente() {
        return ascendente;
    }

    public void setAscendente(boolean ascendente) {
        this.ascendente = ascendente;
    }

    public boolean isMostrarSoloPropios() {
        return mostrarSoloPropios;
    }

    public void setMostrarSoloPropios(boolean mostrarSoloPropios) {
        this.mostrarSoloPropios = mostrarSoloPropios;
    }
}
