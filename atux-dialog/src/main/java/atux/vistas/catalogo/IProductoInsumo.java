/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IProductoInsumo.java
 *
 * Created on 29/09/2015, 03:43:53 PM
 */
package atux.vistas.catalogo;

import atux.common.AtuxDBUtility;
import atux.controllers.CPrincipioActivo;
import atux.controllers.CProductoInsumo;
import atux.enums.IndicadorSNRegistro;
import atux.modelbd.Producto;
import atux.modelgui.ModeloTablaProductoInsumo;
import atux.modelgui.ModeloTablaSimple;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxGridUtils;
import atux.util.common.AtuxUtility;
import atux.util.common.AtuxVariables;
import atux.vistas.buscar.BuscarMaestroProducto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ABC
 */
public class IProductoInsumo extends javax.swing.JDialog {
    private CProductoInsumo cp;
    private ModeloTablaProductoInsumo mtp;
    private ModeloTablaProductoInsumo mtp2;
    private boolean esActualizacion = false;
    private int tipoSeleccion = 1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());
    JOptionPane op;
    private DefaultComboBoxModel mImprime;
    private Double vaCosto;

    public Double getVaCosto() {
        return vaCosto;
    }

    public void setVaCosto(Double vaCosto) {
        this.vaCosto = vaCosto;
    }

    public Double getVaPrecioPublico() {
        return vaPrecioPublico;
    }

    public void setVaPrecioPublico(Double vaPrecioPublico) {
        this.vaPrecioPublico = vaPrecioPublico;
    }
    private Double vaPrecioPublico;
    private String deUnidadInsumo;
    private String Estado;
    private String tiMaterialSAP;
    
    /** Creates new form IProductoInsumo */
    public IProductoInsumo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        AtuxUtility.centrarVentana(this);
        this.setTitle("Mostrando todos los Insumos del Producto");

        cp = new CProductoInsumo();
        setFiltroTexto();
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();
        lbAviso.setVisible(false);
    }

    public String getTiMaterialSAP() {
        return tiMaterialSAP;
    }

    public void setTiMaterialSAP(String tiMaterialSAP) {
        this.tiMaterialSAP = tiMaterialSAP;
    }
    
    public final void CargaDatos() {
        Estado = "T";

        if (tipoSeleccion == 0) {
            Estado = "I";
        } else if (tipoSeleccion == 1) {
            Estado = "A";
        }

        mImprime = new DefaultComboBoxModel(IndicadorSNRegistro.values());
        this.cmbImprime.setModel(mImprime);

        mtp = new ModeloTablaProductoInsumo(AtuxVariables.vCodigoCompania, txtCodigo.getText(), Estado);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado, ModeloTablaProductoInsumo.anchoColumnas);

        mtp2 = new ModeloTablaProductoInsumo(AtuxVariables.vCodigoCompania, txtCodigoPrincipioActivo.getText(), Estado);
        tblListadoInsumo.setModel(mtp2);
        Helper.ajustarSoloAnchoColumnas(tblListadoInsumo, ModeloTablaProductoInsumo.anchoColumnas);
        
        if (this.getTiMaterialSAP().equals("PROD")){
            tblListadoInsumo.setVisible(true);
            jScrollPane2.setVisible(true);
        }else{
            tblListadoInsumo.setVisible(false);
            jScrollPane2.setVisible(false);
        }
        
    }

    private void setFiltroTexto() {
        Helper.setFiltraEntrada(txtCodigoPrincipioActivo.getDocument(), FiltraEntrada.NUM_LETRAS, 10, false);
    }

    private void setEventSelectionModel(ListSelectionModel lsm) {
        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionEvent(e);
            }
        };
        lsm.addListSelectionListener(lsl);
    }

    private void selectionEvent(ListSelectionEvent e) {
        if (tblListado.getSelectedRow() != -1) {
            numRegistros = tblListado.getSelectedRow();
            cp.setProductoInsumo(mtp.getFila(tblListado.getSelectedRow()));
            setProductoInsumo();
            btnModificar.setEnabled(true);
        }
    }

    private void setProductoInsumo() {
        Limpiar();

        this.txtCodigoPrincipioActivo.setText(String.valueOf(cp.getProductoInsumo().getCoProductoInsumo()));
        txtDescripcionPrincipioActivo.setText(String.valueOf(cp.getProductoInsumo().getDeProductoInsumo()));

        //cmbImprime.setSelectedIndex(0);
        if ("S".equals(cp.getProductoInsumo().getInProductoInsumoPrincipal())) {
            cmbImprime.setSelectedIndex(1);
        } else if ("N".equals(cp.getProductoInsumo().getInProductoInsumoPrincipal())) {
            cmbImprime.setSelectedIndex(2);
        }

        if ("A".equals(cp.getProductoInsumo().getEsProductoInsumo())) {
            chbSetActivo(true);
        } else {
            chbSetActivo(false);
        }

        mtp2 = new ModeloTablaProductoInsumo(AtuxVariables.vCodigoCompania, txtCodigoPrincipioActivo.getText(), Estado);
        tblListadoInsumo.setModel(mtp2);
        Helper.ajustarSoloAnchoColumnas(tblListadoInsumo, ModeloTablaProductoInsumo.anchoColumnas);
    }

    private void Limpiar() {
        this.txtCodigoPrincipioActivo.setText("");
        txtDescripcionPrincipioActivo.setText("");
        this.chbEstado.setSelected(true);
        chbSetActivo(true);
    }

    private void ActivarCasillas() {
        pnlEntradas.setEnabled(false);
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblCodigo, lblCodigoPrincipioActivo, lblUnidad, lblCodigoPrincipioActivo, lblImprime, lblEstado, txtCodigo, txtDescripcion, txtUnidad}, false, "");
        this.tblListado.setEnabled(false);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnSalir.setEnabled(false);
        this.chbEstado.setEnabled(true);

        this.btnPrimero.setEnabled(false);
        this.btnUltimo.setEnabled(false);
        this.btnSiguiente.setEnabled(false);
        this.btnAnterior.setEnabled(false);
        this.rbTodos.setEnabled(false);
        this.rbAtivos.setEnabled(false);
        this.rbNoActivos.setEnabled(false);

        this.cmbImprime.setEnabled(true);
    }

    private void DesActivarCasillas() {
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblCodigo, lblCodigoPrincipioActivo, lblUnidad, lblCodigoPrincipioActivo, lblImprime, lblEstado, txtCodigo, txtDescripcion, txtUnidad}, false, "");
        this.tblListado.setEnabled(true);
        this.tblListado.clearSelection();
        this.btnNuevo.setEnabled(true);
        this.btnModificar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnSalir.setEnabled(true);
        this.chbEstado.setEnabled(false);

        this.btnPrimero.setEnabled(true);
        this.btnUltimo.setEnabled(true);
        this.btnSiguiente.setEnabled(true);
        this.btnAnterior.setEnabled(true);
        this.rbTodos.setEnabled(true);
        this.rbAtivos.setEnabled(true);
        this.rbNoActivos.setEnabled(true);

        this.cmbImprime.setEnabled(false);
        
        esActualizacion = false;
        this.pnlBuscadorTDeCambio.setVisible(true);
        logger.info(txtCodigoPrincipioActivo.getText());
    }

    public boolean verficarCambios() {
        if (!this.txtCodigoPrincipioActivo.getText().equals(String.valueOf(cp.getProductoInsumo().getCoProductoInsumo()))) {
            return true;
        } else

        if (!chbEstado.isSelected() != ("I".equals(cp.getProductoInsumo().getEsProductoInsumo()))) {
            return true;
        }
        return false;
    }

    private boolean guardarActualizar() throws SQLException {
        cp.getProductoInsumo().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getProductoInsumo().setCoProducto(txtCodigo.getText());
        cp.getProductoInsumo().setNuRevisionProducto("0");
        cp.getProductoInsumo().setCoProductoInsumo(txtCodigoPrincipioActivo.getText());

        cp.getProductoInsumo().setDeObservacion(null);
        if (chbEstado.isSelected()) {
            cp.getProductoInsumo().setEsProductoInsumo("A");
        } else {
            cp.getProductoInsumo().setEsProductoInsumo("I");
        }

        cp.getProductoInsumo().setDeProductoInsumo(deUnidadInsumo);

        cp.getProductoInsumo().setVaCosto(vaCosto);
        cp.getProductoInsumo().setVaPrecioPublico(vaPrecioPublico);
        cp.getProductoInsumo().setInImpresion(((IndicadorSNRegistro)cmbImprime.getSelectedItem()).getCodigo());

        Boolean EstadoGuardar = false;

        if (!esActualizacion) {
            cp.getProductoInsumo().setIdCreaProductoInsumo(AtuxVariables.vIdUsuario);
            cp.getProductoInsumo().setFeCreaProductoInsumo(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            EstadoGuardar = cp.guardarRegistro(cp.getProductoInsumo());
        } else {
            cp.getProductoInsumo().setIdModProductoInsumo(AtuxVariables.vIdUsuario);
            cp.getProductoInsumo().setFeModProductoInsumo(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));

            int act = cp.actualizarRegistro(cp.getProductoInsumo());
            if (act == 1) {
                EstadoGuardar = true;
            }
        }

        if (EstadoGuardar) {
            EstadoGuardar = true;
        } else {
            EstadoGuardar = false;
        }
        return EstadoGuardar;
    }

    private Date FormatoFecha(String oldFecha) {
        Date Fecha = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(IProductoInsumo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    public void chbSetActivo(boolean opcion) {
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setBackground(new Color(104, 204, 0));
        chbEstado.setForeground(Color.BLACK);
        if (!opcion) {
            chbEstado.setSelected(false);
            chbEstado.setText("No Activo");
            chbEstado.setBackground(Color.red);
            chbEstado.setForeground(Color.WHITE);
        }
    }

    public void setCodigoProducto(String CodProducto) {
        txtCodigo.setText(CodProducto);
    }

    public void setDescripcionProducto(String DescripcionProducto) {
        txtDescripcion.setText(DescripcionProducto);
    }

    public void setUnidadInsumo(String UnidadProducto) {
        deUnidadInsumo=UnidadProducto;
    }

    public void setUnidadProducto(String UnidadProducto) {
        txtUnidad.setText(UnidadProducto);
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

    public JLabel getAviso() {
        return lbAviso;
    }

    private void BuscarInfoPrincipioActivo(String Codigo) {
        CPrincipioActivo BG1 = new CPrincipioActivo();
        BG1.getRegistroPorPk(new String[]{Codigo, "1"});
        txtDescripcionPrincipioActivo.setText(BG1.getPrincipioActivo().getDePrincipioActivo().trim());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradas = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblUnidad = new javax.swing.JLabel();
        lblCodigoPrincipioActivo = new javax.swing.JLabel();
        lblImprime = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        txtUnidad = new elaprendiz.gui.textField.TextField();
        txtDescripcionPrincipioActivo = new elaprendiz.gui.textField.TextField();
        txtCodigoPrincipioActivo = new elaprendiz.gui.textField.TextField();
        lbAviso = new elaprendiz.gui.label.LabelRect();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        cmbImprime = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlBuscadorTDeCambio = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesTDeCambio = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListadoInsumo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N
        panelImage1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelImage1FocusGained(evt);
            }
        });

        pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlEntradas.setEnabled(false);
        pnlEntradas.setOpaque(false);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 13));
        lblCodigo.setText("Codigo:");

        lblUnidad.setFont(new java.awt.Font("Tahoma", 1, 13));
        lblUnidad.setText("Unidad:");

        lblCodigoPrincipioActivo.setFont(new java.awt.Font("Tahoma", 1, 13));
        lblCodigoPrincipioActivo.setText("Insumo:");

        lblImprime.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblImprime.setText("Imprime?");

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));

        txtDescripcion.setEditable(false);
        txtDescripcion.setDireccionDeSombra(30);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcion.setName("pdescrip"); // NOI18N
        txtDescripcion.setPreferredSize(new java.awt.Dimension(120, 25));

        txtUnidad.setEditable(false);
        txtUnidad.setDireccionDeSombra(30);
        txtUnidad.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtUnidad.setFont(new java.awt.Font("Arial", 0, 12));
        txtUnidad.setName("pdescrip"); // NOI18N
        txtUnidad.setPreferredSize(new java.awt.Dimension(120, 25));

        txtDescripcionPrincipioActivo.setEditable(false);
        txtDescripcionPrincipioActivo.setDireccionDeSombra(30);
        txtDescripcionPrincipioActivo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescripcionPrincipioActivo.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescripcionPrincipioActivo.setName("pdescrip"); // NOI18N
        txtDescripcionPrincipioActivo.setPreferredSize(new java.awt.Dimension(120, 25));

        txtCodigoPrincipioActivo.setEditable(false);
        txtCodigoPrincipioActivo.setToolTipText("Presione F1 para ver mas datos");
        txtCodigoPrincipioActivo.setDireccionDeSombra(30);
        txtCodigoPrincipioActivo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigoPrincipioActivo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigoPrincipioActivo.setName("pcodigo"); // NOI18N
        txtCodigoPrincipioActivo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigoPrincipioActivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoPrincipioActivoKeyReleased(evt);
            }
        });

        lbAviso.setBackground(new java.awt.Color(255, 0, 51));
        lbAviso.setForeground(new java.awt.Color(255, 255, 0));
        lbAviso.setText("Debe Seleccionar un Médico");
        lbAviso.setPreferredSize(new java.awt.Dimension(250, 17));

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 13));
        lblEstado.setText("Estado:");

        chbEstado.setBackground(new java.awt.Color(51, 153, 255));
        chbEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setEnabled(false);
        chbEstado.setPreferredSize(new java.awt.Dimension(100, 25));
        chbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbEstadoActionPerformed(evt);
            }
        });
        chbEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chbEstadoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasLayout = new javax.swing.GroupLayout(pnlEntradas);
        pnlEntradas.setLayout(pnlEntradasLayout);
        pnlEntradasLayout.setHorizontalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addGap(16, 16, 16)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(lblUnidad)
                        .addGap(10, 10, 10)
                        .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addComponent(lblCodigoPrincipioActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtCodigoPrincipioActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtDescripcionPrincipioActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lblImprime)
                        .addGap(5, 5, 5)
                        .addComponent(cmbImprime, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(lblEstado)
                        .addGap(6, 6, 6)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlEntradasLayout.setVerticalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblUnidad))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoPrincipioActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoPrincipioActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionPrincipioActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblImprime, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cmbImprime, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblEstado))
                    .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblListado);

        pnlBuscadorTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscadorTDeCambio.setOpaque(false);
        pnlBuscadorTDeCambio.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnPrimero);
        btnPrimero.setBounds(8, 6, 48, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnAnterior);
        btnAnterior.setBounds(61, 6, 40, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnSiguiente);
        btnSiguiente.setBounds(106, 6, 40, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnUltimo);
        btnUltimo.setBounds(152, 6, 48, 25);

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        rbTodos.setFont(new java.awt.Font("Arial", 1, 14));
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbTodos);
        rbTodos.setBounds(205, 6, 69, 25);

        rbAtivos.setBackground(new java.awt.Color(51, 153, 255));
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setSelected(true);
        rbAtivos.setText("Activos");
        rbAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbAtivos);
        rbAtivos.setBounds(279, 6, 77, 25);

        rbNoActivos.setBackground(new java.awt.Color(51, 153, 255));
        rbNoActivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbNoActivos.setForeground(new java.awt.Color(255, 255, 255));
        rbNoActivos.setText("No Activos");
        rbNoActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoActivosActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(rbNoActivos);
        rbNoActivos.setBounds(360, 6, 101, 25);

        pnlAccionesTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesTDeCambio.setOpaque(false);
        pnlAccionesTDeCambio.setLayout(null);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnNuevo);
        btnNuevo.setBounds(9, 6, 78, 25);

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnModificar);
        btnModificar.setBounds(93, 6, 98, 25);

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnGuardar);
        btnGuardar.setBounds(196, 6, 89, 25);

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnCancelar);
        btnCancelar.setBounds(290, 6, 94, 25);

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlAccionesTDeCambio.add(btnSalir);
        btnSalir.setBounds(390, 6, 88, 25);

        tblListadoInsumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblListadoInsumo);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtCodigoPrincipioActivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPrincipioActivoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                ISeleccionarInsumo pvc = new ISeleccionarInsumo(new Frame(), true, this);
                pvc.setPreferredSize(new Dimension(650, 350));
                JLabel aviso = pvc.getAviso();

                String msj = (tipoSeleccion == -1 ? "Mostrando Listado de Insumos" : (tipoSeleccion == 1 ? "Mostrando Listado de Insumos" : "Mostrando Listado de Insumos"));
                pvc.setVisible(true);
//                JOptionPane.showInternalOptionDialog(new Frame() , pvc, msj, -1, -1, null, new Object[]{aviso}, null);


//                if (pvc.getMaestroProducto() != null) {
//                    this.txtCodigoPrincipioActivo.setText(((Producto)pvc.getMaestroProducto()).getCoProducto());
//                    this.txtDescripcionPrincipioActivo.setText(((Producto)pvc.getMaestroProducto()).getDeProducto());
//                }

                break;
            case KeyEvent.VK_ENTER:
                BuscarInfoPrincipioActivo(txtCodigoPrincipioActivo.getText());
                cmbImprime.requestFocus();
                break;
        }
}//GEN-LAST:event_txtCodigoPrincipioActivoKeyReleased

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                btnGuardar.requestFocus();
                break;
        }
}//GEN-LAST:event_chbEstadoKeyReleased

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = 0;
        AtuxGridUtils.showCell(tblListado, numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setProductoInsumo(mtp.getFila(numRegistros));
        setProductoInsumo();
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1) {
            numRegistros = 0;
        }
        AtuxGridUtils.showCell(tblListado, numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setProductoInsumo(mtp.getFila(numRegistros));
        setProductoInsumo();

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros) {
            numRegistros = finalPag;
        }
        AtuxGridUtils.showCell(tblListado, numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setProductoInsumo(mtp.getFila(numRegistros));
        setProductoInsumo();

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = finalPag;
        AtuxGridUtils.showCell(tblListado, numRegistros, ModeloTablaSimple.COLUMNA_DESCRIPCION);
        cp.setProductoInsumo(mtp.getFila(numRegistros));
        setProductoInsumo();

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;
        finalPag = tmpFp;
        Limpiar();
        CargaDatos();
        chbSetActivo(true);
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0;
        finalPag = tmpFp;
        Limpiar();
        CargaDatos();
        chbSetActivo(true);
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0;
        finalPag = tmpFp;
        Limpiar();
        CargaDatos();
        chbSetActivo(false);
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;
        Limpiar();
        ActivarCasillas();
        txtCodigoPrincipioActivo.requestFocus();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
        txtCodigoPrincipioActivo.requestFocus();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        if (ECampos.esObligatorio(this.pnlEntradas, true)) {
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }
//      if (!this.verficarCambios()) {
//            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
//                return;
//            }
//      }

        boolean correcto = false;

//        if (!this.verficarCambios()) {
//            JOptionPane.showMessageDialog(this, "Debe cambiar por lo menos algun valor", "No hubo cambios", JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!correcto) {
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradas, false);
            return;
        }

        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        CargaDatos();
        DesActivarCasillas();
        tblListado.requestFocus();
}//GEN-LAST:event_btnGuardarActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }

        DesActivarCasillas();
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
    dispose();
//    getOptionPane();
//        op.setValue(1);
}//GEN-LAST:event_btnSalirActionPerformed

private void panelImage1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelImage1FocusGained

}//GEN-LAST:event_panelImage1FocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IProductoInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IProductoInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IProductoInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IProductoInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                IProductoInsumo dialog = new IProductoInsumo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private elaprendiz.gui.button.ButtonRect btnAnterior;
    private elaprendiz.gui.button.ButtonRect btnCancelar;
    private elaprendiz.gui.button.ButtonRect btnGuardar;
    private elaprendiz.gui.button.ButtonRect btnModificar;
    private elaprendiz.gui.button.ButtonRect btnNuevo;
    private elaprendiz.gui.button.ButtonRect btnPrimero;
    private elaprendiz.gui.button.ButtonRect btnSalir;
    private elaprendiz.gui.button.ButtonRect btnSiguiente;
    private elaprendiz.gui.button.ButtonRect btnUltimo;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JComboBox cmbImprime;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private elaprendiz.gui.label.LabelRect lbAviso;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoPrincipioActivo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblImprime;
    private javax.swing.JLabel lblUnidad;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesTDeCambio;
    private javax.swing.JPanel pnlBuscadorTDeCambio;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private javax.swing.JTable tblListadoInsumo;
    private elaprendiz.gui.textField.TextField txtCodigo;
    public elaprendiz.gui.textField.TextField txtCodigoPrincipioActivo;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    public elaprendiz.gui.textField.TextField txtDescripcionPrincipioActivo;
    private elaprendiz.gui.textField.TextField txtUnidad;
    // End of variables declaration//GEN-END:variables
}
