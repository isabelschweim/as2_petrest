import org.json.simple.JSONObject;

public class Pedido {
    private int id;
    private int idCliente;
    private String fecha;

    public Pedido(JSONObject pedidoData) {
        this.id = ((Long) pedidoData.get("id")).intValue();
        this.idCliente = ((Long) pedidoData.get("id_cliente")).intValue();
        this.fecha = (String) pedidoData.get("fecha");
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
