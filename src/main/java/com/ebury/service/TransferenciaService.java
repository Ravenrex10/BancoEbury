package com.ebury.service;

import com.ebury.dao.TransferenciasRepository;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.entity.TransferenciaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciasRepository transferenciasRepository;

    public List<TransferenciaDTO> findAllByUsuarioOrigen(Integer id){
        List<TransferenciaEntity> transferenciaEntities = transferenciasRepository.findAllByUsuarioOrigen(id);
        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }

    public List<TransferenciaDTO> findAllByUsuarioDestino(Integer id){
        List<TransferenciaEntity> transferenciaEntities = transferenciasRepository.findAllByUsuarioDestino(id);
        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }

    public List<TransferenciaDTO> findAllTransferencias(Integer usuario) {
        List<TransferenciaEntity> transferenciaEntities = transferenciasRepository.findAllByUsuario(usuario);
        return transferenciaEntities.stream().map(TransferenciaEntity::toDTO).collect(Collectors.toList());
    }
}
