/*
 * Arquitectura de Sistemas II - Practica 3
 * HTTP.java
 * Rodrigo De Lama - 100451775
 * Isabel Schweim - 100460211
 */

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

// JSON
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HTTP {
    // Inicializar el cliente HTTP y la cabecera de autorizacion
    static HttpClient cliente = HttpClient.newHttpClient();
    static String authorizationHeader = "";

	/* ENUNCIADO:
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

    // Inicializar con las credenciales de usuario
    public static void Init(String usr, String psw) {
        String usr_psw = usr + ":" + psw;
        authorizationHeader = "Basic " + Base64.getEncoder().encodeToString(usr_psw.getBytes(StandardCharsets.UTF_8)); // Codificar las credenciales antes de enviarlas
    }

    // GET de arrays de objetos (JSONArray)
    public static JSONArray Get_array(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return sendRequestAndGetArray(request);
    }

    // GET de objetos sueltos (JSONObject)
    public static JSONObject Get_object(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return sendRequestAndGetObject(request);
    }

    // POST
    public static void Post(String uri, String json_data) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json_data))
                .build();

        sendRequest(request);
    }

    // PUT
    public static void Put(String uri, String json_data) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json_data))
                .build();

        sendRequest(request);
    }

    // DELETE
    public static void Delete(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        sendRequest(request);
    }

    // Envio de una peticion HTTP
    private static void sendRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Status =" + response.statusCode());
                System.err.println("Response body: " + response.body());
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    // Envio de una peticion HTTP y obtencion de un objeto JSON
    private static JSONObject sendRequestAndGetObject(HttpRequest request) {
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Status =" + response.statusCode());
                System.err.println("Response body: " + response.body());
                System.exit(1);
            }

            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    // Envio de una peticion HTTP y obtencion de un array JSON
    private static JSONArray sendRequestAndGetArray(HttpRequest request) {
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Status =" + response.statusCode());
                System.err.println("Response body: " + response.body());
                System.exit(1);
            }

            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
