package com.ebury.dao;

import com.ebury.entity.CuentaEntity;
import com.ebury.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
    public List<CuentaEntity> findAllByUsuarioByDuenyo(UsuarioEntity usuario);

    // Busca todas las cuentas que no sean del usuario id
    @Query("select c from CuentaEntity c where c.id != :idCuenta")
    public List<CuentaEntity> findAllCuentasExceptThisUserById(@Param("idCuenta") Integer idCuenta);
}
