package com.netshop.application.usecase;

import com.netshop.application.port.in.CasoUsoVenta;
import com.netshop.dominio.modelo.DetalleVenta;
import com.netshop.dominio.modelo.Venta;
import com.netshop.dominio.repository.VentaRepositorio;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// NOTA: Una implementación real de 'crearVenta' sería más compleja:
// 1. Recibiría un DTO con (idUsuario, List<idProducto, cantidad>)
// 2. Comunicarse con el 'Productos Service' para verificar stock y obtener precios.
// 3. Calcular el total.
// 4. Guardar la venta.
// 5. Publicar un evento en RabbitMQ (ej. "VentaCreada").
// 6. El 'Productos Service' escucharía ese evento para descontar el stock.
// (Esto se llama Patrón SAGA para mantener la consistencia entre microservicios)

// Por ahora, haremos una implementación simple:
@RequiredArgsConstructor
public class CasoUsoVentaImpl implements CasoUsoVenta {

    private final VentaRepositorio ventaRepositorio;

    @Override
    public Venta crearVenta(Venta venta) {
        // 1. Asignar fecha y estado inicial
        venta.setFecha(LocalDateTime.now());
        venta.setEstado("PENDIENTE");

        // 2. Calcular el total de la venta
        // Inicializar el total a cero
        BigDecimal totalVenta = BigDecimal.ZERO;
        if (venta.getDetalles() != null && !venta.getDetalles().isEmpty()) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                // Asegurarse de que subtotal no sea nulo antes de sumarlo
                if (detalle.getSubtotal() != null) {
                    totalVenta = totalVenta.add(detalle.getSubtotal());
                } else {
                    // Si subtotal es nulo, calcúlelo (o maneje el error)
                    BigDecimal precio = detalle.getPrecioUnitario() != null ? detalle.getPrecioUnitario() : BigDecimal.ZERO;
                    BigDecimal cantidad = BigDecimal.valueOf(detalle.getCantidad());
                    BigDecimal subtotalCalculado = precio.multiply(cantidad);
                    detalle.setSubtotal(subtotalCalculado); // Asignar el subtotal calculado al detalle
                    totalVenta = totalVenta.add(subtotalCalculado);
                }
            }
        }
        venta.setTotal(totalVenta);

        return ventaRepositorio.guardar(venta);
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepositorio.obtenerPorId(id);
    }

    @Override
    public List<Venta> obtenerVentasPorUsuario(String idUsuario) {
        return ventaRepositorio.obtenerPorIdUsuario(idUsuario);
    }
}