package com.ebury.service;

import com.ebury.dao.DireccionRepository;
import com.ebury.dao.EmpresaRepository;
import com.ebury.dto.DireccionDTO;
import com.ebury.dto.EmpresaDTO;
import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import com.ebury.ui.EmpresaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class EmpresaRegisterService {
    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected DireccionRepository direccionRepository;
    public String makeRegister(EmpresaWrapper empresaWrapper)
    {
        //TODO: Arreglar save
        //TODO: Control de valores nulos
        //TODO: Control de errores
        //TODO: Usuario asociado

        EmpresaDTO e = empresaWrapper.getNewEmpresa();
        DireccionDTO d = empresaWrapper.getNewDireccion();

        EmpresaEntity empresa = new EmpresaEntity();
        DireccionEntity direccion = new DireccionEntity();

        empresa.setCif(e.getCif());
        empresa.setId(e.getId());
        empresa.setNombre(e.getNombre());
        empresa.setContrasenya(e.getContrasenya());
        empresa.setId(e.getId());

        direccion.setId(d.getId());
        direccion.setCalle(d.getCalle());
        direccion.setCiudad(d.getCiudad());
        direccion.setNumero(d.getNumero());
        direccion.setCp(d.getCp());
        direccion.setPlanta(d.getPlanta());
        direccion.setRegion(d.getRegion());
        direccion.setPais(d.getPais());


        // Añadir direccion en empresa
        empresa.setDireccionByDireccion(direccion);

        // Añadir empresa en direccion
        Collection<EmpresaEntity> empresaEntities  = new ArrayList<>();
        empresaEntities.add(empresa);
        direccion.setEmpresasById(empresaEntities);

        // Insertar
        this.direccionRepository.save(direccion);
        this.empresaRepository.save(empresa);

        return("redirect:/");
    }
}
