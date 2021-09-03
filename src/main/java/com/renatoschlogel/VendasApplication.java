package com.renatoschlogel;

import com.renatoschlogel.domain.entity.Cliente;
import com.renatoschlogel.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner vai(@Autowired ClienteRepository clienteRepository){
        return args -> {
            clienteRepository.save(new Cliente("Renato"));
        };
    }

    public static void main(String[] args) {
         SpringApplication.run(VendasApplication.class, args);
    }
}
