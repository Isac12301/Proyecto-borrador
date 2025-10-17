package com.netshop.infraestructura.adapter.in.web;

import com.netshop.aplicacion.dto.CreateCategoriaRequest;
import com.netshop.aplicacion.dto.CategoriaResponse;
import com.netshop.aplicacion.port.in.CategoriaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;

    @PostMapping
    public CategoriaResponse crear(@RequestBody CreateCategoriaRequest request) {
        return categoriaUseCase.crearCategoria(request);
    }

    @GetMapping
    public List<CategoriaResponse> listar() {
        return categoriaUseCase.listarCategorias();
    }
}
