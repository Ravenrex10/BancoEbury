package com.ebury.dao;

import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Diego
 */

public interface DireccionRepository extends JpaRepository<DireccionEntity,Integer> {

    DireccionEntity findDireccionEntityByEmpresasById(EmpresaEntity empresa);
}
