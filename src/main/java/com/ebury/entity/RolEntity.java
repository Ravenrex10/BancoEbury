package com.ebury.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Rol", schema = "BancoEbury", catalog = "")
public class RolEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "rolByRol")
    private Collection<UsuarioEntity> usuariosById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolEntity rolEntity = (RolEntity) o;

        if (id != rolEntity.id) return false;
        if (nombre != null ? !nombre.equals(rolEntity.nombre) : rolEntity.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public Collection<UsuarioEntity> getUsuariosById() {
        return usuariosById;
    }

    public void setUsuariosById(Collection<UsuarioEntity> usuariosById) {
        this.usuariosById = usuariosById;
    }
}
