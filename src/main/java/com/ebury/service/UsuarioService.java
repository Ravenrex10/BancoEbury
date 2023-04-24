package com.ebury.service;

import com.ebury.dao.EstadoRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EstadoRepository estadoRepository;

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

    public List<UsuarioDTO> findAllByAltaFalse(){
        List<UsuarioEntity> usuariosEntity = usuarioRepository.findAllByAlta(false);
        return usuariosEntity.stream().map(UsuarioEntity::toDTO).collect(Collectors.toList());
    }

    public void darDeAltaUsuario(Integer usuario){
        UsuarioEntity usuarioEntity = (UsuarioEntity) usuarioRepository.findById(usuario).orElse(null);

        usuarioEntity.setAlta(true);

        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setEstadoCuentaByEstado(estadoRepository.getReferenceById(1));
        cuenta.setUsuarioByDuenyo(usuarioEntity);

        usuarioRepository.save(usuarioEntity);
    }

}
