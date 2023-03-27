package com.ebury.dao;

import com.ebury.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select a from UsuarioEntity a where a.primerNombre = :usuario and a.contrasenya = :clave")
    public UsuarioEntity autenticar (@Param("usuario") String usuario, @Param("clave")String clave);
}
