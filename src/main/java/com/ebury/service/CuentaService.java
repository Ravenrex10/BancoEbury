package com.ebury.service;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.EstadoRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.CuentaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.EstadoCuentaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
        Devuelve todas las cuentas excepto las del usuario pasado por parámetro
        @author Diego
     */
    public List<CuentaDTO> findAllCuentasExceptThisUser(Integer usuario)
    {
        List<CuentaEntity> cuentas = this.cuentaRepository.findAllCuentasExceptThisUserById(usuario);
        return cuentas.stream().map(CuentaEntity::toDTO).collect(Collectors.toList());
    }

    /**
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

    /**
        Busca la cuenta por el id y devuelve su dto
        @author Diego
     */
    public CuentaDTO findCuentaByIdToDto(Integer id)
    {
        CuentaEntity cuenta = this.cuentaRepository.findById(id).orElse(null);
        return cuenta.toDTO();
    }

    /**
        Busca todas las cuentas de una empresa
        @author Diego
     */

    public List<CuentaDTO> findAllCuentasByEmpresa(Integer idEmpresa) {
        List<CuentaEntity> cuentaEntities = this.cuentaRepository.findAllByEmpresaId(idEmpresa);
        List<CuentaDTO> res = new ArrayList<>();
        for(CuentaEntity c : cuentaEntities)
        {
            res.add(c.toDTO());
        }
        return res;
    }

    public List<UsuarioDTO> findSociosAndAutorizadosByEmpresaIdNotBloqueado(Integer idEmpresa) {
        List<UsuarioEntity> usuarioEntityList = this.cuentaRepository.findAllCuentasDeSociosYAutorizadosDeEmpresaIdNotBloqueado(idEmpresa);
        List<UsuarioDTO> res = new ArrayList<>();
        for(UsuarioEntity u : usuarioEntityList)
        {
            res.add(u.toDTO());
        }
        return res;
    }
}
