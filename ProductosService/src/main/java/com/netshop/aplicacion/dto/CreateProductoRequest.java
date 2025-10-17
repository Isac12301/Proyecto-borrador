package com.netshop.aplicacion.dto;

import lombok.Data;

@Data
public class CreateProductoRequest {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private Long categoriaId;
}
