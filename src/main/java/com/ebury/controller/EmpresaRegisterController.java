package com.ebury.controller;

import com.ebury.dao.DireccionRepository;
import com.ebury.dao.EmpresaRepository;
import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import com.ebury.entity.UsuarioEntity;
import com.ebury.ui.EmpresaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class EmpresaRegisterController {

    @Autowired
    protected EmpresaRepository empresaRepository;

    @Autowired
    protected DireccionRepository direccionRepository;

    @GetMapping("/registerEmpresa")
    public String doRegister(Model model)
    {
        EmpresaWrapper empresaWrapper = new EmpresaWrapper();
        model.addAttribute("newEmpresaWrapper",empresaWrapper);
        return "empresaRegister";
    }

    @PostMapping("/registerEmpresa/register")
    public String makeRegister(Model model, @ModelAttribute("newEmpresaWrapper") EmpresaWrapper empresaWrapper)
    {
        //TODO: Arreglar save
        //TODO: Control de valores nulos
        //TODO: Control de errores
        //TODO: Usuario asociado

        EmpresaEntity e = empresaWrapper.getNewEmpresa();
        DireccionEntity d = empresaWrapper.getNewDireccion();

        // Añadir direccion en empresa
        e.setDireccionByDireccion(d);

        // Añadir empresa en direccion
        Collection<EmpresaEntity> empresaEntities  = new ArrayList<>();
        empresaEntities.add(e);
        d.setEmpresasById(empresaEntities);

        // Insertar
        this.direccionRepository.save(d);
        this.empresaRepository.save(e);

        return ("redirect:/");
    }


}
