package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}	            	
}
//To Do: resolver problemas de CaseSentitive em atributos únicos
//To Do: resolver condições de corrida
//To Do: Tratar Exceptions
//To DO: otimizar transactions