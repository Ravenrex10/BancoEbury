package com.ebury.service;

import com.ebury.dao.ChatRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.ChatDTO;
import com.ebury.entity.ChatEntity;
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
        //TODO: aqui salta un error porque el id no está puesto como autoincrement
        chatRepository.save(nuevoChat);
        return nuevoChat.toDTO();
    }
}
