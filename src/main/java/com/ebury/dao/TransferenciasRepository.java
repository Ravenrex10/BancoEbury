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

    // Busca todas las transfencias de una empresa ordenadas por fecha ascendente @author Diego
    @Query("select t from TransferenciaEntity t where t.cuentaByCuentaOrigen.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa OR t.cuentaByCuentaDestino.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa ORDER BY t.fecha ASC")
    List<TransferenciaEntity> findAllByEmpresaId(@Param("idEmpresa") Integer idEmpresa);

    // Busca todas las transfencias de una empresa ordenadas por fecha descendente @author Diego
    @Query("select t from TransferenciaEntity t where t.cuentaByCuentaOrigen.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa OR t.cuentaByCuentaDestino.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa ORDER BY t.fecha DESC")
    List<TransferenciaEntity> findAllByEmpresaIdOrderByDesc(@Param("idEmpresa") Integer idEmpresa);

    // Busca todas las transfencias de una empresa, una cuenta y una divisa en orden de fecha descendente @author Diego
    @Query("select t from TransferenciaEntity t where (t.cuentaByCuentaOrigen.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa OR t.cuentaByCuentaDestino.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa) AND (t.divisaByDivisaOrigen.nombre = :divisa) AND (t.cuentaByCuentaOrigen.id = :idCuenta OR t.cuentaByCuentaDestino.id = :idCuenta) ORDER BY t.fecha DESC ")
    List<TransferenciaEntity> findAllTransferenciasFromAEmpresaByDivisaAndUsuarioIdOrderDesc(@Param("idEmpresa") Integer idEmpresa, @Param("divisa") String divisa, @Param("idCuenta") Integer idCuenta);

    // Busca todas las transfencias de una empresa, una cuenta y una divisa en orden de fecha ascendente @author Diego
    @Query("select t from TransferenciaEntity t where (t.cuentaByCuentaOrigen.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa OR t.cuentaByCuentaDestino.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa) AND (t.divisaByDivisaOrigen.nombre = :divisa) AND (t.cuentaByCuentaOrigen.id = :idCuenta OR t.cuentaByCuentaDestino.id = :idCuenta) ORDER BY t.fecha ASC ")
    List<TransferenciaEntity> findAllTransferenciasFromAEmpresaByDivisaAndUsuarioIdOrderAsc(@Param("idEmpresa") Integer idEmpresa, @Param("divisa") String divisa, @Param("idCuenta") Integer idCuenta);

    // Busca todas las transfencias de una empresa y una divisa en orden de fecha descendente @author Diego
    @Query("select t from TransferenciaEntity t where (t.cuentaByCuentaOrigen.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa OR t.cuentaByCuentaDestino.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa) AND (t.divisaByDivisaOrigen.nombre = :divisa) ORDER BY t.fecha DESC ")
    List<TransferenciaEntity> findAllTransferenciasFromAEmpresaByDivisaOrderDesc(@Param("idEmpresa") Integer idEmpresa, @Param("divisa") String divisa);

    // Busca todas las transfencias de una empresa y una divisa en orden de fecha ascendente @author Diego
    @Query("select t from TransferenciaEntity t where (t.cuentaByCuentaOrigen.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa OR t.cuentaByCuentaDestino.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa) AND (t.divisaByDivisaOrigen.nombre = :divisa) ORDER BY t.fecha ASC ")
    List<TransferenciaEntity> findAllTransferenciasFromAEmpresaByDivisaOrderAsc(@Param("idEmpresa") Integer idEmpresa, @Param("divisa") String divisa);

    // Busca todas las transfencias de una empresa y una cuenta en orden de fecha descendente @author Diego
    @Query("select t from TransferenciaEntity t where (t.cuentaByCuentaOrigen.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa OR t.cuentaByCuentaDestino.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa) AND (t.cuentaByCuentaOrigen.id = :idCuenta OR t.cuentaByCuentaDestino.id = :idCuenta) ORDER BY t.fecha DESC ")
    List<TransferenciaEntity> findAllTransferenciasFromAEmpresaByUsuarioIdOrderDesc(@Param("idEmpresa") Integer idEmpresa, @Param("idCuenta") Integer idCuenta);

    // Busca todas las transfencias de una empresa y una cuenta en orden de fecha ascendente @author Diego
    @Query("select t from TransferenciaEntity t where (t.cuentaByCuentaOrigen.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa OR t.cuentaByCuentaDestino.usuarioByDuenyo.empresaByEmpresa.id = :idEmpresa) AND (t.cuentaByCuentaOrigen.id = :idCuenta OR t.cuentaByCuentaDestino.id = :idCuenta) ORDER BY t.fecha ASC ")
    List<TransferenciaEntity> findAllTransferenciasFromAEmpresaByUsuarioIdOrderAsc(@Param("idEmpresa") Integer idEmpresa, @Param("idCuenta") Integer idCuenta);
}
