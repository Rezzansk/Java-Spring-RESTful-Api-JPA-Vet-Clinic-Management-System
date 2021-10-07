package com.works.restcontroller;

import com.works.dto.SuppliersDto;
import com.works.entities.Suppliers;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/suppliers")
@RestController
public class SuppliersRestController {

    final SuppliersDto supDto;

    public SuppliersRestController(SuppliersDto supDto) {
        this.supDto = supDto;
    }

    @ApiOperation(value = "Tedarikçi Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> suppliersAdd(@RequestBody Suppliers suppliers) {

        return supDto.suppliersAdd(suppliers);
    }

    @ApiOperation(value = "Tedarikçi Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> suppliersDelete(@PathVariable String index) {

        return supDto.suppliersDelete(index);
    }

    @ApiOperation(value = "Tedarikçi Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> supplierList() {

        return supDto.suppliersList();
    }

    @ApiOperation(value = "Tedarikçi Profil Görüntüleme Servisi")
    @GetMapping("/profile/{index}")
    public Map<RestEnum, Object> suppliersProfile(@PathVariable String index) {

        return supDto.suppliersProfile(index);
    }

    @ApiOperation(value = "Tedarikçi Bilgileri Güncelleme Servisi")
    @PostMapping("/update/{index}")
    public Map<RestEnum, Object> suppliersUpdate(@RequestBody Suppliers suppliers, @PathVariable String index) {

        return supDto.suppliersUpdate(suppliers, index);
    }

    @ApiOperation(value = "Tedarikçi Borç Ödeme Servisi")
    @PostMapping("/pay-debt/{index}")
    public Map<RestEnum, Object> suppliersPayDebt(String debt, @PathVariable String index) {

        return supDto.suppliersPayDebt(debt, index);
    }
}