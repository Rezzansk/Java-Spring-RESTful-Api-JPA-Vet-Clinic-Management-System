package com.works.restcontroller;

import com.works.dto.AgendaDto;
import com.works.model.Agenda;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/agenda")
@RestController
public class AgendaRestController {

    final AgendaDto agDto;

    public AgendaRestController(AgendaDto agDto) {
        this.agDto = agDto;
    }

    @ApiOperation(value = "Yeni Ajanda Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> agendaAdd(@RequestBody Agenda agenda) {

        return agDto.agendaAdd(agenda);
    }

    @ApiOperation(value = "Ajanda Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> agendaDelete(@PathVariable String index) {

        return agDto.agendaDelete(index);
    }

    @ApiOperation(value = "Ajanda Listeme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> agendaList() {

        return agDto.agendaList();
    }

    @ApiOperation(value = "Tek Ajanda Profil Gösterme Servisi")
    @GetMapping("/profile/{index}")
    public Map<RestEnum, Object> agendaProfile(@PathVariable String index) {

        return agDto.agendaProfile(index);
    }

    @ApiOperation(value = "Ajanda Güncelleme Servisi")
    @PostMapping("/update/{index}")
    public Map<RestEnum, Object> agendaUpdate(@RequestBody Agenda agenda, @PathVariable String index) {

        return agDto.agendaUpdate(agenda, index);
    }

}