package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@ApiModel(value = "Suppliers (Tedarikçiler)", description = "Tedarikçi işlemleri için kullanılır.")
@Entity
@Getter
@Setter
public class Suppliers {
    @ApiModelProperty(value = "Tedarikçi")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suid", nullable = false)
    private Integer suid;

    @ApiModelProperty(value = "Adı")
    private String suname;
    @ApiModelProperty(value = "Email")
    private String sumail;
    @ApiModelProperty(value = "Tel")
    private String sutel;
    @ApiModelProperty(value = "Aktiflik")
    private Integer suactive;
    @ApiModelProperty(value = "Borç")
    private Integer sudebt;


}
