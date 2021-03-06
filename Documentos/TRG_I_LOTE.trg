CREATE OR REPLACE TRIGGER "ECVENTA"."TRG_I_LOTE" 
BEFORE INSERT
ON LOGISTICA.LGTD_RECEPCION_PRODUCTO
REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
DECLARE
vTiDocumentoRecepcion   LGTC_RECEPCION_PRODUCTO.TI_DOCUMENTO_RECEPCION%TYPE;
vNuDocumentoRecepcion  LGTC_RECEPCION_PRODUCTO.NU_DOCUMENTO_RECEPCION%TYPE;

BEGIN
   IF  :NEW.NU_LOTE IS NOT NULL THEN
     
     SELECT TI_DOCUMENTO_RECEPCION,NU_DOCUMENTO_RECEPCION  INTO vTiDocumentoRecepcion,vNuDocumentoRecepcion
     FROM  LOGISTICA.LGTC_RECEPCION_PRODUCTO LGTC
     WHERE LGTC.CO_COMPANIA = :NEW.CO_COMPANIA
         AND LGTC.CO_LOCAL        = :NEW.CO_LOCAL
         AND LGTC.NU_RECEPCION_PRODUCTO = :NEW.NU_RECEPCION_PRODUCTO;
     
     INSERT INTO VENTA.LGTR_PRODUCTO_LOTE (
                        CO_COMPANIA, CO_LOCAL, CO_PRODUCTO, 
                        FE_INGRESO, FE_VENCIMIENTO, LOTE, 
                        TI_DOCUMENTO_RECEPCION, NU_DOCUMENTO_RECEPCION, 
                        ID_CREA_FORMA_PAGO_PEDIDO, FE_CREA_FORMA_PAGO_PEDIDO)
                 VALUES(:NEW.CO_COMPANIA,:NEW.CO_LOCAL,:NEW.CO_PRODUCTO,
                            SYSDATE,:NEW.FE_VENCE_PRODUCTO,:NEW.NU_LOTE,
                            vTiDocumentoRecepcion,vNuDocumentoRecepcion,'SISTEMAS',SYSDATE);
   END IF;
END ;
/

SELECT A.*,A.ROWID FROM  LGTD_RECEPCION_PRODUCTO A
WHERE CO_COMPANIA='001'
AND CO_LOCAL='005'
AND NU_RECEPCION_PRODUCTO='0000074814'

SELECT A.*,A.ROWID FROM  LGTC_RECEPCION_PRODUCTO A
WHERE CO_COMPANIA='001'
AND CO_LOCAL='005'
AND TRUNC(FE_CREA_RECEPCION_PRODUCTO)>TO_DATE('01/05/2015','DD/MM/YYYY')

SELECT * FROM VENTA.LGTR_PRODUCTO_LOTE      