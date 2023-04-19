package com.ebury.dao;

import com.ebury.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {

    @Query("SELECT c FROM ChatEntity c WHERE c.usuarioByClienteA.id = :id OR c.usuarioByClienteB.id = :id")
    public List<ChatEntity> findChatEntitiesByUserId(@Param("id") Integer id);

}
