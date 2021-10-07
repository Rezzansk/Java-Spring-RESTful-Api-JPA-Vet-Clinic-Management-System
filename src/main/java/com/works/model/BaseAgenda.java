package com.works.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@ApiModel(value = "Agenda (Ajanda)", description = "Ajanda işlemleri için kullanılır.")
@Data
@MappedSuperclass
public class BaseAgenda {

    @ApiModelProperty(value = "Id")
    @Id
    private String id;

    @ApiModelProperty(value = "Başlık")
    private String agtitle;
    @ApiModelProperty(value = "Açıklama")
    private String agtext;
    @ApiModelProperty(value = "Tarih")
    private String agdate;
    @ApiModelProperty(value = "Zaman")
    private String agtime;
}
