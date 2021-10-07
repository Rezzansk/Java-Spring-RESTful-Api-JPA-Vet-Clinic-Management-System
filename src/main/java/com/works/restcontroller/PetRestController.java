package com.works.restcontroller;

import com.works.dto.PetDto;
import com.works.entities.Pet;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/pet")
@RestController
public class PetRestController {
    final PetDto petDto;

    public PetRestController(PetDto petDto) {
        this.petDto = petDto;
    }

    @ApiOperation(value = "Pet Ekleme Servisi")
    @PostMapping("/add")
    public Map<RestEnum,Object> petAdd(@RequestBody Pet pet){

        return petDto.petAdd(pet);
    }

    @ApiOperation(value = "Pet Güncelleme Servisi")
    @PostMapping("/update/{index}")
    public Map<RestEnum,Object> petUpdate(@RequestBody Pet pet, @PathVariable String index){

        return petDto.petUpdate(pet, index);
    }

    @ApiOperation(value = "Pet Silme Servisi")
    @DeleteMapping("/delete/{index}")
    public Map<RestEnum,Object> petDelete(@PathVariable String index){

     return petDto.petDelete(index);
    }

    @ApiOperation(value = "Pet Profili Gösterme Servisi")
    @GetMapping("/profile/{index}")
    public Map<RestEnum,Object> petProfile(@PathVariable String index){

        return petDto.petProfile(index);
    }

    @ApiOperation(value = "Pet Listeleme Servisi")
    @GetMapping("/list")
    public Map<RestEnum,Object> petList(){

        return petDto.petList();
    }

    @ApiOperation(value = "Müşteriye Göre Pet Listeleme Servisi")
    @GetMapping("/list-customer/{index}")
    public Map<RestEnum,Object> petListOfCustomer(@PathVariable String index ){

       return petDto.petListOfCustomer(index);
    }

    @ApiOperation(value = "Müşteriye Göre Pet Listeleme(Projection Kullanarak) Servisi")
    @GetMapping("/list-customer-projection/{index}")
    public Map<RestEnum,Object> petListProjectionOfCustomer(@PathVariable String index ){

        return petDto.petListProjectionOfCustomer(index);
    }

}
