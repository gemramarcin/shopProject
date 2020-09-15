package com.example.shop.flyweight.strategy;

import com.example.shop.flyweight.domain.FileType;
import com.example.shop.repository.ProductRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
@Slf4j
@RequiredArgsConstructor
public class PDFGenerator implements GeneratorStrategy {


    private final ProductRepository productRepository;

    @Override
    public byte[] generateFile() {
        Document document = new Document(PageSize.A4.rotate());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            PdfPTable pdfPTable = new PdfPTable(5);
            pdfPTable.addCell("id");
            pdfPTable.addCell("name");
            pdfPTable.addCell("quantity");
            pdfPTable.addCell("price");
            pdfPTable.addCell("description");

            productRepository.findAll().forEach(product -> {
                pdfPTable.addCell(product.getId().toString());
                pdfPTable.addCell(product.getName());
                pdfPTable.addCell(product.getQuantity().toString());
                pdfPTable.addCell(product.getPrice().toString());
                pdfPTable.addCell(product.getDescription());
            });

            document.add(pdfPTable);
            document.close();
        } catch (DocumentException e) {
            log.error(e.getMessage(), e);
        }

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public FileType getType() {
        return FileType.PDF;
    }
}
