import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HTTP
{
	//Abrir la conexión
	static HttpClient cliente;
	
	/*
		Crea la clase Http para agrupar las funciones de petición HTTP
		Necesitarás métodos para hacer:
			GET de arrays de objetos (JSONArray)
			GET de objetos sueltos (JSONObject)
			POST
			PUT
			DELETE
		
		Haz que acepten directamente las uris (sin la parte invariante) y en su
		caso los datos en formato JSON. En caso de que el status de la respuesta
		sea distinto de 200 debe reportarlo y terminar la ejecución.
	*/

	/*
		GET: articulos
		Recupera la lista de ids de los artículos del catálogo
		Ej. con curl
		curl http://10.202.20.125/petrest/articulos

		GET: articulos/<id_articulo>
		Recupera el artículo del catálogo con el id dado
		Ej. con curl
		curl -L http://10.202.20.125/petrest/articulos/1

		GET: clientes
		Recupera la lista de ids de clientes
		Ej. con curl
		curl -L http://10.202.20.125/petrest/clientes

		GET: clientes/<id_cliente>
		Recupera el cliente con el id dado
		Ej. con curl
		curl http://10.202.20.125/petrest/clientes/1

		GET: pedidos <id_pedido>
		Recupera el pedido de id dado
		Ej. con curl
		curl http://10.202.20.125/petrest/pedidos/1

		GET: pedido <id_pedido> items
		Recupera la lista de todos las items del pedido
		Ej. con curl
		curl http://10.202.20.125/petrest/pedidos/1/items

		GET: items
		Recupera la lista de todos los ids de items
		Ej. con curl
		curl http://10.202.20.125/petrest/items

		GET: items <id_item>
		Recupera el item con id dado
		Ej. con curl
		curl http://10.202.20.125/petrest/items/1

		GET: facturas
		Recupera la lista de todos los ids de factura
		Necesita credenciales.
		Ej. con curl
		curl http://10.202.20.125/petrest/facturas -u "usuario:clave"

		GET: facturas <id_factura>
		Recupera la factura con el id dado
		Necesita credenciales.
		Ej. con curl
		curl http://10.202.20.125/petrest/facturas/3 -u "usuario:clave"

		POST: facturas <id_pedido> <importe>
		Crea una factura para el pedido e el importe dados
		Necesita credenciales.
		Retorna el id de la factura creada.
		Ej. con curl
		curl -X POST http://10.202.20.125/petrest/facturas -u "usuario:clave" -H "Content-Type:application/json" -d "{\”id_pedido\”:1,\”importe\”:11.33}"

		PUT: facturas/<id_factura> <descuento> <base> <iva> <total>
		Modifica la factura con el id dado
		Necesita credenciales
		Ej. con curl
		curl -X PUT http://10.202.20.125/petrest/facturas/1 -u "usuario:clave" -H "Content-Type:application/json" -d "{\”descuento\”:22.33,\”base\”:45.67,\”ova\”:2.17,\”total\”:45.67}"
		
		DELETE facturas
		Resetea todas las facturas del usuario
		Necesita credenciales
		Ej. con curl
		curl -X DELETE 10.202.20.125/petrest/facturas -u "usuario:clave"
	*/

	public static void Init(String usr, String psw)
	{
		// Establece las credenciales que serán necesarias para en algunas peticiones operaciones
		// FINISH THIS
	}

	public static JSONArray Get_array(String uri)
	{
		// Obtene el array de objetos de la uri dada
		// FINISH THIS
	}

	public static JSONObject Get_object(String uri)
	{
		// Obtiene el objeto de la uri indicada
		// FINISH THIS
	}

	public static void Post(String uri, String json_data)
	{
		// Invoca el método Post a la uri dada, con los datos dados
		// FINISH THIS
	}

	public static void Put(String uri, String json_data)
	{
		// Invoca el método Put a la uri dada con los datos dados
		// FINISH THIS
	}

	public static void Delete(String uri)
	{
		// Invoca el método DELETE en la uri dada
		// FINISH THIS
	}

	public static void main(String[] args)
	{
		cliente = HttpClient.newHttpClient();
		switch(args[0])
		{
			case "g": Get(); break;
			case "p": Post(); break;
		}
	}
	
	private static void Get()
	{
		//Enviar la petición
		String uri = "http://10.202.20.125/petrest/articulos/1";
		HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("Content-Type", "application/json")
			.GET()
            .build();
            
        //Recoger la respuesta
        HttpResponse<String> respuesta = null;
		try
		{
		    respuesta = 
		    	cliente.send(request, HttpResponse.BodyHandlers.ofString());
		} 
        catch(Exception e){e.printStackTrace(); System.exit(666);}
        
        //Procesar el estado de la respuesta
        int status = respuesta.statusCode();
		if (status != 200) 
		{
			System.err.println("Status=" +status); 
			System.exit(666);
		}
		
		//Procesar el cuerpo de la respuesta
		System.out.println (respuesta.body());
	}

	private static void Post()
	{
		String body = "{\"id_pedido\":1,\"importe\":12.22}";
		String usr_psw = "usuario:clave";
		String credenciales = Base64.getEncoder().encodeToString(usr_psw.getBytes(StandardCharsets.UTF_8));
		String uri = "http://10.202.20.125/petrest/facturas";
		
		//Enviar la petición
		HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("Authorization", "Basic " + credenciales)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
		
	    //Recoger la respuesta
        HttpResponse<String> respuesta = null;
		try
		{
		    respuesta = 
		    	cliente.send(request, HttpResponse.BodyHandlers.ofString());
		} 
        catch(Exception e){e.printStackTrace(); System.exit(666);}
        
        //Procesar el estado de la respuesta
        int status = respuesta.statusCode();
		if (status != 200) 
		{
			System.err.println("Status=" +status); 
			System.exit(666);
		}
			
		//Procesar el cuerpo de la respuesta
		System.out.println (respuesta.body());
	}
}
