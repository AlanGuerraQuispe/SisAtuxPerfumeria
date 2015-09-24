package com.atux.desktop.inventario;

import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 27/11/2014.
 */
public class FrmOrdenar {
    public JPanel pnlMain;
    public JPanel pnlForm;
    public JLabel lblOrdenarPor;
    public JComboBox cmbOrdenarPor;
    public JTextField txtDiferencia;
    public JPanel footerPanel;
    public JLabel lblF10;
    public JLabel lblEsc;
    public JRadioButton chkTodos;
    public JRadioButton chkDiferencia;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Ordenar", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:d:grow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(400, 180));
        pnlMain.setPreferredSize(new Dimension(400, 180));
        pnlForm.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:20dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow", "fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:10dlu:noGrow"));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblOrdenarPor = new JLabel();
        lblOrdenarPor.setText("Ordenar Por");
        pnlForm.add(lblOrdenarPor, cc.xy(1, 1));
        cmbOrdenarPor = new JComboBox();
        pnlForm.add(cmbOrdenarPor, cc.xyw(3, 1, 5));
        chkTodos = new JRadioButton();
        chkTodos.setText("Todos");
        pnlForm.add(chkTodos, cc.xy(1, 5));
        txtDiferencia = new JTextField();
        pnlForm.add(txtDiferencia, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        chkDiferencia = new JRadioButton();
        chkDiferencia.setText("Mayores Diferencias");
        pnlForm.add(chkDiferencia, cc.xy(1, 7));
        final JSeparator separator1 = new JSeparator();
        pnlForm.add(separator1, cc.xyw(1, 3, 7, CellConstraints.FILL, CellConstraints.FILL));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,right:d:grow", "fill:d:grow"));
        pnlMain.add(footerPanel, cc.xy(1, 3));
        lblF10 = new JLabel();
        lblF10.setText("F10 = Aceptar");
        footerPanel.add(lblF10, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(3, 1));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(chkDiferencia);
        buttonGroup.add(chkTodos);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
