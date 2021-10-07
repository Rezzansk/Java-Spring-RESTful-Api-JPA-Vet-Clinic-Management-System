package com.works.dto;

import com.works.entities.Lab;
import com.works.properties.LabProp;
import com.works.repositories.LabRepository;
import com.works.repositories.PetRepository;
import com.works.utils.RestEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class LabDto {

    final LabRepository labRepository;
    final PetRepository petRepository;

    private final String UPLOAD_DIR =  "src/main/resources/static/uploads/";
    long maxFileUploadSize = 2048;

    int sendCount = 0;
    int sendSuccessCount = 0;
    String errorMessage = "";

    public LabDto(LabRepository labRepository, PetRepository petRepository) {
        this.labRepository = labRepository;
        this.petRepository = petRepository;
    }

    public Map<RestEnum, Object> upload(MultipartFile file, LabProp labProp) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        int sendSuccessCount = 0;
        String errorMessage = "";
        Lab lab=new Lab();

        if (!file.isEmpty() ) {
            long fileSizeMB = file.getSize() / 1024;
            if ( fileSizeMB > maxFileUploadSize ) {
                System.err.println("Dosya boyutu çok büyük Max 2MB");
                errorMessage = "Dosya boyutu çok büyük Max "+ (maxFileUploadSize / 1024) +"MB olmalıdır";
            }else {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String ext = fileName.substring(fileName.length()-5, fileName.length());
                String uui = UUID.randomUUID().toString();
                fileName = uui + ext;
                try {
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    sendSuccessCount += 1;

                    // add database
                    System.out.println(labProp);
                    //image.setPid(pid);
                    lab.setLcomment(labProp.getLcomment());
                    lab.setLtitle(labProp.getLtitle());
                    lab.setFileName(fileName);
                    lab.setPid( petRepository.findById(labProp.getPid()).get());
                    lab.setLdate(new Date());
                    labRepository.save(lab);

                    //image.setFileName(fileName);
                    //piRepo.save(image);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put(RestEnum.status, true);
            hm.put(RestEnum.message, "Yükleme Başarılı");
            hm.put(RestEnum.result, lab);
        }else {
            hm.put(RestEnum.status, false);
            hm.put(RestEnum.message, errorMessage);
        }

        return hm;
    }

    public Map<RestEnum, Object> delete(String iid) {

        Map<RestEnum, Object> hm =new LinkedHashMap<>();
        try {
            int ciid = Integer.parseInt( iid );
            Optional<Lab> opi = labRepository.findById(ciid);
            if ( opi.isPresent() ) {
                labRepository.deleteById(ciid);
                // file delete
                String deleteFilePath = opi.get().getFileName();
                File file = new File(UPLOAD_DIR+deleteFilePath);
                file.delete();
                hm.put(RestEnum.status,"succes");
                hm.put(RestEnum.message,"lab result deleted");
            }
        }catch (Exception ex) {
            hm.put(RestEnum.status,"succes");
            hm.put(RestEnum.message,"Silme işlemi sırasında bir hata oluştu!"+ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> profile(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer id = Integer.parseInt(index);

        try{
            Lab lab = labRepository.findById(id).get();

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Ajanda) profil görüntüleme başarılı oldu. Görüntülenen Id: " + id);
            hm.put(RestEnum.result,lab);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Ajanda) profil görüntüleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> list() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try{
            List<Lab> labList = labRepository.findByOrderByLidDesc();
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Ajanda) başarıyla listelendi.");
            hm.put(RestEnum.result,labList);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Ajanda) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> petLab(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        Integer id = Integer.parseInt(index);

        try{
            List<Lab> labList = labRepository.findByPid_PidEqualsOrderByLidDesc(id);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Supplier(Ajanda) profil görüntüleme başarılı oldu. Görüntülenen Id: " + id);
            hm.put(RestEnum.result,labList);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Supplier(Ajanda) profil görüntüleme işlemi başarısız oldu. Ex: "+ ex.getLocalizedMessage());
        }
        return hm;
    }

}
