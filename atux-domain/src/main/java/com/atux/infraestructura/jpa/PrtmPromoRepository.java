package com.atux.infraestructura.jpa;

import com.atux.infraestructura.jpa.pojo.PrtmPromo;
import com.atux.infraestructura.jpa.pojo.PrtmPromoPK;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
public interface PrtmPromoRepository extends CrudRepository<PrtmPromo,PrtmPromoPK> {

}
