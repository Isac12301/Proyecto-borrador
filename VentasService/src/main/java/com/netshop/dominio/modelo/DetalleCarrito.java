package com.netshop.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCarrito {
    private Long id;
    private Long productoId;
    private int cantidad;
    private double subtotal;
}
