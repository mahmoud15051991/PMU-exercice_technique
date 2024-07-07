package com.exercice.pmu_producer.controllers;

import com.exercice.pmu_datamodel.models.Course;
import com.exercice.pmu_producer.Exceptions.BadRequestException;
import com.exercice.pmu_producer.services.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/addcourse")
    public ResponseEntity<String> addCourse(@RequestBody Course course) throws BadRequestException, ExecutionException, InterruptedException {

        log.info("Course : {}", course);

        if(course == null)
            throw new BadRequestException("Course is empty");

        if(course.getPartants().size() < 3)
            throw new BadRequestException("the number of partants is less than 3 ");

        kafkaService.send(course);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
