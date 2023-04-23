package com.ebury.ui;

import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import com.ebury.entity.UsuarioEntity;

public class EmpresaWrapper {
    private EmpresaEntity newEmpresa;
    private DireccionEntity newDireccion;

    public EmpresaEntity getNewEmpresa() {
        return newEmpresa;
    }

    public void setNewEmpresa(EmpresaEntity newEmpresa) {
        this.newEmpresa = newEmpresa;
    }

    public DireccionEntity getNewDireccion() {
        return newDireccion;
    }

    public void setNewDireccion(DireccionEntity newDireccion) {
        this.newDireccion = newDireccion;
    }
}
