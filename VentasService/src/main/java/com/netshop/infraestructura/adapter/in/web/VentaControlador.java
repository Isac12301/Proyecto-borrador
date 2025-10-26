package com.netshop.infraestructura.adapter.in.web;

import com.netshop.application.port.in.CasoUsoVenta;
import com.netshop.dominio.modelo.Venta;
import com.netshop.infraestructura.adapter.out.pdf.VentaPdfExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaControlador {

    private final CasoUsoVenta casoUsoVenta;
    private final VentaPdfExporter ventaPdfExporter; // ✅ inyección correcta

    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = casoUsoVenta.crearVenta(venta);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        return casoUsoVenta.obtenerVentaPorId(id)
                .map(venta -> new ResponseEntity<>(venta, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Venta>> obtenerVentasPorUsuario(@PathVariable String idUsuario) {
        List<Venta> ventas = casoUsoVenta.obtenerVentasPorUsuario(idUsuario);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/{id}/exportar")
    public ResponseEntity<byte[]> exportarVentaAPdf(@PathVariable Long id) {
        return casoUsoVenta.obtenerVentaPorId(id)
                .map(venta -> {
                    ByteArrayInputStream pdfStream = ventaPdfExporter.exportarVenta(venta);
                    byte[] pdfBytes = pdfStream.readAllBytes();

                    return ResponseEntity.ok()
                            .header("Content-Disposition", "attachment; filename=venta_" + id + ".pdf")
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(pdfBytes);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
