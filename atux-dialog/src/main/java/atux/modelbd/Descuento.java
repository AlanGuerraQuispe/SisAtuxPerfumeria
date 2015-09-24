package atux.modelbd;

import atux.core.JAbstractModelBD;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author user
 */
public class Descuento extends JAbstractModelBD implements Serializable,IModel{
    private static final long serialVersionUID = 1L;

    public static final String nt = "VTTR_DESCUENTO";
    public static final String[] COLUMNA_PK ={"CO_COMPANIA","CO_DESCUENTO"};
    public static final String COLUMNA_ACTIVO = "ES_DESCUENTO";

    private String coCompania;
    private String coDescuento;
    private Double pcDescuento;
    private String deCortaDescuento;
    private String deDescuento;
    private String esDescuento;
    private String idCreaDescuento;
    private Date feCreaDescuento;
    private String idModDescuento;
    private Date feModDescuento;

    public static final String[]
          FULL_NOM_CAMPOS ={"CO_COMPANIA,CO_DESCUENTO,PC_DESCUENTO,DE_CORTA_DESCUENTO,DE_DESCUENTO,"+
                            "ES_DESCUENTO,ID_CREA_DESCUENTO,FE_CREA_DESCUENTO"};

    public Descuento() {
        initBasic();
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }

    public String getCoDescuento() {
        return coDescuento;
    }

    public void setCoDescuento(String coDescuento) {
        this.coDescuento = coDescuento;
    }

    public Double getPcDescuento() {
        return pcDescuento;
    }

    public void setPcDescuento(Double pcDescuento) {
        this.pcDescuento = pcDescuento;
    }

    public String getDeCortaDescuento() {
        return deCortaDescuento;
    }

    public void setDeCortaDescuento(String deCortaDescuento) {
        this.deCortaDescuento = deCortaDescuento;
    }

    public String getDeDescuento() {
        return deDescuento;
    }

    public void setDeDescuento(String deDescuento) {
        this.deDescuento = deDescuento;
    }

    public String getEsDescuento() {
        return esDescuento;
    }

    public void setEsDescuento(String esDescuento) {
        this.esDescuento = esDescuento;
    }

    public String getIdCreaDescuento() {
        return idCreaDescuento;
    }

    public void setIdCreaDescuento(String idCreaDescuento) {
        this.idCreaDescuento = idCreaDescuento;
    }

    public Date getFeCreaDescuento() {
        return feCreaDescuento;
    }

    public void setFeCreaDescuento(Date feCreaDescuento) {
        this.feCreaDescuento = feCreaDescuento;
    }

    public String getIdModDescuento() {
        return idModDescuento;
    }

    public void setIdModDescuento(String idModDescuento) {
        this.idModDescuento = idModDescuento;
    }

    public Date getFeModDescuento() {
        return feModDescuento;
    }

    public void setFeModDescuento(Date feModDescuento) {
        this.feModDescuento = feModDescuento;
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
        
        if (!(object instanceof Descuento)) {
            return false;
        }
        Descuento other = (Descuento) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  deDescuento ;
    } 
}