package com.ebury.service;

import com.ebury.dao.DireccionRepository;
import com.ebury.dao.EmpresaRepository;
import com.ebury.dto.DireccionDTO;
import com.ebury.dto.EmpresaDTO;
import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {
    @Autowired
    protected DireccionRepository direccionRepository;

    @Autowired
    protected EmpresaRepository empresaRepository;

    /**  Devuelve la dirección de una empresa en concreto
        @author Diego
    */
    public DireccionDTO findDireccionByEmpresaId(Integer id)
    {
        EmpresaEntity empresaEntity = this.empresaRepository.findById(id).orElse(null);
        DireccionEntity direccionEntity = this.direccionRepository.findDireccionEntityByEmpresasById(empresaEntity);

        return direccionEntity.toDO();

    }
}
