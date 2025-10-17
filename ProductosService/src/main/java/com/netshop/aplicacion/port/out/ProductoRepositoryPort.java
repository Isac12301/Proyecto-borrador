package com.netshop.aplicacion.port.out;

import com.netshop.dominio.model.Producto;
import java.util.List;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);
    List<Producto> findAll();
}
