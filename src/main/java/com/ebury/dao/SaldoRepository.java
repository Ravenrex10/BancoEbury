package com.ebury.dao;

import com.ebury.entity.SaldoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoEntity, Integer> {
}
