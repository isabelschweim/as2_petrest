import org.json.simple.JSONObject;

public class Item {
    public int id;
    public int idArticulo;
    public int cantidad;

    public Item(JSONObject itemData) {
        this.id = ((Long) itemData.get("id")).intValue();
        this.idArticulo = ((Long) itemData.get("id_articulo")).intValue();
        this.cantidad = ((Long) itemData.get("cantidad")).intValue();
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
