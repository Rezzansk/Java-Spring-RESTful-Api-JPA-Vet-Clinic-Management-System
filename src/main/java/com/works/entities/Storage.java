package com.works.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "Storage (Depo)", description = "Depo işlemleri için kullanılır.")
@Entity
@Getter
@Setter
public class Storage {
    @ApiModelProperty(value = "Depo İşlem Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stid", nullable = false)
    private Integer stid;

    @ApiModelProperty(value = "Aksiyon -> Add 1 - Update 2 - Sale 3")
    private Integer staction; // Add 1 - Update 2 - Sale 3
    @ApiModelProperty(value = "Tarih")
    private Date stdate;
    @ApiModelProperty(value = "Değişim Miktarı")
    private Integer stchangeamount;
    @ApiModelProperty(value = "Son Miktar")
    private Integer stlastamount;


    @ApiModelProperty(value = "Aşı")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stvac")
    private Vaccine stvac;

    @ApiModelProperty(value = "Ürün")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stpro")
    private Product stpro;

}
