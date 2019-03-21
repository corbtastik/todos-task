package io.corbs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;


@EnableTask
@SpringBootApplication
@EnableConfigurationProperties({TodosTaskProperties.class})
public class TodosTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosTaskApplication.class, args);
    }

    @Bean
    public TodosTask todosTask(@Autowired TodosTaskProperties properties, @Autowired ObjectMapper objectMapper) {
        return new TodosTask(properties, objectMapper);
    }
}
