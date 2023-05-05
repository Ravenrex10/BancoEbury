package com.ebury.ui;

import com.ebury.dto.DireccionDTO;
import com.ebury.dto.EmpresaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import com.ebury.entity.UsuarioEntity;

/**
 * @author Diego
 * Esta clase auxiliar sirve para crear el registro y edición de una empresa, socio y/o autorizado
 */
public class EmpresaWrapper {
    private EmpresaDTO newEmpresa;
    private DireccionDTO newDireccion;
    private UsuarioDTO newUsuario;

    public EmpresaDTO getNewEmpresa() {
        return newEmpresa;
    }

    public void setNewEmpresa(EmpresaDTO newEmpresa) {
        this.newEmpresa = newEmpresa;
    }

    public DireccionDTO getNewDireccion() {
        return newDireccion;
    }

    public void setNewDireccion(DireccionDTO newDireccion) {
        this.newDireccion = newDireccion;
    }

    public UsuarioDTO getNewUsuario() {
        return newUsuario;
    }

    public void setNewUsuario(UsuarioDTO newUsuario) {
        this.newUsuario = newUsuario;
    }
}
