package com.works.dto;

import com.works.entities.Storage;
import com.works.repositories.ProductRepository;
import com.works.repositories.StorageRepository;
import com.works.repositories.VaccineRepository;
import com.works.utils.RestEnum;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageDto {


    final StorageRepository strRepo;
    final VaccineRepository vacRepo;
    final ProductRepository proRepo;

    public StorageDto(StorageRepository strRepo, VaccineRepository vacRepo, ProductRepository proRepo) {
        this.strRepo = strRepo;
        this.vacRepo = vacRepo;
        this.proRepo = proRepo;
    }

    public Map<RestEnum, Object> storageList() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<Storage> list = strRepo.findByOrderByStidDesc();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Storage(depo) başarıyla listelendi.");
            hm.put(RestEnum.result,list);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Storage(depo) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> storageLastOperations() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<Storage> list = strRepo.findByOrderByStdateDesc();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Storage(depo) son 10 hareket başarıyla listelendi.");
            hm.put(RestEnum.result,list);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Storage(depo) son 10 hareket listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> storageVaccine(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer vac_id = Integer.parseInt(index);

        try{
            List<Storage> list = strRepo.findByStvacEqualsAllIgnoreCaseOrderByStidDesc(vacRepo.getById(vac_id));
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Vaccine(Aşı) depo hareketleri başarıyla listelendi.");
            hm.put(RestEnum.result,list);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Vaccine(Aşı) depo hareketleri listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> storageProduct(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer pro_id = Integer.parseInt(index);

        try{
            List<Storage> list = strRepo.findByStproEqualsAllIgnoreCaseOrderByStidDesc(proRepo.getById(pro_id));
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Product(Ürün) depo hareketleri başarıyla listelendi.");
            hm.put(RestEnum.result,list);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Product(Ürün) depo hareketleri listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }
}

