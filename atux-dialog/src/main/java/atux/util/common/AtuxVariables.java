package atux.util.common;


import java.util.ArrayList;


public class AtuxVariables {

    public static final String MONEDA_SOLES     = "01";
    public static final String MONEDA_DOLARES   = "02";

    public static final String IMPUESTO_EXONERADO = "00";
    public static final String IMPUESTO_IGV       = "01";

    public static final int FORMATO_FECHA       = 1;
    public static final int FORMATO_FECHA_HORA  = 2;

    public static String TIPO_PEDIDO_NORMAL     = "1";
    public static String TIPO_PEDIDO_DELIVERY   = "2";
    public static String TIPO_PEDIDO_MANUAL     = "3";
    public static String TIPO_PEDIDO_ESPECIAL   = "5";
    public static String TIPO_PEDIDO_CREDITO    = "8";

    public static String TICKETERA_EPSON        = "EPSON";

    public static String TIPO_CAJA_TRADICIONAL    = "T";
    public static String TIPO_CAJA_MULTIFUNCIONAL = "M";

    public static final String TIPO_BOLETA         = "01";
    public static final String TIPO_FACTURA        = "02";
    public static final String TIPO_GUIA           = "03";
    public static final String TIPO_TICKET_BOLETA  = "05";
    public static final String TIPO_TICKET_FACTURA = "06";
    public static final String TIPO_BOLETA_MANUAL  = "15";
    public static final String TIPO_FACTURA_MANUAL = "16";

    public static String PERSONA_NATURAL  = "Natural";
    public static String PERSONA_JURIDICA = "Juridica";

    public static String NUMERACION_PEDIDO        = "001";
    public static String NUMERACION_PEDIDO_DIARIO = "012";

    public static final String TIPO_DOCUMENTO_PEDIDO = "06";
    /**
     * Movimientos de Caja
     */
    public static String NUMERACION_MOVIMIENTO_CAJA    = "011";
    public static String TIPO_MOVIMIENTO_CAJA_APERTURA = "A";
    public static String TIPO_MOVIMIENTO_CAJA_ARQUEO   = "R";
    public static String TIPO_MOVIMIENTO_CAJA_CIERRE   = "C";
    public static String TIPO_MOVIMIENTO_CAJA_ENTREGA_PARCIAL = "E";

    /**
     * Grupos de Motivo
     */
    public static final String GRUPO_MOTIVO_KARDEX           = "01";
    public static final String GRUPO_MOTIVO_PEDIDO_ADICIONAL = "02";
    public static final String GRUPO_MOTIVO_TRANSF_PRODUCTO  = "03";
    public static final String GRUPO_MOTIVO_AJUSTE_INVENTARIO= "04";
    /**
     * Motivos Kardex
     */
    public static final String MOTIVO_KARDEX_VENTA             = "001";
    public static final String MOTIVO_KARDEX_DEVOLUCION_VENTA  = "054";
    public static final String MOTIVO_KARDEX_ANULACION_PEDIDO  = "053";
    public static final String MOTIVO_KARDEX_GUIA_SALIDA       = "002";

    public static final String GRUPO_MOTIVO_ANULACION_PEDIDO     = "07";
    public static final String MOTIVO_ANULACION_NO_COBRADO       = "001";
    public static final String MOTIVO_ANULACION_UNIR_PEDIDO      = "002";
    public static final String MOTIVO_ANULACION_DEVOLUCION_VENTA = "003";

    public static final String FORMA_PAGO_EFECTIVO                = "00000";
    public static final String FORMA_PAGO_DOLARES                 = "00007";
    public static final String FORMA_PAGO_CREDITO                 = "01376";

    public static String vTipoPedido         = AtuxVariables.TIPO_PEDIDO_NORMAL;
    public static String vNumeroPedidoDiario = new String("0000");
    public static String vNumeroPedido       = new String("");

    public static String vCoFormaPagoConvenio  = new String("");
    public static String vVaMontoCoPagoCliente = new String("");

    /**
     * Roles del Sistema
     */
    public static final String ROL_VENDEDOR   = "02";
    public static final String ROL_CAJERO     = "04";

    /** Almacena el Número de Comprobante que general el Pedido */
    public static String vNuComprobantePago = "";

    /** Almacena el Número de Impresora para Boleta */
    public static String vNuImpresoraBoleta = "";

    /** Almacena la Cola de Impresión para Boletas */
    public static String vDeColaImpresoraBoleta = "";

    /** Almacena el Número de Impresora para Factura */
    public static String vNuImpresoraFactura = "";

    /** Almacena la Cola de Impresión para Facturas */
    public static String vDeColaImpresoraFactura = "";

    /** Almacena el Número de Impresora para Guias */
    public static String vNuImpresoraGuia = "";

    /** Almacena la Cola de Impresión para Guias */
    public static String vDeColaImpresoraGuia = "";

    //almacena si tienen o no lectora
    public static boolean vTieneLectorTarjetas=false;

    /** Almacena los nombres de las formas de pago asociados a un Pedido **/
    public static String vNombresFormasPago = "";

    /** Almacena el Número de la Caja que realiza la Cobranza */
    public static String vNuCaja = "";

    /** Almacena el Número de Turno de la Caja aperturada */
    public static String vNuTurno = "";

    /** Almacena el Indicador si la Caja estÃ¡ abierta */
    public static String vInCajaAbierta = "";

    public static final int ITEMS_POR_COMPROBANTE = 10;

    //Almacena el Tipo de Cliente
    public static String vTipoCliente = new String("");

    // Almacena el Nombre que aparecerÃ¡ en el Comprobante de Pago    
    public static String vANombreDe = new String("");

    public static String vNumeroTelefono = new String("");

    //Almacena el RUC del Cliente al que se Factura    
    public static String vRUCFacturar = new String("");

    // Almacena la Dirección que aparecerá¡ en el Comprobante de Pago
    public static String vDireccion = new String("");


    public static ArrayList vFormasPago = new ArrayList();
    /**
     * Indica si el producto esta afectado por venta por tarjeta electrÃ³nica, sus
     * valores son A= tiene laboratorio virtual,
     * N = no tiene laboratorio virtual,
     */
    public static String vIndicaProductoTarjElec = new String("");
    /**
     * Almacena el modo en el que fuÃ© cerrada una Ventana.
     * Si la ventana es cerrada Aceptando se almacena TRUE, en caso contrario,
     * es decir, se cierra la ventana pulsando la tecla [Esc] se almacena FALSE
     */
    public static boolean vAceptar = false;
    /**
     * Almacena el Código de la Compañía a la cual se pertenece
     */
    public static String vCodigoCompania = "";
    /**
     * Almacena el Código de Compañía usado por la Oficina de Personal
     */
    public static String vCodigoCompaniaRRHH = "";
    /**
     * Almacena el Local donde se ejecuta la Aplicación
     */
    public static String vCodigoLocal = "";
    /**
     * Almacena la descripción corta del Local
     */
    public static String vDescripcionCortaLocal = "";

    /**
     * Almacena la descripción del Local
     */
    public static String vDescripcionLocal = "";

    /**
     * Almacena el Tipo de Caja establecido según disposición del Local.
     * Los tipos existentes son :
     * Tradicional : Existen vendedores y un determinado Número de cajeros
     * Multifuncional : Existen vendedores - cajeros (ambas funciones)
     */
    public static String vTipoCaja = "";
    /**
     * Almacena el Secuencial del Usuario logueado a la Aplicación
     */
    public static String vNuSecUsuario = "";
    /**
     * Numero de pedido de reposición
     */
    public static String vNumeroPedidoProd = "0";
    /**
     * Almacena Secuencial - Codigo del Cajero
     */
    public static String vNuSecUsuarioCajero = "";
    /**
     * Almacena el Id del Usuario logueado a la Aplicación
     */
    public static String vIdUsuario = "";
    /**
     * Almacena el Nombre del Usuario logueado a la Aplicación
     */
    public static String vNoUsuario = "";
    /**
     * Almacena el Apellido Paterno del Usuario logueado a la Aplicación
     */
    public static String vPaternoUsuario = "";

    /**
     * Almacena el Apellido Materno del Usuario logueado a la Aplicación
     */
    public static String vMaternoUsuario = "";

    /**
     * Almacena el Tipo de Cambio usado por la Aplicación
     */
    public static double vTipoCambio = 0.00;

    /**
     * Almacena el tipo de cambio con el que se trabaja el Pedido
     */
    public static double vTipoCambioPedido = 0.00;

    /**
     * Almacena el valor del Impuesto General a las Ventas - 19.00%
     */
    public static double vIgvPorcentaje = 0.00;

    /**
     * Almacena el valor del Impuesto General a las Ventas para Cálculo - 1.19
     */
    public static double vIgvCalculo = 0.00;

    public static String vImpresoraReporte     = "LPT1";
    public static String vImpresoraTestigo     = "LPT1";
    public static String vImpresoraComanda     = "LPT1";
    public static String vImpresoraTicketBoleta= "";
    public static String vImpresoraBoleta      = "";
    public static String vImpresoraFactura     = "";
    public static String vImpresoraGuia        = "";
    public static String vImpresoraGuiaLote    = "LPT1";

    public static String vNombrePC = "";
    public static String vIP_PC = "";

    public static String vDepartamentoLocal = "";
    public static String vProvinciaLocal    = "";
    public static String vDescripcionCompania = new String("");

    public static int vCifraRedondeo        = 5;
    public static String vDeMensajeTicket   = "";
    public static String vDeImpresoraBoleta = "";
    public static String vCortaTicket       = "S";
    public static double vRedondearTo       = 0.1;

    public static String vCompaniaFono = "";
    public static String vCompaniaDireccion = "Jr. Elias Aguirre Nº141 Of 308"; //maximo 32 caracteres
    public static String vCompaniaDireccionWeb = "www.atux.com";

    public static String vDireccionLocal    = "";
    public static String vDistritoLocal     = "";
    public static String vNuRucCompania     = "";
    public static String vInComprobanteManual = "N";
    public static String vInTicketBoleta    = "N";
    public static String vInTicketFactura   = "N";
    public static boolean vCobroConRedondeo = true;
    public static boolean vModoTest         = false;

    public static boolean vOcultarReimpresionGuias = true;

    public static String  vModelCashDrawer= "";

    /**
     * Indica si se maneja el puslso o no
     */
    public static  boolean pulsoCashDrawer = false;

    public static ArrayList arrayProductos;

    public static ArrayList arrayClientes;

    public static int vDiasPagoCredito = 15;

    public static double vDescuentox1 = 0.0;
    public static double vDescuentox2 = 0.0;
    public static double vDescuentox2a = 0.0;
    public static double vDescuentox3 = 0.0;
    public static String vTicketera = "STAR";
    public static String vCoSucursal = "001";

    public AtuxVariables() {
    }

}
