package com.ebury.dao;

import com.ebury.entity.EstadoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<EstadoCuentaEntity, Integer> {
}
