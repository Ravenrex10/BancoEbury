package com.ebury.dao;

import com.ebury.entity.DireccionEntity;
import com.ebury.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<DireccionEntity,Integer> {

    DireccionEntity findDireccionEntityByEmpresasById(EmpresaEntity empresa);
}
