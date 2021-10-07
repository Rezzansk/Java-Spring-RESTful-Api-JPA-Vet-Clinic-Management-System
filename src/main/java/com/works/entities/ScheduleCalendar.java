package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "ScheduleCalendar (Takvim)", description = "Takvim işlemleri için kullanılır.")
@Entity
@Data
public class ScheduleCalendar {
    @ApiModelProperty(value = "Takvim Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @ApiModelProperty(value = "Id")
    private String id;
    @ApiModelProperty(value = "Başlık")
    private String title;
    @ApiModelProperty(value = "Tüm Gün Mü?")
    private Boolean isAllDay;
    @ApiModelProperty(value = "Başlama")
    private String start;
    @ApiModelProperty(value = "Bitme")
    private String end;
    @ApiModelProperty(value = "Kategori")
    private String category;
    private String dueDateClass;
    @ApiModelProperty(value = "Renk")
    private String color;
    @ApiModelProperty(value = "Arka Plan Renk")
    private String bgColor;
    @ApiModelProperty(value = "Sürükleme Rengi")
    private String dragBgColor;
    @ApiModelProperty(value = "Sınır Renkleri")
    private String borderColor;
    @ApiModelProperty(value = "Konum")
    private String location;
    private String raw;
    @ApiModelProperty(value = "Durum")
    private String state;
    @ApiModelProperty(value = "Takvim Id")
    private String calendarId;

}
