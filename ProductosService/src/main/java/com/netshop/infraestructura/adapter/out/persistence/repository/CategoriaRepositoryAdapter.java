package com.netshop.infraestructura.adapter.out.persistence.repository;

import com.netshop.aplicacion.port.out.CategoriaRepositoryPort;
import com.netshop.dominio.model.Categoria;
import com.netshop.infraestructura.adapter.out.persistence.entity.CategoriaEntity;
import com.netshop.infraestructura.adapter.out.persistence.repository.CategoriaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoriaRepositoryAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository categoriaJpaRepository;

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity entity = CategoriaEntity.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();

        CategoriaEntity saved = categoriaJpaRepository.save(entity);
        return new Categoria(saved.getId(), saved.getNombre(), saved.getDescripcion());
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaJpaRepository.findAll()
                .stream()
                .map(e -> new Categoria(e.getId(), e.getNombre(), e.getDescripcion()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Categoria> findByNombre(String nombre) {
        return categoriaJpaRepository.findByNombre(nombre)
                .map(e -> new Categoria(e.getId(), e.getNombre(), e.getDescripcion()));
    }
}
