import org.json.simple.*;

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
        HTTP.Init("usuario", "clave"); // Cambia por tus credenciales correctas

        // a. Imprimir la lista de ids de clientes
        System.out.println("Ids de clientes");
        JSONArray clientesIds = HTTP.Get_array("http://10.202.20.125/petrest/clientes");
        System.out.println(clientesIds);

        // b. Imprimir los clientes con id entre 1 y 5 individualmente
        System.out.println("\nClientes del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            JSONObject cliente = HTTP.Get_object("http://10.202.20.125/petrest/clientes/" + i);
            System.out.println("Cliente: " + cliente);
        }

        // c. Imprimir la lista de los ids de los artículos
        System.out.println("\nIds de artículos");
        JSONArray articulosIds = HTTP.Get_array("http://10.202.20.125/petrest/articulos");
        System.out.println(articulosIds);

        // d. Imprimir los artículos del 1 al 5 individualmente
        System.out.println("\nArtículos del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            JSONObject articulo = HTTP.Get_object("http://10.202.20.125/petrest/articulos/" + i);
            System.out.println("Artículo: " + articulo);
        }

        // e. Imprimir la lista de los ids de todos los pedidos
        System.out.println("\nIds de pedidos");
        JSONArray pedidosIds = HTTP.Get_array("http://10.202.20.125/petrest/pedidos");
        System.out.println(pedidosIds);

        // f. Imprimir los pedidos con id entre 1 y 5 individualmente
        System.out.println("\nPedidos del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            JSONObject pedido = HTTP.Get_object("http://10.202.20.125/petrest/pedidos/" + i);
            System.out.println("Pedido: " + pedido);
        }

        // g. Imprimir los ítems del pedido 1
        System.out.println("\nItems del pedido 1");
        JSONArray itemsPedido1 = HTTP.Get_array("http://10.202.20.125/petrest/pedidos/1/items");
        System.out.println(itemsPedido1);

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
        System.out.println("\nFactura " + nuevaFacturaId);
        JSONObject factura = HTTP.Get_object("http://10.202.20.125/petrest/facturas/" + nuevaFacturaId);
        System.out.println("Factura: " + factura);
    }
}
