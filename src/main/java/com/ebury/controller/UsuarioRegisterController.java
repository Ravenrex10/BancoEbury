package com.ebury.controller;

import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.UsuarioDTO;
import com.ebury.service.EmpresaRegisterService;
import com.ebury.service.UsuarioRegisterService;
import com.ebury.ui.EmpresaWrapper;
import com.ebury.ui.UsuarioWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jaime
 */
@Controller
public class UsuarioRegisterController {

    @Autowired
    UsuarioRegisterService usuarioRegisterService;

    @GetMapping("/registerUsuario")
    public String doRegister(Model model){
        UsuarioWrapper usuarioWrapper = new UsuarioWrapper();
        model.addAttribute("newUsuarioWrapper", usuarioWrapper);
        return "usuarioRegister";
    }

    @PostMapping("/registerUsuario/register")
    public String makeRegister(@ModelAttribute("user") UsuarioWrapper usuarioWrapper)
    {
        return (this.usuarioRegisterService.makeRegister(usuarioWrapper));
    }
}
