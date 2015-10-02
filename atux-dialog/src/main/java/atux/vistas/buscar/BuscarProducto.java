package atux.vistas.buscar;


import atux.controllers.CProductoLocal;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTablaProducto;
import atux.util.Helper;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.aw.swing.mvp.navigation.AWWindowsManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class BuscarProducto extends javax.swing.JInternalFrame {

    private ProductoLocal prodLocal;
    private ModeloTablaProducto mtp;
    private ModeloTablaProducto mtpInsumos;
    private ModeloTablaProducto mtpPromocion;
    private int COL_DE_PRODUCTO=1;
    CProductoLocal cProdLoc;

    private final Log logger = LogFactory.getLog(getClass());

    boolean modal=false;

//    public void show() {
//        super.show();
//        if (this.modal) startModal();
//    }
//
//
//    public void setVisible(boolean value) {
//        super.setVisible(value);
//        if (modal) {
//            if (value) {
//                startModal();
//            } else {
//                stopModal();
//            }
//        }
//    }
//
//    private synchronized void startModal() {
//
//        try {
//            if (SwingUtilities.isEventDispatchThread()) {
//                EventQueue theQueue =
//                        getToolkit().getSystemEventQueue();
//                while (isVisible()) {
//                    AWTEvent event = theQueue.getNextEvent();
//                    Object source = event.getSource();
//                    boolean dispatch=true;
//
//                    if (event instanceof MouseEvent) {
//                        MouseEvent e = (MouseEvent)event;
//                        MouseEvent m =
//                                SwingUtilities.convertMouseEvent ((Component) e.getSource(),e,this);
//                        if (!this.contains(m.getPoint())
//                                && e.getID()!=MouseEvent.MOUSE_DRAGGED) dispatch=false;
//                    }
//
//                    if (dispatch)
//                        if (event instanceof ActiveEvent) {
//                            ((ActiveEvent)event).dispatch();
//                        } else if (source instanceof Component) {
//                            ((Component)source).dispatchEvent(
//                                    event);
//                        } else if (source instanceof MenuComponent) {
//                            ((MenuComponent)source).dispatchEvent(
//                                    event);
//                        } else {
//                            System.err.println(
//                                    "Unable to dispatch: " + event);
//                        }
//                }
//            } else {
//                while (isVisible()) {
//                    wait();
//                }
//            }
//        } catch (InterruptedException ignored) {
//        }
//
//    }
//
//    private synchronized void stopModal() {
//        notifyAll();
//    }
//
//    public void setModal(boolean modal) {
//        this.modal=modal;
//    }
//    public boolean isModal() {
//        return this.modal;
//    }

    public BuscarProducto() {
        super();
        initComponents();
        lblAviso.setVisible(false);
        CProductoLocal cpl = new CProductoLocal();
        AtuxVariables.arrayProductos = cpl.getProductosPedidoVenta();
        inicializarCarga(AtuxVariables.arrayProductos);
        AtuxUtility.moveFocus(txtDato);
        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupComprobante = new javax.swing.ButtonGroup();
        buttonGroupImpuesto = new javax.swing.ButtonGroup();
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
        lblEzen = new javax.swing.JLabel();
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
        pnlActionButtons = new javax.swing.JPanel();
        bntSalir = new elaprendiz.gui.button.ButtonRect();

        setTitle("Buscar Perfume");
        setNormalBounds(new java.awt.Rectangle(0, 0, 500, 400));
        setPreferredSize(new java.awt.Dimension(860, 630));
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
        rbCaballeros.setFont(new java.awt.Font("Tahoma", 1, 12));
        rbCaballeros.setForeground(new java.awt.Color(255, 255, 255));
        rbCaballeros.setText("Caballeros");
        rbCaballeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCaballerosActionPerformed(evt);
            }
        });

        rbDamas.setBackground(new java.awt.Color(51, 153, 255));
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
            .addGap(0, 844, Short.MAX_VALUE)
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
        jPanel1.setBounds(0, 0, 846, 50);

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
        jPanel6.setBounds(0, 50, 846, 270);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setOpaque(false);

        jPanelDetalleProd.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleProd.setAlignmentX(0.0F);
        jPanelDetalleProd.setAlignmentY(0.0F);
        jPanelDetalleProd.setEnabled(false);
        jPanelDetalleProd.setLayout(null);

        lblEzen.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblEzen.setForeground(new java.awt.Color(153, 0, 0));
        lblEzen.setText("Ezen :");
        lblEzen.setName(""); // NOI18N
        jPanelDetalleProd.add(lblEzen);
        lblEzen.setBounds(10, 4, 32, 20);

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

        jPanelDetalleInsumos.setBackground(new java.awt.Color(53, 151, 255));
        jPanelDetalleInsumos.setEnabled(false);
        jPanelDetalleInsumos.setLayout(null);

        lblAviso.setFont(new java.awt.Font("Segoe Print", 1, 17));
        lblAviso.setForeground(new java.awt.Color(255, 255, 0));
        lblAviso.setText("Promoción");
        jPanelDetalleInsumos.add(lblAviso);
        lblAviso.setBounds(40, 0, 100, 23);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 2, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanelDetalleProd, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanelDetalleInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanelPromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 2, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(jPanelInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanelDetalleProd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addComponent(jPanelDetalleInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(jPanelPromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelImage1.add(jPanel2);
        jPanel2.setBounds(0, 320, 846, 238);

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
        pnlActionButtons.setBounds(0, 560, 846, 40);

        getContentPane().add(panelImage1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSalirActionPerformed
        closeWindow(true);        
    }//GEN-LAST:event_bntSalirActionPerformed

    private void txtDatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoKeyPressed

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoKeyReleased

    private void txtDatoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoKeyTyped

    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbTodosActionPerformed

    private void rbCaballerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCaballerosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbCaballerosActionPerformed

    private void rbDamasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDamasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDamasActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosMouseClicked

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusLost
    
    public void closeWindow(boolean pAceptar) {              
        AtuxVariables.vAceptar = pAceptar;       
        this.setVisible(false);        
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect bntSalir;
    private javax.swing.ButtonGroup buttonGroupComprobante;
    private javax.swing.ButtonGroup buttonGroupEstado;
    private javax.swing.ButtonGroup buttonGroupImpuesto;
    private javax.swing.JLabel jDLinea;
    private javax.swing.JLabel jLDGrupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLinea;
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
    private javax.swing.JLabel jlCajas;
    private javax.swing.JLabel jlDCajas;
    private javax.swing.JLabel jlDPrecio;
    private javax.swing.JLabel jlDStock;
    private javax.swing.JLabel jlPrecio;
    private javax.swing.JLabel jlStock;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblEzen;
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
