DROP TABLE ECVENTA.TMP_PRODUCTOS_FALTA_CERO CASCADE CONSTRAINTS;

CREATE GLOBAL TEMPORARY TABLE ECVENTA.TMP_PRODUCTOS_FALTA_CERO
(
  IN_TIPOPROBLEMA           VARCHAR2(20 BYTE),
  CO_PRODUCTO                CHAR(6 BYTE) CONSTRAINT CHK_TMP_PRDCTS_PRBLMA_PDDO_01 NOT NULL,
  CA_ATENDIDA                  NUMBER(5),
  VA_FRACCION_DELIVERY  NUMBER(6),
  STOCK                            NUMBER,
  DE_UNIDAD_LOCAL          VARCHAR2(60 BYTE),
  VA_FRACCION_LOCAL     NUMBER(6)               DEFAULT NULL
)
ON COMMIT PRESERVE ROWS
NOCACHE;

DROP PUBLIC SYNONYM TMP_PRODUCTOS_PROBLEMA_PEDIDO;

CREATE OR REPLACE PUBLIC SYNONYM TMP_PRODUCTOS_PROBLEMA_PEDIDO FOR ECVENTA.TMP_PRODUCTOS_PROBLEMA_PEDIDO;

GRANT DELETE, INSERT, SELECT, UPDATE ON ECVENTA.TMP_PRODUCTOS_PROBLEMA_PEDIDO TO R_VENTA;