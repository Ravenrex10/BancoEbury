package com.ebury.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpresaController {

    @GetMapping("/empresaHome")
    public String doEmpresa(){
        return "empresaHome";
    }
}