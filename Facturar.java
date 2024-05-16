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

        // Inicializar con nuestras credenciales
        Requests.Autenticar();

        // Recolectar todos los pedidos
        Pedido pedidos[] = Requests.getPedidos();
        int numPedidos = pedidos.length; // Numero de pedidos a facturar

        // Restablecer las facturas
        Requests.deleteFacturas();

        // Imprimir por pantalla cada factura
        for (int i = 1; i <= numPedidos; i++) {
            System.out.println("######################################################################################");
            System.out.println("FACTURA");
            
            // Asumiendo que los ids son aleatorios
            // Seleccionar el id del pedido del array de pedidos, segun la iteracion
            int idPedido = pedidos[i-1].id;

            // Obtener el pedido
            Pedido pedido = Requests.getPedido(idPedido);

            // Obtener la fecha del pedido
            String fecha = formatFecha(pedido.fecha);
            System.out.printf("%-15s %s\n", "Fecha:", fecha);
            System.out.println();

            // Obtener los datos del cliente
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
            Item items[] = Requests.getItems(i);

            // Iterar sobre los items del pedido
            for (Item item : items) {
                // Obtener el item actual
                Item currentItem = item;

                // Obtener el articulo del item
                int idArticulo = currentItem.idArticulo;
                Articulo articulo = Requests.getArticulo(idArticulo);

                // Calcular el valor del articulo segun la cantidad
                double valor = articulo.precio * item.cantidad;

                // Imprimir los datos de cada articulo
                System.out.printf("%-10s %-50s %6.2f %8d %8.2f\n", articulo.referencia, articulo.nombre, articulo.precio, item.cantidad, valor);
                importe += valor; // Acumular el valor total
            }

            // Calcular el descuento, base, iva y total
            double descuento = cliente.descuento * importe / 100;
            double base = importe - descuento;
            double iva = base * 0.21;
            double total = base + iva;

            // Crear nueva factura (POST request)
            Requests.postFactura(idPedido, importe);

            // Obtener el id de la nueva factura para completarla (GET request)
            int nuevaFacturaId = Requests.getFacturaId(i);

            // Completar la factura
            Requests.fillFactura(nuevaFacturaId, importe, descuento, base, iva, total);

            // Obtener la factura completa (GET request)
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
