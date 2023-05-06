package com.ebury.service;

import com.ebury.dao.DireccionRepository;
import com.ebury.dao.EmpresaRepository;
import com.ebury.dao.RolRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.DireccionDTO;
import com.ebury.dto.EmpresaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import com.ebury.entity.UsuarioEntity;
import com.ebury.ui.EmpresaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class EmpresaRegisterService {
    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected RolRepository rolRepository;

    @Autowired
    protected DireccionRepository direccionRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    /** Registra al socio fundador (fundador de la empresa, la empresa y su dirección)
       @author Diego
     */
    public String makeRegister(EmpresaWrapper empresaWrapper) {

        EmpresaDTO e = empresaWrapper.getNewEmpresa();
        DireccionDTO d = empresaWrapper.getNewDireccion();
        UsuarioDTO u = empresaWrapper.getNewUsuario();

        EmpresaEntity empresa = new EmpresaEntity();
        DireccionEntity direccion = new DireccionEntity();
        UsuarioEntity usuario = new UsuarioEntity();

        empresa.setCif(e.getCif());
        empresa.setNombre(e.getNombre());

        direccion.setCalle(d.getCalle());
        direccion.setCiudad(d.getCiudad());
        direccion.setNumero(d.getNumero());
        direccion.setCp(d.getCp());
        direccion.setPlanta(d.getPlanta());
        direccion.setRegion(d.getRegion());
        direccion.setPais(d.getPais());

        usuario.setPrimerNombre(u.getPrimerNombre());
        usuario.setSegundoNombre(u.getSegundoNombre());
        usuario.setPrimerApellido(u.getPrimerApellido());
        usuario.setSegundoApellido(u.getSegundoApellido());
        usuario.setContrasenya(u.getContrasenya());
        usuario.setEmail(u.getEmail());
        usuario.setNif(u.getNif());
        usuario.setFechaNacimiento(u.getFechaNacimiento());

        usuario.setRolByRol(this.rolRepository.findById(4).orElse(null));
        usuario.setAlta(false);
        usuario.setAltaSolicitada(true);

        // Añadir direccion en empresa
        empresa.setDireccionByDireccion(direccion);

        // Añadir empresa en direccion
        Collection<EmpresaEntity> empresaEntities = new ArrayList<>();
        empresaEntities.add(empresa);
        direccion.setEmpresasById(empresaEntities);

        // Añadir empresa a usuario
        usuario.setEmpresaByEmpresa(empresa);

        // Añadir usuario a empresa
        Collection<UsuarioEntity> usuarioEntities = new ArrayList<>();
        usuarioEntities.add(usuario);
        empresa.setUsuariosById(usuarioEntities);

        // Insertar
        this.direccionRepository.save(direccion);
        this.empresaRepository.save(empresa);
        this.usuarioRepository.save(usuario);

        return ("redirect:/");
    }
}
