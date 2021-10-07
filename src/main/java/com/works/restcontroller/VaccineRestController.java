package com.works.restcontroller;

import com.works.dto.VaccineDto;
import com.works.entities.Vaccine;
import com.works.entities.VaccineStockAdd;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/vaccine")
@RestController
public class VaccineRestController {

    final VaccineDto vacDto;

    public VaccineRestController(VaccineDto vacDto) {
        this.vacDto = vacDto;
    }

    @ApiOperation(value = "Aşı Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> vaccineAdd(@RequestBody Vaccine vaccine) {

        return vacDto.vaccineAdd(vaccine);
    }

    @ApiOperation(value = "Aşı Silme Servisi")
    @DeleteMapping("/delete/{vac}")
    public Map<RestEnum, Object> vaccineDelete(@PathVariable String vac) {

        return vacDto.vaccineDelete(vac);
    }

    @ApiOperation(value = "Aşı Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> vaccineList() {

        return vacDto.vaccineList();
    }

    @ApiOperation(value = "Aşı Profil Bilgisi Gösterme Servisi")
    @GetMapping("/profile/{index}")
    public Map<RestEnum, Object> vaccineProfile(@PathVariable String index) {

        return vacDto.vaccineProfile(index);
    }

    @ApiOperation(value = "Aşı Stok Ekleyip / Güncelleme Servisi")
    @PostMapping("/stock/add&update/{index}")
    public Map<RestEnum, Object> vaccineStockAddUpdate(@RequestBody VaccineStockAdd vaccineStockAdd, @PathVariable String index) {

        return vacDto.vaccineStockAddUpdate(vaccineStockAdd, index);
    }

    @ApiOperation(value = "Aşı Bilgileri Güncelleme Servisi")
    @PostMapping("/update/{index}")
    public Map<RestEnum, Object> vaccineUpdate(@RequestBody Vaccine vaccine, @PathVariable String index) {

        return vacDto.vaccineUpdate(vaccine, index);
    }

}
