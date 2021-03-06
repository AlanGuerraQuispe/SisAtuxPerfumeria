package atux.modelgui;

import atux.vistas.buscar.BuscarProducto;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.venta.*;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import atux.util.*;

import static com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary.formatNumber;

public class ModeloTomaPedidoVenta extends ModeloTabla{

    private Class[] tipoColumnas;
    private Double mBruto    = 0.0;
    private Double mDscto    = 0.0;
    private Double mAfecto   = 0.0;
    private Double mImpuesto = 0.0;    
    private Double mRedondeo = 0.0;    
    private Double mTotalPreVenta = 0.0;
    private Integer numItems = 0;

    public final static int GENERACION_PEDIDO = 1;

    public static final Integer[] anchoColumnas  = {50,240,90,60,50,60,
                                                    50,60,160};
    
    private int tipoTabla;

    public ModeloTomaPedidoVenta(ArrayList registros) {
        super(registros);
    }

    public ModeloTomaPedidoVenta(int tipoColumna) {
        this.registros = new ArrayList<DetallePedidoVenta>();
        DetallePedidoVenta dp = new DetallePedidoVenta();
        dp.setProdLocal(new ProductoLocal());
        this.registros.add(dp);
        this.nombreColumnas = new String[]{"Producto Seleccionado","Unidad","Cantidad","Precio",
                                           "Importe","Accion"};
        if(tipoColumna ==1) {
            tipoColumnas = new Class[]{String.class, String.class, Integer.class, Double.class,
                    Double.class, PanelAccionProdInsumos.class};
        }
        else{
            tipoColumnas = new Class[]{String.class, String.class, Integer.class, Double.class,
                    Double.class, PanelAccionProdInsumos.class};
        }
        this.tipoTabla = GENERACION_PEDIDO;
    }   
        
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return tipoColumnas[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        DetallePedidoVenta dp = (DetallePedidoVenta)this.registros.get(rowIndex);

        if(StringUtils.isNotBlank(dp.getCoVentaPromocion())) return false;

        if(dp.getProdLocal().getPrimaryKey() != null)
        {
            if(columnIndex==2 || columnIndex==5)
            {
                return true;
            }
        }else if(dp.getProdLocal().getPrimaryKey() == null)
        {
            if(columnIndex == this.nombreColumnas.length-1)
            {
                return true;
            }
        }
        return false;
    }

    public void setValueAt(IPedidoVentaInsumo pedidoVenta,Object aValue, int rowIndex, int columnIndex)
    {
        this.setValueAt(aValue, rowIndex, columnIndex);
        pedidoVenta.setTotales();
    }

    public void setValueAt(ICompletarPedidoVenta pedidoVenta,Object aValue, int rowIndex, int columnIndex)
    {
        this.setValueAt(aValue, rowIndex, columnIndex);
        pedidoVenta.setTotales();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {        
        DetallePedidoVenta dp = (DetallePedidoVenta)this.registros.get(rowIndex);
        if(dp.getProdLocal().getPrimaryKey() != null)
        {
                switch(columnIndex)
               {
                   case 2:
                       ((DetallePedidoVenta)registros.get(rowIndex)).setCaAtendida(Integer.parseInt(aValue.toString()));
                       break;
                   case 4:
                       ((DetallePedidoVenta)registros.get(rowIndex)).setVaPrecioVenta((Double)aValue);
                       break;
               }
                
               this.mBruto = getBruto(); 
               this.fireTableCellUpdated(rowIndex, columnIndex);           
        }
    }
    
       
    public void agregar(ProductoLocal prd)
    {
        DetallePedidoVenta dc = new DetallePedidoVenta();
        dc.setProdLocal(prd);       
        this.registros.add(dc);               
        this.mBruto = getBruto();  
        contarItems();
        this.fireTableRowsInserted(this.registros.size(), this.registros.size());
    }

    public void fireTableRowsInserted(){
        this.fireTableRowsInserted(this.registros.size(), this.registros.size());
    }

    public void remplazarProducto(ProductoLocal prd,int index)
    {
        DetallePedidoVenta dc = new DetallePedidoVenta();
        dc.setProdLocal(prd); 
        registros.remove(index);
        this.agregar(prd);               
    }
    
    @Override
    public void quitarFila(int fila)
    {
        registros.remove(fila);
    }
    
    public boolean existe(ProductoLocal prd)
    {
        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc = (DetallePedidoVenta)obj;
            if(dc.getProdLocal().getPrimaryKey() != null)
            {
                if(dc.getProdLocal().equals(prd))
                {
                    return true;                   
                }
            }
        }
        return false;
    }

    public Double aplicaDescuento(String descEsp){
        int cantPedida = evaluarCantidadPedida();

        if(cantPedida==1)  //Un perfume
        {
            return AtuxVariables.vDescuentox1;
        }

            if (descEsp=="No" && cantPedida==2){
                return AtuxVariables.vDescuentox2;
            }else{
                if (cantPedida%2==0){
                    int multiplo=0;
                    multiplo = cantPedida / 2;
                    return (AtuxVariables.vDescuentox2a * multiplo);
                }
//                return AtuxVariables.vDescuentox2a;
            }


        if(cantPedida==3) //Tres
        {
                return AtuxVariables.vDescuentox3;
        }

        if(cantPedida>=3) //Tres o mas perfumes
        {
            double pTotal =AtuxVariables.vDescuentoMas3 * cantPedida;
            String myTotal = AtuxUtility.formatNumber(pTotal);
            int myRedondeo = Integer.parseInt(myTotal.substring(myTotal.length() - 1, myTotal.length()));
            if (myRedondeo==5){
                return (AtuxVariables.vDescuentoMas3 * cantPedida) - 0.05;
            }else{
                return (AtuxVariables.vDescuentoMas3 * cantPedida) ;
            }
        }

        return 0d;
    }

    private int evaluarCantidadPedida () {
        int tmpCantidad = 0;

        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc = (DetallePedidoVenta)obj;
            if(dc.getCoCompania()!=null)
                tmpCantidad= tmpCantidad + dc.getCaAtendida();
        }

        return tmpCantidad;
    }

    /**
       DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
        if(dc2.getProdLocal().getPrimaryKey() != null)
        {
           Double bruto = AtuxUtility.getDecimalNumberRedondeado((dc2.getVaVenta()/((dc2.getPcImpuesto_1()/100) + 1))*dc2.getCaAtendida());
           tmpBruto += bruto;
        }
     */
    public Double getBruto()
    {
        Double tmpBruto = 0.0;
        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
            if(dc2.getProdLocal().getPrimaryKey() != null)
            {
                Double bruto = AtuxUtility.getDecimalNumberRedondeado(dc2.getProdLocal().getVaVenta()*dc2.getCaAtendida());
                tmpBruto += bruto;
                for(Object objComponente:dc2.getProdLocal().getInsumosProducto())
                {
                    ProductoLocal pro = (ProductoLocal)objComponente;
                    if (pro.getInImpresion().equals("S")){
                        bruto = AtuxUtility.getDecimalNumberRedondeado(pro.getVaVenta()*dc2.getCaAtendida());
                        tmpBruto += bruto;
                    }
                }
            }
        }
        this.mBruto = tmpBruto;

//        logger.info(tmpBruto );
        return this.mBruto;
    }
    
    public Double getTotalDescuento(String descEsp)
    {
        Double tmpDsc = 0.0;
        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
            if(dc2.getProdLocal().getPrimaryKey() != null)
            {                
                Double descuento = AtuxUtility.getDecimalNumberRedondeado(dc2.getVaPrecioVenta()/((dc2.getPcImpuesto_1()/100) + 1));
                tmpDsc += descuento;
            }
        }
                
//      this.mDscto = this.mBruto - tmpDsc ;
        this.mDscto = aplicaDescuento(descEsp) ;
        return this.mDscto;
    }


    public Double getAfecto()
    {                
        this.mAfecto = this.mTotalPreVenta - mImpuesto;
        //this.mAfecto = this.mBruto;
        return this.mAfecto;
    }
    
//    public Double getTotalImpuesto()
//    {
//        Double tmpImp = 0.0;
//        for(Object obj:this.registros)
//        {
//            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
//            if(dc2.getProdLocal().getPrimaryKey() != null)
//            {
//                Double tmpVenta  = this.mBruto;
//                Double descuento = AtuxUtility.getDecimalNumberRedondeado(tmpVenta/((dc2.getPcImpuesto_1()/100) + 1));
//                Double impuesto  = AtuxUtility.getDecimalNumberRedondeado(tmpVenta - descuento);
//                tmpImp = impuesto;
//
//            }
//        }
//
//        this.mImpuesto = tmpImp;
//        return this.mImpuesto;
//    }

    public Double getTotalImpuesto()
    {
        Double tmpImp = 0.0;
        Double mIGV = 0.0;
        for(Object obj:this.registros)
        {
            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
            if(dc2.getProdLocal().getPrimaryKey() != null)
            {
                mIGV = ((dc2.getPcImpuesto_1()/100) + 1);
                Double tmpVenta  = dc2.getVaPrecioVenta();
                Double descuento = AtuxUtility.getDecimalNumberRedondeado(dc2.getVaPrecioVenta()/((dc2.getPcImpuesto_1()/100) + 1));
                Double impuesto  = AtuxUtility.getDecimalNumberRedondeado(tmpVenta - descuento);
                tmpImp += impuesto;
            }
        }
        this.mImpuesto = tmpImp;
        if (mAfecto ==0){
            this.mImpuesto=0.0;
        }else{
            this.mImpuesto = mTotalPreVenta - (mTotalPreVenta / mIGV);
        }

        return this.mImpuesto;
    }

    public Double getTotalPrecioVenta()
    {            
//        Double tmpTotPreVenta = (mBruto + mImpuesto - mDscto) + getRedondeo();
        Double tmpTotPreVenta = (mBruto -  mDscto);

        this.mTotalPreVenta = tmpTotPreVenta;
        return this.mTotalPreVenta;
    }
    
    public Double getRedondeo()
    {            
        //Double tmpRedondeo = AtuxUtility.getRedondeo(mBruto + mImpuesto - mDscto);
        Double tmpRedondeo = AtuxUtility.getRedondeo(mBruto - (mAfecto + mImpuesto));
//        Double tmpRedondeo = mBruto - (mAfecto + mImpuesto);
        this.mRedondeo = tmpRedondeo;
        return this.mRedondeo;
    }
    
    public void contarItems()
    {
        numItems = 0;
        for(Object obj:this.registros)
        {            
            DetallePedidoVenta dc2 = (DetallePedidoVenta)obj;
            if(dc2.getProdLocal().getPrimaryKey() != null)
            {                
                numItems++;
            }
        }
    }
    
    public Integer getNumItems()
    {
        return numItems;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(tipoTabla)
        {
            case GENERACION_PEDIDO: 
                switch(columnIndex)
                 {
                    case 0: if(((DetallePedidoVenta)registros.get(rowIndex)).getCoCompania()==null){
                       return ""; 
                        }
                        else
                       return ((DetallePedidoVenta)registros.get(rowIndex)).getProdLocal().getProducto().getDeProducto(); 
                    case 1:return ((DetallePedidoVenta)registros.get(rowIndex)).getDeUnidadProducto();    
                    case 2:return ((DetallePedidoVenta)registros.get(rowIndex)).getCaAtendida();
                    case 3:{
                        if((StringUtils.isNotBlank(((DetallePedidoVenta)registros.get(rowIndex)).getCoVentaPromocion()))){
                            if( ((DetallePedidoVenta)registros.get(rowIndex)).getCaAtendida()==null) return null;
                            else return 0;
                        }else{
                            return ((DetallePedidoVenta)registros.get(rowIndex)).getProdLocal().getVaPrecioPublico();
                        }
                    }
                    case 4:return ((DetallePedidoVenta)registros.get(rowIndex)).getVaPrecioVenta();
                    case 5:return "Agregar/Eliminar";
                 }    
                break;  
                default:return null;    
        }
        return null;
    }
    
    public void limpiar()
    {
        registros.clear();
        DetallePedidoVenta dc = new DetallePedidoVenta();
        dc.setProdLocal(new ProductoLocal());
        registros.add(dc);
    }
    
    public ArrayList<DetallePedidoVenta> getDetallesPedidoVenta()
    {
        ArrayList<DetallePedidoVenta> dp = new ArrayList<DetallePedidoVenta>();
        for(Object obj:this.registros)
        {            
            DetallePedidoVenta dp2 = (DetallePedidoVenta)obj;
            if(dp2.getProdLocal().getPrimaryKey() != null)
            {                
                dp.add(dp2);
            }
        }
        return dp;
    }

}
