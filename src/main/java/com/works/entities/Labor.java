package com.works.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@ApiModel(value = "Labor (Hizmet)", description = "Hizmet Eklemeleri için kullanılır.")
@Entity
@Getter
@Setter
public class Labor {
    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laid", nullable = false)
    private Integer laid;

    @ApiModelProperty(value = "Hizmet Adı")
    private String laname;
    @ApiModelProperty(value = "Ücret")
    private Integer lamoney;
}
