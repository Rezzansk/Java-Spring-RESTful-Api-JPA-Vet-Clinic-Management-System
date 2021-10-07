package com.works.dto;

import com.works.entities.Storage;
import com.works.entities.Suppliers;
import com.works.entities.Vaccine;
import com.works.entities.VaccineStockAdd;
import com.works.repositories.StorageRepository;
import com.works.repositories.SuppliersRepository;
import com.works.repositories.VaccineRepository;
import com.works.utils.RestEnum;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VaccineDto {

    final VaccineRepository vacRepo;
    final StorageRepository strRepo;
    final SuppliersRepository supRepo;

    public VaccineDto(VaccineRepository vacRepo, StorageRepository strRepo, SuppliersRepository supRepo) {
        this.vacRepo = vacRepo;
        this.strRepo = strRepo;
        this.supRepo = supRepo;
    }

    public Map<RestEnum, Object> vaccineAdd(Vaccine vaccine) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            Vaccine vac = vacRepo.save(vaccine);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Vaccine(Aşı) başarıyla eklendi.");
            hm.put(RestEnum.result,vac);

            try {
                // first add Storage
                Storage storage = new Storage();
                Date date = new Date();
                int last_id = vacRepo.findByAllIgnoreCaseOrderByVacidDesc().get(0).getVacid(); // find last vac id
                Optional<Vaccine> vaccine1 = vacRepo.findById(last_id);

                storage.setStdate(date);
                storage.setStaction(1);
                storage.setStchangeamount(vaccine1.get().getVacstock());
                storage.setStlastamount(vaccine1.get().getVacstock());
                storage.setStvac(vaccine1.get());
                Storage str = strRepo.save(storage);

                hm.put(RestEnum.status_2,"Success!");
                hm.put(RestEnum.message_2,"Vaccine(Aşı) başarıyla depoya eklendi");
                hm.put(RestEnum.result_2,str);
            } catch (Exception e) {
                hm.put(RestEnum.status_2,"Failed!");
                hm.put(RestEnum.message_2,"Vaccine(Aşı) depoya ekleme işlemi başarısız oldu. "+ e.getLocalizedMessage());
            }

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Vaccine(Aşı) ekleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> vaccineDelete(String vac) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer vac_id = Integer.parseInt(vac);

        try{
            vacRepo.deleteById(vac_id);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Vaccine(Aşı) başarıyla silindi. Silinen Id: "+ vac_id);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Vaccine(Aşı) silme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> vaccineList() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<Vaccine> list = vacRepo.findByAllIgnoreCaseOrderByVacidDesc();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Vaccine(Aşı) başarıyla listelendi.");
            hm.put(RestEnum.result,list);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Vaccine(Aşı) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> vaccineProfile(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer vacid = Integer.parseInt(index);
        try{

            vacid = Integer.parseInt(index);
            Optional<Vaccine> vac = vacRepo.findById(vacid);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Vaccine(Aşı) profil görüntüleme başarılı oldu. Görüntülenen Id: " + vacid);
            hm.put(RestEnum.result,vac.get());
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Vaccine(Aşı) profil görüntüleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    // Vac_select: if==1 -> add | if==2 -> update
    public Map<RestEnum, Object> vaccineStockAddUpdate(VaccineStockAdd vaccineStockAdd, String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer vac_id = Integer.parseInt(index);

        try {
            Date date = new Date();
            Vaccine vac = vacRepo.findById(vac_id).get();

            Storage storage = new Storage();
            storage.setStvac(vac);
            storage.setStdate(date);
            vaccineStockAdd.setVac_id(vac_id);

            if(vaccineStockAdd.getVac_select() == 1) {
                Integer temp = vac.getVacstock() + vaccineStockAdd.getVac_amount();
                vac.setVacstock(temp);
                storage.setStaction(1);
                storage.setStchangeamount(vaccineStockAdd.getVac_amount());
                storage.setStlastamount(temp);
            } else if ( vaccineStockAdd.getVac_select() == 2 ) {
                vac.setVacstock(vaccineStockAdd.getVac_amount());
                storage.setStaction(2);
                storage.setStchangeamount(vaccineStockAdd.getVac_amount());
                storage.setStlastamount(vaccineStockAdd.getVac_amount());
            }
            vacRepo.saveAndFlush(vac);
            strRepo.save(storage);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Depoya Vaccine(Aşı) ekleme / güncelleme başarılı.");
            hm.put(RestEnum.result,storage);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Depoya Vaccine(Aşı)  ekleme / güncelleme başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> vaccineUpdate(Vaccine vaccine, String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer vac_id = Integer.parseInt(index);

        try {
            vaccine.setVacid(vac_id);
            Vaccine vac = vacRepo.saveAndFlush(vaccine);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Vaccine(Aşı) başarıyla güncellendi.");
            hm.put(RestEnum.result,vac);

        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Vaccine(Aşı) güncelleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }

}

