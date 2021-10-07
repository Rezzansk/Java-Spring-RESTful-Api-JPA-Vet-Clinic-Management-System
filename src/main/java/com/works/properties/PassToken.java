package com.works.properties;

import lombok.Data;

@Data
public class PassToken {

    private String email;

    private String newPass;
    private String token;

}
