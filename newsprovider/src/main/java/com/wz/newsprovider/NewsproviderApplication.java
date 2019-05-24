package com.wz.newsprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
@MapperScan(basePackages = {"com.wz.newsprovider.dao"})
public class NewsproviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsproviderApplication.class, args);
	}


}
