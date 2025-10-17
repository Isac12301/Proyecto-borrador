package com.netshop.dominio.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Producto {
    private Long id;
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private Categoria categoria;
}