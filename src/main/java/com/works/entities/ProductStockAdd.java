package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "ProductStockAdd (Ürün Stok)", description = "Ürün stok işlemleri için ara tablo olarak kullanılır.")
@Getter
@Setter
public class ProductStockAdd {

    @ApiModelProperty(value = "Ürün Id")
    private Integer pr_id;
    @ApiModelProperty(value = "Add & Update Seçimi")
    private Integer pr_select;
    @ApiModelProperty(value = "Miktar")
    private Integer pr_amount;

}
