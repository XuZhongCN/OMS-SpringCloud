package com.cpscs.omsbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.cpscs.*.dao"})
@SpringBootApplication
public class ClouddoBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClouddoBaseApplication.class, args);
	}
}
