package com.exercice.pmu_database.mappers;

import com.exercice.pmu_database.entities.CourseEntity;
import com.exercice.pmu_database.models.Course;
import com.exercice.pmu_database.models.Partant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperTest {

    private Course course;

    @BeforeEach
    public void init(){

        course = new Course();
        course.setNom("Nom course");
        course.setDate("2024-07-07");
        course.setNumero(1);

        Partant partant = new Partant();
        partant.setNumero(1);
        partant.setNom("Partant 1");

        Partant partant2 = new Partant();
        partant2.setNumero(2);
        partant2.setNom("Partant 2");

        Partant partant3 = new Partant();
        partant3.setNumero(3);
        partant3.setNom("Partant 3");

        List<Partant> partants = new ArrayList<>();
        partants.add(partant);
        partants.add(partant2);
        partants.add(partant3);

        course.setPartants(partants);

    }

    @Test
    void asCourseEntity() {

        CourseEntity courseEntity = CourseMapper.INSTANCE.asEntity(course);

        assertEquals("Nom course", courseEntity.getNom());
        assertEquals(1, courseEntity.getNumero());
        assertEquals("2024-07-07", courseEntity.getDate());

        assertEquals(1, courseEntity.getPartants().get(0).getNumero());
        assertEquals("Partant 1", courseEntity.getPartants().get(0).getNom());
        assertEquals(2, courseEntity.getPartants().get(1).getNumero());
        assertEquals("Partant 2", courseEntity.getPartants().get(1).getNom());
        assertEquals(3, courseEntity.getPartants().get(2).getNumero());
        assertEquals("Partant 3", courseEntity.getPartants().get(2).getNom());

    }
}