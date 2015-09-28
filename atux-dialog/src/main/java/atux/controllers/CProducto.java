package atux.controllers;

import atux.core.Ex;
import atux.core.JAbstractController;
import atux.core.JAbstractModelBD;
import atux.managerbd.BaseConexion;
import atux.modelbd.Laboratorio;
import atux.modelbd.Producto;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxVariables;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class CProducto extends JAbstractController {
    
    private Producto prd;
    private InputStream itmp;

    
    public void setProducto(JAbstractModelBD prv) {
        this.prd = (Producto)prv;
    }    
    
    public Producto getProducto() {
        if(prd == null)
        {
            prd = new Producto();
        }
        return prd;
    }
    
    @Override
    public ArrayList getRegistros(Object[] cvl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
   @Override
    public ArrayList getRegistros() {
       ArrayList<Producto> rgs = new ArrayList<Producto>();
       Producto      prod      = null;
       StringBuffer  query;
       try {
           query = new StringBuffer();

           query.append("select T1.*  ");
           query.append("  from LGTM_PRODUCTO T1 ");
           query.append(" WHERE T1.ES_PRODUCTO = 'A' ");
           query.append("   AND T1.CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'\n");
           query.append("   AND T1.TI_MATERIAL_SAP='INSU' ");
           query.append(" ORDER BY DE_PRODUCTO ");

           rs =  this.getRegistrosSinFiltro(query);

           while(rs.next()){
               prod = new Producto();
               prod.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PRODUCTO"),rs.getString("NU_REVISION_PRODUCTO")});
               prod.setCoCompania(rs.getString("CO_COMPANIA"));
               prod.setCoProducto(rs.getString("CO_PRODUCTO"));
               prod.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
               prod.setDeCortaProducto(rs.getString("DE_CORTA_PRODUCTO"));
               prod.setDeProducto(rs.getString("DE_PRODUCTO"));
               prod.setCoFactorPrecio(rs.getString("CO_FACTOR_PRECIO"));
               prod.setCoMoneda(rs.getString("CO_MONEDA"));
               prod.setCoImpuesto1(rs.getString("CO_IMPUESTO_1"));
               prod.setCoImpuesto2(rs.getString("CO_IMPUESTO_2"));
               prod.setCoLaboratorio(rs.getString("CO_LABORATORIO"));
               prod.setCoLineaProducto(rs.getString("CO_LINEA_PRODUCTO"));
               prod.setCoGrupoProducto(rs.getString("CO_GRUPO_PRODUCTO"));
               prod.setCoCategoriaCcr(rs.getString("CO_CATEGORIA_CCR"));
               prod.setCoClasificacionIms(rs.getString("CO_CLASIFICACION_IMS"));
               prod.setCoCategoriaSb(rs.getString("CO_CATEGORIA_SB"));
               prod.setCoSubcategoriaSb(rs.getString("CO_SUBCATEGORIA_SB"));
               prod.setCoUnidadMedida(rs.getString("CO_UNIDAD_MEDIDA"));
               prod.setVaUnidadMedida(rs.getInt("VA_UNIDAD_MEDIDA"));
               prod.setCoUnidadCompra(rs.getString("CO_UNIDAD_COMPRA"));
               prod.setCoUnidadVenta(rs.getString("CO_UNIDAD_VENTA"));
               prod.setCoUnidadContenido(rs.getString("CO_UNIDAD_CONTENIDO"));
               prod.setVaUnidadContenido(rs.getInt("VA_UNIDAD_CONTENIDO"));
               prod.setVaPrecioCompra(rs.getDouble("VA_PRECIO_COMPRA"));
               prod.setVaPrecioPromedio(rs.getDouble("VA_PRECIO_PROMEDIO"));
               prod.setVaBono(rs.getDouble("VA_BONO"));
               prod.setPcBono(rs.getDouble("PC_BONO"));
               prod.setDeUnidadProducto(rs.getString("DE_UNIDAD_PRODUCTO"));
               prod.setEsProducto(rs.getString("ES_PRODUCTO"));
               prod.setIdCreaProducto(rs.getString("ID_CREA_PRODUCTO"));
               prod.setFeCreaProducto(rs.getDate("FE_CREA_PRODUCTO"));
               prod.setIdModProducto(rs.getString("ID_MOD_PRODUCTO"));
               prod.setFeModProducto(rs.getDate("FE_MOD_PRODUCTO"));
               prod.setCoClaseVenta(rs.getString("CO_CLASE_VENTA"));
               prod.setCoGrupoProdErp(rs.getString("CO_GRUPO_PROD_ERP"));
               prod.setCoLineaProdErp(rs.getString("CO_LINEA_PROD_ERP"));
               prod.setCoOtc(rs.getString("CO_OTC"));
               prod.setCoGrupoTerapeutico(rs.getString("CO_GRUPO_TERAPEUTICO"));
               prod.setCoGiro(rs.getString("CO_GIRO"));
               prod.setCoGrupoAnatomico(rs.getString("CO_GRUPO_ANATOMICO"));
               prod.setCoFormaPresentacion(rs.getString("CO_FORMA_PRESENTACION"));
               prod.setCoAccionTerapeutica(rs.getString("CO_ACCION_TERAPEUTICA"));
               prod.setCoAccionFarmaceutica(rs.getString("CO_ACCION_FARMACEUTICA"));
               prod.setPcDescuentoBase(rs.getInt("PC_DESCUENTO_BASE"));
               prod.setCoViadm(rs.getString("CO_VIADM"));
               prod.setVaCostoProducto(rs.getDouble("VA_COSTO_PRODUCTO"));
               prod.setDeDetallePresentacionLargo(rs.getString("DE_DETALLE_PRESENTACION_LARGO"));
               prod.setVaPrecioPublico(rs.getDouble("VA_PRECIO_PUBLICO"));
               prod.setCoProductoSap(rs.getString("CO_PRODUCTO_SAP"));
               prod.setCoTipoConsumo(rs.getString("CO_TIPO_CONSUMO"));
               prod.setCoComprador(rs.getString("CO_COMPRADOR"));
               prod.setInReintegro(rs.getString("IN_REINTEGRO"));
               prod.setInDescontinuado(rs.getString("IN_DESCONTINUADO"));
               prod.setInControlado(rs.getString("IN_CONTROLADO"));
               prod.setInControlLote(rs.getString("IN_CONTROL_LOTE"));
               prod.setInPideMedico(rs.getString("IN_PIDE_MEDICO"));
               prod.setInRecetaMedica(rs.getString("IN_RECETA_MEDICA"));
               prod.setInProdFraccionable(rs.getString("IN_PROD_FRACCIONABLE"));
               prod.setInConsignadoSap(rs.getString("IN_CONSIGNADO_SAP"));
               prod.setCoUnidadSap(rs.getString("CO_UNIDAD_SAP"));
               prod.setTiMaterialSap(rs.getString("TI_MATERIAL_SAP"));
               prod.setCoUnidadFmSap(rs.getString("CO_UNIDAD_FM_SAP"));
               prod.setCoGrupoExt(rs.getString("CO_GRUPO_EXT"));
               prod.setDePartidaArancelaria(rs.getString("DE_PARTIDA_ARANCELARIA"));
               //prod.setDeLaboratorio(rs.getString("DE_LABORATORIO"));
               prod.setCoNivel01(rs.getString("CO_NIVEL_01"));
               prod.setCoNivel02(rs.getString("CO_NIVEL_02"));
               prod.setCoNivel03(rs.getString("CO_NIVEL_03"));
               prod.setCoNivel04(rs.getString("CO_NIVEL_04"));
               prod.setCoNivel05(rs.getString("CO_NIVEL_05"));
               prod.setCoProcedencia(rs.getString("CO_PROCEDENCIA"));
               prod.setInGenero(rs.getString("IN_GENERO"));
               rgs.add(prod);
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

    public Producto getRegistro(int id)
    {
        ArrayList<Producto> registros = this.getRegistros(Producto.COLUMNA_PK,new Object[]{id});
        return registros.get(0);
    }
    
     public Producto getRegistroPorCodigo(String id)
    {
        ArrayList<Producto> registros = this.getRegistros(new String[]{"codigo"},new Object[]{id});
        return registros.get(0);
    }
    
    public ArrayList<Producto> getRegistros(String[] columnas,Object[] cvl) {
        return this.getRegistros(new String[]{}, columnas,cvl);
    }
    
    public Producto getRegistroPorPk(Object[] id)
    {
            Producto prod = null;
        try {
            
            rs =  this.selectPorPk(Producto.nt,Producto.FULL_CAMPOS, Producto.COLUMNA_PK, id);
            /*
            public static final String[]
           FULL_CAMPOS ={"CO_COMPANIA, CO_PRODUCTO, NU_REVISION_PRODUCTO, DE_CORTA_PRODUCTO, DE_PRODUCTO,"+
                        "CO_FACTOR_PRECIO, CO_MONEDA, CO_IMPUESTO_1, CO_IMPUESTO_2, CO_LABORATORIO,"+
                        "CO_LINEA_PRODUCTO CO_GRUPO_PRODUCTO, CO_CATEGORIA_CCR, CO_CLASIFICACION_IMS,"+
                        "CO_CATEGORIA_SB, CO_SUBCATEGORIA_SB, CO_UNIDAD_MEDIDA, VA_UNIDAD_MEDIDA,"+
                        "CO_UNIDAD_COMPRA, CO_UNIDAD_VENTA, CO_UNIDAD_CONTENIDO, VA_UNIDAD_CONTENIDO,"+
                        "IN_PROD_FRACCIONABLE, VA_PRECIO_COMPRA, VA_PRECIO_PROMEDIO, VA_BONO, PC_BONO,"+
                        "DE_UNIDAD_PRODUCTO, IN_RECETA_MEDICA, ES_PRODUCTO, ID_CREA_PRODUCTO,"+
                        "FE_CREA_PRODUCTO, ID_MOD_PRODUCTO, FE_MOD_PRODUCTO, CO_CLASE_VENTA,"+
                        "CO_GRUPO_PROD_ERP, CO_LINEA_PROD_ERP, CO_OTC, CO_GRUPO_TERAPEUTICO, CO_GIRO,"+
                        "CO_GRUPO_ANATOMICO, CO_FORMA_PRESENTACION, CO_ACCION_TERAPEUTICA,"+
                        "CO_ACCION_FARMACEUTICA, PC_DESCUENTO_BASE, CO_VIADM, VA_COSTO_PRODUCTO,"+
                        "DE_DETALLE_PRESENTACION_LARGO, VA_PRECIO_PUBLICO, CO_PRODUCTO_SAP,"+
                        "CO_TIPO_CONSUMO, CO_COMPRADOR, IN_REINTEGRO, IN_DESCONTINUADO, IN_CONTROLADO,"+
                        "IN_CONSIGNADO_SAP, CO_UNIDAD_SAP, TI_MATERIAL_SAP, CO_UNIDAD_FM_SAP,"+
                        "CO_GRUPO_EXT, DE_PARTIDA_ARANCELARIA,IN_GENERO"};
             */
            while(rs.next())
            {
               prod = new Producto();
               prod.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PRODUCTO"),rs.getString("NU_REVISION_PRODUCTO")});
               prod.setCoCompania(rs.getString("CO_COMPANIA"));
               prod.setCoProducto(rs.getString("CO_PRODUCTO"));
               prod.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
               prod.setDeCortaProducto(rs.getString("DE_CORTA_PRODUCTO"));
               prod.setDeProducto(rs.getString("DE_PRODUCTO"));
               prod.setCoFactorPrecio(rs.getString("CO_FACTOR_PRECIO"));
               prod.setCoMoneda(rs.getString("CO_MONEDA"));
               prod.setCoImpuesto_1(rs.getString("CO_IMPUESTO_1"));
               prod.setCoImpuesto_2(rs.getString("CO_IMPUESTO_2"));
               prod.setCoLaboratorio(rs.getString("CO_LABORATORIO"));
               prod.setVaCostoProducto(rs.getDouble("VA_COSTO_PRODUCTO"));
               prod.setVaPrecioPublico(rs.getDouble("VA_PRECIO_PUBLICO"));
               prod.setCoProductoSap(rs.getString("CO_PRODUCTO_SAP"));
               prod.setTiMaterialSap(rs.getString("TI_MATERIAL_SAP"));
               prod.setInGenero(rs.getString("IN_GENERO"));
               //prod.setLaboratorio(new CLaboratorio().getRegistroPorPk(new Object[]{rs.getString("CO_COMPANIA"), rs.getString("CO_LABORATORIO")}));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prod;
    }               
    
    @Override
    public boolean guardarRegistro(JAbstractModelBD mdl) throws SQLException {
        Producto prod = (Producto)mdl;
        int gravado = 0;

        Object[] valores = {prod.getCoCompania(),
                prod.getCoProducto(),
                prod.getNuRevisionProducto(),
                prod.getDeProducto(),
                prod.getCoMoneda(),
                prod.getCoImpuesto1(),
                prod.getCoUnidadMedida(),
                prod.getVaUnidadMedida(),
                prod.getVaUnidadContenido(),
                prod.getInProdFraccionable(),
                prod.getVaPrecioCompra(),
                prod.getVaPrecioPromedio(),
                prod.getVaBono(),
                prod.getDeUnidadProducto(),
                prod.getInRecetaMedica(),
                prod.getEsProducto(),
                prod.getIdCreaProducto(),
                prod.getFeCreaProducto(),
                prod.getIdModProducto(),
                prod.getFeModProducto(),
                prod.getPcDescuentoBase(),
                prod.getVaCostoProducto(),
                prod.getVaPrecioPublico(),
                prod.getInControlado(),
                prod.getCoNivel01(),
                prod.getCoNivel02(),
                prod.getCoNivel03(),
                prod.getCoNivel04(),
                prod.getCoNivel05(),
                prod.getCoProcedencia(),
                prod.getFeVigencia(),
                prod.getInControlLote(),
                prod.getInPideMedico(),
                prod.getTiMaterialSap(),
                prod.getInGenero(),
                prod.getCoProductoSap()
        };

        gravado = this.agregarRegistroPs(Producto.nt, Producto.CAMPOS_Maestros, valores);

        if(gravado==1)
        {
            return true;
        }


        return false;
    }

    @Override
    public int actualizarRegistro(JAbstractModelBD mdl) {
        Producto prod = (Producto)mdl;
        int gravado = 0;
        String campos = Producto.CAMPOS_NOID_NOIMAGE;  
        
        Object[] valores = {prod.getCoCompania(),
                            prod.getCoProducto(),
                            prod.getNuRevisionProducto(),
                            prod.getDeCortaProducto(),
                            prod.getDeProducto(),
                            prod.getCoFactorPrecio(),
                            prod.getCoMoneda(),
                            prod.getCoImpuesto_1(),
                            prod.getCoImpuesto_2(),
                            prod.getCoLaboratorio(),
                            prod.getPrimaryKey()
                   };
              
           if(prod.getImagen(null) != null)
            {
                campos = Producto.CAMPOS_NO_ID.toString();
                
                 valores = new Object[]{
                            prod.getImagen(null),
                            prod.getDeCortaProducto(),
                            prod.getDeProducto(),
                            prod.getCoFactorPrecio(),
                            prod.getCoMoneda(),
                            prod.getCoImpuesto_1(),
                            prod.getCoImpuesto_2(),
                            prod.getCoLaboratorio(),
                            prod.getPrimaryKey()
                   };
            }        
           gravado = this.actualizarRegistroPs(Producto.nt, this.adjuntarSimbolo(campos, ",", "?")+Ex.WHERE+Producto.COLUMNA_PK+" = ? ", valores);
       
        return gravado;
    }

    public int actualizarProductos(JAbstractModelBD mdl) {
        Producto prod = (Producto)mdl;
        int gravado = 0;
        String campos = Producto.CAMPOS_NOID_NOIMAGE;

        Object[] valores = {prod.getDeProducto(),
                prod.getCoMoneda(),
                prod.getCoImpuesto1(),
                prod.getCoLaboratorio(),
                prod.getCoUnidadMedida(),
                prod.getVaUnidadMedida(),
                prod.getVaUnidadContenido(),
                prod.getInProdFraccionable(),
                prod.getVaPrecioCompra(),
                prod.getVaPrecioPromedio(),
                prod.getVaBono(),
                prod.getDeUnidadProducto(),
                prod.getInRecetaMedica(),
                prod.getEsProducto(),
                prod.getIdCreaProducto(),
                prod.getFeCreaProducto(),
                prod.getIdModProducto(),
                prod.getFeModProducto(),
                prod.getPcDescuentoBase(),
                prod.getVaCostoProducto(),
                prod.getVaPrecioPublico(),
                prod.getInControlado(),
                prod.getCoNivel01(),
                prod.getCoNivel02(),
                prod.getCoNivel03(),
                prod.getCoNivel04(),
                prod.getCoNivel05(),
                prod.getCoProcedencia(),
                prod.getFeVigencia(),
                prod.getInControlLote(),
                prod.getInPideMedico(),
                prod.getTiMaterialSap()
        };

        gravado = this.actualizarRegistroPs(Producto.nt, this.adjuntarSimbolo(generarArrayAString(Producto.CAMPOS_Maestros_SINPK), ",", "?")+Ex.WHERE+ unirColumnasValores(Producto.COLUMNA_PK, prod.getPrimaryKey()) , valores);

        return gravado;
    }

    @Override
    public ArrayList getRegistros(String[] campos, String[] columnaId, Object[] id) {
        ArrayList<Producto> rgs = new ArrayList<Producto>();        
        try {
                     
            rs = this.getRegistros(Producto.nt,campos,columnaId,id,null);
            
            Producto prod = null;
            while(rs.next())
            {
                   prod = new Producto();
                   prod.setPrimaryKey(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                   prod.setCoCompania(rs.getString(1));
                   prod.setCoProducto(rs.getString(2));
                   prod.setNuRevisionProducto(rs.getString(3));
                   prod.setDeCortaProducto(rs.getString(4));
                   prod.setDeProducto(rs.getString(5));
                   prod.setCoFactorPrecio(rs.getString(6));
                   prod.setCoMoneda(rs.getString(7));
                   prod.setCoImpuesto_1(rs.getString(8));
                   prod.setCoImpuesto_2(rs.getString(9));
                   prod.setCoLaboratorio(rs.getString(10));
                   prod.setVaPrecioPromedio(rs.getDouble(25));
                   prod.setDeUnidadProducto(rs.getString(28));
                   prod.setVaCostoProducto(rs.getDouble(47));
                 rgs.add(prod);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rgs;
    }
        
    public ImageIcon getFoto(String[] id)
    {
        InputStream archivo = this.getArchivo(Producto.nt, "imagen", Producto.COLUMNA_PK, id); 
        itmp = archivo;
        ImageIcon ii = null;
        if(archivo!= null)
        {
            try {
                 BufferedImage read = ImageIO.read(archivo);                 
                  ii = new ImageIcon(read);
            } catch (IOException ex) {
                Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(NullPointerException ex){
                ii = null;
            }
        }
        return ii;        
    }

    public Double getImpuesto(String coImpuesto) throws SQLException {
        return Double.valueOf(AtuxDBUtility.getValueAt("VTTR_IMPUESTO",
                "PC_IMPUESTO",
                " CO_COMPANIA = '" + AtuxVariables.vCodigoCompania + "'" +                
                " AND CO_IMPUESTO= '" + coImpuesto + "'"));                
    }

    public ArrayList getProductosLaboratorio(String Filtro){
        ArrayList<Producto> rgs = new ArrayList<Producto>();        
        Producto      prod      = null;        
        StringBuffer  query;
        try {            
            query = new StringBuffer();
            
            query.append("select T1.*  ");
            query.append("  from LGTM_PRODUCTO T1");
            query.append(" WHERE ");
            query.append("  T1.CO_COMPANIA = '").append(AtuxVariables.vCodigoCompania).append("'\n");
            /*
                    select T1.*
                    from LGTM_PRODUCTO T1
                    WHERE
                           T1.CO_COMPANIA = '003'
                    AND T1.ES_PRODUCTO = 'A'
                    ORDER BY DE_PRODUCTO
             */
            if ("A".equals(Filtro) || "I".equals(Filtro)){
                query.append("   AND T1.ES_PRODUCTO = '").append(Filtro).append("' ");
            }else if (!"T".equals(Filtro)){
                query.append(Filtro);
            }
            
            query.append(" ORDER BY DE_PRODUCTO ");
            
            rs =  this.getRegistrosSinFiltro(query);
            
            while(rs.next()){
                    prod = new Producto();
                    prod.setPrimaryKey(new String[]{rs.getString("CO_COMPANIA"),rs.getString("CO_PRODUCTO"),rs.getString("NU_REVISION_PRODUCTO")});
                    prod.setCoCompania(rs.getString("CO_COMPANIA"));
                    prod.setCoProducto(rs.getString("CO_PRODUCTO"));
                    prod.setNuRevisionProducto(rs.getString("NU_REVISION_PRODUCTO"));
                    prod.setDeCortaProducto(rs.getString("DE_CORTA_PRODUCTO"));
                    prod.setDeProducto(rs.getString("DE_PRODUCTO"));
                    prod.setCoFactorPrecio(rs.getString("CO_FACTOR_PRECIO"));
                    prod.setCoMoneda(rs.getString("CO_MONEDA"));
                    prod.setCoImpuesto1(rs.getString("CO_IMPUESTO_1"));
                    prod.setCoImpuesto2(rs.getString("CO_IMPUESTO_2"));
                    prod.setCoLaboratorio(rs.getString("CO_LABORATORIO"));
                    prod.setCoLineaProducto(rs.getString("CO_LINEA_PRODUCTO"));
                    prod.setCoGrupoProducto(rs.getString("CO_GRUPO_PRODUCTO"));
                    prod.setCoCategoriaCcr(rs.getString("CO_CATEGORIA_CCR"));
                    prod.setCoClasificacionIms(rs.getString("CO_CLASIFICACION_IMS"));
                    prod.setCoCategoriaSb(rs.getString("CO_CATEGORIA_SB"));
                    prod.setCoSubcategoriaSb(rs.getString("CO_SUBCATEGORIA_SB"));
                    prod.setCoUnidadMedida(rs.getString("CO_UNIDAD_MEDIDA"));
                    prod.setVaUnidadMedida(rs.getInt("VA_UNIDAD_MEDIDA"));
                    prod.setCoUnidadCompra(rs.getString("CO_UNIDAD_COMPRA"));
                    prod.setCoUnidadVenta(rs.getString("CO_UNIDAD_VENTA"));
                    prod.setCoUnidadContenido(rs.getString("CO_UNIDAD_CONTENIDO"));
                    prod.setVaUnidadContenido(rs.getInt("VA_UNIDAD_CONTENIDO"));
                    prod.setVaPrecioCompra(rs.getDouble("VA_PRECIO_COMPRA"));
                    prod.setVaPrecioPromedio(rs.getDouble("VA_PRECIO_PROMEDIO"));
                    prod.setVaBono(rs.getDouble("VA_BONO"));
                    prod.setPcBono(rs.getDouble("PC_BONO"));
                    prod.setDeUnidadProducto(rs.getString("DE_UNIDAD_PRODUCTO"));
                    prod.setEsProducto(rs.getString("ES_PRODUCTO"));
                    prod.setIdCreaProducto(rs.getString("ID_CREA_PRODUCTO"));
                    prod.setFeCreaProducto(rs.getDate("FE_CREA_PRODUCTO"));
                    prod.setIdModProducto(rs.getString("ID_MOD_PRODUCTO"));
                    prod.setFeModProducto(rs.getDate("FE_MOD_PRODUCTO"));
                    prod.setCoClaseVenta(rs.getString("CO_CLASE_VENTA"));
                    prod.setCoGrupoProdErp(rs.getString("CO_GRUPO_PROD_ERP"));
                    prod.setCoLineaProdErp(rs.getString("CO_LINEA_PROD_ERP"));
                    prod.setCoOtc(rs.getString("CO_OTC"));
                    prod.setCoGrupoTerapeutico(rs.getString("CO_GRUPO_TERAPEUTICO"));
                    prod.setCoGiro(rs.getString("CO_GIRO"));
                    prod.setCoGrupoAnatomico(rs.getString("CO_GRUPO_ANATOMICO"));
                    prod.setCoFormaPresentacion(rs.getString("CO_FORMA_PRESENTACION"));
                    prod.setCoAccionTerapeutica(rs.getString("CO_ACCION_TERAPEUTICA"));
                    prod.setCoAccionFarmaceutica(rs.getString("CO_ACCION_FARMACEUTICA"));
                    prod.setPcDescuentoBase(rs.getInt("PC_DESCUENTO_BASE"));
                    prod.setCoViadm(rs.getString("CO_VIADM"));
//                    prod.setVaCostoProducto(rs.getInt("VA_COSTO_PRODUCTO"));
                    prod.setDeDetallePresentacionLargo(rs.getString("DE_DETALLE_PRESENTACION_LARGO"));
//                    prod.setVaPrecioPublico(rs.getInt("VA_PRECIO_PUBLICO"));
                    prod.setCoProductoSap(rs.getString("CO_PRODUCTO_SAP"));
                    prod.setCoTipoConsumo(rs.getString("CO_TIPO_CONSUMO"));
                    prod.setCoComprador(rs.getString("CO_COMPRADOR"));
                    prod.setInReintegro(rs.getString("IN_REINTEGRO"));
                    prod.setInDescontinuado(rs.getString("IN_DESCONTINUADO"));
                    prod.setInControlado(rs.getString("IN_CONTROLADO"));
                    prod.setInControlLote(rs.getString("IN_CONTROL_LOTE"));
                    prod.setInPideMedico(rs.getString("IN_PIDE_MEDICO"));
                    prod.setInRecetaMedica(rs.getString("IN_RECETA_MEDICA"));
                    prod.setInProdFraccionable(rs.getString("IN_PROD_FRACCIONABLE"));
//                    prod.setCaEmpaque(rs.getInt("CA_EMPAQUE"));
                    prod.setInConsignadoSap(rs.getString("IN_CONSIGNADO_SAP"));
                    prod.setCoUnidadSap(rs.getString("CO_UNIDAD_SAP"));
                    prod.setTiMaterialSap(rs.getString("TI_MATERIAL_SAP"));
                    prod.setCoUnidadFmSap(rs.getString("CO_UNIDAD_FM_SAP"));
                    prod.setCoGrupoExt(rs.getString("CO_GRUPO_EXT"));
                    prod.setDePartidaArancelaria(rs.getString("DE_PARTIDA_ARANCELARIA"));
                    //prod.setDeLaboratorio(rs.getString("DE_LABORATORIO"));
                    prod.setCoNivel01(rs.getString("CO_NIVEL_01"));
                    prod.setCoNivel02(rs.getString("CO_NIVEL_02"));
                    prod.setCoNivel03(rs.getString("CO_NIVEL_03"));
                    prod.setCoNivel04(rs.getString("CO_NIVEL_04"));
                    prod.setCoNivel05(rs.getString("CO_NIVEL_05"));
                    prod.setCoProcedencia(rs.getString("CO_PROCEDENCIA"));
                    prod.setInGenero(rs.getString("IN_GENERO"));
                    rgs.add(prod);
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

    public String getNuevoCodigo(){
        String codigo="";
        try {
            return AtuxDBUtility.getValueAt(Producto.nt,"NVL(LPAD((to_char(max(co_producto)) + 1) ,6,'0'),'000001')"," co_compania = '" + AtuxVariables.vCodigoCompania + "' and nu_revision_producto =0");
        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo.trim();
    }    
}