/*
 * Arquitectura de Sistemas II - Práctica 3
 * Paso 3
 * Rodrigo De Lama - 100451775
 * Isabel Schweim - 100460211
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Facturar {

    public static void main(String[] args) {

        // String addr = "10.202.20.125"; // Direccion IP de Luis
        String addr = "127.0.0.1";

        // Inicializar con nuestras credenciales
        HTTP.Init("Pareja1", "rEf@ZO{TU");

        // Recolectar todos los pedidos
        JSONArray pedidosIds = HTTP.Get_array("http://"+ addr + "/petrest/pedidos");
        int numPedidos = pedidosIds.size();

        // Restablecer las facturas
        HTTP.Delete("http://" + addr + "/petrest/facturas");

        // Imprimir por pantalla cada factura
        for (int i = 1; i <= numPedidos; i++) {
            System.out.println("######################################################################################");
            System.out.println("FACTURA");
            
            // Obtener el pedido
            JSONObject pedido = HTTP.Get_object("http://"+ addr + "/petrest/pedidos/" + i);

            // Obtener la fecha del pedido
            Object fechaObject = pedido.get("fecha");
            String fechaSinFormato = (fechaObject != null) ? fechaObject.toString() : "";
            String fecha = formatFecha(fechaSinFormato);
            System.out.printf("%-15s %s\n","Fecha:", fecha);
            System.out.println();

            // Obtener los datos del cliente
            JSONObject clienteObject = HTTP.Get_object("http://"+ addr + "/petrest/clientes/" + pedido.get("id_cliente"));
            Cliente cliente = new Cliente(clienteObject);

            System.out.printf("%-15s %s\n","Cliente:", cliente.nombre);
            System.out.printf("%-15s %s\n","CIF", cliente.cif);
            System.out.printf("%-15s %s\n","Dirección:", cliente.direccion);
            System.out.println();
            System.out.printf("%-10s %-50s %6s %8s %8s\n", "Referencia", "Nombre", "Precio", "Cantidad", "Valor");
            System.out.println("--------------------------------------------------------------------------------------");

            // Restablecer el valor total en cada iteracion
            int valorTotal = 0;

            // Obtener los items del pedido
            JSONArray itemsPedido = HTTP.Get_array("http://"+ addr + "/petrest/pedidos/"+ i +"/items");
            for (Object itemObj : itemsPedido) {
                JSONObject itemObject = (JSONObject) itemObj;

                // Obtener cada articulo
                int idArticulo = ((Long) itemObject.get("id_articulo")).intValue();
                JSONObject articuloObject = HTTP.Get_object("http://"+ addr + "/petrest/articulos/" + idArticulo);

                Articulo articulo = new Articulo(articuloObject);
                Item item = new Item(itemObject);

                double valor = item.cantidad * articulo.precio;

                // Imprimir los datos de cada articulo
                System.out.printf("%-10s %-50s %6.2f %8d %8.2f\n", articulo.referencia, articulo.nombre, articulo.precio, item.cantidad, valor);
                valorTotal += valor; // Acumular el valor total
            }

            // Crear nueva factura
            JSONObject facturaData = new JSONObject();
            facturaData.put("id_pedido", i);
            facturaData.put("importe", valorTotal);
            HTTP.Post("http://" + addr + "/petrest/facturas", facturaData.toString());

            // GET request para obtener el id de la nueva factura para completarla
            JSONArray idNuevaFactura = HTTP.Get_array("http://" + addr + "/petrest/facturas");
            Long nuevaFacturaIdLong = (Long) idNuevaFactura.get(0); // Obtener el id de la nueva factura (debe ser long)
            int nuevaFacturaId = nuevaFacturaIdLong.intValue();

            // Completar la factura
            JSONObject facturaModificacion = new JSONObject();
            facturaModificacion.put("descuento", cliente.descuento);
            facturaModificacion.put("base", valorTotal - cliente.descuento);
            facturaModificacion.put("iva", (valorTotal - cliente.descuento) * 0.21);
            facturaModificacion.put("total", valorTotal + (valorTotal - cliente.descuento) * 0.21);
            HTTP.Put("http://" + addr + "/petrest/facturas/" + nuevaFacturaId, facturaModificacion.toString());

            JSONObject facturaObject = HTTP.Get_object("http://" + addr + "/petrest/facturas/" + nuevaFacturaId);
            Factura factura = new Factura(facturaObject);
            
            // Imprimir importe final
            System.out.println("                                                               -----------------------");
            System.out.printf("%75s %10.2f\n","Importe", factura.importe);
            System.out.printf("%75s %10.2f\n","Descuento", factura.descuento);
            System.out.printf("%75s %10.2f\n","Base", factura.base);
            System.out.printf("%75s %10.2f\n","IVA", factura.iva);
            System.out.println("                                                               -----------------------");
            System.out.printf("%75s %10.2f\n","TOTAL", factura.total);
        }
        // Fin del loop de facturacion
    }

    private static String formatCliente(JSONObject cliente) {
        int id = ((Long) cliente.get("id")).intValue();
        String cif = (String) cliente.get("cif");
        String nombre = (String) cliente.get("nombre");
        String direccion = (String) cliente.get("direccion");
        double descuento = ((Number) cliente.get("descuento")).doubleValue();

        return String.format("{%d,'%s','%s','%s',%.6f}", id, cif, nombre, direccion, descuento);
    }

    private static String formatArticulo(JSONObject articulo) {
        int id = ((Long) articulo.get("id")).intValue();
        String referencia = (String) articulo.get("referencia");
        String nombre = (String) articulo.get("nombre");
        double precio = ((Number) articulo.get("precio")).doubleValue();

        return String.format("{%d,'%s','%s',%.2f}", id, referencia, nombre, precio);
    }

    private static String formatPedido(JSONObject pedido) {
        int id = ((Long) pedido.get("id")).intValue();
        int id_cliente = ((Long) pedido.get("id_cliente")).intValue();
        String fecha = (String) pedido.get("fecha");

        // Convertir fecha de "AAAA-MM-DD" a "DD-MM-AAAA"
        String fechaFormateada = formatFecha(fecha);

        return String.format("{%d,'%d','%s'}", id, id_cliente, fechaFormateada);
    }

    private static String formatItem(JSONObject item) {
        int id = ((Long) item.get("id")).intValue();
        int id_articulo = ((Long) item.get("id_articulo")).intValue();
        int cantidad = ((Long) item.get("cantidad")).intValue();

        return String.format("{%d, %d, %d %d}", id, id_articulo, id_articulo, cantidad);
    }

    private static String formatFecha(String fecha) {
        String[] partes = fecha.split("-");
        String fechaFormateada = String.format("%s-%s-%s", partes[2], partes[1], partes[0]);
        return fechaFormateada;
    }

    private static String formatFactura(JSONObject factura) {
        int id = ((Long) factura.get("id")).intValue();
        int id_pedido = ((Long) factura.get("id_pedido")).intValue();
        double importe = ((Number) factura.get("importe")).doubleValue();
        double descuento = ((Number) factura.get("descuento")).doubleValue();
        double base = ((Number) factura.get("base")).doubleValue();
        double iva = ((Number) factura.get("iva")).doubleValue();
        double total = ((Number) factura.get("total")).doubleValue();

        return String.format("id=%d\nid_pedido=%d\nimporte=%.2f\ndescuento=%.2f\nbase=%.2f\niva=%.2f\ntotal=%.2f",
                             id, id_pedido, importe, descuento, base, iva, total);
    }
}
