package com.emma.springcloud.mscv.usuarios.msvc_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcUsuariosApplication.class, args);
	}

}
