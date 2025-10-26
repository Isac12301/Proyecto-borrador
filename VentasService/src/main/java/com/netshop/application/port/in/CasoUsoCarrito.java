package com.netshop.application.port.in;

import com.netshop.dominio.modelo.Carrito;

public interface CasoUsoCarrito {

    Carrito obtenerCarritoPorUsuario(Long usuarioId);

    Carrito agregarProducto(Long usuarioId, Long productoId, int cantidad);

    Carrito eliminarProducto(Long usuarioId, Long productoId);

    void vaciarCarrito(Long usuarioId);
}
