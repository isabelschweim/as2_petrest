import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class luis_HTTP
	{
	//Abrir la conexión
	static HttpClient cliente;
		
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
