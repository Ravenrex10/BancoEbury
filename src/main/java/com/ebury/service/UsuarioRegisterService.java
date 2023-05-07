package com.ebury.service;

import com.ebury.dao.RolRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.UsuarioEntity;
import com.ebury.ui.UsuarioWrapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRegisterService {

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected RolRepository rolRepository;

    public String makeRegister(UsuarioWrapper usuarioWrapper) {
        UsuarioDTO user = usuarioWrapper.getNewUsuario();

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setNif(user.getNif());
        usuarioEntity.setEmail(user.getEmail());
        usuarioEntity.setContrasenya(user.getContrasenya());
        usuarioEntity.setPrimerNombre(user.getPrimerNombre());
        usuarioEntity.setSegundoNombre(user.getSegundoNombre());
        usuarioEntity.setPrimerApellido(user.getPrimerApellido());
        usuarioEntity.setSegundoApellido(user.getSegundoApellido());
        usuarioEntity.setFechaNacimiento(user.getFechaNacimiento());

        usuarioEntity.setRolByRol(rolRepository.findByNombre("Cliente"));

        usuarioEntity.setAlta(false);
        usuarioEntity.setAltaSolicitada(true);

        this.usuarioRepository.save(usuarioEntity);

        return ("redirect:/");
    }
}
