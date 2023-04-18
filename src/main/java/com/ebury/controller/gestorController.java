package com.ebury.controller;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/gestorHome")
@Controller
public class gestorController {

    @Autowired
    protected UsuarioRepository userRepo;

    @Autowired
    protected CuentaRepository cuentaRepo;

    @GetMapping("/")
    public String doGestor(Model model){
        model.addAttribute("usuarios", userRepo.findAll());
        return "gestorHome";
    }

    @GetMapping("/gestorAlta")
    public String doGestorAlta(Model model){
        List<UsuarioEntity> usuariosSinAlta = userRepo.findAllByAlta(false);
        model.addAttribute("usuariosSinAlta", usuariosSinAlta);
        return "gestorAlta";
    }

    @GetMapping("/darDeAlta")
    public String darDeAlta(Model model, @RequestParam("usuario") String usuario){
        UsuarioEntity usuarioEntity = userRepo.getById(Integer.parseInt(usuario));

        usuarioEntity.setAlta(true);

        userRepo.save(usuarioEntity);

        return "redirect:/gestorHome/gestorAlta";
    }
}
