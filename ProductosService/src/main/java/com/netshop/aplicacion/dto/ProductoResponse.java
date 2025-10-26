package com.netshop.aplicacion.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private String imagen;
    private String categoriaNombre;
}