package com.atux.desktop.donacion;

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
public class FrmFNDonacion {
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
    public JButton btnSearch;
    public JPanel footerPanel;
    public JLabel lblF2;
    public JLabel lblEsc;
    public JPanel pnlGrid1;
    public JCheckBox chkSel1;
    public JTable tblGrid1;
    public JScrollPane pnlResult1;
    public JPanel pnlTitGrid1;
    public JLabel lblTitGrid1;
    public JTextField txtFechaInicio;
    public JTextField txtFechaFin;
    public JLabel lblFeInicio;
    public JLabel lblFeFin;
    public JTextField txtDeInstituci�n;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Donaciones", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:58dlu:noGrow,top:4dlu:noGrow,fill:105dlu:noGrow,top:4dlu:noGrow,fill:80dlu:noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(900, 435));
        pnlMain.setPreferredSize(new Dimension(900, 435));
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
        lblTitGrid.setText("Lista de Instituciones");
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 1));
        pnlResult = new JScrollPane();
        pnlGrid.add(pnlResult, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid = new JTable();
        tblGrid.setOpaque(true);
        pnlResult.setViewportView(tblGrid);
        pnlForm.setLayout(new FormLayout("fill:50dlu:noGrow,left:4dlu:noGrow,fill:100dlu:noGrow,left:4dlu:noGrow,fill:70dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:10dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow", "fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow"));
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblBuscar = new JLabel();
        lblBuscar.setText("Donaci�n");
        pnlForm.add(lblBuscar, cc.xy(1, 1));
        txtFechaInicio = new JTextField();
        txtFechaInicio.setText("");
        pnlForm.add(txtFechaInicio, cc.xy(7, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblFeInicio = new JLabel();
        lblFeInicio.setText("Fecha de Cobertura");
        pnlForm.add(lblFeInicio, cc.xy(5, 1));
        lblFeFin = new JLabel();
        lblFeFin.setHorizontalAlignment(0);
        lblFeFin.setText("al");
        pnlForm.add(lblFeFin, cc.xy(9, 1));
        txtFechaFin = new JTextField();
        pnlForm.add(txtFechaFin, cc.xy(11, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        btnSearch = new JButton();
        btnSearch.setText("Buscar");
        pnlForm.add(btnSearch, cc.xy(1, 3));
        txtDeInstituci�n = new JTextField();
        pnlForm.add(txtDeInstituci�n, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,right:max(d;4px):grow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 7));
        lblF2 = new JLabel();
        lblF2.setText("F5 = Ver");
        footerPanel.add(lblF2, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(3, 1));
        pnlGrid1 = new JPanel();
        pnlGrid1.setLayout(new FormLayout("fill:d:grow", "fill:16dlu:noGrow,top:4dlu:noGrow,fill:d:grow"));
        pnlMain.add(pnlGrid1, cc.xy(1, 5));
        pnlTitGrid1 = new JPanel();
        pnlTitGrid1.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "fill:16dlu:noGrow"));
        pnlGrid1.add(pnlTitGrid1, cc.xy(1, 1));
        chkSel1 = new JCheckBox();
        chkSel1.setText("");
        pnlTitGrid1.add(chkSel1, cc.xy(1, 1));
        lblTitGrid1 = new JLabel();
        lblTitGrid1.setText("Detalle Local - Cobertura");
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
