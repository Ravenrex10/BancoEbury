package com.ebury.dto;

import java.util.Collection;

public class DireccionDTO {
    /**
     * @author Diego
     */
    private int id;
    private String calle;
    private String numero;
    private String planta;
    private String ciudad;
    private String pais;
    private String region;
    private String cp;
    private Collection<EmpresaDTO> empresasById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Collection<EmpresaDTO> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(Collection<EmpresaDTO> empresasById) {
        this.empresasById = empresasById;
    }
}
