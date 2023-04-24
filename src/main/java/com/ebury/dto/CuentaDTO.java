package com.ebury.dto;

import java.util.Collection;
import java.util.List;

public class CuentaDTO {
    private String iban;
    private UsuarioDTO usuario;
    private String estado;
    private List<TransferenciaDTO> transferenciasEnviadas;
    private List<TransferenciaDTO> transferenciasRecibidas;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<TransferenciaDTO> getTransferenciasEnviadas() {
        return transferenciasEnviadas;
    }

    public void setTransferenciasEnviadas(List<TransferenciaDTO> transferenciasEnviadas) {
        this.transferenciasEnviadas = transferenciasEnviadas;
    }

    public Collection<TransferenciaDTO> getTransferenciasRecibidas() {
        return transferenciasRecibidas;
    }

    public void setTransferenciasRecibidas(List<TransferenciaDTO> transferenciasRecibidas) {
        this.transferenciasRecibidas = transferenciasRecibidas;
    }
}