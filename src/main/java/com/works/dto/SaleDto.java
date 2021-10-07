package com.works.dto;


import com.works.entities.MoneyCase;
import com.works.entities.Sale;
import com.works.repositories.CaseRepository;
import com.works.repositories.SaleRepository;
import com.works.utils.RestEnum;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleDto {


    final SaleRepository sRepo;
    final CaseRepository cRepo;

    public SaleDto(SaleRepository sRepo, CaseRepository cRepo) {
        this.sRepo = sRepo;
        this.cRepo = cRepo;
    }


    public Map<RestEnum,Object> saleAdd(Sale sale){
        Map<RestEnum,Object> hm =new LinkedHashMap<>();

        try{ sale.setSaleDate(new Date());
            float tax=0.3f;
            sale.setGrossPrice(sale.getPamount()*sale.getPprice());
            sale.setNetPrice(sale.getGrossPrice()-sale.getGrossPrice()*sale.getPdiscount()/100);
            if(sale.getTax_type()==0){
                tax = 0;
            }
            else if(sale.getTax_type()==1){
                tax = 0.01f;
            }
            else if(sale.getTax_type()==2){
                tax = 0.08f;
            }
            else if(sale.getTax_type()==3){
                tax = 0.18f;
            }

            sale.setTotalPrice((int) (sale.getNetPrice()*(1+tax)));

            Sale s = sRepo.save(sale);

            Authentication aut = SecurityContextHolder.getContext().getAuthentication();
            String email = aut.getName(); // username

            // KASA
            MoneyCase casec = new MoneyCase();
            casec.setCmoney(sale.getTotalPrice());
            casec.setCbuysell(1);
            casec.setCdatenow(new Date());
            casec.setCpeople(sale.getCname());
            casec.setCcomment("Satış");
            casec.setCname(email);
            cRepo.save(casec);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Sale(Satış) başarılı.");
            hm.put(RestEnum.result,sale);
        } catch (Exception e) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Sale(Satış) başarısız oldu. "+ e.getLocalizedMessage());
        }

        return hm;
    }

    public Map<RestEnum,Object> saleDelete(String index){
        Map<RestEnum,Object> hm =new LinkedHashMap<>();
        Integer sid = Integer.parseInt(index);

        try{
            sRepo.deleteById(sid);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Sale(Satış) silindi. Silinen id: " + sid);
        } catch (Exception e) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Sale(Satış) silme başarısız oldu. "+ e.getLocalizedMessage());
        }


        return hm;
    }


    public Map<RestEnum,Object> saleStatus(String index){
        Map<RestEnum,Object> hm =new LinkedHashMap<>();

        try{
            int faturaNo = Integer.parseInt(index);
            List<Sale> saleList = sRepo.getByFaturaNoEquals(faturaNo);

            for (Sale sale: saleList){

                sale.setStatus(true);
            }

            sRepo.saveAllAndFlush(saleList);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Sale(Satış) status başarıyla değiştirldi.");
        } catch (Exception e) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Sale(Satış) status değiştirme başarısız oldu. "+ e.getLocalizedMessage());
        }

        return hm;
    }

}
