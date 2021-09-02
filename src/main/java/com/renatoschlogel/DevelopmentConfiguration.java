package com.renatoschlogel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@ProfileDevelopment
public class DevelopmentConfiguration {

    @Bean
    public CommandLineRunner run(){

        return args -> {
            System.out.println("**** AMBIENTE DE DESENVOLVIMENTO ****");
        };
    }



}
