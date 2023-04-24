package com.ebury.controller;

import com.ebury.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/gestorHome")
@Controller
public class gestorController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/")
    public String doGestor(Model model){
        model.addAttribute("usuarios", usuarioService.findAllClientes());
        return "gestorHome";
    }

    @GetMapping("/gestorAlta")
    public String doGestorAlta(Model model){
        model.addAttribute("usuariosSinAlta", usuarioService.findAllByAltaFalse());
        return "gestorAlta";
    }

    @GetMapping("/darDeAlta")
    public String darDeAlta(Model model, @RequestParam("usuario") Integer usuario){
        usuarioService.darDeAltaUsuario(usuario);
        return "redirect:/gestorHome/gestorAlta";
    }
}
