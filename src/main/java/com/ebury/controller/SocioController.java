package com.ebury.controller;

import com.ebury.dto.EmpresaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.service.EmpresaService;
import com.ebury.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/socioHome")
public class SocioController {

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected EmpresaService empresaService;

    @GetMapping("/")
    public String doHome(HttpSession session, Model model)
    {
        model.addAttribute("usuario",session.getAttribute("usuario"));
        return "empresa/empresaHome";
    }

    @GetMapping("/listaUsuarios")
    public String doOperacion(Model model, HttpSession session)
    {
        // TODO: FILTRADO
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario",usuarioDTO);

        int empresaId = usuarioDTO.getEmpresa();

        List<UsuarioDTO> usuarioDTOList = this.usuarioService.findUsuariosDTOByEmpresaId(empresaId);
        model.addAttribute("listaUsuarios",usuarioDTOList);

        return "empresa/empresaLista";
    }

    @GetMapping("/logout")
    public String doLogOut()
    {
        return "redirect:/";
    }
}
