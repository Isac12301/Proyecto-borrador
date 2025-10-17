package com.netshop.infraestructura.adapter.out.persistence.repository;

import com.netshop.infraestructura.adapter.out.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
