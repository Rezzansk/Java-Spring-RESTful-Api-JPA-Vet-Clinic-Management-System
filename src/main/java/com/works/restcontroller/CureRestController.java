package com.works.restcontroller;


import com.works.dto.CureDto;
import com.works.entities.Cure;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cure")
public class CureRestController {

    final CureDto cureDto;

    public CureRestController(CureDto cureDto) {
        this.cureDto = cureDto;
    }

    @ApiOperation(value = "Tedavi Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> addCure(@RequestBody Cure cure) {

        return  cureDto.addCure(cure);
    }

    @ApiOperation(value = "Tedavi Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> listCure() {

        return cureDto.listCure();
    }

    @ApiOperation(value = "Tedavi Ekleme Servisi")
    @GetMapping("/profile/{index}")
    public Map<RestEnum, Object> profileCure(@PathVariable String index) {

        return cureDto.profileCure(index);
    }

    @ApiOperation(value = "Tedavi Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> deleteCure(@PathVariable String index) {

        return cureDto.deleteCure(index);
    }

    @ApiOperation(value = "Tedavi Güncelleme Servisi")
    @PostMapping("/update")
    public Map<RestEnum, Object> updateCure(@RequestBody Cure cure) {

        return  cureDto.updateCure(cure);
    }

}
