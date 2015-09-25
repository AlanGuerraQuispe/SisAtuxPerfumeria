package atux.vistas.venta.aperCierre;

import atux.controllers.CSimpleModelo;
import atux.modelbd.CajaPago;
import atux.util.common.AtuxSearch;
import atux.util.common.AtuxUtility;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author user
 */
public class ITestigo extends javax.swing.JDialog {

    private final Log logger = LogFactory.getLog(getClass());
    
    public ITestigo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Configuraci�n de Impresora");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
        AtuxUtility.centrarVentana(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageTestigo = new elaprendiz.gui.panel.PanelImage();
        lblTestigo = new javax.swing.JLabel();
        lblColaImpresion = new javax.swing.JLabel();
        txtColaImpresion = new elaprendiz.gui.textField.TextField();
        txtDeImpresora = new elaprendiz.gui.textField.TextField();
        bntAceptar = new elaprendiz.gui.button.ButtonRect();
        bntSalir = new elaprendiz.gui.button.ButtonRect();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelImageTestigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        lblTestigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTestigo.setForeground(new java.awt.Color(255, 255, 255));
        lblTestigo.setText("Impresora :");

        lblColaImpresion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblColaImpresion.setForeground(new java.awt.Color(255, 255, 255));
        lblColaImpresion.setText("Cola de impresi�n :");

        txtColaImpresion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtDeImpresora.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        bntAceptar.setBackground(new java.awt.Color(51, 153, 255));
        bntAceptar.setText("Aceptar");
        bntAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAceptarActionPerformed(evt);
            }
        });

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImageTestigoLayout = new javax.swing.GroupLayout(panelImageTestigo);
        panelImageTestigo.setLayout(panelImageTestigoLayout);
        panelImageTestigoLayout.setHorizontalGroup(
            panelImageTestigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageTestigoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelImageTestigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblColaImpresion)
                    .addComponent(lblTestigo))
                .addGap(13, 13, 13)
                .addGroup(panelImageTestigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDeImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelImageTestigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelImageTestigoLayout.createSequentialGroup()
                            .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtColaImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelImageTestigoLayout.setVerticalGroup(
            panelImageTestigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageTestigoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelImageTestigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTestigo)
                    .addComponent(txtDeImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelImageTestigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblColaImpresion)
                    .addComponent(txtColaImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelImageTestigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageTestigo, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImageTestigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        CSimpleModelo csimple = new CSimpleModelo();
        CajaPago cajaPago = csimple.getTestigo();
        txtDeImpresora.setText(cajaPago.getDeCaja());
        txtColaImpresion.setText(cajaPago.getDeColaImpresion());        
    }//GEN-LAST:event_formWindowOpened

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_bntSalirActionPerformed

    private void bntAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAceptarActionPerformed
        if(StringUtils.isNotEmpty(txtColaImpresion.getText()))
        try {
            if (JOptionPane.showConfirmDialog(this,
                    "Desea grabar la cola de impresi�n de testigo?",
                    "Mensaje del Sistema",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
                return;
            AtuxSearch.updateColaTestigo(txtColaImpresion.getText().trim());
        } catch (SQLException ex) {
            logger.error("Error al grabar la cola de impresion \n de Testigo" + ex.getMessage());
        }
    }//GEN-LAST:event_bntAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntAceptar;
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private javax.swing.JLabel lblColaImpresion;
    private javax.swing.JLabel lblTestigo;
    private elaprendiz.gui.panel.PanelImage panelImageTestigo;
    public elaprendiz.gui.textField.TextField txtColaImpresion;
    public elaprendiz.gui.textField.TextField txtDeImpresora;
    // End of variables declaration//GEN-END:variables
}