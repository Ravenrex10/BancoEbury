package com.ebury.service;

import com.ebury.dao.ChatRepository;
import com.ebury.dao.MensajeRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.ChatDTO;
import com.ebury.entity.ChatEntity;
import com.ebury.entity.MensajeEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MensajeRepository mensajeRepository;

    /**
     * Devuelve los chats donde uno de los participantes tiene el id userId
     */
    public List<ChatDTO> findChatsByUserId(int userId) {
        List<ChatEntity> chats = chatRepository.findChatEntitiesByUserId(userId);
        return chats.stream().map(ChatEntity::toDTO).collect(Collectors.toList());
    }

    public List<ChatDTO> findAllChats() {
        List<ChatEntity> chats = chatRepository.findAll();
        return chats.stream().map(ChatEntity::toDTO).collect(Collectors.toList());
    }

    public ChatDTO findChatByChatId(int chatId) {
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) {
            return null;
        } else {
            return chat.toDTO();
        }
    }

    public ChatDTO crearChatEntre(int userAId, int userBId) {
        UsuarioEntity usuarioA = usuarioRepository.findById(userAId).orElse(null);
        UsuarioEntity usuarioB = usuarioRepository.findById(userBId).orElse(null);
        ChatEntity nuevoChat = new ChatEntity();
        nuevoChat.setUsuarioByClienteA(usuarioA);
        nuevoChat.setUsuarioByClienteB(usuarioB);
        chatRepository.save(nuevoChat);
        return nuevoChat.toDTO();
    }

    public void enviarMensaje(int chatId, int emisorId, String contenido) {
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        MensajeEntity nuevoMensaje = new MensajeEntity();
        nuevoMensaje.setChatByChat(chat);
        nuevoMensaje.setContenido(contenido);
        nuevoMensaje.setFecha(new java.sql.Date(System.currentTimeMillis()));
        nuevoMensaje.setEnviadoPorA((byte)(chat.getUsuarioByClienteA().getId() == emisorId? 1 : 0));
        chat.getMensajesById().add(nuevoMensaje);
        chatRepository.save(chat);
        mensajeRepository.save(nuevoMensaje);
    }
}
