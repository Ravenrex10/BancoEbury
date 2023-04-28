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
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected DireccionRepository direccionRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected RolRepository rolRepository;

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

    /* Modifica el usuario, la dirección y la empresa
    / @author Diego*/
    public String makeEdit(EmpresaWrapper empresaWrapper, HttpSession session) {
        //TODO: Control de usuario no repetido
        //TODO: Mensaje de volver a entrar por haber modificado la cuenta
        //TODO: Control de valores nulos
        //TODO: Control de errores

        EmpresaDTO e = empresaWrapper.getNewEmpresa();
        DireccionDTO d = empresaWrapper.getNewDireccion();
        UsuarioDTO u = empresaWrapper.getNewUsuario();

        UsuarioDTO userActual = (UsuarioDTO) session.getAttribute("usuario");

        EmpresaEntity empresa = new EmpresaEntity();
        DireccionEntity direccion = new DireccionEntity();
        UsuarioEntity usuario = new UsuarioEntity();

        empresa.setId(e.getId());
        empresa.setCif(e.getCif());
        empresa.setNombre(e.getNombre());

        direccion.setId(d.getId());
        direccion.setCalle(d.getCalle());
        direccion.setCiudad(d.getCiudad());
        direccion.setNumero(d.getNumero());
        direccion.setCp(d.getCp());
        direccion.setPlanta(d.getPlanta());
        direccion.setRegion(d.getRegion());
        direccion.setPais(d.getPais());

        usuario.setId(u.getId());
        usuario.setPrimerNombre(u.getPrimerNombre());
        usuario.setSegundoNombre(u.getSegundoNombre());
        usuario.setPrimerApellido(u.getPrimerApellido());
        usuario.setSegundoApellido(u.getSegundoApellido());
        usuario.setContrasenya(u.getContrasenya());
        usuario.setEmail(u.getEmail());
        usuario.setNif(u.getNif());
        usuario.setFechaNacimiento(u.getFechaNacimiento());

        UsuarioEntity userActualEntity = this.usuarioRepository.findById(userActual.getId()).orElse(null);

        usuario.setRolByRol(this.rolRepository.findByNombre(userActual.getRolName()));
        usuario.setAlta(userActualEntity.getAlta());
        usuario.setAltaSolicitada(userActualEntity.getAltaSolicitada());

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

        return ("redirect:/logout");
    }

}
