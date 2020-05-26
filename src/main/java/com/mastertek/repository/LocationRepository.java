package com.mastertek.repository;

import com.mastertek.domain.Location;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Location entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("select distinct location from Location location left join fetch location.whiteLists")
    List<Location> findAllWithEagerRelationships();

    @Query("select location from Location location left join fetch location.whiteLists where location.id =:id")
    Location findOneWithEagerRelationships(@Param("id") Long id);

}
