package com.example.shop.flyweight.strategy;

import com.example.shop.domain.dao.Product;
import com.example.shop.flyweight.domain.FileType;
import com.example.shop.repository.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class XLSGenerator implements GeneratorStrategy {

    private final ProductRepository productRepository;

    @Override
    public byte[] generateFile() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();


        try (Workbook workbook = WorkbookFactory.create(true)) {


            Sheet sheet = workbook.createSheet("Products");

            Row row = sheet.createRow(0);

            String[] header = {"id", "name", "quantity", "price", "description"};

            for (int i = 0; i < header.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(header[i]);
            }


            List<Product> products = productRepository.findAll();

            if (!products.isEmpty()) {
                for (int i = 0; i < products.size(); i++) {
                    row = sheet.createRow(i + 1);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(products.get(i).getId().toString());

                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(products.get(i).getName());

                    Cell cell2 = row.createCell(2);
                    cell2.setCellValue(products.get(i).getQuantity().toString());

                    Cell cell3 = row.createCell(3);
                    cell3.setCellValue(products.get(i).getPrice().toString());

                    Cell cell4 = row.createCell(4);
                    cell4.setCellValue(products.get(i).getDescription());
                }
            }

            workbook.write(stream);


            return stream.toByteArray();

        } catch (IOException e) {
           log.error(e.getMessage(), e);
        }

        return new byte[0];
    }

    @Override
    public FileType getType() {
        return FileType.XLS;
    }
}
