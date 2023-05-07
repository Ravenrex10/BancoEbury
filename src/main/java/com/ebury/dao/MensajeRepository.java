package com.ebury.dao;

import com.ebury.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Daniel
 */
public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {
    

}
