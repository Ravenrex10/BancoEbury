package com.ebury.dao;

import com.ebury.entity.CuentaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
    public List<CuentaEntity> findAllByUsuarioByDuenyo(UsuarioEntity usuario);
}
