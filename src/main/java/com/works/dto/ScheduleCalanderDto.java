package com.works.dto;

import com.works.entities.ScheduleCalendar;
import com.works.repositories.ScheduleCalendarRepository;
import com.works.utils.RestEnum;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleCalanderDto {

    final ScheduleCalendarRepository calendarRepo;

    public ScheduleCalanderDto(ScheduleCalendarRepository calendarRepo) {
        this.calendarRepo = calendarRepo;
    }

    public Map<RestEnum, Object> addCalendar(ScheduleCalendar scheduleCalendar) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            ScheduleCalendar c = calendarRepo.save(scheduleCalendar);

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Calendar(Takvim) başarıyla eklendi");
            hm.put(RestEnum.result,c);
        } catch (Exception e) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Calendar(Takvim) ekleme işlemi başarısız oldu. "+ e.getLocalizedMessage());
        }

        return hm;
    }


    public Map<RestEnum, Object> listCalendar() {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            List<ScheduleCalendar> ls = calendarRepo.findAll();

            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Calendar(Takvim) başarıyla listelendi.");
            hm.put(RestEnum.result,ls);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Calendar(Takvim) listeleme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }

    public Map<RestEnum, Object> singleCalendar(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        int id=Integer.parseInt(index);

        try {
            Optional<ScheduleCalendar> c= calendarRepo.findById(id);

            if (c .isPresent()){
                hm.put(RestEnum.status,"Success!");
                hm.put(RestEnum.message,"Calendar(Takvim) başarıyla bulundu.");
                hm.put(RestEnum.result,c);

            }else {
                hm.put(RestEnum.status,"Failed!");
                hm.put(RestEnum.message,"Calendar(Takvim) bulma işlemi başarısız oldu. Id: "+ id);
            }
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Calendar(Takvim) bulma işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }

        return hm;
    }



    public Map<RestEnum, Object> deleteCalendar(String index) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();
        int id= Integer.parseInt(index);

        try {
            calendarRepo.deleteById(id);
            hm.put(RestEnum.status,"Success!");
            hm.put(RestEnum.message,"Calendar(Takvim) başarıyla silindi. Silinen Id: "+ id);
        } catch (Exception ex) {
            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Calendar(Takvim) silme işlemi başarısız oldu."+ ex.getLocalizedMessage());
        }
        return hm;
    }



    public Map<RestEnum, Object> updateCalendar(ScheduleCalendar scheduleCalendar) {
        Map<RestEnum, Object> hm = new LinkedHashMap<>();

        try {
            if (scheduleCalendar.getCalendarId() !=null){
                ScheduleCalendar c =  calendarRepo.saveAndFlush(scheduleCalendar);
                hm.put(RestEnum.status,"Success!");
                hm.put(RestEnum.message,"Calendar(Takvim) başarıyla güncelleme..");
                hm.put(RestEnum.result,c);

            }else{
                ScheduleCalendar c =  calendarRepo.saveAndFlush(scheduleCalendar);
                hm.put(RestEnum.status,"Success!");
                hm.put(RestEnum.message,"Calendar(Takvim) başarıyla kaydedildi.");
                hm.put(RestEnum.result,c);
            }


        } catch (Exception ex) {

            hm.put(RestEnum.status,"Failed!");
            hm.put(RestEnum.message,"Calendar(Takvim) güncelleme işlemi başarısız oldu. "+ ex.getLocalizedMessage());
        }
        return hm;
    }




}
