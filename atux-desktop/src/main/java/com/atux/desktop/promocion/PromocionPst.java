package com.atux.desktop.promocion;

import com.atux.bean.precios.Local;
import com.atux.bean.precios.LocalFlt;
import com.atux.bean.promocion.Promocion;
import com.atux.bean.promocion.PromocionDetalle;
import com.atux.bean.promocion.PromocionLocal;
import com.atux.config.APDD;
import com.atux.desktop.comun.picks.SeleccionarLocalPst;
import com.atux.dominio.promocion.PromocionService;
import com.atux.service.qryMapper.ProveedorQryMapper;
import com.aw.core.report.ReportUtils;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.ActionDialog;
import com.aw.swing.mvp.action.types.*;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.grid.GridInfoProvider;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.navigation.Flow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * Created by JAVA on 15/11/2014.
 */
@AWPresenter(title = "Proveedor - Precio")
public class PromocionPst extends Presenter<Promocion> {
    protected final Log LOG = LogFactory.getLog(getClass());
    private FrmPromocion vsr;

    @Autowired
    ProveedorQryMapper proveedorQryMapper;

    @Autowired
    PromocionService promocionService;

    JFileChooser chooser = new JFileChooser();

    GridProvider gdp;
    GridProvider gdpLocal;

    @Autowired
    APDD apdd;

    public PromocionPst() {
        setBackBean(new Promocion());
        setShowAuditInfo(false);

    }

    @Override
    protected void registerBinding() {
        bindingMgr.bind(vsr.txtCoPromocion, "coPromocion").setUIReadOnly(true);
        bindingMgr.bind(vsr.txtNoPromocion, "noPromocion");
        bindingMgr.bind(vsr.txtMensajeCorto, "mensajeCorto");
        bindingMgr.bind(vsr.txtMensajeLargo, "mensajeLargo");
        bindingMgr.bind(vsr.txtObservacion, "observacion");
        bindingMgr.bind(vsr.txtFeIchanicio, "fechaInicio");
        bindingMgr.bind(vsr.txtFechaFin, "fechaFin");
        bindingMgr.bind(vsr.chkEstado, "esPromocion").registerTrueFalse("A", "I");
    }

    @Override
    protected void registerGridProviders() {
        gdp = gridProviderMgr.registerGridProvider(new IPDetalle());
        gdpLocal = gridProviderMgr.registerGridProvider(new IPLocal());
    }


    private class IPDetalle extends GridInfoProvider<Promocion> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "coProducto", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Producto", "deProducto", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Cant. Ent.", "caEntero", 30, ColumnInfo.RIGHT),
                    new ColumnInfo("Cant. Frac.", "caFraccion", 30, ColumnInfo.RIGHT),
                    new ColumnInfo("Prom. Código", "coProductoP", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Prom. Producto", "deProductoP", 80, ColumnInfo.LEFT),
                    new ColumnInfo("Prom. Cant. Ent.", "caEnteroP", 30, ColumnInfo.RIGHT),
                    new ColumnInfo("Prom. Cant Frac.", "caFraccionP", 30, ColumnInfo.RIGHT),
                    new ColumnInfo("Estado", "esProductoPlan", 80, ColumnInfo.LEFT).setDropDownFormatter(apdd.ES_TABLA),
            };
            return columns;
        }

        public List<PromocionDetalle> getValues(Promocion precioLista) {
            return precioLista.getDetalle();
        }
    }

    private class IPLocal extends GridInfoProvider<Promocion> {

        public ColumnInfo[] getColumnInfo() {
            ColumnInfo[] columns = new ColumnInfo[]{
                    new ColumnInfo("Código", "coLocal", 50, ColumnInfo.LEFT),
                    new ColumnInfo("Local", "deLocal", 80, ColumnInfo.LEFT)
            };
            return columns;
        }

        public List<PromocionLocal> getValues(Promocion precioLista) {
            return precioLista.getDetalleLocal();
        }
    }

    @Override
    protected void afterInitComponents() {

    }

    protected void registerActions() {

        actionRsr.registerAction("Nuevo", new InsertAction(PromocionDetalle.class), gdp)
                .notNeedVisualComponent()
                .refreshGridAtEnd()
                .noExecValidation()
                .setKeyTrigger(ActionDialog.KEY_F2)
                .setTargetPstClass(PromocionCrudPst.class);

        actionRsr.registerAction("Delete", new DeleteItemAction() {
            @Override
            protected Object executeIntern() throws Throwable {
                getBackBean().getDetalle().remove(gdp.getSelectedRow());
                return null;
            }
        }, gdp)
                .notNeedVisualComponent()
                .needSelectedRow()
                .refreshGridAtEnd()
                .setKeyTrigger(ActionDialog.KEY_F4)
        ;

        actionRsr.registerAction("DeleteLocal", new DeleteItemAction() {
            @Override
            protected Object executeIntern() throws Throwable {
                getBackBean().getDetalleLocal().remove(gdpLocal.getSelectedRow());
                return null;
            }
        }, gdpLocal)
                .notNeedVisualComponent()
                .needSelectedRow()
                .refreshGridAtEnd()
                .setKeyTrigger(ActionDialog.KEY_F4)
        ;

        actionRsr.registerAction("Guardar", new Action() {
            @Override
            protected Object executeIntern() throws Throwable {
                promocionService.grabar(getBackBean());
                return null;
            }
        }).notNeedVisualComponent()
                .setKeyTrigger(ActionDialog.KEY_F10)
                .closeViewAtEnd();

        actionRsr.registerAction("Seleccionar", new ShowPstAction(LocalFlt.class){

            @Override
            public Object executeOnReturn(Flow initialFlow, Flow endFlow) {
//                endFlow.getAttribute(Flow.BACK_BEAN_NAME);
                List<Local> localList= (List<Local>) endFlow.getAttribute("selectedRows");

                for (Local local : localList) {
                    PromocionLocal promocionLocal=new PromocionLocal();
                    promocionLocal.setCoLocal(local.getCoLocal());
                    promocionLocal.setDeLocal(local.getDeLocal());
                    getBackBean().getDetalleLocal().add(promocionLocal);
                }

                return super.executeOnReturn(initialFlow, endFlow);
            }
        }, gdpLocal)
                .refreshGridAtEnd()
                .notNeedVisualComponent()
                .noExecValidation()
                .setKeyTrigger(ActionDialog.KEY_F6)
                .setTargetPstClass(SeleccionarLocalPst.class)
        ;

    }

    public void descargarAction() {
        try {
            ReportUtils.showReport(new File(getClass().getResource("/plantilla_precio_proveedor.xls").toURI()).getAbsolutePath());
        } catch (Throwable e) {
            logger.error("Error ", e);
        }
    }

    public void examinarAction() {
        int returnVal = chooser.showOpenDialog(vsr.pnlMain);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            //This is where a real application would open the file.
            logger.info("Opening: " + file.getName() + "." + "\n");
        } else {
            logger.info("Open command cancelled by user." + "\n");
        }

    }

}
