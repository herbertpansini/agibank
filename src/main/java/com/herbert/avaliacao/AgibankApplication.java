package com.herbert.avaliacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.herbert.avaliacao.service.WatchDirService;

@SpringBootApplication
public class AgibankApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgibankApplication.class, args);
		
		new WatchDirService().main();
	}
}
