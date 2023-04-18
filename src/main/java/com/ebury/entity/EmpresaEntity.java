package com.ebury.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Empresa", schema = "BancoEbury", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cif")
    private int cif;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "direccion", referencedColumnName = "id")
    private DireccionEntity direccionByDireccion;
    @OneToMany(mappedBy = "empresaByEmpresa")
    private Collection<UsuarioEntity> usuariosByCif;

    public int getCif() {
        return cif;
    }

    public void setCif(int cif) {
        this.cif = cif;
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

        EmpresaEntity that = (EmpresaEntity) o;

        if (cif != that.cif) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cif;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public DireccionEntity getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public void setDireccionByDireccion(DireccionEntity direccionByDireccion) {
        this.direccionByDireccion = direccionByDireccion;
    }

    public Collection<UsuarioEntity> getUsuariosByCif() {
        return usuariosByCif;
    }

    public void setUsuariosByCif(Collection<UsuarioEntity> usuariosByCif) {
        this.usuariosByCif = usuariosByCif;
    }
}
