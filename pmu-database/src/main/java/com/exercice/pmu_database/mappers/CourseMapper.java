package com.exercice.pmu_database.mappers;

import com.exercice.pmu_database.entities.CourseEntity;
import com.exercice.pmu_database.entities.PartantEntity;
import com.exercice.pmu_database.models.Course;
import com.exercice.pmu_database.models.Partant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseEntity asEntity(Course course);

}
