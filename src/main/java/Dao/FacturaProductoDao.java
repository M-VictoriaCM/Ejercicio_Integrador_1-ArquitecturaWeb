package Dao;

import Modelo.FacturaProducto;

public interface FacturaProductoDao {
    public void crear_tabla();

    public void insertar(FacturaProducto facturaProducto);
}
