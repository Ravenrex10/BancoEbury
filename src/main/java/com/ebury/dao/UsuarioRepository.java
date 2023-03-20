package com.ebury.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ebury.entity.UsuarioEntity;
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
}
