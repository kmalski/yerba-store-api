package com.zti.yerbastore.model.message;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserCredentials {

    private String email;

    private String password;

}
