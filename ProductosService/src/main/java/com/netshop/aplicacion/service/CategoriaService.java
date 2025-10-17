package com.netshop.aplicacion.service;

import com.netshop.aplicacion.dto.CreateCategoriaRequest;
import com.netshop.aplicacion.dto.CategoriaResponse;
import com.netshop.aplicacion.port.in.CategoriaUseCase;
import com.netshop.aplicacion.port.out.CategoriaRepositoryPort;
import com.netshop.dominio.model.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService implements CategoriaUseCase {

    private final CategoriaRepositoryPort categoriaRepository;

    @Override
    public CategoriaResponse crearCategoria(CreateCategoriaRequest request) {
        categoriaRepository.findByNombre(request.getNombre())
                .ifPresent(c -> { throw new RuntimeException("La categoría ya existe"); });

        Categoria categoria = Categoria.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();

        Categoria saved = categoriaRepository.save(categoria);

        return CategoriaResponse.builder()
                .id(saved.getId())
                .nombre(saved.getNombre())
                .descripcion(saved.getDescripcion())
                .build();
    }

    @Override
    public List<CategoriaResponse> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(c -> CategoriaResponse.builder()
                        .id(c.getId())
                        .nombre(c.getNombre())
                        .descripcion(c.getDescripcion())
                        .build())
                .toList();
    }
}
