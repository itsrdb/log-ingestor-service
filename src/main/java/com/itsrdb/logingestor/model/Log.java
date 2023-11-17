package com.itsrdb.logingestor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Log {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}