package com.works.dto;

import com.works.entities.Product;
import com.works.entities.ProductStockAdd;
import com.works.entities.Storage;
import com.works.entities.Suppliers;
import com.works.repositories.ProductRepository;
import com.works.repositories.StorageRepository;
import com.works.repositories.SuppliersRepository;
import com.works.utils.RestEnum;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuppliersDto {
    
    final SuppliersRepository supRepo;

    public SuppliersDto(SuppliersRepository supRepo) {
        
        this.supRepo = supRepo;
    }

    public Map<RestEnum, Object> suppliersAdd(Suppliers suppliers) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            Suppliers sup = supRepo.save(suppliers);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) başarıyla eklendi.");
            hm.put(RestEnum.result,sup);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) ekleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> suppliersDelete(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer sup_id = Integer.parseInt(index);

        try{
            supRepo.deleteById(sup_id);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) başarıyla silindi. Silinen Id: "+ sup_id);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) silme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> suppliersList() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<Suppliers> listSuppliers = supRepo.findByOrderBySuidDesc();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) başarıyla listelendi.");
            hm.put(RestEnum.result,listSuppliers);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> suppliersProfile(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer sup_id = Integer.parseInt(index);

        try{
            Suppliers sup = supRepo.findById(sup_id).get();

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) profil görüntüleme başarılı oldu. Görüntülenen Id: " + sup_id);
            hm.put(RestEnum.result,sup);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) profil görüntüleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> suppliersUpdate(Suppliers suppliers, String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer sup_id = Integer.parseInt(index);

        try {
            suppliers.setSuid(sup_id);
            Suppliers sup = supRepo.saveAndFlush(suppliers);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) başarıyla güncellendi.");
            hm.put(RestEnum.result,sup);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) güncelleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> suppliersPayDebt(String debt, String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer sup_debt = Integer.parseInt(debt);
        Integer sup_id = Integer.parseInt(index);

        try {
            Suppliers suppliers = supRepo.findById(sup_id).get();
            suppliers.setSudebt(suppliers.getSudebt()-sup_debt);
            supRepo.saveAndFlush(suppliers);

            Suppliers sup = supRepo.save(suppliers);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) borç başarılya ödendi.");
            hm.put(RestEnum.result,sup);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Tedarikçi) borç ödeme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

}