package com.ebury.controller;

import com.ebury.dto.ChatDTO;
import com.ebury.dto.MensajeDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.UsuarioEntity;
import com.ebury.service.ChatService;
import com.ebury.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ChatService chatService;

    @GetMapping("/asistente")
    String doRedirigirAChats() {
        return "redirect:/chats";
    }

    @GetMapping("/chats")
    String doListarChats(HttpSession session, Model model) {
        // TODO: hacer esto sin UsuarioEntity
        UsuarioEntity miUsuario = (UsuarioEntity) session.getAttribute("usuario");
        if (miUsuario == null) return "redirect:/";
        List<UsuarioDTO> usuarios = usuarioService.findUsuarios();
        //List<ChatDTO> chats = chatService.findChatsByUserId(miUsuario.getId());
        List<ChatDTO> chats = chatService.findAllChats();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("chats", chats);
        return "chats";
    }

    @GetMapping("/nuevoChat")
    String doNuevoChat(@RequestParam("chatUserId") int userBId, HttpSession session) {
        // TODO: hacer esto sin UsuarioEntity
        UsuarioEntity usuarioA = (UsuarioEntity) session.getAttribute("usuario");
        if (usuarioA == null) return "redirect:/";
        ChatDTO nuevoChat = chatService.crearChatEntre(usuarioA.getId(), userBId);
        return "redirect:/chat?chatId=" + nuevoChat.getId();
    }

    @GetMapping("/chat")
    String doMostrarChat(@RequestParam("chatId") int chatId, Model model, HttpSession session) {
        UsuarioEntity miUsuario = (UsuarioEntity) session.getAttribute("usuario");
        ChatDTO chat = chatService.findChatByChatId(chatId);
        List<MensajeDTO> mensajes = chatService.findMensajesByChatId(chatId, miUsuario.getId());
        model.addAttribute("chat", chat);
        model.addAttribute("mensajes", mensajes);
        return "chat";
    }

    @PostMapping("/enviarMensaje")
    String doEnviarMensaje(@RequestParam("mensaje") String mensaje, @RequestParam("chatId") int chatId, HttpSession session) {
        UsuarioEntity miUsuario = (UsuarioEntity) session.getAttribute("usuario");
        chatService.enviarMensaje(chatId, miUsuario.getId(), mensaje);
        return "redirect:/chat?chatId=" + chatId;
    }

}
