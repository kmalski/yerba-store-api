package com.zti.yerbastore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "yerbas")
public class Yerba {
    @Id
    private String id;

    private String name;

    private List<String> ingredients;

    private String originCountry;

    private Weight weight;

    @DBRef(lazy = true)
    private Photo photo;

    public Yerba(String name, List<String> ingredients, String originCountry, Weight weight) {
        this.name = name;
        this.ingredients = ingredients;
        this.originCountry = originCountry;
        this.weight = weight;
    }

}
