package com.exercice.pmu_database.repositories;

import com.exercice.pmu_database.entities.CourseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void persistCourseEntity(CourseEntity courseEntity){

        em.persist(courseEntity);

    }

}
