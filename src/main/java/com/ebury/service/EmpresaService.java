package com.ebury.service;

import com.ebury.dao.EmpresaRepository;
import com.ebury.dto.EmpresaDTO;
import com.ebury.entity.EmpresaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    protected EmpresaRepository empresaRepository;

    public EmpresaDTO findByCifAndContrasenya(Integer cif, String clave) {
        //EmpresaEntity empresa = this.empresaRepository.findByCifAndContrasenya(cif, clave);
        EmpresaEntity empresa = new EmpresaEntity(); //TEMPORAL!!!
        EmpresaDTO empresaDTO = empresa.toDTO();
        return empresaDTO;
    }

    public List<EmpresaDTO> findAll(){
        List<EmpresaEntity> empresas = empresaRepository.findAll();
        return empresas.stream().map(EmpresaEntity::toDTO).collect(Collectors.toList());
    }

    public EmpresaDTO findById(Integer id){
        EmpresaEntity empresa = empresaRepository.findById(id).orElse(null);
        if(empresa==null){
            return null;
        }else{
            return empresa.toDTO();
        }
    }

}
