package com.example.shop.flyweight.strategy;

import com.example.shop.flyweight.domain.FileType;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DOCGenerator implements GeneratorStrategy {

    private final ProductRepository productRepository;

    @Override
    public byte[] generateFile() {

        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFTable table = xwpfDocument.createTable();
        XWPFTableRow row = table.getRow(0);
        row.getCell(0).setText("id");
        row.addNewTableCell().setText("name");
        row.addNewTableCell().setText("quantity");
        row.addNewTableCell().setText("price");
        row.addNewTableCell().setText("description");

        productRepository.findAll().forEach(product -> {
            XWPFTableRow dataRow = table.createRow();
            dataRow.getCell(0).setText(product.getId().toString());
            dataRow.getCell(1).setText(product.getName());
            dataRow.getCell(0).setText(product.getQuantity().toString());
            dataRow.getCell(0).setText(product.getPrice().toString());
            dataRow.getCell(0).setText(product.getDescription());

        });

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            xwpfDocument.write(byteArrayOutputStream);
        } catch (IOException e) {
           log.error(e.getMessage(), e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public FileType getType() {
        return FileType.DOC;
    }
}
