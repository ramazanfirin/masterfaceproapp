package com.mastertek.web.rest;

import com.mastertek.MasterfaceproApp;

import com.mastertek.domain.StaffType;
import com.mastertek.repository.StaffTypeRepository;
import com.mastertek.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mastertek.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StaffTypeResource REST controller.
 *
 * @see StaffTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasterfaceproApp.class)
public class StaffTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private StaffTypeRepository staffTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStaffTypeMockMvc;

    private StaffType staffType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StaffTypeResource staffTypeResource = new StaffTypeResource(staffTypeRepository);
        this.restStaffTypeMockMvc = MockMvcBuilders.standaloneSetup(staffTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StaffType createEntity(EntityManager em) {
        StaffType staffType = new StaffType()
            .name(DEFAULT_NAME);
        return staffType;
    }

    @Before
    public void initTest() {
        staffType = createEntity(em);
    }

    @Test
    @Transactional
    public void createStaffType() throws Exception {
        int databaseSizeBeforeCreate = staffTypeRepository.findAll().size();

        // Create the StaffType
        restStaffTypeMockMvc.perform(post("/api/staff-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staffType)))
            .andExpect(status().isCreated());

        // Validate the StaffType in the database
        List<StaffType> staffTypeList = staffTypeRepository.findAll();
        assertThat(staffTypeList).hasSize(databaseSizeBeforeCreate + 1);
        StaffType testStaffType = staffTypeList.get(staffTypeList.size() - 1);
        assertThat(testStaffType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createStaffTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = staffTypeRepository.findAll().size();

        // Create the StaffType with an existing ID
        staffType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStaffTypeMockMvc.perform(post("/api/staff-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staffType)))
            .andExpect(status().isBadRequest());

        // Validate the StaffType in the database
        List<StaffType> staffTypeList = staffTypeRepository.findAll();
        assertThat(staffTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = staffTypeRepository.findAll().size();
        // set the field null
        staffType.setName(null);

        // Create the StaffType, which fails.

        restStaffTypeMockMvc.perform(post("/api/staff-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staffType)))
            .andExpect(status().isBadRequest());

        List<StaffType> staffTypeList = staffTypeRepository.findAll();
        assertThat(staffTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStaffTypes() throws Exception {
        // Initialize the database
        staffTypeRepository.saveAndFlush(staffType);

        // Get all the staffTypeList
        restStaffTypeMockMvc.perform(get("/api/staff-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staffType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getStaffType() throws Exception {
        // Initialize the database
        staffTypeRepository.saveAndFlush(staffType);

        // Get the staffType
        restStaffTypeMockMvc.perform(get("/api/staff-types/{id}", staffType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(staffType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStaffType() throws Exception {
        // Get the staffType
        restStaffTypeMockMvc.perform(get("/api/staff-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStaffType() throws Exception {
        // Initialize the database
        staffTypeRepository.saveAndFlush(staffType);
        int databaseSizeBeforeUpdate = staffTypeRepository.findAll().size();

        // Update the staffType
        StaffType updatedStaffType = staffTypeRepository.findOne(staffType.getId());
        // Disconnect from session so that the updates on updatedStaffType are not directly saved in db
        em.detach(updatedStaffType);
        updatedStaffType
            .name(UPDATED_NAME);

        restStaffTypeMockMvc.perform(put("/api/staff-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStaffType)))
            .andExpect(status().isOk());

        // Validate the StaffType in the database
        List<StaffType> staffTypeList = staffTypeRepository.findAll();
        assertThat(staffTypeList).hasSize(databaseSizeBeforeUpdate);
        StaffType testStaffType = staffTypeList.get(staffTypeList.size() - 1);
        assertThat(testStaffType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingStaffType() throws Exception {
        int databaseSizeBeforeUpdate = staffTypeRepository.findAll().size();

        // Create the StaffType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStaffTypeMockMvc.perform(put("/api/staff-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(staffType)))
            .andExpect(status().isCreated());

        // Validate the StaffType in the database
        List<StaffType> staffTypeList = staffTypeRepository.findAll();
        assertThat(staffTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStaffType() throws Exception {
        // Initialize the database
        staffTypeRepository.saveAndFlush(staffType);
        int databaseSizeBeforeDelete = staffTypeRepository.findAll().size();

        // Get the staffType
        restStaffTypeMockMvc.perform(delete("/api/staff-types/{id}", staffType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StaffType> staffTypeList = staffTypeRepository.findAll();
        assertThat(staffTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StaffType.class);
        StaffType staffType1 = new StaffType();
        staffType1.setId(1L);
        StaffType staffType2 = new StaffType();
        staffType2.setId(staffType1.getId());
        assertThat(staffType1).isEqualTo(staffType2);
        staffType2.setId(2L);
        assertThat(staffType1).isNotEqualTo(staffType2);
        staffType1.setId(null);
        assertThat(staffType1).isNotEqualTo(staffType2);
    }
}
