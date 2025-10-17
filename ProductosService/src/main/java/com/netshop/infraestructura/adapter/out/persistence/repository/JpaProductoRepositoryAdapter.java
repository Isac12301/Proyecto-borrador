package com.netshop.infraestructura.adapter.out.persistence.repository;

import com.netshop.aplicacion.port.out.ProductoRepositoryPort;
import com.netshop.dominio.model.Producto;
import com.netshop.infraestructura.adapter.out.persistence.entity.ProductoEntity;
import com.netshop.infraestructura.adapter.out.persistence.mapper.ProductoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final SpringDataProductoRepository repository;

    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = ProductoPersistenceMapper.toEntity(producto);
        ProductoEntity saved = repository.save(entity);
        return ProductoPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Producto> findAll() {
        return repository.findAll().stream()
                .map(ProductoPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
