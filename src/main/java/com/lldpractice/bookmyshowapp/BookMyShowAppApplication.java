package com.lldpractice.bookmyshowapp;

import com.lldpractice.bookmyshowapp.controllers.UserController;
import com.lldpractice.bookmyshowapp.dto.LoginRequestDto;
import com.lldpractice.bookmyshowapp.dto.LoginResponseDto;
import com.lldpractice.bookmyshowapp.dto.SignUpRequestDto;
import com.lldpractice.bookmyshowapp.dto.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowAppApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;
	public static void main(String[] args) {

		SpringApplication.run(BookMyShowAppApplication.class, args);
	}
	@Override
	public void run(String... args) throws  Exception{
//		SignUpRequestDto request = new SignUpRequestDto();
//		request.setEmail("mjisthebest@gmail.com");
//		request.setPassword("mjboi");
//		SignUpResponseDto responseDto = userController.signUp(request);


		LoginRequestDto request = new LoginRequestDto();
		request.setEmail("mjisthebest@gmail.com");
		request.setPassword("mjboi");

		LoginResponseDto responseDto = userController.login(request);

		System.out.println(responseDto.getStatus()+  "  "  +responseDto.getUserId());
	}
}
