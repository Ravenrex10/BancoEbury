package com.ebury.dao;

import com.ebury.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {

    //@Query("SELECT c FROM Chat c WHERE clienteA.id = :id OR clienteB.id = :id")
    //public List<ChatEntity> findChatEntitiesByUserId(@Param("id") Integer id);

}
