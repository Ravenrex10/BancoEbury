package com.ebury.dao;

import com.ebury.entity.CuentaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
}
