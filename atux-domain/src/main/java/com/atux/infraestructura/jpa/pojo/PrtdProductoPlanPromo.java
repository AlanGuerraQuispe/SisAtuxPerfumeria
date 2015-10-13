package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="PRTD_PRODUCTO_PLAN_PROMO")
public class PrtdProductoPlanPromo extends JpaEntityBase implements AuditoriaEntity {


    @EmbeddedId
    private PrtdProductoPlanPromoPK id;

    @Column(name = "CA_PRODUCTO")
    private BigDecimal caEntero;

    @Column(name = "VA_FRACCION")
    private BigDecimal caFraccion;


    @Column(name = "CO_PRODUCTO_P")
    private String coProductoP;

    @Column(name = "CA_PRODUCTO_P")
    private BigDecimal caEnteroP;

    @Column(name = "VA_FRACCION_P")
    private BigDecimal caFraccionP;


    @Column(name = "ES_PRODUCTO_PLAN")
    private String esProductoPlan;

    @Column(name = "ID_CREA_PRODUCTO_PLAN", columnDefinition = "VARCHAR(15)")
    private String creadoPor;

    @Column(name = "FE_CREA_PRODUCTO_PLAN")
    private Date fechaCreacion;

    @Column(name = "ID_MOD_PRODUCTO_PLAN", columnDefinition = "VARCHAR(15)")
    private String modificadoPor;

    @Column(name = "FE_MOD_PRODUCTO_PLAN")
    private Date fechaModificacion;

    public PrtdProductoPlanPromo() {
        id=new PrtdProductoPlanPromoPK();
    }

    public PrtdProductoPlanPromoPK getId() {
        return id;
    }

    public void setId(PrtdProductoPlanPromoPK id) {
        this.id = id;
    }

    public BigDecimal getCaEntero() {
        return caEntero;
    }

    public void setCaEntero(BigDecimal caEntero) {
        this.caEntero = caEntero;
    }

    public BigDecimal getCaFraccion() {
        return caFraccion;
    }

    public void setCaFraccion(BigDecimal caFraccion) {
        this.caFraccion = caFraccion;
    }

    public String getCoProductoP() {
        return coProductoP;
    }

    public void setCoProductoP(String coProductoP) {
        this.coProductoP = coProductoP;
    }

    public BigDecimal getCaEnteroP() {
        return caEnteroP;
    }

    public void setCaEnteroP(BigDecimal caEnteroP) {
        this.caEnteroP = caEnteroP;
    }

    public BigDecimal getCaFraccionP() {
        return caFraccionP;
    }

    public void setCaFraccionP(BigDecimal caFraccionP) {
        this.caFraccionP = caFraccionP;
    }

    public String getEsProductoPlan() {
        return esProductoPlan;
    }

    public void setEsProductoPlan(String esProductoPlan) {
        this.esProductoPlan = esProductoPlan;
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

