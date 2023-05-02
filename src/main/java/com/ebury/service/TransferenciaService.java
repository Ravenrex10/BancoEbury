package com.ebury.service;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.SaldoRepository;
import com.ebury.dao.TransferenciasRepository;
import com.ebury.dao.UsuarioRepository;
import com.ebury.dto.TransferenciaDTO;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.SaldoEntity;
import com.ebury.entity.TransferenciaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {
    @Autowired
    TransferenciasRepository transferenciasRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    SaldoRepository saldoRepository;

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

    /*
        Transfiere una cantidad de dinero desde una cuenta origen a una cuenta destino
        @author Diego
     */
    public String transferir(Integer origen, Integer destino,Double cantidad, String divisaId)
    {
        CuentaEntity cuentaOrigen = this.cuentaRepository.findById(origen).orElse(null);
        CuentaEntity cuentaDestino = this.cuentaRepository.findById(destino).orElse(null);

        SaldoEntity saldoOrigen = this.saldoRepository.findSaldoEntityByDivisaByDivisa_IdAndCuentaByCuenta_Id(divisaId,cuentaOrigen.getId());
        SaldoEntity saldoDestino = this.saldoRepository.findSaldoEntityByDivisaByDivisa_IdAndCuentaByCuenta_Id(divisaId,cuentaDestino.getId());

        saldoOrigen.setCantidad(saldoOrigen.getCantidad() - cantidad);
        saldoDestino.setCantidad(saldoDestino.getCantidad() + cantidad);

        this.saldoRepository.save(saldoDestino);
        this.saldoRepository.save(saldoOrigen);
        this.cuentaRepository.save(cuentaOrigen);
        this.cuentaRepository.save(cuentaDestino);

        return "redirect:/empresa/";


    }
}
