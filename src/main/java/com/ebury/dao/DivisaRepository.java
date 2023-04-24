package com.ebury.dao;

import com.ebury.entity.DivisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisaRepository extends JpaRepository<DivisaEntity, Integer> {
}
