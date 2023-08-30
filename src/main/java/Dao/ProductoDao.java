package Dao;

import Modelo.Producto;

public interface ProductoDao {
    public void crear_tabla();

    public void insertar(Producto producto);
}
