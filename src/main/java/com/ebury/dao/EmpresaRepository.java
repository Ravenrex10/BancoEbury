package com.ebury.dao;

import com.ebury.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Integer> {
}
