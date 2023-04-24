package com.ebury.entity;

import com.ebury.dto.EmpresaDTO;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "Empresa", schema = "BancoEbury", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "cif")
    private int cif;

    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "contrasenya")
    private String contrasenya;
    @ManyToOne
    @JoinColumn(name = "direccion", referencedColumnName = "id")
    private DireccionEntity direccionByDireccion;
    @OneToMany(mappedBy = "empresaByEmpresa")
    private Collection<UsuarioEntity> usuariosByCif;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
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

    public EmpresaDTO toDTO()
    {
        EmpresaDTO res = new EmpresaDTO();
        res.setCif(this.getCif());
        res.setContrasenya(this.getContrasenya());
        res.setId(this.getId());
        res.setNombre(this.getNombre());
        res.setUsuariosByCif(this.getUsuariosByCif().stream().map(UsuarioEntity::toDTO).collect(Collectors.toList()));
        return res;
    }

    public Collection<UsuarioEntity> getUsuariosByCif() {
        return usuariosByCif;
    }

    public void setUsuariosByCif(Collection<UsuarioEntity> usuariosByCif) {
        this.usuariosByCif = usuariosByCif;
    }
}
