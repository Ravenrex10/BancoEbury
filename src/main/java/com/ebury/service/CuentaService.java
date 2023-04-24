package com.ebury.service;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.CuentaDTO;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<CuentaDTO> findAllCuentasByUsuario(Integer usuario){
        List<CuentaEntity> cuentas = cuentaRepository.findAllByUsuarioByDuenyo(usuarioRepository.findById(usuario).orElse(null));
        return cuentas.stream().map(CuentaEntity::toDTO).collect(Collectors.toList());
    }
}
