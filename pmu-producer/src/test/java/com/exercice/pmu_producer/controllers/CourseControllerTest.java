package com.exercice.pmu_producer.controllers;

import com.exercice.pmu_datamodel.models.Course;
import com.exercice.pmu_datamodel.models.Partant;
import com.exercice.pmu_producer.services.KafkaService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private KafkaService kafkaService;

    private Course courseLessThanThree;
    private Course course;

    @BeforeEach
    public void init(){

        courseLessThanThree = new Course();
        Partant partant1 = new Partant();
        Set<Partant> partants = new HashSet<>();
        Set<Partant> partants2 = new HashSet<>();

        partant1.setNom("Nom partant 1");
        partant1.setNumero("1");
        partants.add(partant1);

        courseLessThanThree.setNom("Nom course");
        courseLessThanThree.setDate("2024-07-07");
        courseLessThanThree.setNumero(1);
        courseLessThanThree.setPartants(partants);


        course = new Course();
        course.setNom("Nom course avec 3 partants ");
        course.setDate("2024-07-07");
        course.setNumero(1);

        Partant partant2 = new Partant();
        partant2.setNom("Nom partant 2");
        partant2.setNumero("2");

        Partant partant3 = new Partant();
        partant3.setNom("Nom partant 3");
        partant3.setNumero("3");

        partants2.add(partant1);
        partants2.add(partant2);
        partants2.add(partant3);

        course.setPartants(partants2);

    }

    /*
     *
     * Verification de l'insertion de la course dans kafka
     *
     * 200
     *
     */
    @Test
    void shoud_send_course() throws Exception {

        mvc.perform(post("/course/addcourse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(course))
                )
                .andExpect(status().is2xxSuccessful());
    }

    /*
    *
    * Verification de l'insertion de la course dans kafka
    *
    * bad request 400
    *
    * nombre de partants inferieur à 3
    *
     */
    @Test
    void shoud_return_bad_request_number_of_partants_less_than_3() throws Exception {

    mvc.perform(post("/course/addcourse")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(courseLessThanThree))
            )
            .andExpect(status().is4xxClientError());
    }
}