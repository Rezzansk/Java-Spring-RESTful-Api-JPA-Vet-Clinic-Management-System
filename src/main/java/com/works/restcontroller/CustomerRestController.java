package com.works.restcontroller;

import com.works.dto.CustomerDto;
import com.works.entities.Customer;
import com.works.entities.Suppliers;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/customer")
@RestController
public class CustomerRestController {

  final CustomerDto cDto;

  public CustomerRestController(CustomerDto cDto) {
    this.cDto = cDto;
  }

  @ApiOperation(value = "Müşteri Ekleme Servisi")
  @PostMapping("/add")
  public Map<RestEnum,Object> customerAdd(@RequestBody @Valid Customer customer, BindingResult bResult){
    Map<RestEnum,Object> hm =new LinkedHashMap<>();

    if ( bResult.hasErrors() ) {
      hm.put(RestEnum.status,"Failed!");
      hm.put(RestEnum.message,errors(bResult));
      return hm;
    }else {
      return cDto.customerAdd(customer);
    }
  }

  @ApiOperation(value = "Müşteri Silme Servisi")
  @DeleteMapping("/delete/{index}")
  public Map<RestEnum,Object> customerDelete(@PathVariable String index){

    return cDto.customerDelete(index) ;
  }

  @ApiOperation(value = "Müşteri Listeleme Servisi")
  @GetMapping("/list")
  public Map<RestEnum, Object> customerList() {

    return cDto.customerList();
  }

  @ApiOperation(value = "Müşteri Listeleme(Projection Kullanarak) Servisi")
  @GetMapping("/projection-list")
  public Map<RestEnum, Object> customerProjectionList() {

    return cDto.customerProjectionList();
  }

  @ApiOperation(value = "Müşteri Listeleme(Pagination Kullanarak) Servisi")
  @GetMapping("/pageable-list/{startPage}/{pageSize}")
  public Map<RestEnum, Object> customerPageableList(@PathVariable String startPage, @PathVariable String pageSize) {

    return cDto.customerPageableList(startPage, pageSize);
  }

  @ApiOperation(value = "Müşteri Güncelleme Servisi")
  @PostMapping("/update/{index}")
  public Map<RestEnum, Object> customerUpdate(@RequestBody Customer customer, @PathVariable String index) {

    return cDto.customerUpdate(customer, index);
  }

  @ApiOperation(value = "Müşteri Profili Görüntüleme Servisi")
  @GetMapping("/profile/{index}")
  public Map<RestEnum, Object> customerProfile(@PathVariable String index) {

    return cDto.customerProfile(index);
  }

  public List<Map<String, String>> errors(BindingResult bResult) {
    List<Map<String, String>> ls = new LinkedList<>();

    bResult.getAllErrors().forEach( error -> {
      String fieldName = ( (FieldError) error ).getField();
      String fieldMessage = error.getDefaultMessage();

      Map<String,String> erhm = new HashMap<>();
      erhm.put("fieldName", fieldName);
      erhm.put("fieldMessage", fieldMessage);
      ls.add(erhm);
    });

    return ls;
  }
}
