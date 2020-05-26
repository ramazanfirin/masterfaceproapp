package com.mastertek.repository;

import com.mastertek.domain.Person;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Person entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select distinct person from Person person left join fetch person.staffTypes")
    List<Person> findAllWithEagerRelationships();

    @Query("select person from Person person left join fetch person.staffTypes where person.id =:id")
    Person findOneWithEagerRelationships(@Param("id") Long id);

}
