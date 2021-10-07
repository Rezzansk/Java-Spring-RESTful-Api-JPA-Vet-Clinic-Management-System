package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@ApiModel(value = "Role (Rol)", description = "Rol işlemleri için kullanılır.")
@Entity
@Data
public class Role {

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Rol Adı")
    private String name;

    @ApiModelProperty(value = "Kullanıcılar")
    @ManyToMany(mappedBy = "roles")
    private List<com.works.entities.User> users;

}
