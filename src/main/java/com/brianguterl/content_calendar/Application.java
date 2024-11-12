package com.brianguterl.content_calendar;

import com.brianguterl.content_calendar.config.ContentCalendarProperties;
import com.brianguterl.content_calendar.model.Content;
import com.brianguterl.content_calendar.model.Status;
import com.brianguterl.content_calendar.model.Type;
import com.brianguterl.content_calendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;

@EnableConfigurationProperties(ContentCalendarProperties.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		RestTemplate restTemplate = (RestTemplate) context.getBean("restTemplate");
		System.out.println(restTemplate);

	}

	@Bean
	CommandLineRunner commandLineRunner(ContentRepository repository) {
		return args -> {
			//can insert data into database
			Content c = new Content(null,
					"Blog from commandLinerunner",
					"The description",
					Status.IDEA,
					Type.ARTICLE,
					LocalDateTime.now(),
					null,
					""
			);

			Content b = new Content( null,
					"2nd Blog from command line runner",
					"description",
					Status.IN_PROGRESS,
					Type.VIDEO,
					LocalDateTime.now(),
					null,
					"");

			repository.save(c);
			repository.save(b);
		};
	}

}
