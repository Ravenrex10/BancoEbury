package com.ebury.controller;


import com.ebury.service.EmpresaRegisterService;
import com.ebury.ui.EmpresaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Esta controller se encarga del registro de una empresa y un fundador
 *
 * @author Diego
 */
@Controller
public class EmpresaRegisterController {

    @Autowired
    EmpresaRegisterService empresaRegisterService;

    @GetMapping("/registerEmpresa")
    public String doRegister(Model model) {
        EmpresaWrapper empresaWrapper = new EmpresaWrapper();
        model.addAttribute("newEmpresaWrapper", empresaWrapper);
        return "empresaRegister";
    }

    @PostMapping("/registerEmpresa/register")
    public String makeRegister(Model model, @ModelAttribute("newEmpresaWrapper") EmpresaWrapper empresaWrapper) {
        return (this.empresaRegisterService.makeRegister(empresaWrapper));
    }


}
