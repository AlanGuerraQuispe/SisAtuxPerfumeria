package atux.vistas.catalogo;
import atux.controllers.CDescuentoVenta;
import atux.modelgui.ModeloTablaDescuentoVenta;
import atux.util.ECampos;
import atux.util.FiltraEntrada;
import atux.util.Helper;
import atux.util.common.AtuxVariables;
import atux.util.common.AtuxDBUtility;
import atux.util.common.AtuxUtility;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author Cesar Ruiz  PC
 */
public class IDescuentoVenta extends javax.swing.JInternalFrame {
    private CDescuentoVenta cp;
    private ModeloTablaDescuentoVenta mtp;
    private boolean esActualizacion = false;
    private int tipoSeleccion = -1; //-1 todo,1 activos, 0 No activos
    public int finalPag = 0;//cont
    public int tmpFp = finalPag;
    public int inicioPag = 0;
    public int numRegistros = 0;
    JInternalFrame ifr;
    private final Log logger = LogFactory.getLog(getClass());    
    JOptionPane op;
    
    /** Creates new form IImpuestoIGV */
    public IDescuentoVenta() {
        initComponents();
        cp = new CDescuentoVenta();
        setFiltroTexto();        
        setEventSelectionModel(tblListado.getSelectionModel());
        DesActivarCasillas();
        CargaDatos();
    }

    public final void CargaDatos(){
        String columnas[] = new String[]{"CO_COMPANIA"};
        Object valores[] = new Object[]{AtuxVariables.vCodigoCompania};
        mtp = new ModeloTablaDescuentoVenta(columnas,valores);
        numRegistros = mtp.getCantidadRegistros();
        tblListado.setModel(mtp);
        Helper.ajustarSoloAnchoColumnas(tblListado,ModeloTablaDescuentoVenta.anchoColumnas);
    }
    
    private void setFiltroTexto(){
        Helper.setFiltraEntrada(txtCodigo.getDocument(), FiltraEntrada.NUM_LETRAS, 10, false);
    }

    private void setEventSelectionModel(ListSelectionModel lsm){
        ListSelectionListener lsl = new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                selectionEvent(e);
            }
        };
        lsm.addListSelectionListener(lsl);
    }
    
    private void selectionEvent(ListSelectionEvent e){
        if(tblListado.getSelectedRow() != -1){
            numRegistros = tblListado.getSelectedRow();
            cp.setDescuentoVenta(mtp.getFila(tblListado.getSelectedRow()));
            setDescuentoEspecial();           
            btnModificar.setEnabled(true);
       }
    }
    
    private void setDescuentoEspecial(){
        Limpiar();

        this.txtCodigo.setText(String.valueOf(cp.getDescuentoVenta().getCoDescuentoVenta()));
        this.txtDescripcion.setText(String.valueOf(cp.getDescuentoVenta().getDeDescuentoVenta()));
        this.txtDescuento.setText(String.valueOf(cp.getDescuentoVenta().getCaDescuento()));
        this.txtCantidad.setText(String.valueOf(cp.getDescuentoVenta().getNuProductosVenta()));

        if("A".equals(cp.getDescuentoVenta().getEsDescuentoVenta())){
            chbSetActivo(true); 
        }else{
            chbSetActivo(false); 
        }
        
        if("A".equals(cp.getDescuentoVenta().getEsDescuentoEspecial())){
            chbSetDsctoEsp(true); 
        }else{
            chbSetDsctoEsp(false); 
        }        
    }
    
    private void Limpiar(){
        this.txtCodigo.setText("");
        this.chbEstado.setSelected(true);
        chbSetActivo(true);
    }

    private void ActivarCasillas(){
        pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, true, new Component[]{lblCodigo,lblDescripcion,lblDescuento,lblCantidad,lblDsctoEspecial,lblEstado,txtCodigo,txtDescripcion,txtCantidad,txtDescuento},false,"");
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
    }
    private void DesActivarCasillas(){
        this.pnlEntradas.setEnabled(true);
        ECampos.setEditableTexto(this.pnlEntradas, false, new Component[]{lblCodigo,lblDescripcion,lblDescuento,lblCantidad,lblDsctoEspecial,lblEstado,txtCodigo,txtDescripcion,txtCantidad,txtDescuento},false,"");        
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

        esActualizacion = false;
        this.pnlBuscadorTDeCambio.setVisible(true);
        logger.info(txtCodigo.getText());
    }

    public boolean verficarCambios(){
        // TODO aguerra verificar     
        
        if(!this.txtCodigo.getText().equals(String.valueOf(cp.getDescuentoVenta().getCoDescuentoVenta()))){
            return true;
        }else if(!chbEstado.isSelected() != ("I".equals(cp.getDescuentoVenta().getEsDescuentoVenta()))){
            return true;
        }
        return false;
    }
    
    private boolean guardarActualizar() throws SQLException{
        cp.getDescuentoVenta().setCoCompania(AtuxVariables.vCodigoCompania);
        cp.getDescuentoVenta().setCoDescuentoVenta(txtCodigo.getText());
        cp.getDescuentoVenta().setDeDescuentoVenta(txtDescripcion.getText());
        cp.getDescuentoVenta().setNuProductosVenta(Integer.parseInt(txtCantidad.getText()));
        cp.getDescuentoVenta().setCaDescuento(Integer.parseInt(txtDescuento.getText()));
        
        if (chbEstado.isSelected()){
            cp.getDescuentoVenta().setEsDescuentoVenta("A");
        }else{
            cp.getDescuentoVenta().setEsDescuentoVenta("I");
        }

        if (chbDsctoEsp.isSelected()){
            cp.getDescuentoVenta().setEsDescuentoEspecial("A");
        }else{
            cp.getDescuentoVenta().setEsDescuentoEspecial("I");
        }
        
        Boolean EstadoGuardar=false;
            
        if(!esActualizacion){
            cp.getDescuentoVenta().setCoDescuentoVenta(cp.getNuevoCodigo(AtuxVariables.vCodigoCompania));
            cp.getDescuentoVenta().setIdCreaDescuentoVenta(AtuxVariables.vIdUsuario);
            cp.getDescuentoVenta().setFeCreaDescuentoVenta(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            EstadoGuardar = cp.guardarRegistro(cp.getDescuentoVenta());
        }else{
            cp.getDescuentoVenta().setIdModDescuentoVenta(AtuxVariables.vIdUsuario);
            cp.getDescuentoVenta().setFeModDescuentoVenta(FormatoFecha(AtuxDBUtility.getFechaHoraBD(1)));
            
            int act = cp.actualizarRegistro(cp.getDescuentoVenta());
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
            Logger.getLogger(IDescuentoVentaAnt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Fecha;
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

    public void chbSetDsctoEsp(boolean opcion){
        chbDsctoEsp.setSelected(true);
        chbDsctoEsp.setText("Activo");
        chbDsctoEsp.setBackground(new Color(104,204,0));
        chbDsctoEsp.setForeground(Color.BLACK);
        if(!opcion){
            chbDsctoEsp.setSelected(false);
            chbDsctoEsp.setText("No Activo");
            chbDsctoEsp.setBackground(Color.red);
            chbDsctoEsp.setForeground(Color.WHITE);
        }
    }

    public void setCodigoProducto(String CodProducto){
        txtCodigo.setText(CodProducto);
    }
    
    public void setDescripcionProducto(String DescripcionProducto){
        txtDescripcion.setText(DescripcionProducto);
    }
    
    public void setUnidadProducto(String UnidadProducto){
        txtCantidad.setText(UnidadProducto);
    }


    private void getOptionPane(){
        if(op != null){
            return;
        }
        Component pdr =this.getParent(); 
        while(pdr.getParent() != null){
            if(pdr instanceof JOptionPane){
                op = (JOptionPane)pdr;
                break;
            }            
            pdr = pdr.getParent();
        }
    }
    public JLabel getAviso(){
        return lbAviso;
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
        pnlEntradas = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCodigo = new elaprendiz.gui.textField.TextField();
        txtDescripcion = new elaprendiz.gui.textField.TextField();
        txtCantidad = new elaprendiz.gui.textField.TextField();
        lblEstado = new javax.swing.JLabel();
        chbEstado = new javax.swing.JCheckBox();
        txtDescuento = new elaprendiz.gui.textField.TextField();
        lblDescuento = new javax.swing.JLabel();
        lblDsctoEspecial = new javax.swing.JLabel();
        chbDsctoEsp = new javax.swing.JCheckBox();
        pnlBuscadorTDeCambio = new javax.swing.JPanel();
        btnPrimero = new elaprendiz.gui.button.ButtonRect();
        btnAnterior = new elaprendiz.gui.button.ButtonRect();
        btnSiguiente = new elaprendiz.gui.button.ButtonRect();
        btnUltimo = new elaprendiz.gui.button.ButtonRect();
        rbTodos = new javax.swing.JRadioButton();
        rbAtivos = new javax.swing.JRadioButton();
        rbNoActivos = new javax.swing.JRadioButton();
        lblListado = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        pnlAccionesTDeCambio = new javax.swing.JPanel();
        btnNuevo = new elaprendiz.gui.button.ButtonRect();
        btnModificar = new elaprendiz.gui.button.ButtonRect();
        btnGuardar = new elaprendiz.gui.button.ButtonRect();
        btnCancelar = new elaprendiz.gui.button.ButtonRect();
        btnSalir = new elaprendiz.gui.button.ButtonRect();
        lbAviso = new javax.swing.JLabel();

        setBorder(null);
        setTitle("Formulario de Forma de Pago");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atux/resources/fondoazulceleste.jpg"))); // NOI18N

        pnlEntradas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "Descuentos", 1, 2));
        pnlEntradas.setOpaque(false);

        lblCodigo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCodigo.setText("Codigo:");

        lblDescripcion.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescripcion.setText("Descripcion:");

        lblCantidad.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCantidad.setText("Cantidad:");

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

        txtCantidad.setEditable(false);
        txtCantidad.setDireccionDeSombra(30);
        txtCantidad.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtCantidad.setFont(new java.awt.Font("Arial", 0, 12));
        txtCantidad.setName("pdescrip"); // NOI18N
        txtCantidad.setPreferredSize(new java.awt.Dimension(120, 25));

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 14));
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

        txtDescuento.setEditable(false);
        txtDescuento.setDireccionDeSombra(30);
        txtDescuento.setDisabledTextColor(new java.awt.Color(255, 102, 102));
        txtDescuento.setFont(new java.awt.Font("Arial", 0, 12));
        txtDescuento.setName("pdescrip"); // NOI18N
        txtDescuento.setPreferredSize(new java.awt.Dimension(120, 25));

        lblDescuento.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDescuento.setText("Descuento:");

        lblDsctoEspecial.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblDsctoEspecial.setText("Dsto Especial:");

        chbDsctoEsp.setBackground(new java.awt.Color(51, 153, 255));
        chbDsctoEsp.setFont(new java.awt.Font("Tahoma", 1, 14));
        chbDsctoEsp.setSelected(true);
        chbDsctoEsp.setText("Inactivo");
        chbDsctoEsp.setEnabled(false);
        chbDsctoEsp.setPreferredSize(new java.awt.Dimension(100, 25));
        chbDsctoEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDsctoEspActionPerformed(evt);
            }
        });
        chbDsctoEsp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chbDsctoEspKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlEntradasLayout = new javax.swing.GroupLayout(pnlEntradas);
        pnlEntradas.setLayout(pnlEntradasLayout);
        pnlEntradasLayout.setHorizontalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCodigo)
                        .addGap(10, 10, 10)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblDescripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDescuento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDsctoEspecial)
                        .addGap(5, 5, 5)
                        .addComponent(chbDsctoEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEstado)
                        .addGap(7, 7, 7)
                        .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(3, Short.MAX_VALUE))
        );
        pnlEntradasLayout.setVerticalGroup(
            pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasLayout.createSequentialGroup()
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEntradasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDescuento)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbDsctoEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDsctoEspecial)
                    .addComponent(chbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado))
                .addContainerGap())
        );

        pnlBuscadorTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlBuscadorTDeCambio.setOpaque(false);
        pnlBuscadorTDeCambio.setPreferredSize(new java.awt.Dimension(575, 37));
        pnlBuscadorTDeCambio.setLayout(null);

        btnPrimero.setBackground(new java.awt.Color(102, 204, 0));
        btnPrimero.setText("<<");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnPrimero);
        btnPrimero.setBounds(12, 2, 48, 25);

        btnAnterior.setBackground(new java.awt.Color(102, 204, 0));
        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnAnterior);
        btnAnterior.setBounds(65, 2, 40, 25);

        btnSiguiente.setBackground(new java.awt.Color(102, 204, 0));
        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnSiguiente);
        btnSiguiente.setBounds(111, 2, 40, 25);

        btnUltimo.setBackground(new java.awt.Color(104, 204, 0));
        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });
        pnlBuscadorTDeCambio.add(btnUltimo);
        btnUltimo.setBounds(156, 2, 48, 25);

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
        rbTodos.setBounds(209, 2, 69, 25);

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
        rbAtivos.setBounds(283, 2, 77, 25);

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
        rbNoActivos.setBounds(365, 2, 101, 25);

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
        lblListado.setViewportView(tblListado);

        pnlAccionesTDeCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(0), "", 1, 2));
        pnlAccionesTDeCambio.setOpaque(false);
        pnlAccionesTDeCambio.setPreferredSize(new java.awt.Dimension(550, 50));

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

        javax.swing.GroupLayout pnlAccionesTDeCambioLayout = new javax.swing.GroupLayout(pnlAccionesTDeCambio);
        pnlAccionesTDeCambio.setLayout(pnlAccionesTDeCambioLayout);
        pnlAccionesTDeCambioLayout.setHorizontalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbAviso))
        );
        pnlAccionesTDeCambioLayout.setVerticalGroup(
            pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccionesTDeCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlEntradas, 0, 636, Short.MAX_VALUE)
                    .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblListado, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBuscadorTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblListado, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlAccionesTDeCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void chbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEstadoActionPerformed
        chbSetActivo(chbEstado.isSelected());
}//GEN-LAST:event_chbEstadoActionPerformed

private void chbEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbEstadoKeyReleased
    switch (evt.getKeyCode()){
        case KeyEvent.VK_ENTER: btnGuardar.requestFocus();
             break;
    }    
}//GEN-LAST:event_chbEstadoKeyReleased

private void chbDsctoEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDsctoEspActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_chbDsctoEspActionPerformed

private void chbDsctoEspKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbDsctoEspKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_chbDsctoEspKeyReleased

private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=0;
        cp.setDescuentoVenta(mtp.getFila(numRegistros));
        setDescuentoEspecial();           
        return;
}//GEN-LAST:event_btnPrimeroActionPerformed

private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        finalPag = tblListado.getRowCount() - 1;
        numRegistros = numRegistros - 1;
        if (numRegistros == -1){
            numRegistros=0;
        }
        cp.setDescuentoVenta(mtp.getFila(numRegistros));
        setDescuentoEspecial();           

        return;
}//GEN-LAST:event_btnAnteriorActionPerformed

private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros = numRegistros + 1;
        if (finalPag < numRegistros){
            numRegistros=finalPag;
        }
        cp.setDescuentoVenta(mtp.getFila(numRegistros));
        setDescuentoEspecial();           

        return;
}//GEN-LAST:event_btnSiguienteActionPerformed

private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        finalPag = tblListado.getRowCount()-1;
        numRegistros=finalPag;
        cp.setDescuentoVenta(mtp.getFila(numRegistros));
        setDescuentoEspecial();           

        return;
}//GEN-LAST:event_btnUltimoActionPerformed

private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        tipoSeleccion = -1;
        inicioPag = 0;  
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbTodosActionPerformed

private void rbAtivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAtivosActionPerformed
        tipoSeleccion = 1;
        inicioPag = 0; 
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(true);
        Limpiar();
}//GEN-LAST:event_rbAtivosActionPerformed

private void rbNoActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoActivosActionPerformed
        tipoSeleccion = 0;
        inicioPag = 0; 
        finalPag = tmpFp;
        CargaDatos();
        chbSetActivo(false);
        Limpiar();
}//GEN-LAST:event_rbNoActivosActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        esActualizacion = false;        
        Limpiar();
        ActivarCasillas();
        txtCodigo.requestFocus();        
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        esActualizacion = true;
        ActivarCasillas();
        txtCodigo.requestFocus();
}//GEN-LAST:event_btnModificarActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Esta Seguro de Guardar los Datos","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
            return;
        }
        if(ECampos.esObligatorio(this.pnlEntradas,true)){
            JOptionPane.showInternalMessageDialog(this, "Los campos en rojo son obligatorios", "Llene los campos obligatorios", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!this.verficarCambios()){
            if (JOptionPane.showConfirmDialog(this, "No se ha realizado ningun Cambio,\n Esta seguro de Continuar","Mensaje del Sistema",JOptionPane.YES_NO_OPTION)==JOptionPane.NO_OPTION){
                return;
            }            
        }
        
        boolean correcto = false; 

        if(!this.verficarCambios()){
            JOptionPane.showMessageDialog(this, "Debe cambiar por lo menos algun valor", "No hubo cambios", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            correcto = guardarActualizar();
        } catch (SQLException ex) {
            Logger.getLogger(IProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(!correcto){
            JOptionPane.showMessageDialog(this, "Error: no se pudo guardar.", "Error al guardar el registro", JOptionPane.ERROR_MESSAGE);
            ECampos.esObligatorio(this.pnlEntradas,false);
            return;
        }
        
        AtuxUtility.aceptarTransaccion();
        JOptionPane.showMessageDialog(this, "Información Guardada Satisfactoriamente", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);

        CargaDatos();
        DesActivarCasillas();
        tblListado.requestFocus();
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
    private javax.swing.JCheckBox chbDsctoEsp;
    private javax.swing.JCheckBox chbEstado;
    private javax.swing.JLabel lbAviso;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblDsctoEspecial;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JScrollPane lblListado;
    private elaprendiz.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel pnlAccionesTDeCambio;
    private javax.swing.JPanel pnlBuscadorTDeCambio;
    private javax.swing.JPanel pnlEntradas;
    private javax.swing.JRadioButton rbAtivos;
    private javax.swing.JRadioButton rbNoActivos;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tblListado;
    private elaprendiz.gui.textField.TextField txtCantidad;
    private elaprendiz.gui.textField.TextField txtCodigo;
    private elaprendiz.gui.textField.TextField txtDescripcion;
    private elaprendiz.gui.textField.TextField txtDescuento;
    // End of variables declaration//GEN-END:variables
}
