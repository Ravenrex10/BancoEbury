package com.ebury.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/autorizadoHome")
public class AutorizadoController {

    @GetMapping("/")
    public String doHome(HttpSession session, Model model)
    {
        model.addAttribute("usuario",session.getAttribute("usuario"));
        return "empresa/empresaHome";
    }
}
