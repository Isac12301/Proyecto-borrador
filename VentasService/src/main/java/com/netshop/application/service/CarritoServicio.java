package com.netshop.application.service;

import com.netshop.application.port.in.CasoUsoCarrito;
import com.netshop.dominio.modelo.Carrito;
import com.netshop.dominio.modelo.DetalleCarrito;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarritoServicio implements CasoUsoCarrito {

    private final Map<Long, Carrito> carritos = new HashMap<>();

    @Override
    public Carrito obtenerCarritoPorUsuario(Long usuarioId) {
        return carritos.getOrDefault(usuarioId, new Carrito(usuarioId, usuarioId, new ArrayList<>()));
    }

    @Override
    public Carrito agregarProducto(Long usuarioId, Long productoId, int cantidad) {
        Carrito carrito = carritos.getOrDefault(usuarioId, new Carrito());
        carrito.setUsuarioId(usuarioId);

        if (carrito.getDetalles() == null)
            carrito.setDetalles(new ArrayList<>());

        Optional<DetalleCarrito> existente = carrito.getDetalles().stream()
                .filter(d -> d.getProductoId().equals(productoId))
                .findFirst();

        if (existente.isPresent()) {
            existente.get().setCantidad(existente.get().getCantidad() + cantidad);
        } else {
            DetalleCarrito nuevo = new DetalleCarrito();
            nuevo.setProductoId(productoId);
            nuevo.setCantidad(cantidad);
            nuevo.setSubtotal(0); // calcular según el precio real
            carrito.getDetalles().add(nuevo);
        }

        carritos.put(usuarioId, carrito);
        return carrito;
    }

    @Override
    public Carrito eliminarProducto(Long usuarioId, Long productoId) {
        Carrito carrito = carritos.get(usuarioId);
        if (carrito != null && carrito.getDetalles() != null) {
            carrito.getDetalles().removeIf(d -> d.getProductoId().equals(productoId));
        }
        return carrito;
    }

    @Override
    public void vaciarCarrito(Long usuarioId) {
        carritos.remove(usuarioId);
    }
}
