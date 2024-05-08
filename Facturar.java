/*
 * Facturar.java
 * Isabel Schweim
 * Rodrigo De Lama
 */

import java.net.http.*;
import org.json.simple.*;
import org.json.parser.*;

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
        // Imprimir la lista de ids de cliente
        System.out.println("Ids de clientes");
        System.out.println(Cliente.getIds());

        // Imprimir los clientes con id entre 1 y 5 individualmente
        System.out.println("Clientes del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            Cliente cliente = new Cliente(i);
            System.out.println("cliente: " + cliente);
        }

        // Imprimir la lista los id de los arơculos
        System.out.println("Ids de artículos");
        System.out.println(Articulo.getIds());

        // Imprimir los arơculos 1 a 5 individualmente
        System.out.println("Artículos del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            Articulo articulo = new Articulo(i);
            System.out.println("artículo: " + articulo);
        }

        // Imprimir la lista de los ids de todos los pedidos
        System.out.println("Ids de pedidos");
        System.out.println(Pedido.getIds());

        // Imprimir los pedidos con id entre 1 y 5 individualmente
        System.out.println("Pedidos del 1 al 5");
        for (int i = 1; i <= 5; i++) {
            Pedido pedido = new Pedido(i);
            System.out.println("artículo: " + pedido);
        }

        // Imprimir los ítems del pedido 1
        System.out.println("Items del pedido 1");
        Pedido pedido = new Pedido(1);
        for (Item item : pedido.getItems()) {
            System.out.println("item: " + item);
        }

        // Resetear la base de datos
        System.out.println("Resetear facturas");
        Factura.reset();

        // Crear una nueva factura con los valores: id_pedido=1, importe=1235.22
        Factura factura = new Factura(1, 1235.22);
        System.out.println("Crear factura");
        System.out.println("Factura " + factura.getId());

        // Completar esa factura con descuento=123.52, base=1111.70, iva=233.46, total=1345.16
        factura.setDescuento
    }

    /*
    Imprimir factura		
		
		System.out.println("######################################################################################");
		System.out.println("FACTURA");
		System.out.printf("%-15s %s\n","Fecha:",fecha);
		System.out.println();
		System.out.printf("%-15s %s\n","Cliente:",cliente.nombre);
		System.out.printf("%-15s %s\n","CIF",cliente.cif);
		System.out.printf("%-15s %s\n","Dirección:",cliente.direccion);
		System.out.println();
		System.out.printf("%-10s %-50s %6s %8s %8s\n","Referencia","Nombre","Precio","Cantidad","Valor");
		System.out.println("--------------------------------------------------------------------------------------");

    Imprimir pedido
            System.out.printf(
                    "%-10s %-50s %6.2f %8d %8.2f\n"
                    ,articulo.referencia,articulo.nombre
                    ,articulo.precio,item.cantidad, valor);		

    Imprimir importe
            System.out.println("                                                               -----------------------");
            System.out.printf("%75s %10.2f\n","Importe",factura.importe);
            System.out.printf("%75s %10.2f\n","Descuento",factura.descuento);
            System.out.printf("%75s %10.2f\n","Base",factura.base);
            System.out.printf("%75s %10.2f\n","IVA",factura.iva);
            System.out.println("                                                               -----------------------");
            System.out.printf("%75s %10.2f\n","TOTAL",factura.total);
    */

    /*
    Ejemplo de ejecucion
    [Paso1]$ bash Facturar.sh

    Ids de clientes
    [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19]

    Clientes del 1 al 5
    cliente: {1,'A29808525','Barjam S.A.','Calle del Amazonas 26, 29173
    Málaga',13.000000}
    cliente: {2,'B20377774','Katai S.L.','Pza Menéndez Pelayo 12, 20509
    Guipúzcoa',12.800000}
    cliente: {3,'B28826362','Lemon S.L.','Plaza de Zapola 6, 28289
    Madrid',14.900000}
    cliente: {4,'A49227247','Europa S.A.','Calle de José Manuel Cobián 12,
    49543 Málaga',0.500000}
    cliente: {5,'B28917131','Frandel S.L.','Calle Vaciabotas 38, 28269
    Madrid',6.300000}

    Ids de artículos
    [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,
    27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,5
    0,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73
    ,74,75,76,77,78,79,80,81,82,83]

    Artículos del 1 al 5
    artículo: {1,'TA6450','ARBOL PARA GATO CON CASA 35X56 CM ',25.15}
    artículo: {2,'YH2245','ARBOL PARA GATO- CARA GATO AZUL 40X30X44 CM
    ',30.20}
    artículo: {3,'JC1268','BOLSAS RECOGE EXCREMENTOS X4 DE 15 PZAS ',1.45}
    artículo: {4,'GH1584','TRANSPORTIN MALLA GRANDE SURT ',18.75}
    artículo: {5,'JK2682','ARBOL PARA GATOS GRIS ',18.75}

    Ids de pedidos
    [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,
    27,28,29,30,31,32,33,34,35,36,37,38,39]

    Pedidos del 1 al 5
    artículo: {1,'8','06-04-2023'}
    artículo: {2,'18','09-01-2023'}
    artículo: {3,'2','14-01-2023'}
    artículo: {4,'13','19-01-2023'}
    artículo: {5,'11','13-02-2023'}

    Items del pedido 1
    item: {84, 47, 47 1}
    item: {85, 38, 38 2}

    Resetear facturas
    Hecho

    Crear factura
    Factura 68

    Completar factura
    Hecho

    Factura 68
    id=68
    id_pedido=1
    importe=1235.22
    descuento=123.52
    base=1111.70
    iva=233.46
    total=1345.16
    [Paso1]$
    */
}
