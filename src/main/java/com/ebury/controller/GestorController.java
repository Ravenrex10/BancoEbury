package com.ebury.controller;

import com.ebury.dto.EmpresaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.service.*;
import com.ebury.ui.FiltroTransferencias;
import com.ebury.ui.FiltroUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/gestorHome")
@Controller
public class GestorController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    EmpresaService empresaService;
    @Autowired
    GestorService gestorService;
    @Autowired
    CuentaService cuentaService;

    @Autowired
    SaldoService saldoService;

    @GetMapping("/")
    public String getGestor(Model model){
        return listarUsuarios(usuarioService.findAllClientes(), model);
    }

    @GetMapping("/gestorAlta")
    public String getGestorAlta(Model model){
        model.addAttribute("usuariosSinAlta", usuarioService.findAllByAltaSolicitada());
        return "gestor/gestorAlta";
    }

    @GetMapping("/logout")
    public String doLogOut()
    {
        return "redirect:/";
    }

    @GetMapping("/darDeAlta")
    public String darDeAlta(@RequestParam("usuario") Integer usuario){
        usuarioService.darDeAltaUsuario(usuario);
        return "redirect:/gestorHome/gestorAlta";
    }

    @GetMapping("/denegarAlta")
    public String denegarAlta(@RequestParam("usuario") Integer usuario){
        usuarioService.denegarAltaUsuario(usuario);
        return "redirect:/gestorHome/gestorAlta";
    }

    @PostMapping("/filtrarUsuarios")
    public String doFiltrar(@ModelAttribute("filtroUsuarios") FiltroUsuarios filtroUsuario, Model model){
        List<UsuarioDTO> usuarios;
        usuarios = gestorService.filtrar(filtroUsuario);

        return listarUsuarios(usuarios, model);
    }

    @GetMapping("/informacionUsuario")
    public String getInformacion(@RequestParam("usuario") Integer id, Model model){
        UsuarioDTO usuario = usuarioService.findUsuarioById(id);
        FiltroTransferencias filtro = new FiltroTransferencias();
        filtro.setFiltro("0");
        List<String> filtroItems = new ArrayList<>();
        filtroItems.add("Recibidos");
        filtroItems.add("Enviados");
        if(usuario.getEmpresa()!=null){
            model.addAttribute("empresa", empresaService.findById(usuario.getEmpresa()));
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("transferencias", usuarioService.findAllTransferencias(id));
        model.addAttribute("cuentas", cuentaService.findAllCuentasByUsuario(id));
        model.addAttribute("saldos", saldoService.findAllSaldosByCuenta(id));
        model.addAttribute("filtroTransferencias", filtro);
        model.addAttribute("filtroItems", filtroItems);
        return ("gestor/informacionUsuario");
    }

    private String listarUsuarios(List<UsuarioDTO> usuarios, Model model){
        model.addAttribute("usuarios", usuarios);
        FiltroUsuarios filtro = new FiltroUsuarios();
        filtro.setFiltro("0");
        model.addAttribute("filtroUsuarios", filtro);
        List<String> filtroItems = new ArrayList<>();
        filtroItems.add("Cliente Particular");
        filtroItems.add("Fundador de empresa");
        filtroItems.add("Autorizado de empresa");
        filtroItems.add("Socio de empresa");

        model.addAttribute("roles", filtroItems);
        return("gestor/gestorHome");
    }
}
