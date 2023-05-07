package com.ebury.controller;

import com.ebury.dto.ChatDTO;
import com.ebury.dto.MensajeDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.UsuarioEntity;
import com.ebury.service.ChatService;
import com.ebury.service.UsuarioService;
import com.ebury.ui.FiltroChats;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 */
@Controller
public class ChatController {

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected ChatService chatService;

    @GetMapping("/asistente")
    String doRedirigirAChats() {
        return "redirect:/chats";
    }

    @GetMapping("/chats")
    String doListarChats(HttpSession session, Model model) {
        UsuarioDTO miUsuario = (UsuarioDTO) session.getAttribute("usuario");
        if (miUsuario == null || !miUsuario.getRolName().equals("Asistente")) return "redirect:/";

        FiltroChats filtro = new FiltroChats();
        filtro.setCriterioOrdenacion("ascendente");
        filtro.setMostrarCerrados(true);
        filtro.setMostrarSoloPropios(false);

        listarChatsFiltrados(filtro, model, miUsuario.getId());
        return "asistente/chats";
    }

    @PostMapping("/chats/filtrar")
    String doFiltrarChats(@ModelAttribute("filtro") FiltroChats filtro, HttpSession session, Model model) {
        UsuarioDTO miUsuario = (UsuarioDTO) session.getAttribute("usuario");
        listarChatsFiltrados(filtro, model, miUsuario.getId());
        return "asistente/chats";
    }

    private void listarChatsFiltrados(FiltroChats filtro, Model model, int usuarioId) {
        List<UsuarioDTO> usuarios = usuarioService.findAllUsuariosQueParticipanEnChat();
        List<ChatDTO> chats = chatService.filtrarChats(filtro, usuarioId);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("chats", chats);
        model.addAttribute("filtro", filtro);
    }

    @PostMapping("/nuevoChat")
    String doNuevoChat(@RequestParam("chatUserId") int userBId, HttpSession session) {
        UsuarioDTO usuarioA = (UsuarioDTO) session.getAttribute("usuario");
        if (usuarioA == null) return "redirect:/";
        ChatDTO nuevoChat = chatService.crearChatEntre(usuarioA.getId(), userBId);
        return "redirect:/chat?chatId=" + nuevoChat.getId();
    }

    @GetMapping("/chat")
    String doMostrarChat(@RequestParam("chatId") int chatId, Model model, HttpSession session) {
        UsuarioDTO miUsuario = (UsuarioDTO) session.getAttribute("usuario");
        if (!chatService.usuarioTieneAccesoAChat(miUsuario.getId(), chatId)) {
            return "asistente/accesoDenegado";
        }
        ChatDTO chat = chatService.findChatByChatId(chatId);
        List<MensajeDTO> mensajes = chatService.findMensajesByChatId(chatId, miUsuario.getId());
        boolean usuarioPuedeEscribir = chatService.usuarioPuedeEnviarMensajeAChat(miUsuario.getId(), chatId);
        boolean usuarioPuedeCerrar = !miUsuario.getRolName().equals("Asistente") && !chat.isCerrado();
        model.addAttribute("chat", chat);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("usuarioPuedeEscribir", usuarioPuedeEscribir);
        model.addAttribute("usuarioPuedeCerrar", usuarioPuedeCerrar);
        return "asistente/chat";
    }

    @PostMapping("/chat/enviar")
    String doEnviarMensaje(@RequestParam("mensaje") String mensaje, @RequestParam("chatId") int chatId, HttpSession session) {
        UsuarioDTO miUsuario = (UsuarioDTO) session.getAttribute("usuario");
        chatService.enviarMensaje(chatId, miUsuario.getId(), mensaje);
        return "redirect:/chat?chatId=" + chatId;
    }

    // si soy cliente, quiero ver un listado de todos mis chats con asistentes
    @GetMapping("/asistencia")
    String doMostrarAsistencia(HttpSession session, Model model) {
        UsuarioDTO miUsuario = (UsuarioDTO) session.getAttribute("usuario");
        if (miUsuario == null) return "redirect:/";
        List<ChatDTO> misChats = chatService.findChatsByUserId(miUsuario.getId());
        List<UsuarioDTO> asistentes = usuarioService.findUsuariosByRolNombre("Asistente");
        model.addAttribute("chats", misChats);
        model.addAttribute("usuarios", asistentes);
        return "asistente/asistencia";
    }

    @GetMapping("/volverAChats")
    String doVolverAChats(@SessionAttribute("usuario") UsuarioDTO miUsuario) {
        if (miUsuario.getRolName().equals("Asistente")) {
            return "redirect:/chats";
        } else {
            return "redirect:/asistencia";
        }
    }

    @PostMapping("/chat/cerrar")
    String doCerrarChat(@RequestParam("chatId") int chatId) {
        chatService.cerrarChat(chatId);
        return "redirect:/asistencia";
    }

    @PostMapping("/asistencia/asignarAsistente")
    String doAsignarAsistente(HttpSession session) {
        UsuarioDTO usuarioActual = (UsuarioDTO) session.getAttribute("usuario");
        ChatDTO nuevoChat = chatService.asignarChatACliente(usuarioActual.getId());
        return "redirect:/chat?chatId=" + nuevoChat.getId();
    }


}
