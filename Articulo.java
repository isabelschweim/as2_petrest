import org.json.simple.JSONObject;

public class Articulo {
    public int id;
    public String referencia;
    public String nombre;
    public double precio;

    public Articulo(JSONObject articuloData) {
        this.id = ((Long) articuloData.get("id")).intValue();
        this.referencia = (String) articuloData.get("referencia");
        this.nombre = (String) articuloData.get("nombre");
        this.precio = ((Number) articuloData.get("precio")).doubleValue();
    }

    @Override
    public String toString() {
        return String.format("{%d,'%s','%s',%.2f}", id, referencia, nombre, precio);
    }
}
