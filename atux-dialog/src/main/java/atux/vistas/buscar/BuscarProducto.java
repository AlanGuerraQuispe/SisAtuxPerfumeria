package atux.vistas.buscar;

import atux.controllers.CProductoLocal;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTablaProducto;
import atux.util.Helper;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import atux.vistas.venta.PanelAccionProdInsumos;

public final class BuscarProducto extends javax.swing.JInternalFrame {

    private ProductoLocal prodLocal;
    private ModeloTablaProducto mtp;
    private ModeloTablaProducto mtpInsumos;
    private ModeloTablaProducto mtpPromocion;
    CProductoLocal cProdLoc;
    PanelAccionProdInsumos panelInsumos;

    public BuscarProducto(PanelAccionProdInsumos panel) {
        super();
        initComponents();
        panelInsumos = panel;
        inicializarCarga(AtuxVariables.arrayProductos);
        AtuxUtility.moveFocus(txtDato);
        Helper.ajustarSoloAnchoColumnas(tblProductosPromocion, ModeloTablaProducto.anchoColumnasPromo);
        Helper.ajustarSoloAnchoColumnas(tblInsumosProductos, ModeloTablaProducto.anchoColumnas);
    }

    private void mostrarDetalleProducto(ProductoLocal prodLocal) {

        cProdLoc = new CProductoLocal();
        this.tblInsumosProductos.removeAll();

        mtpInsumos = new ModeloTablaProducto(cProdLoc.getInsumosProductos(prodLocal), ModeloTablaProducto.PRO_INSUMOS);
        this.tblInsumosProductos.setModel(mtpInsumos);

        this.tblInsumosProductos.repaint();
        mtpInsumos.fireTableDataChanged();
        Helper.ajustarSoloAnchoColumnas(tblInsumosProductos, ModeloTablaProducto.anchoColumnas);

        this.tblProductosPromocion.removeAll();

        mtpPromocion = new ModeloTablaProducto(cProdLoc.getProductosPromocion(prodLocal.getCoProducto()),ModeloTablaProducto.PRO_PROMOCION);
        this.tblProductosPromocion.setModel(mtpPromocion);
        Helper.ajustarSoloAnchoColumnas(tblProductosPromocion, ModeloTablaProducto.anchoColumnasPromo);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupEstado = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDato = new elaprendiz.gui.textField.TextField(new Dimension(400,50));
        jPanelOpciones = new javax.swing.JPanel();
        rbTodos = new javax.swing.JRadioButton();
        rbCaballeros = new javax.swing.JRadioButton();
        rbDamas = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPaneProducto = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanelDetalleProd = new javax.swing.JPanel();
        jPanelInsumos = new javax.swing.JPanel();
        jScrollPaneInsumos = new javax.swing.JScrollPane();
        tblInsumosProductos = new javax.swing.JTable();
        jPanelDetalleInsumos = new javax.swing.JPanel();
        lblAviso = new javax.swing.JLabel();
        jPanelPromocion = new javax.swing.JPanel();
        jScrollPanePromocion = new javax.swing.JScrollPane();
        tblProductosPromocion = new javax.swing.JTable();
        pnlActionButtons = new javax.swing.JPanel();
        bntSalir = new elaprendiz.gui.button.ButtonRect();

        setTitle("Buscar Perfume");
        setNormalBounds(new java.awt.Rectangle(0, 0, 500, 400));
        setPreferredSize(new java.awt.Dimension(860, 645));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Buscar Por:");

        txtDato.setAlignmentX(0.0F);
        txtDato.setPreferredSize(new java.awt.Dimension(180, 27));
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

        jPanelOpciones.setBackground(new java.awt.Color(51, 153, 255));

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroupEstado.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setSelected(true);
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });

        rbCaballeros.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroupEstado.add(rbCaballeros);
        rbCaballeros.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbCaballeros.setForeground(new java.awt.Color(255, 255, 255));
        rbCaballeros.setText("Caballeros");
        rbCaballeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCaballerosActionPerformed(evt);
            }
        });

        rbDamas.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroupEstado.add(rbDamas);
        rbDamas.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbDamas.setForeground(new java.awt.Color(255, 255, 255));
        rbDamas.setText("Damas");
        rbDamas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDamasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOpcionesLayout = new javax.swing.GroupLayout(jPanelOpciones);
        jPanelOpciones.setLayout(jPanelOpcionesLayout);
        jPanelOpcionesLayout.setHorizontalGroup(
            jPanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(rbTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbCaballeros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbDamas)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanelOpcionesLayout.setVerticalGroup(
            jPanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbCaballeros, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbDamas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 78, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(10, 10, 10)
                    .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(17, 17, 17)
                    .addComponent(jPanelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 78, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 9, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 9, Short.MAX_VALUE)))
        );

        panelImage1.add(jPanel1);
        jPanel1.setBounds(0, 0, 856, 50);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.BorderLayout());

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto", "Unidad", "Pre.Venta", "Descuento", "Pre.Público", "Stock", "Bono"
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPaneProducto.setViewportView(tblProductos);

        jPanel6.add(jScrollPaneProducto, java.awt.BorderLayout.CENTER);

        panelImage1.add(jPanel6);
        jPanel6.setBounds(0, 50, 846, 250);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);

        jPanelDetalleProd.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleProd.setAlignmentX(0.0F);
        jPanelDetalleProd.setAlignmentY(0.0F);
        jPanelDetalleProd.setEnabled(false);
        jPanelDetalleProd.setLayout(null);
        jPanel2.add(jPanelDetalleProd);
        jPanelDetalleProd.setBounds(2, 2, 843, 10);

        tblInsumosProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto complementario", "Unidad", "Pre.Venta", "Descuento", "Prec.Público", "Stock", "Bono"
            }
        ));
        tblInsumosProductos.setToolTipText("Productos base");
        tblInsumosProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblInsumosProductos.setPreferredSize(new java.awt.Dimension(820, 100));
        tblInsumosProductos.setRowHeight(18);
        jScrollPaneInsumos.setViewportView(tblInsumosProductos);

        javax.swing.GroupLayout jPanelInsumosLayout = new javax.swing.GroupLayout(jPanelInsumos);
        jPanelInsumos.setLayout(jPanelInsumosLayout);
        jPanelInsumosLayout.setHorizontalGroup(
            jPanelInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneInsumos, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
        );
        jPanelInsumosLayout.setVerticalGroup(
            jPanelInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.add(jPanelInsumos);
        jPanelInsumos.setBounds(3, 11, 843, 87);

        jPanelDetalleInsumos.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleInsumos.setEnabled(false);
        jPanelDetalleInsumos.setLayout(null);

        lblAviso.setFont(new java.awt.Font("Segoe Print", 1, 17));
        lblAviso.setForeground(new java.awt.Color(255, 255, 0));
        lblAviso.setText("Promoción");
        jPanelDetalleInsumos.add(lblAviso);
        lblAviso.setBounds(40, 0, 100, 23);

        jPanel2.add(jPanelDetalleInsumos);
        jPanelDetalleInsumos.setBounds(2, 99, 843, 27);

        jScrollPanePromocion.setPreferredSize(new java.awt.Dimension(452, 100));

        tblProductosPromocion.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null}
//            },
//            new String [] {
//                "Código", "Producto promoción", "Unidad", "Prec.Venta", "Descuento", "Prec.Público", "Stock", "Bono"
//            }
                new Object [][] {
                        {null, null},
                        {null, null}
                },
                new String [] {
                        "Promoción", "Laboratorio"
                }
        ));
        tblProductosPromocion.setToolTipText("Promociones");
        tblProductosPromocion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProductosPromocion.setPreferredSize(new java.awt.Dimension(820, 100));
        tblProductosPromocion.setRowHeight(18);
        jScrollPanePromocion.setViewportView(tblProductosPromocion);

        javax.swing.GroupLayout jPanelPromocionLayout = new javax.swing.GroupLayout(jPanelPromocion);
        jPanelPromocion.setLayout(jPanelPromocionLayout);
        jPanelPromocionLayout.setHorizontalGroup(
            jPanelPromocionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePromocion, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
        );
        jPanelPromocionLayout.setVerticalGroup(
            jPanelPromocionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePromocion, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel2.add(jPanelPromocion);
        jPanelPromocion.setBounds(2, 126, 843, 48);

        panelImage1.add(jPanel2);
        jPanel2.setBounds(0, 300, 846, 173);

        pnlActionButtons.setOpaque(false);
        pnlActionButtons.setLayout(null);

        bntSalir.setBackground(new java.awt.Color(51, 153, 255));
        bntSalir.setText("Salir");
        bntSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSalirActionPerformed(evt);
            }
        });
        pnlActionButtons.add(bntSalir);
        bntSalir.setBounds(711, 6, 63, 25);

        panelImage1.add(pnlActionButtons);
        pnlActionButtons.setBounds(0, 475, 846, 40);

        getContentPane().add(panelImage1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ProductoLocal getProductoLocal() {
        return prodLocal;
    }

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {
        closeWindow(true);        
    }

    private void txtDatoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            closeWindow(true);
        }
    }

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {
        String vFindText       = txtDato.getText().trim();
        if(vFindText.length()==0) {
            inicializarCarga(AtuxVariables.arrayProductos);
        }

        if(vFindText.length()>2) {
            ArrayList<ProductoLocal> prodSoloHombres = new ArrayList();
            ProductoLocal productoLoc;
            Iterator iter = AtuxVariables.arrayProductos.iterator();
            while (iter.hasNext()) {
                productoLoc = (ProductoLocal) iter.next();

                if (productoLoc.getProducto().getDeProducto().contains(vFindText)) {
                    prodSoloHombres.add(productoLoc);
                }
            }
            if(prodSoloHombres.size()!=0)
                inicializarCarga(prodSoloHombres);

            else
                inicializarCarga(AtuxVariables.arrayProductos);

        }
        else{
            inicializarCarga(AtuxVariables.arrayProductos);
        }
    }

    private void txtDatoKeyTyped(java.awt.event.KeyEvent evt) {
        AtuxUtility.convertirMayuscula(evt);
    }

    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {
        inicializarCarga(AtuxVariables.arrayProductos);
        AtuxUtility.moveFocus(txtDato);
    }

    private void rbCaballerosActionPerformed(java.awt.event.ActionEvent evt) {
        ProductoLocal productoLoc;
        ArrayList<ProductoLocal> prodSoloHombres = new ArrayList();

        Iterator iter = AtuxVariables.arrayProductos.iterator();
        while (iter.hasNext()){
            productoLoc = (ProductoLocal) iter.next();

            if(productoLoc.getProducto().getInGenero().equals("M")){
                prodSoloHombres.add(productoLoc);
            }
        }
        inicializarCarga(prodSoloHombres);
        AtuxUtility.moveFocus(txtDato);
    }

    private void rbDamasActionPerformed(java.awt.event.ActionEvent evt) {
        ProductoLocal productoLoc;
        ArrayList<ProductoLocal> prodSoloMujeres = new ArrayList();

        Iterator iter = AtuxVariables.arrayProductos.iterator();
        while (iter.hasNext()){
            productoLoc = (ProductoLocal) iter.next();

            if(productoLoc.getProducto().getInGenero().equals("F")){
                prodSoloMujeres.add(productoLoc);
            }
        }
        inicializarCarga(prodSoloMujeres);
        AtuxUtility.moveFocus(txtDato);
    }

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            if (tblProductos.getSelectedRow() != -1) {
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());
               closeWindow(true);
            }
        }

        if (evt.getClickCount() == 1) {
            if (tblProductos.getSelectedRow() != -1) {
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());
                mostrarDetalleProducto(prodLocal);
                txtDato.setText(prodLocal.getProducto().getDeProducto());
                prodLocal = null;
            }
        }
    }

    private void formFocusGained(java.awt.event.FocusEvent evt) {
        AtuxUtility.moveFocus(txtDato);
    }

    private void formFocusLost(java.awt.event.FocusEvent evt) {
        this.toBack();
        this.setEnabled(false);
    }

    public final void inicializarCarga(ArrayList lista){
        mtp = new ModeloTablaProducto(lista,ModeloTablaProducto.PRO_LISTA);
        this.tblProductos.setModel(mtp);
        this.tblProductos.repaint();
        mtp.fireTableDataChanged();

        Helper.ajustarSoloAnchoColumnas(tblProductos, ModeloTablaProducto.anchoColumnas);
    }

    public void closeWindow(boolean pAceptar) {              
        dispose();
        this.setVisible(false);
        if (pAceptar) panelInsumos.agregarItem();
        prodLocal = null;
        AtuxVariables.vAceptar = pAceptar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private javax.swing.ButtonGroup buttonGroupEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelDetalleInsumos;
    private javax.swing.JPanel jPanelDetalleProd;
    private javax.swing.JPanel jPanelInsumos;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelPromocion;
    private javax.swing.JScrollPane jScrollPaneInsumos;
    private javax.swing.JScrollPane jScrollPaneProducto;
    private javax.swing.JScrollPane jScrollPanePromocion;
    private javax.swing.JLabel lblAviso;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlActionButtons;
    private javax.swing.JRadioButton rbCaballeros;
    private javax.swing.JRadioButton rbDamas;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblInsumosProductos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductosPromocion;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables
       
}
