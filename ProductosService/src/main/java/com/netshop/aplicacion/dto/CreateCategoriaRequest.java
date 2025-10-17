package com.netshop.aplicacion.dto;

import lombok.Data;

@Data
public class CreateCategoriaRequest {
    private String nombre;
    private String descripcion;
}
