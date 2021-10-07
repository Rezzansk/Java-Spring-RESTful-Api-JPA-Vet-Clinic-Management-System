package com.works.restcontroller;

import com.works.dto.LaborDto;
import com.works.entities.Labor;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/labor")
public class LaborRestController {
final LaborDto laborDto;

    public LaborRestController(LaborDto laborDto) {
        this.laborDto = laborDto;
    }

    @ApiOperation(value = "Hizmet Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum, Object> addlabor(@RequestBody Labor labor) {


        return  laborDto.addLabor(labor);
    }

    @ApiOperation(value = "Hizmet Listeleme Servisi")
    @GetMapping("/listAll")
    public Map<RestEnum, Object> listAllLabor() {

        return laborDto.listAllLabor();
    }

    @ApiOperation(value = "Hizmet Profili Gösteme Servisi")
    @GetMapping("/profile/{index}")
    public Map<RestEnum, Object> sngleAgenda(@PathVariable String index) {

        return laborDto.singleLabor(index);
    }

    @ApiOperation(value = "Hizmet Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum, Object> deleteAgenda(@PathVariable String index) {

        return laborDto.deleteLabor(index);
    }

    @ApiOperation(value = "Hizmet Güncelleme Servisi")
    @PostMapping("/update")
    public Map<RestEnum, Object> addAndUpdateAgenda(@RequestBody Labor labor) {

        return  laborDto.addUpdateLabor(labor);
    }

}
