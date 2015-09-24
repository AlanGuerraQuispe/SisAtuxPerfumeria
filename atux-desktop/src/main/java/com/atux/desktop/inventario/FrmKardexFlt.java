package com.atux.desktop.inventario;

import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 27/11/2014.
 */
public class FrmKardexFlt {
    public JPanel pnlMain;
    public JPanel pnlForm;
    public JLabel lblTipoMovimiento;
    public JComboBox cmbCoMotivo;
    public JComboBox cmbCoGrupoMotivo;
    public JTextField txtIdUsuario;
    public JLabel lblSistema;
    public JLabel lblUsuario;
    public JButton btnPickIdUsuario;
    public JPanel footerPanel;
    public JLabel lblF10;
    public JLabel lblEsc;
    public JTextField txtFeInicio;
    public JTextField txtFeFin;
    public JLabel lblFeInicio;
    public JLabel lblFeFin;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Criterio de B�squeda", true);
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
        pnlForm.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow,left:4dlu:noGrow,fill:20dlu:noGrow,left:4dlu:noGrow,fill:40dlu:noGrow", "fill:12dlu:noGrow,top:4dlu:noGrow,center:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow"));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblTipoMovimiento = new JLabel();
        lblTipoMovimiento.setText("Tipo Movimiento");
        pnlForm.add(lblTipoMovimiento, cc.xy(1, 5));
        lblUsuario = new JLabel();
        lblUsuario.setText("Usuario");
        pnlForm.add(lblUsuario, cc.xy(1, 7));
        txtIdUsuario = new JTextField();
        pnlForm.add(txtIdUsuario, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblSistema = new JLabel();
        lblSistema.setText("Sistema");
        pnlForm.add(lblSistema, cc.xy(1, 3));
        cmbCoMotivo = new JComboBox();
        pnlForm.add(cmbCoMotivo, cc.xyw(3, 5, 5));
        cmbCoGrupoMotivo = new JComboBox();
        pnlForm.add(cmbCoGrupoMotivo, cc.xyw(3, 3, 5));
        lblFeInicio = new JLabel();
        lblFeInicio.setText("Rango de Fecha");
        pnlForm.add(lblFeInicio, cc.xy(1, 1));
        txtFeInicio = new JTextField();
        pnlForm.add(txtFeInicio, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtFeFin = new JTextField();
        pnlForm.add(txtFeFin, cc.xy(7, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblFeFin = new JLabel();
        lblFeFin.setHorizontalAlignment(0);
        lblFeFin.setText("al");
        pnlForm.add(lblFeFin, cc.xy(5, 1));
        btnPickIdUsuario = new JButton();
        btnPickIdUsuario.setIcon(new ImageIcon(getClass().getResource("/images/zoom.png")));
        btnPickIdUsuario.setText("");
        pnlForm.add(btnPickIdUsuario, cc.xy(5, 7));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,right:d:grow", "fill:d:grow"));
        pnlMain.add(footerPanel, cc.xy(1, 3));
        lblF10 = new JLabel();
        lblF10.setText("F10 = Aceptar");
        footerPanel.add(lblF10, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setText("Esc = Salir");
        footerPanel.add(lblEsc, cc.xy(3, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
