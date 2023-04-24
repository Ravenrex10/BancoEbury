package com.ebury.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresaHome")
public class EmpresaController {
    @GetMapping("/")
    public String doHome()
    {
        return "empresaHome";
    }
}
