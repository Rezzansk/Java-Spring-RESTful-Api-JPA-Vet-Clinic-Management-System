package com.works.elasticmodel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "labor")
@Data
public class LaborElastic {
    @Id
    private String cid = UUID.randomUUID().toString();

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String detail;
}
