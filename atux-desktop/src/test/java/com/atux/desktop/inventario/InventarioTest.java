package com.atux.desktop.inventario;

import atux.config.AppConfig;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.MainWindow;
import com.atux.bean.inventario.LaboratorioInventario;
import com.atux.bean.inventario.LaboratorioInventarioFlt;
import com.atux.comun.context.AppCtx;
import com.atux.desktop.TestPersistenceBase;
import com.atux.dominio.inventario.InventarioService;
import com.atux.enums.TipoTomaInventarioEnum;
import com.aw.swing.spring.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * Created by MATRIX-JAVA on 15/2/2015.
 */
@TransactionConfiguration(defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:appCtx/appctx-context.xml",
        "classpath*:appCtx/appctx-datasource.xml",
        "classpath*:appCtx/appctx-dialog.xml",
        "classpath*:appCtx/appctx-mybatis.xml"
})
public class InventarioTest extends TestPersistenceBase {

    private static final Logger LOG = LoggerFactory.getLogger(InventarioTest.class);

    @Autowired
    private InventarioService inventarioService;

    @Test
    @Transactional
    public void testGeneraTomaPorLaboratorio() throws Exception {

        LaboratorioInventarioFlt laboratorioInventarioFlt = new LaboratorioInventarioFlt();
        laboratorioInventarioFlt.setTiTomaInventario(TipoTomaInventarioEnum.LABORATORIO);
        LaboratorioInventario laboratorio1 = new LaboratorioInventario();
        laboratorio1.setCoLaboratorio("0266");
        laboratorioInventarioFlt.setSeleccionados(Arrays.asList(laboratorio1));
        inventarioService.iniciarToma(laboratorioInventarioFlt);
    }

    @Override
    public void init() throws Exception {
        Application.instance().init();
        AppCtx appCtx = AppCtx.instance();
        appCtx.setEnviromentTest(true);
        AtuxVariables.vCodigoCompania = appCtx.getLocalId().getCoCompania();
        AtuxVariables.vCodigoLocal = appCtx.getLocalId().getCoLocal();
        AtuxVariables.vImpresoraTestigo = appCtx.getImpresoraTestigo();
        AppConfig.configUsuario("ADMIN", "atuxpro");
        AtuxUtility.setCajaTurno();
        AtuxSearch.existeCajaTurnoImpresora(new Frame());
        MainWindow.obtenerInfoLocal();
        AtuxVariables.vInTicketBoleta = "N";
    }
}
