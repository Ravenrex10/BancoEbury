package com.ebury.service;

import com.ebury.dao.*;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    DivisaRepository divisaRepository;
    @Autowired
    SaldoRepository saldoRepository;
    @Autowired
    TransferenciasRepository transferenciasRepository;

    public List<UsuarioDTO> findUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    public UsuarioDTO findUsuarioById(int id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return null;
        } else {
            return usuario.toDTO();
        }
    }

    public List<UsuarioDTO> findAllClientes(){
        List<UsuarioEntity> usuariosEntity = usuarioRepository.findAllClientes();
        return usuariosEntity.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    public List<UsuarioDTO> findClientesFiltrados(String filtroUsuario){
        List<UsuarioEntity> usuariosEntity = usuarioRepository.findAllByRolByRolNombre(filtroUsuario);
        return usuariosEntity.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    public List<UsuarioDTO> findAllByAltaSolicitada(){
        List<UsuarioEntity> usuariosEntity = usuarioRepository.findAllByAltaSolicitada(true);
        return usuariosEntity.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    public void darDeAltaUsuario(Integer usuario){
        UsuarioEntity usuarioEntity = (UsuarioEntity) usuarioRepository.findById(usuario).orElse(null);

        usuarioEntity.setAlta(true);
        usuarioEntity.setAltaSolicitada(false);

        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setEstadoCuentaByEstado(estadoRepository.getReferenceById(1));
        cuenta.setUsuarioByDuenyo(usuarioEntity);
        String Iban = ("ES"+ (Math.abs(new Random().nextInt())) + System.currentTimeMillis());
        Iban = Iban.substring(0, Math.min(Iban.length(), 23));
        cuenta.setIban(Iban);
        SaldoEntity saldo = new SaldoEntity();
        saldo.setCuentaByCuenta(cuenta);
        saldo.setCantidad(0.0);
        DivisaEntity divisa = (DivisaEntity) divisaRepository.findById(1).orElse(null);
        saldo.setDivisaByDivisa(divisa);

        usuarioRepository.save(usuarioEntity);
        cuentaRepository.save(cuenta);
        saldoRepository.save(saldo);
    }

    public void denegarAltaUsuario(Integer usuario){
        UsuarioEntity usuarioEntity = (UsuarioEntity) usuarioRepository.findById(usuario).orElse(null);

        usuarioEntity.setAltaSolicitada(false);

        usuarioRepository.save(usuarioEntity);
    }

    public List<TransferenciaDTO> findAllTransferencias(Integer usuario){
        List<TransferenciaEntity> transferenciaEntities = transferenciasRepository.findAllByUsuario(usuario);
        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }
    public List<UsuarioDTO> findUsuariosByRolNombre(String rolName) {
        return usuarioRepository.findAllByRolByRolNombre(rolName).stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    public UsuarioDTO autenticar(String nombreUsuario, String clave) {
        UsuarioEntity usuario = usuarioRepository.autenticar(nombreUsuario, clave);
        if (usuario == null) return null;
        return usuario.toDTO();
    }

}
