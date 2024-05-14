import org.json.simple.JSONObject;

public class Cliente {
    private int id;
    private String cif;
    private String nombre;
    private String direccion;
    private double descuento;

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
