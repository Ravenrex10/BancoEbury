package com.ebury.service;

import com.ebury.dao.EmpresaRepository;
import com.ebury.dto.EmpresaDTO;
import com.ebury.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    protected EmpresaRepository empresaRepository;

    public EmpresaDTO findByCifAndContrasenya(Integer cif, String clave) {
        EmpresaEntity empresa = this.empresaRepository.findByCifAndContrasenya(cif, clave);
        EmpresaDTO empresaDTO = empresa.toDTO();
        return empresaDTO;
    }

}
