package com.ebury.ui;

/**
 * @author Daniel
 */
public class FiltroChats {
    private String criterioOrdenacion;

    private boolean mostrarCerrados;

    private boolean mostrarSoloPropios;

    private int filtroUsuario;

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

    public boolean isMostrarSoloPropios() {
        return mostrarSoloPropios;
    }

    public void setMostrarSoloPropios(boolean mostrarSoloPropios) {
        this.mostrarSoloPropios = mostrarSoloPropios;
    }

    public int getFiltroUsuario() {
        return filtroUsuario;
    }

    public void setFiltroUsuario(int filtroUsuario) {
        this.filtroUsuario = filtroUsuario;
    }
}
