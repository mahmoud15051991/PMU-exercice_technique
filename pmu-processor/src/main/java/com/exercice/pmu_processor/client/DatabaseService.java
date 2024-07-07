package com.exercice.pmu_processor.client;

import com.exercice.pmu_datamodel.models.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "database-service", url = "${database-service.url}")
public interface DatabaseService {

    @RequestMapping(method = RequestMethod.POST, value = "/course/saveCourse")
    void saveCourse(Course course);

}
