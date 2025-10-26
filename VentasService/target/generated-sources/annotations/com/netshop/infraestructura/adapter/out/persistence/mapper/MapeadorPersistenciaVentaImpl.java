package com.netshop.infraestructura.adapter.out.persistence.mapper;

import com.netshop.dominio.modelo.DetalleVenta;
import com.netshop.dominio.modelo.Venta;
import com.netshop.infraestructura.adapter.out.persistence.entity.DetalleVentaEntidad;
import com.netshop.infraestructura.adapter.out.persistence.entity.VentaEntidad;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-25T13:50:03-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class MapeadorPersistenciaVentaImpl implements MapeadorPersistenciaVenta {

    @Override
    public VentaEntidad aEntidad(Venta venta) {
        if ( venta == null ) {
            return null;
        }

        VentaEntidad ventaEntidad = new VentaEntidad();

        ventaEntidad.setDetalles( detalleVentaListToDetalleVentaEntidadList( venta.getDetalles() ) );
        ventaEntidad.setId( venta.getId() );
        ventaEntidad.setIdUsuario( venta.getIdUsuario() );
        ventaEntidad.setFecha( venta.getFecha() );
        ventaEntidad.setTotal( venta.getTotal() );
        ventaEntidad.setEstado( venta.getEstado() );

        return ventaEntidad;
    }

    @Override
    public DetalleVentaEntidad aEntidadDetalle(DetalleVenta detalleVenta) {
        if ( detalleVenta == null ) {
            return null;
        }

        DetalleVentaEntidad detalleVentaEntidad = new DetalleVentaEntidad();

        detalleVentaEntidad.setId( detalleVenta.getId() );
        detalleVentaEntidad.setIdProducto( detalleVenta.getIdProducto() );
        detalleVentaEntidad.setCantidad( detalleVenta.getCantidad() );
        detalleVentaEntidad.setPrecioUnitario( detalleVenta.getPrecioUnitario() );
        detalleVentaEntidad.setSubtotal( detalleVenta.getSubtotal() );

        return detalleVentaEntidad;
    }

    @Override
    public Venta aDominio(VentaEntidad ventaEntidad) {
        if ( ventaEntidad == null ) {
            return null;
        }

        Venta.VentaBuilder venta = Venta.builder();

        venta.detalles( detalleVentaEntidadListToDetalleVentaList( ventaEntidad.getDetalles() ) );
        venta.id( ventaEntidad.getId() );
        venta.idUsuario( ventaEntidad.getIdUsuario() );
        venta.fecha( ventaEntidad.getFecha() );
        venta.total( ventaEntidad.getTotal() );
        venta.estado( ventaEntidad.getEstado() );

        return venta.build();
    }

    @Override
    public DetalleVenta aDominioDetalle(DetalleVentaEntidad detalleVentaEntidad) {
        if ( detalleVentaEntidad == null ) {
            return null;
        }

        DetalleVenta.DetalleVentaBuilder detalleVenta = DetalleVenta.builder();

        detalleVenta.id( detalleVentaEntidad.getId() );
        detalleVenta.idProducto( detalleVentaEntidad.getIdProducto() );
        detalleVenta.cantidad( detalleVentaEntidad.getCantidad() );
        detalleVenta.precioUnitario( detalleVentaEntidad.getPrecioUnitario() );
        detalleVenta.subtotal( detalleVentaEntidad.getSubtotal() );

        return detalleVenta.build();
    }

    protected List<DetalleVentaEntidad> detalleVentaListToDetalleVentaEntidadList(List<DetalleVenta> list) {
        if ( list == null ) {
            return null;
        }

        List<DetalleVentaEntidad> list1 = new ArrayList<DetalleVentaEntidad>( list.size() );
        for ( DetalleVenta detalleVenta : list ) {
            list1.add( aEntidadDetalle( detalleVenta ) );
        }

        return list1;
    }

    protected List<DetalleVenta> detalleVentaEntidadListToDetalleVentaList(List<DetalleVentaEntidad> list) {
        if ( list == null ) {
            return null;
        }

        List<DetalleVenta> list1 = new ArrayList<DetalleVenta>( list.size() );
        for ( DetalleVentaEntidad detalleVentaEntidad : list ) {
            list1.add( aDominioDetalle( detalleVentaEntidad ) );
        }

        return list1;
    }
}
