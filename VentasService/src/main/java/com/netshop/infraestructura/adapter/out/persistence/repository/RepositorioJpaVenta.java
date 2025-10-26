package com.netshop.infraestructura.adapter.out.persistence.repository;


import com.netshop.infraestructura.adapter.out.persistence.entity.VentaEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepositorioJpaVenta extends JpaRepository<VentaEntidad, Long> {
    List<VentaEntidad> findByIdUsuario(String idUsuario);
}
