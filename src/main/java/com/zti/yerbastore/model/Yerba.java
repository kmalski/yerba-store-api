package com.zti.yerbastore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "yerbas")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Yerba {
    @Id
    private String id;

    private String name;

    private List<String> ingredients;

    private String originCountry;

    private Weight weight;

    @DBRef(lazy = true)
    private Photo photo;

    private Integer views;

    @PersistenceConstructor
    public Yerba(String name, List<String> ingredients, String originCountry, Weight weight, Integer views) {
        this.name = name;
        this.ingredients = ingredients;
        this.originCountry = originCountry;
        this.weight = weight;
        this.views = views;
    }

}
