package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@ApiModel(value = "Logger", description = "Log işlemleri için kullanılır.")
@Entity
@Data
public class Logger {
    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lid", nullable = false)
    private Integer lid;

    @ApiModelProperty(value = "Adı")
    private String lname;
    @ApiModelProperty(value = "Soyadı")
    private String lsurname;
    @ApiModelProperty(value = "Email")
    private String lemail;
    @ApiModelProperty(value = "Oturum Id")
    private String lsessionId;
    @ApiModelProperty(value = "Ip")
    private String lIp;
    @ApiModelProperty(value = "Rol")
    private String lroles;
    @ApiModelProperty(value = "Url")
    private String lUrl;
    @ApiModelProperty(value = "Tarih")
    private LocalDateTime lDate;
}
