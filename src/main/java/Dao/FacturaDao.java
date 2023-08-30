package Dao;

import Modelo.Factura;

public interface FacturaDao {
    public void crear_tabla();

    public void insertar(Factura factura);
}
