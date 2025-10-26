package com.netshop.aplicacion.port.in;

import com.netshop.aplicacion.dto.CreateProductoRequest;
import com.netshop.aplicacion.dto.ProductoResponse;

import java.util.List;

public interface ProductoUseCase {
    ProductoResponse crearProducto(CreateProductoRequest request);
    List<ProductoResponse> listarProductos();

    ProductoResponse obtenerProductoPorId(Long id);
}
