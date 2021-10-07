package com.works.dto;

import com.works.entities.Product;
import com.works.entities.Storage;
import com.works.entities.Product;
import com.works.entities.ProductStockAdd;
import com.works.repositories.ProductRepository;
import com.works.repositories.StorageRepository;
import com.works.repositories.SuppliersRepository;
import com.works.repositories.ProductRepository;
import com.works.utils.RestEnum;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductDto {

    final ProductRepository proRepo;
    final StorageRepository strRepo;
    final SuppliersRepository supRepo;

    public ProductDto(ProductRepository proRepo, StorageRepository strRepo, SuppliersRepository supRepo) {
        this.proRepo = proRepo;
        this.strRepo = strRepo;
        this.supRepo = supRepo;
    }

    public Map<RestEnum, Object> productAdd(Product product) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            Product pro = proRepo.save(product);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Product(Ürün) başarıyla eklendi.");
            hm.put(RestEnum.result,pro);

            try {
                // first add Storage
                Storage storage = new Storage();
                Date date = new Date();
                int last_id = proRepo.findByAllIgnoreCaseOrderByPridDesc().get(0).getPrid(); // find last product id
                Optional<Product> pro1 = proRepo.findById(last_id);

                storage.setStdate(date);
                storage.setStaction(1);
                storage.setStchangeamount(pro1.get().getPrstock());
                storage.setStlastamount(pro1.get().getPrstock());
                storage.setStpro(pro1.get());
                Storage str = strRepo.save(storage);

                hm.put(RestEnum.status_2,"Success!");
                hm.put(RestEnum.message_2,"Product(Ürün) başarıyla depoya eklendi");
                hm.put(RestEnum.result_2,str);
            } catch (Exception e) {
                hm.put(RestEnum.status_2,"Failed!");
                hm.put(RestEnum.message_2,"Product(Ürün) depoya ekleme işlemi başarısız oldu. "+ e.getLocalizedMessage());
            }

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Product(Ürün) ekleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> productDelete(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer pro_id = Integer.parseInt(index);

        try{
            proRepo.deleteById(pro_id);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Product(Ürün) başarıyla silindi. Silinen Id: "+ pro_id);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Product(Ürün) silme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> productList() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<Product> list = proRepo.findByAllIgnoreCaseOrderByPridDesc();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Product(Ürün) başarıyla listelendi.");
            hm.put(RestEnum.result,list);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Product(Ürün) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }
    
    public Map<RestEnum, Object> productProfile(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer pro_id = Integer.parseInt(index);

        try{
            Optional<Product> vac = proRepo.findById(pro_id);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Product(Ürün) profil görüntüleme başarılı oldu. Görüntülenen Id: " + pro_id);
            hm.put(RestEnum.result,vac.get());
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Product(Ürün) profil görüntüleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    // Pr_select: if==1 -> add | if==2 -> update
    public Map<RestEnum, Object> productStockAddUpdate(ProductStockAdd productStockAdd, String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer pro_id = Integer.parseInt(index);

        try {
            Date date = new Date();
            Product pro = proRepo.findById(pro_id).get();

            Storage storage = new Storage();
            storage.setStpro(pro);
            storage.setStdate(date);
            productStockAdd.setPr_id(pro_id);

            if(productStockAdd.getPr_select() == 1) {
                Integer temp = pro.getPrstock() + productStockAdd.getPr_amount();
                pro.setPrstock(temp);
                storage.setStaction(1);
                storage.setStchangeamount(productStockAdd.getPr_amount());
                storage.setStlastamount(temp);
            } else if ( productStockAdd.getPr_select() == 2 ) {
                pro.setPrstock(productStockAdd.getPr_amount());
                storage.setStaction(2);
                storage.setStchangeamount(productStockAdd.getPr_amount());
                storage.setStlastamount(productStockAdd.getPr_amount());
            }
            proRepo.saveAndFlush(pro);
            strRepo.save(storage);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Depoya Product(Ürün) ekleme / güncelleme başarılı.");
            hm.put(RestEnum.result,storage);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Depoya Product(Ürün)  ekleme / güncelleme başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> productUpdate(Product Product, String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer pro_id = Integer.parseInt(index);

        try {
            Product.setPrid(pro_id);
            Product pro = proRepo.saveAndFlush(Product);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Product(Ürün) başarıyla güncellendi.");
            hm.put(RestEnum.result,pro);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Product(Ürün) güncelleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

}