package com.ebury.controller;
//Juan Salmeron

import com.ebury.dto.CuentaDTO;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.exceptions.DivisaException;
import com.ebury.exceptions.NegativeImportException;
import com.ebury.exceptions.NoCoinsException;
import com.ebury.service.*;
import com.ebury.ui.CuentaDivisaWrapper;
import com.ebury.ui.FiltroTransferencias;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping("/efectivo")
    public String getEfectivo(HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        model.addAttribute("newTransferencia", transferenciaDTO);

        List<CuentaDTO> cuentasUsuario = this.cuentaService.findAllCuentasByUsuario(usuarioActual.getId());
        model.addAttribute("cuentasUsuario", cuentasUsuario);

        return "cajero/cajeroEfectivo";
    }

    @PostMapping("/sacarEfectivo")
    public String doSacarEfectivo(@ModelAttribute("newTransferencia") TransferenciaDTO transferenciaDTO, HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        CuentaDTO cuentaDTO = this.cuentaService.findCuentaByIdToDto(transferenciaDTO.getCuentaOrigen().getId());
        if (!usuarioActual.getAlta() || !cuentaDTO.getEstado().equals("Activada")) {
            return getError(model, "Tu cuenta está bloqueada", session);
        }
        try {
            this.transferenciaService.sacarEfectivo(transferenciaDTO.getCuentaOrigen().getId(), transferenciaDTO.getCantidad());
            return "redirect:/cajero/";
        } catch (NegativeImportException exception) {
            return this.getError(model,"No se puede realizar importes menores o iguales a 0.",session);
        } catch (NoCoinsException exception) {
            return this.getError(model,"No disponemos de monedas y billetes pequeños. Por favor, pruebe con un múltiplo de 10.",session);
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
            List<TransferenciaDTO> transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUser(usuarioActual.getId());
            model.addAttribute("transferencias", transferenciaDTOS);
            FiltroTransferencias newFiltro = new FiltroTransferencias();
            newFiltro.setDivisa("0");
            newFiltro.setCuenta(0);
            newFiltro.setOrdenPorFecha("Ascendente");
            model.addAttribute("newFiltro", newFiltro);
        }else {
            List<TransferenciaDTO> transferenciaDTOS = new ArrayList<>();
            if (!filtro.getDivisa().equals("0")) {
                if (!filtro.getCuenta().equals(0)) {
                    if (filtro.getOrdenPorFecha().equals("Descendente")) {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByDivisaAndCuentaIdOrderDesc(usuarioActual.getId(), filtro.getDivisa(), filtro.getCuenta());
                    } else {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByDivisaAndCuentaIdOrderDesc(usuarioActual.getId(), filtro.getDivisa(), filtro.getCuenta());
                        Collections.reverse(transferenciaDTOS);
                    }
                } else {
                    if (filtro.getOrdenPorFecha().equals("Descendente")) {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByDivisaOrderDesc(usuarioActual.getId(), filtro.getDivisa());
                    } else {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByDivisaOrderDesc(usuarioActual.getId(), filtro.getDivisa());
                        Collections.reverse(transferenciaDTOS);
                    }
                }

            } else if (!filtro.getCuenta().equals(0)) {
                if (filtro.getOrdenPorFecha().equals("Descendente")) {
                    transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByCuentaIdOrderDesc(usuarioActual.getId(), filtro.getCuenta());
                } else {
                    transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByCuentaIdOrderDesc(usuarioActual.getId(), filtro.getCuenta());
                    Collections.reverse(transferenciaDTOS);
                }
            } else {
                transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUser(usuarioActual.getId());
                Collections.reverse(transferenciaDTOS);
            }
            model.addAttribute("transferencias", transferenciaDTOS);
        }

        List<String> orden = new ArrayList<>();
        orden.add("Ascendente");
        orden.add("Descendente");
        model.addAttribute("orden", orden);

        List<String> divisas = this.divisaService.findAllDivisaNames();
        model.addAttribute("divisas", divisas);

        List<CuentaDTO> cuentaDTOS = this.cuentaService.findAllCuentasByUsuario(usuarioActual.getId());
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
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioDTO);
        return "cajero/cajeroEdit";
    }

    @PostMapping("/edit")
    public String doEdit(HttpSession session, @ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
        return (this.usuarioService.makeEdit(usuarioDTO, session));
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

        return("redirect:/cajero/efectivo");
    }

    @GetMapping("/desbloquearCuentas")
    public String getSolicitudDesbloqueo(HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        List<CuentaDTO> cuentasUsuario = this.cuentaService.findAllCuentasByUsuario(usuarioActual.getId());
        model.addAttribute("cuentasUsuario", cuentasUsuario);

        return "cajero/cajeroDesbloqueo";
    }

    @GetMapping("/solicitarDesbloqueo")
    public String doSolicitudDesbloqueo(@RequestParam("cuenta") Integer id) {
        this.cuentaService.solicitarDesbloqueoCuenta(id);
        return "redirect:/cajero/cajeroHome";
    }

    @GetMapping("/logout")
    public String doLogOut() {
        return "redirect:/";
    }
}
