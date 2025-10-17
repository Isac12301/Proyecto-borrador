package com.netshop.aplicacion.service;

import com.netshop.aplicacion.dto.CreateProductoRequest;
import com.netshop.aplicacion.dto.ProductoResponse;
import com.netshop.aplicacion.port.in.ProductoUseCase;
import com.netshop.aplicacion.port.out.ProductoRepositoryPort;
import com.netshop.aplicacion.port.out.CategoriaRepositoryPort;
import com.netshop.dominio.model.Producto;
import com.netshop.dominio.model.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements ProductoUseCase {

    private final ProductoRepositoryPort productoRepository;
    private final CategoriaRepositoryPort categoriaRepository;

    @Override
    public ProductoResponse crearProducto(CreateProductoRequest request) {
        // Buscar categoría
        Categoria categoria = categoriaRepository.findAll().stream()
                .filter(c -> c.getId().equals(request.getCategoriaId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = Producto.builder()
                .codigo(request.getCodigo())
                .nombre(request.getNombre())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .categoria(categoria)
                .build();

        Producto saved = productoRepository.save(producto);

        return ProductoResponse.builder()
                .id(saved.getId())
                .codigo(saved.getCodigo())
                .nombre(saved.getNombre())
                .precio(saved.getPrecio())
                .stock(saved.getStock())
                .categoriaNombre(saved.getCategoria().getNombre())
                .build();
    }

    @Override
    public List<ProductoResponse> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(p -> ProductoResponse.builder()
                        .id(p.getId())
                        .codigo(p.getCodigo())
                        .nombre(p.getNombre())
                        .precio(p.getPrecio())
                        .stock(p.getStock())
                        .categoriaNombre(p.getCategoria().getNombre())
                        .build())
                .toList();
    }
}
