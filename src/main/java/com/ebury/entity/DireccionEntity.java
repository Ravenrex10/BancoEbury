package com.ebury.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Direccion", schema = "BancoEbury", catalog = "")
public class DireccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "calle")
    private String calle;
    @Basic
    @Column(name = "numero")
    private String numero;
    @Basic
    @Column(name = "planta")
    private String planta;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @Basic
    @Column(name = "pais")
    private String pais;
    @Basic
    @Column(name = "region")
    private String region;
    @Basic
    @Column(name = "cp")
    private String cp;
    @OneToMany(mappedBy = "direccionByDireccion")
    private Collection<EmpresaEntity> empresasById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DireccionEntity that = (DireccionEntity) o;

        if (id != that.id) return false;
        if (calle != null ? !calle.equals(that.calle) : that.calle != null) return false;
        if (numero != null ? !numero.equals(that.numero) : that.numero != null) return false;
        if (planta != null ? !planta.equals(that.planta) : that.planta != null) return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;
        if (pais != null ? !pais.equals(that.pais) : that.pais != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (cp != null ? !cp.equals(that.cp) : that.cp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (calle != null ? calle.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (planta != null ? planta.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        return result;
    }

    public Collection<EmpresaEntity> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(Collection<EmpresaEntity> empresasById) {
        this.empresasById = empresasById;
    }
}
