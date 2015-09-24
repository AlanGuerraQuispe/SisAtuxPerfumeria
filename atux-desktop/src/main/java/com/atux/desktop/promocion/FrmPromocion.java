package com.atux.desktop.promocion;

import com.aw.swing.mvp.ui.RoundedBorder;
import com.aw.swing.mvp.ui.RoundedPanel;
import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JAVA on 16/11/2014.
 */
public class FrmPromocion {
    public JPanel pnlMain;
    public JPanel pnlForm;
    //    public JTextField txtBuscar;
    public JTable tblGrid;
    public JScrollPane pnlResult;
    public JPanel pnlGrid;
    public JPanel pnlTitGrid;
    public JCheckBox chkSel;
    public JLabel lblTitGrid;
    public JTextField txtBuscar;
    public JLabel lblBuscar;
    public JPanel footerPanel;
    public JLabel lblEsc;
    public JTextField txtFeIchanicio;
    public JTextField txtFeFin;
    public JLabel lblFechaInicio;
    public JLabel lblFeFin;
    public JTextField txtCoPromocion;
    public JTextField txtNoPromocion;
    public JTextField txtNoProveedor;
    public JLabel lblProveedor;
    public JTextField txtMensajeLargo;
    public JTextField txtMensajeCorto;
    public JTextField txtObservacion;
    public JCheckBox chkEstado;
    public JLabel lblFechaFin;
    public JTextField txtFechaFin;
    public JLabel lblDeMensajeLargo;
    public JLabel lblDeMensajeCorto;
    public JLabel lblObservacion;
    public JLabel lblEstado;
    public JTable tblGrid1;
    public JCheckBox chkSel1;
    public JScrollPane pnlResult1;
    public JLabel lblTitGrid1;
    public JPanel pnlTitGrid1;
    public JPanel pnlGrid1;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Promociones", true);
        pnlGrid = new RoundedPanel(new BorderLayout(), 0);
        pnlGrid.setBorder(new RoundedBorder(0));
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        pnlMain = new JPanel();
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:90dlu:noGrow,top:4dlu:noGrow,fill:105dlu:noGrow,top:4dlu:noGrow,center:d:grow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(900, 535));
        pnlMain.setPreferredSize(new Dimension(900, 535));
        pnlGrid.setLayout(new FormLayout("fill:d:grow", "fill:16dlu:noGrow,top:4dlu:noGrow,fill:d:grow"));
        pnlGrid.setPreferredSize(new Dimension(453, 465));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlGrid, cc.xy(1, 3));
        pnlTitGrid = new JPanel();
        pnlTitGrid.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:100dlu:noGrow", "fill:16dlu:noGrow"));
        pnlGrid.add(pnlTitGrid, cc.xy(1, 1));
        chkSel = new JCheckBox();
        chkSel.setText("");
        pnlTitGrid.add(chkSel, cc.xy(1, 1));
        lblTitGrid = new JLabel();
        lblTitGrid.setText("Lista de Productos");
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 1));
        pnlResult = new JScrollPane();
        pnlGrid.add(pnlResult, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid = new JTable();
        tblGrid.setOpaque(true);
        pnlResult.setViewportView(tblGrid);
        pnlForm.setLayout(new FormLayout("fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:10dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:4dlu:grow", "fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow"));
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblBuscar = new JLabel();
        lblBuscar.setText("C�digo ");
        pnlForm.add(lblBuscar, cc.xy(1, 1));
        txtCoPromocion = new JTextField();
        pnlForm.add(txtCoPromocion, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblProveedor = new JLabel();
        lblProveedor.setText("Promoci�n");
        pnlForm.add(lblProveedor, cc.xy(3, 1));
        txtNoPromocion = new JTextField();
        pnlForm.add(txtNoPromocion, cc.xyw(3, 3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblDeMensajeLargo = new JLabel();
        lblDeMensajeLargo.setText("Mensaje POS");
        pnlForm.add(lblDeMensajeLargo, cc.xy(1, 5));
        lblDeMensajeCorto = new JLabel();
        lblDeMensajeCorto.setText("Msj. Corto POS");
        pnlForm.add(lblDeMensajeCorto, cc.xy(1, 7));
        txtMensajeLargo = new JTextField();
        pnlForm.add(txtMensajeLargo, cc.xyw(3, 5, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtMensajeCorto = new JTextField();
        pnlForm.add(txtMensajeCorto, cc.xyw(3, 7, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtObservacion = new JTextField();
        pnlForm.add(txtObservacion, cc.xyw(11, 7, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblObservacion = new JLabel();
        lblObservacion.setText("Observaci�n");
        pnlForm.add(lblObservacion, cc.xy(9, 7));
        txtFechaFin = new JTextField();
        pnlForm.add(txtFechaFin, cc.xy(17, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblFechaFin = new JLabel();
        lblFechaFin.setText("Fecha Fin");
        pnlForm.add(lblFechaFin, cc.xy(17, 1));
        lblFeFin = new JLabel();
        lblFeFin.setHorizontalAlignment(0);
        lblFeFin.setText("al");
        pnlForm.add(lblFeFin, cc.xy(15, 3));
        chkEstado = new JCheckBox();
        chkEstado.setText("");
        pnlForm.add(chkEstado, cc.xy(15, 5));
        lblEstado = new JLabel();
        lblEstado.setText("Estado");
        pnlForm.add(lblEstado, cc.xy(13, 5));
        txtFeIchanicio = new JTextField();
        pnlForm.add(txtFeIchanicio, cc.xy(13, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblFechaInicio = new JLabel();
        lblFechaInicio.setText("Fecha Inicio");
        pnlForm.add(lblFechaInicio, cc.xy(13, 1));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("right:max(d;4px):grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 7));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(1, 1));
        pnlGrid1 = new JPanel();
        pnlGrid1.setLayout(new FormLayout("fill:d:noGrow", "center:d:noGrow,top:4dlu:noGrow,center:d:grow"));
        pnlMain.add(pnlGrid1, cc.xy(1, 5));
        pnlTitGrid1 = new JPanel();
        pnlTitGrid1.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:grow", "fill:16dlu:noGrow"));
        pnlGrid1.add(pnlTitGrid1, cc.xy(1, 1));
        chkSel1 = new JCheckBox();
        chkSel1.setText("");
        pnlTitGrid1.add(chkSel1, cc.xy(1, 1));
        lblTitGrid1 = new JLabel();
        lblTitGrid1.setText("Locales");
        pnlTitGrid1.add(lblTitGrid1, cc.xy(3, 1));
        pnlResult1 = new JScrollPane();
        pnlGrid1.add(pnlResult1, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid1 = new JTable();
        pnlResult1.setViewportView(tblGrid1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
