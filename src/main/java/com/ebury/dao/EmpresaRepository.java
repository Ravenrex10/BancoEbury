package com.ebury.dao;

import com.ebury.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Integer> {
   EmpresaEntity findByCif(int cif);
    //public EmpresaEntity findByCifAndContrasenya(Integer cif, String contrasenya);
}
