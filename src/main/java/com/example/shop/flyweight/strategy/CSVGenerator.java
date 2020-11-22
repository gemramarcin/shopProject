package com.example.shop.flyweight.strategy;


import com.example.shop.flyweight.domain.FileType;
import com.example.shop.repository.jpa.ProductRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Component
@RequiredArgsConstructor
public class CSVGenerator implements GeneratorStrategy {

    private final ProductRepository productRepository;

    @Override
    public FileType getType() {
        return FileType.CSV;
    }

    @Override
    public byte[] generateFile() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(stream);

        CSVWriter csvWriter = new CSVWriter(streamWriter);


        String[] header = {"id", "name", "quantity", "price", "description"};
        csvWriter.writeNext(header);

        productRepository.findAll()
                .forEach(product -> {
                    String[] data = {product.getId().toString(), product.getName(), product.getQuantity().toString(), product.getPrice().toString(), product.getDescription()};
                    csvWriter.writeNext(data);
                });


        try {
            streamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream.toByteArray();
    }
}
