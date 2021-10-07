package com.works.restcontroller;

import com.works.dto.ProductDto;
import com.works.entities.Product;
import com.works.entities.ProductStockAdd;
import com.works.entities.Vaccine;
import com.works.entities.VaccineStockAdd;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/product")
@RestController
public class ProductRestController {

    final ProductDto proDto;

    public ProductRestController(ProductDto proDto) {
        this.proDto = proDto;
    }

    @ApiOperation(value = "Ürün Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> productAdd(@RequestBody Product product) {

        return proDto.productAdd(product);
    }

    @ApiOperation(value = "Ürün Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> productDelete(@PathVariable String index) {

        return proDto.productDelete(index);
    }

    @ApiOperation(value = "Ürün Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> productList() {

        return proDto.productList();
    }

    @ApiOperation(value = "Ürün Profili Görüntüleme Servisi")
    @GetMapping("/profile/{index}")
    public Map<RestEnum, Object> productProfile(@PathVariable String index) {

        return proDto.productProfile(index);
    }

    @ApiOperation(value = "Ürün Stock Ekleme / Güncelleme Servisi")
    @PostMapping("/stock/add&update/{index}")
    public Map<RestEnum, Object> productStockAddUpdate(@RequestBody ProductStockAdd productStockAdd, @PathVariable String index) {

        return proDto.productStockAddUpdate(productStockAdd, index);
    }

    @ApiOperation(value = "Ürün Bilgileri Güncelleme Servisi")
    @PostMapping("/update/{index}")
    public Map<RestEnum, Object> vaccineUpdate(@RequestBody Product product, @PathVariable String index) {

        return proDto.productUpdate(product, index);
    }

}