package com.atux.desktop.central;

import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 24/11/2014.
 */
public class FrmFraccionamientoCrud {
    public JPanel pnlMain;
    public JTextField txtCoProducto;
    public JTextField txtDeProducto;
    public JLabel lblCoProducto;
    public JTextField txtUnidad;
    public JLabel lblDeProducto;
    public JLabel lblUnidad;
    public JTextField txtDeLaboratorio;
    public JLabel lblDeLaboratorio;
    public JTextField txtMinFraccion;
    public JTextField txtVaFraccionNuevo;
    public JTextField txtMaxFraccion;
    public JLabel lblFraccion;
    public JSeparator sep1;
    public JLabel lblVaPrecioPromedio;
    public JLabel lblVaPrecioPVP;
    public JLabel lblVaPrecioVenta;
    public JLabel lblVaPrecioPVPFinal;
    public JPanel footerPanel;
    public JLabel lblF10;
    public JPanel pnlForm;
    public JLabel lblEsc;
    public JTextField txtLocal;
    public JLabel lblCoLocal;
    public JCheckBox chkFraccion;
    public JTextField txtVaFraccionActual;
    public JCheckBox chkActivo;
    public JTextField txtFeVigencia;
    public JLabel lblFeVigencia;
    public JLabel lblActivo;
    public JLabel lblVaFraccionActual;
    public JTextField txtUnidadFraccionNuevo;
    public JLabel lblCorta;
    public JLabel lblLarga;
    public JButton btnPickMinFraccion;
    public JButton btnPickMaxFraccion;
    public JCheckBox chkInFraccionMatriz;
    public JLabel lblInFraccionMatriz;
    public JCheckBox chkActivarFraccion;
    public JLabel lblActivarFraccion;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Ingresar o Modificar Fracci�n", true);
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
        pnlMain.setMinimumSize(new Dimension(560, 480));
        pnlMain.setPreferredSize(new Dimension(560, 480));
        pnlMain.setRequestFocusEnabled(true);
        pnlForm.setLayout(new FormLayout("fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:20dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:20dlu:noGrow,left:4dlu:noGrow,fill:50dlu:noGrow,left:4dlu:noGrow,fill:20dlu:noGrow", "center:12dlu:noGrow,top:4dlu:noGrow,center:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,fill:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,center:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow,top:4dlu:noGrow,fill:12dlu:noGrow"));
        pnlForm.setMinimumSize(new Dimension(400, 284));
        pnlForm.setPreferredSize(new Dimension(400, 284));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblCoProducto = new JLabel();
        lblCoProducto.setText("C�digo");
        pnlForm.add(lblCoProducto, cc.xy(1, 5));
        lblDeProducto = new JLabel();
        lblDeProducto.setText("Producto");
        pnlForm.add(lblDeProducto, cc.xyw(3, 5, 5));
        txtCoProducto = new JTextField();
        txtCoProducto.setText("");
        pnlForm.add(txtCoProducto, cc.xy(1, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtDeProducto = new JTextField();
        pnlForm.add(txtDeProducto, cc.xyw(3, 7, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblDeLaboratorio = new JLabel();
        lblDeLaboratorio.setText("Laboratorio");
        pnlForm.add(lblDeLaboratorio, cc.xy(1, 9));
        txtDeLaboratorio = new JTextField();
        pnlForm.add(txtDeLaboratorio, cc.xyw(1, 11, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        final JSeparator separator1 = new JSeparator();
        pnlForm.add(separator1, cc.xyw(1, 13, 13, CellConstraints.FILL, CellConstraints.FILL));
        lblFraccion = new JLabel();
        lblFraccion.setText("Fracci�n");
        pnlForm.add(lblFraccion, cc.xy(1, 19));
        sep1 = new JSeparator();
        pnlForm.add(sep1, cc.xyw(1, 21, 13, CellConstraints.FILL, CellConstraints.FILL));
        lblVaPrecioPromedio = new JLabel();
        lblVaPrecioPromedio.setText("Tipo Descripci�n");
        pnlForm.add(lblVaPrecioPromedio, cc.xy(1, 23));
        lblVaPrecioPVP = new JLabel();
        lblVaPrecioPVP.setText("*Max Fracci�n");
        pnlForm.add(lblVaPrecioPVP, cc.xy(3, 23));
        lblVaPrecioVenta = new JLabel();
        lblVaPrecioVenta.setText("Valor Fracci�n");
        pnlForm.add(lblVaPrecioVenta, cc.xy(7, 23));
        lblVaPrecioPVPFinal = new JLabel();
        lblVaPrecioPVPFinal.setText("*Min Fracci�n");
        pnlForm.add(lblVaPrecioPVPFinal, cc.xy(9, 23));
        txtVaFraccionNuevo = new JTextField();
        pnlForm.add(txtVaFraccionNuevo, cc.xy(7, 25, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtMinFraccion = new JTextField();
        pnlForm.add(txtMinFraccion, cc.xy(3, 25, CellConstraints.FILL, CellConstraints.DEFAULT));
        txtMaxFraccion = new JTextField();
        pnlForm.add(txtMaxFraccion, cc.xy(9, 25, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblCoLocal = new JLabel();
        lblCoLocal.setText("Local");
        pnlForm.add(lblCoLocal, cc.xy(1, 1));
        txtLocal = new JTextField();
        pnlForm.add(txtLocal, cc.xyw(1, 3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblUnidad = new JLabel();
        lblUnidad.setText("*** Fracci�n x Local");
        pnlForm.add(lblUnidad, cc.xyw(9, 1, 5));
        txtUnidad = new JTextField();
        pnlForm.add(txtUnidad, cc.xyw(9, 3, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        chkFraccion = new JCheckBox();
        chkFraccion.setText("");
        pnlForm.add(chkFraccion, cc.xy(3, 19));
        lblVaFraccionActual = new JLabel();
        lblVaFraccionActual.setText("Valor Fracci�n");
        pnlForm.add(lblVaFraccionActual, cc.xy(7, 19));
        txtVaFraccionActual = new JTextField();
        pnlForm.add(txtVaFraccionActual, cc.xy(9, 19, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblActivo = new JLabel();
        lblActivo.setText("Activo");
        pnlForm.add(lblActivo, cc.xy(1, 29));
        chkActivo = new JCheckBox();
        chkActivo.setText("");
        pnlForm.add(chkActivo, cc.xy(3, 29));
        lblFeVigencia = new JLabel();
        lblFeVigencia.setText("Vigencia");
        pnlForm.add(lblFeVigencia, cc.xy(7, 29));
        txtFeVigencia = new JTextField();
        pnlForm.add(txtFeVigencia, cc.xy(9, 29, CellConstraints.FILL, CellConstraints.DEFAULT));
        lblCorta = new JLabel();
        lblCorta.setText("Corta");
        pnlForm.add(lblCorta, cc.xy(1, 27));
        lblLarga = new JLabel();
        lblLarga.setText("Larga");
        pnlForm.add(lblLarga, cc.xy(1, 25));
        txtUnidadFraccionNuevo = new JTextField();
        pnlForm.add(txtUnidadFraccionNuevo, cc.xyw(3, 27, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        btnPickMinFraccion = new JButton();
        btnPickMinFraccion.setIcon(new ImageIcon(getClass().getResource("/images/zoom.png")));
        btnPickMinFraccion.setText("");
        pnlForm.add(btnPickMinFraccion, cc.xy(5, 25));
        btnPickMaxFraccion = new JButton();
        btnPickMaxFraccion.setIcon(new ImageIcon(getClass().getResource("/images/zoom.png")));
        btnPickMaxFraccion.setText("");
        pnlForm.add(btnPickMaxFraccion, cc.xy(11, 25));
        lblInFraccionMatriz = new JLabel();
        lblInFraccionMatriz.setText("Matriz - Fraccionable");
        pnlForm.add(lblInFraccionMatriz, cc.xyw(9, 9, 5));
        chkInFraccionMatriz = new JCheckBox();
        chkInFraccionMatriz.setText("");
        pnlForm.add(chkInFraccionMatriz, cc.xy(9, 11));
        lblActivarFraccion = new JLabel();
        lblActivarFraccion.setText("Activar Fracci�n");
        pnlForm.add(lblActivarFraccion, cc.xy(1, 15));
        chkActivarFraccion = new JCheckBox();
        chkActivarFraccion.setText("");
        pnlForm.add(chkActivarFraccion, cc.xy(3, 15));
        final JSeparator separator2 = new JSeparator();
        pnlForm.add(separator2, cc.xyw(1, 17, 13, CellConstraints.FILL, CellConstraints.FILL));
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,right:d:grow", "fill:16dlu:noGrow"));
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
