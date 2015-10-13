package com.atux.bean.precios;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class Local {

    private String coCompania;
    private String coLocal;
    private String deLocal;
    private String direccion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCoLocal() {
        return coLocal;
    }

    public void setCoLocal(String coLocal) {
        this.coLocal = coLocal;
    }

    public String getDeLocal() {
        return deLocal;
    }

    public void setDeLocal(String deLocal) {
        this.deLocal = deLocal;
    }

    public String getCoCompania() {
        return coCompania;
    }

    public void setCoCompania(String coCompania) {
        this.coCompania = coCompania;
    }
}
