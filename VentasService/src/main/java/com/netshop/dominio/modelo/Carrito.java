package com.netshop.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {
    private Long id;
    private Long usuarioId;
    private List<DetalleCarrito> detalles;
}
