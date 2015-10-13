package com.atux.infraestructura.jpa.pojo;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="PRTM_PROMO")
public class PrtmPromo extends JpaEntityBase implements AuditoriaEntity {


    @EmbeddedId
    private PrtmPromoPK id;

    @Column(name = "DE_CORTA_PROMOCION")
    private String mensajeCorto;

    @Column(name = "DE_PROMOCION")
    private String mensajeLargo;

    @Column(name = "NO_PROMOCION")
    private String noPromocion;

    @Column(name = "FE_INI_PROMOCION")
    private Date fechaInicio;

    @Column(name = "FE_FIN_PROMOCION")
    private Date fechaFin;

    @Column(name = "IN_TODOS_LOCALES")
    private String inTodosLocales;

    @Column(name = "ES_PROMOCION")
    private String esPromocion;

    @Column(name = "DE_OBSERVACIONES")
    private String observacion;

    @Column(name = "DE_MENSAJE")
    private String deMensaje;

    @Column(name = "ID_CREA_PROMOCION", columnDefinition = "VARCHAR(15)")
    private String creadoPor;

    @Column(name = "FE_CREA_PROMOCION")
    private Date fechaCreacion;

    @Column(name = "ID_MOD_PROMOCION", columnDefinition = "VARCHAR(15)")
    private String modificadoPor;

    @Column(name = "FE_MOD_PROMOCION")
    private Date fechaModificacion;

    public PrtmPromo() {
        id=new PrtmPromoPK();
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public PrtmPromoPK getId() {
        return id;
    }

    public void setId(PrtmPromoPK id) {
        this.id = id;
    }

    public String getMensajeCorto() {
        return mensajeCorto;
    }

    public void setMensajeCorto(String mensajeCorto) {
        this.mensajeCorto = mensajeCorto;
    }

    public String getMensajeLargo() {
        return mensajeLargo;
    }

    public void setMensajeLargo(String mensajeLargo) {
        this.mensajeLargo = mensajeLargo;
    }

    public String getInTodosLocales() {
        return inTodosLocales;
    }

    public void setInTodosLocales(String inTodosLocales) {
        this.inTodosLocales = inTodosLocales;
    }

    public String getEsPromocion() {
        return esPromocion;
    }

    public void setEsPromocion(String esPromocion) {
        this.esPromocion = esPromocion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDeMensaje() {
        return deMensaje;
    }

    public void setDeMensaje(String deMensaje) {
        this.deMensaje = deMensaje;
    }

    public String getNoPromocion() {
        return noPromocion;
    }

    public void setNoPromocion(String noPromocion) {
        this.noPromocion = noPromocion;
    }
}

