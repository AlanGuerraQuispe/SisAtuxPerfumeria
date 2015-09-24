package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.modelbd.DetallePedidoVenta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CDetallePedidoVenta extends JAbstractController{

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

     public ArrayList<DetallePedidoVenta> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }
     
     public ArrayList<DetallePedidoVenta> getRegistrosPorPedidoVenta(String[] pk) {
        return this.getRegistros(new String[]{}, new String[]{"CO_COMPANIA","CO_LOCAL","NU_PEDIDO"},pk);
    }



    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        DetallePedidoVenta detPedVenta = (DetallePedidoVenta)mdl;
        int gravado = 0;
        String[] campos = DetallePedidoVenta.CAMPOS_INSERTS;
        
        Object[] valores = {detPedVenta.getIdPedidoVenta().getCoCompania(),
                            detPedVenta.getIdPedidoVenta().getCoLocal(),
                            detPedVenta.getIdPedidoVenta().getNuPedido(),
                            detPedVenta.getNuItemPedido(),
                            detPedVenta.getIdPedidoVenta().getCoVendedor(),
                            detPedVenta.getCoProducto(),
                            detPedVenta.getNuRevisionProducto(),
                            detPedVenta.getVaVenta(),
                            detPedVenta.getVaPrecioPublico(),
                            detPedVenta.getCaAtendida(),
                            detPedVenta.getVaPrecioVenta(),
                            detPedVenta.getInProductoFraccion(),
                            detPedVenta.getVaFraccion(),
                            detPedVenta.getCoImpuesto_1(),
                            detPedVenta.getPcImpuesto_1(),
                            detPedVenta.getPcDescuento_1(),                            
                            detPedVenta.getDeUnidadProducto(),
                            detPedVenta.getVaBono(),
                            detPedVenta.getEsDetPedidoVenta(),
                            detPedVenta.getIdCreaDetPedidoVenta(),
                            detPedVenta.getFeCreaDetPedidoVenta(),
                            detPedVenta.getPcDescuentoConvenio(),
                            detPedVenta.getPcDescuentoBaseLocal(),
                            detPedVenta.getVaPrecioOriginal(),
                            detPedVenta.getVaPrecioPromedio(),
                            detPedVenta.getCoProductoPrincipal(),
                            detPedVenta.getInProductoPrincipal()
            };
       
           gravado = this.agregarRegistroPs(DetallePedidoVenta.nt, campos, valores);
       
        if(gravado==1)
        {          
            detPedVenta.getProdLocal();
        }
        return false;
    }
    
    private Integer getUltimoPk() throws SQLException
    {
        ResultSet rsTmp = this.getUltimoRegistro(DetallePedidoVenta.nt, "iddetallec");
        Integer pk=null;
        try{
            while(rsTmp.next())
            {
               pk =  rsTmp.getInt(1);
            }
        }catch(SQLException ex){ex.printStackTrace();}
        return pk;
    }
    
    

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        DetallePedidoVenta detPedVenta = (DetallePedidoVenta)mdl;
        int gravado = 0;
        String campos = DetallePedidoVenta.CAMPOS_INSERTS.toString();
        
        Object[] valores = {detPedVenta.getIdPedidoVenta().getPrimaryKey(),
                            detPedVenta.getProdLocal().getPrimaryKey(),
                            detPedVenta.getCaAtendida(),
                            detPedVenta.getVaVenta(),
                            detPedVenta.getDeUnidadProducto(),
                            detPedVenta.getPrimaryKey()};
       
           gravado = this.actualizarRegistroPs(DetallePedidoVenta.nt, this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+DetallePedidoVenta.COLUMNA_PK+" = ? ", valores);
               
        return gravado;
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
       ArrayList<DetallePedidoVenta> rgs = new ArrayList<DetallePedidoVenta>();        
        try {            
            rs = this.getRegistros(DetallePedidoVenta.nt,DetallePedidoVenta.CAMPOS_INSERTS,columnaId,id,null);
      
            DetallePedidoVenta detPedVenta = null;            
            while(rs.next())
            {
                   detPedVenta = new DetallePedidoVenta();
                   detPedVenta.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("NU_PEDIDO")});
                   detPedVenta.setCoCompania(rs.getString("CO_COMPANIA"));
                   detPedVenta.setCoLocal(rs.getString("CO_LOCAL"));
                   detPedVenta.setNuPedido(rs.getString("NU_PEDIDO"));
                   detPedVenta.setNuItemPedido(rs.getInt("NU_ITEM_PEDIDO"));
                   detPedVenta.setCoVendedor(rs.getString("CO_VENDEDOR"));
                   detPedVenta.setCoProducto(rs.getString("CO_PRODUCTO"));
                   detPedVenta.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                   detPedVenta.setProdLocal(new CProductoLocal().getRegistro(new Object[]{rs.getString("CO_COMPANIA"),rs.getString("CO_LOCAL"),rs.getString("CO_PRODUCTO"),rs.getString("NU_REVISION_PRODUCTO")}));

                   if(rs.getString("IN_PRODUCTO_PRINCIPAL").equals ("N"))
                        detPedVenta.setProductoInsumo (new CProductoInsumo ().getRegistroPorPk (new Object[]{rs.getString ("CO_COMPANIA"), rs.getString ("CO_PRODUCTO_PRINCIPAL"), rs.getString ("NU_REVISION_PRODUCTO"), rs.getString ("CO_PRODUCTO")}));

                   detPedVenta.setVaVenta(rs.getDouble("VA_VENTA"));
                   detPedVenta.setVaPrecioPublico(rs.getDouble("VA_PRECIO_PUBLICO"));
                   detPedVenta.setCaAtendida(rs.getInt("CA_ATENDIDA"));
                   detPedVenta.setVaPrecioVenta(rs.getDouble("VA_PRECIO_VENTA"));
                   detPedVenta.setInProductoFraccion(rs.getString("IN_PRODUCTO_FRACCION"));
                   detPedVenta.setVaFraccion(rs.getInt("VA_FRACCION"));
                   detPedVenta.setCoImpuesto_1(rs.getString("CO_IMPUESTO_1"));
                   detPedVenta.setPcImpuesto_1(rs.getDouble("PC_IMPUESTO_1"));
                   detPedVenta.setPcDescuento_1(rs.getDouble("PC_DESCUENTO_1"));
                   detPedVenta.setDeUnidadProducto(rs.getString("DE_UNIDAD_PRODUCTO"));
                   detPedVenta.setVaBono(rs.getDouble("VA_BONO"));
                   detPedVenta.setEsDetPedidoVenta(rs.getString("ES_DET_PEDIDO_VENTA"));
                   detPedVenta.setIdCreaDetPedidoVenta(rs.getString("ID_CREA_DET_PEDIDO_VENTA"));
                   detPedVenta.setFeCreaDetPedidoVenta(rs.getDate("FE_CREA_DET_PEDIDO_VENTA"));
                   detPedVenta.setPcDescuentoConvenio(rs.getDouble("PC_DESCUENTO_CONVENIO"));
                   detPedVenta.setPcDescuentoBaseLocal(rs.getDouble("PC_DESCUENTO_BASE_LOCAL"));
                   detPedVenta.setVaPrecioOriginal(rs.getDouble("VA_PRECIO_ORIGINAL"));
                   detPedVenta.setCoProductoPrincipal(rs.getString("CO_PRODUCTO_PRINCIPAL"));
                   detPedVenta.setInProductoPrincipal(rs.getString("IN_PRODUCTO_PRINCIPAL"));
                   rgs.add(detPedVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
    
}
