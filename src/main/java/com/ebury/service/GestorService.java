package com.ebury.service;

import com.ebury.dao.RolRepository;
import com.ebury.entity.RolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
