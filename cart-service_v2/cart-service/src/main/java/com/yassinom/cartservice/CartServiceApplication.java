package com.yassinom.cartservice;

import com.yassinom.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CartServiceApplication implements CommandLineRunner {
	@Autowired
	private CartService cartService;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("test");
	}

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

}
