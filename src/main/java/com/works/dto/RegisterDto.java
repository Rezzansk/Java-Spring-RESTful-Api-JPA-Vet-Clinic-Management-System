package com.works.dto;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.RoleRepository;
import com.works.utils.RestEnum;
import com.works.utils.UserService;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterDto {
    final UserService uSevice;
    final RoleRepository rRepo;

    public RegisterDto(UserService uSevice, RoleRepository rRepo) {
        this.uSevice = uSevice;
        this.rRepo = rRepo;
    }


    public Map<RestEnum, Object> register(User us , String roleIDSt) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        try {

            long roleID = Long.parseLong(roleIDSt);
            us.setEnabled(true);
            us.setTokenExpired(true);

            Role role = rRepo.findById(roleID).get();

            List<Role> roles = new ArrayList<>();
            roles.add(role);

            us.setRoles(roles);

        User usrr=    uSevice.register(us);
          hm.put(RestEnum.status,"Succes!");
          hm.put(RestEnum.result,usrr);

        }catch ( AuthenticationException ex ) {}
       hm.put(RestEnum.status,"Register failed");
        return hm;
    }
}
