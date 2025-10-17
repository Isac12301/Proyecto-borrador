package com.netshop.dominio.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
    private Long id;
    private String nombre;
    private String descripcion;
}
