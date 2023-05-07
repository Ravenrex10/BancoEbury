package com.ebury.dao;

import com.ebury.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    // @author Lucas Colbert Eastgate
    @Query("select a from UsuarioEntity a where a.email = :usuario and a.contrasenya = :clave")
    public UsuarioEntity autenticar(@Param("usuario") String usuario, @Param("clave") String clave);

    // @author Diego
    @Query("select u from UsuarioEntity u where u.rolByRol.nombre='Cliente' OR u.rolByRol.nombre='SocioEmpresa' OR u.rolByRol.nombre='AutorizadoEmpresa' OR u.rolByRol.nombre='FundadorEmpresa'")
    public List<UsuarioEntity> findAllClientes();

    // @author Diego
    @Query("select u from UsuarioEntity u where (u.rolByRol.nombre='SocioEmpresa' OR u.rolByRol.nombre='AutorizadoEmpresa') AND u.empresaByEmpresa = null AND u.alta = false ")
    public List<UsuarioEntity> findAllSociosAndAutorizadosNotInMyCompany();

    // @author Diego
    public List<UsuarioEntity> findAllByAltaSolicitada(Boolean altaSolicitada);

    // @author Diego
    public List<UsuarioEntity> findAllByRolByRolNombre(String rol);

    // @author Diego
    @Query("select u from UsuarioEntity u where u.empresaByEmpresa.id = :empresaId and (u.rolByRol.nombre = 'AutorizadoEmpresa' OR u.rolByRol.nombre='SocioEmpresa' OR u.rolByRol.nombre='FundadorEmpresa')")
    List<UsuarioEntity> findAllFundadoresAndSociosAndAutorizadosByEmpresaByEmpresaId(@Param("empresaId") Integer empresaId);

    // @author Diego
    @Query("select u from UsuarioEntity u where u.empresaByEmpresa.id = :empresaId and (u.rolByRol.nombre = 'AutorizadoEmpresa' OR u.rolByRol.nombre='SocioEmpresa')")
    List<UsuarioEntity> findAllSociosAndAutorizadosByEmpresa(@Param("empresaId") Integer empresaId);

    // @author Lucas Colbert Eastgate
    @Query("select u from UsuarioEntity u where (DATEDIFF(curdate(), u.fechaUltimaOperacion) >= 30) " +
            "AND (u.rolByRol.nombre='Cliente' OR u.rolByRol.nombre='SocioEmpresa' OR u.rolByRol.nombre='AutorizadoEmpresa' OR u.rolByRol.nombre='FundadorEmpresa') ")
    List<UsuarioEntity> findAllUsuariosInactivos();

    /**
     * @author Daniel
     */
    @Query("select u from UsuarioEntity u where size(u.chatsById) <> 0")
    List<UsuarioEntity> findAllUsuariosQueParticipanEnChat();

}