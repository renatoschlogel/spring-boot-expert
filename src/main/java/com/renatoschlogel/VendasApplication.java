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
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {

        return args -> {

            Cliente cliente = new Cliente();
            cliente.setNome("Renato");
            clienteRepository.save(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Pedro");
            clienteRepository.save(cliente2);

            System.out.println("existe cliente com o nome Renato "
                    + clienteRepository.existsByNome("Renato"));
            System.out.println("existe cliente com o nome Pedro "
                    + clienteRepository.existsByNome("Pedrinho"));



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
