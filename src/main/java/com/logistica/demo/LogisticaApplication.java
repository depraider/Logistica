package com.logistica.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.logistica.demo.util.BaseDeDatos;

@SpringBootApplication
public class LogisticaApplication {

	public static void main(String[] args) {
		BaseDeDatos db = new BaseDeDatos();

		db.inicializar();
		SpringApplication.run(LogisticaApplication.class, args);
	}

}
