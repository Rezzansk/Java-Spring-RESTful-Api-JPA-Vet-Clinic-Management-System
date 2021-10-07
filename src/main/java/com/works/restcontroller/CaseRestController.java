package com.works.restcontroller;

import com.works.dto.CaseDto;
import com.works.entities.MoneyCase;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/case")
@RestController
public class CaseRestController {

    final CaseDto cDto;

    public CaseRestController(CaseDto cDto) {
        this.cDto = cDto;
    }

    @ApiOperation(value = "Kasa Para Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> caseAdd(@RequestBody MoneyCase moneyCase) {

        return cDto.caseAdd(moneyCase);
    }

    @ApiOperation(value = "Kasa Para Çıkarma Servisi")
    @PostMapping("/remove")
    public Map<RestEnum, Object> caseRemove(@RequestBody MoneyCase moneyCase) {

        return cDto.caseRemove(moneyCase);
    }

    @ApiOperation(value = "Kasa Hareket Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> caseDelete(@PathVariable String index) {

        return cDto.caseDelete(index);
    }

    @ApiOperation(value = "Kasa Hareket Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum, Object> caseList() {

        return cDto.caseList();
    }


}