package com.netshop.infraestructura.adapter.out.persistence.mapper;

import com.netshop.dominio.modelo.Carrito;
import com.netshop.dominio.modelo.DetalleCarrito;
import com.netshop.infraestructura.adapter.out.persistence.entity.CarritoEntidad;
import com.netshop.infraestructura.adapter.out.persistence.entity.DetalleCarritoEntidad;

import java.util.List;
import java.util.stream.Collectors;

public class MapeadorPersistenciaCarrito {

    public static CarritoEntidad aEntidad(Carrito carrito) {
        CarritoEntidad entidad = new CarritoEntidad();
        entidad.setId(carrito.getId());
        entidad.setUsuarioId(carrito.getUsuarioId());

        if (carrito.getDetalles() != null) {
            List<DetalleCarritoEntidad> detalles = carrito.getDetalles().stream().map(det -> {
                DetalleCarritoEntidad e = new DetalleCarritoEntidad();
                e.setId(det.getId());
                e.setProductoId(det.getProductoId());
                e.setCantidad(det.getCantidad());
                e.setSubtotal(det.getSubtotal());
                e.setCarrito(entidad);
                return e;
            }).collect(Collectors.toList());
            entidad.setDetalles(detalles);
        }

        return entidad;
    }

    public static Carrito aDominio(CarritoEntidad entidad) {
        Carrito carrito = new Carrito();
        carrito.setId(entidad.getId());
        carrito.setUsuarioId(entidad.getUsuarioId());

        if (entidad.getDetalles() != null) {
            List<DetalleCarrito> detalles = entidad.getDetalles().stream().map(det -> {
                DetalleCarrito d = new DetalleCarrito();
                d.setId(det.getId());
                d.setProductoId(det.getProductoId());
                d.setCantidad(det.getCantidad());
                d.setSubtotal(det.getSubtotal());
                return d;
            }).collect(Collectors.toList());
            carrito.setDetalles(detalles);
        }

        return carrito;
    }
}
