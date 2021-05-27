package com.zti.yerbastore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "statistics")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Statistics {

    @Id
    private String id;

    private Integer websiteViews;

    public Statistics(Integer websiteViews) {
        this.websiteViews = websiteViews;
    }
}
