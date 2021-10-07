package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@ApiModel(value = "User (Kullanıcı)", description = "Kullanıcı işlemleri için kullanılır.")
@Entity
@Data
public class User {

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Adı")
    private String firstName;
    @ApiModelProperty(value = "Soyadı")
    private String lastName;
    @ApiModelProperty(value = "Email")
    private String email;
    @ApiModelProperty(value = "Şifre")
    private String password;
    @ApiModelProperty(value = "Aktiflik")
    private boolean enabled;
    @ApiModelProperty(value = "Token")
    private boolean tokenExpired;

    @ApiModelProperty(value = "Rol")
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
}
