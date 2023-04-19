package com.ebury.service;

import com.ebury.dao.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    UsuarioRepository usuarioRepository;
}
