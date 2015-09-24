package atux.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aguerra on 06/08/2015.
 */
public enum IndicadorProductoGenero {
    CABALLEROS("M","CABALLEROS"),
    DAMAS("F","DAMAS");

    private String codigo;
    private String nombre;

    IndicadorProductoGenero(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static IndicadorProductoGenero findByCode(String codigo){
        IndicadorProductoGenero[] array= IndicadorProductoGenero.values();
        IndicadorProductoGenero result=null;
        for (int i=0;i<array.length;i++){

            if(array[i].getCodigo().equals(codigo)){
                result=array[i];
                break;
            }

        }
        return result;
    }

    public static List<IndicadorProductoGenero> findAll(){
        List<IndicadorProductoGenero> list= new ArrayList<IndicadorProductoGenero>();

        for(IndicadorProductoGenero status: IndicadorProductoGenero.values()){
            list.add(status);
        }

        return list;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
