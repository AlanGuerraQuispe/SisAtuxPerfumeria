package com.atux.desktop.comun.picks;

import com.atux.bean.precios.Local;
import com.atux.bean.precios.LocalFlt;
import com.atux.comun.LocalId;
import com.atux.comun.context.AppCtx;
import com.atux.service.qryMapper.LocalQryMapper;
import com.aw.stereotype.AWPresenter;
import com.aw.swing.mvp.FindPresenter;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.PstMgr;
import com.aw.swing.mvp.action.types.SelectRowsAction;
import com.aw.swing.mvp.binding.component.support.ColumnInfo;
import com.aw.swing.mvp.binding.component.support.SelectorColumn;
import com.aw.swing.mvp.ui.ZonePanel;
import com.aw.swing.mvp.ui.laf.LookAndFeelManager;
import com.aw.swing.spring.Application;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: PC6
 * Date: 19/05/2009
 * Time: 09:57:36 AM
 * To change this template use File | Settings | File Templates.
 */
@AWPresenter(title = "Locales", secure = false)
public class SeleccionarLocalPst extends FindPresenter<LocalFlt> {
    private FrmPickLocal vsr;

    @Autowired
    private LocalQryMapper localQryMapper;

    private boolean seleccionarVarios = false;

    public SeleccionarLocalPst() {
        setSearchAtBeginning(true);
    }

    @Override
    public void afterConfiguration() {
        ((ZonePanel) vsr.pnlForm).setLabelTitle("Seleccionar Local");
        vsr.lblTitGrid.setText("Lista de Locales");
    }

    protected ColumnInfo[] getColumnInfo() {
        ColumnInfo[] columns = null;
        columns = new ColumnInfo[]{
                new SelectorColumn(),
                new ColumnInfo("Código", "coLocal", 50, ColumnInfo.LEFT),
                new ColumnInfo("Descripción", "deLocal", 300, ColumnInfo.LEFT)
        };
        return columns;
    }

    protected void registerActions() {
        actionRsr.registerAction("Seleccionar", new SelectRowsAction(), getGridProvider())
                .closeViewAtEnd();
    }



    public List<Local> getValues(LocalFlt obj) {
        return localQryMapper.findList(obj);
    }


    public static void main(String[] args) {

        AppCtx.instance().setLocalId(new LocalId("001", "005"));

        new LookAndFeelManager().initialize();
        Application.instance().init();
        Presenter presenter = PstMgr.instance().getPst(SeleccionarLocalPst.class);
//        presenter.setBackBean(new LocalF());
        presenter.init();

    }
}
