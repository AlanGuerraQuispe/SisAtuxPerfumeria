package com.atux.bean.precios;

import com.atux.comun.FilterBaseLocal;

/**
 * Created by JAVA on 15/11/2014.
 */
public class LocalFlt extends FilterBaseLocal {

    private String buscar;
    private boolean multiple=true;

    private String tiLocal;

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getTiLocal() {
        return tiLocal;
    }

    public void setTiLocal(String tiLocal) {
        this.tiLocal = tiLocal;
    }
}
