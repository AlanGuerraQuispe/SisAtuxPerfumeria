package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="PRTR_LOCAL_PROMO")
public class PrtrLocalPromo extends JpaEntityBase implements AuditoriaEntity {


    @EmbeddedId
    private PrtrLocalPromoPK id;

    public PrtrLocalPromo() {
        id=new PrtrLocalPromoPK();
    }

    @Column(name = "ES_LOCAL_PROMOCION")
    private String esLocalPromocion;

    @Column(name = "ID_CREA_LOCAL_PROMOCION", columnDefinition = "VARCHAR(15)")
    private String creadoPor;

    @Column(name = "FE_CREA_LOCAL_PROMOCION")
    private Date fechaCreacion;

    @Column(name = "ID_MOD_LOCAL_PROMOCION", columnDefinition = "VARCHAR(15)")
    private String modificadoPor;

    @Column(name = "FE_MOD_LOCAL_PROMOCION")
    private Date fechaModificacion;

    public PrtrLocalPromoPK getId() {
        return id;
    }

    public void setId(PrtrLocalPromoPK id) {
        this.id = id;
    }

    public String getEsLocalPromocion() {
        return esLocalPromocion;
    }

    public void setEsLocalPromocion(String esLocalPromocion) {
        this.esLocalPromocion = esLocalPromocion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}

