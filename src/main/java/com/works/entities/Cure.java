package com.works.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@ApiModel(value = "Cure (Tedavi)", description = "Tedavi için kullanılır.")
@Entity
@Getter
@Setter@Table(name = "cure")
public class Cure {
    @ApiModelProperty(value = "Id")
    @Id
    @Column(name = "curid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer curid;

    @ApiModelProperty(value = "Tedavi Başlık")
    private String curtitle;
    @ApiModelProperty(value = "Açıklama")
    private String curcomment;
    @ApiModelProperty(value = "Borç")
    private Integer curdebt;

    @ApiModelProperty(value = "Hayvan")
    @OneToOne
    private  Pet pet;

    @ApiModelProperty(value = "Aşı (Varsa)")
    @ManyToOne
    @JoinColumn(name = "curvac")
    private Vaccine curvac;

    @ApiModelProperty(value = "Ürün (Varsa)")
    @ManyToOne
    @JoinColumn(name = "curpro")
    private Product curpro;

    @ApiModelProperty(value = "Hizmet")
    @ManyToOne
    @JoinColumn(name = "curla")
    private Labor curla;


}
