package com.brianguterl.content_calendar.config;

import com.brianguterl.content_calendar.model.Content;
import com.brianguterl.content_calendar.repository.ContentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

//runs after dependency injection with commandLineRunner
@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final ContentRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        try(InputStream inputSteam = TypeReference.class.getResourceAsStream("/data/content.json")) {
            repository.saveAll(objectMapper.readValue(inputSteam, new TypeReference<List<Content>>() {}));
        }
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("hello, from commandLineRunner");
//    }
}
