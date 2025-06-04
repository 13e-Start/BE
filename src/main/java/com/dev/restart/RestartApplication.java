package com.dev.restart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RestartApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestartApplication.class, args);
		log.info("Re:Start 서버 시작");
	}
}
