package com.ebury.ui;

/**
 * @author Diego
 * Esta clase auxiliar sirve para el cambio de divisa
 */

public class CuentaDivisaWrapper {
    private Integer cuentaId;
    private String divisaNombre;

    public CuentaDivisaWrapper()
    {
        this.cuentaId = 0;
        this.divisaNombre = " ";
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getDivisaNombre() {
        return divisaNombre;
    }

    public void setDivisaNombre(String divisaNombre) {
        this.divisaNombre = divisaNombre;
    }
}
