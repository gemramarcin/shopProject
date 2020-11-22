package com.example.shop.domain.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "basket")
public class Basket {

    @Id
    private String id;
    private String email;
    private Set<ProductBasket> products;
}
