package atux.modelbd;

import atux.core.JAbstractModelBD;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author user
 */
public class DescuentoProducto extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "VTTX_DESCUENTO_PRODUCTO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_LOCAL","CO_PRODUCTO","NU_REVISION_PRODUCTO"};
    public static final String COLUMNA_ACTIVO = "ES_DESCUENTO_PRODUCTO";

    private String coCompania;
    private String coLocal;
    private String coProducto;
    private String nuRevisionProducto;
    private String inGenero;
    private String coDescuento;
    private Date feInicioVigencia;
    private Date feFinVigencia;
    private String esDescuentoProducto;
    private String idCreaDescuentoProducto;
    private Date feCreaDescuentoProducto;
    private String idModDescuentoProducto;
    private Date feModDescuentoProducto;

    public static final String[]
          FULL_NOM_CAMPOS ={"CO_COMPANIA,CO_LOCAL,CO_PRODUCTO,NU_REVISION_PRODUCTO,IN_GENERO,CO_DESCUENTO,"+
                            "FE_INICIO_VIGENCIA_DESCUENTO,FE_FIN_VIGENCIA_DESCUENTO,"+
                            "ES_DESCUENTO_PRODUCTO,ID_CREA_DESCUENTO_PRODUCTO,FE_CREA_DESCUENTO_PRODUCTO"};


    public static final String[]
            CAMPOS_NO_ID ={"IN_GENERO,CO_DESCUENTO,"+
            "FE_INICIO_VIGENCIA_DESCUENTO,FE_FIN_VIGENCIA_DESCUENTO,"+
            "ES_DESCUENTO_PRODUCTO,ID_MOD_DESCUENTO_PRODUCTO,FE_MOD_DESCUENTO_PRODUCTO"};

    public DescuentoProducto() {
        initBasic();
    }

    public DescuentoProducto(String coCompania, String coLocal, String coProducto, String nuRevisionProducto, String inGenero, String coDescuento, Date feInicioVigencia, Date feFinVigencia, String esDescuentoProducto, String idModDescuentoProducto, Date feModDescuentoProducto) {
        this.coCompania = coCompania;
        this.coLocal = coLocal;
        this.coProducto = coProducto;
        this.nuRevisionProducto = nuRevisionProducto;
        this.inGenero = inGenero;
        this.coDescuento = coDescuento;
        this.feInicioVigencia = feInicioVigencia;
        this.feFinVigencia = feFinVigencia;
        this.esDescuentoProducto = esDescuentoProducto;
        this.idModDescuentoProducto = idModDescuentoProducto;
        this.feModDescuentoProducto = feModDescuentoProducto;
        setPrimaryKey(new String[]{this.coCompania, this.coLocal, this.coProducto,this.nuRevisionProducto});
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getCoProducto() {
        return coProducto;
    }

    public void setCoProducto(String coProducto) {
        this.coProducto = coProducto;
    }

    public String getNuRevisionProducto() {
        return nuRevisionProducto;
    }

    public void setNuRevisionProducto(String nuRevisionProducto) {
        this.nuRevisionProducto = nuRevisionProducto;
    }

    public String getInGenero() {
        return inGenero;
    }

    public void setInGenero(String inGenero) {
        this.inGenero = inGenero;
    }

    public String getCoDescuento() {
        return coDescuento;
    }

    public void setCoDescuento(String coDescuento) {
        this.coDescuento = coDescuento;
    }

    public Date getFeInicioVigencia() {
        return feInicioVigencia;
    }

    public void setFeInicioVigencia(Date feInicioVigencia) {
        this.feInicioVigencia = feInicioVigencia;
    }

    public Date getFeFinVigencia() {
        return feFinVigencia;
    }

    public void setFeFinVigencia(Date feFinVigencia) {
        this.feFinVigencia = feFinVigencia;
    }

    public String getEsDescuentoProducto() {
        return esDescuentoProducto;
    }

    public void setEsDescuentoProducto(String esDescuentoProducto) {
        this.esDescuentoProducto = esDescuentoProducto;
    }

    public String getIdCreaDescuentoProducto() {
        return idCreaDescuentoProducto;
    }

    public void setIdCreaDescuentoProducto(String idCreaDescuentoProducto) {
        this.idCreaDescuentoProducto = idCreaDescuentoProducto;
    }

    public Date getFeCreaDescuentoProducto() {
        return feCreaDescuentoProducto;
    }

    public void setFeCreaDescuentoProducto(Date feCreaDescuentoProducto) {
        this.feCreaDescuentoProducto = feCreaDescuentoProducto;
    }

    public String getIdModDescuentoProducto() {
        return idModDescuentoProducto;
    }

    public void setIdModDescuentoProducto(String idModDescuentoProducto) {
        this.idModDescuentoProducto = idModDescuentoProducto;
    }

    public Date getFeModDescuentoProducto() {
        return feModDescuentoProducto;
    }

    public void setFeModDescuentoProducto(Date feModDescuentoProducto) {
        this.feModDescuentoProducto = feModDescuentoProducto;
    }

    private void initBasic()
    {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {   
        
        if (!(object instanceof DescuentoProducto)) {
            return false;
        }
        DescuentoProducto other = (DescuentoProducto) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

}