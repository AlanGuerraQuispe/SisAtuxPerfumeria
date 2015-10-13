package com.atux.desktop.promocion;

import com.atux.bean.precios.ProveedorFlt;
import com.atux.bean.promocion.Promocion;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.bean.promocion.PromocionFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.config.APDD;
import com.atux.desktop.promocion.FrmFNPromocion;
import com.atux.dominio.precios.ProveedorService;
import com.atux.dominio.promocion.PromocionService;
import com.atux.service.qryMapper.PromocionQryMapper;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.DeleteItemAction;
import com.aw.swing.mvp.action.types.EditAction;
import com.aw.swing.mvp.action.types.InsertAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Promociones")
public class FNPromocionPst extends FindPresenter<PromocionFlt> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmFNPromocion vsr;

    @Autowired
    PromocionService promocionService;

    @Autowired
    PromocionQryMapper promocionQryMapper;

    @Autowired
    ProveedorService proveedorService;

    GridProvider gdpDetalleLista;

    @Autowired
    APDD apdd;

    public FNPromocionPst() {
        setBackBean(new PromocionFlt());
        setShowAuditInfo(false);
        setSearchAtBeginning(false);
    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtNoPromocion, "noPromocion");
    }

    @Override
    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = new ColumnInfo[]{
                new ColumnInfo("Promoción", "coPromocion", 45, ColumnInfo.LEFT),
                new ColumnInfo("Nombre", "noPromocion", 150, ColumnInfo.LEFT),
                new ColumnInfo("Mensaje Corto", "mensajeCorto", 60, ColumnInfo.LEFT),
                new ColumnInfo("Mensaje Largo", "mensajeLargo", 120, ColumnInfo.LEFT),
                new ColumnInfo("Fecha Inicio", "fechaInicio", 50, ColumnInfo.LEFT).formatAsDate(),
                new ColumnInfo("Fecha Fin", "fechaFin", 50, ColumnInfo.LEFT).formatAsDate(),
                new ColumnInfo("Todos Locales", "inTodosLocales", 60, ColumnInfo.LEFT).formatAsDate(),
                new ColumnInfo("Estado", "esPromocion", 60, ColumnInfo.LEFT).setDropDownFormatter(apdd.ES_TABLA)


        };
        return columns;
    }

    public List getValues(PromocionFlt prove) {
        prove.setLocalId(AppCtx.instance().getLocalId());
        return promocionQryMapper.findPromocionList(prove);
    }


    protected void registerGridProviders() {
        gdpDetalleLista = gridProviderMgr.registerGridProvider(new IPPromocionDetalle())
                .dependsOn(getGridProvider());
    }

    protected void registerActions() {
        actionRsr.registerAction("Nuevo", new InsertAction<Promocion, Promocion>(Promocion.class))
                .notNeedVisualComponent()
                .setKeyTrigger(ActionDialog.KEY_F2)
                .setTargetPstClass(PromocionPst.class)
                .refreshGridAtEnd();

        actionRsr.registerAction("Editar", new EditAction<Promocion, Promocion>() {

            @Override
            protected Promocion getObjectToBeUpdated(Promocion obj) {
                Promocion precioLista = null;
                if (getGridProvider().getSelectedRow() != null) {
                    precioLista = (Promocion) getGridProvider().getSelectedRow();
                    PromocionFlt proveedorFlt = new PromocionFlt();
                    proveedorFlt.setLocalId(AppCtx.instance().getLocalId());
                    proveedorFlt.setCoPromocion(obj.getCoPromocion());
                    precioLista.setDetalle(promocionQryMapper.findPromocionDetalleList(proveedorFlt));
                    precioLista.setDetalleLocal(promocionQryMapper.findPromocionDetalleLocalList(proveedorFlt));
                }
                return precioLista;
            }
        }, getGridProvider()).notNeedVisualComponent()
                .needSelectedRow()
                .refreshGridAtEnd()
                .setKeyTrigger(ActionDialog.KEY_F3)
                .setTargetPstClass(PromocionPst.class);

        actionRsr.registerAction("Eliminar", new DeleteItemAction() {
            @Override
            protected Object executeIntern() throws Throwable {
                promocionService.eliminar((Promocion) getGridProvider().getSelectedRow());
                return null;
            }
        }, getGridProvider())
                .needSelectedRow()
                .setKeyTrigger(ActionDialog.KEY_F4)
                .notNeedVisualComponent()
                .refreshGridAtEnd()
                .setConfirmMsg("¿Está seguro de eliminar la promoción?");
    }


    private class IPPromocionDetalle extends GridInfoProvider<Promocion> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "coProducto", 40, ColumnInfo.LEFT) .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Producto", "deProducto", 200, ColumnInfo.LEFT) .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Cant. Ent.", "caEntero", 45, ColumnInfo.RIGHT)
                            .formatAsNumberWithGroup()
                            .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Cant. Frac.", "caFraccion", 45, ColumnInfo.RIGHT)
                            .formatAsNumberWithGroup()
                            .setBackgroundColor(new Color(79, 129, 189), Color.black),
                    new ColumnInfo("Código", "coProductoP", 40, ColumnInfo.LEFT),
                    new ColumnInfo("Producto", "deProductoP", 200, ColumnInfo.LEFT),
                    new ColumnInfo("Cant. Ent.", "caEnteroP", 45, ColumnInfo.RIGHT)
                            .formatAsNumberWithGroup(),
                    new ColumnInfo("Cant Frac.", "caFraccionP", 45, ColumnInfo.RIGHT)
                            .formatAsNumberWithGroup(),
                    new ColumnInfo("Estado", "esProductoPlan", 40, ColumnInfo.LEFT),
            };
            return columns;
        }

        public List<PromocionDetalle> getValues(Promocion precioLista) {
            PromocionFlt proveedorFlt = new PromocionFlt();
            proveedorFlt.setLocalId(AppCtx.instance().getLocalId());
            proveedorFlt.setCoPromocion(precioLista.getCoPromocion());
            return promocionQryMapper.findPromocionDetalleList(proveedorFlt);

        }

    }

    public static void main(String[] args) {
        AppCtx.instance().setLocalId(new LocalId("003", "001"));


        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(FNPromocionPst.class);
        PromocionFlt productoFlt = new PromocionFlt();
        productoFlt.setLocalId(AppCtx.instance().getLocalId());
        presenter.setBackBean(productoFlt);
        presenter.init();
    }

}
