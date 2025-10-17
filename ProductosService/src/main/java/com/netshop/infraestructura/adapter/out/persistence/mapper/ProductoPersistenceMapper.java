package com.netshop.infraestructura.adapter.out.persistence.mapper;

import com.netshop.dominio.model.Categoria;
import com.netshop.dominio.model.Producto;
import com.netshop.infraestructura.adapter.out.persistence.entity.CategoriaEntity;
import com.netshop.infraestructura.adapter.out.persistence.entity.ProductoEntity;

public class ProductoPersistenceMapper {

    public static ProductoEntity toEntity(Producto producto) {
        CategoriaEntity categoriaEntity = null;
        if (producto.getCategoria() != null) {
            categoriaEntity = CategoriaEntity.builder()
                    .id(producto.getCategoria().getId())
                    .nombre(producto.getCategoria().getNombre())
                    .build();
        }

        return ProductoEntity.builder()
                .id(producto.getId())
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoria(categoriaEntity)
                .build();
    }

    public static Producto toDomain(ProductoEntity entity) {
        Categoria categoria = null;
        if (entity.getCategoria() != null) {
            categoria = Categoria.builder()
                    .id(entity.getCategoria().getId())
                    .nombre(entity.getCategoria().getNombre())
                    .build();
        }

        return Producto.builder()
                .id(entity.getId())
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .precio(entity.getPrecio())
                .stock(entity.getStock())
                .categoria(categoria)
                .build();
    }
}
