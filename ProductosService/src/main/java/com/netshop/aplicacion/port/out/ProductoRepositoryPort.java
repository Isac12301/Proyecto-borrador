package com.netshop.aplicacion.port.out;

import com.netshop.dominio.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);
    List<Producto> findAll();

    Optional<Object> findById(Long id);
}
