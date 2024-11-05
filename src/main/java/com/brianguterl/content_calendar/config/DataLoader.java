package com.brianguterl.content_calendar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//runs after dependency injection with commandLineRunner
@Component
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello, from commandLineRunner");
    }
}
