package com.ebury.dao;

import com.ebury.entity.DivisaEntity;
import com.ebury.entity.SaldoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoEntity, Integer> {
    List<SaldoEntity> findAllByCuentaByCuenta_Id(Integer id);

    /**
        Devuelve el saldo de una cuenta con una divisa en concreto
        @author Diego
     */
    @Query("select s from SaldoEntity s where s.cuentaByCuenta.id = :cuentaId")
    SaldoEntity findSaldoEntityByCuentaByCuenta_Id(@Param("cuentaId") Integer cuentaId);


    @Query("select s.divisaByDivisa from SaldoEntity s where s.id = :saldoId")
    DivisaEntity findDivisaBySaldoId(@Param("saldoId") Integer saldoId);
}
