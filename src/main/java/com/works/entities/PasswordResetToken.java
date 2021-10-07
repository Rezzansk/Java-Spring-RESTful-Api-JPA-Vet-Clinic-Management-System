package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "PasswordResetToken (Şifre Sıfırlama Token)", description = "Şifre sıfırlama işlemleri için kullanılır.")
@Entity
@Getter
@Setter
public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24;

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "Token")
    private String token;

    @ApiModelProperty(value = "Kullanıcı")
    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id")
    private User user;

    @ApiModelProperty(value = "Tarih")
    private Date expiryDate;



}
