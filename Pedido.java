/*
 * Arquitectura de Sistemas II - Practica 3
 * Pedido.java
 * Rodrigo De Lama - 100451775
 * Isabel Schweim - 100460211
 */

import org.json.simple.JSONObject;

public class Pedido {
    public int id;
    public int idCliente;
    public String fecha;

    public Pedido(JSONObject pedidoData) {
        this.id = ((Long) pedidoData.get("id")).intValue();
        this.idCliente = ((Long) pedidoData.get("id_cliente")).intValue();
        this.fecha = (String) pedidoData.get("fecha");
    }
}
