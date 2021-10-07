package com.works.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "Pet (Hayvan)", description = "Hayvan işlemleri için kullanılır.")
@Getter
@Setter
@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @ApiModelProperty(value = "Pet Adı", required = true, notes = "Name belirtilmediğinde  servis hata verir!")
    @NotNull(message = "Name parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    private String pname;

    @ApiModelProperty(value = "Pet chip numarası", required = false, notes = "Belirtilmesi tavsiye edilir")
    private Integer pchip;
    @ApiModelProperty(value = "Pet küpe numarası", required = false, notes = "Belirtilmesi tavsiye edilir")
    private String pearnumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pbirthday;
    @ApiModelProperty(value = "Pet chip numarası", required = false, notes = "Belirtilmesi tavsiye edilir")
    private Integer ptype;
    @ApiModelProperty(value = "Kısırlık Durumu")
    private Integer pspay;
    @ApiModelProperty(value = "Irkı")
    private String prace;
    @ApiModelProperty(value = "Rengi")
    private Integer pcolor;
    @ApiModelProperty(value = "Cinsiyet")
    private Integer pgender;

    @ApiModelProperty(value = "Customer (Müşteri)")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cid", nullable = false)
    private Customer cid;

}
