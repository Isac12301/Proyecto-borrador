package com.netshop.dominio.repository;

import com.netshop.dominio.modelo.Carrito;
import java.util.Optional;

public interface CarritoRepositorio {

    Optional<Carrito> findByUsuarioId(Long usuarioId);

    Carrito save(Carrito carrito);

    void deleteByUsuarioId(Long usuarioId);
}
