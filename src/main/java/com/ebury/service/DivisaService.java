package com.ebury.service;

import com.ebury.dao.CuentaRepository;
import com.ebury.dao.DivisaRepository;
import com.ebury.dao.SaldoRepository;
import com.ebury.entity.CuentaEntity;
import com.ebury.entity.DivisaEntity;
import com.ebury.entity.SaldoEntity;
import com.ebury.ui.CuentaDivisaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisaService {
    @Autowired
    protected DivisaRepository divisaRepository;

    @Autowired
    protected CuentaRepository cuentaRepository;

    @Autowired
    protected SaldoRepository saldoRepository;

    /** Devuelve una lista con todos los nombres de las divisas.
     @author Diego
     */
    public List<String> findAllDivisaNames()
    {
        return this.divisaRepository.findAllDivisaNames();
    }

    /**
     * Modifica la divisa de la cuenta y actualiza su saldo.
     * @author Diego
     */
    public void cambiarCuentaDivisa(CuentaDivisaWrapper newDivisa) {

        Integer cuentaId = newDivisa.getCuentaId();
        String divisaString = newDivisa.getDivisaNombre();

        CuentaEntity cuenta = this.cuentaRepository.findById(cuentaId).orElse(null);
        SaldoEntity saldoAntiguo = this.saldoRepository.findSaldoEntityByCuentaByCuenta_Id(cuentaId);
        DivisaEntity divisaNueva = this.divisaRepository.findByNombre(divisaString);

        DivisaEntity divisaAntigua = saldoAntiguo.getDivisaByDivisa();
        DivisaEntity dolar = this.divisaRepository.findById(1).orElse(null);

        SaldoEntity saldo = new SaldoEntity();
        saldo.setDivisaByDivisa(divisaNueva);

        Double newSaldo = 0.0;

        // Factores de conversión
        newSaldo = saldoAntiguo.getCantidad() * (dolar.getValor()/divisaAntigua.getValor()) * (divisaNueva.getValor()/dolar.getValor());
        saldo.setCantidad(newSaldo);
        saldo.setCuentaByCuenta(cuenta);

        this.saldoRepository.save(saldo);
        this.saldoRepository.delete(saldoAntiguo);
        this.cuentaRepository.save(cuenta);


    }
}
