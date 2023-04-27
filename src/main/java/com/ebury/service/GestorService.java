package com.ebury.service;

import com.ebury.dao.RolRepository;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.RolEntity;
import com.ebury.ui.FiltroUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestorService {

    @Autowired
    RolRepository rolRepository;

    @Autowired
    UsuarioService usuarioService;

    public List<String> getRoles(){
        List<RolEntity> roles = rolRepository.findAll();
        List<String> rolesNombres = new ArrayList<>();
        for(RolEntity rol : roles){
            rolesNombres.add(rol.getNombre());
        }
        return rolesNombres;
    }

    public List<UsuarioDTO> filtrar(FiltroUsuarios filtroUsuario){
        List<UsuarioDTO> usuarios;
        if(filtroUsuario.getFiltro().equals("0")){
            usuarios = usuarioService.findAllClientes();
        }else{
            String filtro = "";
            switch (filtroUsuario.getFiltro()){
                case "Cliente Particular": filtro="Cliente";
                    break;

                case "Fundador de empresa": filtro="FundadorEmpresa";
                    break;

                case "Autorizado de empresa": filtro="AutorizadoEmpresa";
                    break;

                case "Socio de empresa": filtro="SocioEmpresa";
            }
            usuarios = usuarioService.findClientesFiltrados(filtro);
        }
        return usuarios;
    }
}
