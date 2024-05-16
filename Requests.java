/*
 * Arquitectura de Sistemas II - Practica 3
 * Requests.java
 * Rodrigo De Lama - 100451775
 * Isabel Schweim - 100460211
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Requests {

    private static String addr = "10.202.20.125"; // Direccion IP de Luis
    // private static String addr = "127.0.0.1"; // Para pruebas locales
    private static String user = "Pareja1";
    private static String pass = "rEf@ZO{TU";

    // Autenticar con las credenciales
    public static void Autenticar() {
        HTTP.Init(user, pass);
    }

    // Borrar todas las facturas
    public static void deleteFacturas() {
        HTTP.Delete("http://" + addr + "/petrest/facturas");
    }

    // Obtener un pedido a partir de su ID
    public static Pedido getPedido(int id) {
        JSONObject pedidoData = HTTP.Get_object("http://" + addr + "/petrest/pedidos/" + id);
        return new Pedido(pedidoData);
    }

    // Recoleccion de todos los pedidos en un array de objetos Pedido
    public static Pedido[] getPedidos() {
        JSONArray pedidosArray = HTTP.Get_array("http://" + addr + "/petrest/pedidos");
        Pedido[] pedidos = new Pedido[pedidosArray.size()];
        for (int i = 0; i < pedidosArray.size(); i++) {
            pedidos[i] = getPedido(((Long) pedidosArray.get(i)).intValue());
        }
        return pedidos;
    }

    // Obtener los datos de un cliente a partir de su ID
    public static Cliente getCliente(int id) {
        JSONObject clienteData = HTTP.Get_object("http://" + addr + "/petrest/clientes/" + id);
        return new Cliente(clienteData);
    }

    // Obtener los items de un pedido a partir de su ID
    public static Item[] getItems(int id) {
        JSONArray itemsArray = HTTP.Get_array("http://" + addr + "/petrest/pedidos/" + id + "/items");
        Item[] items = new Item[itemsArray.size()];
        for (int i = 0; i < itemsArray.size(); i++) {
            items[i] = new Item((JSONObject) itemsArray.get(i));
        }
        return items;
    }

    // Obtener un articulo a partir de su ID
    public static Articulo getArticulo(int id) {
        JSONObject articuloData = HTTP.Get_object("http://" + addr + "/petrest/articulos/" + id);
        return new Articulo(articuloData);
    }

    // Crear una nueva factura
    public static void postFactura(int idPedido, double importe) {
        JSONObject factura = new JSONObject();
        factura.put("id_pedido", idPedido);
        factura.put("importe", importe);
        HTTP.Post("http://" + addr + "/petrest/facturas", factura.toString());
    }

    // Obtener el id de la nueva factura creada
    public static int getFacturaId(int i) {
        JSONArray idNuevaFactura = HTTP.Get_array("http://" + addr + "/petrest/facturas");
        Long nuevaFacturaIdLong = (Long) idNuevaFactura.get(i-1); // Obtener el id del array de la nueva factura (debe ser long)
        return nuevaFacturaIdLong.intValue();
    }

    // Rellenar una factura
    public static void fillFactura(int id, double importe, double descuento, double base, double iva, double total) {
        JSONObject facturaModificacion = new JSONObject();
        facturaModificacion.put("importe", importe);
        facturaModificacion.put("descuento", descuento);
        facturaModificacion.put("base", base);
        facturaModificacion.put("iva", iva);
        facturaModificacion.put("total", total);
        HTTP.Put("http://" + addr + "/petrest/facturas/" + id, facturaModificacion.toString());
    }

    // Descargar factura completa
    public static Factura getFactura(int id) {
        JSONObject facturaData = HTTP.Get_object("http://" + addr + "/petrest/facturas/" + id);
        return new Factura(facturaData);
    }
}
