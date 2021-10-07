package com.works.restcontroller;

import com.works.dto.SaleDto;
import com.works.entities.Sale;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sale")
public class SaleRestController {
    final SaleDto saleDto;


    public SaleRestController(SaleDto saleDto) {
        this.saleDto = saleDto;
    }

    @ApiOperation(value = "Satış Tamamlama Servisi")
    @GetMapping("/status/{index}")
    public Map<RestEnum, Object> saleStatus(@PathVariable String index) {

        return saleDto.saleStatus(index);
    }

    @ApiOperation(value = "Satış Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> saleDelete(@PathVariable String index) {

        return saleDto.saleDelete(index);
    }

    @ApiOperation(value = "Satış Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> saleAdd(@RequestBody Sale sale) {

        return  saleDto.saleAdd(sale);
    }


}
