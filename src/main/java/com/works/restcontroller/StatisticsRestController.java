package com.works.restcontroller;

import com.works.dto.StatisticsDto;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/statistics")
@RestController
public class StatisticsRestController {

    final StatisticsDto stDto;

    public StatisticsRestController(StatisticsDto stDto) {
        this.stDto = stDto;
    }

    @ApiOperation(value = "Yönetilen Veterinerliğe Dair İstatistik Bilgiler")
    @GetMapping("")
    public Map<String, Object> agendaList() {

        return stDto.list();
    }

}