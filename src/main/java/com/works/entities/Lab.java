package com.works.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "Lab (laboratuvar)", description = "Laboratuvar için kullanılır.")
@Entity
@Getter
@Setter
public class Lab {

    @ApiModelProperty(value = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lid", nullable = false)
    private Integer lid;

    @ApiModelProperty(value = "Lab Başlık")
    private String ltitle;
    @ApiModelProperty(value = "Açıklama")
    private String lcomment;

    @ApiModelProperty(value = "Pet")
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pid")
    private Pet pid;

    @ApiModelProperty(value = "Tarih")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ldate;

    @ApiModelProperty(value = "Dosya")
    private String fileName;

}
