package com.ebury.controller;

import com.ebury.dao.UsuarioRepository;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class  testController {
    @Autowired
    UsuarioRepository userRepo;

    @GetMapping("/")
    String getTest(Model model){
        List<UsuarioEntity> usuarios = userRepo.findAll();
        model.addAttribute("usuarios", usuarios);
        return "entrada";
    }
}
