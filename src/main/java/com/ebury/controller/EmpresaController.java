package com.ebury.controller;

import com.ebury.dto.CuentaDTO;
import com.ebury.dto.EmpresaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.service.CuentaService;
import com.ebury.service.DireccionService;
import com.ebury.service.EmpresaService;
import com.ebury.service.UsuarioService;
import com.ebury.ui.EmpresaWrapper;
import com.ebury.ui.FiltroUsuarios;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Este controller es para tres roles en común: Fundador, Socio y Autorizado
 * Se ha decidio unir en el mismo controller a estos tres roles por tema de refactorización: repiten demasiado código y
 * solo se diferencian en el número de funciones que pueden realizar.
 *
 * @author Diego
 */


@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected EmpresaService empresaService;

    @Autowired
    protected DireccionService direccionService;

    @Autowired
    protected CuentaService cuentaService;

    @GetMapping("/")
    public String getHome(HttpSession session, Model model) {
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "empresa/empresaHome";
    }

    @GetMapping("/datos")
    public String getDatos(HttpSession session, Model model) {
        model.addAttribute("usuario", session.getAttribute("usuario"));

        EmpresaWrapper empresaWrapper = new EmpresaWrapper();
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");
        empresaWrapper.setNewUsuario(user);

        int empresaId = user.getEmpresa();
        EmpresaDTO empresaDTO = this.empresaService.findById(empresaId);
        empresaWrapper.setNewEmpresa(empresaDTO);

        empresaWrapper.setNewDireccion(this.direccionService.findDireccionByEmpresaId(empresaId));

        model.addAttribute("newEmpresaWrapper", empresaWrapper);

        return ("empresa/empresaDatos");
    }

    @PostMapping("/register")
    public String doRegister(HttpSession session, @ModelAttribute("newEmpresaWrapper") EmpresaWrapper empresaWrapper) {
        return (this.empresaService.makeRegister(empresaWrapper, session));
    }

    @GetMapping("/fundadorAlta")
    public String getAlta(Model model, HttpSession session) {
        UsuarioDTO fundador = (UsuarioDTO) session.getAttribute("usuario");
        if (!fundador.getRolName().equals("FundadorEmpresa")) {
            return getError(model, "Acceso denegado", session);
        }
        model.addAttribute("usuario", fundador);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("newUsuarioDTO", usuarioDTO);

        return "empresa/empresaAlta";
    }

    @PostMapping("/solicitarAlta")
    public String getSolicitarAlta(@ModelAttribute("newUsuarioDTO") UsuarioDTO usuarioDTO, HttpSession session, Model model) {
        UsuarioDTO fundador = (UsuarioDTO) session.getAttribute("usuario");
        if (!fundador.getRolName().equals("FundadorEmpresa")) {
            return getError(model, "Acceso denegado", session);
        }
        int empresaId = fundador.getEmpresa();
        return this.usuarioService.makeRegister(usuarioDTO, empresaId);
    }

    @GetMapping("/listaUsuarios")
    public String getLista(Model model, HttpSession session) {
        UsuarioDTO fundador = (UsuarioDTO) session.getAttribute("usuario");
        if (!fundador.getRolName().equals("FundadorEmpresa") && !fundador.getRolName().equals("SocioEmpresa")) {
            return getError(model, "Acceso denegado", session);
        }
        model.addAttribute("usuario", session.getAttribute("usuario"));
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
            usuarioDTOList = this.usuarioService.findFundadorSociosAndAutorizadosByEmpresaId(empresaId);

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
                    usuarioDTOList = this.usuarioService.findFundadorSociosAndAutorizadosByEmpresaId(empresaId);
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

    @GetMapping("/bloquearUsuarios")
    public String getBloquearUsuarios(HttpSession session, Model model) {
        UsuarioDTO fundador = (UsuarioDTO) session.getAttribute("usuario");
        if (!fundador.getRolName().equals("FundadorEmpresa") && !fundador.getRolName().equals("SocioEmpresa")) {
            return getError(model, "Acceso denegado", session);
        }
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        List<UsuarioDTO> usuarioDTOList = this.usuarioService.findSociosAndAutorizadosByEmpresaId(usuarioActual.getEmpresa());
        model.addAttribute("listaUsuarios", usuarioDTOList);

        UsuarioDTO usuarioABloquear = new UsuarioDTO();
        model.addAttribute("blockUser", usuarioABloquear);

        return "empresa/empresaBloqueo";
    }

    @PostMapping("/bloquear")
    public String doBloquear(@ModelAttribute("blockUser") UsuarioDTO usuarioDTO) {
        this.usuarioService.bloquearUsuarioById(usuarioDTO.getId());
        return "redirect:/empresa/";
    }

    @GetMapping("/cuentas")
    public String getSolicitudDesbloqueo(HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        List<CuentaDTO> cuentasUsuario = this.cuentaService.findAllCuentasByUsuario(usuarioActual.getId());
        model.addAttribute("cuentasUsuario", cuentasUsuario);
        return "empresa/empresaDesbloqueo";
    }

    @GetMapping("/solicitarDesbloqueo")
    public String doSolicitudDesbloqueo(@RequestParam("cuenta") Integer id) {
        this.cuentaService.solicitarDesbloqueoCuenta(id);
        return "redirect:/empresa/cuentas";
    }

    private String getError(Model model, String error, HttpSession session) {
        model.addAttribute("error", error);
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "empresa/empresaError";
    }

    @GetMapping("/logout")
    public String doLogOut() {
        return "redirect:/";
    }

}
