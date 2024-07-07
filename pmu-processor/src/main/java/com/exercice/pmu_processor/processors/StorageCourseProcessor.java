package com.exercice.pmu_processor.processors;

import com.exercice.pmu_datamodel.models.Course;
import com.exercice.pmu_processor.client.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StorageCourseProcessor {

    @Autowired
    DatabaseService databaseService;

    public Course persistCourseIntoDatabase(Course course){

//        Traitement
        log.info("--------------- course : {}", course);

//        register course into database
        log.info("save course into database");
        databaseService.saveCourse(course);

        return course;
    }

}
