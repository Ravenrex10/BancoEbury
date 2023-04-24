package com.ebury.ui;

import com.ebury.dto.DireccionDTO;
import com.ebury.dto.EmpresaDTO;
import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import com.ebury.entity.UsuarioEntity;

public class EmpresaWrapper {
    private EmpresaDTO newEmpresa;
    private DireccionDTO newDireccion;

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
}
