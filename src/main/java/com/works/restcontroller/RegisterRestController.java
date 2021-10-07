package com.works.restcontroller;

import com.works.dto.RegisterDto;
import com.works.entities.User;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterRestController {
final RegisterDto registerDto;

    public RegisterRestController(RegisterDto registerDto) {
        this.registerDto = registerDto;
    }

    @ApiOperation(value = "Sisteme Kayıt Olma Servisi")
    @PostMapping("/register")
    public Map<RestEnum,Object> register(User us, @RequestParam(defaultValue = "1") String roleIDSt) {

        return registerDto.register(us,roleIDSt);
    }





}
