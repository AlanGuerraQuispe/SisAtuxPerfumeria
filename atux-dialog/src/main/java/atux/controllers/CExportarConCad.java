package atux.controllers;

import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.ExportarConCad;
import java.sql.SQLException;
import java.util.ArrayList;


public class CExportarConCad extends JAbstractController{
    private ExportarConCad exportarConCad;

    public ArrayList getRegistros(String codigoCompania, String codigoLocal, String fechaIncio, String fechaFinal)
     {
        ArrayList<ExportarConCad> rgs = new ArrayList<ExportarConCad>();
        try {                        
            StringBuffer  query = new StringBuffer();
            query.append("select 'Sub-Diario' Sub_Diario, ");
            query.append("       to_char(tcp.fe_comprobante,'mm') || trim(to_char(rownum,'0000')) Numero, ");
            query.append("       tcp.fe_comprobante, ");
            query.append("       tc.co_moneda, ");
            query.append("       'Glosa Principal' Glosa_Principal, ");
            query.append("       tc.va_tasa_cambio, ");
            query.append("       'V' Tipo_Conversion, ");
            query.append("       'N' Flag_Conversion_Moneda, ");
            query.append("       to_char(tcp.fe_comprobante,'dd/mm/yyyy') Fecha_Tipo_Cambio, ");
            query.append("       'Cuenta Contable' Cuenta_Contable, ");
            query.append("       'Codigo_Anexo' Codigo_Anexo, ");
            query.append("       tc.co_local Codigo_Centro_Costo, ");
            query.append("       'D' Debe_Haber, ");
            query.append("       TO_CHAR(tcp.VA_TOTAL_PRECIO_VENTA,'00000.00') Importe_Original, ");
            query.append("       TO_CHAR((tcp.VA_TOTAL_PRECIO_VENTA/tc.va_tasa_cambio),'00000.00') Importe_Dolares, ");
            query.append("       TO_CHAR(tcp.VA_TOTAL_PRECIO_VENTA,'00000.00') Importe_Soles, ");
            query.append("       tcp.ti_comprobante Tipo_Documento, ");
            query.append("       tcp.nu_comprobante_pago Numero_Documento, ");
            query.append("       to_char(TCP.FE_COMPROBANTE, 'dd/mm/yyyy') Fecha_Documento, ");
            query.append("       'DD/MM/YYYY' Fecha_Vencimiento, ");
            query.append("       'Código_Area' Codigo_Area, ");
            query.append("       'Glosa_Detalle' Glosa_Detalle, ");
            query.append("       'Código_Anexo_Auxiliar' Codigo_Anexo_Auxiliar, ");
            query.append("       tpp.co_forma_pago Medio_Pago, ");
            query.append("       'Tipo_Documento_Referencia' Tipo_Documento_Referencia, ");
            query.append("       'Número_Documento_Referencia' Numero_Documento_Referencia, ");
            query.append("       'DD/MM/YYYY' Fecha_Documento_Referencia, ");
            query.append("       'Base_Imponible_Doc_Referencia' Base_Imponible_Doc_Referencia, ");
            query.append("       'IGV_Documento_Provisión' IGV_Documento_Provision, ");
            query.append("       'Tipo_Referencia_Estado_MQ' Tipo_Referencia_Estado_MQ, ");
            query.append("       'Numero_Serie_Caja_Reg' Numero_Serie_Caja_Reg, ");
            query.append("       to_char(tcp.fe_comprobante,'dd/mm/yyyy')  Fecha_Operacion, ");
            query.append("       'XXXXX' Tipo_Tasa, ");
            query.append("       '99999999999999.99' Tasa_Detraccion, ");
            query.append("       '99999999999999.99' Importe_Base_Detrac_Dolares, ");
            query.append("       '99999999999999.99' Importe_Base_Detrac_Soles ");
            query.append("  from vttc_pedido_venta tc, ");
            query.append("       vttd_pedido_venta td, ");
            query.append("       vttv_comprobante_pago tcp, ");
            query.append("       vttx_forma_pago_pedido tpp ");
            query.append(" where tc.co_compania = td.co_compania ");
            query.append("   and tc.co_local    = td.co_local ");
            query.append("   and tc.nu_pedido   = td.nu_pedido ");
            query.append("   and tc.co_compania = tcp.co_compania ");
            query.append("   and tc.co_local    = tcp.co_local ");
            query.append("   and tc.nu_pedido   = tcp.nu_pedido ");
            query.append("   and tc.co_compania = tpp.co_compania ");
            query.append("   and tc.co_local    = tpp.co_local ");
            query.append("   and tc.nu_pedido   = tpp.nu_pedido ");
            query.append("   and tc.co_compania = '").append(codigoCompania).append("'");
            query.append("   and tc.co_local    = '").append(codigoLocal).append("'");
            query.append("   and tc.fe_pedido between to_date('").append(fechaIncio).append(" 00:00:00','dd/mm/yyyy hh24:mi:ss')");
            query.append("                        and to_date('").append(fechaFinal).append(" 23:59:59','dd/mm/yyyy hh24:mi:ss')");
            query.append("   and td.in_producto_principal = 'S' ");
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next())
            {
                exportarConCad = new ExportarConCad();
                exportarConCad.setNuSubDiario(rs.getString("Sub_Diario"));
                exportarConCad.setNuNumero(rs.getString("Numero"));
                exportarConCad.setFeComprobante(rs.getString("fe_comprobante"));
                exportarConCad.setCoMoneda(rs.getString("co_moneda"));
                exportarConCad.setDeGlosaPrincipal(rs.getString("Glosa_Principal"));
                exportarConCad.setVaTasaCambio(rs.getString("va_tasa_cambio"));
                exportarConCad.setTiConversion(rs.getString("Tipo_Conversion"));
                exportarConCad.setInConversionMoneda(rs.getString("Flag_Conversion_Moneda"));
                exportarConCad.setFeTipoCambio(rs.getString("Fecha_Tipo_Cambio"));
                exportarConCad.setNuCuentaContable(rs.getString("Cuenta_Contable"));
                exportarConCad.setCoAnexo(rs.getString("Codigo_Anexo"));
                exportarConCad.setCoCentroCosto(rs.getString("Codigo_Centro_Costo"));
                exportarConCad.setInDebeHaber(rs.getString("Debe_Haber"));
                exportarConCad.setNuImporteOriginal(rs.getString("Importe_Original"));
                exportarConCad.setNuImporteDolares(rs.getString("Importe_Dolares"));
                exportarConCad.setNuImporteSoles(rs.getString("Importe_Soles"));
                exportarConCad.setTiDocumento(rs.getString("Tipo_Documento"));
                exportarConCad.setNuDocumento(rs.getString("Numero_Documento"));
                exportarConCad.setFeDocumento(rs.getString("Fecha_Documento"));
                exportarConCad.setFeVencimiento(rs.getString("Fecha_Vencimiento"));
                exportarConCad.setCoArea(rs.getString("Codigo_Area"));
                exportarConCad.setDeGlosaDetalle(rs.getString("Glosa_Detalle"));
                exportarConCad.setCoAnexoAuxiliar(rs.getString("Codigo_Anexo_Auxiliar"));
                exportarConCad.setInMedioPago(rs.getString("Medio_Pago"));
                exportarConCad.setTiDocumentoReferencia(rs.getString("Tipo_Documento_Referencia"));
                exportarConCad.setNuDocumentoReferencia(rs.getString("Numero_Documento_Referencia"));
                exportarConCad.setFeDocumentoReferencia(rs.getString("Fecha_Documento_Referencia"));
                exportarConCad.setBaImponibleDocReferencia(rs.getString("Base_Imponible_Doc_Referencia"));
                exportarConCad.setVaIGVDocumentoProvision(rs.getString("IGV_Documento_Provision"));
                exportarConCad.setTiReferenciaEstadoMQ(rs.getString("Tipo_Referencia_Estado_MQ"));
                exportarConCad.setNuSerieCajaReg(rs.getString("Numero_Serie_Caja_Reg"));
                exportarConCad.setFeOperacion(rs.getString("Fecha_Operacion"));
                exportarConCad.setTiTasa(rs.getString("Tipo_Tasa"));
                exportarConCad.setNuTasaDetraccion(rs.getString("Tasa_Detraccion"));
                exportarConCad.setNuImporteBaseDetracDolares(rs.getString("Importe_Base_Detrac_Dolares"));
                exportarConCad.setNuImporteBaseDetracSoles(rs.getString("Importe_Base_Detrac_Soles"));                
                rgs.add(exportarConCad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }    

    public ExportarConCad getExportarConCad() {
        if(exportarConCad == null)
        {
            exportarConCad = new ExportarConCad();
        }
        return exportarConCad;
    }

    public void setExportarConCad(JAbstractModelBD prv) {
        this.exportarConCad = (ExportarConCad)prv;
    }

    @Override
    public ArrayList getRegistros() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros(Object[] cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD getRegistro(JAbstractModelBD mdl, boolean opcion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JAbstractModelBD buscarRegistro(Object cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
