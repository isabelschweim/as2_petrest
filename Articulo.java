/*
 * Arquitectura de Sistemas II - Practica 3
 * Articulo.java
 * Rodrigo De Lama - 100451775
 * Isabel Schweim - 100460211
 */

import org.json.simple.JSONObject;

public class Articulo {
    public int id;
    public String referencia;
    public String nombre;
    public double precio;

    public Articulo(JSONObject articuloData) {
        this.id = ((Long) articuloData.get("id")).intValue();
        this.referencia = (String) articuloData.get("referencia");
        this.nombre = (String) articuloData.get("nombre");
        this.precio = ((Number) articuloData.get("precio")).doubleValue();
    }
}
