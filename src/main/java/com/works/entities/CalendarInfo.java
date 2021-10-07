package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "CalendarInfo (Takvim Bilgileri)", description = "Takvim bilgileri için kullanılır.")
@Entity
@Data
public class CalendarInfo {

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;

    @ApiModelProperty(value = "İsim")
    private String cname;
    @ApiModelProperty(value = "Renk")
    private String ccolor;
    @ApiModelProperty(value = "Arka Plan Renk")
    private String cbgColor;
    @ApiModelProperty(value = "Sürükleme Renk")
    private String cdragBgColor;
    @ApiModelProperty(value = "Sınır Renkleri")
    private String cborderColor;

}
