package atux.vistas.buscar;

import atux.controllers.CProducto;
import atux.core.JAbstractModelBD;
import atux.modelbd.Producto;
import atux.modelgui.ModeloTablaMaestroProductos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxUtility;
import atux.vistas.catalogo.IProductoInsumo;
import java.awt.Component;
import javax.swing.*;


public class BuscarMaestroProducto extends javax.swing.JPanel {

    JOptionPane op;
    int opcion = -1;
    String Filtro;
    private ModeloTablaMaestroProductos mtp;
    private CProducto cp;
    IProductoInsumo base;

    public BuscarMaestroProducto(int opcion) {
        initComponents();
        lbAviso.setVisible(false);
        this.opcion = opcion;
        switch (opcion) {
            case 0:
                Filtro = "I";
                break;
            case 1:
                Filtro = "A";
                break;
            default:
                Filtro = "T";
        }
        mtp = new ModeloTablaMaestroProductos(Filtro);
        tblProducto.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblProducto, ModeloTablaMaestroProductos.anchoColumnasBusqueda);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 50, false);
        AtuxGridUtils.setearPrimerRegistro(tblProducto, txtDato, 1);
        AtuxUtility.moveFocus(txtDato);
    }

    public BuscarMaestroProducto(JPanel base) {
        initComponents();
        this.base = (IProductoInsumo)base;
        lbAviso.setVisible(false);
        mtp = new ModeloTablaMaestroProductos();
        tblProducto.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblProducto, ModeloTablaMaestroProductos.anchoColumnasBusqueda);
        Helper.setFiltraEntrada(txtDato.getDocument(), FiltraEntrada.SOLO_LETRAS, 50, false);
        AtuxGridUtils.setearPrimerRegistro(tblProducto, txtDato, 1);
        AtuxUtility.moveFocus(txtDato);
    }

    private void getOptionPane() {
        if (op != null) {
            return;
        }
        Component pdr = this.getParent();
        while (pdr.getParent() != null) {
            if (pdr instanceof JOptionPane) {
                op = (JOptionPane) pdr;
                break;
            }
            pdr = pdr.getParent();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        panelCurves1 = new elaprendiz.gui.panel.PanelCurves();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDato = new elaprendiz.gui.textField.TextField();
        btnBuscar = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnSeleccionar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        lbAviso = new elaprendiz.gui.label.LabelRect();

        setOpaque(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Buscar Por:");

        txtDato.setPreferredSize(new java.awt.Dimension(250, 27));
        txtDato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDatoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDatoKeyTyped(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jScrollPane1.setOpaque(false);

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CODIGO", "DESCRIPCION", "UNIDAD", "LABORATORIO"
            }
        ));
        tblProducto.setOpaque(false);
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCurves1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);

        btnSeleccionar.setBackground(new java.awt.Color(51, 153, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText("Debe Seleccionar un Proveedor");
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCurves1.add(jPanel3, java.awt.BorderLayout.CENTER);

        panelImage1.add(panelCurves1, java.awt.BorderLayout.CENTER);

        add(panelImage1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        getOptionPane();
        op.setValue(1);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (!txtDato.getText().isEmpty()) {
            String Condicion = null;
            String Estado = "";
            txtDato.setText(txtDato.getText().toString().toUpperCase());
            if (opcion == 0) {
                Estado = "I";
            } else if (opcion == 1) {
                Estado = "A";
            }
            Condicion = Estado;
            if (opcion != -1) {
                Condicion = " and Es_PRODUCTO='" + Estado + "' and de_producto like '%" + txtDato.getText() + "%'";
            }

            this.mtp = new ModeloTablaMaestroProductos(Condicion);
            this.tblProducto.setModel(mtp);
        } else {
            switch (opcion) {
                case 0:
                    mtp = new ModeloTablaMaestroProductos("I");
                case 1:
                    mtp = new ModeloTablaMaestroProductos("A");
                    break;
                default:
                    mtp = new ModeloTablaMaestroProductos("T");
            }

            tblProducto.setModel(mtp);
        }
        Helper.ajustarSoloAnchoColumnas(tblProducto, ModeloTablaMaestroProductos.anchoColumnasBusqueda);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        getOptionPane();
        if (this.tblProducto.getSelectedRow() != -1) {
            cp = new CProducto();
            cp.setProducto(mtp.getFila(tblProducto.getSelectedRow()));
            if(base !=null){
                base.txtCodigoPrincipioActivo.setText(((Producto)cp.getProducto()).getCoProducto());
                base.txtDescripcionPrincipioActivo.setText(((Producto)cp.getProducto()).getDeProducto());
                base.setVaCosto(cp.getProducto().getVaCostoProducto());
                base.setVaPrecioPublico(cp.getProducto().getVaPrecioPublico());
            }
            op.setValue(1);
        } else {
            lbAviso.setVisible(true);
        }

    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        if (evt.getClickCount() == 1) {
            Producto pro = (Producto) mtp.getFila(tblProducto.getSelectedRow());
            txtDato.setText(pro.getDeProducto());
            AtuxUtility.moveFocus(txtDato);
        }
        if (evt.getClickCount() == 2) {
            getOptionPane();
            if (tblProducto.getSelectedRow() != -1) {
                cp = new CProducto();
                cp.setProducto(mtp.getFila(tblProducto.getSelectedRow()));
                if(base !=null){
                    base.txtCodigoPrincipioActivo.setText(((Producto)cp.getProducto()).getCoProducto());
                    base.txtDescripcionPrincipioActivo.setText(((Producto)cp.getProducto()).getDeProducto());
                    base.setVaCosto(cp.getProducto().getVaCostoProducto());
                    base.setVaPrecioPublico(cp.getProducto().getVaPrecioPublico());
                    base.setDeUnidadInsumo(cp.getProducto().getDeUnidadProducto());
                }
                op.setValue(1);
            } else {
                lbAviso.setVisible(true);
            }
        }
    }

    private void txtDatoKeyTyped(java.awt.event.KeyEvent evt) {
        AtuxUtility.convertirMayuscula(evt);
    }

    private void formFocusGained(java.awt.event.FocusEvent evt) {
        AtuxUtility.moveFocus(txtDato);
    }

    private void txtDatoKeyPressed(java.awt.event.KeyEvent evt) {
        AtuxGridUtils.aceptarTeclaPresionada(evt, tblProducto, txtDato, 1);
    }

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {
        AtuxGridUtils.buscarDescripcion(evt, tblProducto, txtDato, 1);
    }

    public JAbstractModelBD getMaestroProducto() {
        if (cp != null) {
            return (JAbstractModelBD) cp.getProducto();
        }
        return null;
    }

    public JLabel getAviso() {
        return lbAviso;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnBuscar;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private elaprendiz.gui.panel.PanelCurves panelCurves1;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tblProducto;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables

}
