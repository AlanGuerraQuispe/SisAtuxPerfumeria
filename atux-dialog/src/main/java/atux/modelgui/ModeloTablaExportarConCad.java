package atux.modelgui;

import atux.controllers.CExportarConCad;
import atux.modelbd.ExportarConCad;

public class ModeloTablaExportarConCad extends ModeloTabla{

    String[] columnas = {"Sub Diario", "Número", "Fecha Comprobante", "Codigo Moneda", "Glosa Principal", "Tasa cambio", "Tipo Conversion", "Flag Conversion Moneda", "Fecha Tipo Cambio","Cuenta Contable", "Codigo Anexo", "Codigo Centro Costo", "Debe Haber", "Importe Original", "Importe Dolares", "Importe Soles","Tipo Documento", "Numero Documento", "Fecha Documento", "Fecha Vencimiento","Codigo Area", "Glosa Detalle", "Codigo Anexo Auxiliar", "Medio Pago","Tipo Documento Referencia", "Numero Documento Referencia", "Fecha Documento Referencia", "Base Imponible Doc Referencia", "IGV Documento Provision", "Tipo Referencia Estado MQ", "Numero Serie Caja Reg", "Fecha Operacion","Tipo Tasa", "Tasa Detraccion", "Importe Base Detrac Dolares", "Importe Base Detrac Soles"};
    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;    
    public static final Integer[] anchoColumnas  = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
    
    public ModeloTablaExportarConCad() {
        cc = new CExportarConCad();
        this.nombreColumnas = columnas;       
        registros = ((CExportarConCad)cc).getRegistros();
    }
    
    public ModeloTablaExportarConCad(int opcion) {
        cc = new CExportarConCad();
        this.nombreColumnas = columnas;       
        registros = ((CExportarConCad)cc).getRegistros();
    }
    
    public ModeloTablaExportarConCad(String codigoCompania, String codigoLocal, String fechaIncio, String fechaFinal) {
        cc = new CExportarConCad();
        this.nombreColumnas = columnas;
//        cc.setNumPaginador(inicio, finalPag);
        registros = ((CExportarConCad)cc).getRegistros(codigoCompania, codigoLocal, fechaIncio, fechaFinal);
    }
    
    public ModeloTablaExportarConCad(int opcion,int inicio,int finalPag) {
        cc = new CExportarConCad();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CExportarConCad)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CExportarConCad)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CExportarConCad)cc).getRegistros(null);
        }
        
    }
    
    public int getCantidadRegistros()
    {
        return cc.getCantidadRegistros();
    }  

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {        
        switch(columnIndex)
        {
            case  0: return ((ExportarConCad)registros.get(rowIndex)).getNuSubDiario();
            case  1: return ((ExportarConCad)registros.get(rowIndex)).getNuNumero();
            case  2: return ((ExportarConCad)registros.get(rowIndex)).getFeComprobante();
            case  3: return ((ExportarConCad)registros.get(rowIndex)).getCoMoneda();
            case  4: return ((ExportarConCad)registros.get(rowIndex)).getDeGlosaPrincipal();
            case  5: return ((ExportarConCad)registros.get(rowIndex)).getVaTasaCambio();
            case  6: return ((ExportarConCad)registros.get(rowIndex)).getTiConversion();
            case  7: return ((ExportarConCad)registros.get(rowIndex)).getInConversionMoneda();
            case  8: return ((ExportarConCad)registros.get(rowIndex)).getFeTipoCambio();
            case  9: return ((ExportarConCad)registros.get(rowIndex)).getNuCuentaContable();
            case 10: return ((ExportarConCad)registros.get(rowIndex)).getCoAnexo();
            case 11: return ((ExportarConCad)registros.get(rowIndex)).getCoCentroCosto();
            case 12: return ((ExportarConCad)registros.get(rowIndex)).getInDebeHaber();
            case 13: return ((ExportarConCad)registros.get(rowIndex)).getNuImporteOriginal();
            case 14: return ((ExportarConCad)registros.get(rowIndex)).getNuImporteDolares();
            case 15: return ((ExportarConCad)registros.get(rowIndex)).getNuImporteSoles();
            case 16: return ((ExportarConCad)registros.get(rowIndex)).getTiDocumento();
            case 17: return ((ExportarConCad)registros.get(rowIndex)).getNuDocumento();
            case 18: return ((ExportarConCad)registros.get(rowIndex)).getFeDocumento();
            case 19: return ((ExportarConCad)registros.get(rowIndex)).getFeVencimiento();
            case 20: return ((ExportarConCad)registros.get(rowIndex)).getCoArea();
            case 21: return ((ExportarConCad)registros.get(rowIndex)).getDeGlosaDetalle();
            case 22: return ((ExportarConCad)registros.get(rowIndex)).getCoAnexoAuxiliar();
            case 23: return ((ExportarConCad)registros.get(rowIndex)).getInMedioPago();
            case 24: return ((ExportarConCad)registros.get(rowIndex)).getTiDocumentoReferencia();
            case 25: return ((ExportarConCad)registros.get(rowIndex)).getNuDocumentoReferencia();
            case 26: return ((ExportarConCad)registros.get(rowIndex)).getFeDocumentoReferencia();
            case 27: return ((ExportarConCad)registros.get(rowIndex)).getBaImponibleDocReferencia();
            case 28: return ((ExportarConCad)registros.get(rowIndex)).getVaIGVDocumentoProvision();
            case 29: return ((ExportarConCad)registros.get(rowIndex)).getTiReferenciaEstadoMQ();
            case 30: return ((ExportarConCad)registros.get(rowIndex)).getNuSerieCajaReg();
            case 31: return ((ExportarConCad)registros.get(rowIndex)).getFeOperacion();
            case 32: return ((ExportarConCad)registros.get(rowIndex)).getTiTasa();
            case 33: return ((ExportarConCad)registros.get(rowIndex)).getNuTasaDetraccion();
            case 34: return ((ExportarConCad)registros.get(rowIndex)).getNuImporteBaseDetracDolares();
            case 35: return ((ExportarConCad)registros.get(rowIndex)).getNuImporteBaseDetracSoles();
            default: return null;   
        }
    }
    
}
