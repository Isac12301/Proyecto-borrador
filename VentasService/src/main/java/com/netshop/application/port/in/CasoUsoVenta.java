package com.netshop.application.port.in;

import com.netshop.dominio.modelo.Venta;

import java.util.List;
import java.util.Optional;

public interface CasoUsoVenta {
    Venta crearVenta(Venta venta);
    Optional<Venta> obtenerVentaPorId(Long id);
    List<Venta> obtenerVentasPorUsuario(String idUsuario);
    // Aquí irían otros casos de uso, como:
    // Venta actualizarEstadoVenta(Long id, String nuevoEstado);
}
