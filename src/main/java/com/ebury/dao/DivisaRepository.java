package com.ebury.dao;

import com.ebury.entity.DivisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisaRepository extends JpaRepository<DivisaEntity, Integer> {

    @Query("select d.nombre from DivisaEntity d")
    List<String> findAllDivisaNames();


}
