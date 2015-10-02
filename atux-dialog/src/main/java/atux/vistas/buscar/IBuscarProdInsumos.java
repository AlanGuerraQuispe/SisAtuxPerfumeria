package atux.vistas.buscar;

import atux.controllers.CProductoLocal;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTablaProducto;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @Alan Guerra
 */
public class IBuscarProdInsumos extends javax.swing.JDialog implements ActionListener{

    private ProductoLocal       prodLocal;
    private ModeloTablaProducto mtp;
    private ModeloTablaProducto mtpInsumos;
    private ModeloTablaProducto mtpPromocion;
    private int COL_DE_PRODUCTO=1;
    CProductoLocal   cProdLoc;

    private final Log logger = LogFactory.getLog(getClass());

    public IBuscarProdInsumos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblAviso.setVisible(false);
        inicializarCarga(AtuxVariables.arrayProductos);
        AtuxUtility.moveFocus(txtDato);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        AtuxUtility.centrarVentana(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        panelImageBusquedaProducto = new elaprendiz.gui.panel.PanelImage();
        jPanelBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDato = new elaprendiz.gui.textField.TextField(new Dimension(400,50));
        jPanelOpciones = new javax.swing.JPanel();
        rbTodos = new javax.swing.JRadioButton();
        rbCaballeros = new javax.swing.JRadioButton();
        rbDamas = new javax.swing.JRadioButton();
        jPanelProductos = new javax.swing.JPanel();
        jPanelProducto = new javax.swing.JPanel();
        jScrollPaneProducto = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanelDetalleProd = new javax.swing.JPanel();
        jlGrupo = new javax.swing.JLabel();
        jLDGrupo = new javax.swing.JLabel();
        jLinea = new javax.swing.JLabel();
        jDLinea = new javax.swing.JLabel();
        jlCajas = new javax.swing.JLabel();
        jlDCajas = new javax.swing.JLabel();
        jlPrecio = new javax.swing.JLabel();
        jlDPrecio = new javax.swing.JLabel();
        jlStock = new javax.swing.JLabel();
        jlDStock = new javax.swing.JLabel();
        jPanelInsumos = new javax.swing.JPanel();
        jScrollPaneInsumos = new javax.swing.JScrollPane();
        tblInsumosProductos = new javax.swing.JTable();
        jPanelDetalleInsumos = new javax.swing.JPanel();
        lblAviso = new javax.swing.JLabel();
        jPanelPromocion = new javax.swing.JPanel();
        jScrollPanePromocion = new javax.swing.JScrollPane();
        tblProductosPromocion = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(860, 339));
        jPanel1.setLayout(new java.awt.BorderLayout());

        panelImageBusquedaProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImageBusquedaProducto.setPreferredSize(new java.awt.Dimension(860, 320));
        panelImageBusquedaProducto.setLayout(new java.awt.BorderLayout());

        jPanelBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelBusqueda.setAlignmentX(0.9F);
        jPanelBusqueda.setAlignmentY(0.9F);
        jPanelBusqueda.setAutoscrolls(true);
        jPanelBusqueda.setOpaque(false);
        jPanelBusqueda.setPreferredSize(new java.awt.Dimension(860, 40));
        jPanelBusqueda.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Buscar Por:");
        jPanelBusqueda.add(jLabel1);
        jLabel1.setBounds(121, 10, 80, 22);

        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }

            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

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
        jPanelBusqueda.add(txtDato);
        txtDato.setBounds(211, 8, 221, 25);

        jPanelOpciones.setBackground(new java.awt.Color(51, 153, 255));

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup.add(rbTodos);
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
        buttonGroup.add(rbCaballeros);
        rbCaballeros.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbCaballeros.setForeground(new java.awt.Color(255, 255, 255));
        rbCaballeros.setText("Caballeros");
        rbCaballeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCaballerosActionPerformed(evt);
            }
        });

        rbDamas.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup.add(rbDamas);
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

        jPanelBusqueda.add(jPanelOpciones);
        jPanelOpciones.setBounds(449, 6, 370, 30);

        panelImageBusquedaProducto.add(jPanelBusqueda, java.awt.BorderLayout.PAGE_START);

        jPanelProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelProductos.setForeground(new java.awt.Color(255, 255, 255));
        jPanelProductos.setOpaque(false);
        jPanelProductos.setPreferredSize(new java.awt.Dimension(860, 300));
        jPanelProductos.setLayout(null);

        jPanelProducto.setAutoscrolls(true);

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

        javax.swing.GroupLayout jPanelProductoLayout = new javax.swing.GroupLayout(jPanelProducto);
        jPanelProducto.setLayout(jPanelProductoLayout);
        jPanelProductoLayout.setHorizontalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        jPanelProductoLayout.setVerticalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        jPanelProductos.add(jPanelProducto);
        jPanelProducto.setBounds(1, 1, 840, 310);

        jPanelDetalleProd.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleProd.setAlignmentX(0.0F);
        jPanelDetalleProd.setAlignmentY(0.0F);
        jPanelDetalleProd.setEnabled(false);
        jPanelDetalleProd.setLayout(null);

        jlGrupo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlGrupo.setForeground(new java.awt.Color(153, 0, 0));
        jlGrupo.setText("Grupo :");
        jlGrupo.setName(""); // NOI18N
        jPanelDetalleProd.add(jlGrupo);
        jlGrupo.setBounds(10, 4, 40, 20);

        jLDGrupo.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLDGrupo.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jLDGrupo);
        jLDGrupo.setBounds(50, 4, 140, 20);

        jLinea.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLinea.setForeground(new java.awt.Color(153, 0, 0));
        jLinea.setText("Linea :");
        jPanelDetalleProd.add(jLinea);
        jLinea.setBounds(190, 4, 36, 20);

        jDLinea.setFont(new java.awt.Font("Tahoma", 1, 11));
        jDLinea.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jDLinea);
        jDLinea.setBounds(230, 4, 150, 20);

        jlCajas.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlCajas.setForeground(new java.awt.Color(153, 0, 0));
        jlCajas.setText("Cajas :");
        jPanelDetalleProd.add(jlCajas);
        jlCajas.setBounds(380, 4, 43, 20);

        jlDCajas.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDCajas.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jlDCajas);
        jlDCajas.setBounds(420, 4, 65, 20);

        jlPrecio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlPrecio.setForeground(new java.awt.Color(153, 0, 0));
        jlPrecio.setText("Precio :");
        jPanelDetalleProd.add(jlPrecio);
        jlPrecio.setBounds(490, 4, 42, 20);

        jlDPrecio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDPrecio.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jlDPrecio);
        jlDPrecio.setBounds(532, 4, 40, 20);

        jlStock.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlStock.setForeground(new java.awt.Color(153, 0, 0));
        jlStock.setText("Stock :");
        jPanelDetalleProd.add(jlStock);
        jlStock.setBounds(572, 4, 39, 20);

        jlDStock.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlDStock.setForeground(new java.awt.Color(255, 255, 255));
        jPanelDetalleProd.add(jlDStock);
        jlDStock.setBounds(611, 4, 25, 20);

        jPanelProductos.add(jPanelDetalleProd);
        jPanelDetalleProd.setBounds(1, 312, 840, 32);

        jPanelInsumos.setPreferredSize(new java.awt.Dimension(769, 105));

        jScrollPaneInsumos.setPreferredSize(new java.awt.Dimension(452, 400));

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
        tblInsumosProductos.setPreferredSize(new java.awt.Dimension(820, 400));
        tblInsumosProductos.setRowHeight(18);
        jScrollPaneInsumos.setViewportView(tblInsumosProductos);

        javax.swing.GroupLayout jPanelInsumosLayout = new javax.swing.GroupLayout(jPanelInsumos);
        jPanelInsumos.setLayout(jPanelInsumosLayout);
        jPanelInsumosLayout.setHorizontalGroup(
            jPanelInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneInsumos, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        jPanelInsumosLayout.setVerticalGroup(
            jPanelInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInsumosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneInsumos, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
        );

        jPanelProductos.add(jPanelInsumos);
        jPanelInsumos.setBounds(1, 334, 840, 130);

        jPanelDetalleInsumos.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleInsumos.setEnabled(false);
        jPanelDetalleInsumos.setLayout(null);

        lblAviso.setFont(new java.awt.Font("Segoe Print", 1, 17));
        lblAviso.setForeground(new java.awt.Color(255, 255, 0));
        lblAviso.setText("Promoción");
        jPanelDetalleInsumos.add(lblAviso);
        lblAviso.setBounds(40, 0, 100, 23);

        jPanelProductos.add(jPanelDetalleInsumos);
        jPanelDetalleInsumos.setBounds(1, 465, 840, 27);

        jPanelPromocion.setPreferredSize(new java.awt.Dimension(787, 90));

        jScrollPanePromocion.setPreferredSize(new java.awt.Dimension(452, 250));

        tblProductosPromocion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto promoción", "Unidad", "Prec.Venta", "Descuento", "Prec.Público", "Stock", "Bono"
            }
        ));
        tblProductosPromocion.setToolTipText("Promociones");
        tblProductosPromocion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProductosPromocion.setPreferredSize(new java.awt.Dimension(820, 200));
        tblProductosPromocion.setRowHeight(18);
        jScrollPanePromocion.setViewportView(tblProductosPromocion);

        javax.swing.GroupLayout jPanelPromocionLayout = new javax.swing.GroupLayout(jPanelPromocion);
        jPanelPromocion.setLayout(jPanelPromocionLayout);
        jPanelPromocionLayout.setHorizontalGroup(
            jPanelPromocionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePromocion, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        jPanelPromocionLayout.setVerticalGroup(
            jPanelPromocionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanePromocion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        jPanelProductos.add(jPanelPromocion);
        jPanelPromocion.setBounds(1, 493, 840, 55);

        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(0);
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.addActionListener(this);
        jPanelProductos.add(btnSalir);
        btnSalir.setBounds(700, 565, 110, 19);

        panelImageBusquedaProducto.add(jPanelProductos, java.awt.BorderLayout.CENTER);

        jPanel1.add(panelImageBusquedaProducto, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 842, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(WindowEvent evt) {
        AtuxUtility.moveFocus(txtDato);
    }

    public ProductoLocal getProductoLocal() {
        return prodLocal;
    }

    private void txtDatoKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if (tblProductos.getSelectedRow() != -1) {
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());
               // if (prodLocal.getCaStockDisponible() == 0) {
                  //  AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", txtDato);
                //} else {
                    logger.info("ProductoLocal: " + prodLocal.getProducto().getDeProducto());
                    AtuxVariables.vAceptar = true;
                    this.setVisible(false);
                //}
            }
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            prodLocal = null;
            AtuxVariables.vAceptar = false;
        }
//        else if (tblProductos != null && !(evt.getKeyCode() == KeyEvent.VK_ESCAPE) &&
//                (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)) {
//            checkTeclaPresionada(evt, tblProductos);
//        }
    }

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyReleased
        //if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());
              String vFindText       = txtDato.getText().trim();
            if(vFindText.length()==0) {
                inicializarCarga(AtuxVariables.arrayProductos);
            }

            if(vFindText.length()>2) {
                ArrayList<ProductoLocal> prodSoloHombres = new ArrayList();
                ProductoLocal productoLoc;
                Iterator<ProductoLocal> iter = AtuxVariables.arrayProductos.iterator();
                while (iter.hasNext()) {
                    productoLoc = iter.next();

                    if (productoLoc.getProducto().getDeProducto().indexOf(vFindText) != -1) {
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
//            if ((currentCodigo.equalsIgnoreCase(vFindText) || currentProducto.substring(0, vFindText.length()).equalsIgnoreCase(vFindText))) {
//                   bntBuscar.doClick();
//            }
//            if(prodLocal.getCaStockDisponible()==0){
//                AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", null);
//            }
            //else {
                //logger.info("ProductoLocal: "+prodLocal.getProducto().getDeProducto());
                //AtuxVariables.vAceptar = true;
                //this.setVisible(false);
            //}
        //}
//        else
//        if (!(evt.getKeyCode() == KeyEvent.VK_ESCAPE) && !(evt.getKeyCode() == KeyEvent.VK_O) &&
//                (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)) {
//            AtuxGridUtils.buscarDescripcion(evt, tblProductos, txtDato, COL_DE_PRODUCTO);
//            prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());
//            mostrarDetalleProducto(prodLocal);
//        }
    }//GEN-LAST:event_txtDatoKeyReleased

    private void txtDatoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyTyped
        AtuxUtility.convertirMayuscula(evt);
    }//GEN-LAST:event_txtDatoKeyTyped

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        AtuxUtility.moveFocus(txtDato);
    }//GEN-LAST:event_formFocusGained

    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {
        inicializarCarga(AtuxVariables.arrayProductos);
        AtuxUtility.moveFocus(txtDato);
    }

    private void rbCaballerosActionPerformed(java.awt.event.ActionEvent evt) {
        ProductoLocal productoLoc;
        ArrayList<ProductoLocal> prodSoloHombres = new ArrayList();

        Iterator<ProductoLocal> iter = AtuxVariables.arrayProductos.iterator();
        while (iter.hasNext()){
            productoLoc = iter.next();

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

        Iterator<ProductoLocal> iter = AtuxVariables.arrayProductos.iterator();
        while (iter.hasNext()){
            productoLoc = iter.next();

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
                //if (prodLocal.getCaStockDisponible() == 0) {
                //    AtuxUtility.showMessage(null, "Producto no cuenta con stock disponible !!!", txtDato);
                //} else {
                 //   logger.info("ProductoLocal: " + prodLocal.getProducto().getDeProducto());
                    AtuxVariables.vAceptar = true;
                    this.setVisible(false);
                //}
            }
        }

        if (evt.getClickCount() == 1) {
            if (tblProductos.getSelectedRow() != -1) {
                prodLocal = (ProductoLocal) mtp.getFila(tblProductos.getSelectedRow());
                mostrarDetalleProducto(prodLocal);
                txtDato.setText(prodLocal.getProducto().getDeProducto());
                prodLocal = null;
                AtuxUtility.moveFocus(txtDato);
            }
        }
    }

    public final void inicializarCarga(ArrayList lista){
        mtp = new ModeloTablaProducto(lista,ModeloTablaProducto.PRO_LISTA);
        this.tblProductos.setModel(mtp);
        this.tblProductos.repaint();
        mtp.fireTableDataChanged();

        Helper.ajustarSoloAnchoColumnas(tblProductos, ModeloTablaProducto.anchoColumnas);
        prodLocal =(ProductoLocal)mtp.getFila(0);
        mostrarDetalleProducto(prodLocal);
    }

    private void checkTeclaPresionada(KeyEvent evt, JTable tblProductos) {
        AtuxGridUtils.aceptarTeclaPresionada(evt, tblProductos, txtDato, COL_DE_PRODUCTO);
        //AtuxGridUtils.buscarDescripcion(evt, tblProductos, txtDato, 0);
        prodLocal =(ProductoLocal)mtp.getFila(tblProductos.getSelectedRow());
        mostrarDetalleProducto(prodLocal);
    }

    private void mostrarDetalleProducto(ProductoLocal prodLocal) {

        jLDGrupo.setText(prodLocal.getDeJ5());
        jDLinea.setText(prodLocal.getDeJ1());

        jlDCajas.setText(prodLocal.getProducto().getDeUnidadProducto());

        if  (prodLocal.getInProdFraccionado().equals("S")){
            double preCaja = Helper.redondear(prodLocal.getVaPrecioPublico()*prodLocal.getVaFraccion(), 2);
            jlDPrecio.setText(String.valueOf(preCaja));
            int stockCaja = Math.round(prodLocal.getCaStockDisponible()/prodLocal.getVaFraccion());
            jlDStock.setText(String.valueOf(stockCaja));
        }
        else{
            jlDPrecio.setText(prodLocal.getVaPrecioPublico().toString());
            jlDStock.setText(String.valueOf(prodLocal.getCaStockDisponible()));
        }

        cProdLoc = new CProductoLocal();
        this.tblInsumosProductos.removeAll();

        mtpInsumos = new ModeloTablaProducto(cProdLoc.getInsumosProductos(prodLocal), ModeloTablaProducto.PRO_INSUMOS);
        this.tblInsumosProductos.setModel(mtpInsumos);

//      prodLocal.setVaPrecioPublico(mtpInsumos.getPrecioVentaInsumos());
//      prodLocal.setVaVenta(mtpInsumos.getPrecioVentaInsumos());
//      prodLocal.setVaCostoProducto(mtpInsumos.getCostoInsumos());

        this.tblProductos.repaint();
        mtp.fireTableDataChanged();

        this.tblInsumosProductos.repaint();
        mtpInsumos.fireTableDataChanged();
        Helper.ajustarSoloAnchoColumnas(tblInsumosProductos, ModeloTablaProducto.anchoColumnas);

        this.tblProductosPromocion.removeAll();

        mtpPromocion = new ModeloTablaProducto(cProdLoc.getProductosPromocion(prodLocal.getCoProducto()),ModeloTablaProducto.PRO_PROMOCION);
        this.tblProductosPromocion.setModel(mtpPromocion);
        Helper.ajustarSoloAnchoColumnas(tblProductosPromocion, ModeloTablaProducto.anchoColumnasPromo);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            dispose();
            prodLocal = null;
            AtuxVariables.vAceptar = false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jDLinea;
    private javax.swing.JLabel jLDGrupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLinea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelDetalleInsumos;
    private javax.swing.JPanel jPanelDetalleProd;
    private javax.swing.JPanel jPanelInsumos;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelProducto;
    private javax.swing.JPanel jPanelProductos;
    private javax.swing.JPanel jPanelPromocion;
    private javax.swing.JScrollPane jScrollPaneInsumos;
    private javax.swing.JScrollPane jScrollPaneProducto;
    private javax.swing.JScrollPane jScrollPanePromocion;
    private javax.swing.JLabel jlCajas;
    private javax.swing.JLabel jlDCajas;
    private javax.swing.JLabel jlDPrecio;
    private javax.swing.JLabel jlDStock;
    private javax.swing.JLabel jlGrupo;
    private javax.swing.JLabel jlPrecio;
    private javax.swing.JLabel jlStock;
    private javax.swing.JLabel lblAviso;
    private elaprendiz.gui.panel.PanelImage panelImageBusquedaProducto;
    private javax.swing.JRadioButton rbCaballeros;
    private javax.swing.JRadioButton rbDamas;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblInsumosProductos;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductosPromocion;
    private elaprendiz.gui.textField.TextField txtDato;
    // End of variables declaration//GEN-END:variables
}