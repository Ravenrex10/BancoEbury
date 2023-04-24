package com.ebury.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fundadorHome")
public class FundadorController {
    @GetMapping("/")
    public String doHome(HttpSession session)
    {
        return "fundadorHome";
    }

    @GetMapping("/fundadorAlta")
    public String doAlta()
    {
        return "fundadorAlta";
    }

    @GetMapping("/fundadorOperacion")
    public String doOperacion()
    {
        return "fundadorOperacion";
    }

    @GetMapping("/logout")
    public String doLogOut()
    {
        return "redirect:/";
    }
}
