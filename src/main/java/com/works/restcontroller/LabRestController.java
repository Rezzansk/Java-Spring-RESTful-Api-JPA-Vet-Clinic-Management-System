package com.works.restcontroller;

import com.works.dto.LabDto;
import com.works.entities.Lab;
import com.works.properties.LabProp;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@RequestMapping("/lab")
@RestController
public class LabRestController {

    final LabDto labDto;

    public LabRestController(LabDto labDto) {
        this.labDto = labDto;
    }

    @ApiOperation(value = "Lab Sonucu Ekleme Servisi")
    @PostMapping("/upload")
    public Map<RestEnum, Object> upload(@RequestParam("fileName") MultipartFile file, LabProp lab) {

        return labDto.upload(file, lab);
    }

    @ApiOperation(value = "Lab Sonucu Silme Servisi")
    @DeleteMapping("/delete/{iid}")
    public Map<RestEnum, Object> deleteLabresult(@PathVariable String iid) {

        return labDto.delete(iid);
    }

    @ApiOperation(value = "Lab Sonucu Profili Gösterme Servisi")
    @GetMapping("/profile/{index}")
    public Map<RestEnum, Object> profile(@PathVariable String index) {

        return labDto.profile(index);
    }

    @ApiOperation(value = "Lab Sonucu Pet'e Göre Listeleme Servisi")
    @GetMapping("/pet/{index}")
    public Map<RestEnum, Object> petLab(@PathVariable String index) {

        return labDto.petLab(index);
    }

    @ApiOperation(value = "Lab Sonucu Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> list() {

        return labDto.list();
    }

}
