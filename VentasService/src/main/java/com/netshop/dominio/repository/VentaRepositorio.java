package com.netshop.dominio.repository;

import com.netshop.dominio.modelo.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaRepositorio {
    Venta guardar(Venta venta);
    Optional<Venta> obtenerPorId(Long id);
    List<Venta> obtenerPorIdUsuario(String idUsuario);
}
