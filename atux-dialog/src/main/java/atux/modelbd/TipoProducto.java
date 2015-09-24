package atux.modelbd;

import atux.core.JAbstractModelBD;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author user
 */
public class TipoProducto extends JAbstractModelBD implements Serializable, IModel {
    private static final long serialVersionUID = 1L;

    public static final String nt = "CMTR_TIPO_PRODUCTO";
    public static final String[] COLUMNA_PK = {"NU_ORDEN_FILA"};
    public static final String COLUMNA_ACTIVO = "ES_TIPO_PRODUCTO";

    private int nuOrdenFila;
    private String tiProducto;
    private String deTipoProducto;
    private String esTipoProducto;
    private String idCreaTipoProducto;
    private Date feCreaTipoProducto;

    public static final String[]
            FULL_NOM_CAMPOS = {"NU_ORDEN_FILA,TI_PRODUCTO,DESCRIPCION_TIPO,ES_TIPO_PRODUCTO," +
            "ID_CREA_TIPO_PROD,FE_CREA_TIPO_PROD"};

    public TipoProducto() {
        initBasic();
    }

    private void initBasic() {
        this.setNombreTabla(nt);
        this.setCampoClavePrimaria(COLUMNA_PK);
        this.setCampoExistencial(COLUMNA_ACTIVO);
    }

    public int getNuOrdenFila() {
        return nuOrdenFila;
    }

    public void setNuOrdenFila(int nuOrdenFila) {
        this.nuOrdenFila = nuOrdenFila;
    }

    public String getTiProducto() {
        return tiProducto;
    }

    public void setTiProducto(String tiProducto) {
        this.tiProducto = tiProducto;
    }

    public String getDeTipoProducto() {
        return deTipoProducto;
    }

    public void setDeTipoProducto(String deTipoProducto) {
        this.deTipoProducto = deTipoProducto;
    }

    public String getEsTipoProducto() {
        return esTipoProducto;
    }

    public void setEsTipoProducto(String esTipoProducto) {
        this.esTipoProducto = esTipoProducto;
    }

    public String getIdCreaTipoProducto() {
        return idCreaTipoProducto;
    }

    public void setIdCreaTipoProducto(String idCreaTipoProducto) {
        this.idCreaTipoProducto = idCreaTipoProducto;
    }

    public Date getFeCreaTipoProducto() {
        return feCreaTipoProducto;
    }

    public void setFeCreaTipoProducto(Date feCreaTipoProducto) {
        this.feCreaTipoProducto = feCreaTipoProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof TipoProducto)) {
            return false;
        }
        TipoProducto other = (TipoProducto) object;
        if ((this.getPrimaryKey() == null && other.getPrimaryKey() != null) || (this.getPrimaryKey() != null && !Arrays.equals(this.getPrimaryKey(), other.getPrimaryKey()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return deTipoProducto;
    }
}