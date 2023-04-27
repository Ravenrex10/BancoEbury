package com.ebury.entity;

import com.ebury.dto.SaldoDTO;
import com.ebury.dto.UsuarioDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "Saldo", schema = "BancoEbury", catalog = "")
public class SaldoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "cantidad")
    private Double cantidad;
    @ManyToOne
    @JoinColumn(name = "cuenta", referencedColumnName = "id")
    private CuentaEntity cuentaByCuenta;
    @ManyToOne
    @JoinColumn(name = "divisa", referencedColumnName = "id")
    private DivisaEntity divisaByDivisa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaldoEntity that = (SaldoEntity) o;

        if (id != that.id) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }

    public CuentaEntity getCuentaByCuenta() {
        return cuentaByCuenta;
    }

    public void setCuentaByCuenta(CuentaEntity cuentaByCuenta) {
        this.cuentaByCuenta = cuentaByCuenta;
    }

    public DivisaEntity getDivisaByDivisa() {
        return divisaByDivisa;
    }

    public void setDivisaByDivisa(DivisaEntity divisaByDivisa) {
        this.divisaByDivisa = divisaByDivisa;
    }

    public SaldoDTO toDTO() {
        SaldoDTO dto = new SaldoDTO();
        dto.setDivisa(divisaByDivisa.getNombre());
        dto.setCantidad(cantidad);
        return dto;
    }
}
