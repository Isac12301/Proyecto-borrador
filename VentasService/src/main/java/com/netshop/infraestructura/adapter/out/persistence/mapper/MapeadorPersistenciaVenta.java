package com.netshop.infraestructura.adapter.out.persistence.mapper;


import com.netshop.dominio.modelo.DetalleVenta;
import com.netshop.dominio.modelo.Venta;
import com.netshop.infraestructura.adapter.out.persistence.entity.DetalleVentaEntidad;
import com.netshop.infraestructura.adapter.out.persistence.entity.VentaEntidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapeadorPersistenciaVenta {

    // Mapeo de Venta (Dominio) a VentaEntidad (Persistencia)
    @Mapping(target = "detalles", source = "detalles")
    VentaEntidad aEntidad(Venta venta);

    @Mapping(target = "venta", ignore = true) // Ignorar mapeo inverso de la venta
    DetalleVentaEntidad aEntidadDetalle(DetalleVenta detalleVenta);

    // Mapeo de VentaEntidad (Persistencia) a Venta (Dominio)
    @Mapping(target = "detalles", source = "detalles")
    Venta aDominio(VentaEntidad ventaEntidad);

    DetalleVenta aDominioDetalle(DetalleVentaEntidad detalleVentaEntidad);
}
