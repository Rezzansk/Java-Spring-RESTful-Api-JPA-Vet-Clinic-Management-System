package com.works.dto;

import com.works.entities.Customer;
import com.works.entities.Pet;
import com.works.projections.PetProjection;
import com.works.repositories.CustomerRepository;
import com.works.repositories.PetRepository;
import com.works.utils.RestEnum;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PetDto {
final PetRepository pRepo;
final CustomerRepository cRepo;

    public PetDto(PetRepository pRepo, CustomerRepository cRepo) {
        this.pRepo = pRepo;
        this.cRepo = cRepo;
    }

    public Map<RestEnum, Object> petAdd(Pet pet){
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            Pet pet1 = pRepo.save(pet);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla eklendi.");
            hm.put(RestEnum.result,pet1);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) ekleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> petUpdate(Pet pet, String index){
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer pet_id = Integer.parseInt(index);

        try {
            pet.setPid(pet_id);
            Pet pet1 = pRepo.save(pet);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla güncellendi.");
            hm.put(RestEnum.result,pet1);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) güncelleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> petDelete(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        int pid=Integer.parseInt(index);


        try {
           pRepo.deleteById(pid);
        hm.put(RestEnum.status,"succes");
        hm.put(RestEnum.message,"The Pet has been deleted");

        } catch (Exception e) {
            hm.put(RestEnum.status,"failed");
            hm.put(RestEnum.message,"an error occurred in deleting action "+e.getLocalizedMessage());

        }
        return hm;
    }

    public Map<RestEnum, Object> petList() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<Pet> pet = pRepo.findAll();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla listelendi.");
            hm.put(RestEnum.result,pet);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> petListOfCustomer(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer cu_id = Integer.parseInt(index);

        try{
            List<Pet> pet = pRepo.findByCid_CidEquals(cu_id);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla listelendi.");
            hm.put(RestEnum.result,pet);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> petListProjectionOfCustomer(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer cu_id = Integer.parseInt(index);

        try{
            List<PetProjection> pet = pRepo.findByCid_CidIsAllIgnoreCase(cRepo.findById(cu_id).get());
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) başarıyla listelendi.");
            hm.put(RestEnum.result,pet);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> petProfile(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer pet_id = Integer.parseInt(index);

        try{
            Pet pet = pRepo.findById(pet_id).get();
            System.out.println(pet);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Customer(Müşteri) profil görüntüleme başarılı oldu. Görüntülenen Id: " + pet_id);
            hm.put(RestEnum.result,pet);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Customer(Müşteri) profil görüntüleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }
        return hm;
    }

}
