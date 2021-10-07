package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "Sale (Satış)", description = "Satış işlemleri için kullanılır.")
@Entity
@Getter
@Setter
public class Sale {

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @ApiModelProperty(value = "Statüsü")
    private boolean status;
    @ApiModelProperty(value = "Müşteri")
    private String cname;
    @ApiModelProperty(value = "Ürün")
    private String pname;
    @ApiModelProperty(value = "Fatura")
    private int faturaNo;
    @ApiModelProperty(value = "Fiyat")
    private int pprice;

    @ApiModelProperty(value = "Tarih")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date saleDate;

    @ApiModelProperty(value = "Açıklama")
    @Column(length = 250)
   private String note;

    @ApiModelProperty(value = "Toplam Fiyat")
    private int totalPrice;
    @ApiModelProperty(value = "Miktar")
    private int pamount;
    @ApiModelProperty(value = "Brüt Miktar")
    private int grossPrice;
    @ApiModelProperty(value = "Net Miktar")
    private int netPrice;
    @ApiModelProperty(value = "Ürün Türü")
    private int proType;
    @ApiModelProperty(value = "Vergi")
    private int tax_type;
    @ApiModelProperty(value = "İndirim")
    private int pdiscount;
    @ApiModelProperty(value = "Ödeme Türü")
    private int purType;

}
