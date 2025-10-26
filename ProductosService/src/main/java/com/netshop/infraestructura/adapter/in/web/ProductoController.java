package com.netshop.infraestructura.adapter.in.web;

import com.netshop.aplicacion.dto.CreateProductoRequest;
import com.netshop.aplicacion.dto.ProductoResponse;
import com.netshop.aplicacion.port.in.ProductoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoUseCase productoUseCase;

    @PostMapping
    public ProductoResponse crear(@RequestBody CreateProductoRequest request) {
        return productoUseCase.crearProducto(request);
    }

    @GetMapping
    public List<ProductoResponse> listar() {
        return productoUseCase.listarProductos();
    }

    @GetMapping("/{id}")
    public ProductoResponse obtenerPorId(@PathVariable Long id) {
        return productoUseCase.obtenerProductoPorId(id);
    }
}

