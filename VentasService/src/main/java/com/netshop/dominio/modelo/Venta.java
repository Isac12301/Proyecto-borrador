package com.netshop.dominio.modelo;

import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Venta {
    private Long id;
    private String idUsuario;
    private LocalDateTime fecha;
    private BigDecimal total;
    private String estado; // Ejs: PENDIENTE, PAGADO, ENVIADO, CANCELADO
    private List<DetalleVenta> detalles;
}
