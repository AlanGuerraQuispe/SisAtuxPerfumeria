package com.aw.core.report.pdf;

import java.util.Date;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public class BNIngreso {
    protected Date fechaIngreso;
    protected String nuOC;
    protected String moneda;
    protected String almacen;

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNuOC() {
        return nuOC;
    }

    public void setNuOC(String nuOC) {
        this.nuOC = nuOC;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }
}
