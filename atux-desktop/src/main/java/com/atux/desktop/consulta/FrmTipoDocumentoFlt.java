package com.atux.desktop.consulta;

import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

/**
 * Created by MATRIX-JAVA on 25/11/2014.
 */
public class FrmTipoDocumentoFlt {
    public JPanel pnlMain;
    public JLabel lblTipoDocumento;
    public JComboBox cmbTipoDocumento;
    public JPanel pnlForm;
    public JPanel footerPanel;
    public JLabel lblF10;
    public JLabel lblEsc;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Seleccionar Tipo Documento", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:noGrow", "center:max(d;4px):noGrow,top:4dlu:noGrow,fill:16dlu:noGrow"));
        pnlForm.setLayout(new FormLayout("fill:72dlu:noGrow,fill:4dlu:noGrow,fill:100dlu:noGrow", "center:12dlu:noGrow"));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblTipoDocumento = new JLabel();
        lblTipoDocumento.setText("Tipo Documento");
        pnlForm.add(lblTipoDocumento, cc.xy(1, 1));
        cmbTipoDocumento = new JComboBox();
        pnlForm.add(cmbTipoDocumento, cc.xy(3, 1));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:grow"));
        pnlMain.add(footerPanel, cc.xy(1, 3));
        lblF10 = new JLabel();
        lblF10.setText("F10 = Aceptar");
        footerPanel.add(lblF10, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Cancelar");
        footerPanel.add(lblEsc, cc.xy(3, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
