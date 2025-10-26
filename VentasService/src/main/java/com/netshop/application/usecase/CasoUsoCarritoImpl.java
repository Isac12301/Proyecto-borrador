package com.netshop.application.usecase;

import com.netshop.application.port.in.CasoUsoCarrito;
import com.netshop.dominio.modelo.Carrito;
import com.netshop.dominio.modelo.DetalleCarrito;
import com.netshop.dominio.repository.CarritoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CasoUsoCarritoImpl implements CasoUsoCarrito {

    private final CarritoRepositorio carritoRepositorio;

    @Override
    public Carrito obtenerCarritoPorUsuario(Long usuarioId) {
        return carritoRepositorio.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuarioId(usuarioId);
                    nuevo.setDetalles(new ArrayList<>());
                    return carritoRepositorio.save(nuevo);
                });
    }

    @Override
    public Carrito agregarProducto(Long usuarioId, Long productoId, int cantidad) {
        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);

        DetalleCarrito existente = carrito.getDetalles().stream()
                .filter(d -> d.getProductoId().equals(productoId))
                .findFirst()
                .orElse(null);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
        } else {
            DetalleCarrito nuevo = DetalleCarrito.builder()
                    .productoId(productoId)
                    .cantidad(cantidad)
                    .subtotal(0)
                    .build();
            carrito.getDetalles().add(nuevo);
        }

        return carritoRepositorio.save(carrito);
    }

    @Override
    public Carrito eliminarProducto(Long usuarioId, Long productoId) {
        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);
        carrito.getDetalles().removeIf(d -> d.getProductoId().equals(productoId));
        return carritoRepositorio.save(carrito);
    }

    @Override
    public void vaciarCarrito(Long usuarioId) {
        carritoRepositorio.deleteByUsuarioId(usuarioId);
    }
}
