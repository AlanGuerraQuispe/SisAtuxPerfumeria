package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.ProductoInsumo;

import java.sql.SQLException;
import java.util.ArrayList;


public class CProductoInsumo extends JAbstractController{
    private ProductoInsumo prodInsumo;
   
    
    @Override
    public ArrayList<ProductoInsumo> getRegistros(Object[] op) {
        return this.getRegistros(new String[]{}, op!=null?new String[]{ProductoInsumo.COLUMNA_ACTIVO}:null,op);
    }
    
    public ArrayList<ProductoInsumo> getRegistros(String[] columna,Object[] valor) {
        return getRegistros(new String[]{},columna,valor);
    }       
     
    @Override
    public ArrayList<ProductoInsumo> getRegistros() {
        ArrayList<ProductoInsumo> rgs = new ArrayList<ProductoInsumo>();
        try {                        
//            rs = this.getRegistros(ProductoInsumo.nt,ProductoInsumo.FULL_NOM_CAMPOS);
            rs = this.getRegistros(ProductoInsumo.nt, ProductoInsumo.FULL_NOM_CAMPOS, null, null, ProductoInsumo.COLUMNA_ORDER);
            while(rs.next())
            {
                prodInsumo = new ProductoInsumo();
                prodInsumo.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRINCIPIO_ACTIVO")});
                prodInsumo.setCoCompania(rs.getString("CO_COMPANIA"));
                prodInsumo.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodInsumo.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
//                prodInsumo.setCoPrincipioActivo(rs.getString("CO_PRINCIPIO_ACTIVO"));
//                prodInsumo.setInPrincipioActivoPrincipal(rs.getString("IN_PRINCIPIO_ACTIVO_PRINCIPAL"));
//                prodInsumo.setDeObservacion(rs.getString("DE_OBSERVACION"));
//                prodInsumo.setEsProductoPrincipio(rs.getString("ES_PRODUCTO_PRINCIPIO"));
//                prodInsumo.setIdCreaProductoServicio(rs.getString("ID_CREA_PRODUCTO_SERVICIO"));
//                prodInsumo.setFeCreaProductoServicio(rs.getDate("FE_CREA_PRODUCTO_SERVICIO"));
//                prodInsumo.setIdModProductoServicio(rs.getString("ID_MOD_PRODUCTO_SERVICIO"));
//                prodInsumo.setFeModProductoServicio(rs.getDate("FE_MOD_PRODUCTO_SERVICIO"));
                rgs.add(prodInsumo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;    
    }
    
    public ProductoInsumo getRegistroPorPk(Object[] id)
    {
        try {            
            rs = this.selectPorPk(ProductoInsumo.nt, ProductoInsumo.FULL_NOM_CAMPOS, ProductoInsumo.COLUMNA_PK , id);
            while(rs.next())
            {
                prodInsumo = new ProductoInsumo();
                prodInsumo.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRODUCTO_INSUMO")});
                prodInsumo.setCoCompania(rs.getString("CO_COMPANIA"));
                prodInsumo.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodInsumo.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                prodInsumo.setCoProductoInsumo (rs.getString("CO_PRODUCTO_INSUMO"));
                prodInsumo.setInImpresion (rs.getString ("IN_IMPRESION"));
                prodInsumo.setDeObservacion(rs.getString("DE_OBSERVACION"));
                prodInsumo.setVaCosto(rs.getDouble ("VA_COSTO"));
                prodInsumo.setVaPrecioPublico (rs.getDouble ("VA_PRECIO_PUBLICO"));
                prodInsumo.setDeUnidad (rs.getString ("DE_UNIDAD_INSUMO"));
                prodInsumo.setEsProductoInsumo (rs.getString ("ES_PRODUCTO_INSUMO"));
                prodInsumo.setIdCreaProductoInsumo(rs.getString("ID_CREA_PRODUCTO_INSUMO"));
                prodInsumo.setFeCreaProductoInsumo(rs.getDate("FE_CREA_PRODUCTO_INSUMO"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return prodInsumo;
    }
    
    @Override
    public ArrayList<ProductoInsumo> getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<ProductoInsumo> rgs = new ArrayList<ProductoInsumo>();
        try {
            if(id != null){ 
               this.numRegistros = this.getNumeroRegistros(ProductoInsumo.nt, ProductoInsumo.COLUMNA_ACTIVO, ProductoInsumo.COLUMNA_ACTIVO, id);
            }else{
               this.numRegistros = this.getNumeroRegistros(ProductoInsumo.nt, ProductoInsumo.COLUMNA_ACTIVO);
               if(rs.isClosed()){
                   System.out.println("resultset esta cerrado...");
               }
            }
            rs = this.getRegistros(ProductoInsumo.nt, campos, columnaId, id, ProductoInsumo.COLUMNA_ORDER);
            if(this.numRegistros<finalPag)
           {
              finalPag =  this.numRegistros;
           }
            if(finalPag>inicioPag)
            {
                inicioPag -= (finalPag-inicioPag)-1;
            }
            while(rs.next())
            {
                prodInsumo= new ProductoInsumo();
                prodInsumo.setPrimaryKey(new String[]{rs.getString("Co_Compania"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRINCIPIO_ACTIVO")});
                prodInsumo.setCoCompania(rs.getString("CO_COMPANIA"));
                prodInsumo.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodInsumo.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
//                prodInsumo.setCoPrincipioActivo(rs.getString("CO_PRINCIPIO_ACTIVO"));
//                prodInsumo.setInPrincipioActivoPrincipal(rs.getString("IN_PRINCIPIO_ACTIVO_PRINCIPAL"));
//                prodInsumo.setDeObservacion(rs.getString("DE_OBSERVACION"));
//                prodInsumo.setEsProductoPrincipio(rs.getString("ES_PRODUCTO_PRINCIPIO"));
//                prodInsumo.setIdCreaProductoServicio(rs.getString("ID_CREA_PRODUCTO_SERVICIO"));
//                prodInsumo.setFeCreaProductoServicio(rs.getDate("FE_CREA_PRODUCTO_SERVICIO"));
//                prodInsumo.setIdModProductoServicio(rs.getString("ID_MOD_PRODUCTO_SERVICIO"));
//                prodInsumo.setFeModProductoServicio(rs.getDate("FE_MOD_PRODUCTO_SERVICIO"));
                rgs.add(prodInsumo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
        
    }

    public ArrayList getProductoInsumo(String CoCompania, String CoProducto, String Estado){
        ArrayList<ProductoInsumo> rgs = new ArrayList<ProductoInsumo>();
        ProductoInsumo prod      = null;
        StringBuffer  query;
        try {

            query = new StringBuffer();
            query.append("SELECT INS.CO_COMPANIA,  ");
            query.append("       INS.CO_PRODUCTO,  ");
            query.append("       INS.NU_REVISION_PRODUCTO,  ");
            query.append("       INS.CO_PRODUCTO_INSUMO,  ");
            query.append("       INS.ES_PRODUCTO_INSUMO,  ");
            query.append("       PRO.DE_PRODUCTO, ");
            query.append("       INS.DE_UNIDAD_INSUMO, ");
            query.append("       INS.VA_COSTO, ");
            query.append("       INS.VA_PRECIO_PUBLICO, ");
            query.append("       INS.IN_IMPRESION ");
            query.append("  FROM LGTM_PRODUCTO PRO, ");
            query.append("       LGTR_PRODUCTO_INSUMO INS ");
            query.append(" WHERE PRO.CO_COMPANIA=INS.CO_COMPANIA ");
            query.append(" AND PRO.CO_PRODUCTO  =INS.CO_PRODUCTO_INSUMO ");
            query.append("   and INS.CO_COMPANIA = '").append(CoCompania).append("'");
            query.append("   and INS.CO_PRODUCTO = '").append(CoProducto).append("' ");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next()){
                prodInsumo= new ProductoInsumo();
                prodInsumo.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PRODUCTO"), rs.getString("NU_REVISION_PRODUCTO"),rs.getString("CO_PRODUCTO_INSUMO")});
                prodInsumo.setCoCompania(rs.getString("CO_COMPANIA"));
                prodInsumo.setCoProducto(rs.getString("CO_PRODUCTO"));
                prodInsumo.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                prodInsumo.setCoProductoInsumo(rs.getString("CO_PRODUCTO_INSUMO"));
                prodInsumo.setEsProductoInsumo(rs.getString("ES_PRODUCTO_INSUMO"));
                prodInsumo.setDeProductoInsumo(rs.getString("DE_PRODUCTO"));
                prodInsumo.setDeUnidad(rs.getString("DE_UNIDAD_INSUMO"));
                prodInsumo.setVaCosto(rs.getDouble("VA_COSTO"));
                prodInsumo.setVaPrecioPublico(rs.getDouble("VA_PRECIO_PUBLICO"));
                prodInsumo.setInImpresion(rs.getString("IN_IMPRESION"));
                rgs.add(prodInsumo);
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            // Se cierran los recursos de base de datos.
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return rgs;
    }

    public ProductoInsumo getPreciosInsumo(String coCompania, String coProducto){
        StringBuffer  query;
        try {

            query = new StringBuffer();
            query.append("SELECT SUM(PRO.VA_COSTO_PRODUCTO)  VA_COSTO,  ");
            query.append("       SUM(PRO.VA_PRECIO_PUBLICO) VA_PRECIO_PUBLICO  ");
            query.append("  FROM LGTM_PRODUCTO PRO, ");
            query.append("       LGTR_PRODUCTO_INSUMO INS ");
            query.append(" WHERE PRO.CO_COMPANIA=INS.CO_COMPANIA ");
            query.append(" AND PRO.CO_PRODUCTO  =INS.CO_PRODUCTO_INSUMO ");
            query.append("   and INS.CO_COMPANIA = '").append(coCompania).append("'");
            query.append("   and INS.CO_PRODUCTO = '").append(coProducto).append("' ");

            rs =  this.getRegistrosSinFiltro(query);

            while(rs.next()){
                prodInsumo= new ProductoInsumo();
                prodInsumo.setCoCompania(coCompania);
                prodInsumo.setCoProducto(coProducto);
                prodInsumo.setVaCosto(rs.getDouble("VA_COSTO"));
                prodInsumo.setVaPrecioPublico(rs.getDouble("VA_PRECIO_PUBLICO"));
            }
            rs.close();
            BaseConexion.closeConnection();
            BaseConexion.setConnectionNull();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            // Se cierran los recursos de base de datos.
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("No ha podido cerrar ResultSet.");
            }
        }
        return prodInsumo;
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
        prodInsumo = (ProductoInsumo)mdl;
        int gravado = 0;
        String campos = ProductoInsumo.FULL_NOM_CAMPOS.toString();
        
        Object[] valores = {prodInsumo.getCoCompania(),
                            prodInsumo.getCoProducto(),
                            prodInsumo.getNuRevisionProducto(),
                            prodInsumo.getCoProductoInsumo(),
                            prodInsumo.getInImpresion(),
                            prodInsumo.getDeObservacion(),
                            prodInsumo.getVaCosto(),
                            prodInsumo.getVaPrecioPublico(),
                            prodInsumo.getDeProductoInsumo(),
                            prodInsumo.getEsProductoInsumo(),
                            prodInsumo.getIdCreaProductoInsumo(),
                            prodInsumo.getFeCreaProductoInsumo()
                           };

           gravado = this.agregarRegistroPs(prodInsumo.getNombreTabla(), ProductoInsumo.FULL_NOM_CAMPOS, valores);

        if(gravado==1)
            return true;
        
        return false;
    }

    public boolean eliminarRegistro(JAbstractModelBD mdl) throws SQLException {
        int gravado = 0;
        prodInsumo = (ProductoInsumo)mdl;
        gravado = this.eliminacionReal(prodInsumo);

        if(gravado==1){
            return true;
        }
        return false;
    }
    
    
    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        prodInsumo = (ProductoInsumo) mdl;
        int gravado = 0;

        Object[] valores = {
                prodInsumo.getInImpresion(),
                prodInsumo.getDeObservacion(),
                prodInsumo.getVaCosto(),
                prodInsumo.getVaPrecioPublico(),
                prodInsumo.getDeProductoInsumo(),
                prodInsumo.getEsProductoInsumo(),
                prodInsumo.getIdModProductoInsumo(),
                prodInsumo.getFeModProductoInsumo()
        };

        gravado = this.actualizarRegistroPs(prodInsumo.getNombreTabla(), this.adjuntarSimbolo(generarArrayAString(ProductoInsumo.CAMPOS_NO_ID), ",", "?")+Ex.WHERE+ unirColumnasValores(ProductoInsumo.COLUMNA_PK, prodInsumo.getPrimaryKey()) , valores);
        return gravado;
    }

    public ProductoInsumo getProductoInsumo() {
        if(prodInsumo == null)
        {
            prodInsumo = new ProductoInsumo();
        }
        return prodInsumo;
    }

    public void setProductoInsumo(JAbstractModelBD prv) {
        this.prodInsumo = (ProductoInsumo)prv;
    }
}
