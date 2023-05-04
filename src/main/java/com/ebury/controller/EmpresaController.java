package com.ebury.controller;

import com.ebury.dto.*;
import com.ebury.exceptions.DivisaException;
import com.ebury.service.*;
import com.ebury.ui.EmpresaWrapper;
import com.ebury.ui.FiltroTransferencias;
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

    @Autowired
    protected TransferenciaService transferenciaService;

    @Autowired
    protected DivisaService divisaService;

    @Autowired
    protected SaldoService saldoService;

    @GetMapping("/")
    public String getHome(HttpSession session, Model model) {
        model.addAttribute("usuario", session.getAttribute("usuario"));
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("empresa",this.empresaService.findById(usuarioActual.getEmpresa()));
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

    @PostMapping("/edit")
    public String doEdit(HttpSession session, @ModelAttribute("newEmpresaWrapper") EmpresaWrapper empresaWrapper) {
        //TODO: CONTROL DE ERRORES Y VALORES NULOS
        return (this.empresaService.makeEdit(empresaWrapper, session));
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
        //TODO: CONTROL DE ERRORES Y VALORES NULOS
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

        List<SaldoDTO> saldos = new ArrayList<>();
        for (CuentaDTO c : cuentasUsuario) {
            saldos.addAll(this.saldoService.findAllSaldosByCuenta(c.getId()));
        }
        model.addAttribute("saldos", saldos);

        return "empresa/empresaDesbloqueo";
    }

    @GetMapping("/solicitarDesbloqueo")
    public String doSolicitudDesbloqueo(@RequestParam("cuenta") Integer id) {
        this.cuentaService.solicitarDesbloqueoCuenta(id);
        return "redirect:/empresa/cuentas";
    }

    @GetMapping("/transferencias")
    public String getTransferencias(HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        model.addAttribute("newTransferencia", transferenciaDTO);

        List<CuentaDTO> cuentasUsuario = this.cuentaService.findAllCuentasByUsuario(usuarioActual.getId());
        model.addAttribute("cuentasUsuario", cuentasUsuario);

        List<CuentaDTO> cuentasDestino = this.cuentaService.findAllCuentasExceptThisUser(usuarioActual.getId());
        model.addAttribute("cuentasDestino", cuentasDestino);

        return "empresa/empresaTransferencia";
    }

    @PostMapping("/transferir")
    public String doTransferir(@ModelAttribute("newTransferencia") TransferenciaDTO transferenciaDTO, HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        CuentaDTO cuentaDTO = this.cuentaService.findCuentaByIdToDto(transferenciaDTO.getCuentaOrigen().getId());
        if (!usuarioActual.getAlta() || !cuentaDTO.getEstado().equals("Activada")) {
            return getError(model, "Tu cuenta no está activada o tu usuario no ha sido dado de alta aún. Contacta con el gestor de tu empresa.", session);
        }
        try {
            return this.transferenciaService.transferir(transferenciaDTO.getCuentaOrigen().getId(), transferenciaDTO.getCuentaDestino().getId(), transferenciaDTO.getCantidad());
        } catch (DivisaException divisaException) {
            return this.getError(model, "Las divisas entre la cuenta de origen y destino son diferentes. Intenta cambiar de divisa.", session);
        }
    }

    @GetMapping("/listaTransferencias")
    public String getListaTransferencias(HttpSession session, Model model) {
        return (listarTransferencias(model, null, session));
    }

    private String listarTransferencias(Model model, FiltroTransferencias filtro, HttpSession session) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        if (filtro == null || filtro.getDivisa().equals("0") && filtro.getCuenta().equals(0) && filtro.getOrdenPorFecha().equals("Ascendente")) {
            FiltroTransferencias newFiltro = new FiltroTransferencias();
            List<TransferenciaDTO> transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresa(usuarioActual.getEmpresa());
            newFiltro.setDivisa("0");
            newFiltro.setCuenta(0);
            newFiltro.setOrdenPorFecha("Ascendente");
            model.addAttribute("transferencias", transferenciaDTOS);
            model.addAttribute("newFiltro", newFiltro);
        } else {
            List<TransferenciaDTO> transferenciaDTOS = new ArrayList<>();
            if (!filtro.getDivisa().equals("0")) {
                if (!filtro.getCuenta().equals(0)) {
                    if (filtro.getOrdenPorFecha().equals("Descendente")) {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresaByDivisaAndCuentaIdOrderDesc(usuarioActual.getEmpresa(), filtro.getDivisa(), filtro.getCuenta());
                    } else {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresaByDivisaAndCuentaIdOrderAsc(usuarioActual.getEmpresa(), filtro.getDivisa(), filtro.getCuenta());
                    }
                } else {
                    if (filtro.getOrdenPorFecha().equals("Descendente")) {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresaByDivisaOrderDesc(usuarioActual.getEmpresa(), filtro.getDivisa());
                    } else {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresaByDivisaOrderAsc(usuarioActual.getEmpresa(), filtro.getDivisa());
                    }
                }

            } else if (!filtro.getCuenta().equals(0)) {
                if (filtro.getOrdenPorFecha().equals("Descendente")) {
                    transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresaByCuentaIdOrderDesc(usuarioActual.getEmpresa(), filtro.getCuenta());
                } else {
                    transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresaByCuentaIdOrderAsc(usuarioActual.getEmpresa(), filtro.getCuenta());
                }
            } else {
                transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresaOrderDesc(usuarioActual.getEmpresa());
            }
            model.addAttribute("transferencias", transferenciaDTOS);
            model.addAttribute("newFiltro", filtro);
        }
        List<String> orden = new ArrayList<>();
        orden.add("Ascendente");
        orden.add("Descendente");
        model.addAttribute("orden", orden);

        List<String> divisas = this.divisaService.findAllDivisaNames();
        model.addAttribute("divisas", divisas);

        List<CuentaDTO> cuentaDTOS = this.cuentaService.findAllCuentasByEmpresa(usuarioActual.getEmpresa());
        model.addAttribute("cuentasDTOS", cuentaDTOS);
        return ("empresa/empresaListaTransferencias");
    }

    @PostMapping("/filtrarTransferencias")
    public String doFiltrarTransferencias(@ModelAttribute("newFiltro") FiltroTransferencias filtro, Model model, HttpSession session) {
        return listarTransferencias(model, filtro, session);
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
