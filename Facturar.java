/*
 * Arquitectura de Sistemas II - Practica 3
 * Facturar.java
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
        // HTTP.Init("Pareja1", "rEf@ZO{TU");
        Requests.Autenticar();

        // Recolectar todos los pedidos
        // JSONArray pedidosIds = HTTP.Get_array("http://"+ addr + "/petrest/pedidos");
        // JSONArray pedidosIds = Requests.getPedidosIds();
        // int numPedidos = pedidosIds.size();
        Pedido pedidos[] = Requests.getPedidos();
        int numPedidos = pedidos.length;

        // Restablecer las facturas
        // HTTP.Delete("http://" + addr + "/petrest/facturas");
        Requests.deleteFacturas();

        // Asumir que los ids son aleatorios
        // Imprimir por pantalla cada factura
        for (int i = 1; i <= numPedidos; i++) {
            System.out.println("######################################################################################");
            System.out.println("FACTURA");
            
            // Obtener el pedido
            // Seleccionar el id del pedido del array de pedidos, segun la iteracion
            // int idPedido = ((Long) pedidosIds.get(i-1)).intValue();
            int idPedido = pedidos[i-1].id;
            // JSONObject pedido = HTTP.Get_object("http://" + addr + "/petrest/pedidos/" + idPedido);
            // Pedido pedidoObj = new Pedido(pedido);
            Pedido pedido = Requests.getPedido(idPedido);

            // Obtener la fecha del pedido
            // Object fechaObject = pedido.get("fecha");
            // String fechaSinFormato = (fechaObject != null) ? fechaObject.toString() : "";
            // String fecha = formatFecha(fechaSinFormato);
            String fecha = formatFecha(pedido.fecha);
            System.out.printf("%-15s %s\n", "Fecha:", fecha);
            System.out.println();

            // Obtener los datos del cliente
            // JSONObject clienteObject = HTTP.Get_object("http://" + addr + "/petrest/clientes/" + pedidoObj.idCliente);
            // Cliente cliente = new Cliente(clienteObject);
            Cliente cliente = Requests.getCliente(pedido.idCliente);

            System.out.printf("%-15s %s\n", "Cliente:", cliente.nombre);
            System.out.printf("%-15s %s\n", "CIF", cliente.cif);
            System.out.printf("%-15s %s\n", "Dirección:", cliente.direccion);
            System.out.println();
            System.out.printf("%-10s %-50s %6s %8s %8s\n", "Referencia", "Nombre", "Precio", "Cantidad", "Valor");
            System.out.println("--------------------------------------------------------------------------------------");

            // Restablecer el valor total en cada iteracion
            double importe = 0;

            // Obtener los items del pedido
            // JSONArray itemsPedido = HTTP.Get_array("http://" + addr + "/petrest/pedidos/" + i + "/items");
            Item items[] = Requests.getItems(i);

            // Iterar sobre los items del pedido
            // for (Object itemObj : itemsPedido) {
            for (Item item : items) {
                // JSONObject itemObject = (JSONObject) itemObj;
                Item currentItem = item;

                // Obtener cada articulo
                // int idArticulo = ((Long) itemObject.get("id_articulo")).intValue();
                // JSONObject articuloObject = HTTP.Get_object("http://" + addr + "/petrest/articulos/" + idArticulo);

                // Obtener el articulo
                int idArticulo = currentItem.idArticulo;
                Articulo articulo = Requests.getArticulo(idArticulo);

                // Articulo articulo = new Articulo(articuloObject);
                // Item item = new Item(itemObject);

                double valor = articulo.precio * item.cantidad;

                // Imprimir los datos de cada articulo
                System.out.printf("%-10s %-50s %6.2f %8d %8.2f\n", articulo.referencia, articulo.nombre, articulo.precio, item.cantidad, valor);
                importe += valor; // Acumular el valor total
            }

            double descuento = cliente.descuento * importe / 100;
            double base = importe - descuento;
            double iva = base * 0.21;
            double total = base + iva;

            // Crear nueva factura
            // JSONObject facturaData = new JSONObject();
            // facturaData.put("id_pedido", idPedido);
            // facturaData.put("importe", valorTotal);
            // HTTP.Post("http://" + addr + "/petrest/facturas", facturaData.toString());
            // Requests.postFactura(facturaData.toString());

            // Crear nueva factura
            // Factura facturaPost = Requests.postFactura(idPedido, valorTotal);
            Requests.postFactura(idPedido, importe);

            // GET request para obtener el id de la nueva factura para completarla
            // JSONArray idNuevaFactura = HTTP.Get_array("http://" + addr + "/petrest/facturas");
            // Long nuevaFacturaIdLong = (Long) idNuevaFactura.get(i-1); // Obtener el id del array de la nueva factura (debe ser long)
            // int nuevaFacturaId = nuevaFacturaIdLong.intValue();

            // int nuevaFacturaId = Requests.getFacturaId(facturaData.toString(), i);
            int nuevaFacturaId = Requests.getFacturaId(i);

            // Completar la factura
            // JSONObject facturaModificacion = new JSONObject();
            // facturaModificacion.put("importe", valorTotal);
            // facturaModificacion.put("descuento", descuento);
            // facturaModificacion.put("base", base);
            // facturaModificacion.put("iva", iva);
            // facturaModificacion.put("total", total);
            // HTTP.Put("http://" + addr + "/petrest/facturas/" + nuevaFacturaId, facturaModificacion.toString());
            Requests.fillFactura(nuevaFacturaId, importe, descuento, base, iva, total);

            // JSONObject facturaObject = HTTP.Get_object("http://" + addr + "/petrest/facturas/" + nuevaFacturaId);
            // Factura factura = new Factura(facturaObject);
            Factura factura = Requests.getFactura(nuevaFacturaId);

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

    private static String formatFecha(String fecha) {
        String[] partes = fecha.split("-");
        String fechaFormateada = String.format("%s-%s-%s", partes[2], partes[1], partes[0]);
        return fechaFormateada;
    }
}
