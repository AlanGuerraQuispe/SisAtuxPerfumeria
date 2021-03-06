package atux.presenter;

import com.aw.swing.mvp.ui.GradientPanel;
import com.aw.swing.mvp.ui.RoundedBorder;
import com.aw.swing.mvp.ui.RoundedPanel;
import com.aw.swing.mvp.ui.ZonePanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MATRIX-JAVA on 18/2/2015.
 */
public class FrmIngresarDonacion {
    public JPanel pnlMain;
    public JPanel pnlForm;
    public JPanel pnlGrid;
    public JTable tblGrid;
    public JCheckBox chkSel;
    public JLabel lblTitGrid;
    public JPanel pnlTitGrid;
    public JScrollPane pnlResult;
    public JComboBox cmbInstitucion;
    public JTextField txtMonto;
    public JLabel lblMonto;
    public JLabel lblInstitucion;
    public JButton btnAgregar;
    public JPanel footerPanel;
    public JLabel lblF10;
    public JLabel lblEsc;

    private void createUIComponents() {
        pnlForm = new ZonePanel("Ingresar Donación", true);
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
        pnlMain.setLayout(new FormLayout("fill:d:grow", "fill:70dlu:noGrow,top:4dlu:noGrow,fill:d:grow,top:4dlu:noGrow,center:16dlu:noGrow"));
        pnlMain.setMinimumSize(new Dimension(300, 260));
        pnlMain.setPreferredSize(new Dimension(300, 260));
        pnlForm.setLayout(new FormLayout("fill:40dlu:noGrow,left:4dlu:noGrow,fill:30dlu:noGrow,left:4dlu:noGrow,fill:50dlu:grow", "center:12dlu:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,fill:12dlu:noGrow"));
        pnlForm.setMaximumSize(new Dimension(230, 78));
        pnlForm.setMinimumSize(new Dimension(230, 78));
        CellConstraints cc = new CellConstraints();
        pnlMain.add(pnlForm, cc.xy(1, 1));
        lblInstitucion = new JLabel();
        lblInstitucion.setText("Institución");
        pnlForm.add(lblInstitucion, cc.xy(1, 1));
        cmbInstitucion = new JComboBox();
        pnlForm.add(cmbInstitucion, cc.xyw(3, 1, 3));
        btnAgregar = new JButton();
        btnAgregar.setText("Agregar");
        pnlForm.add(btnAgregar, cc.xy(1, 5));
        lblMonto = new JLabel();
        lblMonto.setText("Monto");
        pnlForm.add(lblMonto, cc.xy(1, 3));
        txtMonto = new JTextField();
        pnlForm.add(txtMonto, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        pnlGrid.setLayout(new FormLayout("fill:143dlu:noGrow", "center:d:noGrow,top:4dlu:noGrow,center:d:grow"));
        pnlMain.add(pnlGrid, cc.xy(1, 3));
        pnlTitGrid = new JPanel();
        pnlTitGrid.setLayout(new FormLayout("fill:16dlu:noGrow,left:4dlu:noGrow,fill:80dlu:noGrow", "fill:16dlu:noGrow"));
        pnlGrid.add(pnlTitGrid, cc.xy(1, 1));
        chkSel = new JCheckBox();
        chkSel.setText("");
        pnlTitGrid.add(chkSel, cc.xy(1, 1));
        lblTitGrid = new JLabel();
        lblTitGrid.setText("Lista de Donaciones");
        pnlTitGrid.add(lblTitGrid, cc.xy(3, 1));
        pnlResult = new JScrollPane();
        pnlGrid.add(pnlResult, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tblGrid = new JTable();
        pnlResult.setViewportView(tblGrid);
        footerPanel = new JPanel();
        footerPanel.setLayout(new FormLayout("fill:50dlu:noGrow,left:4dlu:noGrow,fill:80dlu:noGrow", "fill:16dlu:noGrow"));
        pnlMain.add(footerPanel, cc.xy(1, 5));
        lblF10 = new JLabel();
        lblF10.setText("F10 - Aceptar");
        footerPanel.add(lblF10, cc.xy(1, 1));
        lblEsc = new JLabel();
        lblEsc.setHorizontalAlignment(4);
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
