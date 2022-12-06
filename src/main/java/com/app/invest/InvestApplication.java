package com.app.invest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvestApplication {

    public static void main(String[] args) {
        System.out.println("oi");
        System.out.println("Diretorio = " + System.getProperty("user.dir"));

        SpringApplication.run(InvestApplication.class, args);
    }

}
