package com.renatoschlogel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {

    @Bean
    public Animal gato(){
        return new Animal() {
            @Override
            public String fazerBarulho() {
                return "miau";
            }
        };
    }

    @Bean
    public Animal vaca(){
        return new Animal() {
            @Override
            public String fazerBarulho() {
                return "muuuuuu";
            }
        };
    }
}
