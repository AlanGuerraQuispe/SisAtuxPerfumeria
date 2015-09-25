package atux.vistas.catalogo;
import atux.controllers.CClienteCompania;
import atux.controllers.CFormaPago;
import atux.controllers.CFormaPagoLocal;
import atux.modelgui.ModeloTablaFormaPago;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import atux.vistas.buscar.BuscarClienteCompania;
import org.apache.commons.lang.StringUtils;
import elaprendiz.gui.comboBox.ComboBoxRect;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Cesar Ruiz  PC
 */
public class IFormaPago extends javax.swing.JInternalFrame {
    private CFormaPago cp;
    private CFormaPagoLocal cpl;
    private CClienteCompania CCia;
    private ModeloTablaFormaPago mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    private final Log logger = LogFactory.getLog(getClass());   
    
    /** Creates new form IImpuestoIGV */
    public IFormaPago() {
        initComponents();
        cp = new CFormaPago();
        cpl = new CFormaPagoLocal();
        CCia = new CClienteCompania();
        mtp = new ModeloTablaFormaPago(inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        
        tblFormaDePago.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblFormaDePago,ModeloTablaFormaPago.anchoColumnas);
        Helper.ajustarAnchoColumnas(tblFormaDePago);
        setFiltroTexto();        
        setEventSelectionModel(tblFormaDePago.getSelectionModel());
        DesActivarCasillas();
        rbTodosActionPerformed(null);
        lblMensaje.setVisible(true);
    }

    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.NUM_LETRAS, 10, false);
    }

    private void setEventSelectionModel(ListSelectionModel lsm){
        ListSelectionListener lsl = new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionEvent(e);
            }
        };
        lsm.addListSelectionListener(lsl);
    }
    
    private void selectionEvent(ListSelectionEvent e){
        if(tblFormaDePago.getSelectedRow() != -1){
            numRegistros = tblFormaDePago.getSelectedRow();
            cp.setFormaPago(mtp.getFila(tblFormaDePago.getSelectedRow()));
            setFormaPago();
            BuscarClienteCia(txtClienteCompania.getText());
       }
    }
    
    private void setFormaPago(){
        Limpiar();
        this.txtCodigo.setText(cp.getFormaPago().getCoFormaPago());
        this.txtDescrip.setText(cp.getFormaPago().getDeFormaPago());
        this.txtAbrev.setText(cp.getFormaPago().getDeCortaFormaPago());
        this.txtClienteCompania.setText(cp.getFormaPago().getCoClienteCompania());
        
        if("A".equals(cp.getFormaPago().getEsFormaPago())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        cmbOpcion(cmbPagoExacto, cp.getFormaPago().getInPagoExacto());
        cmbOpcion(cmbTarjetaBancaria, cp.getFormaPago().getInTarjetaBancaria());
        cmbOpcion(cmbDeficitCaja, cp.getFormaPago().getInDeficitCaja());
        cmbOpcion(cmbDetalle, cp.getFormaPago().getInDetalle());
        cmbOpcion(cmbTransaccionBancaria, cp.getFormaPago().getInTarjetaBancaria());
        cmbOpcion(cmbAperturaGaveta, cp.getFormaPago().getInAperturaGaveta());
        cmbOpcion(cmbSobreHermes, cp.getFormaPago().getInSobreHermes());
        
        this.btnModificar.setEnabled(true);
    }

    public void chbSetActivo(boolean opcion){
        chbEstado.setSelected(true);
        chbEstado.setText("Activo");
        chbEstado.setBackground(new Color(104,204,0));
        chbEstado.setForeground(Color.BLACK);
        if(!opcion){
            chbEstado.setSelected(false);
            chbEstado.setText("No Activo");
            chbEstado.setBackground(Color.red);
            chbEstado.setForeground(Color.WHITE);
        }
    }

    public void cmbOpcion(ComboBoxRect Combo, String opcion){
        Combo.setSelectedIndex(0);

        if("S".equals(opcion)){
            Combo.setSelectedIndex(1);
        }else if("N".equals(opcion)){
            Combo.setSelectedIndex(2);
        }else if("P".equals(opcion)){
            Combo.setSelectedIndex(1);
        }else if("D".equals(opcion)){
            Combo.setSelectedIndex(2);
        }else if("M".equals(opcion)){
            Combo.setSelectedIndex(3);
        }
    }
    
    private void Limpiar(){
        this.txtCodigo.setText("");
        this.txtDescrip.setText("");
        this.txtAbrev.setText("");
        this.txtClienteCompania.setText("");
        
        this.chbEstado.setSelected(false);
        chbSetActivo(false);
        cmbPagoExacto.setSelectedIndex(0);
        cmbTarjetaBancaria.setSelectedIndex(0);
        cmbDeficitCaja.setSelectedIndex(0);
        cmbDetalle.setSelectedIndex(0);
        cmbTransaccionBancaria.setSelectedIndex(0);
        cmbAperturaGaveta.setSelectedIndex(0);

    }

    private void ActivarCasillas(){
        pnlAccionesFPago.setEnabled(false);
        pnlBuscadorFPago.setEnabled(false);

        ECampos.setEditableTexto(pnlEntradasFPago,true,new Component[]{lblCodigo,lblDescrip,lblAbrev,lblEstado},false,"");              
        ECampos.setEditableTexto(pnlIndicadores,true,new Component[]{lblPagEstado,lblTarjBancaria,lblDeficit,lblDetalle,lblSobreHermes,lblTransacBancaria,lblAperGaveta,lblCompania,lblDesCompania},false,"");
        txtCodigo.setEnabled(true);
        cmbPagoExacto.setEnabled(true);
        cmbTarjetaBancaria.setEnabled(true);
        cmbDeficitCaja.setEnabled(true);
        cmbDetalle.setEnabled(true);
        cmbSobreHermes.setEnabled(true);
        cmbTransaccionBancaria.setEnabled(true);
        cmbAperturaGaveta.setEnabled(true);

        this.tblFormaDePago.setEnabled(false);
        this.tblFormaDePago.clearSelection();
        
        
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
    }
    private void DesActivarCasillas(){
        this.pnlEntradasFPago.setEnabled(true);

        ECampos.setEditableTexto(pnlEntradasFPago,false,new Component[]{lblCodigo,lblDescrip,lblAbrev,lblEstado},true,"");              
        ECampos.setEditableTexto(pnlIndicadores,false,new Component[]{lblPagEstado,lblTarjBancaria,lblDeficit,lblDetalle,lblSobreHermes,lblTransacBancaria,lblAperGaveta,lblCompania,lblDesCompania},true,"");        
        cmbPagoExacto.setEnabled(false);
        cmbTarjetaBancaria.setEnabled(false);
        cmbDeficitCaja.setEnabled(false);
        cmbDetalle.setEnabled(false);
        cmbSobreHermes.setEnabled(false);
        cmbTransaccionBancaria.setEnabled(false);
        cmbAperturaGaveta.setEnabled(false);
        
        this.tblFormaDePago.setEnabled(true);
        this.tblFormaDePago.clearSelection();
        
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

        esActualizacion = false;
        this.pnlBuscadorFPago.setVisible(true);
        logger.info(txtCodigo.getText() + " - " + txtDescrip.getText());
    }

    public boolean verficarCambios(){
        if(!this.txtCodigo.getText().equals(cp.getFormaPago().getCoFormaPago())){
            return true;
        }else if(!this.txtDescrip.getText().equals(cp.getFormaPago().getDeFormaPago())){
            return true;
        }else if(!this.txtAbrev.getText().equals(cp.getFormaPago().getDeCortaFormaPago())){
            return true;
        }else if(!this.txtClienteCompania.getText().equals(cp.getFormaPago().getCoClienteCompania())){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getFormaPago().getEsFormaPago()))){
            return true;
        }else if(!DatoCombo(cmbPagoExacto).equals(cp.getFormaPago().getInPagoExacto())){
            return true;
        }else if(!DatoCombo(cmbTarjetaBancaria).equals(cp.getFormaPago().getInTarjetaBancaria())){
            return true;
        }else if(!DatoCombo(cmbDeficitCaja).equals(cp.getFormaPago().getInDeficitCaja())){
            return true;
        }else if(!DatoCombo(cmbDetalle).equals(cp.getFormaPago().getInDetalle())){
            return true;
        }else if(!DatoCombo(cmbSobreHermes).equals(cp.getFormaPago().getInSobreHermes())){
            return true;
        }else if(!DatoCombo(cmbTransaccionBancaria).equals(cp.getFormaPago().getTiTransaccionTarjeta())){
            return true;
        }else if(!DatoCombo(cmbAperturaGaveta).equals(cp.getFormaPago().getInAperturaGaveta())){
            return true;
        }
        
        return false;
    }

    private String DatoCombo(ComboBoxRect Cmb){
        if (Cmb.getSelectedIndex()==1){
            return "S";
        }else if(Cmb.getSelectedIndex()==2){
            return "N";
        }else{
            return null;
        }                
    }
    
    private boolean guardarActualizar() throws SQLException{
        txtAbrev.setText(txtAbrev.getText().toUpperCase());
        txtDescrip.setText(txtDescrip.getText().toUpperCase());
        
        cp.getFormaPago().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getFormaPago().setCoFormaPago(txtCodigo.getText());
        cp.getFormaPago().setNuOrdenFila(Integer.parseInt(txtCodigo.getText()));
        cp.getFormaPago().setDeCortaFormaPago(txtAbrev.getText());
        cp.getFormaPago().setDeFormaPago(txtDescrip.getText());
        cp.getFormaPago().setCoClienteCompania(txtClienteCompania.getText());
        cp.getFormaPago().setInPagoExacto(DatoCombo(cmbPagoExacto));
        cp.getFormaPago().setInTarjetaBancaria(DatoCombo(cmbTarjetaBancaria));
        cp.getFormaPago().setInDeficitCaja(DatoCombo(cmbDeficitCaja));
        cp.getFormaPago().setInDetalle(DatoCombo(cmbDetalle));
        cp.getFormaPago().setInSobreHermes(DatoCombo(cmbSobreHermes));
        cp.getFormaPago().setTiTransaccionTarjeta(DatoCombo(cmbTransaccionBancaria));
        cp.getFormaPago().setInAperturaGaveta(DatoCombo(cmbAperturaGaveta));
        cp.getFormaPago().setInCreditoConvenioCompania(null);
        cp.getFormaPago().setInMuestraCajaElectronica(null);

        cpl.getFormaPagoLocal().setCoCompania(AtuxVariables.vCodigoCompania);
        cpl.getFormaPagoLocal().setCoLocal(AtuxVariables.vCodigoLocal);
        cpl.getFormaPagoLocal().setCoFormaPago(txtCodigo.getText());

        if (chbEstado.isSelected()){
            cp.getFormaPago().setEsFormaPago("A");
            cpl.getFormaPagoLocal().setEsFormaPago("A");
        }else{
            cp.getFormaPago().setEsFormaPago("I");
            cpl.getFormaPagoLocal().setEsFormaPago("I");            
        }
        
        Boolean EstadoGuardar=false;

        
        
        if(!esActualizacion){
            txtCodigo.setText(cp.getNuevoCodigo());
            
            cp.getFormaPago().setCoFormaPago(txtCodigo.getText());
            cpl.getFormaPagoLocal().setCoFormaPago(txtCodigo.getText());
            
            cp.getFormaPago().setIdCreaFormaPago(AtuxVariables.vIdUsuario);
            cp.getFormaPago().setFeCreaFormaPago(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));

            cpl.getFormaPagoLocal().setIdCreaFormaPagoLocal(AtuxVariables.vIdUsuario);
            cpl.getFormaPagoLocal().setFeCreaFormaPagoLocal(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
                    
            EstadoGuardar = cp.guardarRegistro(cp.getFormaPago());
            if(EstadoGuardar){
                EstadoGuardar = cpl.guardarRegistro(cpl.getFormaPagoLocal());
            }
            
        }else{
            cp.getFormaPago().setIdModFormaPago(AtuxVariables.vIdUsuario);
            cp.getFormaPago().setFeModFormaPago(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            cpl.getFormaPagoLocal().setIdModFormaPagoLocal(AtuxVariables.vIdUsuario);
            cpl.getFormaPagoLocal().setFeModFormaPagoLocal(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            int act = cp.actualizarRegistro(cp.getFormaPago());
            if (act ==1) {
                EstadoGuardar = true;
            }
        }
        
        if(EstadoGuardar){
            EstadoGuardar = true; 
        }else{
            EstadoGuardar = false; 
        }
        return EstadoGuardar;
    }
    
    private Date FormatoFecha(String oldFecha){
        Date Fecha=null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = oldFecha;

        try {
            Fecha = formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(IFormaPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
    }

    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelImage1 = new elaprendiz.gui.panel.PanelImage();
        pnlEntradasFPago = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblDescrip = new javax.swing.JLabel();
        lblAbrev = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtDescrip = new elaprendiz.gui.textField.TextField();
        txtAbrev = new elaprendiz.gui.textField.TextField();
        chbEstado = new javax.swing.JCheckBox();
        pnlBuscadorFPago = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        pnlAccionesFPago = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormaDePago = new javax.swing.JTable();
        pnlIndicadores = new javax.swing.JPanel();
        lblPagEstado = new javax.swing.JLabel();
        lblTarjBancaria = new javax.swing.JLabel();
        lblDeficit = new javax.swing.JLabel();
        lblDetalle = new javax.swing.JLabel();
        cmbPagoExacto = new elaprendiz.gui.comboBox.ComboBoxRect();
        cmbTarjetaBancaria = new elaprendiz.gui.comboBox.ComboBoxRect();
        cmbDeficitCaja = new elaprendiz.gui.comboBox.ComboBoxRect();
        cmbDetalle = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblSobreHermes = new javax.swing.JLabel();
        cmbSobreHermes = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblTransacBancaria = new javax.swing.JLabel();
        cmbTransaccionBancaria = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblAperGaveta = new javax.swing.JLabel();
        cmbAperturaGaveta = new elaprendiz.gui.comboBox.ComboBoxRect();
        lblCompania = new javax.swing.JLabel();
        txtClienteCompania = new elaprendiz.gui.textField.TextField();
        lblDesCompania = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();

        setBorder(null);
        setTitle("Formulario de Forma de Pago");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlEntradasFPago.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de Forma de Pago", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlEntradasFPago.setOpaque(false);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Codigo:");

        lblDescrip.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescrip.setText("Descripcion:");

        lblAbrev.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblAbrev.setText("Abrev:");

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblEstado.setText("Estado:");

        txtCodigo.setEditable(false);
        txtCodigo.setDireccionDeSombra(30);
        txtCodigo.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12));
        txtCodigo.setName("pcodigo"); // NOI18N
        txtCodigo.setPreferredSize(new java.awt.Dimension(120, 25));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtDescrip.setEditable(false);
        txtDescrip.setDireccionDeSombra(30);
        txtDescrip.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescrip.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescrip.setName("pdescrip"); // NOI18N
        txtDescrip.setPreferredSize(new java.awt.Dimension(120, 25));
        txtDescrip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripKeyReleased(evt);
            }
        });

        txtAbrev.setEditable(false);
        txtAbrev.setDireccionDeSombra(30);
        txtAbrev.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtAbrev.setFont(new java.awt.Font("Arial", 0, 12));
        txtAbrev.setName("pruc"); // NOI18N
        txtAbrev.setPreferredSize(new java.awt.Dimension(120, 25));
        txtAbrev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbrevKeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout pnlEntradasFPagoLayout = new javax.swing.GroupLayout(pnlEntradasFPago);
        pnlEntradasFPago.setLayout(pnlEntradasFPagoLayout);
        pnlEntradasFPagoLayout.setHorizontalGroup(
            pnlEntradasFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasFPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescrip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblAbrev)
                .addGap(4, 4, 4)
                .addComponent(txtAbrev, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        pnlEntradasFPagoLayout.setVerticalGroup(
            pnlEntradasFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasFPagoLayout.createSequentialGroup()
                .addGroup(pnlEntradasFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAbrev, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEntradasFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEstado)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtAbrev.getAccessibleContext().setAccessibleName("tvalor");

        pnlBuscadorFPago.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlBuscadorFPago.setOpaque(false);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });

        rbTodos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbTodos);
        rbTodos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbTodos.setForeground(new java.awt.Color(255, 255, 255));
        rbTodos.setSelected(true);
        rbTodos.setText("Todos");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });

        rbAtivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAtivos);
        rbAtivos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbAtivos.setForeground(new java.awt.Color(255, 255, 255));
        rbAtivos.setText("Activos");
        rbAtivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAtivosActionPerformed(evt);
            }
        });

        rbNoActivos.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbNoActivos);
        rbNoActivos.setFont(new java.awt.Font("Arial", 1, 14));
        rbNoActivos.setForeground(new java.awt.Color(255, 255, 255));
        rbNoActivos.setText("No Activos");
        rbNoActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoActivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBuscadorFPagoLayout = new javax.swing.GroupLayout(pnlBuscadorFPago);
        pnlBuscadorFPago.setLayout(pnlBuscadorFPagoLayout);
        pnlBuscadorFPagoLayout.setHorizontalGroup(
            pnlBuscadorFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscadorFPagoLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(rbTodos)
                .addGap(4, 4, 4)
                .addComponent(rbAtivos)
                .addGap(4, 4, 4)
                .addComponent(rbNoActivos))
        );
        pnlBuscadorFPagoLayout.setVerticalGroup(
            pnlBuscadorFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rbTodos)
            .addComponent(rbAtivos)
            .addComponent(rbNoActivos)
        );

        pnlAccionesFPago.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        pnlAccionesFPago.setOpaque(false);

        btnNuevo.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(51, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(51, 153, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(51, 153, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccionesFPagoLayout = new javax.swing.GroupLayout(pnlAccionesFPago);
        pnlAccionesFPago.setLayout(pnlAccionesFPagoLayout);
        pnlAccionesFPagoLayout.setHorizontalGroup(
            pnlAccionesFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesFPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAccionesFPagoLayout.setVerticalGroup(
            pnlAccionesFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesFPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblFormaDePago.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblFormaDePago);

        pnlIndicadores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlIndicadores.setOpaque(false);

        lblPagEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPagEstado.setText("Pago Exacto:");

        lblTarjBancaria.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTarjBancaria.setText("Tarj. Bancaria:");

        lblDeficit.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDeficit.setText("Deficit Caja:");

        lblDetalle.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDetalle.setText("Detalle:");

        cmbPagoExacto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Si", "No" }));
        cmbPagoExacto.setName("pexacto"); // NOI18N
        cmbPagoExacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbPagoExactoKeyReleased(evt);
            }
        });

        cmbTarjetaBancaria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Si", "No" }));
        cmbTarjetaBancaria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTarjetaBancariaKeyReleased(evt);
            }
        });

        cmbDeficitCaja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Si", "No" }));
        cmbDeficitCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbDeficitCajaKeyReleased(evt);
            }
        });

        cmbDetalle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Si", "No" }));
        cmbDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbDetalleKeyReleased(evt);
            }
        });

        lblSobreHermes.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblSobreHermes.setText("Sobre Hermes:");

        cmbSobreHermes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Si", "No" }));
        cmbSobreHermes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSobreHermesKeyReleased(evt);
            }
        });

        lblTransacBancaria.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTransacBancaria.setText("Transacci�n Bancaria:");

        cmbTransaccionBancaria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "POS", "Delivery", "Manual" }));
        cmbTransaccionBancaria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTransaccionBancariaKeyReleased(evt);
            }
        });

        lblAperGaveta.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblAperGaveta.setText("Apertura Gaveta:");

        cmbAperturaGaveta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "Si", "No" }));
        cmbAperturaGaveta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbAperturaGavetaKeyReleased(evt);
            }
        });

        lblCompania.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCompania.setText("Cod Compania:");

        txtClienteCompania.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtClienteCompaniaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClienteCompaniaFocusLost(evt);
            }
        });
        txtClienteCompania.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteCompaniaKeyReleased(evt);
            }
        });

        lblDesCompania.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDesCompania.setText(" ");

        javax.swing.GroupLayout pnlIndicadoresLayout = new javax.swing.GroupLayout(pnlIndicadores);
        pnlIndicadores.setLayout(pnlIndicadoresLayout);
        pnlIndicadoresLayout.setHorizontalGroup(
            pnlIndicadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIndicadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlIndicadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIndicadoresLayout.createSequentialGroup()
                        .addComponent(lblPagEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPagoExacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTarjBancaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTarjetaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDeficit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDeficitCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlIndicadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlIndicadoresLayout.createSequentialGroup()
                                .addComponent(lblDetalle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlIndicadoresLayout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(lblSobreHermes)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSobreHermes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlIndicadoresLayout.createSequentialGroup()
                        .addComponent(lblTransacBancaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTransaccionBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblAperGaveta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbAperturaGaveta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCompania)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtClienteCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDesCompania, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlIndicadoresLayout.setVerticalGroup(
            pnlIndicadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIndicadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlIndicadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPagEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPagoExacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTarjBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTarjetaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeficit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDeficitCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDetalle)
                    .addComponent(cmbDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlIndicadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSobreHermes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSobreHermes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlIndicadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTransacBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDesCompania)
                    .addComponent(lblAperGaveta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAperturaGaveta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCompania, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteCompania, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTransaccionBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
        lblMensaje.setText("F1 para ver mas datos");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlIndicadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEntradasFPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAccionesFPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addComponent(pnlBuscadorFPago, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(pnlEntradasFPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlIndicadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBuscadorFPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMensaje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccionesFPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblFormaDePago.getRowCount()-1;
        numRegistros=0;
        cp.setFormaPago(mtp.getFila(numRegistros));
        setFormaPago();           
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblFormaDePago.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setFormaPago(mtp.getFila(numRegistros));
        setFormaPago();
        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblFormaDePago.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setFormaPago(mtp.getFila(numRegistros));
        setFormaPago();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblFormaDePago.getRowCount()-1;
        numRegistros=finalPag;
        cp.setFormaPago(mtp.getFila(numRegistros));
        setFormaPago();           

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        mtp = new ModeloTablaFormaPago(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblFormaDePago.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblFormaDePago,ModeloTablaFormaPago.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaFormaPago(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblFormaDePago.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblFormaDePago,ModeloTablaFormaPago.anchoColumnas);
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        mtp = new ModeloTablaFormaPago(tipoSeleccion,inicioPag,finalPag);
        numRegistros = mtp.getCantidadRegistros();
        tblFormaDePago.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblFormaDePago,ModeloTablaFormaPago.anchoColumnas);
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        Limpiar();
        ActivarCasillas();
        txtCodigo.setText(cp.getNuevoCodigo());
        txtCodigo.requestFocus();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradasFPago,true)){
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!this.verficarCambios()){
            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
                return;
            }            
        }
        
        boolean correcto = false; 

        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradasFPago,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Informaci�n Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        if(tipoSeleccion == -1){
            this.mtp = new ModeloTablaFormaPago(inicioPag,finalPag);
        }else if(tipoSeleccion == 1){
            this.mtp = new ModeloTablaFormaPago(tipoSeleccion,inicioPag,finalPag);
        }
        tblFormaDePago.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblFormaDePago,ModeloTablaFormaPago.anchoColumnas);
        this.numRegistros = mtp.getCantidadRegistros();
        Helper.ajustarSoloAnchoColumnas(tblFormaDePago,ModeloTablaFormaPago.anchoColumnas);
        DesActivarCasillas();
        tblFormaDePago.requestFocus();
}//GEN-LAST:event_btnGuardarActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Se perderan todos los datos ingresados\nEsta Seguro de Cancelar ","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }

        DesActivarCasillas();
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
}//GEN-LAST:event_btnSalirActionPerformed

private void txtClienteCompaniaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteCompaniaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_F1: 
            lblDesCompania.setText("");

            BuscarClienteCompania pvc = new BuscarClienteCompania();

            JLabel aviso = pvc.getAviso();

                        String msj = "Mostrando Clientes Compa�ia";
            JOptionPane.showInternalOptionDialog(this, pvc, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            if(pvc.getClienteCompania() != null){
                CClienteCompania cpLC = new CClienteCompania();
                cpLC.setClienteCompania(pvc.getClienteCompania());
                this.txtClienteCompania.setText(pvc.getCodigoClienteCompania());
                this.lblDesCompania.setText(pvc.getRazonSocial());
            }            
            break;
        case KeyEvent.VK_ENTER: BuscarClienteCia(txtClienteCompania.getText());
             break;
    }
}//GEN-LAST:event_txtClienteCompaniaKeyReleased

private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtDescrip.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtCodigoKeyReleased

private void txtDescripKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtAbrev.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtDescripKeyReleased

private void txtAbrevKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbrevKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: chbEstado.requestFocus();
             break;
    }    
}//GEN-LAST:event_txtAbrevKeyReleased

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbPagoExacto.requestFocus();
             break;
    }    
}//GEN-LAST:event_chbEstadoKeyReleased

private void cmbPagoExactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPagoExactoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTarjetaBancaria.requestFocus();
             break;
    }    
}//GEN-LAST:event_cmbPagoExactoKeyReleased

private void cmbTarjetaBancariaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTarjetaBancariaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbDeficitCaja.requestFocus();
             break;
    }    
}//GEN-LAST:event_cmbTarjetaBancariaKeyReleased

private void cmbDeficitCajaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDeficitCajaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbDetalle.requestFocus();
             break;
    }    
}//GEN-LAST:event_cmbDeficitCajaKeyReleased

private void cmbDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDetalleKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbSobreHermes.requestFocus();
             break;
    }    
}//GEN-LAST:event_cmbDetalleKeyReleased

private void cmbSobreHermesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSobreHermesKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbTransaccionBancaria.requestFocus();
             break;
    }    
}//GEN-LAST:event_cmbSobreHermesKeyReleased

private void cmbTransaccionBancariaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTransaccionBancariaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: cmbAperturaGaveta.requestFocus();
             break;
    }    
}//GEN-LAST:event_cmbTransaccionBancariaKeyReleased

private void cmbAperturaGavetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAperturaGavetaKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: txtClienteCompania.requestFocus();
             break;
    }    
}//GEN-LAST:event_cmbAperturaGavetaKeyReleased

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
    chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void txtClienteCompaniaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClienteCompaniaFocusGained
    if (txtClienteCompania.isEditable()) lblMensaje.setVisible(true);
}//GEN-LAST:event_txtClienteCompaniaFocusGained

private void txtClienteCompaniaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClienteCompaniaFocusLost
    lblMensaje.setVisible(false);
}//GEN-LAST:event_txtClienteCompaniaFocusLost
private void BuscarClienteCia(String CoClienteCompania){
    lblDesCompania.setText("");
    String[] Datos ={AtuxVariables.vCodigoCompania,CoClienteCompania};
    CCia.getRegistroPorPk(Datos);
    
    if(!StringUtils.isEmpty(CCia.getClienteCompania().getDeRazonSocial())){
        lblDesCompania.setText(CCia.getClienteCompania().getDeRazonSocial());
        if(txtClienteCompania.isEditable()){
            chbEstado.requestFocus();
        }
        
    }else{
        if(txtClienteCompania.isEditable()){
            if (JOptionPane.showConfirmDialog(this, "Lo c�digo ingresado no existe\nDesea buscarlo de una lista","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
                return;
            }
            BuscarClienteCompania bCliCia = new BuscarClienteCompania(tipoSeleccion);
            JLabel aviso = bCliCia.getAviso();
            String msj = (tipoSeleccion==-1?"Mostrando todos los Tipos de Cambios existentes":(tipoSeleccion==1?"Mostrando todo los Tipos de Cambios activos":"Mostrando todo los Tipos de Cambios No activos"));
            JOptionPane.showInternalOptionDialog(this, bCliCia, msj,JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{aviso},null);

            CCia.setClienteCompania(bCliCia.getClienteCompania());
            txtClienteCompania.setText(bCliCia.getCodigoClienteCompania());
            lblDesCompania.setText(bCliCia.getRazonSocial());
        }
    }
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chbEstado;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbAperturaGaveta;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbDeficitCaja;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbDetalle;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbPagoExacto;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbSobreHermes;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTarjetaBancaria;
    private elaprendiz.gui.comboBox.ComboBoxRect cmbTransaccionBancaria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAbrev;
    private javax.swing.JLabel lblAperGaveta;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCompania;
    private javax.swing.JLabel lblDeficit;
    private javax.swing.JLabel lblDesCompania;
    private javax.swing.JLabel lblDescrip;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblPagEstado;
    private javax.swing.JLabel lblSobreHermes;
    private javax.swing.JLabel lblTarjBancaria;
    private javax.swing.JLabel lblTransacBancaria;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesFPago;
    private javax.swing.JPanel pnlBuscadorFPago;
    private javax.swing.JPanel pnlEntradasFPago;
    private javax.swing.JPanel pnlIndicadores;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblFormaDePago;
    private elaprendiz.gui.textField.TextField txtAbrev;
    private elaprendiz.gui.textField.TextField txtClienteCompania;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtDescrip;
    // End of variables declaration//GEN-END:variables
}