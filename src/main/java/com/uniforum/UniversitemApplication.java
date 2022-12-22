package com.uniforum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class UniversitemApplication {
	public static void main(String[] args) {
		SpringApplication.run(UniversitemApplication.class, args);
	}

}
// PROJENİN ÇALIŞMASINI İSTİYORSANIZ VERİTABANI BAGLANTISINI YAPIN. BU PROJE MONGODB ATLAS VERİTABANINA GÖRE TASARLANDI.
