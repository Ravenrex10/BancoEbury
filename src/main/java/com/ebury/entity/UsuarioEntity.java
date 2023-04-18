package com.ebury.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "Usuario", schema = "BancoEbury", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "primerNombre")
    private String primerNombre;
    @Basic
    @Column(name = "segundoNombre")
    private String segundoNombre;
    @Basic
    @Column(name = "primerApellido")
    private String primerApellido;
    @Basic
    @Column(name = "segundoApellido")
    private String segundoApellido;
    @Basic
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
    @Basic
    @Column(name = "contrasenya")
    private String contrasenya;
    @Basic
    @Column(name = "alta")
    private Boolean alta;
    @OneToMany(mappedBy = "usuarioByClienteA")
    private Collection<ChatEntity> chatsById;
    @OneToMany(mappedBy = "usuarioByClienteB")
    private Collection<ChatEntity> chatsById_0;
    @OneToMany(mappedBy = "usuarioByDuenyo")
    private Collection<CuentaEntity> cuentasById;
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "cif")
    private EmpresaEntity empresaByEmpresa;
    @ManyToOne
    @JoinColumn(name = "rol", referencedColumnName = "id")
    private RolEntity rolByRol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity that = (UsuarioEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (primerNombre != null ? !primerNombre.equals(that.primerNombre) : that.primerNombre != null) return false;
        if (segundoNombre != null ? !segundoNombre.equals(that.segundoNombre) : that.segundoNombre != null)
            return false;
        if (primerApellido != null ? !primerApellido.equals(that.primerApellido) : that.primerApellido != null)
            return false;
        if (segundoApellido != null ? !segundoApellido.equals(that.segundoApellido) : that.segundoApellido != null)
            return false;
        if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null)
            return false;
        if (contrasenya != null ? !contrasenya.equals(that.contrasenya) : that.contrasenya != null) return false;
        if (alta != null ? !alta.equals(that.alta) : that.alta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (primerNombre != null ? primerNombre.hashCode() : 0);
        result = 31 * result + (segundoNombre != null ? segundoNombre.hashCode() : 0);
        result = 31 * result + (primerApellido != null ? primerApellido.hashCode() : 0);
        result = 31 * result + (segundoApellido != null ? segundoApellido.hashCode() : 0);
        result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
        result = 31 * result + (contrasenya != null ? contrasenya.hashCode() : 0);
        result = 31 * result + (alta != null ? alta.hashCode() : 0);
        return result;
    }

    public Collection<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(Collection<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public Collection<ChatEntity> getChatsById_0() {
        return chatsById_0;
    }

    public void setChatsById_0(Collection<ChatEntity> chatsById_0) {
        this.chatsById_0 = chatsById_0;
    }

    public Collection<CuentaEntity> getCuentasById() {
        return cuentasById;
    }

    public void setCuentasById(Collection<CuentaEntity> cuentasById) {
        this.cuentasById = cuentasById;
    }

    public EmpresaEntity getEmpresaByEmpresa() {
        return empresaByEmpresa;
    }

    public void setEmpresaByEmpresa(EmpresaEntity empresaByEmpresa) {
        this.empresaByEmpresa = empresaByEmpresa;
    }

    public RolEntity getRolByRol() {
        return rolByRol;
    }

    public void setRolByRol(RolEntity rolByRol) {
        this.rolByRol = rolByRol;
    }
}
