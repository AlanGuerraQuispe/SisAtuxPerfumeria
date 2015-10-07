package com.atux.dominio.promocion;

import com.atux.bean.promocion.Promocion;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.bean.promocion.PromocionLocal;
import com.atux.comun.context.AppCtx;
import com.atux.infraestructura.jpa.PrtmPromoRepository;
import com.atux.infraestructura.jpa.pojo.*;
import com.atux.service.qryMapper.ProductoQryMapper;
import com.atux.service.qryMapper.PromocionQryMapper;
import com.atux.service.qryMapper.SequenceQryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by MATRIX-JAVA on 01/12/2014.
 */
@Service
public class PromocionServiceImpl implements PromocionService {

    @Autowired
    ProductoQryMapper productoQryMapper;

    @Autowired
    PromocionQryMapper promocionQryMapper;

    @Autowired
    EntityManager entityManager;

    @Autowired
    SequenceQryMapper sequenceQryMapper;

    @Autowired
    PrtmPromoRepository prtmPromoRepository;

    @Autowired
    PrtdProductoPlanPromoRepository prtdProductoPlanPromoRepository;

    @Autowired
    PrtrLocalPromoRepository prtrLocalPromoRepository;

    @Autowired
    ConversionService conversionService;

    @Transactional
    public void grabar(Promocion backBean) {

        if (StringUtils.isBlank(backBean.getCoPromocion())) {
            String coPromocion = sequenceQryMapper.nextPrtmPromo();
            backBean.setCoPromocion(coPromocion);
        }
        PrtmPromo prtmPromo = new PrtmPromo();
        BeanUtils.copyProperties(backBean, prtmPromo);
        prtmPromo.getId().setCoCompania(AppCtx.instance().getLocalId().getCoCompania());
        prtmPromo.getId().setCoPromocion(backBean.getCoPromocion());
        prtmPromoRepository.save(prtmPromo);
        promocionQryMapper.deletePromocion(prtmPromo.getId().getCoPromocion());
        promocionQryMapper.deletePromocionLocal(prtmPromo.getId().getCoPromocion());
        for (PromocionDetalle precioListaDetalle : backBean.getDetalle()) {
            PrtdProductoPlanPromo planPromo = new PrtdProductoPlanPromo();
            BeanUtils.copyProperties(precioListaDetalle, planPromo);
            planPromo.getId().setCoCompania(AppCtx.instance().getLocalId().getCoCompania());
            planPromo.getId().setCoPromocion(prtmPromo.getId().getCoPromocion());
            planPromo.getId().setCoProducto(precioListaDetalle.getCoProducto());
            prtdProductoPlanPromoRepository.save(planPromo);
        }
        Iterator locales = new HashSet(backBean.getDetalleLocal()).iterator();
        while (locales.hasNext()) {
            PromocionLocal promocioLocal = (PromocionLocal) locales.next();
            PrtrLocalPromo planPromo = new PrtrLocalPromo();
            planPromo.getId().setCoCompania(AppCtx.instance().getLocalId().getCoCompania());
            planPromo.getId().setCoPromocion(prtmPromo.getId().getCoPromocion());
            planPromo.getId().setCoLocal(promocioLocal.getCoLocal());
            planPromo.setEsLocalPromocion("A");
            prtrLocalPromoRepository.save(planPromo);
        }
    }

    @Transactional
    public void eliminar(Promocion backBean) {
        prtmPromoRepository.delete(new PrtmPromoPK(backBean.getCoCompania(), backBean.getCoPromocion()));
        promocionQryMapper.deletePromocion(backBean.getCoPromocion());
        promocionQryMapper.deletePromocionLocal(backBean.getCoPromocion());
    }

}
