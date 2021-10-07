package com.works.restcontroller;

import com.works.dto.ScheduleCalanderDto;
import com.works.entities.ScheduleCalendar;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/calendar")
public class ScheduleCalendarRestController {

    final ScheduleCalanderDto caDto;

    public ScheduleCalendarRestController(ScheduleCalanderDto caDto) {
        this.caDto = caDto;
    }

    @ApiOperation(value = "Takvime Randevu Atama Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> addCalendar(@RequestBody ScheduleCalendar scheduleCalendar) {

        return  caDto.addCalendar(scheduleCalendar);
    }

    @ApiOperation(value = "Takvim İçeriği Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> listCalendar() {

        return caDto.listCalendar();
    }

    @ApiOperation(value = "Takvim İçerik Görüntüleme Servisi")
    @GetMapping("/singleCalendar/{index}")
    public Map<RestEnum, Object> singleCalendar(@PathVariable String index) {

        return caDto.singleCalendar(index);
    }

    @ApiOperation(value = "Takvim Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> deleteCalendar(@PathVariable String index) {

        return caDto.deleteCalendar(index);
    }

    @ApiOperation(value = "Takvim İçerik Güncelleme Servisi")
    @PostMapping("/update")
    public Map<RestEnum, Object> updateCalendar(@RequestBody ScheduleCalendar scheduleCalendar) {

        return  caDto.updateCalendar(scheduleCalendar);
    }

}
