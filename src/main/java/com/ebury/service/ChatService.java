package com.ebury.service;

import com.ebury.dao.ChatRepository;
import com.ebury.dao.MensajeRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.ChatDTO;
import com.ebury.dto.MensajeDTO;
import com.ebury.entity.ChatEntity;
import com.ebury.entity.MensajeEntity;
import com.ebury.entity.UsuarioEntity;
import com.ebury.ui.FiltroChats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Daniel
 */
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
        nuevoChat.setCerrado(false);
        usuarioA.getChatsById().add(nuevoChat);
        usuarioB.getChatsById().add(nuevoChat);
        chatRepository.save(nuevoChat);
        usuarioRepository.save(usuarioA);
        usuarioRepository.save(usuarioB);
        return nuevoChat.toDTO();
    }

    public void enviarMensaje(int chatId, int emisorId, String contenido) {
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        MensajeEntity nuevoMensaje = new MensajeEntity();
        nuevoMensaje.setChatByChat(chat);
        nuevoMensaje.setContenido(contenido);
        nuevoMensaje.setFecha(new java.sql.Timestamp(System.currentTimeMillis()));
        nuevoMensaje.setEnviadoPorA((byte)(chat.getUsuarioByClienteA().getId() == emisorId? 1 : 0));
        chat.getMensajesById().add(nuevoMensaje);
        chatRepository.save(chat);
        mensajeRepository.save(nuevoMensaje);
    }

    /**
     *
     * @param chatId El id del chat al que se desea acceder
     * @param usuarioActualId El usuario que está accediendo al chat
     * @return Lista de los mensajes de ese chat (se mostrarán de forma diferente según si el punto de vista es desde A o desde B)
     */
    public List<MensajeDTO> findMensajesByChatId(int chatId, int usuarioActualId) {
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        Collection<MensajeEntity> mensajes = chat.getMensajesById();
        List<MensajeDTO> mensajeDTOs = new ArrayList<>();
        for (MensajeEntity mensaje : mensajes) {
            boolean enviadoPorUsuarioActual = mensajeEnviadoPor(chat, mensaje, usuarioActualId);
            MensajeDTO mensajeDTO = mensaje.toDto();
            mensajeDTO.setEnviadoPorUsuarioActual(enviadoPorUsuarioActual);

            mensajeDTOs.add(mensajeDTO);
        }
        return mensajeDTOs;
    }

    /**
     * Un usuario solo tendrá acceso a un chat si participa en él, a no se que sea asistente, entonces tendrá acceso
     * a todos los chats.
     */
    public boolean usuarioTieneAccesoAChat(int usuarioId, int chatId) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) return false;
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) return false;
        return usuario.getRolByRol().getNombre().equals("Asistente") || usuarioParticipaEnChat(usuario, chat);
    }

    public boolean usuarioPuedeEnviarMensajeAChat(int usuarioId, int chatId) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) return false;
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) return false;
        return !chat.getCerrado() && usuarioParticipaEnChat(usuario, chat);
    }

    private boolean usuarioParticipaEnChat(UsuarioEntity usuario, ChatEntity chat) {
        return chat.getUsuarioByClienteA().getId() == usuario.getId() ||
                chat.getUsuarioByClienteB().getId() == usuario.getId();
    }

    private boolean mensajeEnviadoPor(ChatEntity chat, MensajeEntity mensaje, int usuarioId) {
        boolean usuarioEsA = chat.getUsuarioByClienteA().getId() == usuarioId;
        boolean usuarioEsB = chat.getUsuarioByClienteB().getId() == usuarioId;
        return usuarioEsA && mensaje.getEnviadoPorA() == 1 || usuarioEsB && mensaje.getEnviadoPorA() == 0;
    }

    public void cerrarChat(int chatId) {
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        chat.setCerrado(true);
        chatRepository.save(chat);
    }

    public List<ChatDTO> filtrarChats(FiltroChats filtro, int usuarioId) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId).orElse(null);
        List<ChatEntity> chats;
        UsuarioEntity filtroUsuario = usuarioRepository.findById(filtro.getFiltroUsuario()).orElse(null);

        if (filtro.getCriterioOrdenacion().equals("ascendente")) {
            chats = chatRepository.findAllByOrderByIdAsc();
        } else {
            chats = chatRepository.findAllByOrderByIdDesc();
        }

        return chats.stream()
                .filter(chat -> filtroUsuario == null || usuarioParticipaEnChat(filtroUsuario, chat))
                .filter(chat -> filtro.isMostrarCerrados() || !chat.getCerrado())
                .filter(chat -> !filtro.isMostrarSoloPropios() || usuarioParticipaEnChat(usuario, chat))
                .map(ChatEntity::toDTO).toList();
    }

    public ChatDTO asignarChatACliente(int clienteId) {
        List<UsuarioEntity> asistentes = usuarioRepository.findAllByRolByRolNombre("Asistente");
        Random rng = new Random();
        UsuarioEntity asistenteAsignado = asistentes.get(rng.nextInt(asistentes.size()));
        return crearChatEntre(clienteId, asistenteAsignado.getId());
    }

}
