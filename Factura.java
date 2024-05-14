import org.json.simple.JSONObject;

public class Factura {
    private int id;
    private int idPedido;
    private double importe;
    private double descuento;
    private double base;
    private double iva;
    private double total;

    public Factura(JSONObject facturaData) {
        this.id = ((Long) facturaData.get("id_factura")).intValue();
        this.idPedido = ((Long) facturaData.get("id_pedido")).intValue();
        this.importe = (double) facturaData.get("importe");
        this.descuento = (double) facturaData.get("descuento");
        this.base = (double) facturaData.get("base");
        this.iva = (double) facturaData.get("iva");
        this.total = (double) facturaData.get("total");
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public double getImporte() {
        return importe;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getBase() {
        return base;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", idPedido=" + idPedido +
                ", importe=" + importe +
                ", descuento=" + descuento +
                ", base=" + base +
                ", iva=" + iva +
                ", total=" + total +
                '}';
    }
}
