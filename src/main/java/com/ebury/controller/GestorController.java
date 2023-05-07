package com.ebury.controller;

import com.ebury.dto.EmpresaDTO;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.service.*;
import com.ebury.ui.FiltroTransferencias;
import com.ebury.ui.FiltroUsuarios;
import com.ebury.ui.OrdenaTransferencias;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas Colbert Eastgate
 */

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
    TransferenciaService transferenciaService;
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
        List<UsuarioDTO> usuarios = gestorService.filtrar(filtroUsuario);
        return listarUsuarios(usuarios, model);
    }

    @GetMapping("/informacionUsuario")
    public String getInformacion(@RequestParam("usuario") Integer id, Model model){
        List<TransferenciaDTO> transferencias = transferenciaService.findAllTransferencias(id);
        return verInformacionUsuario(transferencias, model, id);
    }

    @PostMapping("/filtrarTransferencias")
    public String doFiltrarTransferencias(@ModelAttribute("filtroTransferencias") FiltroTransferencias filtro, Model model){
        Integer usuario = filtro.getUsuario();
        List<TransferenciaDTO> transferencias = gestorService.filtrarTransferencias(filtro, usuario);
        return verInformacionUsuario(transferencias, model, usuario);
    }

    @GetMapping("/inactivos")
    public String getInactivos(Model model){
        model.addAttribute("usuariosInactivos", usuarioService.getUsuariosInactivos());
        return("gestor/gestorInactivos");
    }

    @GetMapping("/desactivarCuentas")
    public String doDesactivarCuentas(@RequestParam("usuario") Integer id){
        gestorService.desactivarCuentas(id);
        return("redirect:/gestorHome/inactivos");
    }

    @GetMapping("/desactivarCuenta")
    public String doDesactivarCuenta(@RequestParam("cuenta") Integer id){
        gestorService.desactivarCuenta(id);
        return("redirect:/gestorHome/cuentasSospechosas");
    }

    @GetMapping("/cuentasSospechosas")
    public String getCuentasSospechosas(Model model){
        model.addAttribute("cuentasSospechosas", gestorService.getCuentasSospechosas());
        return("gestor/gestorSospechosas");
    }

    @GetMapping("/bloqueadas")
    public String getCuentasBloqueadas(Model model){
        model.addAttribute("cuentasBloqueadas", gestorService.getCuentasBloqueadas());
        return("gestor/gestorBloqueados");
    }

    @GetMapping("/desbloquear")
    public String doDesbloquearCuenta(Model model, @RequestParam("cuenta") Integer idCuenta){
        gestorService.desbloquear(idCuenta);
        return("redirect:/gestorHome/bloqueadas");
    }

    @PostMapping("/ordenar")
    public String doOrdenar(Model model, @ModelAttribute("ordenTransferencias") OrdenaTransferencias ordenaTransferencias){
        Integer usuario = ordenaTransferencias.getUsuarioId();

        List<TransferenciaDTO> transferencias = transferenciaService.findAllTransferenciasOrdenadas(usuario, ordenaTransferencias.getOrden());

        return verInformacionUsuario(transferencias, model, usuario);
    }

    private String verInformacionUsuario(List<TransferenciaDTO> transferencias, Model model, Integer id){
        UsuarioDTO usuario = usuarioService.findUsuarioById(id);

        OrdenaTransferencias orden = new OrdenaTransferencias();
        model.addAttribute("ordenTransferencias", orden);
        orden.setOrden("Fecha");
        orden.setUsuarioId(id);

        FiltroTransferencias filtro = new FiltroTransferencias();
        filtro.setFiltro("0");
        filtro.setUsuario(id);
        List<String> filtroItems = new ArrayList<>();
        filtroItems.add("Recibidos");
        filtroItems.add("Enviados");
        if(usuario.getEmpresa()!=null){
            model.addAttribute("empresa", empresaService.findById(usuario.getEmpresa()));
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("cuentas", cuentaService.findAllCuentasByUsuario(id));
        model.addAttribute("saldos", saldoService.findAllSaldosByCuenta(id));
        model.addAttribute("filtroTransferencias", filtro);
        model.addAttribute("filtroItems", filtroItems);
        model.addAttribute("transferencias", transferencias);
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
