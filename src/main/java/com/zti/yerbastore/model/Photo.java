package com.zti.yerbastore.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder
@Document(collection = "images")
public class Photo {
    @Id
    private String id;

    private String title;

    private Binary image;

}
