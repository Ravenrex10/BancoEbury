package com.ebury.controller;

import com.ebury.dto.ChatDTO;
import com.ebury.dto.UsuarioDTO;
import com.ebury.entity.UsuarioEntity;
import com.ebury.service.ChatService;
import com.ebury.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
        ChatDTO nuevoChat = chatService.crearChatEntre(usuarioA.getId(), userBId);
        return "redirect:/chat?chatId=" + nuevoChat.getId();
    }

    @GetMapping("/chat")
    String doMostrarChat(@RequestParam("chatId") int chatId, Model model) {
        ChatDTO chat = chatService.findChatByChatId(chatId);
        model.addAttribute("chat", chat);
        return "chat";
    }

}
