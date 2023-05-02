package com.ebury.service;

import com.ebury.dao.DivisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisaService {
    @Autowired
    protected DivisaRepository divisaRepository;

    // Devuelve una lista con todos los nombres de las divisas.
    // @author Diego
    public List<String> findAllDivisaNames()
    {
        return this.divisaRepository.findAllDivisaNames();
    }
}
