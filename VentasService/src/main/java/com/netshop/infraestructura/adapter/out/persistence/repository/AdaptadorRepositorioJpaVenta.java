package com.netshop.infraestructura.adapter.out.persistence.repository;

import com.netshop.dominio.modelo.Venta;
import com.netshop.dominio.repository.VentaRepositorio;
import com.netshop.infraestructura.adapter.out.persistence.entity.VentaEntidad;
import com.netshop.infraestructura.adapter.out.persistence.mapper.MapeadorPersistenciaVenta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdaptadorRepositorioJpaVenta implements VentaRepositorio {

    private final RepositorioJpaVenta repositorioJpa;
    private final MapeadorPersistenciaVenta mapeador;

    @Override
    public Venta guardar(Venta venta) {
        VentaEntidad entidad = mapeador.aEntidad(venta);
        // Asegurar la relación bidireccional
        if (entidad.getDetalles() != null) {
            entidad.getDetalles().forEach(detalle -> detalle.setVenta(entidad));
        }
        VentaEntidad entidadGuardada = repositorioJpa.save(entidad);
        return mapeador.aDominio(entidadGuardada);
    }

    @Override
    public Optional<Venta> obtenerPorId(Long id) {
        return repositorioJpa.findById(id).map(mapeador::aDominio);
    }

    @Override
    public List<Venta> obtenerPorIdUsuario(String idUsuario) {
        return repositorioJpa.findByIdUsuario(idUsuario).stream()
                .map(mapeador::aDominio)
                .collect(Collectors.toList());
    }
}