package com.netshop.infraestructura.adapter.out.persistence.repository;

import com.netshop.infraestructura.adapter.out.persistence.entity.CarritoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioJpaCarrito extends JpaRepository<CarritoEntidad, Long> {
    Optional<CarritoEntidad> findByUsuarioId(Long usuarioId);
    void deleteByUsuarioId(Long usuarioId);
}
