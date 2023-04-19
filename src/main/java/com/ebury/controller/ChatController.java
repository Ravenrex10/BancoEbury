package com.ebury.controller;

import com.ebury.dto.UsuarioDTO;
import com.ebury.service.UsuarioService;
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

    @GetMapping("/asistente")
    String doRedirigirAChats() {
        return "redirect:/chats";
    }

    @GetMapping("/chats")
    String doListarChats(Model model) {
        List<UsuarioDTO> usuarios = usuarioService.findUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "chats";
    }

    @GetMapping("/chats/nuevoChat")
    String doNuevoChat(@RequestParam("chatUserId") int userID, Model model) {
        UsuarioDTO usuario = usuarioService.findUsuarioById(userID);
        model.addAttribute("usuario", usuario);
        return "chat";
    }

}
