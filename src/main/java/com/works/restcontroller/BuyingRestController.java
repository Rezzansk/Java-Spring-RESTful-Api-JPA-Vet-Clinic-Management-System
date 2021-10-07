package com.works.restcontroller;

import com.works.dto.BuyingDto;
import com.works.entities.Buying;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/buying")
public class BuyingRestController {

    final BuyingDto buyingDto;

    public BuyingRestController(BuyingDto buyingDto) {
        this.buyingDto = buyingDto;
    }

    @ApiOperation(value = "Yeni Alış Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> buyingAdd(@RequestBody Buying buying) {

        return  buyingDto.buyingAdd(buying);
    }

    @ApiOperation(value = "Alış Detay Gösterme Servisi")
    @GetMapping("/detail/{index}")
    public Map<RestEnum, Object> buyingDetail(@PathVariable String index) {

        return buyingDto.buyingDetail(index);
    }

    @ApiOperation(value = "Alış Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> buyingDelete(@PathVariable String index) {

        return buyingDto.buyingDelete(index);
    }

    @ApiOperation(value = "Alış Liste Gösterme Servisi")
    @GetMapping("/list-complated")
    public Map<RestEnum, Object> buyingComplatedList() {

        return  buyingDto.buyingComplatedList();
    }

    @ApiOperation(value = "Alış Tamamlama Servisi")
    @GetMapping("/complated/{index}")
    public Map<RestEnum, Object> buyingComplated(@PathVariable String index) {

        return buyingDto.buyingComplated(index);
    }



}
