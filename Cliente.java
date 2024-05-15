import org.json.simple.JSONObject;

public class Cliente {
    public int id;
    public String cif;
    public String nombre;
    public String direccion;
    public double descuento;

    public Cliente(JSONObject clienteData) {
        this.id = ((Long) clienteData.get("id")).intValue();
        this.cif = (String) clienteData.get("cif");
        this.nombre = (String) clienteData.get("nombre");
        this.direccion = (String) clienteData.get("direccion");
        this.descuento = ((Number) clienteData.get("descuento")).doubleValue();
    }

    @Override
    public String toString() {
        return String.format("{%d,'%s','%s','%s',%.6f}", id, cif, nombre, direccion, descuento);
    }
}
