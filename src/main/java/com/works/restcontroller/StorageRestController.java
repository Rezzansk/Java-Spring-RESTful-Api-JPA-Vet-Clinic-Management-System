package com.works.restcontroller;

import com.works.dto.StorageDto;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/storage")
@RestController
public class StorageRestController {

    final StorageDto strDto;

    public StorageRestController(StorageDto strDto) {
        this.strDto = strDto;
    }

    // depo ürün çeşitleri
    @ApiOperation(value = "Depo Ürün Çeşitleri Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> storageList() {

        return strDto.storageList();
    }

    // Son 10 depo işlemi
    @ApiOperation(value = "Depo Son 10 Hareket Listeleme Servisi")
    @GetMapping("/last-operations")
    public Map<RestEnum, Object> storageLastOperations() {

        return strDto.storageLastOperations();
    }

    @ApiOperation(value = "Depo Aşı Hareketleri Listeleme Servisi")
    @GetMapping("/vaccine/{index}")
    public Map<RestEnum, Object> storageVaccine(@PathVariable String index) {

        return strDto.storageVaccine(index);
    }

    @ApiOperation(value = "Depo Ürün Hareketleri Listeleme Servisi")
    @GetMapping("/product/{index}")
    public Map<RestEnum, Object> storageProduct(@PathVariable String index) {

        return strDto.storageProduct(index);
    }

}
