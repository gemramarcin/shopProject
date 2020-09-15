package com.example.shop.flyweight.strategy;

import com.example.shop.domain.dao.Product;
import com.example.shop.flyweight.domain.FileType;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class XLSGenerator implements GeneratorStrategy {

    private final ProductRepository productRepository;

    @Override
    public byte[] generateFile() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Products");

        XSSFRow row = sheet.createRow(0);

        String[] header = {"id", "name", "quantity", "price", "description"};

        for (int i = 0; i < header.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }


        List<Product> products = productRepository.findAll();

        if(!products.isEmpty()){
            for (int i = 0; i < products.size(); i++) {
                row = sheet.createRow(i + 1);
                XSSFCell cell = row.createCell(0);
                cell.setCellValue(products.get(i).getId().toString());

                XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(products.get(i).getName());

                XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(products.get(i).getQuantity().toString());

                XSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(products.get(i).getPrice().toString());

                XSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(products.get(i).getDescription());
                }
            }


        try {
            workbook.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream.toByteArray();
    }

    @Override
    public FileType getType() {
        return FileType.XLS;
    }
}
