package com.renatoschlogel;

import com.renatoschlogel.domain.entity.Cliente;
import com.renatoschlogel.domain.entity.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {

        return args -> {

            Cliente cliente = new Cliente();
            cliente.setNome("Renato");
            clienteRepository.salvar(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Pedro");
            clienteRepository.salvar(cliente2);

            System.out.println("Listando clientes inseridos");
            List<Cliente> clientes = clienteRepository.obterTodos();
            clientes.forEach(System.out::println);

            clientes.forEach(c -> {
                c.setNome(c.getNome().concat(" atualizado"));
                clienteRepository.atualizar(c);
            });

            System.out.println("Listando clientes atualizados");

            clientes = clienteRepository.obterTodos();
            clientes.forEach(System.out::println);

            System.out.println("Buscando cliente renato");
            clientes = clienteRepository.buscarPorNome("Ren");
            clientes.forEach(System.out::println);

            clientes.stream().findFirst().ifPresent(c -> clienteRepository.deletar(c));

            System.out.println("Listando clientes restantes");
            clientes = clienteRepository.obterTodos();
            clientes.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
