package com.atux.desktop.consulta;

import com.aw.swing.mvp.ui.RoundedBorder;
import com.aw.swing.mvp.ui.RoundedPanel;
import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import elaprendiz.gui.textField.TextField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 18/11/2014.
 */
public class FrmVentaPorVendedor {
    public JPanel pnlMain;
    public JTextField txtFeInicio;
    public JTextField txtFeFin;
    public JPanel pnlForm;
    public JTextField txtVaTotal;
    public JPanel pnlGrid;
    public JCheckBox chkSel;
    public JLabel lblTitGrid;
    public JPanel pnlTitGrid;
    public JLabel lblFeInicio;
    public JLabel lblFeFin;
    public JLabel lblVaTotal;
    public JPanel footerPanel;
    public JLabel lblF7;
    public JLabel lblEsc;
    public JTable tblGrid;
    public JScrollPane pnlResult;
    public JLabel lblGrupoA;
    public JTextField txtGrupoATotal;
    public JLabel lblTotalPP;
    public JTextField txtTotalBono;
    public JTextField txtNuPedidos;
    public JLabel lblGrupoAPorc;
    public JLabel lblGrupoPPPorc;
    public JTextField txtTotalPP;
    public JTextField txtMargenPP;
    public JTextField txtMargenGrupoA;
    public JLabel lblTotalBono;
    public JLabel lblNuPedidos;
    public JLabel lblF5;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Venta x Vendedor", true);
        pnlGrid = new RoundedPanel(new BorderLayout(), 0);
        pnlGrid.setBorder(new RoundedBorder(0));
        txtFeFin = new TextField();
        txtFeInicio = new TextField();
        txtVaTotal = new TextField();

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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:d:noGrow,top:4dlu:noGrow,fill:210dlu:noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(1200, 500));
        pnlMain.setPreferredSize(new Dimension(1200, 500));
        pnlGrid.setLayout(new FormLayout("fill:d:grow", "fill:16dlu:noGrow,top:4dlu:noGrow,fill:d:grow"));
        pnlGrid.setPreferredSize(new Dimension(450, 240));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlGrid, cc.xy(1, 3));
        pnlTitGrid = new JPanel();
        pnlTitGrid.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:d:grow", "fill:16dlu:noGrow"));
        pnlGrid.add(pnlTitGrid, cc.xy(1, 1));
        chkSel = new JCheckBox();
        chkSel.setText("");
        pnlTitGrid.add(chkSel, cc.xy(1, 1));
        lblTitGrid = new JLabel();
        lblTitGrid.setText("Relaci�n de Ventas por Vendedor");
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 1));
        pnlResult = new JScrollPane();
        pnlGrid.add(pnlResult, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid = new JTable();
        pnlResult.setViewportView(tblGrid);
        pnlForm.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:10dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:40dlu:noGrow", "fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow"));
        pnlForm.setMinimumSize(new Dimension(380, 108));
        pnlForm.setPreferredSize(new Dimension(380, 108));
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblFeInicio = new JLabel();
        lblFeInicio.setText("Rango Evaluaci�n");
        pnlForm.add(lblFeInicio, cc.xy(1, 1));
        txtFeInicio = new JTextField();
        pnlForm.add(txtFeInicio, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblFeFin = new JLabel();
        lblFeFin.setText("al");
        pnlForm.add(lblFeFin, cc.xy(5, 1));
        txtFeFin = new JTextField();
        pnlForm.add(txtFeFin, cc.xy(7, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblVaTotal = new JLabel();
        lblVaTotal.setText("Vta. Total");
        pnlForm.add(lblVaTotal, cc.xy(9, 1));
        txtVaTotal = new JTextField();
        pnlForm.add(txtVaTotal, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblGrupoA = new JLabel();
        lblGrupoA.setText("Grupo A");
        pnlForm.add(lblGrupoA, cc.xy(13, 1));
        txtGrupoATotal = new JTextField();
        pnlForm.add(txtGrupoATotal, cc.xy(15, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblGrupoAPorc = new JLabel();
        lblGrupoAPorc.setText("% Grupo A");
        pnlForm.add(lblGrupoAPorc, cc.xy(17, 1));
        txtMargenGrupoA = new JTextField();
        pnlForm.add(txtMargenGrupoA, cc.xy(19, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtNuPedidos = new JTextField();
        pnlForm.add(txtNuPedidos, cc.xy(23, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblNuPedidos = new JLabel();
        lblNuPedidos.setText("# Pedidos");
        pnlForm.add(lblNuPedidos, cc.xy(21, 1));
        lblTotalPP = new JLabel();
        lblTotalPP.setText("PP");
        pnlForm.add(lblTotalPP, cc.xy(13, 3));
        txtTotalPP = new JTextField();
        pnlForm.add(txtTotalPP, cc.xy(15, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblGrupoPPPorc = new JLabel();
        lblGrupoPPPorc.setText("% Grupo PP");
        pnlForm.add(lblGrupoPPPorc, cc.xy(17, 3));
        txtMargenPP = new JTextField();
        pnlForm.add(txtMargenPP, cc.xy(19, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblTotalBono = new JLabel();
        lblTotalBono.setText("Total Bono");
        pnlForm.add(lblTotalBono, cc.xy(21, 3));
        txtTotalBono = new JTextField();
        pnlForm.add(txtTotalBono, cc.xy(23, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:noGrow,left:4dlu:noGrow,right:d:grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 5));
        lblF7 = new JLabel();
        lblF7.setAutoscrolls(true);
        lblF7.setBackground(new Color(-13395480));
        lblF7.setForeground(new Color(-16777216));
        lblF7.setOpaque(false);
        lblF7.setText("F7 = Reporte");
        footerPanel.add(lblF7, cc.xy(3, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(5, 1));
        lblF5 = new JLabel();
        lblF5.setText("F5 = Detalle");
        footerPanel.add(lblF5, cc.xy(1, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
