package atux.vistas.utilitario;

import atux.modelbd.Usuario;
import atux.modelgui.ModeloTablaBitacora;
import atux.util.Helper;
import java.awt.Component;
import javax.swing.JOptionPane;


public class PanelBitacora extends javax.swing.JPanel {

    
    private Usuario us;
    JOptionPane op;
    public PanelBitacora(Usuario us) {
        initComponents();
        this.us = us;
        this.txtNomUsuario.setText(us.getNombreCompleto());
        ModeloTablaBitacora mtb = new ModeloTablaBitacora(us.getPrimaryKey());
        this.tblBitacora.setModel(mtb);
        Helper.ajustarAnchoColumnas(tblBitacora);
        
    }
    
    
    private void getOptionPane()
    {
        if(op != null)
        {
            return;
        }
        Component pdr =this.getParent(); 
        while(pdr.getParent() != null)
        {
            if(pdr instanceof JOptionPane)
            {
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        txtNomUsuario = new elaprendiz.gui.textField.TextFieldRectIcon();
        buttonRect1 = new elaprendiz.gui.button.ButtonRect();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBitacora = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(620, 300));
        setLayout(new java.awt.BorderLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        txtNomUsuario.setEditable(false);
        txtNomUsuario.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtNomUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/user.png"))); // NOI18N
        txtNomUsuario.setPreferredSize(new java.awt.Dimension(250, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 8);
        jPanel1.add(txtNomUsuario, gridBagConstraints);

        buttonRect1.setBackground(new java.awt.Color(51, 153, 255));
        buttonRect1.setText("Cerrar");
        buttonRect1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRect1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 8);
        jPanel1.add(buttonRect1, gridBagConstraints);

        panelImage1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(379, 250));

        tblBitacora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblBitacora);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelImage1.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(panelImage1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void buttonRect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRect1ActionPerformed
 getOptionPane();
 op.setValue(1);
}//GEN-LAST:event_buttonRect1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect buttonRect1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tblBitacora;
    private elaprendiz.gui.textField.TextFieldRectIcon txtNomUsuario;
    // End of variables declaration//GEN-END:variables
}
