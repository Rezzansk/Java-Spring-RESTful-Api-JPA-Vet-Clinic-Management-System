package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "MoneyCase (Kasa)", description = "Kasa işlemleri için kullanılır.")
@Entity
@Getter
@Setter
public class MoneyCase {
    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;

    @ApiModelProperty(value = "Kasa Toplam")
    private Integer cmoney;
    @ApiModelProperty(value = "1 buy ekleme | 2 sell çıkarma")
    private Integer cbuysell; // 1 buy ekleme | 2 sell çıkarma

    @ApiModelProperty(value = "Tarih")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cdatenow;

    @ApiModelProperty(value = "İşlem Yapılan (Müşteri / Tedarikçi)")
    private String cpeople;
    @ApiModelProperty(value = "Yapan")
    private String cname;
    @ApiModelProperty(value = "Açıklama")
    private String ccomment;
}
