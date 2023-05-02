package com.ebury.dao;

import com.ebury.entity.DivisaEntity;
import com.ebury.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciasRepository extends JpaRepository<TransferenciaEntity, Integer> {

    @Query("select t from TransferenciaEntity t where t.cuentaByCuentaOrigen.usuarioByDuenyo.id = :id OR t.cuentaByCuentaDestino.usuarioByDuenyo.id = :id")
    public List<TransferenciaEntity> findAllByUsuario(@Param("id") Integer id);

    @Query("select t from TransferenciaEntity t where t.cuentaByCuentaOrigen.usuarioByDuenyo.id = :id")
    public List<TransferenciaEntity> findAllByUsuarioOrigen(Integer id);

    @Query("select t from TransferenciaEntity  t where t.cuentaByCuentaDestino.usuarioByDuenyo.id = :id")
    public List<TransferenciaEntity> findAllByUsuarioDestino(Integer id);
}
