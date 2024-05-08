import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HTTP {
    static HttpClient cliente = HttpClient.newHttpClient();
    static String authorizationHeader = "";

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

    // Método para manejar peticiones generales
    private static void sendRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Status=" + response.statusCode());
                System.err.println("Response body: " + response.body());
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void Init(String usr, String psw) {
        String usr_psw = usr + ":" + psw;
        authorizationHeader = "Basic " + Base64.getEncoder().encodeToString(usr_psw.getBytes(StandardCharsets.UTF_8));
    }

    public static JSONArray Get_array(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return sendRequestAndGetArray(request);
    }

    public static JSONObject Get_object(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return sendRequestAndGetObject(request);
    }

    public static void Post(String uri, String json_data) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json_data))
                .build();

        sendRequest(request);
    }

    public static void Put(String uri, String json_data) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json_data))
                .build();

        sendRequest(request);
    }

    public static void Delete(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        sendRequest(request);
    }

    private static JSONObject sendRequestAndGetObject(HttpRequest request) {
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Status=" + response.statusCode());
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

    private static JSONArray sendRequestAndGetArray(HttpRequest request) {
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Status=" + response.statusCode());
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
