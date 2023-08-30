package Modelo;

public class FacturaProducto {
    private Factura idFactura;
    private Producto idProducto;
    private int cantidad;

    public FacturaProducto(Factura idFactura, Producto idProducto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
    public FacturaProducto(int cantidad) {
        this.cantidad = cantidad;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
