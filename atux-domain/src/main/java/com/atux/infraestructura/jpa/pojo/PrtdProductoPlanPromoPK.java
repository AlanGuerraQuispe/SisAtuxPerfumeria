package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PrtdProductoPlanPromoPK implements Serializable {

    @Column(name = "CO_COMPANIA")
    private String coCompania;

    @Column(name = "CO_PROMOCION")
    private String coPromocion;

    @Column(name = "CO_PRODUCTO")
    private String coProducto;

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
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
