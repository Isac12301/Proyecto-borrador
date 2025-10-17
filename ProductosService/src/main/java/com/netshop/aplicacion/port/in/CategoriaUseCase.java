package com.netshop.aplicacion.port.in;

import com.netshop.aplicacion.dto.CreateCategoriaRequest;
import com.netshop.aplicacion.dto.CategoriaResponse;

import java.util.List;

public interface CategoriaUseCase {
    CategoriaResponse crearCategoria(CreateCategoriaRequest request);
    List<CategoriaResponse> listarCategorias();
}
