package com.netshop.aplicacion.port.out;

import com.netshop.dominio.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {
    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Optional<Categoria> findByNombre(String nombre);
}
