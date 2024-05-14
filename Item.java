import org.json.simple.JSONObject;

public class Item {
    private int id;
    private int idArticulo;
    private int cantidad;

    public Item(JSONObject itemData) {
        this.id = ((Long) itemData.get("id")).intValue();
        this.idArticulo = ((Long) itemData.get("id_articulo")).intValue();
        this.cantidad = ((Long) itemData.get("cantidad")).intValue();
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", idArticulo=" + idArticulo +
                ", cantidad=" + cantidad +
                '}';
    }
}
