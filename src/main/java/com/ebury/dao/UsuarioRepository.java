package com.ebury.dao;

import com.ebury.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select a from UsuarioEntity a where a.email = :usuario and a.contrasenya = :clave")
    public UsuarioEntity autenticar (@Param("usuario") String usuario, @Param("clave")String clave);
    @Query("select u from UsuarioEntity u where u.rolByRol.nombre='Cliente' OR u.rolByRol.nombre='SocioEmpresa' OR u.rolByRol.nombre='AutorizadoEmpresa'")
    public List<UsuarioEntity> findAllClientes();

    @Query("select u from UsuarioEntity u where (u.rolByRol.nombre='SocioEmpresa' OR u.rolByRol.nombre='AutorizadoEmpresa') AND u.empresaByEmpresa = null AND u.alta = false ")
    public List<UsuarioEntity> findAllSociosAndAutorizadosNotInMyCompany();

    public List<UsuarioEntity> findAllByAlta(Boolean alta);
}
