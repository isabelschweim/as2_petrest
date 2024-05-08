import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Facturar {
    /*
    Crea el programa Facturar.java para probar esos métodos con invocaciones para
    descargar e imprimir:
        a.  Imprimir la lista de ids de cliente
        b.  Imprimir los clientes con id entre 1 y 5 individualmente
        c.  Imprimir la lista los id de los arơculos
        d.  Imprimir los arơculos 1 a 5 individualmente.
        e.  Imprimir la lista de los ids de todos los pedidos
        f.  Imprimir los pedidos con id entre 1 y 5 individualmente.
        g.  Imprimir los ítems del pedido 1
        h.  Resetear la base de datos.
        i.  Crear una nueva factura con los valores: id_pedido=1, importe=1235.22
        j.  Completar esa factura con descuento=123.52, base=1111.70, iva=233.46,
            total=1345.16
        k.  Imprimir la factura creada
    */

    public static void main(String[] args) {
        // Inicializar credenciales para autenticación básica
        HTTP.Init("usuario", "clave");

        // a. Imprimir la lista de ids de clientes
        System.out.println("Ids de clientes");
        JSONArray clientesIds = HTTP.Get_array("http://10.202.20.125/petrest/clientes");
        System.out.println(clientesIds);

        // b. Imprimir los clientes con id entre 1 y 5 individualmente
        System.out.println("\nClientes del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            JSONObject cliente = HTTP.Get_object("http://10.202.20.125/petrest/clientes/" + i);
            System.out.println("cliente: " + formatCliente(cliente));
        }

        // c. Imprimir la lista de los ids de los artículos
        System.out.println("\nIds de artículos");
        JSONArray articulosIds = HTTP.Get_array("http://10.202.20.125/petrest/articulos");
        System.out.println(articulosIds);

        // d. Imprimir los artículos del 1 al 5 individualmente
        System.out.println("\nArtículos del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            JSONObject articulo = HTTP.Get_object("http://10.202.20.125/petrest/articulos/" + i);
            System.out.println("artículo: " + formatArticulo(articulo));
        }

        // e. Imprimir la lista de los ids de todos los pedidos
        System.out.println("\nIds de pedidos");
        JSONArray pedidosIds = HTTP.Get_array("http://10.202.20.125/petrest/pedidos");
        System.out.println(pedidosIds);

        // f. Imprimir los pedidos con id entre 1 y 5 individualmente
        System.out.println("\nPedidos del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            JSONObject pedido = HTTP.Get_object("http://10.202.20.125/petrest/pedidos/" + i);
            System.out.println("artículo: " + formatPedido(pedido));
        }

        // g. Imprimir los ítems del pedido 1
        System.out.println("\nItems del pedido 1");
        JSONArray itemsPedido1 = HTTP.Get_array("http://10.202.20.125/petrest/pedidos/1/items");
        for (Object item : itemsPedido1) {
            System.out.println("item: " + formatItem((JSONObject) item));
        }

        // h. Restablecer las facturas
        System.out.println("\nResetear facturas");
        HTTP.Delete("http://10.202.20.125/petrest/facturas");
        System.out.println("Hecho");

        // i. Crear una nueva factura con id_pedido=1, importe=1235.22
        System.out.println("\nCrear factura");
        JSONObject facturaData = new JSONObject();
        facturaData.put("id_pedido", 1);
        facturaData.put("importe", 1235.22);
        HTTP.Post("http://10.202.20.125/petrest/facturas", facturaData.toString());

        // j. Modificar la factura con descuento=123.52, base=1111.70, iva=233.46, total=1345.16
        int nuevaFacturaId = 68; // Cambia por el id real de la nueva factura
        JSONObject facturaModificacion = new JSONObject();
        facturaModificacion.put("descuento", 123.52);
        facturaModificacion.put("base", 1111.70);
        facturaModificacion.put("iva", 233.46);
        facturaModificacion.put("total", 1345.16);
        HTTP.Put("http://10.202.20.125/petrest/facturas/" + nuevaFacturaId, facturaModificacion.toString());

        System.out.println("Completar factura");
        System.out.println("Hecho");

        // k. Imprimir la factura creada
        System.out.println("\nFactura 68");
        JSONObject factura = HTTP.Get_object("http://10.202.20.125/petrest/facturas/68");
        System.out.println(formatFactura(factura));
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
