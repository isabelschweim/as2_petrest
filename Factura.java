/*
 * Arquitectura de Sistemas II - Practica 3
 * Factura.java
 * Rodrigo De Lama - 100451775
 * Isabel Schweim - 100460211
 */

import org.json.simple.JSONObject;

public class Factura {
    public int id;
    public int idPedido;
    public double importe;
    public double descuento;
    public double base;
    public double iva;
    public double total;

    public Factura(JSONObject facturaData) {
        this.id = ((Long) facturaData.get("id")).intValue();
        this.idPedido = ((Long) facturaData.get("id_pedido")).intValue();
        this.importe = (double) facturaData.get("importe");
        this.descuento = (double) facturaData.get("descuento");
        this.base = (double) facturaData.get("base");
        this.iva = (double) facturaData.get("iva");
        this.total = (double) facturaData.get("total");
    }
}
