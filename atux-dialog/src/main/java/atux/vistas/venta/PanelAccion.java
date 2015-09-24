package atux.vistas.venta;

import atux.handlers.PedidoVentaInterceptor;
import atux.modelbd.DetallePedidoVenta;
import atux.modelbd.PedidoVenta;
import atux.modelbd.ProductoLocal;
import atux.modelgui.ModeloTomaPedidoVenta;
import atux.modelgui.ModeloTablaProducto;
import atux.util.CeldaAccionEditor;
import atux.util.CellEditorSpinnerPedidoVenta;
import atux.vistas.buscar.BuscarProducto;
import atux.vistas.buscar.IBuscarProducto;
import com.aw.swing.spring.Application;

import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class PanelAccion extends javax.swing.JPanel {


    private JInternalFrame ifr;
    private JTable tabla;
    private ModeloTomaPedidoVenta mtpv;
    private int indexFila = -1;
    private CeldaAccionEditor cae;

    public PanelAccion(JInternalFrame ifr) {
        initComponents();
        this.ifr = ifr;
    }

    public void setCeldaEditor(CeldaAccionEditor cae) {
        this.cae = cae;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
        mtpv = (ModeloTomaPedidoVenta) this.tabla.getModel();
    }

    public void setIndexFila(int fila) {
        this.indexFila = fila;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bntAgregar = new javax.swing.JButton();
        bntEliminar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(92, 35));

        bntAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/agregar.png"))); // NOI18N
        bntAgregar.setBorder(null);
        bntAgregar.setBorderPainted(false);
        bntAgregar.setContentAreaFilled(false);
        bntAgregar.setPreferredSize(new java.awt.Dimension(24, 24));
        bntAgregar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/agregar.png"))); // NOI18N
        bntAgregar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/agregar_over.png"))); // NOI18N
        bntAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAgregarActionPerformed(evt);
            }
        });

        bntEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/eliminar.png"))); // NOI18N
        bntEliminar.setBorder(null);
        bntEliminar.setBorderPainted(false);
        bntEliminar.setContentAreaFilled(false);
        bntEliminar.setPreferredSize(new java.awt.Dimension(24, 24));
        bntEliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/eliminar_over.png"))); // NOI18N
        bntEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(bntAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bntEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bntAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(bntEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAgregarActionPerformed
        if (this.tabla.getSelectedRow() != -1) {
            this.indexFila = this.tabla.getSelectedRow();
        }

        IBuscarProducto pvc = new IBuscarProducto(new Frame(),true);
        pvc.setVisible(true);

        if (pvc.getProductoLocal() != null) {
            /**
             * coreegir cuamdo se borra la primera fila nos genera un dato nulo.
             */
            if (!mtpv.existe(pvc.getProductoLocal())) {
                try {
                    mtpv.contarItems();
                    ((DetallePedidoVenta) mtpv.getFila(this.indexFila)).setNuItemPedido(mtpv.getNumItems());
                    ((DetallePedidoVenta) mtpv.getFila(this.indexFila)).agregarItem(pvc.getProductoLocal());
                    this.tabla.changeSelection(this.indexFila, 2, true, false);
                    cae.lanzarDetencionEdicion();
                    ((CellEditorSpinnerPedidoVenta) this.tabla.getCellEditor(this.indexFila, 2)).getSpinner().requestFocus();
                    nuevoItem();
                } catch (SQLException ex) {
                    Logger.getLogger(PanelAccion.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showInternalMessageDialog(ifr, "El Producto: " + pvc.getProductoLocal().getProducto().getDeProducto() +
                        " ya ha sido agregado", "Producto Duplicado", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_bntAgregarActionPerformed

    private void bntEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEliminarActionPerformed
        if (this.tabla.getSelectedRow() != -1) {
            this.indexFila = this.tabla.getSelectedRow();
        }
        if (((DetallePedidoVenta) mtpv.getFila(this.indexFila)).getProdLocal().getPrimaryKey() != null) {
            if (this.tabla.getRowCount() > 1) {
                if (this.indexFila < this.tabla.getRowCount() - 1) {
                    int opc = JOptionPane.showInternalConfirmDialog(ifr, "�Realmente desea quitar este producto?", "Quitar Producto", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (opc == JOptionPane.OK_OPTION) {
                        PedidoVenta pedidoVenta = new PedidoVenta();
                        pedidoVenta.setDetallePedidoVenta(mtpv.getRegistros());
                        DetallePedidoVenta detallePedidoVenta = ((DetallePedidoVenta) mtpv.getFila(this.indexFila));
//                        PedidoVentaInterceptor pedidoVentaInterceptor = Application.instance().getBean(PedidoVentaInterceptor.class);
//                        Map result=new HashMap();
//                        pedidoVentaInterceptor.quitar(pedidoVenta,detallePedidoVenta,result);
//                        List<DetallePedidoVenta> itemsEliminados = (List<DetallePedidoVenta>) result.get("itemsEliminados");
//                        for (DetallePedidoVenta item : itemsEliminados) {
//                            mtpv.quitarFila(item.getNuItemPedido()-1);
//                        }
                        ((DetallePedidoVenta) mtpv.getFila(this.indexFila)).setProdLocal(new ProductoLocal());
                        mtpv.quitarFila(this.indexFila);


                        mtpv.contarItems();
                        cae.lanzarDetencionEdicion();
                        return;
                    }
                }
            }
            if ((this.indexFila == 0 && this.tabla.getRowCount() == 1) || (this.indexFila == this.tabla.getRowCount() - 1 && this.tabla.getRowCount() > 1)) {
                int opc = JOptionPane.showInternalConfirmDialog(ifr, "�Realmente desea quitar este producto?", "Quitar Producto", JOptionPane.YES_NO_CANCEL_OPTION);
                if (opc == JOptionPane.OK_OPTION) {
                    mtpv.remplazarProducto(new ProductoLocal(), indexFila);
                    mtpv.contarItems();
                    cae.lanzarDetencionEdicion();
                    return;
                }
            }
        }

    }//GEN-LAST:event_bntEliminarActionPerformed

    private void nuevoItem() throws SQLException {
        if (this.indexFila == this.tabla.getRowCount() - 1) {
            if (((DetallePedidoVenta) mtpv.getFila(this.indexFila)).getProdLocal().getPrimaryKey() != null) {
                if (((DetallePedidoVenta) mtpv.getFila(this.indexFila)).getCaAtendida() > 0) {
                    mtpv.agregar(new ProductoLocal());
                } else {
                    JOptionPane.showInternalMessageDialog(ifr, "La cantidad debe ser mayor que cero.",
                            "Error: cantidad cero", JOptionPane.ERROR_MESSAGE);

                }
            }

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAgregar;
    private javax.swing.JButton bntEliminar;
    // End of variables declaration//GEN-END:variables
}
