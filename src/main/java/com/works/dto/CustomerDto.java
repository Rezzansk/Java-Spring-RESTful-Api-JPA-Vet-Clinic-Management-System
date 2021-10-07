package com.works.dto;

import com.works.entities.Customer;
import com.works.entities.Suppliers;
import com.works.projections.PetCustomerProjection;
import com.works.repositories.CustomerRepository;
import com.works.utils.RestEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerDto {
final CustomerRepository cRepo;

    public CustomerDto(CustomerRepository cRepo) {
        this.cRepo = cRepo;
    }

    public Map<RestEnum, Object> customerAdd(Customer customer){
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            Customer cus = cRepo.save(customer);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla eklendi.");
            hm.put(RestEnum.result,cus);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) ekleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> customerDelete(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer c_id= Integer.parseInt(index);

        try{
            cRepo.deleteById(c_id);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla silindi. Silinen Id: "+ c_id);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) silme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> customerList() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<Customer> customerList = cRepo.findAll();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla listelendi.");
            hm.put(RestEnum.result,customerList);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> customerProjectionList() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<PetCustomerProjection> customerList = cRepo.findByAllIgnoreCaseOrderByCidDesc();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla listelendi.");
            hm.put(RestEnum.result,customerList);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> customerPageableList(String startPage, String pageSize) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer startPg = Integer.parseInt(startPage);
        Integer pgSize = Integer.parseInt(pageSize);

        try {
            Pageable pageable = PageRequest.of(startPg, pgSize);
            List<Customer> customerList=  cRepo.findByOrderByCidAsc(pageable);
            if (customerList.size() != 0) {
    
                long totalCustomer= cRepo.countByCidAllIgnoreCase();

                hm.put(RestEnum.status,"Success!");
                hm.put(RestEnum.message,"Customer(Müşteri) başarıyla sayfalama ile listelendi.");
                hm.put(RestEnum.totalSize,"Toplam Customer(Müşteri) Sayısı: " + totalCustomer);
                hm.put(RestEnum.startPage,"Başlanan Sayfa: " + startPg );
                hm.put(RestEnum.pageSize,"Sayfa Başı Değer: " + pgSize );
                hm.put(RestEnum.result,customerList);

            } else {
                hm.put(RestEnum.status,"Failed!");
                hm.put(RestEnum.message,"Veritabanında yeterli veri yok.");
            }
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.startPage,"Başlanan Sayfa: " + startPg );
            hm.put(RestEnum.pageSize,"Sayfa Başı Değer: " + pgSize );
            hm.put(RestEnum.message,"Customer(Müşteri) sayfalama ile listeleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }

        return hm;
    }

    public Map<RestEnum, Object> customerUpdate(Customer customer, String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer cus_id = Integer.parseInt(index);

        try {
            customer.setCid(cus_id);
            Customer cus = cRepo.saveAndFlush(customer);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla güncellendi.");
            hm.put(RestEnum.result,cus);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) güncelleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> customerProfile(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer cus_id = Integer.parseInt(index);

        try{
            Customer cus = cRepo.findById(cus_id).get();

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) profil görüntüleme başarılı oldu. Görüntülenen Id: " + cus_id);
            hm.put(RestEnum.result,cus);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) profil görüntüleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }
        return hm;
    }

}
