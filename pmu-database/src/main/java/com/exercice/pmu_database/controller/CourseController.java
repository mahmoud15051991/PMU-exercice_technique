package com.exercice.pmu_database.controller;

import com.exercice.pmu_database.entities.CourseEntity;
import com.exercice.pmu_database.mappers.CourseMapper;
import com.exercice.pmu_database.models.Course;
import com.exercice.pmu_database.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping(value = "saveCourse")
    public void saveCourse(@RequestBody Course course){

        log.info("Course ------- {} ", course);

        CourseEntity courseEntity = CourseMapper.INSTANCE.asEntity(course);

        courseEntity.getPartants().forEach(partant -> partant.setCourse(courseEntity));

        courseRepository.persistCourseEntity(courseEntity);

    }

}
