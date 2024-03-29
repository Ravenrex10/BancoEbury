package com.ebury.controller;

import com.ebury.dto.CuentaDTO;
import com.ebury.dto.EmpresaDTO;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.exceptions.DivisaException;
import com.ebury.exceptions.NegativeImportException;
import com.ebury.service.CuentaService;
import com.ebury.service.DivisaService;
import com.ebury.service.TransferenciaService;
import com.ebury.service.UsuarioService;
import com.ebury.ui.CuentaDivisaWrapper;
import com.ebury.ui.EmpresaWrapper;
import com.ebury.ui.FiltroTransferencias;
import com.ebury.ui.UsuarioWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jaime
 */

@Controller
@RequestMapping("/cliente")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CuentaService cuentaService;

    @Autowired
    TransferenciaService transferenciaService;

    @Autowired
    DivisaService divisaService;

    @GetMapping("/")
    public String getHome(HttpSession session, Model model) {
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "cliente/clienteHome";
    }

    @GetMapping("/edit")
    public String getDatos(HttpSession session, Model model) {
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");

        model.addAttribute("usuario", usuarioDTO);
        return "cliente/clienteEdit";
    }

    @PostMapping("/edited")
    public String doEdit(HttpSession session, @ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
        return (this.usuarioService.makeEdit(usuarioDTO, session));
    }

    @GetMapping("/transferencia")
    public String getTransferencias(HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        model.addAttribute("newTransferencia", transferenciaDTO);

        List<CuentaDTO> cuentasUsuario = this.cuentaService.findAllCuentasByUsuario(usuarioActual.getId());
        model.addAttribute("cuentasUsuario", cuentasUsuario);

        List<CuentaDTO> cuentasDestino = this.cuentaService.findAllCuentasExceptThisUser(usuarioActual.getId());
        model.addAttribute("cuentasDestino", cuentasDestino);

        return "cliente/clienteTransferencia";
    }

    @PostMapping("/transferir")
    public String doTransferir(@ModelAttribute("newTransferencia") TransferenciaDTO transferenciaDTO, HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        CuentaDTO cuentaDTO = this.cuentaService.findCuentaByIdToDto(transferenciaDTO.getCuentaOrigen().getId());
        if (!usuarioActual.getAlta() || !cuentaDTO.getEstado().equals("Activada")) {
            return getError(model, "Tu cuenta está bloqueada/desactivada o tu usuario no ha sido dado de alta aún. Contacta con el gestor de tu empresa.", session);
        }
        try {
            this.transferenciaService.transferir(transferenciaDTO.getCuentaOrigen().getId(), transferenciaDTO.getCuentaDestino().getId(), transferenciaDTO.getCantidad());
            return "redirect:/cliente/";
        } catch (DivisaException divisaException) {
            return this.getError(model, "Las divisas entre la cuenta de origen y destino son diferentes. Intenta cambiar de divisa.", session);
        } catch (NegativeImportException exception)
        {
            return this.getError(model,"No se puede realizar importes menores o iguales a 0.",session);
        }
    }

    private String getError(Model model, String error, HttpSession session) {
        model.addAttribute("error", error);
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "cliente/clienteError";
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

        return ("cliente/clienteCambioDivisas");
    }

    @PostMapping("/cambiarDivisa")
    public String doCambiarDivisa(@ModelAttribute("newDivisa")CuentaDivisaWrapper newDivisa, HttpSession session, Model model)
    {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        CuentaDTO cuentaDTO = this.cuentaService.findCuentaByIdToDto(newDivisa.getCuentaId());

        if (!usuarioActual.getAlta() || !cuentaDTO.getEstado().equals("Activada")) {
            return getError(model, "Tu cuenta está bloqueada/desactivada o tu usuario no ha sido dado de alta aún. Contacta con el gestor de tu empresa.", session);
        }

        this.divisaService.cambiarCuentaDivisa(newDivisa);

        return("redirect:/cliente/");
    }

    @GetMapping("/listaTransferencias")
    public String getListaTransferencias(HttpSession session, Model model) {
        return (listarTransferencias(model, null, session));
    }

    /*

    // Filtro sin refactorizar

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
                        revlist(transferenciaDTOS);
                    }
                } else {
                    if (filtro.getOrdenPorFecha().equals("Descendente")) {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByDivisaOrderDesc(usuarioActual.getId(), filtro.getDivisa());
                    } else {
                        transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByDivisaOrderDesc(usuarioActual.getId(), filtro.getDivisa());
                        revlist(transferenciaDTOS);
                    }
                }

            } else if (!filtro.getCuenta().equals(0)) {
                if (filtro.getOrdenPorFecha().equals("Descendente")) {
                    //transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAEmpresaByCuentaIdOrderDesc(usuarioActual.getEmpresa(), filtro.getCuenta());
                    transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByCuentaIdOrderDesc(usuarioActual.getId(), filtro.getCuenta());
                } else {
                    transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByCuentaIdOrderDesc(usuarioActual.getId(), filtro.getCuenta());
                    revlist(transferenciaDTOS);
                }
            } else {
                transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUser(usuarioActual.getId());
                revlist(transferenciaDTOS);
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
        return ("cliente/clienteListarTransferencias");
    }

     */

    // Filtro refactorizado
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
                    transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByDivisaAndCuentaIdOrderDesc(usuarioActual.getId(), filtro.getDivisa(), filtro.getCuenta());
                } else {
                    transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByDivisaOrderDesc(usuarioActual.getId(), filtro.getDivisa());
                }

            } else if (!filtro.getCuenta().equals(0)) {
                transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUserByCuentaIdOrderDesc(usuarioActual.getId(), filtro.getCuenta());
            } else {
                transferenciaDTOS = this.transferenciaService.findAllTransferenciasFromAnUser(usuarioActual.getId());
                revlist(transferenciaDTOS);
            }

            if (filtro.getOrdenPorFecha().equals("Descendente")) {
                revlist(transferenciaDTOS);
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
        return ("cliente/clienteListarTransferencias");
    }

    public static <T> void revlist(List<T> list)
    {
        if (list.size() <= 1 || list == null)
            return;

        T value = list.remove(0);

        revlist(list);
        list.add(value);
    }

    @PostMapping("/filtrarTransferencias")
    public String doFiltrarTransferencias(@ModelAttribute("newFiltro") FiltroTransferencias filtro, Model model, HttpSession session) {
        return listarTransferencias(model, filtro, session);
    }

    @GetMapping("/solicitarActivacionDesbloqueo")
    public String doSolicitarActivacionDesbloqueo(HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);

        List<CuentaDTO> cuentasUsuario = this.cuentaService.findAllCuentasByUsuario(usuarioActual.getId());
        model.addAttribute("cuentasUsuario", cuentasUsuario);
        return "cliente/clienteSolicitarActivacionDesbloqueo";
    }

    @GetMapping("/solicitarActivacion")
    public String doSolicitarActivacion(@RequestParam ("id") Integer id) {
        usuarioService.solicitarActivacion(id);
        return ("redirect:/cliente/");
    }

    @GetMapping("/cajero")
    public String doIrCagero(HttpSession session, Model model) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        model.addAttribute("usuario", usuarioActual);
        return "cajero/cajeroHome";
    }

    @GetMapping("/logout")
    public String doLogOut() {
        return "redirect:/";
    }
}
