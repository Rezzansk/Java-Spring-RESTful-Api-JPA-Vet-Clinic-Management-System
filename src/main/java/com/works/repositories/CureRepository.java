package com.works.repositories;

import com.works.entities.Cure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CureRepository extends JpaRepository<Cure,Integer> {

    List<Cure> findByPet_Pid(Integer pid);

    // active count
    @Query(value = "SELECT COUNT(cure.curid) FROM cure",nativeQuery = true)
    long totallab();

    @Query(value = "select c from Cure c order by c.curid DESC",nativeQuery = true)
    List<Cure> findByOrderByCuridDesc();


}
