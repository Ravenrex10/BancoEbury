package com.ebury.controller;

import com.ebury.dto.UsuarioDTO;
import com.ebury.service.EmpresaService;
import com.ebury.service.UsuarioService;
import com.ebury.ui.FiltroUsuarios;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Este controller es para tres roles en común: Fundador, Socio y Autorizado
 * Se ha decidio unir en el mismo controller a estos tres roles por tema de refactorización: repiten demasiado código y
 * solo se diferencian en el número de funciones que pueden realizar.
 */


@Controller
@RequestMapping("/empresa")
public class EmpresaCommonController {
    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected EmpresaService empresaService;

    @GetMapping("/")
    public String getHome(HttpSession session, Model model) {
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "empresa/empresaHome";
    }

    @GetMapping("/fundadorAlta")
    public String getAlta(Model model, HttpSession session) {
        model.addAttribute("usuario", session.getAttribute("usuario"));

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("newUsuarioDTO", usuarioDTO);

        return "empresa/empresaAlta";
    }

    @PostMapping("/solicitarAlta")
    public String solicitarAlta(@ModelAttribute("newUsuarioDTO") UsuarioDTO usuarioDTO, HttpSession session) {
        // TODO: Controlar que el usuario sea fundador
        UsuarioDTO fundador = (UsuarioDTO) session.getAttribute("usuario");
        int empresaId = fundador.getEmpresa();
        return this.usuarioService.makeRegister(usuarioDTO, empresaId);
    }

    @GetMapping("/listaUsuarios")
    public String getLista(Model model, HttpSession session) {
        // TODO: Controlar que el usuario no sea autorizado

        return (listarUsuarios(model, null, session));
    }

    @PostMapping("/filtrarLista")
    public String doFiltrar(@ModelAttribute("filtro") FiltroUsuarios filtro, Model model, HttpSession session) {
        return (listarUsuarios(model, filtro, session));
    }

    private String listarUsuarios(Model model, FiltroUsuarios filtro, HttpSession session) {
        List<UsuarioDTO> usuarioDTOList;
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioDTO);
        int empresaId = usuarioDTO.getEmpresa();

        List<String> roles = inicializarRoles();
        model.addAttribute("rolesList", roles);

        if (filtro == null || filtro.getFiltro().equals("0")) {
            filtro = new FiltroUsuarios();
            filtro.setFiltro("0");
            usuarioDTOList = this.usuarioService.findUsuariosDTOByEmpresaId(empresaId);

        } else {
            switch (filtro.getFiltro()) {
                case "SocioEmpresa":
                    usuarioDTOList = this.usuarioService.findUsuariosByRolNombre("SocioEmpresa");
                    break;
                case "FundadorEmpresa":
                    usuarioDTOList = this.usuarioService.findUsuariosByRolNombre("FundadorEmpresa");
                    break;
                case "AutorizadoEmpresa":
                    usuarioDTOList = this.usuarioService.findUsuariosByRolNombre("AutorizadoEmpresa");
                    break;
                default:
                    usuarioDTOList = this.usuarioService.findUsuariosDTOByEmpresaId(empresaId);
            }
        }
        model.addAttribute("listaUsuarios", usuarioDTOList);
        model.addAttribute("newFiltro", filtro);
        return ("empresa/empresaLista");
    }

    private List<String> inicializarRoles() {
        List<String> res = new ArrayList<>();
        res.add("FundadorEmpresa");
        res.add("SocioEmpresa");
        res.add("AutorizadoEmpresa");

        return res;
    }

    @GetMapping("/logout")
    public String doLogOut() {
        return "redirect:/";
    }

}
