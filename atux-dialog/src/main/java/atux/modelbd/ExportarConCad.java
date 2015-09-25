package atux.modelbd;

import atux.core.JAbstractModelBD;
import java.io.Serializable;
import java.util.Arrays;


public class ExportarConCad extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

//    public static final String nt = "VTTR_FACTOR_PRECIO";
//    public static final String[] COLUMNA_PK ={"CO_COMPANIA", "CO_FACTOR_PRECIO"};
//    public static final String COLUMNA_ACTIVO = "ES_FACTOR_PRECIO";
//    public static final String[] COLUMNA_ORDER ={"DE_FACTOR_PRECIO"};

    private String nuSubDiario;
    private String nuNumero;
    private String feComprobante;
    private String coMoneda;
    private String deGlosaPrincipal;
    private double vaTasaCambio;
    private String tiConversion;
    private String inConversionMoneda;
    private String feTipoCambio;
    private String nuCuentaContable;
    private String coAnexo;
    private String coCentroCosto;
    private String inDebeHaber;
    private double nuImporteOriginal;
    private double nuImporteDolares;
    private double nuImporteSoles;
    private String tiDocumento;
    private String nuDocumento;
    private String feDocumento;
    private String feVencimiento;
    private String coArea;
    private String deGlosaDetalle;
    private String coAnexoAuxiliar;
    private String inMedioPago;
    private String tiDocumentoReferencia;
    private String nuDocumentoReferencia;
    private String feDocumentoReferencia;
    private String baImponibleDocReferencia;
    private String vaIGVDocumentoProvision;
    private String tiReferenciaEstadoMQ;
    private String nuSerieCajaReg;
    private String feOperacion;
    private String tiTasa;
    private String nuTasaDetraccion;
    private String nuImporteBaseDetracDolares;
    private String nuImporteBaseDetracSoles;

    public String getBaImponibleDocReferencia() {
        return baImponibleDocReferencia;
    }

    public void setBaImponibleDocReferencia(String baImponibleDocReferencia) {
        this.baImponibleDocReferencia = baImponibleDocReferencia;
    }

    public String getCoAnexo() {
        return coAnexo;
    }

    public void setCoAnexo(String coAnexo) {
        this.coAnexo = coAnexo;
    }

    public String getCoAnexoAuxiliar() {
        return coAnexoAuxiliar;
    }

    public void setCoAnexoAuxiliar(String coAnexoAuxiliar) {
        this.coAnexoAuxiliar = coAnexoAuxiliar;
    }

    public String getCoArea() {
        return coArea;
    }

    public void setCoArea(String coArea) {
        this.coArea = coArea;
    }

    public String getCoCentroCosto() {
        return coCentroCosto;
    }

    public void setCoCentroCosto(String coCentroCosto) {
        this.coCentroCosto = coCentroCosto;
    }

    public String getCoMoneda() {
        return coMoneda;
    }

    public void setCoMoneda(String coMoneda) {
        this.coMoneda = coMoneda;
    }

    public String getDeGlosaDetalle() {
        return deGlosaDetalle;
    }

    public void setDeGlosaDetalle(String deGlosaDetalle) {
        this.deGlosaDetalle = deGlosaDetalle;
    }

    public String getDeGlosaPrincipal() {
        return deGlosaPrincipal;
    }

    public void setDeGlosaPrincipal(String deGlosaPrincipal) {
        this.deGlosaPrincipal = deGlosaPrincipal;
    }

    public String getFeComprobante() {
        return feComprobante;
    }

    public void setFeComprobante(String feComprobante) {
        this.feComprobante = feComprobante;
    }

    public String getFeDocumento() {
        return feDocumento;
    }

    public void setFeDocumento(String feDocumento) {
        this.feDocumento = feDocumento;
    }

    public String getFeDocumentoReferencia() {
        return feDocumentoReferencia;
    }

    public void setFeDocumentoReferencia(String feDocumentoReferencia) {
        this.feDocumentoReferencia = feDocumentoReferencia;
    }

    public String getFeOperacion() {
        return feOperacion;
    }

    public void setFeOperacion(String feOperacion) {
        this.feOperacion = feOperacion;
    }

    public String getFeTipoCambio() {
        return feTipoCambio;
    }

    public void setFeTipoCambio(String feTipoCambio) {
        this.feTipoCambio = feTipoCambio;
    }

    public String getFeVencimiento() {
        return feVencimiento;
    }

    public void setFeVencimiento(String feVencimiento) {
        this.feVencimiento = feVencimiento;
    }

    public String getInConversionMoneda() {
        return inConversionMoneda;
    }

    public void setInConversionMoneda(String inConversionMoneda) {
        this.inConversionMoneda = inConversionMoneda;
    }

    public String getInDebeHaber() {
        return inDebeHaber;
    }

    public void setInDebeHaber(String inDebeHaber) {
        this.inDebeHaber = inDebeHaber;
    }

    public String getInMedioPago() {
        return inMedioPago;
    }

    public void setInMedioPago(String inMedioPago) {
        this.inMedioPago = inMedioPago;
    }

    public String getNuCuentaContable() {
        return nuCuentaContable;
    }

    public void setNuCuentaContable(String nuCuentaContable) {
        this.nuCuentaContable = nuCuentaContable;
    }

    public String getNuDocumento() {
        return nuDocumento;
    }

    public void setNuDocumento(String nuDocumento) {
        this.nuDocumento = nuDocumento;
    }

    public String getNuDocumentoReferencia() {
        return nuDocumentoReferencia;
    }

    public void setNuDocumentoReferencia(String nuDocumentoReferencia) {
        this.nuDocumentoReferencia = nuDocumentoReferencia;
    }

    public String getNuImporteBaseDetracDolares() {
        return nuImporteBaseDetracDolares;
    }

    public void setNuImporteBaseDetracDolares(String nuImporteBaseDetracDolares) {
        this.nuImporteBaseDetracDolares = nuImporteBaseDetracDolares;
    }

    public String getNuImporteBaseDetracSoles() {
        return nuImporteBaseDetracSoles;
    }

    public void setNuImporteBaseDetracSoles(String nuImporteBaseDetracSoles) {
        this.nuImporteBaseDetracSoles = nuImporteBaseDetracSoles;
    }

    public double getNuImporteDolares() {
        return nuImporteDolares;
    }

    public void setNuImporteDolares(double nuImporteDolares) {
        this.nuImporteDolares = nuImporteDolares;
    }

    public double getNuImporteOriginal() {
        return nuImporteOriginal;
    }

    public void setNuImporteOriginal(double nuImporteOriginal) {
        this.nuImporteOriginal = nuImporteOriginal;
    }

    public double getNuImporteSoles() {
        return nuImporteSoles;
    }

    public void setNuImporteSoles(double nuImporteSoles) {
        this.nuImporteSoles = nuImporteSoles;
    }

    public String getNuNumero() {
        return nuNumero;
    }

    public void setNuNumero(String nuNumero) {
        this.nuNumero = nuNumero;
    }

    public String getNuSerieCajaReg() {
        return nuSerieCajaReg;
    }

    public void setNuSerieCajaReg(String nuSerieCajaReg) {
        this.nuSerieCajaReg = nuSerieCajaReg;
    }

    public String getNuSubDiario() {
        return nuSubDiario;
    }

    public void setNuSubDiario(String nuSubDiario) {
        this.nuSubDiario = nuSubDiario;
    }

    public String getNuTasaDetraccion() {
        return nuTasaDetraccion;
    }

    public void setNuTasaDetraccion(String nuTasaDetraccion) {
        this.nuTasaDetraccion = nuTasaDetraccion;
    }

    public String getTiConversion() {
        return tiConversion;
    }

    public void setTiConversion(String tiConversion) {
        this.tiConversion = tiConversion;
    }

    public String getTiDocumento() {
        return tiDocumento;
    }

    public void setTiDocumento(String tiDocumento) {
        this.tiDocumento = tiDocumento;
    }

    public String getTiDocumentoReferencia() {
        return tiDocumentoReferencia;
    }

    public void setTiDocumentoReferencia(String tiDocumentoReferencia) {
        this.tiDocumentoReferencia = tiDocumentoReferencia;
    }

    public String getTiReferenciaEstadoMQ() {
        return tiReferenciaEstadoMQ;
    }

    public void setTiReferenciaEstadoMQ(String tiReferenciaEstadoMQ) {
        this.tiReferenciaEstadoMQ = tiReferenciaEstadoMQ;
    }

    public String getTiTasa() {
        return tiTasa;
    }

    public void setTiTasa(String tiTasa) {
        this.tiTasa = tiTasa;
    }

    public String getVaIGVDocumentoProvision() {
        return vaIGVDocumentoProvision;
    }

    public void setVaIGVDocumentoProvision(String vaIGVDocumentoProvision) {
        this.vaIGVDocumentoProvision = vaIGVDocumentoProvision;
    }

    public double getVaTasaCambio() {
        return vaTasaCambio;
    }

    public void setVaTasaCambio(double vaTasaCambio) {
        this.vaTasaCambio = vaTasaCambio;
    }
    

    public ExportarConCad() {
        initBasic();
    }
    
    private void initBasic()
    {
//        this.setNombreTabla(nt);
//        this.setCampoClavePrimaria(COLUMNA_PK);
//        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    
    
    @Override
//    public String toString() {
//        return "tCambio{" + "nu_Sec_Tipo_Cambio=" + nuSecTipoCambio + ", fe_tipo_cambio=" + feTipoCambio + '}';
//    } 

    public int hashCode() {
        int hash = 0;
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExportarConCad)) {
            return false;
        }
        ExportarConCad other = (ExportarConCad) object;
        if ((this.primaryKey == null && other.primaryKey != null) || (this.primaryKey != null && !Arrays.equals(this.primaryKey, other.primaryKey))) {
            return false;
        }
        return true;
    }          
    
}
