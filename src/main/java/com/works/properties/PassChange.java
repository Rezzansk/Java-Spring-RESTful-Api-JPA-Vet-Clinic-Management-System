package com.works.properties;

import lombok.Data;

@Data
public class PassChange {

    private String email;
    private String oldPass;
    private String newPass;
}
