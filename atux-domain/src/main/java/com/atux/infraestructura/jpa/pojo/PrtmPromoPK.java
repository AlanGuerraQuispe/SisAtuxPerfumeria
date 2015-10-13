package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PrtmPromoPK implements Serializable {



    @Column(name = "CO_COMPANIA")
    private String coCompania;

    @Column(name = "CO_PROMOCION")
    private String coPromocion;

    public PrtmPromoPK() {
    }

    public PrtmPromoPK(String coCompania, String coPromocion) {
        this.coCompania = coCompania;
        this.coPromocion = coPromocion;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoPromocion() {
        return coPromocion;
    }

    public void setCoPromocion(String coPromocion) {
        this.coPromocion = coPromocion;
    }
}
