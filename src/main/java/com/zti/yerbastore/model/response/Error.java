package com.zti.yerbastore.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
public class Error {

    private Instant timestamp;

    private String reason;

    private String details;

}
