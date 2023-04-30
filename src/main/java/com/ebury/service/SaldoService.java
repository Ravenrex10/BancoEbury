package com.ebury.service;

import com.ebury.dao.SaldoRepository;
import com.ebury.dto.SaldoDTO;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.SaldoEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaldoService {
    @Autowired
    SaldoRepository saldoRepository;

    //Autor Lucas Colbert Eastgate
    public List<SaldoDTO> findAllSaldosByCuenta(Integer id){
        List<SaldoEntity> saldoEntities = saldoRepository.findAllByCuentaByCuenta_Id(id);
        return saldoEntities.stream().map(SaldoEntity::toDTO).collect(Collectors.toList());
    }

}
