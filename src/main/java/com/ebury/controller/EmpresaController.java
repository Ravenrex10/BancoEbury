package com.ebury.controller;

import com.ebury.dao.UsuarioRepository;
import com.ebury.entity.EmpresaEntity;
import com.ebury.entity.UsuarioEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/empresaHome")
public class EmpresaController {

    @Autowired
    public UsuarioRepository userRepo;
    @GetMapping("/")
    public String doHome(Model model, HttpSession session)
    {
        EmpresaEntity empresa = (EmpresaEntity) session.getAttribute("empresa");
        model.addAttribute("empresa",empresa);
        return "empresaHome";
    }

    @GetMapping("/empresaAlta")
    public String doEmpresaAlta(Model model, HttpSession session)
    {
        return "empresaAlta";
    }

    @GetMapping("/logout")
    public String doLogOut()
    {
        return "redirect:/";
    }

    @GetMapping("/empresaOperacion")
    public String doEmpresaOperacion()
    {
        return "empresaOperacion";
    }
}
