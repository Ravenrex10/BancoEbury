package com.ebury.dao;

import com.ebury.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<RolEntity,Integer> {
    RolEntity findByNombre(String nombre);
}
