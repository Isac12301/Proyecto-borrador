package com.netshop.infraestructura.adapter.out.persistence.repository;

import com.netshop.dominio.modelo.Carrito;
import com.netshop.dominio.repository.CarritoRepositorio;
import com.netshop.infraestructura.adapter.out.persistence.entity.CarritoEntidad;
import com.netshop.infraestructura.adapter.out.persistence.mapper.MapeadorPersistenciaCarrito;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdaptadorRepositorioJpaCarrito implements CarritoRepositorio {

    private final RepositorioJpaCarrito repositorioJpa;

    @Override
    public Optional<Carrito> findByUsuarioId(Long usuarioId) {
        return repositorioJpa.findByUsuarioId(usuarioId)
                .map(MapeadorPersistenciaCarrito::aDominio);
    }

    @Override
    public Carrito save(Carrito carrito) {
        CarritoEntidad entidad = MapeadorPersistenciaCarrito.aEntidad(carrito);
        return MapeadorPersistenciaCarrito.aDominio(repositorioJpa.save(entidad));
    }

    @Override
    public void deleteByUsuarioId(Long usuarioId) {
        repositorioJpa.deleteByUsuarioId(usuarioId);
    }
}
