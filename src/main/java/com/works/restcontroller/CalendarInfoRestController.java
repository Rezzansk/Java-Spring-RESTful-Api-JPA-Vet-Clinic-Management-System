package com.works.restcontroller;

import com.works.dto.CalendarInfoDto;
import com.works.entities.CalendarInfo;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/calendar-info")
public class CalendarInfoRestController {

    final CalendarInfoDto calendarInfoDto;

    public CalendarInfoRestController(CalendarInfoDto calendarInfoDto) {
        this.calendarInfoDto = calendarInfoDto;
    }

    @ApiOperation(value = "Takvime Özellik Atama Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> addCalendar(@RequestBody CalendarInfo calendarInfo) {

        return  calendarInfoDto.addCalendar(calendarInfo);
    }

    @ApiOperation(value = "Takvim Özellik Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> listCalendar() {

        return calendarInfoDto.listCalendar();
    }

    @ApiOperation(value = "Takvim Özellik Profili Gösterme Servisi")
    @GetMapping("/singleCalendar/{index}")
    public Map<RestEnum, Object> singleCalendar(@PathVariable String index) {

        return calendarInfoDto.singleCalendar(index);
    }

    @ApiOperation(value = "Takvim Özellik Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> deleteCalendar(@PathVariable String index) {

        return calendarInfoDto.deleteCalendar(index);
    }

    @ApiOperation(value = "Takvim Özellik Güncelleme Servisi")
    @PostMapping("/update")
    public Map<RestEnum, Object> addAndUpdateCalendar(@RequestBody CalendarInfo calendarInfo) {

        return  calendarInfoDto.addUpdateCalendar(calendarInfo);
    }



}
