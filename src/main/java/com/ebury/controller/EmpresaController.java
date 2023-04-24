package com.ebury.controller;

import com.ebury.entity.EmpresaEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresaHome")
public class EmpresaController {
    @GetMapping("/")
    public String doHome(Model model, HttpSession session)
    {
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        model.addAttribute("empresa",empresa);
        return "empresaHome";
    }

    @GetMapping("/empresaAlta")
    public String doEmpresaAlta()
    {
        return "empresaAlta";
    }
}
