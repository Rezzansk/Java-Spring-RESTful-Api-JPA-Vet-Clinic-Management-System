package com.works.dto;

import com.works.entities.PasswordResetToken;
import com.works.entities.User;
import com.works.properties.PassChange;
import com.works.properties.PassToken;
import com.works.repositories.PassChangeTokenRepository;
import com.works.repositories.UserRepository;
import com.works.utils.RestEnum;
import com.works.utils.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service

@Transactional
public class PassChangeDto {

    final UserService uService;
final UserRepository uRepo;
final PassChangeTokenRepository passChangeTokenRepository;

    public PassChangeDto(UserService userService, UserRepository uRepo, PassChangeTokenRepository passChangeTokenRepository) {
        this.uService = userService;
        this.uRepo = uRepo;
        this.passChangeTokenRepository = passChangeTokenRepository;
    }


    public Map<RestEnum,Object> passChange(PassChange passChange){
        Map<RestEnum,Object> hm =new LinkedHashMap<>();
        UserDetails userDetails =  uService.loadUserByUsername(passChange.getEmail());
        System.out.println(userDetails.getUsername());

        if (userDetails.getUsername().equals(passChange.getEmail())){
            PasswordResetToken pResetToken=new PasswordResetToken();
            String email=passChange.getEmail();
            String token = UUID.randomUUID().toString();
            Optional<User> oUser = uRepo.findByEmailEqualsAllIgnoreCase(email);
            User us=oUser.get();
            pResetToken.setUser(us);
            pResetToken.setToken(token);
            PasswordResetToken p=passChangeTokenRepository.save(pResetToken);

          /*  us.setPassword( uService.encoder().encode(passChange.getNewPass()));
            uRepo.saveAndFlush(us);*/
            hm.put(RestEnum.result,"success");
            hm.put(RestEnum.message,"passResetToken saved");
            hm.put(RestEnum.result,p.getToken());
        }else {
            hm.put(RestEnum.result, "failed");
            hm.put(RestEnum.message, "Kullanıcı adı veya şifre hatalı");
        }
        return hm;
    }



    public Map<RestEnum,Object> passChangeWithToken(PassToken passToken){
        Map<RestEnum,Object> hm =new LinkedHashMap<>();
        UserDetails userDetails =  uService.loadUserByUsername(passToken.getEmail());

        PasswordResetToken ptoken= passChangeTokenRepository.findByTokenEquals(passToken.getToken());
        if (ptoken != null) {

    hm.put(RestEnum.result,ptoken);
    hm.put(RestEnum.message,"token ");
        if (userDetails.getUsername().equals(passToken.getEmail())){
            System.out.println(userDetails);
            String email=passToken.getEmail();
            Optional<User> oUser = uRepo.findByEmailEqualsAllIgnoreCase(email);
            User us=oUser.get();
            us.setPassword( uService.encoder().encode(passToken.getNewPass()));
            uRepo.saveAndFlush(us);
            hm.put(RestEnum.result,"Şifre değiştirme başarılı. ");
        }

        }else {

            hm.put(RestEnum.result, "Email hatalı ");
            hm.put(RestEnum.message, "şifre yada email hatalı ");
        }
    return hm;
    }

}
