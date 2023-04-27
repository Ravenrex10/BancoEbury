package com.ebury.dao;

import com.ebury.entity.CuentaEntity;
import com.ebury.entity.SaldoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoEntity, Integer> {
    public List<SaldoEntity> findAllByCuentaByCuenta_Id(Integer id);
}
