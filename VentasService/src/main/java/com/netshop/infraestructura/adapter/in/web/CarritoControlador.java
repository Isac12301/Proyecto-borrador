package com.netshop.infraestructura.adapter.in.web;

import com.netshop.application.port.in.CasoUsoCarrito;
import com.netshop.dominio.modelo.Carrito;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoControlador {

    private final CasoUsoCarrito casoUsoCarrito;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(casoUsoCarrito.obtenerCarritoPorUsuario(usuarioId));
    }

    @PostMapping("/{usuarioId}/agregar/{productoId}")
    public ResponseEntity<Carrito> agregarProducto(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId,
            @RequestParam int cantidad) {
        return ResponseEntity.ok(casoUsoCarrito.agregarProducto(usuarioId, productoId, cantidad));
    }

    @DeleteMapping("/{usuarioId}/eliminar/{productoId}")
    public ResponseEntity<Carrito> eliminarProducto(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId) {
        return ResponseEntity.ok(casoUsoCarrito.eliminarProducto(usuarioId, productoId));
    }

    @DeleteMapping("/{usuarioId}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long usuarioId) {
        casoUsoCarrito.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
