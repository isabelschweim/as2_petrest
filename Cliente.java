/*
 * Arquitectura de Sistemas II - Practica 3
 * Cliente.java
 * Rodrigo De Lama - 100451775
 * Isabel Schweim - 100460211
 */

import org.json.simple.JSONObject;

public class Cliente {
    public int id;
    public String cif;
    public String nombre;
    public String direccion;
    public double descuento;

    public Cliente(JSONObject clienteData) {
        this.id = ((Long) clienteData.get("id")).intValue();
        this.cif = (String) clienteData.get("cif");
        this.nombre = (String) clienteData.get("nombre");
        this.direccion = (String) clienteData.get("direccion");
        this.descuento = (double) clienteData.get("descuento");
    }
}
