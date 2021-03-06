package com.zti.yerbastore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "photos")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photo {
    @Id
    private String id;

    private Binary image;

    public Photo(Binary image) {
        this.image = image;
    }

}
