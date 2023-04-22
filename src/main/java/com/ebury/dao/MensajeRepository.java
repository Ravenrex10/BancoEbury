package com.ebury.dao;

import com.ebury.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {

}
