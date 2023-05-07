package com.ebury.controller;
//Juan Salmeron

import com.ebury.dto.CuentaDTO;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.exceptions.DivisaException;
import com.ebury.exceptions.NegativeImportException;
import com.ebury.service.*;
import com.ebury.ui.CuentaDivisaWrapper;
import com.ebury.ui.FiltroTransferencias;
import com.ebury.ui.UsuarioWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cajero")
public class CajeroController {
    @Autowired
    protected UsuarioService usuarioService;

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
        return "cajero/cajeroHome";
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

        return "cajero/cajeroTransferencia";
    }

    @PostMapping("/transferir")
    public String doTransferir(@ModelAttribute("newTransferencia") TransferenciaDTO transferenciaDTO, HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        CuentaDTO cuentaDTO = this.cuentaService.findCuentaByIdToDto(transferenciaDTO.getCuentaOrigen().getId());
        if (!usuarioActual.getAlta() || !cuentaDTO.getEstado().equals("Activada")) {
            return getError(model, "Tu cuenta está bloqueada", session);
        }
        try {
            this.transferenciaService.transferir(transferenciaDTO.getCuentaOrigen().getId(), transferenciaDTO.getCuentaDestino().getId(), transferenciaDTO.getCantidad());
            return "redirect:/cajero/";
        } catch (DivisaException divisaException) {
            return this.getError(model, "Las divisas entre la cuenta de origen y destino son diferentes.", session);
        } catch (NegativeImportException exception) {
            return this.getError(model,"No se puede realizar importes menores o iguales a 0.",session);
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
        return ("cajero/cajeroListaTransferencias");
    }

    @PostMapping("/filtrarTransferencias")
    public String doFiltrarTransferencias(@ModelAttribute("newFiltro") FiltroTransferencias filtro, Model model, HttpSession session) {
        return listarTransferencias(model, filtro, session);
    }

    private String getError(Model model, String error, HttpSession session) {
        model.addAttribute("error", error);
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "cajero/cajeroError";
    }

    @GetMapping("/datos")
    public String getDatos(HttpSession session, Model model) {
        model.addAttribute("usuario", session.getAttribute("usuario"));
        UsuarioWrapper usuarioWrapper = new UsuarioWrapper();
        model.addAttribute("newUsuarioWrapper", usuarioWrapper);
        return "cajero/cajeroEdit";
    }

    @PostMapping("/edit")
    public String doEdit(HttpSession session, @ModelAttribute("newUsuarioWrapper") UsuarioWrapper usuarioWrapper) {
        return (this.usuarioService.makeEdit(usuarioWrapper, session));
    }

    @GetMapping("/cambioDivisa")
    public String getCambioDivisa(Model model, HttpSession session)
    {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        List<CuentaDTO> cuentaDTOS = this.cuentaService.findAllCuentasByUsuario(usuarioActual.getId());
        model.addAttribute("cuentaDTOS",cuentaDTOS);

        List<String> divisas = this.divisaService.findAllDivisaNames();
        divisas.add(0," ");
        model.addAttribute("divisas",divisas);

        CuentaDivisaWrapper cuentaDivisaWrapper = new CuentaDivisaWrapper();
        model.addAttribute("newDivisa",cuentaDivisaWrapper);

        return ("cajero/cajeroCambioDivisas");
    }

    @PostMapping("/cambiarDivisa")
    public String doCambiarDivisa(@ModelAttribute("newDivisa")CuentaDivisaWrapper newDivisa, HttpSession session, Model model)
    {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        CuentaDTO cuentaDTO = this.cuentaService.findCuentaByIdToDto(newDivisa.getCuentaId());

        if (!usuarioActual.getAlta() || !cuentaDTO.getEstado().equals("Activada")) {
            return getError(model, "Tu cuenta está bloqueada.", session);
        }

        this.divisaService.cambiarCuentaDivisa(newDivisa);

        return("redirect:/cajero/");
    }

    @GetMapping("/logout")
    public String doLogOut() {
        return "redirect:/";
    }
}
