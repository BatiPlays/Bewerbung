package com.homestream.HomeStream;

import com.homestream.HomeStream.main.assets.property.Properties;
import com.homestream.HomeStream.main.console.ConsoleHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class HomeStreamApplication{

	public static void main(String[] args)
	{

		System.out.println(Properties.VERSION);
		SpringApplication homeStream = new SpringApplication();

		Map<String, Object> properties = new HashMap<>();
		properties.put("spring.resources.static-locations", ".\\res\\css");

		homeStream.setDefaultProperties(properties);
		SpringApplication.run(HomeStreamApplication.class, args);

		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.run();
		
	}
}
