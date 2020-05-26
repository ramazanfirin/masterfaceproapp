package com.mastertek.repository;

import com.mastertek.domain.StaffType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StaffType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StaffTypeRepository extends JpaRepository<StaffType, Long> {

}
