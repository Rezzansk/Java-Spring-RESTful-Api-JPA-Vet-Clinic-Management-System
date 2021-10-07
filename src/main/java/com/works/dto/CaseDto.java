package com.works.dto;

import com.works.entities.MoneyCase;
import com.works.repositories.CaseRepository;
import com.works.utils.RestEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CaseDto {

    final CaseRepository caRepo;

    public CaseDto(CaseRepository caRepo) {
        this.caRepo = caRepo;
    }

    public Map<RestEnum, Object> caseAdd(MoneyCase moneyCase) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {

            Authentication aut = SecurityContextHolder.getContext().getAuthentication();
            String email = aut.getName(); // username

            moneyCase.setCbuysell(1);
            moneyCase.setCname(email);

            MoneyCase money = caRepo.save(moneyCase);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Ajanda) başarıyla eklendi.");
            hm.put(RestEnum.result,money);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Ajanda) ekleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> caseRemove(MoneyCase moneyCase) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {


            Authentication aut = SecurityContextHolder.getContext().getAuthentication();
            String email = aut.getName(); // username


            moneyCase.setCbuysell(2);
            moneyCase.setCname(email);

            MoneyCase money = caRepo.save(moneyCase);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Ajanda) başarıyla eklendi.");
            hm.put(RestEnum.result,money);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Ajanda) ekleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> caseDelete(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer cid = Integer.parseInt(index);

        try{
            caRepo.deleteById(cid);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Ajanda) başarıyla silindi. Silinen Id: "+ cid);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Ajanda) silme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> caseList() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<MoneyCase> moneyCaseList = caRepo.findByOrderByCidDesc();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Ajanda) başarıyla listelendi.");
            hm.put(RestEnum.result,moneyCaseList);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Ajanda) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }


}