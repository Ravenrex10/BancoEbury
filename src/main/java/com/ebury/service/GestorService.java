package com.ebury.service;

import com.ebury.dao.RolRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.ChatEntity;
import com.ebury.entity.RolEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestorService {

    @Autowired
    RolRepository rolRepository;

    public List<String> getRoles(){
        List<RolEntity> roles = rolRepository.findAll();
        List<String> rolesNombres = new ArrayList<>();
        for(RolEntity rol : roles){
            rolesNombres.add(rol.getNombre());
        }
        return rolesNombres;
    }
}
