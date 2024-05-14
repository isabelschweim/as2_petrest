import org.json.simple.JSONArray;

public class Requests {
    
    public static int[] Lista(String uri) {
        JSONArray array = HTTP.Get_array(uri);
        int[] ids = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            ids[i] = ((Long) array.get(i)).intValue();
        }
        return ids;
    }


    // unsure about stuff below here ---------------------------------------------------------
    //Luis
    // String addr = "10.202.20.125";
    private String addr = "127.0.0.1";

    public Articulo Articulo(int id) {
        JSONObject articuloData = HTTP.Get_object("http://" + addr +"/petrest/articulos/" + id);
        return new Articulo(articuloData);
    }

    // Agrega más métodos según los necesites para otras operaciones.
// }


// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;

// public class Requests {

    // Método para obtener un array de enteros de una URI
    public static int[] Lista(String uri) {
        JSONArray array = HTTP.Get_array(uri);
        int[] ids = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            ids[i] = ((Long) array.get(i)).intValue();
        }
        return ids;
    }

    // Método para obtener un objeto de tipo Articulo a partir de su ID
    public Articulo getArticulo(int id) {
        JSONObject articuloData = HTTP.Get_object("http://" + addr + "/petrest/articulos/" + id);
        return new Articulo(articuloData);
    }

    // Método para obtener un objeto de tipo Pedido a partir de su ID
    public Pedido getPedido(int id) {
        JSONObject pedidoData = HTTP.Get_object("http://" + addr + "/petrest/pedidos/" + id);
        return new Pedido(pedidoData);
    }

    // Método para obtener un objeto de tipo Item a partir de su ID
    public Item getItem(int id) {
        JSONObject itemData = HTTP.Get_object("http://" + addr + "/petrest/items/" + id);
        return new Item(itemData);
    }

}
