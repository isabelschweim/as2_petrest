import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Facturar {

    public static void main(String[] args) {

        //Luis
        // String addr = "10.202.20.125";
        String addr = "127.0.0.1";

        // Inicializar credenciales para autenticación básica
        // HTTP.Init("usuario", "clave");
        HTTP.Init("Pareja1", "rEf@ZO{TU");

        // // a. Imprimir la lista de ids de clientes
        // System.out.println("Ids de clientes");
        // JSONArray clientesIds = HTTP.Get_array("http://"+ addr + "/petrest/clientes");
        // System.out.println(clientesIds);

        // // b. Imprimir los clientes con id entre 1 y 5 individualmente
        // System.out.println("\nClientes del 1 al 5");
        // for (int i = 1; i <= 5; i++) {
        //     JSONObject cliente = HTTP.Get_object("http://"+ addr + "/petrest/clientes/" + i);
        //     System.out.println("cliente: " + formatCliente(cliente));
        // }

        // // c. Imprimir la lista de los ids de los artículos
        // System.out.println("\nIds de artículos");
        // JSONArray articulosIds = HTTP.Get_array("http://"+ addr + "/petrest/articulos");
        // System.out.println(articulosIds);

        // // d. Imprimir los artículos del 1 al 5 individualmente
        // System.out.println("\nArtículos del 1 al 5");
        // for (int i = 1; i <= 5; i++) {
        //     JSONObject articulo = HTTP.Get_object("http://"+ addr + "/petrest/articulos/" + i);
        //     System.out.println("artículo: " + formatArticulo(articulo));
        // }

        // System.out.println("\nIds de pedidos");
        JSONArray pedidosIds = HTTP.Get_array("http://"+ addr + "/petrest/pedidos");
        // System.out.println(pedidosIds);
        int numPedidos = pedidosIds.size();
        // System.err.println("Hay que imprimir: " + numPedidos + " pedidos");

        // f. Imprimir los pedidos con id entre 1 y 5 individualmente
        // System.out.println("\nPedidos del 1 al 5");

        // Restablecer las facturas
        HTTP.Delete("http://" + addr + "/petrest/facturas");

        // Imprimir por pantalla todos los pedidos
        for (int i = 1; i <= numPedidos; i++) {
            // Imprimir factura
            System.out.println("######################################################################################");
            System.out.println("FACTURA");
            
            JSONObject pedido = HTTP.Get_object("http://"+ addr + "/petrest/pedidos/" + i);
            // System.out.println("pedido: " + formatPedido(pedido));
            
            // Get the fecha (date) from the pedido object
            Object fechaObject = pedido.get("fecha");
            String fechaSinFormato = (fechaObject != null) ? fechaObject.toString() : "";
            String fecha = formatFecha(fechaSinFormato);
            System.out.printf("%-15s %s\n","Fecha:", fecha);
            System.out.println();

            /*
                curl http://127.0.0.1/petrest/clientes/1
                {
                    "id": 1,
                    "nombre": "Barjam S.A.",
                    "cif": "A29808525",
                    "direccion": "Calle del Amazonas 26, 29173 Málaga",
                    "descuento": 13.0
                }
             */
            JSONObject clienteObject = HTTP.Get_object("http://"+ addr + "/petrest/clientes/" + pedido.get("id_cliente"));
            Cliente cliente = new Cliente(clienteObject);
            System.out.printf("%-15s %s\n","Cliente:", cliente.nombre);
            System.out.printf("%-15s %s\n","CIF", cliente.cif);
            System.out.printf("%-15s %s\n","Dirección:", cliente.direccion);
            System.out.println();
            System.out.printf("%-10s %-50s %6s %8s %8s\n", "Referencia", "Nombre", "Precio", "Cantidad", "Valor");
            System.out.println("--------------------------------------------------------------------------------------");

            int valorTotal = 0;
            JSONArray itemsPedido = HTTP.Get_array("http://"+ addr + "/petrest/pedidos/"+ i +"/items");
            for (Object itemObj : itemsPedido) {
                JSONObject itemObject = (JSONObject) itemObj;

                int idArticulo = ((Long) itemObject.get("id_articulo")).intValue();
                JSONObject articuloObject = HTTP.Get_object("http://"+ addr + "/petrest/articulos/" + idArticulo);
                Articulo articulo = new Articulo(articuloObject);

                Item item = new Item(itemObject);
                double valor = item.cantidad * articulo.precio;

                // Printing the values with ',' instead of '.'
                // System.out.printf("%-10s %-50s %6s %8d %8s\n", articulo.referencia, articulo.nombre, String.valueOf(articulo.precio).replace('.', ','), item.cantidad, String.valueOf(valor).replace('.', ','));
                
                // Imprimir pedido
                System.out.printf("%-10s %-50s %6.2f %8d %8.2f\n", articulo.referencia, articulo.nombre, articulo.precio, item.cantidad, valor);
                valorTotal += valor;
            }
            
            // System.out.println("Salimos ------------------------------------------------------------------------------");
            // Crear Factura
            JSONObject facturaData = new JSONObject();
            /*
            {
                "id": 7791,
                "id_pedido": 1,
                "importe": 1235.22,
                "descuento": 123.52,
                "base": 1111.7,
                "iva": 233.46,
                "total": 1345.16
            }
             */
            // facturaData.put("id_factura", i);
            // i. Crear una nueva factura con id_pedido=1, importe=1235.22
            // JSONObject facturaData = new JSONObject();
            facturaData.put("id_pedido", i);
            facturaData.put("importe", valorTotal);
                //curl -X POST http://127.0.0.1/petrest/facturas -u "Pareja1:rEf@ZO{TU" -H "Content-Type:application/json" -d "{\"id_pedido\":1,\"importe\":1235.22}"
            HTTP.Post("http://" + addr + "/petrest/facturas", facturaData.toString());

            // j. Modificar la factura con descuento=123.52, base=1111.70, iva=233.46, total=1345.16
            // GET request to retrieve the newly created factura
            JSONArray idNuevaFactura = HTTP.Get_array("http://" + addr + "/petrest/facturas");
            // Print the idNuevaFactura
            // System.out.println("idNuevaFactura: " + idNuevaFactura);
            // int nuevaFacturaId = (Integer) idNuevaFactura.get(0); // It MUST be a long, since thats what we recieve
            // Exception in thread "main" java.lang.ClassCastException: class java.lang.Long cannot be cast to class java.lang.Integer (java.lang.Long and java.lang.Integer are in module java.base of loader 'bootstrap')
            // at Facturar.main(Facturar.java:94)

            // System.out.println("Preput -------------------------------------------------------------------------------");

            // facturaData.put("id_pedido", i);
            // facturaData.put("importe", valorTotal);
            // facturaData.put("descuento", cliente.descuento);
            // facturaData.put("base", valorTotal - cliente.descuento);
            // facturaData.put("iva", (valorTotal - cliente.descuento) * 0.21);
            // facturaData.put("total", valorTotal + (valorTotal - cliente.descuento) * 0.21);
            // Get the first element of the JSONArray
            Long nuevaFacturaIdLong = (Long) idNuevaFactura.get(0);
            // Convert the Long value to an int
            int nuevaFacturaId = nuevaFacturaIdLong.intValue();
            // Print the nuevaFacturaId
            // System.out.println("nuevaFacturaId: " + nuevaFacturaId);

            JSONObject facturaModificacion = new JSONObject();
            facturaModificacion.put("descuento", cliente.descuento);
            facturaModificacion.put("base", valorTotal - cliente.descuento);
            facturaModificacion.put("iva", (valorTotal - cliente.descuento) * 0.21);
            facturaModificacion.put("total", valorTotal + (valorTotal - cliente.descuento) * 0.21);
            HTTP.Put("http://" + addr + "/petrest/facturas/" + nuevaFacturaId, facturaModificacion.toString());
            // System.out.println("Completar factura");
            // System.out.println("Hecho");
            
            // System.out.println(facturaData.toString());
            // System.out.println("Preput -------------------------------------------------------------------------------");
            // HTTP.Post("http://" + addr + "/petrest/facturas/" + i, facturaData.toString());
            // System.out.println("Postput ------------------------------------------------------------------------------");

            // Imprimir importe
            // JSONObject facturaObject = HTTP.Get_object("http://" + addr + "/petrest/facturas/" + i);
            JSONObject facturaObject = HTTP.Get_object("http://" + addr + "/petrest/facturas/" + nuevaFacturaId);
            // System.out.println("Post Get Object ----------------------------------------------------------------------");
            // System.out.println(formatFactura(facturaObject));
            
            Factura factura = new Factura(facturaObject);
            // System.out.println("Post Factura -------------------------------------------------------------------------");
            
            System.out.println("                                                               -----------------------");
            System.out.printf("%75s %10.2f\n","Importe", factura.importe);
            System.out.printf("%75s %10.2f\n","Descuento", factura.descuento);
            System.out.printf("%75s %10.2f\n","Base", factura.base);
            System.out.printf("%75s %10.2f\n","IVA", factura.iva);
            // System.out.printf("%75s %s\n","Importe", String.valueOf(factura.importe).replace('.', ','));
            // System.out.printf("%75s %s\n","Descuento", String.valueOf(factura.descuento).replace('.', ','));
            // System.out.printf("%75s %s\n","Base", String.valueOf(factura.base).replace('.', ','));
            // System.out.printf("%75s %s\n","IVA", String.valueOf(factura.iva).replace('.', ','));
            System.out.println("                                                               -----------------------");
            System.out.printf("%75s %10.2f\n","TOTAL", factura.total);
            // System.out.printf("%75s %s\n","TOTAL", String.valueOf(factura.total).replace('.', ','));
        }

        // // g. Imprimir los ítems del pedido 1
        // System.out.println("\nItems del pedido 1");
        // JSONArray itemsPedido1 = HTTP.Get_array("http://"+ addr + "/petrest/pedidos/1/items");
        // for (Object item : itemsPedido1) {
        //     System.out.println("item: " + formatItem((JSONObject) item));
        //     // curl http://127.0.0.1/petrest/pedidos/1/items
        //     // [
        //     //     {
        //     //         "id": 84,
        //     //         "id_pedido": 1,
        //     //         "id_articulo": 47,
        //     //         "cantidad": 1
        //     //     },
        //     //     {
        //     //         "id": 85,
        //     //         "id_pedido": 1,
        //     //         "id_articulo": 38,
        //     //         "cantidad": 2
        //     //     }
        //     // ]
        // }

        // // i. Crear una nueva factura con id_pedido=1, importe=1235.22
        // JSONObject facturaData = new JSONObject();
        // facturaData.put("id_pedido", 1);
        // facturaData.put("importe", 1235.22);
        //     //curl -X POST http://127.0.0.1/petrest/facturas -u "Pareja1:rEf@ZO{TU" -H "Content-Type:application/json" -d "{\"id_pedido\":1,\"importe\":1235.22}"
        // HTTP.Post("http://" + addr + "/petrest/facturas", facturaData.toString());

        // // j. Modificar la factura con descuento=123.52, base=1111.70, iva=233.46, total=1345.16
        // // GET request to retrieve the newly created factura
        // JSONArray idNuevaFactura = HTTP.Get_array("http://" + addr + "/petrest/facturas");

        // // Get the first element of the JSONArray
        // Long nuevaFacturaIdLong = (Long) idNuevaFactura.get(0);
        // // Convert the Long value to an int
        // int nuevaFacturaId = nuevaFacturaIdLong.intValue();
        // JSONObject facturaModificacion = new JSONObject();
        // facturaModificacion.put("descuento", 123.52);
        // facturaModificacion.put("base", 1111.70);
        // facturaModificacion.put("iva", 233.46);
        // facturaModificacion.put("total", 1345.16);
        // HTTP.Put("http://"+ addr + "/petrest/facturas/" + nuevaFacturaId, facturaModificacion.toString());
        // System.out.println("Completar factura");
        // System.out.println("Hecho");

        // // k. Imprimir la factura creada
        // System.out.println("\nFactura " + nuevaFacturaId);
        // JSONObject factura = HTTP.Get_object("http://"+ addr + "/petrest/facturas/" + nuevaFacturaId);
        // System.out.println(formatFactura(factura));
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
