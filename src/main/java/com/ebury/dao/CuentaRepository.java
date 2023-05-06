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
    public List<CuentaEntity> findAllByEstadoCuentaByEstado_Id(Integer id);

    // Busca todas las cuentas que no sean del usuario id @author Diego
    @Query("select c from CuentaEntity c where c.usuarioByDuenyo.id <> :idCuenta")
    public List<CuentaEntity> findAllCuentasExceptThisUserById(@Param("idCuenta") Integer idCuenta);

    // Devuelve todas las cuentas que sean de una empresa @author Diego
    @Query("select c from CuentaEntity c  where c.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa")
    List<CuentaEntity> findAllByEmpresaId(@Param("idEmpresa") Integer idEmpresa);
}
