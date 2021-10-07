package com.works.dto;

import com.works.entities.*;
import com.works.repositories.*;
import com.works.utils.RestEnum;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CureDto {

    final CureRepository cureRepository;
    final StorageRepository strRepo;
    final CustomerRepository cRepo;
    final PetRepository pRepo;
    final ProductRepository prRepo;
    final VaccineRepository vacRepo;
    final LaborRepository laRepo;

    public CureDto(CureRepository cureRepository, StorageRepository strRepo, CustomerRepository cRepo, PetRepository pRepo, ProductRepository prRepo, VaccineRepository vacRepo, LaborRepository laRepo) {
        this.cureRepository = cureRepository;
        this.strRepo = strRepo;
        this.cRepo = cRepo;
        this.pRepo = pRepo;
        this.prRepo = prRepo;
        this.vacRepo = vacRepo;
        this.laRepo = laRepo;
    }

    public Map<RestEnum, Object> addCure(Cure cure) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        Integer temp_debt = 0;
        Storage storagePro = new Storage();
        Storage storageVac = new Storage();
        storagePro.setStaction(3);
        storageVac.setStaction(3);

        try {
            if( cure.getCurpro() != null)  {
                Product product = prRepo.findById(cure.getCurpro().getPrid()).get();
                cure.setCurpro(product);
                temp_debt = temp_debt + product.getPrsales();
                cure.getCurpro().setPrstock(product.getPrstock() - 1);
                storagePro.setStpro(cure.getCurpro());
                storagePro.setStchangeamount(1);
                storagePro.setStlastamount(product.getPrstock() - 1);
                storagePro.setStdate(new Date());
                strRepo.save(storagePro);
                System.out.println("sdad");
            }
            if (cure.getCurvac() != null) {
                Vaccine vaccine = vacRepo.findById(cure.getCurvac().getVacid()).get();
                cure.setCurvac(vaccine);
                temp_debt = temp_debt + vaccine.getVacsales();
                cure.getCurvac().setVacstock(vaccine.getVacstock() - 1);
                storageVac.setStvac(cure.getCurvac());
                storageVac.setStchangeamount(1);
                storageVac.setStlastamount(vaccine.getVacstock() - 1);
                storageVac.setStdate(new Date());
                strRepo.save(storageVac);
                System.out.println("sdssssad");
            }
            if (cure.getCurla() != null) {
                Labor labor = laRepo.findById(cure.getCurla().getLaid()).get();
                cure.setCurla(labor);
                temp_debt = temp_debt + labor.getLamoney();
                System.out.println("sdaddd + temp_debt");
            }

            cure.setCurdebt(temp_debt);
            Cure c = cureRepository.save(cure);
            Optional<Pet> pet1 = pRepo.findById(cure.getPet().getPid());
            Optional<Customer> customerOptional = cRepo.findById(pet1.get().getCid().getCid());
            customerOptional.get().setCdebpt(customerOptional.get().getCdebpt() + temp_debt);
            cRepo.saveAndFlush(customerOptional.get());

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Cure(Tedavi) başarıyla kaydedildi.");
            hm.put(RestEnum.result,c);
        } catch (Exception e) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Cure(Tedavi) kaydı sırasında bir hata oluştu."+ e);
        }

        return hm;
    }


    public Map<RestEnum, Object> listCure() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            List<Cure> ls = cureRepository.findAll();

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Cure(Tedavi) başarıyla listelendi.");
            hm.put(RestEnum.result,ls);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Cure(Tedavi) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }


    public Map<RestEnum, Object> profileCure(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        int id=Integer.parseInt(index);

        try {
            Cure c= cureRepository.findById(id).get();

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Cure(Tedavi) profil görüntüleme başarılı oldu. Görüntülenen Id: " + id);
            hm.put(RestEnum.result,c);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Cure(Tedavi) profil görüntüleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> deleteCure(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        int id= Integer.parseInt(index);

        try {
            cureRepository.deleteById(id);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Cure(Tedavi) başarıyla silindi.");
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Cure(Tedavi) silme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }


    public Map<RestEnum, Object> updateCure(Cure cure) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            Cure c =  cureRepository.saveAndFlush(cure);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Cure(Tedavi) başarıyla güncellendi.");
            hm.put(RestEnum.result,c);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Cure(Tedavi) güncelleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

}
