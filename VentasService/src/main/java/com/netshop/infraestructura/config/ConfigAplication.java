package com.netshop.infraestructura.config;

import com.netshop.application.port.in.CasoUsoCarrito;
import com.netshop.application.port.in.CasoUsoVenta;
import com.netshop.application.usecase.CasoUsoCarritoImpl;
import com.netshop.application.usecase.CasoUsoVentaImpl;
import com.netshop.dominio.repository.CarritoRepositorio;
import com.netshop.dominio.repository.VentaRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAplication {

    @Bean
    public CasoUsoVenta casoUsoVenta(VentaRepositorio ventaRepositorio) {
        return new CasoUsoVentaImpl(ventaRepositorio);
    }

    @Bean
    public CasoUsoCarrito casoUsoCarrito(CarritoRepositorio carritoRepositorio) {
        return new CasoUsoCarritoImpl(carritoRepositorio);
    }
}
