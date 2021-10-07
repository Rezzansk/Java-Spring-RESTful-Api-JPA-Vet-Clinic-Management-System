package com.works.restcontroller;

import com.works.dto.PassChangeDto;
import com.works.properties.PassChange;
import com.works.properties.PassToken;
import com.works.repositories.UserRepository;
import com.works.utils.RestEnum;
import com.works.utils.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/passchange")
public class PassChangeRepository {

    final PassChangeDto passChangeDto;
    final UserService uService;
    final UserRepository uRepo;

    public PassChangeRepository(PassChangeDto passChangeDto, UserService uService, UserRepository uRepo) {
        this.passChangeDto = passChangeDto;
        this.uService = uService;
        this.uRepo = uRepo;
    }

    @ApiOperation(value = "Şifre Değiştirme Servisi")
    @PostMapping("/passchanger")
    public Map<RestEnum, Object> passChangeRequest(PassToken passToken) {

        return passChangeDto.passChangeWithToken(passToken);
    }

    @ApiOperation(value = "Şifre Değiştirmek İçin Gerekli Token'ı Alma Servisi")
    @PostMapping("/request")
    public Map<RestEnum, Object> passChanger(PassChange passChange) {

        return passChangeDto.passChange(passChange);

    }
}
