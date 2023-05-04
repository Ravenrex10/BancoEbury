package com.ebury.dao;

import com.ebury.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {

    @Query("SELECT m FROM MensajeEntity m WHERE m.chatByChat = :chatId ORDER BY m.id DESC LIMIT 1")
    MensajeEntity findUltimoMensajeDeChat(@Param("chatId") int chatId);

}
