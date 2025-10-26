package com.netshop.infraestructura.adapter.out.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.netshop.dominio.modelo.DetalleVenta;
import com.netshop.dominio.modelo.Venta;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Component
public class VentaPdfExporter {

    public ByteArrayInputStream exportarVenta(Venta venta) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // --- Título principal ---
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Comprobante de Venta", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(Chunk.NEWLINE);

            // --- Datos de la venta ---
            Font textoFont = new Font(Font.FontFamily.HELVETICA, 12);
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            document.add(new Paragraph("ID Venta: " + venta.getId(), textoFont));
            document.add(new Paragraph("Usuario: " + venta.getIdUsuario(), textoFont));
            document.add(new Paragraph("Fecha: " + venta.getFecha().format(formatoFecha), textoFont));
            document.add(new Paragraph("Estado: " + venta.getEstado(), textoFont));

            document.add(Chunk.NEWLINE);

            // --- Tabla de detalles ---
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{2, 1, 1, 1});

            agregarCeldaEncabezado(tabla, "ID Producto");
            agregarCeldaEncabezado(tabla, "Cantidad");
            agregarCeldaEncabezado(tabla, "Precio Unitario");
            agregarCeldaEncabezado(tabla, "Subtotal");

            for (DetalleVenta d : venta.getDetalles()) {
                tabla.addCell(d.getIdProducto().toString());
                tabla.addCell(String.valueOf(d.getCantidad()));
                tabla.addCell("S/ " + d.getPrecioUnitario());
                tabla.addCell("S/ " + d.getSubtotal());
            }

            document.add(tabla);

            document.add(Chunk.NEWLINE);

            // --- Total ---
            Font totalFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph total = new Paragraph("Total: S/ " + venta.getTotal(), totalFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void agregarCeldaEncabezado(PdfPTable table, String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}