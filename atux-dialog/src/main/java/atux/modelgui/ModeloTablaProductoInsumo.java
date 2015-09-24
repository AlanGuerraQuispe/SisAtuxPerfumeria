package atux.modelgui;

import atux.controllers.CProductoInsumo;
import atux.modelbd.ProductoInsumo;

public class ModeloTablaProductoInsumo extends ModeloTabla{

    String[] columnas = {"Código","Descripción","Unidad","Costo","Precio","Imprime?"};
    public static final Integer[] anchoColumnas  = {60,160,80,30,40,40};

    public static int TODOS = -1;
    public static int ACTIVOS = 1;
    public static int NO_ACTIVOS = 0;
    public static String[] Columnas_Sin_Estado = new String[]{"CO_COMPANIA", "CO_PRODUCTO", "NU_REVISION_PRODUCTO"};
    public static String[] Columnas_Con_Estado = new String[]{"CO_COMPANIA", "CO_PRODUCTO", "NU_REVISION_PRODUCTO","ES_PRODUCTO_PRINCIPIO"};
    

    public ModeloTablaProductoInsumo() {
        cc = new CProductoInsumo();
        this.nombreColumnas = columnas;       
        registros = ((CProductoInsumo)cc).getRegistros();
    }
    
    public ModeloTablaProductoInsumo(int opcion) {
        cc = new CProductoInsumo();
        this.nombreColumnas = columnas;       
        registros = ((CProductoInsumo)cc).getRegistros();
    }
    
    public ModeloTablaProductoInsumo(String[] campo, Object[] valor) {
        cc = new CProductoInsumo();
        this.nombreColumnas = columnas;       
        registros = ((CProductoInsumo)cc).getRegistros(campo,valor);
    }
    
    public ModeloTablaProductoInsumo(String Compania, String Producto, String Estado) {
        cc = new CProductoInsumo();
        this.nombreColumnas = columnas;
        registros = ((CProductoInsumo)cc).getProductoInsumo(Compania, Producto, Estado);

    }

    
    public ModeloTablaProductoInsumo(int inicio, int finalPag) {
        cc = new CProductoInsumo();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        registros = ((CProductoInsumo)cc).getRegistros(null);
    }
    public ModeloTablaProductoInsumo(int opcion, int inicio, int finalPag) {
        cc = new CProductoInsumo();
        this.nombreColumnas = columnas;
        cc.setNumPaginador(inicio, finalPag);
        switch(opcion)
        {
            case 0: 
                registros = ((CProductoInsumo)cc).getRegistros(new Object[]{new String("I")});
                break;
            case 1:
                registros = ((CProductoInsumo)cc).getRegistros(new Object[]{new String("A")});
                break;
            default:
                registros = ((CProductoInsumo)cc).getRegistros(null);
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
            case 0: return ((ProductoInsumo)registros.get(rowIndex)).getCoProductoInsumo();
            case 1: return ((ProductoInsumo)registros.get(rowIndex)).getDeProductoInsumo();
            case 2: return ((ProductoInsumo)registros.get(rowIndex)).getDeUnidad();
            case 3: return ((ProductoInsumo)registros.get(rowIndex)).getVaCosto();
            case 4: return ((ProductoInsumo)registros.get(rowIndex)).getVaPrecioPublico();
            case 5: return ((ProductoInsumo)registros.get(rowIndex)).getInImpresion();
            default: return null;   
        }
    }
}
