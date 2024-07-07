package com.exercice.pmu_producer.services;

import com.exercice.pmu_datamodel.models.Course;
import com.exercice.pmu_producer.config.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Course> courseKafkaTemplate;

    @Autowired
    private ServiceProperties serviceProperties;

    public void send(Course message) throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, Course>> sendResult = courseKafkaTemplate.send(
                serviceProperties.getTopicCourseName(),
                message.getNumero() + "",
                message
        );
        sendResult.get();
    }

}
