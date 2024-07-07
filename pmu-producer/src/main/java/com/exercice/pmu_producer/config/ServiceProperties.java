package com.exercice.pmu_producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("topics")
@Data
public class ServiceProperties {

    private String topicCourseName;
}
