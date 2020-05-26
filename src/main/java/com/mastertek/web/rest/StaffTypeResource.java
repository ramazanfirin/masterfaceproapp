package com.mastertek.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mastertek.domain.StaffType;

import com.mastertek.repository.StaffTypeRepository;
import com.mastertek.web.rest.errors.BadRequestAlertException;
import com.mastertek.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing StaffType.
 */
@RestController
@RequestMapping("/api")
public class StaffTypeResource {

    private final Logger log = LoggerFactory.getLogger(StaffTypeResource.class);

    private static final String ENTITY_NAME = "staffType";

    private final StaffTypeRepository staffTypeRepository;

    public StaffTypeResource(StaffTypeRepository staffTypeRepository) {
        this.staffTypeRepository = staffTypeRepository;
    }

    /**
     * POST  /staff-types : Create a new staffType.
     *
     * @param staffType the staffType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new staffType, or with status 400 (Bad Request) if the staffType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/staff-types")
    @Timed
    public ResponseEntity<StaffType> createStaffType(@Valid @RequestBody StaffType staffType) throws URISyntaxException {
        log.debug("REST request to save StaffType : {}", staffType);
        if (staffType.getId() != null) {
            throw new BadRequestAlertException("A new staffType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StaffType result = staffTypeRepository.save(staffType);
        return ResponseEntity.created(new URI("/api/staff-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /staff-types : Updates an existing staffType.
     *
     * @param staffType the staffType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated staffType,
     * or with status 400 (Bad Request) if the staffType is not valid,
     * or with status 500 (Internal Server Error) if the staffType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/staff-types")
    @Timed
    public ResponseEntity<StaffType> updateStaffType(@Valid @RequestBody StaffType staffType) throws URISyntaxException {
        log.debug("REST request to update StaffType : {}", staffType);
        if (staffType.getId() == null) {
            return createStaffType(staffType);
        }
        StaffType result = staffTypeRepository.save(staffType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, staffType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /staff-types : get all the staffTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of staffTypes in body
     */
    @GetMapping("/staff-types")
    @Timed
    public List<StaffType> getAllStaffTypes() {
        log.debug("REST request to get all StaffTypes");
        return staffTypeRepository.findAll();
        }

    /**
     * GET  /staff-types/:id : get the "id" staffType.
     *
     * @param id the id of the staffType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the staffType, or with status 404 (Not Found)
     */
    @GetMapping("/staff-types/{id}")
    @Timed
    public ResponseEntity<StaffType> getStaffType(@PathVariable Long id) {
        log.debug("REST request to get StaffType : {}", id);
        StaffType staffType = staffTypeRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(staffType));
    }

    /**
     * DELETE  /staff-types/:id : delete the "id" staffType.
     *
     * @param id the id of the staffType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/staff-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteStaffType(@PathVariable Long id) {
        log.debug("REST request to delete StaffType : {}", id);
        staffTypeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
