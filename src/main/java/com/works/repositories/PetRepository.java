package com.works.repositories;


import com.works.projections.PetProjection;
import com.works.entities.Customer;
import com.works.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    // Cid e göre pet bilgileri
    @Query(value = "select pid,pname,pchip from Pet  where upper(Pet .cid) = upper(?1)",nativeQuery = true)
    List<PetProjection> findByCid_CidIsAllIgnoreCase(Customer customer);

    // Cid e göre
    List<Pet> findByCid_CidEquals(Integer cid);

    @Query("select p from Pet p where p.cid.cid = ?1")
    List<Pet> notstackoverflow(Integer cid);

    // active count
    @Query(value = "SELECT COUNT(pet.pid) FROM pet",nativeQuery = true)
    long totalpet();


}