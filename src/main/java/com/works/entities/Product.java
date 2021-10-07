package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "Product (Ürün)", description = "Ürün işlemleri için kullanılır.")
@Entity
@Getter
@Setter
@Table(name="product")
public class Product {

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prid", nullable = false)
    private Integer prid;

    @ApiModelProperty(value = "Adı")
    @NotNull(message = "Name parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    @Length(min = 2, max = 150, message = "Name min = 2, max = 100")
    private String prname;

    @ApiModelProperty(value = "Birim")
    private Integer prunit;
    @ApiModelProperty(value = "Tip")
    private Integer prtype;
    @ApiModelProperty(value = "Kategori")
    private Integer prcategory;
    @ApiModelProperty(value = "Tedarikçi")
    private Integer prsupplier;
    @ApiModelProperty(value = "Barkod")
    private String prbarcode;
    @ApiModelProperty(value = "Kod")
    private String prcode;
    @ApiModelProperty(value = "Alış Fiyatı")
    private Integer prbuying;
    @ApiModelProperty(value = "Satış Fiyatı")
    private Integer prsales;
    @ApiModelProperty(value = "Vergi")
    private Integer prtax;
    @ApiModelProperty(value = "Aktiflik")
    private Integer practive;
    @ApiModelProperty(value = "Vergi Dahil Mi?")
    private Integer prtaxincluded;
    @ApiModelProperty(value = "Stok Miktarı")
    private Integer prstock;

    @ApiModelProperty(value = "Depo")
    @JsonIgnore
    @OneToMany(mappedBy = "stpro", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Storage> storages;


}
