package com.dev.restart;

import com.dev.restart.global.env.EnvLoaderApplicationContextInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing
public class RestartApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RestartApplication.class);
		app.addInitializers(new EnvLoaderApplicationContextInitializer()); // .env 값 등록을 위한 커스텀 Initializer 등록
		app.run(args);

		log.info("Re:Start 서버 시작");
	}
}
