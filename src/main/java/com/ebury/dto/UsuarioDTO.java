package com.ebury.dto;

import java.sql.Date;

public class UsuarioDTO {
    /**
     * @author Dani 33,33%
     * @author Diego 33,33%
     * @author Lucas Colbert Eastgate 33,33%
     */

    private int id;
    private int nif;
    private String email;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private Integer empresa;
    private String rolName;

    private Boolean alta;
    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    private String segundoApellido;
    private Date fechaNacimiento;
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    private String contrasenya;

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

}
