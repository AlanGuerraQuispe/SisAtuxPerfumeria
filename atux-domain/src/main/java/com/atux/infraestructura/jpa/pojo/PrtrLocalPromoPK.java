package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PrtrLocalPromoPK implements Serializable {

    @Column(name = "CO_COMPANIA")
    private String coCompania;

    @Column(name = "CO_PROMOCION")
    private String coPromocion;

    @Column(name = "CO_LOCAL")
    private String coLocal;

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
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
