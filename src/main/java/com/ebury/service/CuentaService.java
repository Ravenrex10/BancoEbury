package com.ebury.service;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.EstadoRepository;
import com.ebury.dao.RolRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.CuentaDTO;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.EstadoCuentaEntity;
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

    @Autowired
    EstadoRepository estadoRepository;

    public List<CuentaDTO> findAllCuentasByUsuario(Integer usuario){
        List<CuentaEntity> cuentas = cuentaRepository.findAllByUsuarioByDuenyo(usuarioRepository.findById(usuario).orElse(null));
        return cuentas.stream().map(CuentaEntity::toDTO).collect(Collectors.toList());
    }

    /*
        Devuelve todas las cuentas excepto las del usuario pasado por parámetro
        @author Diego
     */
    public List<CuentaDTO> findAllCuentasExceptThisUser(Integer usuario)
    {
        List<CuentaEntity> cuentas = this.cuentaRepository.findAllCuentasExceptThisUserById(usuario);
        return cuentas.stream().map(CuentaEntity::toDTO).collect(Collectors.toList());
    }

    /*
        Cambia el estado de la cuenta a "SolicitudBloqueada"
        @author Diego
     */
    public void solicitarDesbloqueoCuenta(Integer id)
    {
        CuentaEntity cuenta = this.cuentaRepository.findById(id).orElse(null);
        EstadoCuentaEntity estadoCuenta = this.estadoRepository.findById(3).orElse(null);

        cuenta.setEstadoCuentaByEstado(estadoCuenta);
        this.cuentaRepository.save(cuenta);

    }
}
