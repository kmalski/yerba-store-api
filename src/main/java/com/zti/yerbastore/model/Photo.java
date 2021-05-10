package com.zti.yerbastore.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "photos")
public class Photo {
    @Id
    private String id;

    private Binary image;

    public Photo(Binary image) {
        this.image = image;
    }

}
