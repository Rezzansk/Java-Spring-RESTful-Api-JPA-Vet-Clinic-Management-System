package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "Buying (Alış)", description = "Alış kısmı için kullanılır.")
@Entity
@Getter
@Setter
public class Buying {

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid", nullable = false)
    private Integer bid;

    @ApiModelProperty(value = "Tedarikçi")
    private String tname;
    @ApiModelProperty(value = "Ürün")
    private String pname;
    @ApiModelProperty(value = "Id")
    private String bnote;
    @ApiModelProperty(value = "Ürün Fiyat")
    private int proprice;
    @ApiModelProperty(value = "Ürün Miktar")
    private int proamount;
    @ApiModelProperty(value = "Ürün Birim")
    private int probirim;
    @ApiModelProperty(value = "Vergi")
    private int taxtype;
    @ApiModelProperty(value = "İndirim")
    private int pdiscount;
    @ApiModelProperty(value = "Ödeme Türü")
    private int purtype;
    @ApiModelProperty(value = "Toplam Tutar")
    private int btotalPrice;
    @ApiModelProperty(value = "Net Tutar")
    private int bnetPrice;
    @ApiModelProperty(value = "Brüt Tutar")
    private int bgrossPrice;
    @ApiModelProperty(value = "Fatura No")
    private int bfaturaNo;


    @ApiModelProperty(value = "Tarih")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyingDate;

    @ApiModelProperty(value = "Statüsü")
    private int status;
}
