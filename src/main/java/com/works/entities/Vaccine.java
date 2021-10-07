package com.works.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "Vaccine (Aşı)", description = "Aşı işlemleri için kullanılır.")
@Entity
@Data
@Table(name="vaccine")
public class Vaccine {

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacid", nullable = false)
    private Integer vacid;

    @ApiModelProperty(value = "Aşı Adı")
    @NotNull(message = "Name parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    @Length(min = 2, max = 150, message = "Name min = 2, max = 100")
    private String vacname;

    @ApiModelProperty(value = "Birim")
    private Integer vacunit;
    @ApiModelProperty(value = "Tür")
    private Integer vactype;
    @ApiModelProperty(value = "Kategori")
    private Integer vaccategory;
    @ApiModelProperty(value = "Tedarikçi")
    private Integer vacsupplier;
    @ApiModelProperty(value = "Barcode")
    private String vacbarcode;
    @ApiModelProperty(value = "Kod")
    private String vaccode;
    @ApiModelProperty(value = "Alış")
    private Integer vacbuying;
    @ApiModelProperty(value = "Satış")
    private Integer vacsales;
    @ApiModelProperty(value = "Vergi")
    private Integer vactax;
    @ApiModelProperty(value = "Vergi Dahil Mi?")
    private Integer vactaxincluded;
    @ApiModelProperty(value = "Stok")
    private Integer vacstock;
    @ApiModelProperty(value = "Aktiflik")
    private Integer vacactive;
    @ApiModelProperty(value = "Pet Türü")
    private Integer vacpettype;
    @ApiModelProperty(value = "Aşı Tekrarı")
    private Integer vacrepeat;

    @ApiModelProperty(value = "Depo İşlemleri")
    @JsonIgnore
    @OneToMany(mappedBy = "stvac", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Storage> storages;

}
