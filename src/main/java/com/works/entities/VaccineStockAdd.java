package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "VaccineStockAdd (Aşı Stok)", description = "Aşı stok işlemleri için ara tablo olarak kullanılır.")
@Getter
@Setter
public class VaccineStockAdd {

    @ApiModelProperty(value = "Aşı Id")
    private Integer vac_id;
    @ApiModelProperty(value = "Add & Update Seçimi")
    private Integer vac_select;
    @ApiModelProperty(value = "Miktarı")
    private Integer vac_amount;

}
