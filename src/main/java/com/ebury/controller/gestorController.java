package com.ebury.controller;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class gestorController {

    @Autowired
    protected UsuarioRepository userRepo;

    @Autowired
    protected CuentaRepository cuentaRepo;

    @GetMapping("/gestorHome")
    public String doGestor(Model model){
        model.addAttribute("usuarios", userRepo.findAll());
        return "gestorHome";
    }

    @GetMapping("/gestorAlta")
    public String doGestorAlta(Model model){
        List<CuentaEntity> cuentas = cuentaRepo.findAll();
        List<UsuarioEntity> usuariosSinAlta = new ArrayList<UsuarioEntity>();
        for(CuentaEntity cuenta: cuentas){
            if(cuenta.getEstado()==0){
                UsuarioEntity usuario = userRepo.getReferenceById(cuenta.getDuenyo());
                usuariosSinAlta.add(usuario);
            }
        }
        model.addAttribute("usuariosSinAlta", usuariosSinAlta);
        return "gestorAlta";
    }
}
