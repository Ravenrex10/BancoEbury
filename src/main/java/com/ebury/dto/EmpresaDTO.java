package com.ebury.dto;

import java.util.Collection;

public class EmpresaDTO {
    private int id;
    private int cif;
    private String nombre;
    private String contrasenya;
    private DireccionDTO direccionByDireccion;
    private Collection<UsuarioDTO> usuariosByCif;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCif() {
        return cif;
    }

    public void setCif(int cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public DireccionDTO getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public void setDireccionByDireccion(DireccionDTO direccionByDireccion) {
        this.direccionByDireccion = direccionByDireccion;
    }

    public Collection<UsuarioDTO> getUsuariosByCif() {
        return usuariosByCif;
    }

    public void setUsuariosByCif(Collection<UsuarioDTO> usuariosByCif) {
        this.usuariosByCif = usuariosByCif;
    }
}
